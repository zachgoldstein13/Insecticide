package td.entities.projectile;

import td.entities.enemy.Enemy;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class ProjectilePellet extends Projectile {

    public ProjectilePellet(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override

    public void damage(){
        super.damage();
    }
}
