package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoli.controller.EVersion;
import it.unibo.monopoli.view.listener.StartPlay;
import it.unibo.monopoli.view.listener.VersionSelected;

/**
 * 
 * This class represents the frame of starting the game.
 *
 */
public class Go {
    private static int numPlayer = 0;

    /**
     * class constructor Go. Create the main frame, with its components: version
     * and information very players and typology.
     */
    public Go() {
        final MyFrame start = new MyFrame("Start - Monopoli", new BorderLayout(), new Dimension(900, 450));
        start.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        start.setLocation(new Point(300, 100));
        start.getMainPanel().setLayout(new BorderLayout(0, 0));

        final JPanel panel = new JPanel();
        start.getMainPanel().add(panel, BorderLayout.CENTER);

        final JLabel lblMonopoliBenvenuto = new JLabel("MONOPOLI - WELCOME");
        panel.add(lblMonopoliBenvenuto);
        lblMonopoliBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonopoliBenvenuto.setFont(new Font("Berlin Sans FB", Font.BOLD, 50));

        final JPanel gridC = new JPanel();
        panel.add(gridC);
        final GridBagLayout gbcGridC = new GridBagLayout();
        gbcGridC.columnWidths = new int[] { 81, 237, 157, 104, 0, 0, 63, 0, 1 };
        gbcGridC.rowHeights = new int[] { 24, 53, 0, 166, 31, 36, -17, 2 };
        gbcGridC.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
        gbcGridC.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
        gridC.setLayout(gbcGridC);

        final JPanel firstRow = new JPanel();
        final GridBagConstraints gbcRow1 = new GridBagConstraints();
        gbcRow1.anchor = GridBagConstraints.WEST;
        gbcRow1.insets = new Insets(0, 0, 5, 5);
        gbcRow1.fill = GridBagConstraints.VERTICAL;
        gbcRow1.gridx = 1;
        gbcRow1.gridy = 1;
        gridC.add(firstRow, gbcRow1);

        final JLabel lblChooseVersion = new JLabel("Choose version: ");
        lblChooseVersion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblChooseVersion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        firstRow.add(lblChooseVersion);

        final JPanel panelComboBox = new JPanel();
        final GridBagConstraints gcbPanelComboBox = new GridBagConstraints();
        gcbPanelComboBox.insets = new Insets(0, 0, 5, 5);
        gcbPanelComboBox.fill = GridBagConstraints.BOTH;
        gcbPanelComboBox.gridx = 2;
        gcbPanelComboBox.gridy = 1;
        gridC.add(panelComboBox, gcbPanelComboBox);

        final JComboBox<String> comboBoxVersion = new JComboBox<String>();
        lblChooseVersion.setLabelFor(comboBoxVersion);
        comboBoxVersion.addItemListener(new VersionSelected());
        panelComboBox.add(comboBoxVersion);
        comboBoxVersion.setModel(new DefaultComboBoxModel<String>() {

            private boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(final Object anObject) {
                if (!C.NOT_SELECTABLE_OPTION.equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }

        });
        Arrays.asList(EVersion.values()).forEach(v -> comboBoxVersion.addItem(v.getName()));

        final JPanel secondRow = new JPanel();
        final GridBagConstraints gcbRow2 = new GridBagConstraints();
        gcbRow2.gridwidth = 2;
        gcbRow2.anchor = GridBagConstraints.WEST;
        gcbRow2.insets = new Insets(0, 0, 5, 5);
        gcbRow2.fill = GridBagConstraints.VERTICAL;
        gcbRow2.gridx = 1;
        gcbRow2.gridy = 2;
        gridC.add(secondRow, gcbRow2);

        final JLabel lblChooseNumber = new JLabel("Choose number of players, their type and color of the pawn: ");
        lblChooseNumber.setVerticalAlignment(SwingConstants.TOP);
        lblChooseNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblChooseNumber.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        secondRow.add(lblChooseNumber);

        final JPanel panelBtnAddPlayer = new JPanel();
        final GridBagConstraints gbcPanelBtnAddPlayer = new GridBagConstraints();
        gbcPanelBtnAddPlayer.insets = new Insets(0, 0, 5, 5);
        gbcPanelBtnAddPlayer.fill = GridBagConstraints.BOTH;
        gbcPanelBtnAddPlayer.gridx = 3;
        gbcPanelBtnAddPlayer.gridy = 2;
        gridC.add(panelBtnAddPlayer, gbcPanelBtnAddPlayer);

        final JPanel playerPanel = new JPanel();
        final GridBagConstraints gbcPlayerPanel = new GridBagConstraints();
        gbcPlayerPanel.insets = new Insets(0, 0, 5, 5);
        gbcPlayerPanel.gridwidth = 6;
        gbcPlayerPanel.fill = GridBagConstraints.BOTH;
        gbcPlayerPanel.gridx = 1;
        gbcPlayerPanel.gridy = 3;
        gridC.add(playerPanel, gbcPlayerPanel);
        playerPanel.setLayout(new FlowLayout());

        final JPanel computer = new InizializedComputer().build(playerPanel);
        playerPanel.add(computer);
        addNumPlayers(1);

        final JButton btnAddPlayer = new JButton("Add Player");

        btnAddPlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                if (!InizializedPlayer.isSave()) {
                    new Dialog(new JFrame(), "Error", "Error! Before you add do you have to save the player");
                } else if (playerPanel.getComponentCount() < C.MAX_PLAYERS) {
                    playerPanel.add(new InizializedPlayer().build(playerPanel));
                    playerPanel.revalidate();

                    addNumPlayers(1);
                    InizializedPlayer.setSave(false);

                } else {
                    new Dialog(new JFrame(), "Error", "Error! You can not enter more than 6 players");
                }
            }
        });

        panelBtnAddPlayer.add(btnAddPlayer);

        final JButton btnNewButton = new JButton("AVVIA PARTITA");
        btnNewButton.addActionListener(new StartPlay());
        btnNewButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        final GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
        gbcBtnNewButton.gridheight = 2;
        gbcBtnNewButton.insets = new Insets(0, 0, 5, 5);
        gbcBtnNewButton.gridx = 3;
        gbcBtnNewButton.gridy = 4;
        gridC.add(btnNewButton, gbcBtnNewButton);

        start.setVisible(true);

    }

    /**
     * main method.
     * 
     * @param args
     *            -main args
     */
    public static void main(final String[] args) {

        new Go();

    }

    /**
     * @return the numPlayers
     */
    public static int getNumPlayers() {
        return numPlayer;
    }

    /**
     * @param numPlayers
     *            the numPlayers to set
     */
    public static void addNumPlayers(final int numPlayers) {
        numPlayer += numPlayers;
    }
}
