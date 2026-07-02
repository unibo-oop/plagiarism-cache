package javawulf.view.gamemenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javawulf.scoreboard.ScoreBoardImpl;
import javawulf.scoreboard.Scoreboard;
import javawulf.view.GamePanel;

import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameMenuPanel is used to display the GUI.
 */
public class GameMenuPanel extends JPanel {

    public static final long serialVersionUID = 4328743;
    private static final int SCALE_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    private static final int SCALE_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    @SuppressFBWarnings(
        value = {
            "M", "D", "ST"
        },
        justification = "menuborders needs to be static to become a box"
    )
    private static int menuBorders;
    @SuppressFBWarnings(
        value = {
            "M", "D", "ST"
        },
        justification = "scoreBoardBoerders needs to be static to become a box"
    )
    private static int scoreboardBorders;

    private static final int MAX_BUTTON_WIDTH = 800;
    private static final int MAX_BUTTON_HEIGHT = 120;
    private static final int MENU_OFFSET = 5;
    private static final int SCOREBOARD_OFFSET = 7;
    private static final int COLS_RESULTS = 3;
    private final JFrame frame; // NOPMD suppressed as this is used in the constructor

    /**
     * Sets the size of the window and creates the menu.
     * @throws InterruptedException 
     */
    public GameMenuPanel() throws InterruptedException {
        menuBorders = SCALE_X / MENU_OFFSET; // NOPMD suppressed as it is a false positive
        scoreboardBorders = SCALE_X / SCOREBOARD_OFFSET; // NOPMD suppressed as it is a false positive
        frame = new JFrame("JavaWulf");
        createMenuGUI(frame);
    }

    /**
     * Create the buttons of the game menu.
     * @param frame Is the frame where the menu is shown
     */
    public static void createMenuGUI(final JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(SCALE_Y, SCALE_X));
        frame.setSize(new Dimension(SCALE_Y, SCALE_X));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        showMenu(frame);

        frame.setVisible(true);
    }

    private static void showMenu(final JFrame frame) {

        final JPanel menu = new JPanel(new GridLayout(2, 2));
        final JButton startButton = new JButton("PLAY");
        final JButton leaderboardButton = new JButton("Leaderboard");
        final JButton guideButton = new JButton("Guide");
        final JButton exitButton = new JButton("Exit");

        // To limit Max button sixing
        final Dimension maxButtonSize = new Dimension(MAX_BUTTON_WIDTH, MAX_BUTTON_HEIGHT);
        startButton.setMaximumSize(maxButtonSize);
        leaderboardButton.setMaximumSize(maxButtonSize);
        guideButton.setMaximumSize(maxButtonSize);
        exitButton.setMaximumSize(maxButtonSize);

        // Start Game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                frame.getContentPane().removeAll();
                frame.add(new GamePanel(frame));
                frame.setSize(GamePanel.TILESIZE * GamePanel.MAX_SCREEN_COL, GamePanel.TILESIZE * GamePanel.MAX_SCREEN_ROW);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        // Show LeaderBoard
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                showLeaderboard(frame);
            }
        });
        // Show Guide
        guideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Press the UP key to move up\n"
                    + "Press the DOWN key to move down\n" + "Press the LEFT key to move left\n"
                    + "Press the RIGHT key to move right\n" + "Press the COMMA (,) key to attack\n");
            }
        });
        // Exit 
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final int choice = JOptionPane.showConfirmDialog(frame,
                        "Sure you want to quit?",
                        "Confirm exit",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    exitApplication();
                }
            }
        });

        menu.add(startButton);
        menu.add(leaderboardButton);
        menu.add(guideButton);
        menu.add(exitButton);
        frame.add(menu, BorderLayout.CENTER);
        frame.add(Box.createVerticalStrut(menuBorders), BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(menuBorders), BorderLayout.SOUTH);
        frame.add(Box.createHorizontalStrut(menuBorders), BorderLayout.WEST);
        frame.add(Box.createHorizontalStrut(menuBorders), BorderLayout.EAST);
    }

    private static void showLeaderboard(final JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        final Scoreboard scoreboard = new ScoreBoardImpl();
        scoreboard.loadScoreBoardFromFile();
        JPanel scoreBoardJPanel;
        final JPanel leaderboardPanel = new JPanel(new BorderLayout());
        final JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        final JPanel legendPanel = new JPanel(new GridLayout(1, COLS_RESULTS));
        final JLabel titleLabel = new JLabel("LeaderBoard", SwingConstants.CENTER);
        final JLabel legendNicknameLabel = new JLabel("Nickname", SwingConstants.CENTER);
        final JLabel legendScoreLabel = new JLabel("Score", SwingConstants.CENTER);
        final JLabel legendWonLabel = new JLabel("Did you win?", SwingConstants.CENTER);

        final JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.getContentPane().removeAll();
                showMenu(frame);
                frame.setVisible(true);
            }
        });

        if (!scoreboard.getAllScores().isEmpty()) {
            scoreBoardJPanel = new JPanel(new GridLayout(scoreboard.getAllScores().size(), COLS_RESULTS));
            scoreboard.getAllScores().forEach(score -> {
            final JLabel nameLabel = new JLabel(score.getUserName());
            final JLabel scoreLabel = new JLabel(Integer.toString(score.getScore()), SwingConstants.CENTER);
            final JLabel wonLabel = new JLabel(score.hasWon() ? "yes" : "no", SwingConstants.CENTER);
            scoreBoardJPanel.add(nameLabel);
            scoreBoardJPanel.add(scoreLabel);
            scoreBoardJPanel.add(wonLabel);
            });
        } else {
            scoreBoardJPanel = new JPanel();
            scoreBoardJPanel.add(new JLabel("no results yet!"));
        }

        legendPanel.add(legendNicknameLabel);
        legendPanel.add(legendScoreLabel);
        legendPanel.add(legendWonLabel);
        titlePanel.add(titleLabel);
        titlePanel.add(legendPanel);
        leaderboardPanel.add(titlePanel, BorderLayout.NORTH);
        leaderboardPanel.add(scoreBoardJPanel, BorderLayout.CENTER);
        leaderboardPanel.add(backButton, BorderLayout.SOUTH);
        frame.add(leaderboardPanel, BorderLayout.CENTER);
        frame.add(Box.createVerticalStrut(scoreboardBorders), BorderLayout.NORTH);
        frame.add(Box.createVerticalStrut(scoreboardBorders), BorderLayout.SOUTH);
        frame.add(Box.createHorizontalStrut(scoreboardBorders), BorderLayout.WEST);
        frame.add(Box.createHorizontalStrut(scoreboardBorders), BorderLayout.EAST);

        frame.revalidate();
        frame.repaint();
    }

    private static void exitApplication() {
        System.exit(0);
    }

}
