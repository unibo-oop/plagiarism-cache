package zombieversity.view.world.graphics;

import javafx.scene.image.WritableImage;
/**
 * Interface to model a TileSet, a tileset is a collection of images associated by an id.
 *
 */
public interface TileSet {
    /**
     * Method to load the source of the tileSet ( Image containing all the Tiles).
     * @param url - position of the image.
     */
    void loadImage(String url);
    /**
     * Once the image is loaded this method can be called to populate the tileSet with given properties.
     * @param tileSz
     */
    void loadTiles(int tileSz);
    /**
     * Method to get the corresponding Tile from the index.
     * @param index - of the tile
     * @return WritableImage 
     */

    WritableImage getTile(int index);

}
