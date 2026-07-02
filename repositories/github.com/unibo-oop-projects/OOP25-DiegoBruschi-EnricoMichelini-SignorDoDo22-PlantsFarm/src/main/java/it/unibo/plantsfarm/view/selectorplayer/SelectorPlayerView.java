package it.unibo.plantsfarm.view.selectorplayer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Dialog window that allows the user to select the type of player
 * before starting the game.
 * The dialog is modal, meaning that it blocks the execution flow
 * until a selection is made.
 * The user can:
 * - View information about each player type
 * - Select either Farmer or Expert Farmer
 * If no selection is made (should not normally happen due to
 * DO_NOTHING_ON_CLOSE), a default type is returned.
 */
public final class SelectorPlayerView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int COL = 2;
    private static final int ROW = 1;
    private static final int WIDTH = 450;
    private static final int HEIGH = 350;
    private PlayersTypes selectedType;

    /**
     * Creates and initializes the player selection dialog.
     * The dialog is modal and cannot be closed using the window close button.
     */
    public SelectorPlayerView() {

        setTitle("Choose your Player");
        setModal(true);
        setSize(WIDTH, HEIGH);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        final JPanel infoPanel = new JPanel(new GridLayout(ROW, COL));
        final JButton jbFarmer = new JButton("Info Farmer");
        final JButton jbExpert = new JButton("Info Expert");
        infoPanel.add(jbFarmer);
        infoPanel.add(jbExpert);
        add(infoPanel, BorderLayout.NORTH);

        final JPanel buttonPanel = new JPanel(new GridLayout(ROW, COL));
        final JButton farmerBtn = new JButton("Farmer");
        final JButton expertBtn = new JButton("Expert Farmer");

        // Selection logic
        farmerBtn.addActionListener(e -> {
            selectedType = PlayersTypes.FARMER;
            dispose();
        });

        expertBtn.addActionListener(e -> {
            selectedType = PlayersTypes.EXPERTFARMER;
            dispose();
        });

        jbExpert.addActionListener(e ->
            javax.swing.JOptionPane.showMessageDialog(
                this,
                """
                The Expert starts with all items at maximum level,
                can plant any type of plant,
                and moves significantly faster than the Farmer.
                """,
                "Expert Information",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            )
        );

        jbFarmer.addActionListener(e ->
            javax.swing.JOptionPane.showMessageDialog(
                this,
                """
                The Farmer represents the classic game experience.
                You start with all items at level 0
                and have slower movement speed.
                """,
                "Farmer Information",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            )
        );

        buttonPanel.add(farmerBtn);
        buttonPanel.add(expertBtn);
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Returns the selected player type.
     * If no selection was explicitly made, Farmer is returned as default.
     *
     * @return the selected PlayersTypes value
     */
    public PlayersTypes getSelectedType() {
        return selectedType != null ? selectedType : PlayersTypes.FARMER;
    }
}
