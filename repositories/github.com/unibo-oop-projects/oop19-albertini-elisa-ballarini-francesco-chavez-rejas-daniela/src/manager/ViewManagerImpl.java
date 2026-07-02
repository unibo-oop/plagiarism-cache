package manager;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import view.View;

/**
 * Class implementation of {@link ViewManager}.
 * @see ViewManager.
 */
public class ViewManagerImpl implements ViewManager {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int PROPORTION_SIZE = 5;

    private final JFrame frame;
    private final JInternalFrame background;

    /**
     * 
     */
    public ViewManagerImpl() {
        this.frame = new JFrame();
        this.frame.setSize(SCREEN_WIDTH * 3 / PROPORTION_SIZE, SCREEN_HEIGHT * 4 / PROPORTION_SIZE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("TetrisLogo.png")).getImage());
        this.frame.setTitle("TETRIS");
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout());
        final JDesktopPane desktop = new JDesktopPane();
        this.frame.setContentPane(desktop);
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        this.background = new JInternalFrame("", false, false, false);
        this.frame.getContentPane().add(this.background);
        try {
            this.background.setMaximum(true);
        } catch (PropertyVetoException e) {
            System.out.println("JInterna Frame have to be the same size of the JFrame.");
            e.printStackTrace();
        }
        this.background.setEnabled(false);
        final BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) this.background.getUI();
        basicInternalFrameUI.setNorthPane(null);
        this.background.setBorder(null);
        this.background.setVisible(true);
        this.frame.setVisible(true);
    }

    @Override
    public final void setView(final View view) {
        view.initView(this.frame.getSize());
        this.background.setContentPane(view.getMainPanel());
        this.background.getContentPane().setFocusable(true);
        this.background.getContentPane().requestFocusInWindow();
    }
}
