package talisman.view.battle;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import talisman.controller.battle.BattleController;
import talisman.util.Pair;
import talisman.view.ImagePanel;

/**
 * Swing implementation of the top view of the battle.
 * 
 * @author Alice Girolomini
 */
public class BattleTopViewImpl extends JPanel implements BattleTopView {
    private static final long serialVersionUID = 1L;
    private static final int INSETSVALUE = 5;
    private static final int XCOORDINATEIMAGE = 4;
    private static final int XCOORDINATELABEL = 5;
    private final JLabel firstCharDamage;
    private final JLabel secondCharDamage;
    private final BattleController controller;

    /**
     * Initializes the top view of the battle.
     * 
     * @param controller - the controller of the battle
     */
    public BattleTopViewImpl(final BattleController controller) {
        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);
        this.controller = controller;
        Pair<Integer, Integer> values = this.controller.initializeScores();
        this.firstCharDamage = new JLabel(String.valueOf(values.getX()));
        this.firstCharDamage.setForeground(Color.BLACK);
        this.secondCharDamage = new JLabel(String.valueOf(values.getY()));
        this.secondCharDamage.setForeground(Color.BLACK);
        this.add(this.firstCharDamage, this.setConstraints(2, 2, 1));
        this.add(this.secondCharDamage, this.setConstraints(XCOORDINATELABEL, 2, 1));
        List<JLabel> labels = new ArrayList<>();
        labels.add(new JLabel("Attack score 1 :"));
        labels.add(new JLabel("Attack score 2 :"));
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setForeground(Color.BLACK);
        }
        this.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/banner.png"))), this.setConstraints(3, 0, 1));
        this.add(labels.get(0), this.setConstraints(0, 1, 1));
        this.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/sword.png"))), this.setConstraints(0, 2, 1));
        this.add(labels.get(1), this.setConstraints(XCOORDINATEIMAGE, 1, 1));
        this.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource("imgs/battle/sword.png"))), this.setConstraints(XCOORDINATEIMAGE, 2, 1));
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
        c.insets = new Insets(INSETSVALUE, INSETSVALUE, INSETSVALUE, INSETSVALUE);
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAttackScore(final int character) {
        if (character == 1) {
            return Integer.parseInt(this.firstCharDamage.getText());
        }
        return Integer.parseInt(this.secondCharDamage.getText());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAttackScore(final int character, final int value) {
        if (character == 1) {
            this.firstCharDamage.setText(String.valueOf(value));
        } else {
            this.secondCharDamage.setText(String.valueOf(value));
        }
    }
}
