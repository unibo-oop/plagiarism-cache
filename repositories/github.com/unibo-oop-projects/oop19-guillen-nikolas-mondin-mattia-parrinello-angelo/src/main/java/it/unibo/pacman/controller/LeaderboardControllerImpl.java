package it.unibo.pacman.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.pacman.controller.utilities.LeaderboardIO;
import it.unibo.pacman.model.leaderboard.Leaderboard;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.view.GUIFactory;
import it.unibo.pacman.view.LeaderboardView;
import it.unibo.pacman.view.ViewableUI;
/**
 * An implementation of {@link LeaderboardController}.
 */
public class LeaderboardControllerImpl implements LeaderboardController {

    private Leaderboard leadeboard;
    private LeaderboardView lbView;
    private Map<String, Integer> ranking;
    private final ViewableUI mmv;
    private final GUIFactory gf;

    /**
     * Constructor for Leaderboard Controller.
     * 
     * @param mmv        mainMenuView, used in LeaderboardView's constructor
     * @param gf         GUIFactory, used in LeaderboardView's constructor
     */
    public LeaderboardControllerImpl(final ViewableUI mmv, final GUIFactory gf) {
        super();
        this.mmv = mmv;
        this.gf = gf;
    }

    private void createRanking(final Difficulty difficulty) {
        ranking = new HashMap<>();
        this.leadeboard = new Leaderboard(ranking);
        LeaderboardIO.readLeaderboard(difficulty);
        try {
            String player;
            player = LeaderboardIO.getNext();
            int score;
            while (player != null) {
                score = Integer.parseInt(LeaderboardIO.getNext());
                ranking.put(player, score);
                player = LeaderboardIO.getNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> getSortedRanking(final Difficulty difficulty) {
        createRanking(difficulty);
        this.leadeboard = new Leaderboard(ranking);
        return this.leadeboard.getSortedRanking();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        if (this.lbView != null) {
            this.lbView.close();
        }
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void writeScore(final String player, final int score, final Difficulty difficulty) {
        LeaderboardIO.writeLeaderboard(player, score, difficulty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final Difficulty difficulty) {
        this.lbView = new LeaderboardView(this.mmv, this.gf, difficulty, this);
        this.lbView.show();
    }
}
