package starcatraz.view.cards;

import java.util.UUID;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardColor;
import starcatraz.model.cards.CardType;
import starcatraz.model.game.InvalidCardException;
import starcatraz.util.AppImage;

/**
 * View of a Card
 */
public class CardView extends ImageView {

    private static final int CARD_HEIGHT = 240;
    private static final int CARD_WIDTH = 160;

    private final Card card;
    private boolean isFaceDown;
    private final String id;

    /**
     * Specific card.
     * @param suitNum
     * @param cardNum
     */
    public CardView(final Card card) {
        super(getImageFromCard(card));
        this.card = card;
        this.id = UUID.randomUUID().toString();
        setFitHeight(CARD_HEIGHT);
        setFitWidth(CARD_WIDTH);
    }

    /**
     * @return the Card represented by the view
     */
    public Card getCard() {
        return this.card;
    }

    private static Image getImageFromCard(final Card card) {
        final CardType type = card.getType();
        final CardColor color = card.getColor();
        if (type == CardType.ROCKET) {
            if (color == CardColor.RED) {
                return AppImage.CARD_ROCKET_RED.getImage();
            } else if (color == CardColor.BLUE) {
                return AppImage.CARD_ROCKET_BLUE.getImage();
            } else if (color == CardColor.YELLOW) {
                return AppImage.CARD_ROCKET_YELLOW.getImage();
            } else if (color == CardColor.GREY) {
                return AppImage.CARD_ROCKET_GRAY.getImage();
            }
        } else if (type == CardType.ROCKET_PART_A) {
            if (color == CardColor.RED) {
                return AppImage.CARD_ROCKET_A_RED.getImage();
            } else if (color == CardColor.BLUE) {
                return AppImage.CARD_ROCKET_A_BLUE.getImage();
            } else if (color == CardColor.YELLOW) {
                return AppImage.CARD_ROCKET_A_YELLOW.getImage();
            } else if (color == CardColor.GREY) {
                return AppImage.CARD_ROCKET_A_GRAY.getImage();
            }
        } else if (type == CardType.ROCKET_PART_B) {
            if (color == CardColor.RED) {
                return AppImage.CARD_ROCKET_B_RED.getImage();
            } else if (color == CardColor.BLUE) {
                return AppImage.CARD_ROCKET_B_BLUE.getImage();
            } else if (color == CardColor.YELLOW) {
                return AppImage.CARD_ROCKET_B_YELLOW.getImage();
            } else if (color == CardColor.GREY) {
                return AppImage.CARD_ROCKET_B_GRAY.getImage();
            }
        } else if (type == CardType.CHIP) {
            if (color == CardColor.RED) {
                return AppImage.CARD_CHIP_RED.getImage();
            } else if (color == CardColor.BLUE) {
                return AppImage.CARD_CHIP_BLUE.getImage();
            } else if (color == CardColor.YELLOW) {
                return AppImage.CARD_CHIP_YELLOW.getImage();
            } else if (color == CardColor.GREY) {
                return AppImage.CARD_CHIP_GRAY.getImage();
            }
        } else if (type == CardType.ROBOT) {
            return AppImage.CARD_ROBOT.getImage();
        }
        throw new InvalidCardException(type, color);
    }

    /**
     * @return true if the card is face down, false otherwise
     */
    public boolean isFaceDown() {
        return this.isFaceDown;
    }

    /**
     * @param isFaceDown true to put the card face down, false to put it face up
     */
    public void setFaceDown(final boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
        setImage(this.isFaceDown ? AppImage.CARD_BACK.getImage() : getImageFromCard(this.card));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((card == null) ? 0 : card.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardView other = (CardView) obj;
        if (card == null) {
            if (other.card != null) {
                return false;
            }
        } else if (!card.equals(other.card)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
