package it.unibo.plantsfarm.view.menu.api;

/**
 * Interface defining Storage view component.
 */
public interface StorageScreen {

    /**
     * Creates a visual slot for a plant using ButtonFactory.
     *
     * @param plantName The name of the plant.
     */
    void createStorageSlot(String plantName);

    /**
     * Updates the quantity for a specific plant.
     *
     * @param plantName   The name of the plant.
     * @param newQuantity The new quantity to display.
     */
    void updateItemQuantity(String plantName, int newQuantity);

    /**
     * Shows the storage screen.
     */
    void show();

}
