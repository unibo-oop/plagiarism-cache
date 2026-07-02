package javagotchi.view.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class is the implementation of the main menu making user able to select if he wants:
 *  manage the avatar saved, check ther informations, or exit the game.
 * @author giulia
 *
 */
public class MainMenu implements Menu {

    private static final double MENU_PERC_WIDTH = 0.30;
    private static final double MENU_PERC_HEIGHT = 0.30;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Optional<JFrame> frame;

    /**
     * This is the constructor for this class.
     * @param view **this is the MenuView**
     */
    public MainMenu(final MenuView view) {
        System.out.println("Creating MainMenu..."); // log for testing
        this.frame = Optional.of(new JFrame("Menu"));
        this.frame.get().setSize((int) (screenSize.getWidth() * MENU_PERC_WIDTH), (int) (screenSize.getHeight() * MENU_PERC_HEIGHT));
        this.frame.get().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final JPanel panel = new JPanel();
        final JButton selectionButton = new JButton("Saved Avatar");
        final JButton infoButton = new JButton("Info");
        final JButton exitButton = new JButton("Exit");

        selectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MainMenu.this.frame.get().setVisible(false);
                System.out.println("Selcting avatar...");
                view.getMenuManager().showSavedAvatarMenu();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println("Exiting application...");
                System.exit(0);
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                MainMenu.this.frame.get().setVisible(false);
                view.getMenuManager().showInformationMenu();
            }
        });

        panel.setLayout(new GridBagLayout());
        panel.setBorder(new TitledBorder("Game Menu"));
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0; // row 1
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(selectionButton, constraints);
        constraints.gridy++; // row 2
        panel.add(infoButton, constraints);
        constraints.gridy++; // row 3
        panel.add(exitButton, constraints);

        this.frame.get().setResizable(false);
        this.frame.get().getContentPane().add(panel);
        this.frame.get().setLocationRelativeTo(null);
    }

    @Override
    public final void show() {
        if (this.frame.isPresent()) {
            this.frame.get().setVisible(true);
            System.out.println("Showing MainMenu..."); // log
            return;
        }
    }
}
