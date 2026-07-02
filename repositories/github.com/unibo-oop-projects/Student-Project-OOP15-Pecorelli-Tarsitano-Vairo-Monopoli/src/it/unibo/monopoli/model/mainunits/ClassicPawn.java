package it.unibo.monopoli.model.mainunits;

/**
 * This class represents the {@link Pawn} used in the classic version of
 * Monopoly.
 *
 */
public class ClassicPawn implements Pawn {

    private static final int LAST_POSITION = 39;

    private final int id;
    private int actualPos;
    private int previousPos;

    /**
     * Constructs an instance of {@link ClassicPawn}. It needs a specific ID
     * 
     * @param id
     *            - {@link Pawn}'s ID
     */
    public ClassicPawn(final int id) {
        this.id = id;
        this.actualPos = 0;
        this.previousPos = 0;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getActualPos() {
        return this.actualPos;
    }

    @Override
    public int getPreviousPos() {
        return this.previousPos;
    }

    @Override
    public void setPos(final int newPosizion) {
        this.previousPos = this.actualPos;
        this.actualPos = newPosizion;
        if (this.isPassedFromStartBox()) {
            this.actualPos = this.actualPos - LAST_POSITION - 1;
        }
    }

    private boolean isPassedFromStartBox() {
        return this.actualPos > LAST_POSITION;
    }

}
