package model.world;


/**
* utility class with only getter to obtain informations above all cells.
*/
public interface CellInfos {
    /**
     * 
     * @return medium age of the cells in the simulation
     */
    int getMediumAge();

    /**
     * 
     * @return medium quantity of energy obtained by all the cells from eating other cells
     */
    int getMediumPercEatingEnergies();

    /**
     * 
     * @return medium quantity of energy obtained by all the cells from photosyntesis
     */
    int getMediumPercPhotosyntesisEnergy();

    /**
     * 
     * @return medium quantity of energy obtained by all the cells from absorbing minerals in the world
     */
    int getMediumPercMineralEnergy();

    /**
     * 
     * @return medium quantity of energy obtained by all the cells from donation by other cells
     */
    int getMediumPercAltruismEnergy();

    /**
     * 
     * @return medium quantity of all the energy obtained by all the cells during all the simulation
     */
    int getMediumTotalEnergy();

    /**
     * @return medium number of genes of the cells still alive
     */
    int getMediumNumberofGenes();

    /**
     * 
     * @return number of dead cells in the simulation
     */
    int getDeadCellsNumber();

    /**
     * 
     * @return number of alive cells in the simulation
     */
    int getAliveCellsNumber();
}
