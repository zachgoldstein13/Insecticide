package td.tile;

import static td.main.Window.TILE_SIZE;

/**
 * Created by andrew_sayegh on 5/31/17.
 */

public class TileGrid {

    public Tile[][] map;
    private int tilesWide;
    private int tilesHigh;

    public TileGrid() {
        this.tilesWide = 25;
        this.tilesHigh = 20;
        map = new Tile[tilesWide][tilesHigh];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
            }
        }
    }

    public TileGrid(int[][] newmap) {
        this.tilesWide = newmap[0].length;
        this.tilesHigh = newmap.length;
        map = new Tile[tilesWide][tilesHigh];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (newmap[j][i]) {
                    case 0:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Grass);
                        break;
                    case 1:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Path);
                        break;
                    case 2:
                        map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Water);
                        break;
                }
            }
        }
    }

    public void setTile(int xCoord, int yCoord, TileType type) {
        map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);
    }

    public Tile getTile(int xPlace, int yPlace) {
        if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
            return map[xPlace][yPlace];
        else {
            return new Tile(0, 0, 0, 0, TileType.NULL);
        }
    }


    public void draw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].draw();
            }
        }
    }

    //int
    public int getTilesWide() {
        return tilesWide;
    }

    public int getTilesHigh() {
        return tilesHigh;
    }

    //void
    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public void setTilesWide(int tilesWide) {
        this.tilesWide = tilesWide;
    }

    public void setTilesHigh(int tilesHigh) {
        this.tilesHigh = tilesHigh;
    }

    //other
    public Tile[][] getMap() {
        return map;
    }

}
