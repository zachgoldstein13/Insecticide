package td.entities.tower;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import td.entities.enemy.Enemy;
import td.entities.projectile.ProjectileIceBall;
import td.entities.projectile.ProjectileShadowBall;
import td.tile.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by andrew_sayegh on 6/6/17.
 */

public class IceTower extends Tower {

    private SpriteSheet spritesheet;
    private Animation animation;
    public Image img;

    public IceTower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) throws SlickException {
        super(type, startTile, enemies);

        this.spritesheet = new SpriteSheet("res/sprite/tower/WaterLilly.png", 32, 32);
        this.animation = new Animation(spritesheet, new int[]{0, 0, 1, 0, 2, 0, 1, 0, 1, 1, 1, 2, 2, 0, 2, 1, 2, 2, 0, 3, 1, 3, 2, 3, 0, 3}, new int[]{400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400});
        this.img = spritesheet.getSubImage(0,0);

    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileIceBall(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }
    public void draw(){
        animation.draw(super.getX(), super.getY());
    }

}
