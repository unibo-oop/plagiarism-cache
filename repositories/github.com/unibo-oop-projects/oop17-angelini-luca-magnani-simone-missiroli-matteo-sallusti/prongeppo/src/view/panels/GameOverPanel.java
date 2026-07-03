package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javafx.util.Pair;
import utility.GameValues;
import utility.Utilities;
import view.StartMenu;


/**
 * @author Paolo
 *
 */
public class GameOverPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -7023804699365121697L;
    private static final Clip CLIP = Utilities.createClip();
    /**
     * @param score **map of team and its score**
     * @param frame **the main frame**
     * @param numcombo **the Pair(numComboTeam1, numComboTeam2)**
     */
    public GameOverPanel(final Pair<Integer, Integer> score, final Pair<Integer, Integer> numcombo, final JFrame frame) {
        super(new BorderLayout());
        final JLabel gameover = this.createLabel("GAME OVER", GameValues.FONT_MEDIUM);
        final JLabel winner = this.createLabel("Team " + (score.getKey() > score.getValue() ? "1" : "2"), GameValues.FONT_MEDIUM);
        final JButton rematch = this.createButton("Rematch");
        final JPanel micro = Utilities.fillerPanel();
        final JPanel micro2 = Utilities.fillerPanel();
        final JPanel micro3 = Utilities.fillerPanel();
        final JPanel scorePanel = Utilities.fillerPanel();
        this.setBackground(Color.BLACK);
        scorePanel.setBorder(new TitledBorder(new LineBorder(Color.BLUE), "STATS", TitledBorder.CENTER, 0, new Font("", Font.BOLD, GameValues.FONT_SMALL), Color.BLUE));
        scorePanel.setLayout(new GridBagLayout());
        final JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new GridLayout(1, 3));
        scorePanel.add(container);
        final JPanel grid = new JPanel(new GridLayout(5, 1));
        grid.setOpaque(false);
        final JLabel jscore = this.createLabel("Score", GameValues.FONT_SMALL);
        grid.add(jscore);
        final JLabel labscore = this.createLabel(score.getKey() + "  :  " + score.getValue(), GameValues.FONT_MEDIUM);
        grid.add(labscore);
        final Random rand = new Random();
        final ImageIcon gifImage = new ImageIcon(this.getClass().getResource("/res/" + GameValues.GAMEOVER_LIST_STRING.get(
                (rand.nextInt(GameValues.GAMEOVER_LIST_STRING.size())))));
        gifImage.setImage(gifImage.getImage().getScaledInstance(GameValues.GIF_DIMENSION, GameValues.GIF_DIMENSION, Image.SCALE_DEFAULT));
        final JLabel gifleft = new JLabel(gifImage);
        container.add(gifleft);
        container.add(grid);
        final JLabel gifright = new JLabel(gifImage);
        container.add(gifright);
        final JLabel combo = this.createLabel("Combos pongo-combed", GameValues.FONT_SMALLER);
        grid.add(combo);
        final JLabel combocount = this.createLabel(numcombo.getKey() + "  :  " + numcombo.getValue(), GameValues.FONT_MEDIUM);
        grid.add(combocount);
        grid.add(Utilities.fillerPanel());
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/Crowd cheer.wav"));
            CLIP.open(audioInputStream);
            notifyEnableMusic(GameValues.ISSOUNDENABLED);
        } catch (Exception ex) {
            System.out.println("Error while replaying sound");
            ex.printStackTrace();
        }
        micro2.add(gameover);
        micro3.add(winner);
        micro.add(micro2);
        micro.add(micro3);
        this.add(micro, BorderLayout.PAGE_START);
        this.add(scorePanel, BorderLayout.CENTER);
        this.add(rematch, BorderLayout.PAGE_END);
        this.setVisible(true);
        if (SetOptions.isAudio()) {
            StartMenu.notifyEnableMusic(true);
        }
        rematch.addActionListener(l -> {
            notifyEnableMusic(false);
            Utilities.changePanel(frame, this, new MatchChoicePanel(frame));
        });
        micro.setLayout(new GridLayout(2, 1));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(final MouseEvent e) { }
            @Override
            public void mousePressed(final MouseEvent e) { }
            @Override
            public void mouseExited(final MouseEvent e) { }
            @Override
            public void mouseEntered(final MouseEvent e) { }
            @Override
            public void mouseClicked(final MouseEvent e) {
                notifyEnableMusic(false);
                Utilities.changePanel(frame, GameOverPanel.this, new StartMenuPanel(new BorderLayout(), frame));
            }
        });
    }
    /**
     * @param s **String to print on this JLabel**
     * @param font **dimension of the string**
     * @return **centered JLabel with blue color**
     */
    private JLabel createLabel(final String s, final int font) {
        final JLabel toreturn = new JLabel(s);
        toreturn.setHorizontalAlignment(SwingConstants.CENTER);
        toreturn.setVerticalAlignment(SwingConstants.CENTER);
        toreturn.setFont(new Font(s, Font.BOLD, font));
        toreturn.setForeground(Color.BLUE);
        return toreturn;
    }
    /**
     * @param audio **music starts if audio == true**
     */
    public static void notifyEnableMusic(final boolean audio) {
        if (CLIP != null) {
            if (audio) {
                CLIP.start();
                CLIP.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                CLIP.close();
            }
        }
    }
    /**
     * @param s **string text of this button**
     * @return **this button**
     */
    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.BLUE, GameValues.FONT_LARGE, new Dimension(GameValues.BUTTON_LARGE, GameValues.BUTTON_LARGE));
    }
}
