package view;

import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * 
 *main tabbed frame.
 */
public class MainTabbedPaneImpl extends JFrame implements MainTabbedPane, View {
    /**
     * 
     */
    private static final long serialVersionUID = 5997570723709134592L;
    private final JTabbedPane tabbedPane; //main tabbed pane
    private final List<JPanel> tabs; //list of the tabs of the tabbed pane, each one can be accessed by pushing associated button
    private final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final double PROPORTION = 1.5;

    /**
     * 
     */
    public MainTabbedPaneImpl() {

        this.tabs = new ArrayList<>(); //new array of tabs
        this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT); //creates the tabbed pane
        tabbedPane.setBackground(Colors.getFreeStatusColor());
        this.getContentPane().add(tabbedPane); //adds content to the JTabbedPane
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Blooming Florist");
        this.setSize((int) (screenWidth / PROPORTION), (int) (screenHeight / PROPORTION));
        //set icon
        final URL iconURL = getClass().getResource("/the_blooming_florist.png");
        ImageIcon genericIcn = new ImageIcon(iconURL);
        this.setIconImage(genericIcn.getImage());
        this.setContentPane(tabbedPane);
        this.setBackground(Colors.getDarkGreen());
        this.setLocationByPlatform(true);
    }


    //method that adds the tab to the tabbed pane
    @Override
    public void addTab(final JPanel tab, final String title, final Icon icon) {
        this.tabs.add(tab); //adds tab to the array of tabs
        this.tabbedPane.addTab(title, icon, tab); //adds tabs to the tabbed pane
        this.validate();
    }


    @Override
    public void replaceTab(final View nextTab, final View previousTab) {
        final int index = tabbedPane.indexOfComponent((Component) previousTab);
        final Component prevoiusTabContent = this.tabbedPane.getComponentAt(index);
        this.tabs.set(index, (JPanel) nextTab);
        this.tabbedPane.remove((Component) previousTab);
        this.tabbedPane.add((Component) nextTab, index);
        this.tabbedPane.setTabComponentAt(index, prevoiusTabContent);
        this.validate();
    }


    @Override
    public void goToTab(final View selectedTab) {
        this.tabbedPane.setSelectedComponent((Component) selectedTab);
    }


    @Override
    public void init() {
        this.setVisible(true);
    }

}