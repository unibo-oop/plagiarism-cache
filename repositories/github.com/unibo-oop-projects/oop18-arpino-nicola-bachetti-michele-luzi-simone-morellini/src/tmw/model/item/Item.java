package tmw.model.item;

import tmw.model.entities.MilkEntity;
import tmw.model.objects.GameObject;

/**
 * Interface to represent the items obtainable in the map. The different
 * existing types, if necessary, will extend this interface.
 */
public interface Item extends GameObject {

    /**
     * @return the name of the item
     */
    String getName();

    /**
     * @return the description of the item
     */
    String getDescription();

    /**
     * @return the points to be added to the score given by the collection of the
     *         item
     */
    int getPoints();

    /**
     * Method to use the item.
     * 
     * @param milk The model of the milk character
     */
    void useItem(MilkEntity milk);

    /**
     * Method to use a specific item implemented by his class.
     */
    void useSpecificItem();
}

