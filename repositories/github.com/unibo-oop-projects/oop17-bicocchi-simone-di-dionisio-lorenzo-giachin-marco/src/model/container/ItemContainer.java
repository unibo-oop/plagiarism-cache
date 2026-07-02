package model.container;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * abstract class for shop and inventory.
 *
 */
public abstract class ItemContainer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7458544020440732549L;
    private Map<String, List<Box>> itemMap = new HashMap<>();

    @Override
    public String toString() {
        return "ItemContainer [itemMap=" + itemMap + "]";
    }

    /**
     * 
     * @param newMap
     *            is the new itemMap
     */
    public void setMap(final Map<String, List<Box>> newMap) {
        this.itemMap = newMap;
    }

    /**
     * 
     * @return map of category and respective list of items
     */
    public Map<String, List<Box>> getMap() {
        return this.itemMap;
    }

    /**
     * setting map of item with categories. * @param categories is the list of
     * categories for ItemContainer
     */
    public void attachCategories(final Set<String> categories) {
        for (String category : categories) {
            this.itemMap.put(category, new LinkedList<Box>());
        }

    }

    /**
     * 
     * @param category
     *            is the category
     * @param box
     *            is the item for category
     *
     */
    public void addItemForCategory(final Box box, final String category) {
        if (!this.itemMap.get(category).contains(box)) {
            if (box.getSecond() < 1) {
                box.increaseQuantity();
            }
            this.itemMap.get(category).add(box);
        } else {
            this.itemMap.get(category).stream().filter(pair -> pair.equals(box))
                    .forEach(pair -> pair.increaseQuantity());
        }
    }

    /**
     * 
     * @return collection of all categories
     */
    public Collection<String> getAllCategories() {
        return this.itemMap.keySet();
    }

}
