package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javafx.util.Pair;
import utility.GameValues;
import utility.Utilities;

/**
 * The JPanel containing both the PongPanel and the score JLabel.
 * @author Paolo
 *
 */
public class MainLedge extends JPanel implements GraphicEnvironment {
    /**
     * 
     */
    private static final long serialVersionUID = 3059025763437768768L;
    private final JFrame frame;
    private final JLabel labScore;
    /**
     * @param pongpanel ** PongPanel of the game **
     * @param frame ** the main frame **
     */
    public MainLedge(final JPanel pongpanel, final JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        this.labScore = new JLabel();
        this.updateScore(new Pair<>(0, 0));
        this.labScore.setFont(new Font("score", Font.BOLD, GameValues.FONT_MEDIUM));
        this.labScore.setBackground(Color.GREEN);
        this.labScore.setOpaque(true);
        this.labScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.labScore, BorderLayout.NORTH);
        this.add(pongpanel, BorderLayout.CENTER);
        this.setVisible(true);
        this.grabFocus();
    }

    /**
     * Updates the score.
     * @param score **the pair of score team1 team2**
     */
    @Override
    public void updateScore(final Pair<Integer, Integer> score) {
        this.labScore.setText(score.getKey() + "  :  " + score.getValue());
    }
    /**
     * Closes MainLedge and opens a new GameOverPanel.
     * @param finalScore **score of match (int, int)**
     * @param numCombo **combos do from players (int, int)**
     */
    @Override
    public void close(final Pair<Integer, Integer> finalScore, final Pair<Integer, Integer> numCombo) {
        Utilities.changePanel(this.frame, this, new GameOverPanel(finalScore, numCombo, this.frame));
        this.frame.paintAll(this.frame.getGraphics());
        this.frame.repaint();
    }

    /**
     *Repaint all object contained in MainLedge.
     */
    @Override
    public void render() {
        this.repaint();
    }
}
