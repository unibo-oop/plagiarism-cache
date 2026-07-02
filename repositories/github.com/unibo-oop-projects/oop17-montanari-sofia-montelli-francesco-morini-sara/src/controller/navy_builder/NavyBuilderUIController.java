package controller.navy_builder;

import java.util.List;
import java.util.Set;

import controller.SpecificController;
import model.basic_component.StaticPoint2D;

/**
 * Controller for the {@link NavyBuilderUI} user interface.
 */
public interface NavyBuilderUIController extends SpecificController {

    /**
     * Getter for the grid size.
     * @return the grid's size
     */
    int getGridSize();

    /**
     * Getter for the missing ship count.
     * @return The list of the missing ships's number
     */
    List<Integer> getMissingShipCount();

    /**
     * Return the available coordinate.
     * @return a {@link Set} of {@link StaticPoint2D} representing the free points
     */
    Set<StaticPoint2D> getAvaiableCoord();

    /**
     * Returns the set of occupied position.
     * @return a {@link Set} of {@link StaticPoint2D} representing the occupied points
     */
    Set<StaticPoint2D> getOccupiedPosition();
    /**
     * Set a new cord into the builder.
     * @param point Is the selected {@link StaticPoint2D}
     */
    void setCoord(StaticPoint2D point);

    /**
     * Setter for the current ship size.
     * @param size Is the size to set for the ship currently under construction.
     */
    void setCurrentShipSize(int size);

    /**
     * Resets the information.
     */
    void reset();

    /**
     * Creates a new random disposition.
     */
    void newRandomDisposition();

    /**
     * @return if it's possible to confirm.
     */
    boolean canConfirm();

    /**
     * Confirms the internal information.
     */
    void confirm();
}
