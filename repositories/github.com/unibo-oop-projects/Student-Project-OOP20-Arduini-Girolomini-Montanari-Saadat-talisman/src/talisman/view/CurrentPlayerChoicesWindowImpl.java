package talisman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import talisman.Controllers;
import talisman.controller.character.CurrentPlayerChoicesController;
import talisman.view.cards.TalismanDeckView;

/**
 * Swing implementation of the view used to display the player's choices.
 * 
 * @author Alice Girolomini
 *
 */
public class CurrentPlayerChoicesWindowImpl extends JFrame implements CurrentPlayerChoicesWindow {
    private static final long serialVersionUID = 1L;
    private static final int LASTXCOORDINATE = 6;
    private static final int INSETSVALUE = 5;
    private static final int HEIGHT = 400;
    private static final int WIDTH = 550;
    private final JButton diceButton;
    private final JButton passTurnButton;
    private final JButton moveButton;
    private final JButton cellEventButton;
    private final JButton attackButton;
    private final JLabel rollResult;
    private final JLabel infos;
    private final CurrentPlayerChoicesController controller;

    /**
     * Creates the view used to display the player's choices.
     *
     * @param controller - the controller of the player's choices
     */
    public CurrentPlayerChoicesWindowImpl(final CurrentPlayerChoicesController controller) {
        this.controller = controller;
        final BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.diceButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/diceButton.png")));
        this.passTurnButton = new JButton("Pass");
        this.moveButton = new JButton("Move");
        this.cellEventButton = new JButton("Event");
        this.attackButton = new JButton("Challenge");
        this.rollResult = new JLabel("0");
        this.infos = new JLabel("Roll the dice");
        this.diceButton.setEnabled(!this.controller.checkRoll());
        this.moveButton.setEnabled(false);
        this.attackButton.setEnabled(false);
        this.cellEventButton.setEnabled(false);
        this.passTurnButton.setEnabled(false);
        this.getContentPane().add(createInfoAndDicePanel(), BorderLayout.NORTH);
        this.getContentPane().add(createChoicesPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createInventoryPanel(), BorderLayout.SOUTH);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInteractible(final boolean enabled) {
        this.getContentPane().setEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeWindow() {
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Creates a panel with informations for the character and the dice.
     */
    private JPanel createInfoAndDicePanel() {
        JPanel panel = new JPanel();
        LayoutManager layout = new GridBagLayout();
        panel.setLayout(layout);
        int index = controller.getCurrentPlayerIndex() + 1;
        JLabel title = new JLabel("It's your turn player " + index);
        title.setForeground(Color.BLACK);
        this.infos.setForeground(Color.BLACK);
        this.rollResult.setForeground(Color.BLACK);
        panel.add(title, this.setConstraints(3, 0, 1));
        panel.add(this.diceButton, this.setConstraints(0, 1, 1));
        this.diceButton.addActionListener(e -> {
            rollResult.setText(String.valueOf(controller.getDiceRoll()));
            infos.setText("Move your pawn");
            diceButton.setEnabled(!controller.checkRoll());
            moveButton.setEnabled(true);
        });
        panel.add(this.rollResult, this.setConstraints(1, 1, 1));
        panel.add(this.infos, this.setConstraints(LASTXCOORDINATE, 1, 1));
        panel.setBackground(Color.darkGray);
        return panel;
    }

    /**
     * Creates a panel with informations for the character and the dice.
     */
    private JPanel createChoicesPanel() {
        JPanel panel = new JPanel();
        LayoutManager layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.add(this.moveButton, this.setConstraints(0, 0, 1));
        this.moveButton.addActionListener(e -> {
            controller.movePawn();
            infos.setText("Choose action");
            moveButton.setEnabled(false);
            attackButton.setEnabled(controller.checkOpponents());
            cellEventButton.setEnabled(true);
        });
        panel.add(this.cellEventButton, this.setConstraints(0, 1, 1));
        this.cellEventButton.addActionListener(e -> {
            controller.cellEvent();
            attackButton.setEnabled(false);
            cellEventButton.setEnabled(false);
            passTurnButton.setEnabled(true);
        });
        panel.add(this.attackButton, this.setConstraints(0, 2, 1));
        this.attackButton.addActionListener(e -> {
            controller.challengeCharacter();
            attackButton.setEnabled(false);
            cellEventButton.setEnabled(false);
            passTurnButton.setEnabled(true);
        });
        panel.add(this.passTurnButton, this.setConstraints(LASTXCOORDINATE, 1, 1));
        this.passTurnButton.addActionListener(e -> {
            if (controller.checkRoll()) {
                controller.passTurn();
            }
        });
        panel.setBackground(Color.darkGray);
        return panel;
    }

    private JPanel createInventoryPanel() {
        return (JPanel) TalismanDeckView.create(controller.getCurrentCharacterCards());

    }

    /**
     * Sets new constraints for the specified component.
     * 
     * @param x     - the x coordinate of the component
     * @param y     - the y coordinate of the component
     * @param width - the width of the component
     * @return the bottom view
     */
    private GridBagConstraints setConstraints(final int x, final int y, final int width) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, INSETSVALUE, INSETSVALUE, INSETSVALUE);
        return c;
    }
}
