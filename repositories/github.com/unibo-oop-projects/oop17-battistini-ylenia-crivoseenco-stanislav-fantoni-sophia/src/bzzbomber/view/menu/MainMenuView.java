package bzzbomber.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bzzbomber.view.GenericView;

/**
 * Class for a welcome menu that extends @Menu .
 *
 */
public final class MainMenuView implements GenericView {

    private static final int TOP = 15;
    private static final int TOPBOTTOM = 5;
    private static final int POSITION = 290;
    private static final int DISTANCE = 25;
    private static final int RIGHT = 250;
    private static final int LEFTBOTTOM = 130;
    private static final int BOTTOM = 5;
    private static final int RIGHTPANE = 230;
    private final Color color = Color.cyan;
    private final ViewManager manager;
    private final JFrame frame;

    /**
     * Constructor for @MainMenu.
     * 
     * @param manager
     *            The menu manager.
     */
    public MainMenuView(final ViewManager manager) {

        this.manager = manager;
        final GUIFactory menufactory = new GUIFactoryImpl();

        /**
         * Using a MenuFactory method create a new basic frame.
         */
        this.frame = menufactory.createBasicFrame();
        this.frame.setLayout(new BorderLayout());

        /**
         * Create a button with MenuFactory method and set our property.
         */
        final JButton start = menufactory.createButton("START GAME", "/main/Start.png");
        final JButton besttenranking = menufactory.createButton("BEST SCORES", "/main/Star.png");
        final JButton preferences = menufactory.createButton("PREFERENCES", "/main/Preferences.png");
        final JButton information = menufactory.createButton("INFORMATIONS", "/main/Info.png");

        /**
         * Create a new JPanel and set panel's background. Add to Panel all create
         * button.
         */
        final JPanel pannello = new JPanel(new GridBagLayout());
        pannello.setBackground(color);
        final GridBagConstraints constant = new GridBagConstraints();
        constant.gridy = 0;
        constant.insets = new Insets(TOPBOTTOM, LEFTBOTTOM, BOTTOM, RIGHTPANE);
        constant.fill = GridBagConstraints.HORIZONTAL;
        pannello.add(start, constant);
        constant.gridy++;
        pannello.add(besttenranking, constant);
        constant.gridy++;
        pannello.add(preferences, constant);
        constant.gridy++;
        pannello.add(information, constant);

        /**
         * Create another panel for the title.
         */

        JPanel panel = new JPanel(new GridBagLayout());
        panel = menufactory.createTitleButton("/main/BzzBomberman.png", TOP, POSITION, DISTANCE, RIGHT, color);
        final JPanel imagepane = new JPanel(new GridBagLayout());
        final JLabel lbl = new JLabel();
        final ImageIcon imageicon = new ImageIcon(menufactory.createPathImage("/main/Bomberman.png"));
        lbl.setIcon(imageicon);
        lbl.setBackground(color);
        imagepane.add(lbl);
        imagepane.setBackground(color);

        start.addActionListener(e -> {
            this.frame.setVisible(false);
            this.manager.showAvatarMenu();
        });

        besttenranking.addActionListener(e -> {
            this.manager.showStatisticMenu();
        });

        preferences.addActionListener(e -> {
            this.manager.showPreferencesMenu();
        });

        information.addActionListener(e -> {
            this.manager.showInformationMenu();
        });
        /**
         * Add to frame all components created. Also set frame's property.
         */
        this.frame.getContentPane().add(panel, BorderLayout.NORTH);
        this.frame.getContentPane().add(pannello, BorderLayout.WEST);
        this.frame.getContentPane().add(imagepane, BorderLayout.CENTER);

    }

    @Override
    public void show() {
        this.frame.setVisible(true);
        System.out.println("Show MainMenu...");
    }

}
