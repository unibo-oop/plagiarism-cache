package view;

import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * Creates a new JFrame.
 *
 */
public class MyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel jp;

    /**
     * 
     * @param lm     layout
     * @param width  width of layout
     * @param height height of layout
     * 
     * Constructor for MyFrame.
     */
    public MyFrame(final LayoutManager lm, final int width, final int height) {
        this.setResizable(false);
        this.setBounds(width, height, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.jp = new JPanel(lm);
        this.getContentPane().add(this.jp);
    }

    /**
     * 
     * @return the panel
     */
    public JPanel getMainPanel() {
        return this.jp;
    }
}
