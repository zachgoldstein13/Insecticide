package td.entities.projectile;

import td.entities.enemy.Enemy;

/**
 * Created by andrew_sayegh on 6/6/17.
 */

public class ProjectileIceBall extends Projectile {

    public ProjectileIceBall(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void damage() {
        super.getTarget().setSpeed(3f);
        super.damage();
    }
}
