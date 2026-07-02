package giocoscudetto.controller.impl;

import giocoscudetto.controller.api.MatchController;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.api.match.Scoreboard;
import giocoscudetto.model.impl.match.BoardImpl;
import giocoscudetto.view.api.GameObserver;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of the MatchController interface.
 */
public class MatchControllerImpl implements MatchController {
    private static final int WINNER_POINT = 3;
    private static final int DRAWN_POINT = 1;
    private static final int LOSER_POINT = 0;

    private final Board board = new BoardImpl();
    private final List<GameObserver> observers = new ArrayList<>();
    private final Fixtures fixture;
    private Match match;
    private boolean helpFlag;

    /**
     * Constructor for MatchControllerImpl.
     * 
     * @param fixture the fixture of the league.
     */
    @SuppressFBWarnings //not expose internal representation.
    public MatchControllerImpl(final Fixtures fixture) {
        this.fixture = fixture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkBox() {
        this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).event(this.match);
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxImage(final int i) {
        return this.board.getBoxImage(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHomePosition() {
        return this.match.getClubHome().getPawn().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeeperPosition(final int i) {
        this.match.setKeeperPosition(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGuestPosition() {
        return this.match.getClubAway().getPawn().getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean kickPenalty() {
        final int oldGuestScore = this.match.getScore().getGuestScore();
        final int oldHomeScore = this.match.getScore().getHomeScore();
        this.match.eventMode();
        return this.match.getScore().getGuestScore() != oldGuestScore || this.match.getScore().getHomeScore() != oldHomeScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentPlayer() {
        return this.match.getCurrentPlayer().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int move() {
        final int resultDice = this.match.rollDice();
        if (resultDice == 0) {
            this.match.turn();
        } else {
        this.match.getCurrentPlayer().getPawn().changePosition(resultDice);
        }
        notifyViews();
        return resultDice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMatch() {
        this.match = this.fixture.nextMatch();
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameMode() {
        return this.match.getGameMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameModeFinished() {
        this.match.setGameMode(Match.GameMode.NONE);
        this.match.turn();
        notifyViews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final GameObserver ob) {
        this.observers.add(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(final GameObserver ob) {
        this.observers.remove(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyViews() {
        observers.forEach(GameObserver::updateState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHomePawnRGB() {
        return this.match.getClubHome().getPawn().getPawnRGB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGuestPawnRGB() {
        return this.match.getClubAway().getPawn().getPawnRGB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastBox() {
        return this.match.getCurrentPlayer().getPawn().getPosition() == 32;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lastBox() {
        this.fixture.setScore(match, this.match.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastMatch() {
        return this.fixture.seeNextMatch(this.match) == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoints() {
        final Scoreboard matchResult = match.getScore();
        if (match.getWinnerClub().isEmpty()) {
            updateClubScores(match.getClubHome(), DRAWN_POINT, matchResult.getHomeScore(), matchResult.getGuestScore());
            updateClubScores(match.getClubAway(), DRAWN_POINT, matchResult.getGuestScore(), matchResult.getHomeScore());
        } else if (match.getWinnerClub().get().equals(match.getClubHome())) {
            updateClubScores(match.getClubHome(), WINNER_POINT, matchResult.getHomeScore(), matchResult.getGuestScore());
            updateClubScores(match.getClubAway(), LOSER_POINT, matchResult.getGuestScore(), matchResult.getHomeScore());
        } else {
            updateClubScores(match.getClubHome(), LOSER_POINT, matchResult.getHomeScore(), matchResult.getGuestScore());
            updateClubScores(match.getClubAway(), WINNER_POINT, matchResult.getGuestScore(), matchResult.getHomeScore());
        }
    }

    /**
     * Method to update a specific team point and net diff.
     * 
     * @param club is the club i want to update points and net diff.
     * @param points contains the point of that club.
     * @param goalScored contains the goal scored  of that club.
     * @param goalConceded contains the goal conceided of that club.
     */
    private void updateClubScores(final Club club,
        final int points,
        final int goalScored,
        final int goalConceded) {
            club.changeNetDiffs(goalScored, goalConceded);
            club.incrementPoints(points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int diceEvent() {
       return this.match.diceEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHomeName() {
        return this.match.getClubHome().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGuestName() {
        return this.match.getClubAway().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHelpFlag(final boolean selected) {
        this.helpFlag = selected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHelpFlag() {
        return this.helpFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxName() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBoxDescript() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastShootPosition() {
        return this.match.getLastShootPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotCurrentPlayer() {
        return this.match.getNotCurrentPlayer();
    }

}
