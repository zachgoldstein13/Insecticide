package td.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import td.entities.tower.Tower;
import td.main.Clock;
import td.tile.Tile;
import td.tile.TileGrid;
import td.tile.TileType;

import java.util.ArrayList;

import static td.main.Window.HEIGHT;
import static td.main.Window.TILE_SIZE;

/**
 * Created by andrew_sayegh on 5/31/17.
 */
public class Player {

    private TileGrid grid;
    private TileType[] types;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, holdingTower;
    private Tower tempTower;
    public static int Gold, Lives;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Path;
        this.types[2] = TileType.Water;

        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.leftMouseButtonDown = false;
        this.holdingTower = false;
        this.tempTower = null;
        this.Gold = 0;
        this.Lives = 0;
    }

    //Initialize Gold and Live values
    public void setup() {
        Gold = 200;
        Lives = 10;
    }

    //Check if player can afford, if do charge player
    public static boolean modifyGold(int amount) {
        if (Gold + amount >= 0) {
            Gold += amount;
            System.out.println(Gold);
            return true;
        }
        return false;
    }

    public static void modifyLives(int amount) {
        Lives += amount;
    }

    public void update() {

        //Update holding tower
        if (holdingTower) {
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            tempTower.draw();
        }

        //Update all towers in the game
        for (Tower t : towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        //Handle mouse Input
        if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
            placeTower();
        }

        leftMouseButtonDown = Mouse.isButtonDown(0);

        //Handle key input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(-0.2f);
            }
        }
    }

    private void placeTower() {
        Tile currentTile = getMouseTile();
        if (holdingTower) {
            if (!currentTile.isOccupied() && modifyGold(-tempTower.getCost())) {
                towerList.add(tempTower);
                currentTile.setOccupied(true);
                holdingTower = false;
                tempTower = null;
            }
        }
    }

    public void pickTower(Tower t) {
        tempTower = t;
        holdingTower = true;
    }

    public Tile getMouseTile() {
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }
}
