package view;

import javax.swing.Icon;
import javax.swing.JPanel;

/**
 * MainTabbedPane interface.
 *
 */
public interface MainTabbedPane {
    /**
     * Used to add tab.
     * 
     * @param tab
     *             the tab to be added to the JTabbedPane
     * @param title
     *             title of the tab
     * @param icon
     *             icon of the tab
     */
    void addTab(JPanel tab, String title, Icon icon);
    /**
     * Used to go to the selected tab.
     * 
     * @param selectedTab
     *          goes to selected tab
     */
    void goToTab(View selectedTab);
    /**
     * Used to replace the old tab with a new one.
     * 
     * @param nextTab
     *                  the tab to be displayed
     * @param previousTab
     *                  the tab that was previously displayed
     */
    void replaceTab(View nextTab, View previousTab);
}
