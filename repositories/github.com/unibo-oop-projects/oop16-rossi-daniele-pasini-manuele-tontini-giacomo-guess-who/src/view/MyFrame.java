package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import utilities.ScreenResolution;

/**
 * Represents the main window.
 *
 */
public class MyFrame extends JFrame {

    private static final long serialVersionUID = -1947466585058197323L;
    private static final double X_PROPORTION = 0.8;
    private static final double Y_PROPORTION = 0.9;

    /**
     * @param title
     *            the window title
     * @param lm
     *            the window layout
     * @param background
     *            the background image
     * 
     */

    @SuppressWarnings("serial")
    public MyFrame(final String title, final Optional<ImageIcon> background, final LayoutManager lm) {
        super(title);
        final Dimension d = new Dimension((int) (ScreenResolution.getScreenWidth() * X_PROPORTION),
                (int) (ScreenResolution.getScreenHeight() * Y_PROPORTION));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(d);
        this.setMaximumSize(d);
        JPanel bgPanel;
        if (background.isPresent()) {
            bgPanel = new JPanel() {
                private Image image = background.get().getImage();

                @Override
                public void paintComponent(final Graphics g) {
                    g.drawImage(image, 0, 0, this);
                }
            };
        } else {
            bgPanel = new JPanel();
        }
        bgPanel.setLayout(lm);
        this.setContentPane(bgPanel);
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Look and feel error");
        }
    }

    /**
     * @return the window main panel
     */
    public JPanel getMainPanel() {
        return (JPanel) this.getContentPane();
    }
}
