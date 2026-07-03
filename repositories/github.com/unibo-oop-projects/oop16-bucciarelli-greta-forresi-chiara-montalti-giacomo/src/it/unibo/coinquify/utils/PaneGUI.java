package it.unibo.coinquify.utils;

import javax.swing.JPanel;

/**
 * Every feature have a PaneGUI.
 */
public interface PaneGUI {
    /**
     * 
     * @return JPanel to add in homeGUI
     */
    JPanel getPanel();
    /**
     * 
     * @return JPanel name
     */
    String getName();
}
