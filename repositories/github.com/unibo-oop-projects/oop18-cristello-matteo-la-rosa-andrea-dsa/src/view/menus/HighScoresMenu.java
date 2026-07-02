package view.menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ControllerUtils;
import controller.ScoreList;
import view.BoardColorPalette;
import view.ExceptionDialog;

/**
 * This class represent menu is used to set name and color of all the players.
 */
public class HighScoresMenu {

    private final JFrame frame = new JFrame();
    private final JPanel contentPanel = new JPanel();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String HIGHSCOREIMAGE = "/Highscore.jpg";
    private final JPanel buttonsPanel = new JPanel();
    private final JButton main = new JButton("To Main");
    private final JPanel scoresPanel = new JPanel();
    private final JPanel scoresPanelCentered = new JPanel();
    private final Font fontForScores = new Font("Arial", Font.BOLD, 48);

    /**
     * This is the constructor for the set gaming menu.
     */
    public HighScoresMenu() {
        try {
           final ScoreList myScoreList = new ScoreList(ControllerUtils.getHighScore());
           myScoreList.getScores().forEach(x -> {
               final JLabel scoreLabel = new JLabel(
                       "<html><div style='text-align: center;'>" + x.getName() + " " + x.getScore() + "</div></html>",
                       SwingConstants.CENTER);
               scoreLabel.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
               scoreLabel.setFont(fontForScores);
               this.scoresPanelCentered.add(scoreLabel);
           });
        } catch (IOException e1) {
            new ExceptionDialog(e1.getMessage());
            e1.printStackTrace();
        }
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // tengo a tutto schermo il frame
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        final ImageIcon mainImageIcon = new ImageIcon(getClass().getResource(HIGHSCOREIMAGE));
        final Image mainImageTmp = mainImageIcon.getImage();
        final Image scaledImage = mainImageTmp.getScaledInstance(frame.getWidth(), frame.getHeight(),
                Image.SCALE_SMOOTH);
        final JLabel mainImageLabel = new JLabel(new ImageIcon(scaledImage));

        this.frame.setContentPane(mainImageLabel); // immagine di background
        this.frame.setLayout(new BorderLayout());
        this.scoresPanelCentered.setLayout(new BoxLayout(scoresPanelCentered, BoxLayout.PAGE_AXIS));
        main.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                new MainMenu();
                frame.setVisible(false);
            }

        });

        this.scoresPanel.setLayout(new BorderLayout());
        this.scoresPanel.setOpaque(false);

        this.buttonsPanel.add(main);
        this.buttonsPanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.buttonsPanel.setOpaque(false);

        scoresPanelCentered.setOpaque(false);
        this.scoresPanel.add(scoresPanelCentered, BorderLayout.PAGE_END);
        this.contentPanel.setLayout(new GridLayout(2, 1));
        this.contentPanel.add(scoresPanel);

        this.contentPanel.setOpaque(false);
        this.frame.add(this.contentPanel, BorderLayout.CENTER);
        this.frame.add(this.buttonsPanel, BorderLayout.PAGE_END);
        this.frame.setVisible(true);
    }

}