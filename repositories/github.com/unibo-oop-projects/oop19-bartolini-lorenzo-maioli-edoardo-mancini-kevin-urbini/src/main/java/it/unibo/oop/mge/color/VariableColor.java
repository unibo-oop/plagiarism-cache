package it.unibo.oop.mge.color;

import java.util.Optional;

/**
 * The Interface VariableColor.
 */
public interface VariableColor {

    /**
     * Gets the red.
     *
     * @return an optional containing: the red parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getRed();

    /**
     * Gets the green.
     *
     * @return an optional containing: the green parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getGreen();

    /**
     * Gets the blue.
     *
     * @return an optional containing: the blue parameter of the VariableColor or
     *         Optional.empty().
     */
    Optional<Integer> getBlue();
}
