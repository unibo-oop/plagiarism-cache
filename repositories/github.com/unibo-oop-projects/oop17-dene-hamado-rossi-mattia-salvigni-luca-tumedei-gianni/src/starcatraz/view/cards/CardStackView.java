package starcatraz.view.cards;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.transform.Transform;

/**
 * View of multiple cards staced on top of each other.
 */
public class CardStackView extends CardsView {

    private double cardTranslateX, cardTranslateY;

    private DoubleProperty cardTranslateXProperty, cardTranslateYProperty;

    /**
     * @return card translate Y property
     */
    public DoubleProperty cardTranslateXProperty() {
        if (cardTranslateXProperty == null) {
            cardTranslateXProperty = new SimpleDoubleProperty();
        }
        return cardTranslateXProperty;
    }

    /**
     * @return card translate X property
     */
    public DoubleProperty cardTranslateYProperty() {
        if (cardTranslateYProperty == null) {
            cardTranslateYProperty = new SimpleDoubleProperty();
        }
        return cardTranslateYProperty;
    }

    /**
     * @return card translate X property
     */
    public double getCardTranslateX() {
        return (cardTranslateXProperty != null ? cardTranslateXProperty.get() : cardTranslateX);
    }

    /**
     * @return card translate Y property
     */
    public double getCardTranslateY() {
        return (cardTranslateYProperty != null ? cardTranslateYProperty.get() : cardTranslateY);
    }

    /**
     * @param cardTranslateX property to set
     */
    public void setCardTranslateX(final double cardTranslateX) {
        if (cardTranslateXProperty != null) {
            cardTranslateXProperty.set(cardTranslateX);
        } else {
            this.cardTranslateX = cardTranslateX;
        }
        updateCards();
    }

    /**
     * @param cardTranslateY property to set
     */
    public void setCardTranslateY(final double cardTranslateY) {
        if (cardTranslateYProperty != null) {
            cardTranslateYProperty.set(cardTranslateY);
        } else {
            this.cardTranslateY = cardTranslateY;
        }
        updateCards();
    }

    /**
     * Constructor for CardStackView.
     * @param cardTranslateX
     * @param cardTranslateY
     */
    public CardStackView(final double cardTranslateX, final double cardTranslateY) {
        this.cardTranslateX = cardTranslateX;
        this.cardTranslateY = cardTranslateY;
    }

    /**
     * Constructor for CardStackView.
     * @param cardTranslateX
     * @param cardTranslateY
     */
    public CardStackView() {
        this(0, 0);
    }

    /**
     * Set both card translate properties.
     * @param cardTranslateX
     * @param cardTranslateY
     */
    public void setCardTranslate(final double cardTranslateX, final double cardTranslateY) {
        setCardTranslateX(cardTranslateX);
        setCardTranslateY(cardTranslateY);
    }

    @Override
    public Transform getCardTransform(final CardView card, final int num) {
        return Transform.translate(cardTranslateX * num, cardTranslateY * num);
    }
}
