package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * Finestra iniziale.
 * @author silviobrasini
 *
 */
public class Main extends JFrame {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int MIN_WIDTH = screenSize.width * 1 / 6;
    private static final int MIN_HEIGHT = screenSize.height * 1 / 5;
    private JPanel actualPanel = null;
    private JButton btnBack;
    private JButton btnOK;
    private JPanel panelDown;
    /**
     * Costruttore di Main.
     */
    public Main() {
        this.setTitle("DbEditor");
        this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());
        this.panelDown = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnBack = new JButton("←");
        this.btnOK = new JButton("OK");
        this.btnOK.addActionListener(e -> this.setPanel(new LocalRemote(this.btnOK)));
        this.btnBack.setVisible(false);
        this.panelDown.add(this.btnBack);
        this.panelDown.add(this.btnOK);
        this.add(this.panelDown, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    /**
     * Setta un nuovo panel sul frame principale.
     * @param newPanel
     */
    public void setPanel(JPanel newPanel) {
        if (this.actualPanel != null) {
            this.remove(this.actualPanel);
        }
        this.actualPanel = newPanel;
        this.add(actualPanel);
        revalidate();
        //this.pack();
    }
}
