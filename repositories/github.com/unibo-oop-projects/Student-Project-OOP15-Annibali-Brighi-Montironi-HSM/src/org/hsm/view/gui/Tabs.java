package org.hsm.view.gui;

import java.awt.BorderLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import org.hsm.view.tab.DatabaseTab;
import org.hsm.view.tab.GreenhouseChartTab;
import org.hsm.view.tab.GreenhouseTab;
import org.hsm.view.tab.PlantsTab;
import org.hsm.view.tab.Table;
import org.hsm.view.tab.UpgradeableTable;

/**
 * The class which create and use the Tabbes.
 *
 */
public class Tabs implements GUIComponent {

    private final JPanel panel;
    private final JTabbedPane tab;
    private final Table<String> databaseTab;
    private final UpgradeableTable<Integer> plantsTab;
    private final GreenhouseTab greenhouseTab;
    private final Resettable greenhouseChartTab;

    /**
     * Create the Tabbes.
     * 
     * @param frame
     *            the main frame of the app
     */
    public Tabs(final JFrame frame) {
        this.tab = new JTabbedPane();
        this.panel = new JPanel(new BorderLayout());
        this.panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // tabs
        this.greenhouseTab = new GreenhouseTab();
        this.plantsTab = new PlantsTab(frame);
        this.databaseTab = new DatabaseTab(frame);
        this.greenhouseChartTab = new GreenhouseChartTab();
        this.tab.add("Greenhouse", this.greenhouseTab.getComponent());
        this.tab.add("Greenhouse Composition", this.greenhouseChartTab.getComponent());
        this.tab.add("Plants", this.plantsTab.getComponent());
        this.tab.add("Plants Database", this.databaseTab.getComponent());
        // observer
        ((Observable) this.plantsTab).addObserver(this.greenhouseTab);
        this.panel.add(tab, BorderLayout.CENTER);
    }

    /**
     * Set the tabbed visible and usable.
     * 
     * @param state
     *            the visibility state.
     */
    public void setVisible(final boolean state) {
        this.tab.setVisible(state);
    }

    /**
     * Get the Database tab.
     * 
     * @return the database tab
     */
    protected Table<String> getDatabaseTab() {
        return this.databaseTab;
    }

    /**
     * Get the Plants tab.
     * 
     * @return the plants tab
     */
    protected UpgradeableTable<Integer> getPlantsTab() {
        return this.plantsTab;
    }

    /**
     * Get the greenhouse tab.
     * 
     * @return the greenhouse tab
     */
    protected GreenhouseTab getGreenhouseTab() {
        return this.greenhouseTab;
    }

    /**
     * Get the greenhouse chart tab.
     * 
     * @return the greenhouse chart tab
     */
    protected Resettable getGreenhouseChartTab() {
        return this.greenhouseChartTab;
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }
}
