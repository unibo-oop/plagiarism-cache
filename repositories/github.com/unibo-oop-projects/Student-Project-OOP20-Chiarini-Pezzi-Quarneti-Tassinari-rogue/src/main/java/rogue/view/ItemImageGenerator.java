package rogue.view;

import javafx.scene.image.Image;
import rogue.model.items.Item;

/**
 * An interface for an ItemImageGenerator.
 * 
 */
public interface ItemImageGenerator {

    /**
     * Use this method to get the image of the wanted item.
     * @param item you want the image of.
     * @return image of the requested item.
     */
    Image getImage(Item item);

}
