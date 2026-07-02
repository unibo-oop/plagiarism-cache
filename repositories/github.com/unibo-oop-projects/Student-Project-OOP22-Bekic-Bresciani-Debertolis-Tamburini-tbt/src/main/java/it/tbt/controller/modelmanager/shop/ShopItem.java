package it.tbt.controller.modelmanager.shop;

/**
 * Stripped down item, to be used in controller and viwe.
 */
public class ShopItem {
    private final String name;
    private int count;
    private final int value;

    /**
     * Default contructor.
     * @param name
     * @param count
     * @param value
     */
    public ShopItem(final String name, final int count, final int value) {
        this.name = name;
        this.count = count;
        this.value = value;
    }

    /**
     * Create a new item based on an existing item.
     * @param item
     */
    public ShopItem(final ShopItem item) {
        this.name = item.getName();
        this.count = 1;
        this.value = item.getValue();
    }

    /**
     * Get the item name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get how many items of this type there are.
     * @return item count
     */
    public int getCount() {
        return count;
    }

    /**
     * Increase counter.
     */
    public void incCount() {
        count++;
    }

    /**
     * Decrease counter.
     */
    public void decCount() {
        count--;
    }

    /**
     * Get the item value.
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
