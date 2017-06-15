package td.tile;

/**
 * Created by andrew_sayegh on 5/31/17.
 */
public class Checkpoint {

    private Tile tile;
    private int xDir, yDir;

    public Checkpoint(Tile tile, int xDir, int yDir) {
        this.tile = tile;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public Tile getTile() { return tile; }
    public int getxDir() {
        return xDir;
    }
    public int getyDir() {
        return yDir;
    }
}
