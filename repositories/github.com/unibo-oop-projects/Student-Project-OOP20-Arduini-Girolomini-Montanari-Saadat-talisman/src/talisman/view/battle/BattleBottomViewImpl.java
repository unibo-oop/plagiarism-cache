package talisman.view.battle;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import talisman.controller.battle.BattleController;

/**
 * Swing implementation of the bottom view of the battle.
 * 
 * @author Alice Girolomini
 */
public class BattleBottomViewImpl extends JPanel implements BattleBottomView {
    private static final long serialVersionUID = 1L;
    private static final int INSETSVALUE = 5;
    private final JButton diceButton;
    private final JLabel firstRoll;
    private final JLabel secondRoll;

    /**
     * Initializes the bottom view of the battle.
     * 
     * @param controller - the controller of the battle
     */
    public BattleBottomViewImpl(final BattleController controller) {
        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);
        List<JLabel> labels = new ArrayList<>();
        this.firstRoll = new JLabel("0");
        this.firstRoll.setForeground(Color.BLACK);
        this.secondRoll = new JLabel("0");
        this.secondRoll.setForeground(Color.BLACK);
        this.diceButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/diceButton.png")));
        labels.add(new JLabel("Dice"));
        labels.add(new JLabel("Roll dice 1:"));
        labels.add(new JLabel("Roll dice 2:"));
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setForeground(Color.BLACK);
        }
        this.add(labels.get(0), this.setConstraints(1, 0, 1));
        this.add(diceButton, this.setConstraints(1, 1, 1));
        this.add(firstRoll, this.setConstraints(2, 1, 1));
        this.add(labels.get(1), this.setConstraints(2, 0, 1));
        this.add(secondRoll, this.setConstraints(3, 1, 1));
        this.add(labels.get(2), this.setConstraints(3, 0, 1));
        this.setBackground(Color.darkGray);
        this.diceButton.addActionListener(e -> {
            if (getAttackRoll(controller.getTurn()) == 0 && controller.canRoll()) {
                setAttackRoll(controller.getTurn(), controller.updateRoll());
            }
        });
    }

    /**
     * Sets new constraints for the specified component.
     * 
     * @param x - the x coordinate of the component
     * @param y - the y coordinate of the component
     * @param width - the width of the component
     *@return the bottom view
     */
    private GridBagConstraints setConstraints(final int x, final int y, final int width) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(INSETSVALUE, INSETSVALUE, INSETSVALUE, INSETSVALUE);
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackRoll(final int character) {
        if (character == 1) {
            return Integer.parseInt(this.firstRoll.getText());
        }
        return Integer.parseInt(this.secondRoll.getText());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttackRoll(final int character, final int value) {
        if (character == 1) {
            this.firstRoll.setText(String.valueOf(value));
        } else {
            this.secondRoll.setText(String.valueOf(value));
        }
    }
}
