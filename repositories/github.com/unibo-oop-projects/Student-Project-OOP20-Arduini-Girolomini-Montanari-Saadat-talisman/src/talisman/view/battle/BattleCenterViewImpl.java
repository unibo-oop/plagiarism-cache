package talisman.view.battle;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import talisman.controller.battle.BattleController;
import talisman.model.battle.BattleState;

/**
 * Swing implementation of the center view of the battle.
 * 
 * @author Alice Girolomini
 */
public class BattleCenterViewImpl extends JPanel implements BattleCenterView {
    private static final long serialVersionUID = 1L;
    private static final int INSETSVALUE = 5;
    private static final int YCOORDINATEBUTTON = 5;
    private final JButton attackButton;
    private final JButton fateButton;
    private final BattleController controller;

    /**
     * Initializes the center view of the battle.
     * 
     * @param controller - the controller of the battle
     * @param topView - the top view of the battle
     * @param bottomView - the bottom view of the battle
     */
    public BattleCenterViewImpl(final BattleController controller, final BattleTopView topView, final BattleBottomView bottomView) {
        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);
        this.controller = controller;
        this.attackButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/attackButton.png")));
        this.fateButton = new JButton(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/fateButton.png")));
        this.add(attackButton, this.setConstraints(1, 2, 1));
        this.attackButton.addActionListener(e -> {
            if (!controller.canRoll()) {
                topView.setAttackScore(controller.getTurn(), controller.requestedAttack());
            }
            if (controller.getTurn() == 2) {
                endBattle();
            }
            fateButton.setEnabled(controller.requestedFate()); 
        });
        List<JLabel> labels = new ArrayList<>();
        labels.add(new JLabel("Attack"));
        labels.add(new JLabel("Fate"));
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setForeground(Color.BLACK);
        }
        this.add(labels.get(0), this.setConstraints(1, 1, 1));
        this.add(fateButton, this.setConstraints(1, YCOORDINATEBUTTON, 1));
        fateButton.setEnabled(this.controller.requestedFate());
        this.fateButton.addActionListener(e -> {
            if (!controller.canRoll()) {
                controller.updateFate();
                bottomView.setAttackRoll(controller.getTurn(), 0);
                fateButton.setEnabled(controller.requestedFate());
            } 
        });
        this.add(labels.get(1), this.setConstraints(1, 4, 1));
        this.setBackground(Color.darkGray);
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
     * Shows a JOptionPane with the message associated to the outcome of the battle and closes the battle window.
     */
    private void endBattle() {
        Window win = SwingUtilities.getWindowAncestor(this.attackButton);
        BattleState status = this.controller.getResult();
        if (status.equals(BattleState.FIRST)) {
            JOptionPane.showMessageDialog(null, "First opponent wins!");
            win.dispose();
        } else if (status.equals(BattleState.SECOND)) {
            JOptionPane.showMessageDialog(null, "Second opponent wins!");
            win.dispose();
        } else if (status.equals(BattleState.STAND_OFF)) {
            JOptionPane.showMessageDialog(null, "It's a standoff!");
            win.dispose();
        }
    }
}
