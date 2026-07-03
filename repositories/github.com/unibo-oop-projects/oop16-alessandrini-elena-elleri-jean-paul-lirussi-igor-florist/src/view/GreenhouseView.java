package view;
/**
 * interface of the main greenhouse view.
 *
 */
public interface GreenhouseView {
    /**
     * changes the color of a plant according to its change of status.
     * @param pos
     *          index of the plant
     * @param status
     *          status of the plant
     */
    void changeStatusColor(int pos, boolean status);

    /**
     * sets the visibility of the option to plant a flower.
     * @param status
     *              status of the plant
     */
    void setPlantable(boolean status);

    /**
     * 
     * @param pos
     *      of the {@link Plant}
     */
    void resetPlantButton(int pos);
}