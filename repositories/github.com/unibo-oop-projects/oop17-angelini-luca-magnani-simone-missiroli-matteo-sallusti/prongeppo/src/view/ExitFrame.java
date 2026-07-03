package view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * @author Luca
 *
 */
public class ExitFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1168529427175177389L;
    /**
     * the frame displayed at the exit choice.
     */
    public ExitFrame() {
        super();
        final int width = 400;
        final int height = 250;
        final JPanel panel = new JPanel();
        final JLabel text = new JLabel("Is this a good choice?");
        final ImageIcon icon = new ImageIcon(getClass().getResource("/res/gerry.png"));

        final Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon.setImage(img);
        final JLabel label = new JLabel(icon);

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(text, BorderLayout.NORTH);

        final int n = JOptionPane.showConfirmDialog(this, panel, "Sure?", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
                System.exit(0);
        }
    }
}
