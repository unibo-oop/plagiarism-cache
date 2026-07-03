package model.bonus;

/**
 * 
 * Class implementing the concept of BonusCard.
 *
 */
public class BonusCardImpl implements BonusCard {

    private static final long serialVersionUID = -807961984553887578L;
    private final Bonus type;

    /**
     * 
     * @param type
     *            type of bonus of this card.
     *
     */
    public BonusCardImpl(final Bonus type) {
        super();
        this.type = type;
    }

    @Override
    public Bonus getBonusType() {

        return this.type;
    }

    @Override
    public String toString() {
        return "Type: " + this.type.toString();
    }
}
