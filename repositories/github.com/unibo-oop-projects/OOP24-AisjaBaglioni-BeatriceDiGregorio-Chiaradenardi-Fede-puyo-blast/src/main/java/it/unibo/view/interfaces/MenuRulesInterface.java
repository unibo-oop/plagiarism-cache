package it.unibo.view.interfaces;

import java.awt.event.ActionListener;

/**
 * Interface for the MenuRules class.
 * This interface defines the contract for adding an ActionListener
 * to the back button in the rules menu screen.
 */
public interface MenuRulesInterface {

    /**
     * Adds an ActionListener to the "Back" button.
     * This method allows the back button to trigger an action when clicked.
     * 
     * @param listener The ActionListener to be added to the back button.
     */
    void addBackButtonListener(ActionListener listener);
}
