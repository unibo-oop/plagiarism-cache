package view.menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.BoardColorPalette;
import view.ExceptionDialog;

/**
 * This menu manage the awards of the game.
 */
public class CreditsMenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JFrame frame = new JFrame();
    private final JPanel contentPanel = new JPanel();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String HIGHSCOREIMAGE = "/credits.jpg";
    private static final int VGAP = 20;
    private final JPanel buttonsPanel = new JPanel();
    private final JButton main = new JButton("To Main");
    private final Font fontForCredits = new Font("Arial", Font.BOLD, 18);
    private final Dimension boxDimension = new Dimension(10, 80);

    private final JLabel creditsOfGame = new JLabel(
            "<html><div style='text-align: center;'><font color=\"#00ffff\">From an original idea of :</font> Jun Sasaki & Goro Sasaki </div></html>",
            SwingConstants.CENTER);
    private final JLabel creditsOfProgrammer = new JLabel(
            "<html><div style='text-align: center;'><font color=\"#00ffff\">Programmer :</font> Andrea La Rosa & Matteo Cristello </div></html>",
            SwingConstants.CENTER);
    private final JLabel creditsOfSound = new JLabel("<html><div style='text-align: center;'>"
            + "<font color=\"#00ffff\">Sound Effect from :</font><address><font color=\"yellow\"> https://www.zapsplat.com/</font></address> "
            + "</div></html>",
            SwingConstants.CENTER);
    private final JLabel creditsOfImage = new JLabel("<html><div style='text-align: center;'>"
            + "<font color=\"#00ffff\">Images created with :</font><font color=\"yellow\"> Paint 3D</font>"
            + "</div></html>", SwingConstants.CENTER);
    private final JPanel creditsPanelCentered = new JPanel();
    private final JPanel creditsPanel = new JPanel(new BorderLayout());
    private final JPanel emptyPanel = new JPanel();

    /**
     * This is the constructor for the set gaming menu.
     */
    public CreditsMenu() {
        super();
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
        main.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                new MainMenu();
                frame.setVisible(false);
            }

        });
        this.creditsOfGame.setFont(this.fontForCredits);
        this.creditsOfGame.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.creditsOfImage.setFont(this.fontForCredits);
        this.creditsOfImage.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.creditsOfProgrammer.setFont(this.fontForCredits);
        this.creditsOfProgrammer.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.creditsOfSound.setFont(this.fontForCredits);
        this.creditsOfSound.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        this.buttonsPanel.add(main);
        this.buttonsPanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.buttonsPanel.setOpaque(false);
        this.contentPanel.setLayout(new GridLayout(3, 1));
        this.creditsPanelCentered.setLayout(new BoxLayout(this.creditsPanelCentered, BoxLayout.PAGE_AXIS));
        this.creditsPanelCentered.add(Box.createRigidArea(new Dimension(0, VGAP)));
        this.creditsPanelCentered.add(this.creditsOfGame);
        this.creditsPanelCentered.add(Box.createRigidArea(new Dimension(0, VGAP)));
        this.creditsPanelCentered.add(this.creditsOfProgrammer);
        this.creditsPanelCentered.add(Box.createRigidArea(new Dimension(0, VGAP)));
        this.creditsPanelCentered.add(this.creditsOfImage);
        this.creditsPanelCentered.add(Box.createRigidArea(new Dimension(0, VGAP)));
        this.creditsPanelCentered.add(this.creditsOfSound);
        this.creditsPanel.add(this.creditsPanelCentered, BorderLayout.PAGE_END);
        this.contentPanel.add(this.emptyPanel, BorderLayout.PAGE_START);
        this.contentPanel.add(this.creditsPanel);
        this.creditsPanelCentered.setOpaque(false);
        this.creditsPanel.setOpaque(false);
        this.contentPanel.setOpaque(false);
        this.emptyPanel.setOpaque(false);
        this.emptyPanel.setPreferredSize(boxDimension);

        this.frame.add(this.contentPanel, BorderLayout.CENTER);
        this.frame.add(this.buttonsPanel, BorderLayout.PAGE_END);
        this.frame.setVisible(true);

        // from here mouse oh link

        this.creditsOfImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.creditsOfSound.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.creditsOfImage.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(
                            "https://www.microsoft.com/it-it/p/paint-3d/9nblggh5fv99?activetab=pivot:overviewtab"));

                } catch (IOException | URISyntaxException e1) {
                    new ExceptionDialog(e1.getMessage());
                    e1.printStackTrace();
                }
            }

        });

        this.creditsOfSound.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI("https://www.zapsplat.com/"));

                } catch (IOException | URISyntaxException e1) {
                    new ExceptionDialog(e1.getMessage());
                    e1.printStackTrace();
                }
            }

        });

    }

}

