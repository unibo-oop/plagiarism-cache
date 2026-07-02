package model.dice;

/**
 * Chiara Tarantino.
 * A class that specifies the faces of a hedge jumping dice.
 *
 */
public class HedgeJumpingDice extends AbstractDice {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    @Override
    protected final String getResultFace() {
        switch (this.getResult()) {
        case ONE:
            return "BLACK";
        case TWO:
            return "GREY";
        case THREE:
            return "GREEN";
        case FOUR:
            return "FOUR";
        case FIVE:
            return "FIVE";
        case SIX:
            return "SIX";
        default:
            return null;

        }
    }

}
