package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * 
 * Class that create a panel for information about Player in Frame Go.
 *
 */
public class InizializedPlayer {
    private final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

    private static Map<String, Boolean> map = new HashMap<>();
    private JTextField textNome;
    private boolean isUman;
    private JRadioButton rdbtnComputer;
    private JRadioButton rdbtnUman;
    private String positionAndName;

    private static boolean save;

    /**
     * 
     */
    public InizializedPlayer() {
    }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation.
     * 
     * @param playerP
     *            Player
     * @return JPanel playerP
     */
    public JPanel build(final JPanel playerP) {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(C.DIM_PLAYER));

        panel.setBorder(border);
        panel.setLayout(new BorderLayout(0, 0));

        final JPanel northP = new JPanel();
        panel.add(northP, BorderLayout.NORTH);

        final JPanel westP = new JPanel();
        panel.add(westP, BorderLayout.WEST);

        final JPanel southP = new JPanel();
        panel.add(southP, BorderLayout.SOUTH);

        final JButton save = new JButton("Save");
        save.setFont(new Font("Times New Roman", Font.BOLD, 10));
        save.setPreferredSize(new Dimension(70, 22));
        southP.add(save);

        final JButton remove = new JButton("Remove");
        remove.setFont(new Font("Times New Roman", Font.BOLD, 10));
        remove.setPreferredSize(new Dimension(70, 22));
        southP.add(remove);
        remove.setVisible(false);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (textNome.getText().isEmpty()) {
                    new Dialog(new JFrame(), "Error", "Error! You must enter the name of the player");
                } else {
                    if (rdbtnUman.isSelected()) {
                        isUman = true;
                    } else {
                        isUman = false;
                    }
                    // alla mappa passo N?NOMEGIOCATORE, MI E' UTILE PER
                    // DECIDERE IL COLORE DELLE PEDINE
                    positionAndName = (textNome.getText());
                    map.put(positionAndName, isUman);
                    InizializedPlayer.setSave(true);
                    save.setVisible(false);
                    remove.setVisible(true);
                    textNome.setEditable(false);
                    rdbtnComputer.setEnabled(false);
                    rdbtnUman.setEnabled(false);
                    System.out.println("Name Save: " + positionAndName);

                }

            }
        });

        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                positionAndName = textNome.getText();
                InizializedPlayer.getMap().remove(positionAndName);
                panel.setVisible(false);
                playerP.remove(panel);
                Go.addNumPlayers(-1);
                playerP.revalidate();
                System.out.println("Name RemoveP: " + positionAndName);
            }

        });

        final JPanel eastP = new JPanel();
        panel.add(eastP, BorderLayout.EAST);

        final JPanel centerP = new JPanel();
        panel.add(centerP, BorderLayout.CENTER);
        centerP.setLayout(new BorderLayout());

        final JPanel centerPRow1 = new JPanel();
        centerP.add(centerPRow1, BorderLayout.NORTH);

        final JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        northP.add(lblNome);

        textNome = new JTextField();
        textNome.setPreferredSize(new Dimension(0, 30));
        textNome.setHorizontalAlignment(SwingConstants.CENTER);
        centerP.add(textNome, BorderLayout.NORTH);
        textNome.setColumns(10);

        final JPanel centerPRow2 = new JPanel();

        rdbtnUman = new JRadioButton("Umano");
        rdbtnUman.setSelected(true);
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        centerPRow2.add(rdbtnUman);

        rdbtnComputer = new JRadioButton("Computer");
        centerPRow2.add(rdbtnComputer);
        centerP.add(centerPRow2, BorderLayout.CENTER);
        final ButtonGroup group = new ButtonGroup();
        group.add(rdbtnComputer);
        group.add(rdbtnUman);
        rdbtnUman.setSelected(true);

        panel.setVisible(true);
        return panel;

    }

    /**
     * Method says if the save button is pressed.
     * 
     * @param x
     *            isSave
     * 
     */
    protected static void setSave(final boolean x) {
        save = x;

    }

    /**
     * returns the status of the panel of the player. Returns true if it is
     * saved, false otherwise.
     * 
     * @return Boolean
     */
    public static Boolean isSave() {
        return save;

    }

    /**
     * returns the HashMap representing the players, their type and their pawn.
     * 
     * @return HashMap<>
     */
    public static Map<String, Boolean> getMap() {
        return map;
    }

    /**
     * return state o Computer RadioButton.
     * 
     * @return JRadioButton
     */
    public JRadioButton getRdbtnComputer() {
        return rdbtnComputer;
    }

}
