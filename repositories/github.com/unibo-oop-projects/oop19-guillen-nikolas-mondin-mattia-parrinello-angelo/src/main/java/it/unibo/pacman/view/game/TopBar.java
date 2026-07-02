package it.unibo.pacman.view.game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.pacman.model.entities.Mortal;
import it.unibo.pacman.model.leaderboard.PlayerScoreImpl;
import it.unibo.pacman.view.GUIFactory;

/**
 * Class that builds the top bar of game's UI, used to keep trace of score and lives.
 */
public class TopBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private final PlayerScoreImpl psi;
    private final JLabel score;
    private final JLabel lives;
    private final Mortal pacman;
    /**
     * Used to create {@link TopBar}.
     * @param gf the GUIFactory.
     * @param psi the class that handle the score of the player.
     * @param pacman the pacman that represents the hero.
     */
    public TopBar(final GUIFactory gf, final PlayerScoreImpl psi, final Mortal pacman) {
        super();
        this.psi = psi;
        this.pacman = pacman;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        score = gf.createLabel();
        lives = gf.createLabel();
        this.add(lives, BorderLayout.EAST);
        lives.setText("LIVES: " + Integer.toString(pacman.getLives()));
        this.add(score, BorderLayout.WEST);
        score.setText("SCORE: " + Integer.toString(this.psi.getScore()));
    }

    /**
     * Used to update score and lives labels.
     */
    public final void update() {
        score.setText("SCORE: " + Integer.toString(psi.getScore()));
        lives.setText(Integer.toString(pacman.getLives()));
    }
}
