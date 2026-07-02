package view.menu;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.EnvironmentControllerImpl;
import view.menu.fair.FairGui;
import view.menu.profit.ProfitGui;
import view.model.activity.ActivityType;

public class ActivityInsertionPanelBox extends JPanel {

    private static final long serialVersionUID = 2187419885864432704L;
    private final JPanel activityInsertionPanel = new JPanel();
    private final GridBagConstraints cnst = new GridBagConstraints();
    private final JButton fair = new JButton("Add a FAIR");
    private final JButton restaurant = new JButton("Add a RESTAURANT");
    private final JButton shop = new JButton("Add a SHOP");
    private final ActivityPanel gui;

    public ActivityInsertionPanelBox(final EnvironmentControllerImpl view, final ActivityPanel gui) {
        this.gui = gui;
        this.setLayout(new FlowLayout());
        activityInsertionPanel.setLayout(new GridBagLayout());
        cnst.gridy = 0;
        cnst.insets = new Insets(3, 3, 3, 3);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        activityInsertionPanel.add(this.fair, cnst);
        cnst.gridy++;
        activityInsertionPanel.add(this.restaurant, cnst);
        cnst.gridy++;
        activityInsertionPanel.add(this.shop, cnst);
        cnst.gridy++;
        this.setBorder(new TitledBorder("Add a new activity"));
        this.add(activityInsertionPanel);

        this.fair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final FairGui fairWindow = new FairGui(view, ActivityInsertionPanelBox.this);
                fairWindow.display();

            }
        });

        this.restaurant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ProfitGui profitWindow = new ProfitGui(view, ActivityInsertionPanelBox.this, ActivityType.REST);
                profitWindow.display();
            }
        });

        this.shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ProfitGui shopWindow = new ProfitGui(view, ActivityInsertionPanelBox.this, ActivityType.SHOP);
                shopWindow.display();
            }
        });
    }

    /**
     * @return the menu {@link GraphicalUserInterface}
     */
    public ActivityPanel getGui() {
        return this.gui;
    }

    /**
     * It enables/disables Start and Default setting buttons.
     * @param flag needed to decide whether enable or disable action is needed
     */
    public void enableDisableButtons(final boolean flag) {
        this.fair.setEnabled(flag);
        this.restaurant.setEnabled(flag);
        this.shop.setEnabled(flag);
    }

}
