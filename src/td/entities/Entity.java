package td.entities;

/**
 * Created by andrew_sayegh on 6/6/17.
 */
@SuppressWarnings("warnning")
public interface Entity {

    public void update();
    public void draw();

    public float getX();
    public float getY();

    public int getWidth();
    public int getHeight();

    public void setX(float x);
    public void setY(float y);

    public void setWidth(int width);
    public void setHeight(int height);
}
