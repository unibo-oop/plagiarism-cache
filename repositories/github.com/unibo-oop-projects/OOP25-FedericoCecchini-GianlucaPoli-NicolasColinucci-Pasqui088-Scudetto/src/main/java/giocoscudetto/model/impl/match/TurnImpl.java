package giocoscudetto.model.impl.match;

import giocoscudetto.model.api.Turn;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.dices.MainDice;

/**
 * Implementations of turn management.
 */
public final class TurnImpl implements Turn {

    private final Club club1;
    private final Club club2;
    private final MainDice dice;
    private Club currentPlayer;
    private boolean skipClub1;
    private boolean skipClub2;

    /**
     * Creates a turn manager.
     *
     * @param club1 first club
     * @param club2 second club
     */
    public TurnImpl(final Club club1, final Club club2) {

        this.club1 = club1;
        this.club2 = club2;
        this.dice = new MainDice();
        chooseStartingPlayer();
    }

    /**
     * Marks a club to skip its next turn.
     *
     * @param club club that must skip
     */
    public void setSkipTurn(final Club club) {
        if (club1.equals(club)) {
            skipClub1 = true;
        } else {
            skipClub2 = true;
        }
    }

    @Override
    public void chooseStartingPlayer() {
        final int roll1 = dice.rollDice() + dice.rollDice();
        final int roll2 = dice.rollDice() + dice.rollDice();

        if (roll1 >= roll2) {
            currentPlayer = club1;
        } else {
            currentPlayer = club2;
        }
    }

    @Override
    public Club getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchTurn() {
        if (club1.equals(currentPlayer)) {
            currentPlayer = club2;
        } else {
            currentPlayer = club1;
        }

        if (club1.equals(currentPlayer) && skipClub1) {
            skipClub1 = false;

            //System.out.println(club1.getName() + "--skip the turn");
            currentPlayer = club2;

        } else if (club2.equals(currentPlayer) && skipClub2) {
            skipClub2 = false;

            //System.out.println(club2.getName() + "--skip the turn");
            currentPlayer = club1;
        }
    }

    /**
     * Checks whether a club must skip its turn.
     *
     * @param club club to check
     * @return true if the club must skip
     */
    public boolean hasToSkip(final Club club) {

        if (club1.equals(club)) {
            return skipClub1;
        }

        return skipClub2;
    }

    /**
     * Consume the skip-turn effect.
     *
     * @param club club whose skip is removed
     */
    public void consumeSkip(final Club club) {

        if (club1.equals(club)) {
            skipClub1 = false;
        } else {
            skipClub2 = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Club getNotCurrentPlayer() {
        if (currentPlayer.equals(club1)) {
            return club2;
        }
        return club1;
    }

}
