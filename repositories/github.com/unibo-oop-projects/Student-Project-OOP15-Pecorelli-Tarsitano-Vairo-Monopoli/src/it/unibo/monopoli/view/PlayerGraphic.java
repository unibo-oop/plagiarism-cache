package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * 
 * 
 * class that initializes the panel of players.
 *
 */
public class PlayerGraphic extends JPanel {

    private String name;
    private int value;
    private Controller controller;
    private List<Player> list;
    private JLabel lblColc;
    private JLabel lblColBank;
    private JLabel l;
    private JPanel row2;
    private HashMap<Integer, JLabel> labels;
    private int pos;

    /**
     * Builder.
     * 
     * @param name
     *            player name
     * @param value
     *            player value
     * @param controller
     *            controller
     */
    public PlayerGraphic(final String name, final int value, final Controller controller) {
        this.name = name;
        this.value = value;
        this.controller = controller;
        labels = new HashMap<>();

    }

    /**
     * builder.
     * 
     * @param controller
     *            controller
     */
    public PlayerGraphic(final Controller controller) {
        this(null, 0, controller);
    }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation.
     * 
     * @param boxes
     *            List of box
     * 
     * @param list
     *            list Player
     * 
     * @param i
     *            id
     * 
     * @return -return a Player's panel
     */
    public JPanel build(final List<Box> boxes, final List<Player> list, final int i) {
        this.list = list;
        this.pos = i;

        this.name = list.get(i).getName();
        this.value = list.get(i).getMoney();

        final JPanel player = new JPanel();
        player.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0 };
        gridBagLayout.rowHeights = new int[] { 47, 120, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        player.setPreferredSize(new Dimension(350, 200));
        player.setLayout(gridBagLayout);

        final JPanel row1 = new JPanel();
        row1.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow1 = new GridBagConstraints();
        gbcRow1.insets = new Insets(0, 0, 5, 0);
        gbcRow1.fill = GridBagConstraints.BOTH;
        gbcRow1.gridx = 0;
        gbcRow1.gridy = 0;
        player.add(row1, gbcRow1);
        row1.setLayout(new GridLayout(1, 3, 0, 0));

        final JLabel lblCol = new JLabel(this.name);
        lblCol.setFont(new Font("Papyrus", Font.BOLD, 18));
        row1.add(lblCol);

        lblColBank = new JLabel("" + this.value);
        lblColBank.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
        row1.add(lblColBank);

        lblColc = new JLabel();
        lblColc.setOpaque(true);
        lblColc.setBackground(C.CL.get(list.get(i).getPawn().getID()));
        System.out.println("Name: " + list.get(i).getName());
        System.out.println("ID: " + list.get(i).getPawn().getID());
        lblColc.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
        row1.add(lblColc);

        row2 = new JPanel();
        row2.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow2 = new GridBagConstraints();
        gbcRow2.insets = new Insets(0, 0, 5, 0);
        gbcRow2.fill = GridBagConstraints.BOTH;
        gbcRow2.gridx = 0;
        gbcRow2.gridy = 1;
        player.add(row2, gbcRow2);
        final GridBagLayout gblRow2 = new GridBagLayout();
        gblRow2.columnWidths = new int[] { 0, 25, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblRow2.rowHeights = new int[] { 16, 22, 0, 10, 9, 0, 0 };
        gblRow2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gblRow2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        row2.setLayout(gblRow2);

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            int id = o.getID();
            l = new JLabel("" + (id < 10 ? "0" : "") + id);

            labels.put(id, l);
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            l.setPreferredSize(new Dimension(20, 20));
            l.setOpaque(true);
            final GridBagConstraints gbcLabels = new GridBagConstraints();
            gbcLabels.insets = new Insets(0, 0, 5, 5);
            gbcLabels.gridx = id % 15; // 15 caselle per riga
            gbcLabels.gridy = id / 15; // una riga ogni 15 caselle
            row2.add(l, gbcLabels);
        });

        final JPanel row3 = new JPanel();
        final FlowLayout flowLayout = (FlowLayout) row3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setAlignOnBaseline(true);
        row3.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow3 = new GridBagConstraints();
        gbcRow3.fill = GridBagConstraints.BOTH;
        gbcRow3.gridx = 0;
        gbcRow3.gridy = 2;
        player.add(row3, gbcRow3);

        setLAbelContract();

        player.setVisible(true);
        return player;

    }

    /**
     * Set pos looser.
     * 
     * @param pos
     *            int
     */
    public void setPosDeath(final int pos) {
        if (this.pos > pos) {
            this.pos--;
        }
    }

    /**
     * Update Player information.
     */
    public void setLAbelContract() {
        Player p = list.get(pos);

        lblColBank.setText(p.getMoney() + "");

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            Ownership own = (Ownership) o;

            l = labels.get(o.getID());
            if (own.getOwner().equals(p)) {
                l.setOpaque(true);
                l.setVisible(true);

                if (o instanceof Land) {
                    l.setBackground(((Land) o).getColor());
                    if (((Ownership) o).isMortgaged()) {
                        l.setEnabled(false);
                    } else {
                        l.setEnabled(true);
                    }
                } else {
                    l.setBackground(Color.WHITE);
                }
            } else {
                l.setOpaque(false);
                l.setVisible(false);
            }
        });
    }

    /**
     * Create a bank panel.
     * 
     * @param allBoxes
     *            list box
     * @param bank
     *            bank
     * @return Component
     * 
     */
    public Component buildBank(final List<Box> allBoxes, final Bank bank) {
        this.name = "Bank";

        final JPanel player = new JPanel();
        player.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0 };
        gridBagLayout.rowHeights = new int[] { 47, 120, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        player.setPreferredSize(new Dimension(350, 200));
        player.setLayout(gridBagLayout);

        final JPanel row1 = new JPanel();
        row1.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gcbRow1 = new GridBagConstraints();
        gcbRow1.insets = new Insets(0, 0, 5, 0);
        gcbRow1.fill = GridBagConstraints.BOTH;
        gcbRow1.gridx = 0;
        gcbRow1.gridy = 0;
        player.add(row1, gcbRow1);
        row1.setLayout(new GridLayout(1, 2, 0, 0));

        final JLabel lblCol = new JLabel(this.name);
        lblCol.setFont(new Font("Papyrus", Font.BOLD, 18));
        row1.add(lblCol);

        row2 = new JPanel();
        row2.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow2 = new GridBagConstraints();
        gbcRow2.insets = new Insets(0, 0, 5, 0);
        gbcRow2.fill = GridBagConstraints.BOTH;
        gbcRow2.gridx = 0;
        gbcRow2.gridy = 1;
        player.add(row2, gbcRow2);

        final GridBagLayout gblRow2 = new GridBagLayout();
        gblRow2.columnWidths = new int[] { 0, 25, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblRow2.rowHeights = new int[] { 16, 22, 0, 10, 9, 0, 0 };
        gblRow2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gblRow2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        row2.setLayout(gblRow2);

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            int id = ((Box) o).getID();
            final JLabel l = new JLabel("" + (id < 10 ? "0" : "") + id);

            labels.put(id, l);
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            l.setPreferredSize(new Dimension(20, 20));
            l.setOpaque(true);
            final GridBagConstraints gbcLabels = new GridBagConstraints();
            gbcLabels.insets = new Insets(0, 0, 5, 5);
            gbcLabels.gridx = id % 15; // 15 caselle per riga
            gbcLabels.gridy = id / 15; // una riga ogni 15 caselle
            row2.add(l, gbcLabels);
        });

        final JPanel row3 = new JPanel();
        final FlowLayout flowLayout = (FlowLayout) row3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setAlignOnBaseline(true);
        row3.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow3 = new GridBagConstraints();
        gbcRow3.fill = GridBagConstraints.BOTH;
        gbcRow3.gridx = 0;
        gbcRow3.gridy = 2;
        player.add(row3, gbcRow3);

        setLabelBank();

        player.setVisible(true);
        return player;

    }

    /**
     * Update bank info.
     */
    public void setLabelBank() {
        Bank p = controller.getBank();

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            Ownership own = (Ownership) o;
            l = labels.get(o.getID());
            if (own.getOwner().equals(p)) {
                l.setOpaque(true);
                l.setVisible(true);
                if (o instanceof Land) {
                    l.setBackground(((Land) o).getColor());
                } else {
                    l.setBackground(Color.WHITE);
                }
            } else {
                l.setOpaque(false);
                l.setVisible(false);
            }
        });

    }

}
