package giocoscudetto.model.impl.match;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.api.match.Club;

/**
 * Implementing Club interface.
 */
public class ClubImpl implements Club {

    private final String name;
    private final Pawn pawn;
    private int points;
    private int netDiff;

    /**
     * @param name the name selected for the club.
     * @param pawnRGB the pawn color assigned to the club.
     */
    public ClubImpl(final String name, final int pawnRGB) {
        this.pawn = new PawnImpl(pawnRGB);
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNetDiff() {
        return this.netDiff;
    }

    /**
     * Here i suppres the warning of expousure because, i want to return the exact club's pawn, not a copy.
     * 
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public Pawn getPawn() {
        return this.pawn;
        //return new PawnImpl(pawn.getPawnRGB(), pawn.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementPoints(final int pointsReceived) {
        if (pointsReceived >= 0) {
            this.points += pointsReceived;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeNetDiffs(final int goalScored, final int goalConceded) {
        if (goalScored >= 0 && goalConceded >= 0) {
            this.netDiff += goalScored - goalConceded;
        }
    }

}
