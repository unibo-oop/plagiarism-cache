package view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.BoardColorPalette;

/**
 * 
 * The Main class to start the application.
 *
 */
public class MainMenu {
	
    private final JFrame frame = new JFrame();

    private final JPanel panelContainer = new JPanel(new GridLayout(4, 1, 0, 10));
    private final JButton buttonStartGame = new JButton("START GAME");
    private final JButton buttonRules = new JButton("RULES");
    private final JButton buttonHighScore = new JButton("HIGHSCORE");
    private final JButton buttonCredits = new JButton("CREDITS");
    private final Font fontForMain = new Font("Arial", Font.BOLD, 20);
    private static final String MAINADDR = "/Main.jpg";
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * The constructor.
     */
    public MainMenu() {
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // tengo a tutto schermo il frame
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        final ImageIcon mainImageIcon = new ImageIcon(getClass().getResource(MAINADDR));
        final Image mainImageTmp = mainImageIcon.getImage();
        final Image scaledImage = mainImageTmp.getScaledInstance(frame.getWidth(), frame.getHeight(),
                Image.SCALE_SMOOTH);
        final JLabel mainImageLabel = new JLabel(new ImageIcon(scaledImage));

        this.frame.setContentPane(mainImageLabel); // immagine di background

        this.frame.setLayout(new GridBagLayout());

        this.panelContainer.setOpaque(false);
        this.panelContainer.add(this.buttonStartGame);
        this.panelContainer.add(this.buttonRules);
        this.panelContainer.add(this.buttonHighScore);
        this.panelContainer.add(this.buttonCredits);

        this.buttonCredits.setFont(fontForMain);
        this.buttonCredits.setBackground(Color.WHITE);
        this.buttonCredits.setForeground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.buttonCredits.setOpaque(true);

        this.buttonHighScore.setFont(fontForMain);
        this.buttonHighScore.setBackground(Color.WHITE);
        this.buttonHighScore.setForeground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.buttonHighScore.setOpaque(true);

        this.buttonRules.setFont(fontForMain);
        this.buttonRules.setBackground(Color.WHITE);
        this.buttonRules.setForeground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.buttonRules.setOpaque(true);

        this.buttonStartGame.setFont(fontForMain);
        this.buttonStartGame.setBackground(Color.WHITE);
        this.buttonStartGame.setForeground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
        this.buttonStartGame.setOpaque(true);

        final ActionListener actionListenerStartGame = e -> {

            this.frame.setVisible(false);
            new SetGameMenu();
        };

        final ActionListener actionListenerMain = e -> {

            this.frame.setVisible(false);
            new RulesMenu();
        };

        final ActionListener actionListenerHighscore = e -> {

            this.frame.setVisible(false);
            new HighScoresMenu();
        };

        final ActionListener actionListenerCredits = e -> {

            this.frame.setVisible(false);
            new CreditsMenu();
        };

        this.buttonStartGame.addActionListener(actionListenerStartGame);
        this.buttonRules.addActionListener(actionListenerMain);
        this.buttonHighScore.addActionListener(actionListenerHighscore);
        this.buttonCredits.addActionListener(actionListenerCredits);
        this.frame.add(this.panelContainer);
        this.frame.setVisible(true);

    }

    /**
     * @param args
     *                 -
     */
    public static void main(final String[] args) {

        new MainMenu();
    }
}
