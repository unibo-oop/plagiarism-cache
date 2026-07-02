package model.battleground;

import java.util.List;

import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import model.navy.Navy;

/**
 * Representation of a battleground, contains method
 * or interaction and get information about the current status.
 */
public interface BattleGround {
    /**
     * Shoot on the ground and eventually hit a {@link Ship}.
     * @param coordX The x coordinate of the cell
     * @param coordY The x coordinate of the cell
     * @throws IllegalArgumentException if the coordinate
     *         is outside the grid or is already shoot
     */
    void shoot(int coordX, int coordY) throws IllegalArgumentException;

    /**
     * Shoot on the ground and eventually hit a {@link Ship}.
     * @param coordinate The coordinate of the point to shoot
     * @throws IllegalArgumentException if the coordinate 
     *         is outside the grid or is already shoot
     */
    void shoot(StaticPoint2D coordinate) throws IllegalArgumentException;
    /**
     * Execute a random shot using the cell yet free.
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @return true if the shot was successful, false 
     *         otherwise (the grid is completely shoot) 
     */
    boolean randomShoot();
    /**
     * @return the shoots history
     */
    List<Cell> getShootsHistory();
    /**
     * @return the {@link CellStatus} of the {@link BattleGround} cell
     * @param coordX The x coordinate of the cell
     * @param coordY The x coordinate of the cell
     * @throws IllegalArgumentException if the coordinate 
     *         is outside the grid or is already shoot
     */
    Cell.Status getStatus(int coordX, int coordY) throws IllegalArgumentException;
    /**
     * @return the {@link CellStatus} of the {@link BattleGround} cell
     * @param coordinate
     *            The coordinate of the cell as {@link StaticPoint2D}
     * @throws IllegalArgumentException if the coordinate 
     *         is outside the grid or is already shoot
     */
    Cell.Status getStatus(StaticPoint2D coordinate) throws IllegalArgumentException;
    /**
     * Getter for the navy.
     * @return a copy of the current navy
     */
    Navy getNavy();
}
