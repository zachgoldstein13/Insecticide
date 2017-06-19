package td.main.state;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import td.main.UI;

import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 6/3/17.
 */
public class MainMenu {

    private Texture background;
    private UI menuUI;

    public MainMenu() {
        background = quickLoad("background/BackgroundMain");
        menuUI = new UI();
        menuUI.addButton("Play", "sprite/play_button", WIDTH / 2 - 128, (int) (HEIGHT * 0.25f));
        menuUI.addButton("Editor", "sprite/Play", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
        menuUI.addButton("Exit", "sprite/Play", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
    }

    //Check if button is pressed
    private void updateButtons() {
        if (Mouse.isButtonDown(0)) {
            if (menuUI.isButtonClicked("Play")) {
                StateManager.setState(StateManager.GameState.GAME);
            } else if (menuUI.isButtonClicked("Editor")) {
                StateManager.setState(StateManager.GameState.EDITOR);
            } else if (menuUI.isButtonClicked("Exit")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        if (background != null) {
            drawQuadTex(background, 0, 0, 0, 0);
        }
        drawQuad(0, 0, WIDTH, HEIGHT);
        menuUI.draw();
        updateButtons();
    }
}
