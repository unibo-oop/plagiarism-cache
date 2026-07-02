package giocoscudetto.model.impl.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.model.api.dices.Dice;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.GoalNet;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;
import giocoscudetto.model.impl.dices.MainDice;
import giocoscudetto.model.impl.dices.ResultDice;

/**
 * This class implements the Match interface, it represents a match between two clubs,
 * it keeps track of the score and of the clubs that are playing.
 */
public class MatchImpl implements Match {

    private static final int HALF_BOARD = 16;
    private static final int BOUND = 6;
    private static final int FREE_KICK_GOAL = 7;
    private static final Random RANDOM = new Random();
    private final Club clubHome;
    private final Club clubAway;
    private final Scoreboard score;
    private final TurnImpl turn;
    private final Dice dice6;
    private final Dice dice3;
    private final GoalNet net = new GoalNetImpl();
    private final List<Integer> eventDices = new ArrayList<>();
    private GameMode mode = GameMode.NONE;

    /**
     * Constructor for the MatchImpl class, it initializes the score, the turn and the dices,
     * it also choose randomly which club will start the match.
     * 
     * @param clubHome the home club
     * @param clubAway the away club
     */
    public MatchImpl(final Club clubHome, final Club clubAway) {
        this.score = new ScoreboardImpl();
        this.turn = new TurnImpl(clubHome, clubAway);
        this.dice6 = new MainDice();
        this.dice3 = new ResultDice();
        turn.chooseStartingPlayer();
        this.clubHome = clubHome;
        this.clubAway = clubAway;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club turn() {
        turn.switchTurn();
        return turn.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void goalHome() {
        this.score.increaseHomeScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void goalAway() {
        this.score.increaseGuestScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGoalHome(final int goal) {
        this.score.setHomeScore(goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void removeGoalHome() {
        this.score.decreaseHomeScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGoalAway() {
        this.score.decreaseGuestScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGoalAway(final int goal) {
        this.score.setGuestScore(goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club getClubHome() {
        return clubHome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club getClubAway() {
        return clubAway;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings
    @Override
    public final Scoreboard getScore() {
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Club> getWinnerClub() {
        if (this.score.getHomeScore() > this.score.getGuestScore()) {
            return Optional.of(this.clubHome);
        } else if (this.score.getHomeScore() < this.score.getGuestScore()) {
            return Optional.of(this.clubAway);
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Club> getLoserClub() {
        if (this.score.getHomeScore() < this.score.getGuestScore()) {
            return Optional.of(this.clubHome);
        } else if (this.score.getHomeScore() > this.score.getGuestScore()) {
            return Optional.of(this.clubAway);
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Club getCurrentPlayer() {
        return turn.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        if (turn.hasToSkip(turn.getCurrentPlayer())) {
            //System.out.println(turn.getCurrentPlayer().getName() + "skip the turn");
            turn.consumeSkip(turn.getCurrentPlayer());
            return 0;
        }
        final int dice;
        if (this.turn.getCurrentPlayer().getPawn().getPosition() < HALF_BOARD) {
            dice = this.dice6.rollDice() + this.dice6.rollDice();
            //System.out.println("due dadi" + dice6);
            return dice;
        }
        dice = this.dice6.rollDice();
        //System.out.println("un dado" + dice6);
        return dice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameMode(final GameMode newMode) {
        this.mode = newMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameMode() {
        return this.mode.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkipTurn(final Club club) {
        turn.setSkipTurn(club);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int diceEvent() {
        final int result;
        if (eventDices.isEmpty()) {
            if (this.mode == GameMode.RESULT) {
                result = this.dice3.rollDice();
            } else {
                result = this.dice6.rollDice();
            }
            this.eventDices.add(result);
            return result;
        } else {
            if (this.mode == GameMode.RESULT) {
                result = this.dice3.rollDice();
            } else {
                result = this.dice6.rollDice();
            }
            this.eventDices.add(result);
            this.eventMode();
            return result;
        }
    }

    /**
     * Method that manage the event mode, it check the game mode and the value of the event 
     * dices and update the score accordingly.
     */
    @Override
    public void eventMode() {
        if (this.mode == GameMode.RESULT) {

            this.setGoalHome(this.eventDices.get(0));
            this.setGoalAway(this.eventDices.get(1));
        } else if (this.mode == GameMode.FREE_KICK) {

            if (this.eventDices.get(0) + this.eventDices.get(1) == FREE_KICK_GOAL) {

                if (this.getCurrentPlayer().equals(this.getClubAway())) {
                    this.goalAway();
                } else {
                    this.goalHome();
                }
            }
        } else if (this.mode == GameMode.CORNER) {
            if (this.eventDices.get(0) == 1 || this.eventDices.get(1) == 1) {
                if (this.getCurrentPlayer().equals(this.getClubAway())) {
                    this.goalAway();
                } else {
                    this.goalHome();
                }
            }
        } else if (this.mode == GameMode.PENALTY && this.net.isGoal(RANDOM.nextInt(BOUND) + 1)) {
            if (this.getCurrentPlayer().equals(this.getClubHome())) {
                this.goalHome();
            } else {
                this.goalAway();
            }
        }
        this.eventDices.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setKeeperPosition(final int i) {
        this.net.setGoalKeeperPosition(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastShootPosition() {
        return this.net.getLastShootPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.clubHome.getName() + " - " + this.clubAway.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotCurrentPlayer() {
        return turn.getNotCurrentPlayer().getName();
    }
}
