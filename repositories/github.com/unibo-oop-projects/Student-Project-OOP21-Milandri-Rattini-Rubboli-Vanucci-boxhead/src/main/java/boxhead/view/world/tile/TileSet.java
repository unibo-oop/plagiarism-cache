package boxhead.view.world.tile;

import javafx.scene.image.Image;

/*
 * Interface to create a set of tile, a collection of image
 *
 */
public interface TileSet {
    /*
     * Method to load the image of a tileSet.
     */
    void loadImage();

    /**
     * Method to get a tile from the index
     *
     * @param index
     * @return Image
     */
    Image getTile(int index);
}