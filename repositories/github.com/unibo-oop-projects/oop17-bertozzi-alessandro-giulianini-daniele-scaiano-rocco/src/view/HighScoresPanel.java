package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Panel that displays best scores marked by player in SinglePlayer mode.
 */
public class HighScoresPanel extends GamePanel {

    private static final long serialVersionUID = 1L;

    /**String used to identify HishScoresPanel instance and to put it in the JFrame. It is needed as JPanel in
     * View are supposed to be managed by {@link java.awt.CardLayout}. It has to be public because it must be accessed from outside this class.*/
    public static final String TITLE = "HighScoresScreen";
    private final JTextPane jt = new JTextPane();
    private final List<String> highscores;

    /**Constructs a JPanel that displays best scores reached by the user during the game.
     * @param view the view this panel is associated to.*/
    public HighScoresPanel(final View view) {
        super(view, HighScoresPanel.TITLE);
        this.highscores = new ArrayList<>();

        jt.setEditable(false);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

                final JLabel title = new JLabel("HighScores"); //title
                title.setAlignmentX(CENTER_ALIGNMENT);
                title.setFont(new Font("BankGothic Md BT", Font.BOLD, BUTTON_SIZE.height / 2));
                title.setForeground(Color.WHITE);
                this.add(title);

        this.add(Box.createVerticalGlue()); 

        this.add(jt);

        this.add(Box.createVerticalGlue());

        final SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        jt.setParagraphAttributes(attribs, true);
        jt.setMaximumSize(new Dimension(ViewImpl.SCREEN_WIDTH / 2, ViewImpl.SCREEN_HEIGHT / 2));

        jt.setOpaque(true);
        this.prepareButton("Back", this).addActionListener(e -> {
            this.getView().switchWindow(this, MenuPanel.TITLE);
        });

        this.add(Box.createVerticalGlue());
    }

    /**Displays the panel after getting the scores refreshed. */
    @Override
    public void display() {
        this.updateHighScorePanel();
        this.getView().switchWindow(this, HighScoresPanel.TITLE);
    }

    private void updateHighScorePanel() {
        this.getView().getObserver().update(this, ViewState.HIGHSCORES);
        this.jt.setText(this.highscores.stream().collect(Collectors.joining("\n"))); 
    }

    /**
     * @param highscores the new list of highscores
     */
    public void setHighscores(final List<String> highscores) {
        this.highscores.clear();
        this.highscores.addAll(highscores);
    }
}
