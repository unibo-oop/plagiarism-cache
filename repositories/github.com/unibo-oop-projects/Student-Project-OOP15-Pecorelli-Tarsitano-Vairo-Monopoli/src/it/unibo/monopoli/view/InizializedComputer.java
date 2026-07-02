package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * 
 * Class that create a panel for information about Computer Player in Frame Go.
 *
 */
public class InizializedComputer {
    private final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    private static boolean isUman;
    private String positionAndName;

    /**
     * Builder.
     * 
     * @param name
     *            computer's name
     * @param isUman
     *            player type
     */
    public InizializedComputer(final String name, final boolean isUman) {
        this.isUman = isUman;
    }

    /**
     * Builder.
     */
    public InizializedComputer() {

    }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation.
     * 
     * @param playerP
     *            Player
     * @return -return a Player's panel
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

        final JTextField textNome = new JTextField();
        textNome.setPreferredSize(new Dimension(0, 30));
        textNome.setHorizontalAlignment(SwingConstants.CENTER);
        centerPRow1.add(textNome);
        textNome.setColumns(10);
        textNome.setText("Computer");
        centerP.add(textNome, BorderLayout.NORTH);
        textNome.setEditable(false);

        final JPanel centerPRow2 = new JPanel();
        centerP.add(centerPRow2, BorderLayout.CENTER);

        final JRadioButton rdbtnUman = new JRadioButton("Umano");
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        rdbtnUman.setEnabled(false);
        centerPRow2.add(rdbtnUman);

        final JRadioButton rdbtnComputer = new JRadioButton("Computer");
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        centerPRow2.add(rdbtnComputer);
        rdbtnComputer.setSelected(true);
        rdbtnComputer.setEnabled(false);

        final ButtonGroup group = new ButtonGroup();
        group.add(rdbtnComputer);
        group.add(rdbtnUman);

        final JButton remove = new JButton("Remove");
        remove.setFont(new Font("Times New Roman", Font.BOLD, 10));
        remove.setPreferredSize(new Dimension(70, 22));
        southP.add(remove);

        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                positionAndName = (textNome.getText());
                InizializedPlayer.getMap().remove(positionAndName);
                panel.setVisible(false);
                playerP.remove(panel);
                Go.addNumPlayers(-1);
                playerP.revalidate();
                System.out.println("Name RemoveC: " + positionAndName);
            }

        });

        if (rdbtnComputer.isSelected()) {
            isUman = false;
        } else {
            isUman = true;
        }

        InizializedPlayer.getMap().put(textNome.getText(), isUman);
        InizializedPlayer.setSave(true);
        panel.setVisible(true);
        return panel;

    }

    /**
     * 
     * returns if the player is the computer or human.
     * 
     * @return boolean
     */
    public static boolean isUman() {
        return isUman;
    }

}
