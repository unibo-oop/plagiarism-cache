package tmw.controller.item;

import tmw.model.item.Item;

/**
 * Interface that handle the items.
 *
 * @param <K> type of item
 */
public interface ItemController<K extends Item> {

    /**
     * Getter for the item.
     * 
     * @return the item
     */
    K getItem();

}
