package view.menus;

import java.awt.Dimension;
/**
 * This menu is used to set name and color of all the players.
 */
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PlayerColor;

/**
 * This class represent menu is used to set name and color of all the players.
 */
public class SetGameMenu extends PlayerConfigPanel {

    private final JFrame frame = new JFrame();
    private final PlayerConfigPanel playerConfigPanel = new PlayerConfigPanel();
    private final JPanel contentPanel = new JPanel(new FlowLayout());
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String NPLAYERIMAGE = "/nPlayerWall.jpg";
    private final List<String> playerColorList;

    /**
     * This is the constructor for the set gaming menu.
     */
    public SetGameMenu() {
        super();
        playerColorList = (Arrays.asList(PlayerColor.values())).stream().map(x -> x.toString())
                .collect(Collectors.toList());
        this.frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        final ImageIcon mainImageIcon = new ImageIcon(getClass().getResource(NPLAYERIMAGE));
        final Image mainImageTmp = mainImageIcon.getImage();
        final Image scaledImage = mainImageTmp.getScaledInstance(frame.getWidth(), frame.getHeight(),
                Image.SCALE_SMOOTH);
        final JLabel mainImageLabel = new JLabel(new ImageIcon(scaledImage));

        this.frame.setContentPane(mainImageLabel); // immagine di background01
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // tengo a tutto schermo il frame
        this.frame.setLayout(new GridBagLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.contentPanel.setLayout(new FlowLayout());

        final ActionListener ac = e -> {
            this.frame.setVisible(false);
            PlayersConfigMenu secondFrameMenu = new PlayersConfigMenu(
                    Integer.parseInt((String) this.playerConfigPanel.getComboBox().getSelectedItem()), playerColorList);
            secondFrameMenu.generateConfig();
        };

        this.playerConfigPanel.getButton().addActionListener(ac);
        this.contentPanel.add(this.playerConfigPanel.getLabel());
        this.contentPanel.add(this.playerConfigPanel.getComboBox());
        this.contentPanel.add(this.playerConfigPanel.getButton());
        this.contentPanel.setOpaque(false);
        this.frame.add(this.contentPanel);
        this.frame.setVisible(true);
    }

}