package talisman.view.cards;
/**
 * Creates the view for a single card.
 * @author Abtin Saadat
 *
 */
public interface TalismanCardView {
    /**
     * Creates the view for a single card.
     * @param imagePath The card's image path
     * @param text The card's text
     * @param name The name of the card
     * @return Returns the view 
     */
    static TalismanCardView create(final String imagePath, final String text, final String name) {
        return new TalismanCardViewImpl(imagePath, text, name);
    }
}
