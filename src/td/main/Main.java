package td.main;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;
import td.main.state.StateManager;


import static td.main.Window.*;

public class Main {

    public Main() throws SlickException{

        //Call static method in Window Class to Initialize OpenGL calls
        beginSession();

        //Main game loop
        while (!Display.isCloseRequested()) {
            Clock.update();
            StateManager.update();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    //ye
    public static void main(String[] args)throws SlickException {
       new Main();
    }
}
