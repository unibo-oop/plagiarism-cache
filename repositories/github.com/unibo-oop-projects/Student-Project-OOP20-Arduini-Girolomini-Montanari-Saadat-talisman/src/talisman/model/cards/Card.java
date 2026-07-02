package talisman.model.cards;

/**
 * Interface that models a card.
 * 
 * @author Abtin Saadat
 * 
 */
public interface Card {
    /**
     * 
     * @return The name of the card
     */
    String getName();

    /**
     * 
     * @return The text of the card
     */
    String getText();

    /**
     * 
     * @return The type of the card
     */
    CardType getType();

    /**
     * 
     * @return The image path of the card
     */
    String getImagePath();
}
