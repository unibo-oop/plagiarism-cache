package it.unibo.bmbman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.bmbman.controller.MainMenuController;
import it.unibo.bmbman.controller.MainMenuList;
import it.unibo.bmbman.controller.MenuController;
import it.unibo.bmbman.controller.OptionMenuController;
import it.unibo.bmbman.controller.OptionsMenuList;
import it.unibo.bmbman.view.utilities.ImageLoaderUtils;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * define the start menu of the game.
 */
public class MainMenuView {
    private static final double CENTER_SCALE_WIDTH = 0.4;
    private static final double EAST_SCALE_WIDTH = 0.6;
    private static final double PANEL_SCALE_HEIGHT = 0.8;
    private static final double NORTH_SCALE_HEIGHT = 0.2;
    private static final Insets INSETS = new Insets(25, 60, 35, 20);
    private final Map<JButton, MainMenuList> jbMap = new HashMap<>();
    private final MenuController<MainMenuList> mainMenuController;
    private final GUIFactoryImpl gui;
    /**
     * Parameter added to manage GridBag layout.
     */
    private GridBagConstraints c;
    private JPanel centerP;
    private JFrame f;
    private String titleImagePath;
    private String mainImagePath;

    /**
     * Create the main menu view.
     */
    public MainMenuView() {
        this.gui = new GUIFactoryImpl();
        final MenuController<OptionsMenuList> optMenuController = new OptionMenuController();
        this.mainMenuController = new MainMenuController(this, optMenuController);
        saveMainImagePath();
        saveTitleImagePath();
    }
    /**
     * Load all the menu components.
     */
    public void loadMainMenuView() {
        f = this.gui.createFrame();
        f.setTitle("BOMBERMAN - Main Menu");
        loadPanels();
        loadButtons();
        f.setVisible(true);
        f.pack();
    }
    /**
     * Used to get MainMenu frame.
     * @return {@link JFrame}
     */
    public JFrame getFrame() {
        return this.f;
    }
    /**
     * Create three panels and add them to the frame.
     */
    private void loadPanels() {
        centerP = new JPanel(new GridBagLayout());
        centerP.setBackground(Color.BLACK);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 1.0;
        c.insets = gui.createScaledInsets(INSETS);
        centerP.setPreferredSize(new Dimension((int) (f.getWidth() * CENTER_SCALE_WIDTH), (int) (f.getHeight() * PANEL_SCALE_HEIGHT)));
        final JPanel eastP = new JPanel(new BorderLayout());
        eastP.setPreferredSize(new Dimension((int) (f.getWidth() * EAST_SCALE_WIDTH), (int) (f.getHeight() * PANEL_SCALE_HEIGHT)));
        eastP.setBackground(Color.BLACK);
        final JLabel label = new JLabel(new ImageIcon(ImageLoaderUtils.loadImage(mainImagePath)));
        eastP.add(label, BorderLayout.CENTER);
        final JPanel northP = new JPanel(new BorderLayout());
        northP.setPreferredSize(new Dimension(f.getWidth(), (int) (f.getHeight() * NORTH_SCALE_HEIGHT)));
        final JLabel title = new JLabel(new ImageIcon(ImageLoaderUtils.loadImage(titleImagePath)));
        northP.setBackground(Color.BLACK);
        northP.add(title, BorderLayout.SOUTH);
        f.add(centerP, BorderLayout.CENTER);
        f.add(northP, BorderLayout.NORTH);
        f.add(eastP, BorderLayout.EAST);
    }
    /**
     * Create a buttons for each menu option and add them to the panel.
     */
    private void loadButtons() {
        for (int i = 0; i < MainMenuList.values().length; i++) {
            final JButton b = gui.createButton(MainMenuList.values()[i].toString());
            b.addActionListener(e -> {
                final JButton jb = (JButton) e.getSource();
                mainMenuController.setOptionSelected(jbMap.get(jb));
                this.f.setVisible(false);
            });
            b.setBorderPainted(true);
            c.gridx = 0;
            c.gridy = i;
            centerP.add(b, c);
            jbMap.put(b, MainMenuList.values()[i]);
        }
    }
    /**
     * Save the main image path based on the screen resolution.
     */
    private void saveMainImagePath() {
        mainImagePath = "/image/" + ScreenToolUtils.getScreenRes() + "_MainImage.png";
    }
    /**
     * Save the title image path based on the screen resolution.
     */
    private void saveTitleImagePath() {
        titleImagePath = "/image/" + ScreenToolUtils.getScreenRes() + "_TitleImage.jpg";
    }
}
