package starcatraz.model.cards;

/**
 * Card implementation.
 */
public class CardImpl implements Card {

    private final CardType type;
    private final CardColor color;
    private final int value;

    /**
     * Constructor for CardImpl.
     * @param type
     * @param color
     */
    public CardImpl(final CardType type, final CardColor color) {
        this.type = type;
        this.color = color;
        this.value = color.getValue() + type.getValue();
    }

    @Override
    public CardType getType() {
        return this.type;
    }

    @Override
    public CardColor getColor() {
        return this.color;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.type.toString() + " - " + this.color.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
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
        final CardImpl other = (CardImpl) obj;
        return value == other.value;
    }
}
