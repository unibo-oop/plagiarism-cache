package starcatraz.view.cards;

import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Transform;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardColor;
import starcatraz.model.cards.CardType;

/**
 * View of multiple CardViews.
 */
public abstract class CardsView extends Group {

    /**
     * Add a CardView to the View.
     * @param cardView
     */
    public void addCard(final CardView cardView) {
        getChildren().add(cardView);
        updateCards();
    }

    /**
     * Add a Card to the View.
     * @param card
     */
    public void addCard(final Card card) {
        getChildren().add(new CardView(card));
        updateCards();
    }

    /**
     * Remove and return the CardView at the given index.
     * @param index
     * @return the removed CardView
     */
    public CardView removeCard(final int index) {
        final CardView card = (CardView) getChildren().remove(index);
        updateCards();
        return card;
    }

    /**
     * Remove the given Card from the View.
     * @param card to be removed
     */
    public void removeCard(final Card card) {
        for (int i = 0; i < getChildren().size(); i++) {
            if (((CardView) getChildren().get(i)).getCard().equals(card)) {
                getChildren().remove(i);
                return;
            }
        }
        updateCards();
    }

    /**
     * Remove the first Card of the given Type and Color from the View.
     * @param type
     * @param color
     */
    public void removeCard(final CardType type, final CardColor color) {
        for (int i = 0; i < getChildren().size(); i++) {
            if (((CardView) getChildren().get(i)).getCard().getType() == type &&
                ((CardView) getChildren().get(i)).getCard().getColor() == color) {
                getChildren().remove(i);
                return;
            }
        }
        updateCards();
    }

    /**
     * Removed all CardViews from the View.
     */
    public void clear() {
        getChildren().clear();
    }

    /**
     * @return number of Cards in the View
     */
    public int getCardCount() {
        return getChildren().size();
    }

    /**
     * Sort the cards.
     */
    public void sort() {
        Collections.sort(getChildren(), (a, b) -> Integer.compare(
                                                  ((CardView)a).getCard().getValue(), 
                                                  ((CardView)b).getCard().getValue()));
    }

    public abstract Transform getCardTransform(CardView card, int num);

    /**
     * Update the Cards in the View.
     */
    protected void updateCards() {
        int num = 0;
        for (final Node node : getChildrenUnmodifiable()) {
            if (node instanceof CardView) {
                final CardView card = (CardView) node;
                final Transform transform = getCardTransform(card, num);
                final ObservableList<Transform> transforms = node.getTransforms();
                transforms.clear();
                transforms.add(transform);
                num++;
            }
        }
    }
}
