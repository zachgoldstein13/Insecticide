package td.entities.projectile;

import org.newdawn.slick.opengl.Texture;
import td.entities.enemy.Enemy;
import td.entities.Entity;

import static td.main.Clock.delta;
import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 6/1/17.
 */

public abstract class Projectile implements Entity {

    private Texture texture;
    private float speed, x, y, xVelocity, yVelocity;
    private int width, height, damage;
    private Enemy target;
    private boolean alive;

    public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        this.texture = type.texture;
        this.damage = type.damage;
        this.target = target;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = type.speed;
        this.alive = true;

        this.xVelocity = 0f;
        this.yVelocity = 0f;

        calculateDirection();
    }

    private void calculateDirection() {
        float totalAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
        float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
        float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;

        xVelocity = xPercentOfMovement;
        yVelocity = totalAllowedMovement - xPercentOfMovement;

        //Set direction based on position of target relative to tower
        if (target.getX() < x)
            xVelocity *= -1;
        if (target.getY() < y)
            yVelocity *= -1;
    }

    //Deal damage to enemy
    public void damage() {
        target.damage(damage);
        alive = false;
    }

    public void update() {
        if (alive) {
            calculateDirection();
            x += xVelocity * speed * delta();
            y += yVelocity * speed * delta();
            if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
                damage();
            }
            draw();
        }
    }

    public void draw() {
        drawQuadTex(texture, x, y, TILE_SIZE / 2 , TILE_SIZE / 2);
    }

    public float getX() {
        return y;
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

    public void setAlive(boolean status) {
        this.alive = status;
    }
}
