package it.unibo.briscoola.view.api;

import java.awt.event.ActionListener;

/**
 * Interface representing the graphical view of a single card.
 * It defines the methods to render the card and handle user interactions.
 * 
 * @author Riem Boukhama
 * @author Maisam Noumi
 */
public interface CardView {

    /**
     * Renders the card's visual representation on the screen.
     * If one of the parameters is null, it displays the back of the card.
     * 
     * @param seed the card seed
     * @param value the card value
     */
    void renderCard(String seed, String value);

    /**
     * Adds a {@link ActionListener} to handle the user interaction when the card is clicked.
     * 
     * @param listener the action to be executed upon clicking the card.
     */
    void addCardClickListener(ActionListener listener);
}
