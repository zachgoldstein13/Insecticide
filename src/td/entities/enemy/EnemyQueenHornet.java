package td.entities.enemy;

import td.tile.TileGrid;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class EnemyQueenHornet extends Enemy {

    public EnemyQueenHornet(int tileX, int tileY, TileGrid grid) {
        super(tileX, tileY, grid);
        super.setTexture("sprite/enemy/QueenHornet");
        super.setHealth(100);
        super.setSpeed(8);
    }
}
