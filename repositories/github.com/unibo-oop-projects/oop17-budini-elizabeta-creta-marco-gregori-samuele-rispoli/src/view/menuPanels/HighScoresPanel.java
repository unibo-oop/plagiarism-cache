package view.menuPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.ImageLoader;
import utilities.Pair;
import view.BackgroundPanel;
import view.ViewImpl;

/**
 * This is the High Scores Panel which allows the user to check out the last
 * scores in the previews games If the application is run for the first time no
 * scores are displayed in this panel
 */
public class HighScoresPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -1660139571372451321L;
    private List<Pair<String, Integer>> scores;
    private final JPanel panelName;

    public HighScoresPanel() {
        final ImageIcon background = ImageLoader.getInstance().getImage("images/background2.jpg");
        final ImageIcon highsc = ImageLoader.getInstance().getImage("images/high_scores_text.png");
        
        final JLabel labelhigh = new JLabel();
        labelhigh.setIcon(highsc);
        labelhigh.setHorizontalAlignment(SwingConstants.CENTER);


        panelName = new JPanel();
        panelName.setLayout(new BoxLayout(panelName, BoxLayout.Y_AXIS));

        BackgroundPanel backgroundPanel = new BackgroundPanel(background.getImage(), BackgroundPanel.SCALED, 0.0f,
                0.0f);

        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.add(panelName, BorderLayout.CENTER);
        backgroundPanel.add(labelhigh, BorderLayout.NORTH);

        this.setLayout(new BorderLayout());
        this.add(backgroundPanel);
        
        ViewImpl.getHighScoreManager().addScore(new Pair<>("GIORGIA", 10));
        ViewImpl.getHighScoreManager().addScore(new Pair<>("LORENZO", 20));
        updateScores();

        this.add(backgroundPanel);
    }

    public void updateScores() {
        scores = ViewImpl.getHighScoreManager().getScores();
        if (!scores.isEmpty()) {
            for (Pair<String, Integer> pair : scores) {
                final JLabel text = new JLabel();
                text.setText("nome: " + pair.getX() + " score: " + pair.getY());
                panelName.add(text);
                text.setOpaque(true);
                text.setBackground(Color.GREEN.darker());
                text.setFont(new Font("Arial", Font.BOLD, 20));
                text.setAlignmentX(Component.CENTER_ALIGNMENT);

            }
        } else {
            final JLabel text = new JLabel();
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setFont(new Font("Courier New", Font.ITALIC, 20));
            text.setOpaque(true);
            text.setBackground(Color.ORANGE.darker());
            text.setText("No score to display");
            panelName.add(text, BorderLayout.NORTH);
            text.setAlignmentX(Component.CENTER_ALIGNMENT);

        }
    }
}
