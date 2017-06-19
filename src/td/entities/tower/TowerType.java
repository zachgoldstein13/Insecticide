package td.entities.tower;

import org.newdawn.slick.opengl.Texture;
import td.entities.projectile.ProjectileType;

import static td.main.Window.quickLoad;

/**
 * Created by andrew_sayegh on 6/6/17.
 */

public enum TowerType {

    PelletTree(ProjectileType.Pellet, 5, 600, 1, 50),
    Mage(new Texture[]{quickLoad("sprite/tower/cannonBase"), quickLoad("sprite/tower/cannonGun")}, ProjectileType.ShadowBall, 5, 600, 3, 50),
    IceTower(ProjectileType.Iceball,5,600,3,50);

    Texture[] textures;
    ProjectileType projectileType;
    int damage, range, cost;
    float firingSpeed;

    TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
        this.textures = textures;
        this.projectileType = projectileType;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.firingSpeed = firingSpeed;
    }

    TowerType(ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
        this.projectileType = projectileType;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.firingSpeed = firingSpeed;
    }
}

