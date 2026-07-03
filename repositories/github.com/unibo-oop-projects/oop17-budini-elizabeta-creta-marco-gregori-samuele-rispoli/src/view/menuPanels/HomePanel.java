package view.menuPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import utilities.ImageLoader;
import view.BackgroundPanel;
import view.GameFrame;
import view.MenuFrame;

public class HomePanel extends JPanel {
    /**
     * This is the Home Menu Panel which allows the user to start a new Game and to
     * exit the application
     */
    private static final long serialVersionUID = 1L;
    private GameFrame gameFrame;
    private final JButton newGame;
    private final JButton exit;

    public HomePanel() {

        ImageIcon background = ImageLoader.getInstance().getImage("images/background2.jpg");
        ImageIcon iconNewGame = ImageLoader.getInstance().getImage("images/new_game.png");
        ImageIcon image = ImageLoader.getInstance().getImage("icons/donkey-kong2.gif");
        ImageIcon title = ImageLoader.getInstance().getImage("images/logo3.png");
        ImageIcon iconExit = ImageLoader.getInstance().getImage("images/exit.png");
        BackgroundPanel backgroundPanel = new BackgroundPanel(background.getImage(), BackgroundPanel.SCALED, 0.0f,
                0.0f);

        // inizializza bottoni
        newGame = new JButton();
        newGame.setIcon(iconNewGame);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);

        newGame.addActionListener(e -> {
            this.gameFrame = new GameFrame();
            MenuFrame.getMenuFrame().dispose();

        });

        exit = new JButton();
        exit.setIcon(iconExit);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            System.exit(0);
        });

        backgroundPanel.setLayout(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(newGame);
        buttonsPanel.add(exit);
        final JLabel lblTitle = new JLabel();
        final JLabel lblImage = new JLabel();
        lblImage.setIcon(image);
        lblTitle.setIcon(title);

        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(lblTitle, BorderLayout.NORTH);
        backgroundPanel.add(buttonsPanel, BorderLayout.SOUTH);
        backgroundPanel.add(lblImage, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(backgroundPanel);
    }

}
