package td.entities.tower;

import td.entities.enemy.Enemy;
import td.entities.projectile.ProjectileShadowBall;
import td.tile.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by andrew_sayegh on 6/6/17.
 */

public class MageTower extends Tower {

    public MageTower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileShadowBall(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
        super.target.reduceHiddenHealth(super.type.projectileType.damage);
    }
}
