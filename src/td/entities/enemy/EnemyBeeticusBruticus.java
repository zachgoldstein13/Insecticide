package td.entities.enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import td.tile.TileGrid;

import static td.main.Clock.delta;
import static td.main.Window.TILE_SIZE;
import static td.main.Window.drawQuadTex;

/**
 * Created by andrew_sayegh on 6/12/17.
 */
public class EnemyBeeticusBruticus extends Enemy {

    private SpriteSheet spriteSheet;
    private Animation animation;

    public EnemyBeeticusBruticus(int tileX, int tileY, TileGrid grid) throws SlickException {
        super(tileX, tileY, grid);

        this.spriteSheet = new SpriteSheet("res/sprite/enemy/BeeticusBruticus.png", 32, 32);
        this.animation = new Animation(spriteSheet, new int[]{0, 0, 1, 0, 0, 1}, new int[]{200, 200, 200});
    }

    @Override
    public void update() {
        super.update();
        try {
            animation.update((long) delta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        float healthPercentage = super.getHealth() / super.getStartHealth();
        animation.draw(super.getX(), super.getY());
        drawQuadTex(super.getHealthBackground(), super.getX(), super.getY() - 16, super.getWidth(), 8);
        drawQuadTex(super.getHealthForeground(), super.getX(), super.getY() - 16, TILE_SIZE * healthPercentage, 8);
        drawQuadTex(super.getHealthBorder(), super.getX(), super.getY() - 16, super.getWidth(), 8);
    }
}
