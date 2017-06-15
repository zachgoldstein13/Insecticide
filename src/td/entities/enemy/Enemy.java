package td.entities.enemy;

import org.newdawn.slick.opengl.Texture;
import td.entities.Entity;
import td.entities.Player;
import td.tile.Checkpoint;
import td.tile.Tile;
import td.tile.TileGrid;

import java.util.ArrayList;

import static td.main.Clock.delta;
import static td.main.Window.*;

/**
 * Created by andrew_sayegh on 5/31/17.
 */

public class Enemy implements Entity {

    private int width, height, currentCheckpoint;
    private float speed, x, y, health, startHealth, hiddenHealth;
    private Texture texture, healthBackground, healthForeground, healthBorder;
    private Tile startTile;
    private static boolean first, alive;
    private TileGrid grid;

    private ArrayList<Checkpoint> checkpoints;
    private int[] directions;


    public Enemy(int tileX, int tileY, TileGrid grid){
        this.texture = quickLoad("sprite/enemy/QueenHornet");
        this.healthBackground = quickLoad("sprite/healthBackground");
        this.healthForeground = quickLoad("sprite/healthForeground");
        this.healthBorder = quickLoad("sprite/healthBorder");
        this.startTile = grid.getTile(tileX, tileY);
        this.x = startTile.getX();
        this.y = startTile.getY();

        this.width = TILE_SIZE;
        this.height = TILE_SIZE;

        this.speed = 50;
        this.health = 50;
        this.startHealth = health;
        this.hiddenHealth = health;
        this.grid = grid;
        this.checkpoints = new ArrayList<Checkpoint>();
        this.directions = new int[2];

        //x direction
        this.directions[0] = 0;
        //y direction
        this.directions[1] = 0;

        this.directions = findNextDir(startTile);
        this.currentCheckpoint = 0;

        populateCheckpointList();
    }

    public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
        this.texture = texture;
        this.healthBackground = quickLoad("sprite/healthBackground");
        this.healthForeground = quickLoad("sprite/healthForeground");
        this.healthBorder = quickLoad("sprite/healthBorder");

        this.first = true;
        this.alive = true;

        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();

        this.width = width;
        this.height = height;

        this.speed = speed;
        this.health = health;
        this.startHealth = health;
        this.hiddenHealth = health;

        this.grid = grid;
        this.checkpoints = new ArrayList<Checkpoint>();
        this.directions = new int[2];

        //x direction
        this.directions[0] = 0;
        //y direction
        this.directions[1] = 0;

        this.directions = findNextDir(startTile);
        this.currentCheckpoint = 0;

        populateCheckpointList();
    }


    public void update() {
        //Check if it's the first time the class is updated, if so do nothing
        if (first) {
            first = false;
        } else {
            if (checkpointReached()) {
                //Check if there are more checkpoints before moving on
                if (currentCheckpoint + 1 == checkpoints.size()) {
                    endOfMazeReached();
                } else {
                    currentCheckpoint++;
                }
            } else {
                //If not at a checkpoint, continue in current direction
                x += delta() * checkpoints.get(currentCheckpoint).getxDir() * speed;
                y += delta() * checkpoints.get(currentCheckpoint).getyDir() * speed;
            }
        }
    }

    //Run when last checkpoint is reached by enemy
    private void endOfMazeReached() {
        Player.modifyLives(-1);
        die();
    }

    private boolean checkpointReached() {
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        //Check if position reached tile within variance of 3
        if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
            reached = true;
            x = t.getX();
            y = t.getY();
        }
        return reached;
    }

    private void populateCheckpointList() {
        //Add first checkpoint
        checkpoints.add(findNextCheck(startTile, directions = findNextDir(startTile)));
        int counter = 0;
        boolean cont = true;
        while (cont) {
            int[] currentDir = findNextDir(checkpoints.get(counter).getTile());
            // Check id next direction checkpoint exists end after 20
            if (currentDir[0] == 2 || counter == 20) {
                cont = false;
            } else {
                checkpoints.add(findNextCheck(checkpoints.get(counter).getTile(),
                        directions = findNextDir(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }

    private Checkpoint findNextCheck(Tile tile, int[] dir) {
        Tile next = null;
        Checkpoint check = null;

        // check if next checkpoint is found
        boolean found = false;
        //Increment each loop
        int counter = 1;

        while (!found) {
            if (tile.getxPlace() + dir[0] * counter == grid.getTilesWide() ||
                    tile.getyPlace() + dir[1] * counter == grid.getTilesHigh() ||
                    tile.getType() != grid.getTile(tile.getxPlace() + dir[0] * counter,
                            tile.getyPlace() + dir[1] * counter).getType()) {

                found = true;
                // Move back 1 before new tiletype
                counter -= 1;
                next = grid.getTile(tile.getxPlace() + dir[0] * counter, tile.getyPlace() + dir[1] * counter);
            }
            counter++;

        }
        check = new Checkpoint(next, dir[0], dir[1]);
        return check;
    }

    private int[] findNextDir(Tile tile) {
        int[] dir = new int[2];
        Tile u = grid.getTile(tile.getxPlace(), tile.getyPlace() - 1);
        Tile r = grid.getTile(tile.getxPlace() + 1, tile.getyPlace());
        Tile d = grid.getTile(tile.getxPlace(), tile.getyPlace() + 1);
        Tile l = grid.getTile(tile.getxPlace() - 1, tile.getyPlace());

        //Check if current inhabited tiletype matches: above, down, left or right
        if (tile.getType() == u.getType() && directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (tile.getType() == r.getType() && directions[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (tile.getType() == d.getType() && directions[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (tile.getType() == l.getType() && directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }
        return dir;
    }

    //Take damage from external source
    public void damage(int amount) {
        health -= amount;
        if (health <= 0) {
            die();
            Player.modifyGold(5);
        }
    }

    private void die() {
        alive = false;
    }

    public void draw() {
        float healthPercentage = health / startHealth;
        //Enemy texture
        drawQuadTex(texture, x, y, width, height);
        //Enemy health-bar texture
        drawQuadTex(healthBackground, x, y - 16, width, 8);
        drawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
        drawQuadTex(healthBorder, x, y - 16, width, 8);
    }

    public void reduceHiddenHealth(float amount) {
        hiddenHealth -= amount;
    }


    //Getters and setters
    //Ints
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public int[] getDirections() {
        return directions;
    }

    //Floats
    public float getSpeed() {
        return speed;
    }

    public float getHealth() {
        return health;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHiddenHealth(){
        return hiddenHealth;
    }

    //Void
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

    public void setHealth(float health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setTexture(String textureName) { this.texture = quickLoad(textureName); }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public static void setFirst(boolean first) {
        Enemy.first = first;
    }

    public static void setAlive(boolean alive) {
        Enemy.alive = alive;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void setCurrentCheckpoint(int currentCheckpoint) {
        this.currentCheckpoint = currentCheckpoint;
    }

    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public void setDirections(int[] directions) {
        this.directions = directions;
    }

    //Boolean
    public static boolean isFirst() {
        return first;
    }

    public static boolean isAlive() {
        return alive;
    }

    //Other
    public Texture getTexture() {
        return texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public TileGrid getGrid() {
        return grid;
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
