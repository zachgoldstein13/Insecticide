package td.entities.enemy;

import td.tile.TileGrid;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class EnemyBeeticusBruticus extends Enemy {
    public EnemyBeeticusBruticus(int tileX, int tileY, TileGrid grid) {
        super(tileX, tileY, grid);

        super.setTexture("sprite/enemy/BeeticusBruticus");
    }
}
