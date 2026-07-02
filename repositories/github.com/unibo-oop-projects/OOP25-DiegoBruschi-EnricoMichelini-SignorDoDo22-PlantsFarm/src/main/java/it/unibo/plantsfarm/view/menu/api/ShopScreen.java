package it.unibo.plantsfarm.view.menu.api;

import java.awt.event.ActionListener;

/**
 * Interface defining Shop view component.
 */
public interface ShopScreen {

    /**
     * Adds a selling option to the left panel.
     *
     * @param plantName The plant name.
     * @param quantity  The quantity to sell.
     * @param price     The total price.
     */
    void addSellItem(String plantName, int quantity, int price);

    /**
     * Clears the current request items to allow refresh.
     */
    void resetRequestsPanel();

    /**
     * Sets up the SELL button at the bottom of the left panel.
     *
     * @param listener The action to perform.
     */
    void setSellButton(ActionListener listener);

    /**
     * Sets up the 4 Buy buttons in the right panel.
     *
     * @param listener Action when clicked.
     */
    void setBuyButtons(ActionListener listener);

    /**
     * Enables or disables all buy buttons.
     *
     * @param enabled True to enable, false to disable.
     */
    void setBuyButtonsEnabled(boolean enabled);

    /**
     * Plays the coin sound.
     */
    void playCoinSound();

    /**
     * Plays the mystery box sound.
     */
    void playMisteryBoxSound();

    /**
     * Shows the dialog.
     */
    void show();

    /**
     * Closes the dialog.
     */
    void close();

}
