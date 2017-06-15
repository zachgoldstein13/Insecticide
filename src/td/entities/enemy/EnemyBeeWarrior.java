package td.entities.enemy;

import td.tile.TileGrid;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class EnemyBeeWarrior extends Enemy {
    public EnemyBeeWarrior(int tileX, int tileY, TileGrid grid) {
        super(tileX, tileY, grid);
        super.setTexture("sprite/enemy/Beewarrior");
    }
}
