package td.main;

import td.tile.Tile;
import td.tile.TileGrid;
import td.tile.TileType;

import java.io.*;

/**
 * Created by andrew_sayegh on 6/4/17.
 */
public class Leveler {

    public static void saveMap(String mapName, TileGrid grid) {
        String mapData = "";
        for (int i = 0; i < grid.getTilesWide(); i++) {
            for (int j = 0; j < grid.getTilesHigh(); j++) {
                mapData += getTileID(grid.getTile(i, j));
            }
        }

        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TileGrid loadMap(String mapName) {
        TileGrid grid = new TileGrid();
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < grid.getTilesWide(); i++) {
                for (int j = 0; j < grid.getTilesHigh(); j++) {
                    grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;
    }

    public static TileType getTileType(String ID) {
        TileType type = TileType.NULL;

        switch (ID) {
            case "0":
                type = TileType.Grass;
                break;
            case "1":
                type = TileType.Path;
                break;
            case "2":
                type = TileType.Water;
                break;
            case "3":
                type = TileType.NULL;
                break;
        }

        return type;
    }

    public static String getTileID(Tile t) {
        String ID = null;

        switch (t.getType()) {
            case Grass:
                ID = "0";
                break;
            case Path:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
            case NULL:
                ID = "3";
                break;
        }
        return ID;
    }
}
