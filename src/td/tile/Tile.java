package td.tile;

import org.newdawn.slick.opengl.Texture;

import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 5/31/17.
 */

public class Tile {

    private float x, y;
    private int width, height;
    private Texture texture;
    private TileType type;
    private boolean occupied;

    public Tile(float x, float y, int width, int height, TileType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = quickLoad(type.textureName);
        if (this.type.buildable)
            this.occupied = false;
        else
            this.occupied = true;
    }

    public void draw() {
        drawQuadTex(texture, x, y, width, height);
    }

    //int
    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getxPlace() {
        return (int) x / TILE_SIZE;
    }

    public int getyPlace() {
        return (int) y / TILE_SIZE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //void
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    //other
    public Texture getTexture() {
        return texture;
    }

    public TileType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
