package view.gamescreen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.bonus.BonusCard;
import model.bonus.StateBonusCard;

/**
 * 
 * This class represent a bonus card. A card is composed by a shape (that changes on bonus type)
 * and by the state name linked to that bonus.
 *
 */
public class Card extends StackPane {

    private static final double SIZE = 28;
    private static final double ROTATION = 45;

    private Label stateName = new Label("Jolly");
    private boolean selected;

    private final BonusCard cardType;
    private Shape shape;

    /**
     * 
     * Class constructor.
     * 
     * @param card
     *                  BonusCard.
     * 
     */
    public Card(final BonusCard card) {
        this.cardType = card;
        switch (card.getBonusType()) {
        case ARTILLERY:
            this.drawRect();
            break;
        case INFANTRY:
            this.drawCirc();
            break;
        case CAVALRY:
            this.drawRhomb();
            break;
        case JOLLY:
            this.drawLine();
            break;
        default:
            break;
        }

        if (card instanceof StateBonusCard) {
            this.stateName = new Label(((StateBonusCard) card).getState().getName());
        }

        shape.getStyleClass().add("rsk-card");
        shape.setId("not-sel");

        this.getChildren().addAll(shape, stateName);
        StackPane.setAlignment(stateName, Pos.BOTTOM_CENTER);
    }

    /**
     * 
     * Getter for card type.
     * 
     * @return
     *          Actual card.
     * 
     */
    public BonusCard getCardType() {
        return cardType;
    }

    private void drawRect() {
        final Rectangle rect = new Rectangle(SIZE, SIZE);
        shape = rect;
    }

    private void drawCirc() {
        final Circle circ = new Circle(SIZE / 2);
        shape = circ;
    }

    private void drawRhomb() {
        final Rectangle rect = new Rectangle(SIZE / Math.sqrt(2), SIZE / Math.sqrt(2));
        rect.setRotate(ROTATION);
        shape = rect;
    }

    private void drawLine() {
        shape = new Rectangle(SIZE / 3, SIZE);
    }

    /**
     * 
     * Set the card color and id to selected or not.
     * 
     */
    public void setSelected() {
        if (shape.getId().equals("not-sel")) {
            shape.setId("sel");
            selected = true;
        } else {
            shape.setId("not-sel");
            selected = false;
        }
    }

    /**
     * 
     * Check if card is selected or not.
     * 
     * @return
     *          Selection's check.
     * 
     */
    public boolean isSelected() {
        return this.selected;
    }
}
