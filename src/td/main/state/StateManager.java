package td.main.state;

import org.newdawn.slick.SlickException;
import td.main.Game;
import td.main.Leveler;
import td.tile.TileGrid;

/**
 * Created by andrew_sayegh on 6/3/17.
 */
public class StateManager {

    public static enum GameState {
        MAINMENU, GAME, EDITOR
    }

    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;

    public static long nextSec = System.currentTimeMillis() + 1000;
    public static int framesInLastSecond = 0;
    public static int framesInCurrentSecond = 0;


    static TileGrid map = Leveler.loadMap("newMap1");

    public static void update() throws SlickException {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null)
                    mainMenu = new MainMenu();
                mainMenu.update();
                break;
            case GAME:
                if (game == null)
                    game = new Game(map);
                game.update();
                break;
            case EDITOR:
                if (editor == null)
                    editor = new Editor();
                editor.update();
                break;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime > nextSec) {
            nextSec += 1000;
            framesInLastSecond = framesInCurrentSecond;
            framesInCurrentSecond  = 0;
        }
        framesInCurrentSecond++;
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }
}
