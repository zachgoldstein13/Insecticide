package td.main;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import td.entities.enemy.Enemy;
import td.entities.Player;
import td.entities.WaveManager;
import td.entities.enemy.EnemyBeeticusBruticus;
import td.entities.enemy.EnemyQueenHornet;
import td.entities.tower.IceTower;
import td.entities.tower.MageTower;
import td.entities.tower.TowerPelletTree;
import td.entities.tower.TowerType;
import td.main.UI.Menu;
import td.main.state.StateManager;
import td.tile.TileGrid;

import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 6/1/17.
 */

public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Menu towerMenu;
    private Texture menuBackground;
    private Enemy[] enemyTypes;
    private IceTower iceTower;

    public Game(TileGrid grid) throws SlickException{
        this.grid = grid;
        this.enemyTypes = new Enemy[2];
        this.enemyTypes[1] = new EnemyQueenHornet(0, 0, grid);
        this.enemyTypes[0] = new EnemyBeeticusBruticus(quickLoad("sprite/enemy/BeeticusBruticus"), grid.getTile(0, 0),grid, TILE_SIZE, TILE_SIZE, 10, 50);
        this.waveManager = new WaveManager(enemyTypes, 5, 3);

        this.player = new Player(grid, waveManager);
        this.player.setup();

        this.menuBackground = quickLoad("background/BackgroundMain");

        setupUI();
    }

    private void setupUI() throws SlickException {
        gameUI = new UI();
        gameUI.createMenu("PickTower", WIDTH - TILE_SIZE * 3, HEIGHT / 8, TILE_SIZE * 3, HEIGHT, 2, 0);

        towerMenu = gameUI.getMenu("PickTower");
        towerMenu.quickAdd("CannonShadow", "sprite/tower/cannonBase");
        towerMenu.quickAdd("PelletTree", "sprite/particle/Wolf");
        towerMenu.quickAdd("IceTower", "sprite/particle/IceBolt");
    }

    private void updateUI() throws SlickException {
        gameUI.draw();
        gameUI.drawString(820, 400, "Lives: " + Player.Lives);
        gameUI.drawString(820, 500, "Gold: " + Player.Gold);
        gameUI.drawString(820, 600, "Wave: " + waveManager.getWaveNumber());
        gameUI.drawString(0, 0, StateManager.framesInLastSecond + "fps");

        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (towerMenu.isButtonClicked("CannonShadow"))
                    player.pickTower(new MageTower(TowerType.Mage, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemies()));
                if (towerMenu.isButtonClicked("PelletTree"))
                    player.pickTower(new TowerPelletTree(TowerType.PelletTree, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemies()));
                if (towerMenu.isButtonClicked("IceTower"))
                    player.pickTower(new IceTower(TowerType.PelletTree, grid.getTile(0, 0), waveManager.getCurrentWave().getEnemies()));
            }
        }
    }

    public void update() throws SlickException{
        drawQuadTex(menuBackground, 1280, 0, TILE_SIZE * 3, 960);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}
