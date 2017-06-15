package td.main.state;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import td.main.UI;
import td.main.UI.Menu;
import td.tile.TileGrid;
import td.tile.TileType;

import static td.main.Leveler.loadMap;
import static td.main.Leveler.saveMap;
import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 6/3/17.
 */

public class Editor {

    private TileGrid grid;
    private int index;
    private TileType[] types;
    private UI editorUI;
    private Menu tileMenu;
    private Texture menuBackground;

    public Editor() {
//        this.grid = loadMap("newMap1");
        this.grid = new TileGrid();
        this.index = 0;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Path;
        this.types[2] = TileType.Water;
//        menuBackground = quickLoad("");
        setupUI();
    }

    private void setupUI() {
        editorUI = new UI();

        editorUI.createMenu("TilePicker", WIDTH - TILE_SIZE * 3, 100, TILE_SIZE * 3, HEIGHT, 2, 0);
        tileMenu = editorUI.getMenu("TilePicker");
        tileMenu.quickAdd("Grass", "tile/Grass");
        tileMenu.quickAdd("Path", "tile/Path");
        tileMenu.quickAdd("Water", "tile/Water");
    }

    public void update() {
        draw();

        //Handle mouse Input
        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (tileMenu.isButtonClicked("Grass")) {
                    index = 0;
                }
                else  if (tileMenu.isButtonClicked("Path")){
                    index = 1;
                } else if (tileMenu.isButtonClicked("Water")){
                    index = 2;
                }
                else
                    setTile();
            }
        }

        //Handle key input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                moveIndex();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
                System.out.println("Saving map...");
                saveMap("newMap1", grid);
                System.out.println("Saving complete");
            }
        }
    }
    private void draw(){
//        drawQuadTex(menuBackground, WIDTH - TILE_SIZE * 3, 0, TILE_SIZE * 3, HEIGHT);
        grid.draw();
        editorUI.draw();
    }

    private void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
                types[index]);
    }

    //Allows for the changing of tiles in editor
    public void moveIndex() {
        index++;
        if (index > types.length - 1)
            index = 0;
    }
}
