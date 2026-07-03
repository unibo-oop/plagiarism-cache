package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Frame that contains a JTabbedPane,
 * which in turn contains a determinated
 * JPanel pertinent to his section. Implemented
 * with singleton pattern to make unique this class,
 * considering that, this is the main JFrame.
 *
 */
public class MainViewTabbedImpl extends JFrame implements View, MainViewTabbed {
    private final JTabbedPane tabbedPane;
    private final List<JPanel> tabs;
    private static final long serialVersionUID = 6638824393771027540L;

    /**
     * the class constructor.
     */
    public MainViewTabbedImpl() {
        this.tabs = new ArrayList<>();
        this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        this.getContentPane().add(this.tabbedPane);
        this.setResizable(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle("TOD-Today, To date, To do");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {

               final Object[] options = {"Yes", "No"};

               final int close = JOptionPane.showOptionDialog(e.getComponent(),
                        "Really want to close this application?\n"
                        + "If you click -Yes- your change will not be saved!\n"
                        + "Click logout button for save\n", "Attention",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        null);

                if (close == JOptionPane.YES_OPTION) {
                   ((JFrame) e.getSource()).setDefaultCloseOperation(
                           JFrame.EXIT_ON_CLOSE);
                } else {
                   ((JFrame) e.getSource()).setDefaultCloseOperation(
                           JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
            }
        );
    }

    @Override
    public void addTab(final JPanel tab, final String name) {
        this.tabs.add(tab);
        this.tabbedPane.addTab(name, tab);
        this.tabbedPane.setTabComponentAt(this.tabbedPane.getTabCount() - 1, fitTabInPane(name));
        this.validate();
    }

    @Override
    public void replaceTab(final View newTab, final View oldTab) {
        final int index = this.tabbedPane.indexOfComponent((Component) oldTab);
        final Component oldTabComponent = this.tabbedPane.getTabComponentAt(index);
        this.tabs.set(index, (JPanel) newTab);
        this.tabbedPane.remove((Component) oldTab);
        this.tabbedPane.add((Component) newTab, index);
        this.tabbedPane.setTabComponentAt(index, oldTabComponent);
        this.validate();
    }

    private static JPanel fitTabInPane(final String name) {

        final JPanel p = new JPanel(new BorderLayout());
        final JLabel t = new JLabel(name);
        t.setHorizontalAlignment(JLabel.CENTER);
        p.add(t, BorderLayout.SOUTH);
        p.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        p.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        return p;

    }

    @Override
    public void init() {
        this.setVisible(true);
    }

    @Override
    public void goToTab(final View view) {
        this.tabbedPane.setSelectedComponent((Component) view);
    }

}
