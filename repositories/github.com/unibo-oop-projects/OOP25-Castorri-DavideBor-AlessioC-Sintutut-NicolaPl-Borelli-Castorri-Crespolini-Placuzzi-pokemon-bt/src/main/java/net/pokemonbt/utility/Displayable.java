package net.pokemonbt.utility;

import java.io.Serializable;

/**
 * An object that inherits this interface will eventually be display in
 *      the GUI. Ovveriding this methods allows to display a proper view of it.
 */
public interface Displayable extends Serializable {

    /**
     * @return The {@link String} formatted to visualize the Name of the Object.
     */
    String getDisplayName();

    /**
     * @return A {@link String} of flavour-text describing what the Object is or does.
     */
    String getDescription();
}
