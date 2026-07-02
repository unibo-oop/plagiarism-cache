package giocoscudetto.model.impl.match;

import giocoscudetto.model.api.Pawn;

/**
 * Implementation of a pawn used by the player.
 */
public final class PawnImpl implements Pawn {

    private static final int MAX_POSITION = 32;

    private final int pawnRGB;
    private int position;

    /**
     * Creates a pawn.
     * 
     * @param pawnRGB pawn color
     */
    public PawnImpl(final int pawnRGB) {
        this.position = 0;
        this.pawnRGB = pawnRGB;
    }

    @Override
    public void changePosition(final int steps) {

        int newPosition = this.position + steps; //DA CONTROLLARE *****
        if (newPosition > MAX_POSITION) { // ************************
            newPosition = MAX_POSITION;
        } else {
            newPosition = this.position + steps;
        }

        //System.out.println("Pawn moves from " + this.position + " to " + newPosition
        //);

        this.position = newPosition;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getPawnRGB() {
        return this.pawnRGB;
    }

    @Override
    public void setPosition(final int position) {
       this.position = position;
    }
}
