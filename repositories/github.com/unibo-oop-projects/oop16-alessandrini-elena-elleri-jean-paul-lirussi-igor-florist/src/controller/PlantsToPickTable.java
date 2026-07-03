package controller;

/**
 * 
 * This interface allows {@link GreenhouseController #plantsToPickToday()}
 * to show all the plants that needs to be picked today.
 *
 */
public interface PlantsToPickTable {

    /**
     * This method select a {@link planting} from it's position.
     * 
     * @param pos
     *            position of selected {@link planting}
     */
    void selectPlanting(int pos);

    /**
     * This method remove the selected {@link planting}.
     */
    void removePlanting();

    /**
     * Simple getter of ToPickTable.
     * 
     * @return the ToPickTable
     */
    Object[][] getToPickTable();
}
