package it.unibo.plantsfarm.view.menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;

import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;

/**
 * Represents the menù panel of the game.
 */
public final class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int ROWS = 4;
    private static final int COLS = 1;

    private static final double GAP_RATIO = 0.01;
    private static final double PADDING_RATIO = 0.01;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    private static final int GAP = (int) (SCREEN_SIZE.height * GAP_RATIO);
    private static final int PADDING = (int) (SCREEN_SIZE.height * PADDING_RATIO);

    private static final Color BG_COLOR = new Color(139, 69, 19);

    private final JButton shop;
    private final JButton storage;
    private final JButton encyclopedia;
    private final JButton exit;

    /**
     * Initializes the menu panel using the ButtonFactory.
     */
    public MenuPanel() {
        super(new GridLayout(ROWS, COLS, GAP, GAP));

        this.setBackground(BG_COLOR);
        this.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        this.shop = ButtonFactory.createMenuButton(Texture.SHOP_ICON);
        this.storage = ButtonFactory.createMenuButton(Texture.STORAGE_ICON);
        this.encyclopedia = ButtonFactory.createMenuButton(Texture.ENCYCLOPEDIA_ICON);
        this.exit = ButtonFactory.createMenuButton(Texture.SETTINGS_ICON);

        this.add(this.shop);
        this.add(this.storage);
        this.add(this.encyclopedia);
        this.add(this.exit);
    }

    /**
     * Attaches a listener to the Shop button.
     *
     * @param listener The action to perform.
     */
    public void addShopListener(final ActionListener listener) {
        this.shop.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Storage button.
     *
     * @param listener The action to perform.
     */
    public void addStorageListener(final ActionListener listener) {
        this.storage.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Encyclopedia button.
     *
     * @param listener The action to perform.
     */
    public void addEncyclopediaListener(final ActionListener listener) {
        this.encyclopedia.addActionListener(listener);
    }

    /**
     * Attaches a listener to the Exit button.
     *
     * @param listener The action to perform.
     */
    public void addExitListener(final ActionListener listener) {
        this.exit.addActionListener(listener);
    }
}
