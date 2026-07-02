package javagotchi.view.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javagotchi.controller.menu.MenuController;

/**
 * This class is the implementation of the starting menu.
 * @author giulia

 */

public class StartMenu implements Menu {
    private static final double MENU_PERC_WIDTH = 0.30;
    private static final double MENU_PERC_HEIGHT = 0.30;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    /**
     * this is the constructor for this class.
     * @param controller **this is the MenuController**
     * @param view **this is the MenuView**
     */
    public StartMenu(final MenuController controller, final MenuView view) {
        System.out.println("Creating StartMenu...");
        this.frame = new JFrame("Tamagotchi");
        this.frame.setSize((int) (screenSize.getWidth() * MENU_PERC_WIDTH), (int) (screenSize.getHeight() * MENU_PERC_HEIGHT));
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final JPanel panel = new JPanel();
        final JButton startButton = new JButton("NEW GAME ");
        final JButton exitButton = new JButton("EXIT");
        final JButton resume = new JButton("RESUME");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                StartMenu.this.frame.setVisible(false);
                view.getMenuManager().showInitMenu();
                controller.getInfoManager().writeNewInfoFile();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("Closing application...");
                System.exit(0);
            }
        });

        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("resume the game...");
                controller.resumeFile();
                 StartMenu.this.frame.setVisible(false);
                 view.getMenuManager().showSavedAvatarMenu();
            }
        });

        panel.setLayout(new GridBagLayout());
        panel.setBorder(new TitledBorder("TAMAGOTCHI STARTING MENU"));
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0; // riga
        constraints.insets = new Insets(3, 3, 3, 3);
        panel.add(startButton, constraints);

        constraints.gridy++; // riga 2
        constraints.ipadx = 10;
        constraints.ipady = 0;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(resume, constraints);

        constraints.gridy++; // riga 3
        constraints.ipadx = 40;
        constraints.ipady = 0;
        panel.add(exitButton, constraints);

        this.frame.setResizable(false);
        this.frame.getContentPane().add(panel);
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
        System.out.println("Showing StartMenu...");
    }
}