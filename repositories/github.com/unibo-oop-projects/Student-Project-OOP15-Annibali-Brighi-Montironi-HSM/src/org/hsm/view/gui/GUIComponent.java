package org.hsm.view.gui;

import javax.swing.JComponent;

/**
 * All the GUI components in the frame must implements this interface.
 *
 */
public interface GUIComponent {

    /**
     * Get the component.
     * 
     * @return the component.
     */
    JComponent getComponent();

}
