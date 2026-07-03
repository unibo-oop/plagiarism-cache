package laterunner.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import laterunner.core.GameEngine;
import laterunner.model.user.User;


/**
 * Menu is the class that displays the main menu and it's the first panel showed when the game starts.
 */
public class Menu extends PanelImpl {

    private static final long serialVersionUID = 1L;
    private static final int X = 72;
    private static final int Y = 268;
    private static final int INTER = 10;
    private static final int LEVELS = 11;
    private static final int SURVIVAL = 11;
    private ImageIcon playPng = super.getPics().getIcon(Icons.PLAY);
    private ImageIcon shopPng = super.getPics().getIcon(Icons.SHOP);
    private ImageIcon statsPng = super.getPics().getIcon(Icons.STATS);
    private ImageIcon quitPng = super.getPics().getIcon(Icons.QUIT);
    private GameEngine gmEngine;
    private Integer i = 1;
    private static Integer levUnblocked;
    private static Font fontChalkDash;
    private static final float SIZE = 48;

    /**
     * Common Menu constructor. It has no layout and draws all the components relative to each other.
     * 
     * @param road
     *          an instance of Road class
     * @param gameEngine
     *          an instance of GameEngine class
     */
    public Menu(final Road road, final GameEngine gameEngine) {

        this.setLayout(null);
        fontChalkDash = super.createFont("Digital Dot Roadsign.otf", SIZE);
        this.gmEngine = gameEngine;
        updateLevel();

        //Combo box
        JComboBox<String> level = new JComboBox<>();
        level.setFont(fontChalkDash);

        DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
        level.setRenderer(dlcr);

        for (int n = 1; n <= LEVELS; n++) {
            if (n == SURVIVAL) {
                level.addItem("S");
            } else {
                level.addItem(Integer.toString(n));
            }
        }

        level.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (e.getItemSelectable().getSelectedObjects()[0].equals("S")) {
                        i = SURVIVAL;
                    } else {
                        i = Integer.parseInt((String) e.getItemSelectable().getSelectedObjects()[0]);
                    }
                }
            }
        });

        level.setEditable(true);
        level.setOpaque(false);
        ((JTextField) level.getEditor().getEditorComponent()).setOpaque(false);
        level.setBounds(X + playPng.getIconWidth() + INTER, Y, playPng.getIconHeight(), playPng.getIconHeight());
        this.add(level);

        //Play button
        JButton play = super.createButton(playPng);
        play.setBounds(X, Y, playPng.getIconWidth(), playPng.getIconHeight());

        play.addActionListener(e -> {
            if (i <= levUnblocked || i == SURVIVAL) {
                this.gmEngine.setupLevel(i, 0);
                SceneImpl.changePanel("road");
                new Thread(road).start();
            } else {
                JOptionPane.showMessageDialog(this, "You have to unblock it!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Shop button
        JButton shop = super.createButton(shopPng);
        shop.setBounds(X, play.getY() + play.getHeight() + INTER,
                shopPng.getIconWidth(), shopPng.getIconHeight());
        shop.addActionListener(e -> {
            SceneImpl.changePanel("shop");

        });

        //Stats button
        JButton stats = super.createButton(statsPng);
        stats.setBounds(X, shop.getY() + shop.getHeight() + INTER,
                statsPng.getIconWidth(), statsPng.getIconHeight());
        stats.addActionListener(e -> {

            SceneImpl.changePanel("stats");

        });

        //Quit Button
        JButton quit = super.createButton(quitPng);
        quit.setBounds(X, stats.getY() + stats.getHeight() + INTER,
                quitPng.getIconWidth(), quitPng.getIconHeight());
        quit.addActionListener(e -> {
            System.exit(0);
         });

        this.add(play);
        this.add(shop);
        this.add(stats);
        this.add(quit);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        g.drawImage(super.getPics().getImage(Icons.MENU), 0, 0, null);
     }

    /**
     * Updates the number of the last level reached once the player has unlocked it.
     */
    public static void updateLevel() {
        levUnblocked = User.getUser().getLevelReached();
    }
}

