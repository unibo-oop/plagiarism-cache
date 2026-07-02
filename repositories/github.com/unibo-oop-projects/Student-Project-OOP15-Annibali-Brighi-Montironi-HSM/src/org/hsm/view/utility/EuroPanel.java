package org.hsm.view.utility;

import org.hsm.view.gui.GUIComponent;

/**
 * This interface contains methods to use a specific panel for managing euros.
 *
 */
public interface EuroPanel extends GUIComponent {

    /**
     * Get the values expressed in cents.
     * 
     * @return the value expressed in cents.
     */
    int getValue();

}
