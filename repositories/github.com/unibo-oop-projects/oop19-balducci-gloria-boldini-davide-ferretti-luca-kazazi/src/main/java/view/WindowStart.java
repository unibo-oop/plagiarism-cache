package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import application.StealthNinja;
import controllers.camera.Camera;
import scores.WriteFile;

/**
 * 
 * Creates the main menu.
 *
 */
public class WindowStart implements UserInterface {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static String playerName;
    private final JTextField name;

    private final MyFrame frame;
    private final WindowPlay windowPlay;
    private final WindowScores windowScores;
    private final WindowLeaderboard windowLeaderboard;
    private final WriteFile writeFile;

    /**
     * Constructor for UserInterface.
     */
    public WindowStart() {

        this.name = new JTextField("", SCREEN_WIDTH / 130);
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
        this.windowPlay = new WindowPlay();
        this.windowScores = new WindowScores();
        this.windowLeaderboard = new WindowLeaderboard();
        this.writeFile = new WriteFile();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void createWindow() {

        final ImageIcon title = new ImageIcon(ClassLoader.getSystemResource("menu/Title.png"));

        final ImageIcon nickImage = new ImageIcon(ClassLoader.getSystemResource("menu/Nickname.png"));

        final ImageIcon playImage = new ImageIcon(ClassLoader.getSystemResource("menu/PLAY.png"));

        final ImageIcon leaderImage = new ImageIcon(ClassLoader.getSystemResource("menu/LEADERBOARD.png"));

        final ImageIcon infoImage = new ImageIcon(ClassLoader.getSystemResource("menu/Info.png"));

        final ImageIcon playerImage = new ImageIcon(ClassLoader.getSystemResource("menu/playerMenu.png"));

        final ImageIcon howPlayImage = new ImageIcon(ClassLoader.getSystemResource("menu/howPlay.png"));

        final ImageIcon scoresImage = new ImageIcon(ClassLoader.getSystemResource("menu/scores.png"));

        final ImageIcon legendImage = new ImageIcon(ClassLoader.getSystemResource("menu/legend.png"));

        final ImageIcon enemyImage = new ImageIcon(ClassLoader.getSystemResource("menu/enemyLegend.png"));

        final ImageIcon exitImage = new ImageIcon(ClassLoader.getSystemResource("menu/exit.png"));

        final JLabel nickname = new JLabel();
        final JLabel player = new JLabel();
        final JButton play = new JButton();
        final JButton scores = new JButton();
        final JButton leaderboard = new JButton();
        final JButton info = new JButton();
        final JButton howToPlay = new JButton();
        final JButton legend = new JButton();
        final JButton enemy = new JButton();
        final JButton exit = new JButton();
        final JPanel pUp = new JPanel(new BorderLayout());
        final JPanel pInternal = new JPanel(new BorderLayout());
        final JPanel p = new JPanel(new BorderLayout());
        final JPanel pDown = new JPanel(new BorderLayout());
        final JLabel label = new JLabel();

        label.setIcon(title);
        nickname.setIcon(nickImage);
        player.setIcon(playerImage);
        scores.setIcon(scoresImage);
        leaderboard.setIcon(leaderImage);
        play.setIcon(playImage);
        info.setIcon(infoImage);
        howToPlay.setIcon(howPlayImage);
        legend.setIcon(legendImage);
        enemy.setIcon(enemyImage);
        exit.setIcon(exitImage);

        name.setBackground(Color.red.darker());
        name.setForeground(Color.black);
        name.setOpaque(true);
        name.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 85));
        play.setBackground(Color.black);
        play.setOpaque(true);
        scores.setBackground(Color.black);
        scores.setOpaque(true);
        leaderboard.setBackground(Color.black);
        leaderboard.setOpaque(true);
        info.setBackground(Color.black);
        info.setOpaque(true);
        howToPlay.setBackground(Color.black);
        howToPlay.setOpaque(true);
        legend.setBackground(Color.black);
        legend.setOpaque(true);
        enemy.setBackground(Color.black);
        enemy.setOpaque(true);
        exit.setBackground(Color.black);
        exit.setOpaque(true);
        pUp.setBackground(Color.black);
        pUp.setOpaque(true);
        pDown.setBackground(Color.black);
        pDown.setOpaque(true);
        p.setBackground(Color.black);
        p.setOpaque(true);
        pInternal.setBackground(Color.black);
        pInternal.setOpaque(true);

        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final String insertName = name.getText();
                playerName = insertName;
                windowPlay.createWindow();
                windowPlay.setDimensions(SCREEN_WIDTH / 3, SCREEN_WIDTH / 2);
                windowPlay.show();
                StealthNinja.GUICONTROLLER.playButton(insertName);
                StealthNinja.GUICONTROLLER.setName(name);
                writeFile.createRankingFile();
                writeFile.createTimesFile();
                writeFile.createScoresFile();
                frame.dispose();
            }
        });

        scores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                writeFile.createRankingFile();
                writeFile.createTimesFile();
                writeFile.createScoresFile();
                windowScores.createWindow();
                windowScores.setDimensions(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
                windowScores.show();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        leaderboard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                writeFile.createRankingFile();
                writeFile.createTimesFile();
                windowLeaderboard.createWindow();
                windowLeaderboard.setDimensions(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2);
                windowLeaderboard.show();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        info.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                StealthNinja.GUICONTROLLER.showInfo();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        howToPlay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                StealthNinja.GUICONTROLLER.showHowToPlay();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        legend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                StealthNinja.GUICONTROLLER.showLegend();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        enemy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                StealthNinja.GUICONTROLLER.showEnemy();
                StealthNinja.GUICONTROLLER.setName(name);
            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        pDown.add(howToPlay, BorderLayout.NORTH);
        pDown.add(enemy, BorderLayout.CENTER);
        pDown.add(exit, BorderLayout.SOUTH);

        pInternal.add(leaderboard, BorderLayout.NORTH);
        pInternal.add(info, BorderLayout.CENTER);
        pInternal.add(pDown, BorderLayout.SOUTH);
        pInternal.add(scores, BorderLayout.WEST);
        pInternal.add(legend, BorderLayout.EAST);

        p.add(pUp, BorderLayout.NORTH);
        p.add(play, BorderLayout.CENTER);
        p.add(pInternal, BorderLayout.SOUTH);

        pUp.add(label, BorderLayout.NORTH);
        pUp.add(nickname, BorderLayout.WEST);
        pUp.add(name, BorderLayout.CENTER);
        pUp.add(player, BorderLayout.EAST);

        frame.getMainPanel().add(p);
        frame.setUndecorated(true);
    }

    @Override
    public final void setDimensions(final int x, final int y) {
        this.frame.setSize(x, y);
        this.frame.pack();
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
    }

    /**
     * 
     * @param g      where draw
     * @param camera camera
     * 
     * Write on display the player's name.
     */
    public static void drawName(final Graphics g, final Camera camera) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, (int) SCREEN_HEIGHT / 50));
        g.drawString(playerName, SCREEN_WIDTH / 100 + (int) camera.getX(), SCREEN_HEIGHT / 25 + (int) camera.getY());
    }

    /**
     * 
     * @return JTextFiel where get the name
     */
    public JTextField getJTextField() {
        return name;
    }


}
