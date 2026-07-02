package zombieversity.view.world.graphics;

import javafx.scene.paint.Color;
/**
 * 
 *
 */
public enum TileType {

    //Need to give them an int as index - probably static
    /**
     * integer is Tile purpose, Colour is MiniMap purpose.
     */
    WALL(100, Color.RED),
    /**
     * 
     */
    BOX(128, Color.RED),
    /**
     * 
     */
    PLAYER_SPAWN(450, Color.YELLOW),
    /**
     * 
     */
    ITEM(2, Color.WHITE),
    /**
     * 
     */
    ZOMBIE_SPAWN(357, Color.AZURE),
    /**
     * 
     */
    TERRAIN(4, Color.BROWN),
    /**
     * 
     */
    TREE(128, Color.LIGHTGREEN),
    /**
     * 
     */
    GRASS(3, Color.GREEN);


    private int id;
    private Color color;

    TileType(final int id, final Color color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return this.id;
    }

    public Color getColor() {
        return this.color;
    }
    public static TileType getFromId(final int id) {
        final TileType res = null;
        for (final TileType v : TileType.values()) {
            if (v.getId() == id) {
                return res;
            }
        }
        return res;
    }
}
