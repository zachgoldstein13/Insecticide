package td.entities.projectile;

import org.newdawn.slick.opengl.Texture;

import static td.main.Window.quickLoad;

/**
 * Created by andrew_sayegh on 6/7/17.
 */

public enum ProjectileType {

    ShadowBall(quickLoad("sprite/particle/Wolf"), 10, 500),
    Pellet(quickLoad("sprite/particle/pellet"), 6, 800);

    public Texture texture;
    public int damage;
    public float speed;

    ProjectileType(Texture texture, int damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}
