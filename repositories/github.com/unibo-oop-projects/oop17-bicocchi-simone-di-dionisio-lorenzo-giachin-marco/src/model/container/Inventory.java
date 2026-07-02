package model.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * class inventory of character.
 *
 */
public class Inventory extends ItemContainer {

    /**
     * 
     */
    private static final long serialVersionUID = 333332530134730794L;
    private Map<String, Box> mainItem = new HashMap<>();

    /**
     * constructor of inventory.
     * 
     * @param newMap
     *            is the list of stat used to add category to inventory
     */
    public Inventory(final Map<String, List<Box>> newMap) {
        this.attachCategories(newMap.keySet());
        for (String key : this.getAllCategories()) {
            mainItem.put(key, null);
        }

    }

    /**
     * 
     * @param category
     *            is the category of item
     * @param item
     *            is the new main item for this category
     */
    public void setMainItem(final String category, final Box item) {
        this.mainItem.put(category, item);
    }

    /**
     * 
     * @return the mainItemMap
     */
    public Map<String, Box> getMainItemMap() {
        return this.mainItem;
    }

    /**
     * 
     * @return main item for this category
     * @param category
     *            is the category of item
     */
    public Box getMainItem(final String category) {
        return this.mainItem.get(category);
    }

}
