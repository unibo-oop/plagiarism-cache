package talisman.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;

import talisman.controller.battle.BattleController;
import talisman.view.battle.BattleBottomView;
import talisman.view.battle.BattleCenterView;
import talisman.view.battle.BattleTopView;
import talisman.view.battle.BattleViewFactory;

/**
 * A window showing the battle.
 * 
 * @author Alice Girolomini
 *
 */
public class BattleWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    /**
     * Creates a new battle window.
     * 
     * @param controller - the controller of the battle
     */
    public BattleWindow(final BattleController controller) {
        final LayoutManager layout = new GridBagLayout();
        final GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        BattleTopView topView = BattleViewFactory.createTopView(controller);
        BattleBottomView bottomView = BattleViewFactory.createBottomView(controller);
        BattleCenterView centerView = BattleViewFactory.createCenterView(controller, topView, bottomView);
        this.getContentPane().add((JPanel) topView, constraint);
        constraint.gridy = 1;
        this.getContentPane().add((JPanel) centerView, constraint);
        constraint.gridy = 2;
        this.getContentPane().add((JPanel) bottomView, constraint);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

}
