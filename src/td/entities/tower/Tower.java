package td.entities.tower;

import org.newdawn.slick.opengl.Texture;
import td.entities.enemy.Enemy;
import td.entities.Entity;
import td.entities.projectile.Projectile;
import td.tile.Tile;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static td.main.Clock.delta;
import static td.main.Window.drawQuadTex;
import static td.main.Window.drawQuadTexRot;

/**
 * Created by andrew_sayegh on 6/6/17.
 */

public abstract class Tower implements Entity {

    private float x, y, timeSinceLastShot, firingSpeed, angle;
    private int width, height, range, cost;
    public Enemy target;
    private Texture[] textures;
    private ArrayList<Enemy> enemies;
    private boolean targeted;
    public ArrayList<Projectile> projectiles;
    public TowerType type;

    public Tower(TowerType type, Tile startTile, ArrayList<Enemy> enemies) {
        this.type = type;
        this.textures = type.textures;
        this.cost = type.cost;
        this.range = type.range;

        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();

        this.enemies = enemies;
        this.targeted = false;
        this.timeSinceLastShot = 0f;
        this.projectiles = new ArrayList<Projectile>();
        this.firingSpeed = type.firingSpeed;
        this.angle = 0f;
    }

    private Enemy acquireTarget() {
        Enemy closest = null;
        //Arbitrary distance
        float closestDistance = 600;
        //Go through each enemy and return nearest one
        for (Enemy e : enemies) {
            if (isInRange(e) && findDistance(e) < closestDistance && e.getHiddenHealth() > 0) {
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        //If an enemy exists and is returned, targeted == true
        if (closest != null)
            targeted = true;
        return closest;
    }

    private boolean isInRange(Enemy e) {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        if (xDistance < range && yDistance < range)
            return true;
        return false;
    }

    private float findDistance(Enemy e) {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return xDistance + yDistance;
    }

    private float calculateAngle() {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    //Abstract method for 'shoot', must be overwritten in subclasses
    public abstract void shoot(Enemy target);

    public void updateEnemyList(ArrayList<Enemy> newList) {
        enemies = newList;
    }

    public void update() {
        if (!targeted || target.getHiddenHealth() < 0) {
            target = acquireTarget();
        } else {
            angle = calculateAngle();
            if (timeSinceLastShot > firingSpeed) {
                shoot(target);
                timeSinceLastShot = 0;
            }
        }

        if (target == null || target.isAlive() == false)
            targeted = false;

        timeSinceLastShot += delta();
        for (Projectile p : projectiles)
            p.update();

        draw();
    }


    public void draw() {
        drawQuadTex(textures[0], x, y, width, height);
        if (textures.length > 1) {
            for (int i = 1; i < textures.length; i++) {
                drawQuadTexRot(textures[i], x, y, width, height, angle);
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Enemy getTarget() {
        return target;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }
}