package td.main;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;

import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 6/3/17.
 */
public class UI {

    private ArrayList<Button> buttons;
    private ArrayList<Menu> menuList;
    private TrueTypeFont font;
    private Font awtFont;

    public UI() {
        buttons = new ArrayList<Button>();
        menuList = new ArrayList<Menu>();
        awtFont = new Font("Times New Ronman", Font.BOLD, 10);
        font = new TrueTypeFont(awtFont, false);
    }

    public void drawString(int x, int y, String text){
        font.drawString(x, y, text);
    }

    public void addButton(String name, String textureName, int x, int y) {
        buttons.add(new Button(name, quickLoad(textureName), x, y));
    }

    public boolean isButtonClicked(String buttonName) {
        Button b = getButton(buttonName);
        float mouseY = HEIGHT - Mouse.getY() - 1;
        if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
            return true;
        }
        return false;
    }

    private Button getButton(String buttonName) {
        for (Button b : buttons) {
            if (b.getName().equals(buttonName)) {
                return b;
            }
        }
        return null;
    }

    public void createMenu(String name, int x, int y, int width, int height, int optionWidth, int optionHeight) {
        menuList.add(new Menu(name, x, y, width, height, optionWidth, optionHeight));
    }

    public Menu getMenu(String name) {
        for (Menu m : menuList)
            if (name.equals(m.getName()))
                return m;
        return null;
    }

    public void draw() {
        for (Button b : buttons)
            drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());

        for (Menu m : menuList)
            m.draw();
    }

    public class Menu {

        String name;
        private ArrayList<Button> menuButtons;
        private int x, y, width, height, buttonAmount, optionWidth, optionHeight, padding;

        public Menu(String name, int x, int y, int width, int heigh, int optionWidth, int optionHeight) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = heigh;
            this.buttonAmount = 0;
            this.optionWidth = optionWidth;
            this.optionHeight = optionHeight;
            this.padding = (width - (optionWidth * TILE_SIZE)) / (optionWidth + 1);
            this.menuButtons = new ArrayList<Button>();
        }

        public void addButton(Button b) {
           setButton(b);
        }

        public void quickAdd(String name, String buttonTextureName){
            Button b = new Button(name, quickLoad(buttonTextureName), 0, 0);
            setButton(b);
        }

        private void setButton(Button b){
            if (optionWidth != 0)
                b.setY(y + (buttonAmount / optionWidth) * TILE_SIZE);
            b.setX(x + (buttonAmount % 2) * (padding + TILE_SIZE) + padding);
            buttonAmount++;
            menuButtons.add(b);
        }

        public boolean isButtonClicked(String buttonName) {
            Button b = getButton(buttonName);
            float mouseY = HEIGHT - Mouse.getY() - 1;
            if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() && mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
                return true;
            }
            return false;
        }

        private Button getButton(String buttonName) {
            for (Button b : menuButtons) {
                if (b.getName().equals(buttonName)) {
                    return b;
                }
            }
            return null;
        }

        public void draw() {
            for (Button b : menuButtons) {
                drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }

        public String getName() {
            return name;
        }
    }
}
