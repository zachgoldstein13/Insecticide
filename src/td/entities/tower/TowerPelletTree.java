package td.entities.tower;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import td.entities.enemy.Enemy;
import td.entities.projectile.ProjectileShadowBall;
import td.tile.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

import static td.main.Clock.delta;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class TowerPelletTree extends Tower {

    private SpriteSheet spritesheet;
    private Animation animation;

    public TowerPelletTree(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) throws SlickException {
        super(type, startTile, enemies);

        this.spritesheet = new SpriteSheet("res/sprite/tower/PelletTree.png", 32, 32);
        this.animation = new Animation(spritesheet, new int[]{0, 0, 1, 0, 0, 1, 1, 1}, new int[]{200, 200, 400, 400});

    }


    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileShadowBall(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }

    public void update() {
        super.update();

        try {
            animation.update((long) delta());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("L");
        }
    }

    @Override
    public void draw() {
        animation.draw(super.getX(), super.getY());
    }
}
