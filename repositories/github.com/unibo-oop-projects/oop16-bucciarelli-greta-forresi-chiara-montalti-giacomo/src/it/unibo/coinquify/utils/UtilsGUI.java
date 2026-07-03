package it.unibo.coinquify.utils;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Panel with utility.
 */
public class UtilsGUI implements PaneGUI {
    private final JPanel panel;

    /**
     * Create a utils gui.
     */
    public UtilsGUI() {
        this.panel = new JPanel(new GridLayout(0, 2));
        build();
    }

    private void build() {
        this.panel.add(new JLabel(Messages.getMessages().getString("FLAT_ME_MESSAGE"), JLabel.CENTER));
        this.panel.add(getJLabelWithLink("https://flatme.it"));
        this.panel.add(new JLabel(Messages.getMessages().getString("SHOP_MESSAGE"), JLabel.CENTER));
        this.panel.add(getJLabelWithLink("https://www.doveconviene.it"));
        this.panel.add(new JLabel(Messages.getMessages().getString("FRIGOK_MESSAGE"), JLabel.CENTER));
        this.panel.add(getJLabelWithLink("http://bit.ly/2nRT9cl"));
    }

    /**
     * @param url
     * @return
     */
    private JLabel getJLabelWithLink(final String url) {
        final JLabel label = new JLabel("<html><br><a href=#>" + url + "</a></html>");
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL(url).toURI());
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(panel, Messages.getMessages().getString("ERROR"));
                }
            }
        });
        return label;
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("UTILS");
    }

    /**
     * @param frame
     *            to display
     */
    public static void finishFrame(final JFrame frame) {
        ((JComponent) frame.getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
