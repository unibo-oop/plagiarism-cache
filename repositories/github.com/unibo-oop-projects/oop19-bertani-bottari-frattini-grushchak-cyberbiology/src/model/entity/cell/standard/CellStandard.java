package model.entity.cell.standard;

import model.direction.Direction;

/**
 * 
 *the standard interface for the main protagonist of the game of life.
 *
 */


import model.entity.cell.Cell;
import model.entity.cell.standard.action.Action;
import model.entity.cell.standard.age.Age;
import model.entity.cell.standard.genome.Genome;
import model.entity.cell.standard.history.History;
import model.entity.cell.standard.obtainable.Obtainable;

public interface CellStandard extends Cell, Genome, Age, Action, Obtainable, History {

    /**
     * a standard getter to take the direction.
     * 
     * @return the direction
     */
    Direction getDirection();

    /**
     * a standard setter.
     * 
     * @param direction
     */
    void setDirection(Direction direction);

    /**
     * the main method . start the cell work with gene.
     */
    void run();
    /**
     * 
     * @return
     *  a copy of the cell
     */
    CellStandard makeChild();
    /**
     * 
     * @return
     * true if the cell is active
     * 
     */
    boolean isActive();
    /**
     * make the cell active = false.
     * if is used @throw a exception.
     */
    void deactive();
    /**
     * hashcode.
     * @return
     * the hashcode.
     */
    int hashCode();
    /**
     * 
     * @return
     * the class to string for debug.
     */
    String toString();
}
