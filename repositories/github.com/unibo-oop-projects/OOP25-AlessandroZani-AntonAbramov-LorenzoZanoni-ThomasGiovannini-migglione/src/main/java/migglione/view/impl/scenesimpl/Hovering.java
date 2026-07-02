package migglione.view.impl.scenesimpl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import migglione.model.impl.Card;

/**
 * This class is used to implement the hovering effect on the buttons.
 * It's an implementation of the MouseMotionListener interface but 
 * really only uses the mouseMoved() method, the other is empty 
 * because it has no real fucntion in the game.
 */
public final class Hovering implements MouseListener {
    private static final String CARDS_IMAGE_PATH = "/images/cards/";
    private static final String STATS_IMAGE_PATH = "/images/statistics/";
    private final HoveringCard hoveringCard;
    private final Field field;

    /**
     * Constructor for the Hovering class.
     * It takes a card and a field as parameters and creates a hovering card.
     * The card has the image and stats specified by itself.
     * 
     * @param hoveringCard the card to be hovered
     * @param field the field on which the card will be hovered
     */
    public Hovering(final Card hoveringCard, final Field field) {
        this.hoveringCard = new HoveringCard(hoveringCard);
        this.field = field.getClass().cast(field);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        field.clearHoveredCard();
        field.repaint();
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        field.setHoveredCard(hoveringCard);
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        field.clearHoveredCard();
        field.repaint();
    }

    /**
     * Method to update the card to be hovered.
     * Used when the user clicks on a card to play it,
     *  so that the hovering card is updated to the new one in hand.
     * 
     * @param newCard the new card to be hovered
     */
    public void setHoveredCard(final Card newCard) {
        hoveringCard.actualCard = newCard;
    }

/**
 * Nested class used to create a card with the same stats as the one in the hand but with an image path.
 * Like a card but with an image too, used to create the hovering effect on the buttons.
 */
static class HoveringCard extends Card {
        private Card actualCard;

        HoveringCard(final Card actualCard) {
            super(actualCard.getName(), actualCard.getAttk(), actualCard.getDeff(), actualCard.getStrength(),
                  actualCard.getIntelligence(), actualCard.getStealth());
            this.actualCard = actualCard;
        }

        public String getImage() {
            return CARDS_IMAGE_PATH + actualCard.getName() + ".png";
        }

        public String getStats() {
            return STATS_IMAGE_PATH + actualCard.getName() + ".png";
        }

    }
}
