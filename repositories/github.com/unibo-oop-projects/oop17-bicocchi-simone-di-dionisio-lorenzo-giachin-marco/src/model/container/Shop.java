package model.container;

import java.util.List;
import java.util.Map;

/**
 * 
 * class shop for character's bought.
 *
 */
public class Shop extends ItemContainer {

    /**
     * 
     */
    private static final long serialVersionUID = -229759180253995753L;

    /**
     * 
     * @param newMap
     *            is the new list of categories
     */
    public Shop(final Map<String, List<Box>> newMap) {
        this.setMap(newMap);
    }

    @Override
    public String toString() {
        return "Shop [" + super.toString() + "]";
    }

}
