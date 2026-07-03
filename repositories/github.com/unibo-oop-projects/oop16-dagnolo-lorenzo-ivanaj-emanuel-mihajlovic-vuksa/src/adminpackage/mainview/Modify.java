package adminpackage.mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import adminpackage.account.ModificaAccount;
import adminpackage.rooms.SingolaModificaExtraCamera;
import adminpackage.roomtype.ModificaTipoCamera;

/**
 * 
 * emanu.
 *
 */
public class Modify {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JButton singolacamera;
    private JButton tipoCamera;
    private JButton account;
    private JButton indietro;
    private Dimension screenSize;
    private Image backIcon;

    /**
     * 
     */
    public Modify() {
        this.frame = new JFrame("Hotel Master - Modifica");
        this.frame.setSize(new Dimension(400, 400));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridLayout(2, 2));
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.singolacamera = new JButton("Supplemento Camera");
        this.singolacamera.setBackground(Color.cyan);
        this.singolacamera.addActionListener(a -> {
            new SingolaModificaExtraCamera();
            this.frame.setVisible(false);
            this.frame.dispose();
        });
        this.tipoCamera = new JButton("Tipo camera");
        this.tipoCamera.setBackground(Color.cyan);
        this.tipoCamera.addActionListener(c -> {
            this.frame.setVisible(false);
            this.frame.dispose();
            new ModificaTipoCamera();
        });
        this.account = new JButton("Modifica Account");
        this.account.setBackground(Color.CYAN);
        this.account.addActionListener(i -> {
            this.frame.setVisible(false);
            this.frame.dispose();
            new ModificaAccount();
        });
        this.panel.add(this.singolacamera);
        this.panel.add(this.tipoCamera);
        this.panel.add(this.account);
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.indietro.addActionListener(b -> {
            this.frame.setVisible(false);
            this.frame.dispose();
            new Scelte();
        });
        this.southPanel.add(this.indietro);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new Modify();
    }
}