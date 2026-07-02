package view.start.world;

/**
 * Interface that will be implemented by the classes that will update the world interface.
 *
 */
public interface UpdateWorld {

    /**
     * Method that updates the graphic interface of the world, analyzing one by one the entities contained in it
     * and coloring them according to the color obtained by the filter itself.
     */
    void updateStatus();

    /**
     * Method that updates the type of filter used by the application to update the world, from the next iteration.
     * @param filtername name of the new filter selected
     */
    void updateFilter(String filtername);

    /**
     * Method that returns the number of updates performed by the world interface.
     * @return the number of updates.
     */
    int getCoutUpDate();

    /**
     * Method that enables the ability to click on any entity in the world to view its specifications.
     */
    void enablePressCells();

}
