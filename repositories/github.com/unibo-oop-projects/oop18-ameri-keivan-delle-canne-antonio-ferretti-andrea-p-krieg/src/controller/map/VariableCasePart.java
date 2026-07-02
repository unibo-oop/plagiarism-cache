package controller.map;

import java.util.Optional;

/**
 * a case used to describe the variable part of the game map, the only one that
 * is refreshed each time.
 */
public interface VariableCasePart {

    /**
     * 
     * @return the bottom part of the case if present(the structure).
     */
    Optional<String> getBottom();

    /**
     * 
     * @return the top part of the case if present(the unit).
     */
    Optional<String> getTop();

    /**
     * 
     * @return the bottom part of the case if present(the selection).
     */
    Optional<String> getBorder();
}
