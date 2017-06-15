package td.tile;

/**
 * Created by andrew_sayegh on 5/31/17.
 */
public enum TileType {

    Path("tile/Path", false), Grass("tile/Grass", true), Water("tile/Water", false), NULL("tile/Build", false);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
