package it.unibo.bmbman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.bmbman.view.utilities.ImageLoaderUtils;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Frame for help section of the main menu.
 */
public class HelpView {
    private final GUIFactory gui;
    private final JFrame frame;
    private final MainMenuView mainView;
    private String helpImagePath;
    /**
     * Generate a base frame.
     * @param mainMenuView the frame of the main menu.
     */
    public HelpView(final MainMenuView mainMenuView) {
        this.mainView = mainMenuView;
        this.gui = new GUIFactoryImpl();
        this.frame = this.gui.createFrame();
        saveHelpImagePath();
        this.loadHelpView();
    }
    /**
     * Method used to custom the frame.
     */
    private void loadHelpView() {
        final JPanel panel = new JPanel();
        this.frame.add(panel);
        this.frame.setTitle("BOMBERMAN - Help Menu");
        panel.setBackground(Color.BLACK);
        final JLabel label = new JLabel(new ImageIcon(ImageLoaderUtils.loadImage(helpImagePath)));
        panel.add(label, BorderLayout.CENTER);
        this.frame.setVisible(true);
        final JButton b = gui.createReturnButton(this.frame);
        b.addActionListener(e -> {
            this.frame.setVisible(false);
            this.mainView.getFrame().setVisible(true);
        });
    }
    /**
     * Getter Method.
     * @return the help view frame
     */
    public JFrame getFrame() {
        return this.frame;
    }
    /**
     * Save the help image path based on the screen resolution.
     */
    private void saveHelpImagePath() {
        helpImagePath = "/image/" + ScreenToolUtils.getScreenRes() + "_HelpImage.png";
    }
}
