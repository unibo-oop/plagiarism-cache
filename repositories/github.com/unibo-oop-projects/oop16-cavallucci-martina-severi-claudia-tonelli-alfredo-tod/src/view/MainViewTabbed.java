package view;

import javax.swing.JPanel;

/**
 * 
 * Interface of the class that permits to switch all the tabs.
 *
 */
public interface MainViewTabbed extends View {
    /**
     * Permits to manually switch the tabs.
     * 
     * @param view
     *            the tab to switch to
     */
    void goToTab(View view);
    /**
     * Permits to add new tab.
     * 
     * @param tab
     *            the tab to be added
     * @param name
     *            name of the tab
     */
    void addTab(JPanel tab, String name);
    /**
     * Permits to replace a tab.
     * 
     * @param newTab
     *            new tab
     * @param oldTab
     *            old tab
     */
    void replaceTab(View newTab, View oldTab);

}
