package controller;

public interface Runner {
    /**
     * runs operations for all the alive cells in the world simulating a day
     * @param screen
     */
    void aliveCells();
    
    /**
     *runs operations for the dead cells in the world simulating a day
     *@param screen
     */
    void deadCells();

}
