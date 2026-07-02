package view.start.world;

/**
 * Interface that will be implemented by the classes that will update the specifications related to the world.
 *
 */
public interface UpdateSpecific {

    /**
     * Method that updates the graphical interface of the world's specifications with the parameters passed to it.
     * @param contUpdateWorld number of graphic world interface updates
     * @param contDay number of world updates 
     * @param contCellAlive number of live cells
     * @param contCellDeath number of dead cells
     */
    void updateSpecific(int contUpdateWorld, int contDay);

}
