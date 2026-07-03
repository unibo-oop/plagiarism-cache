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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import admin.GuiFactory;
import adminpackage.prices.PriceBoard;
import adminpackage.prices.PriceExtraCamera;
import adminpackage.prices.PricePerson;
import adminpackage.prices.PriceSeason;
import adminpackage.prices.PriceStayExtra;

public class ModificaPrezzo {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JButton supplementoSoggionro;
    private JButton supplementoCamera;
    private JButton tipoPersona;
    private JButton tipoCamera;
    private JButton stagione;
    private JButton pensione;
    private Dimension screenSize;
    private JButton indietro;
    private Image backIcon;

    public ModificaPrezzo() {
        this.frame = new JFrame("Modifica prezzo");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 400);
        this.panel = new JPanel(new GridLayout(3, 2));
        this.southPanel = new JPanel();
        this.panel.setBackground(Color.cyan);
        this.southPanel.setBackground(Color.cyan);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.supplementoCamera = GuiFactory.createButton("Supplemento Camera");
        this.supplementoCamera.addActionListener(e -> {
            this.frame.setVisible(false);
            new PriceExtraCamera("Scegli supplemento camera");
        });
        this.supplementoSoggionro = GuiFactory.createButton("Supplemento Soggiorno");
        this.supplementoSoggionro.addActionListener(e -> {
            this.frame.setVisible(false);
            new PriceStayExtra("Scegli supplemento soggiorno");
        });
        this.tipoPersona = GuiFactory.createButton("Persona");
        this.tipoPersona.addActionListener(e -> {
            this.frame.setVisible(false);
            new PricePerson("Scegli tipo di persona");
        });
        this.tipoCamera = GuiFactory.createButton("Tipo Camera");
        this.tipoCamera.addActionListener(e -> {
            this.frame.setVisible(false);
            new PricePerson("Scegli tipo di persona");
        });
        this.stagione = GuiFactory.createButton("Stagione");
        this.stagione.addActionListener(e -> {
            this.frame.setVisible(false);
            new PriceSeason("Scegli la stagione");
        });
        this.pensione = GuiFactory.createButton("Pensione");
        this.pensione.addActionListener(e -> {
            this.frame.setVisible(false);
            new PriceBoard("Scegli il tipo di pensione");
        });
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.indietro.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Tornare indietro?", "Indietro",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.panel.add(this.supplementoCamera);
        this.panel.add(this.supplementoSoggionro);
        this.panel.add(this.tipoPersona);
        this.panel.add(this.tipoCamera);
        this.panel.add(this.stagione);
        this.panel.add(this.pensione);
        this.southPanel.add(this.indietro);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new ModificaPrezzo();
    }
}
