package it.unibo.pacman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import it.unibo.pacman.controller.LeaderboardController;
import it.unibo.pacman.model.utilities.Difficulty;

/**
 * GUI to show the leaderboard.
 */
public class LeaderboardView implements ViewableUI {

    private JTextArea players;
    private JTextArea scores;
    private JFrame frame;
    private final GUIFactory gf;
    private final ViewableUI mmv;
    private final LeaderboardController lbc;
    private static final int WIDTH = 450;
    private static final int HEIGHT = 600;
    private final Difficulty difficulty;
    /**
     * Create a {@link LeaderboardView}.
     * @param mmv the mainmenuview.
     * @param gf the GUIFactory.
     * @param difficulty the difficulty.
     * @param lbc the controller of leaderboard.
     */
    public LeaderboardView(final ViewableUI mmv, final GUIFactory gf, final Difficulty difficulty, final LeaderboardController lbc) {
        this.mmv = mmv;
        this.gf = gf;
        this.difficulty = difficulty;
        this.lbc = lbc;
        buildView();
    }

    private void buildView() {
        frame = gf.createFrame();
        final JPanel panel = gf.createJPanel(new BorderLayout(), Color.BLACK);
        final JLabel header = gf.createLabel();
        players = gf.createTextArea(Color.BLACK, Color.WHITE, false);
        scores = gf.createTextArea(Color.BLACK, Color.WHITE, false);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setText("TOP SCORES");
        panel.add(header, BorderLayout.NORTH);
        panel.add(players, BorderLayout.WEST);
        panel.add(scores, BorderLayout.EAST);
        panel.add(gf.createMenuButton("RETURN", e -> {
            this.close();
            this.mmv.show();
        }), BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(false);
    }

    private void writeRanking() {
        final Map<String, Integer> ranking = lbc.getSortedRanking(this.difficulty);
        String playersList = "";
        String scoreList = "";
        for (final String player : ranking.keySet()) {
            playersList = playersList.concat(player + "\n");
            scoreList = scoreList.concat(ranking.get(player).toString() + "\n");
        }
        players.setText(playersList);
        scores.setText(scoreList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        mmv.close();
        writeRanking();
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.frame.dispose();
        this.mmv.show();
    }
}
