package it.unibo.bmbman.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.leaderboard.PlayerScoreImpl;
import it.unibo.bmbman.view.utilities.ImageLoaderUtils;
/**
 * Create TopBar inside {@link SinglePlayerView}.
 */
public class TopBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int DIM = 40;
    private static final double WEIGHTX = 0.1;
    private final JLabel gameTime;
    private final JLabel score;
    private final JLabel lives;
    private JLabel key;
    private final HeroImpl hero;
    private boolean keyloaded;
    private final PlayerScoreImpl ps;
    private final GridBagConstraints c = new GridBagConstraints();
    /**
     * Construct TopBar.
     * @param gui 
     * @param ps 
     * @param hero 
     */
    public TopBar(final GUIFactory gui, final PlayerScoreImpl ps, final HeroImpl hero) {
        super();
        final JLabel heart;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = WEIGHTX;
        gameTime = gui.createLabel("");
        score = gui.createLabel("");
        lives = gui.createLabel("");
        heart = new JLabel((new ImageIcon(ImageLoaderUtils.loadImage("/powerups/+life.png"))));
        this.add(heart, c);
        c.insets = new Insets(0, 0, 0, 0);
        this.add(lives, c);
        c.insets = new Insets(0, 100, 0, 0);
        this.add(score, c);
        gameTime.setPreferredSize(new Dimension(DIM, 0));
        this.add(gameTime, c);
        this.hero = hero;
        this.ps = ps;
        this.keyloaded = false;
    }
    /**
     * Used to update the graphic.
     */
    public void render() {
        this.score.setText(String.valueOf(this.ps.getScore()));
        this.lives.setText(String.valueOf(hero.getLives()));
        if (hero.hasKey() && !keyloaded) {
            keyloaded = true;
            key = new JLabel((new ImageIcon(ImageLoaderUtils.loadImage("/powerups/key.png"))));
            this.add(key, c);
        }
    }
    /**
     * Get panel.
     * @return panel
     */
    public JPanel getPanel() {
        return this;
    }
    /**
     * Get label of gameTime.
     * @return label gameTime
     */
    public JLabel getLabelTime() {
        return this.gameTime;
    }
}
