package adminpackage.mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import admin.GuiFactory;
import adminpackage.account.NewAccount;
import secretary.LogIn;
/**
 * 
 *  emanu.
 *
 */
public class Scelte {
    private JFrame frame;
    private Dimension screenSize;
    private JPanel panel;
    private JPanel southPanel;
    private JButton crea;
    private JButton cancella;
    private JButton modificaPrezzo;
    private JButton modifica;
    private JButton storico;
    private JButton statistica;
    private JButton esci;
    private NewAccount newaccount;
    private LogIn log;
    private Crea creaQualcosa;
/**
 * 
 */
    public Scelte() {
        this.frame = new JFrame("Hotel Master - Amministratore");
        this.frame.setSize(new Dimension(600, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(3, 2));
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.crea = GuiFactory.createButton("Crea");
        this.cancella = GuiFactory.createButton("Cancella");
        this.modificaPrezzo = GuiFactory.createButton("Modifica Prezzo");
        this.modifica = GuiFactory.createButton("Modifica");
        this.storico = GuiFactory.createButton("Storico");
        this.statistica = GuiFactory.createButton("Statistica");
        this.crea.addActionListener(a -> {
            this.frame.setVisible(false);
            new Crea();
        });
        this.cancella.addActionListener(b -> {
            this.frame.setVisible(false);
            new Cancella();
        });
        this.modifica.addActionListener(c -> {
            this.frame.setVisible(false);
            new Modify();
        });
        this.modificaPrezzo.addActionListener(d -> {
            this.frame.setVisible(false);
            new ModificaPrezzo();
        });
        this.storico.addActionListener(e -> {

        });
        this.statistica.addActionListener(f -> {

        });
        this.esci = new JButton("Esci");
        this.esci.setBackground(Color.orange);
        this.esci.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.log = new LogIn();
            }
        });

        this.southPanel.add(this.esci);
        this.panel.add(this.crea);
        this.panel.add(this.cancella);
        this.panel.add(this.modifica);
        this.panel.add(this.modificaPrezzo);
        this.panel.add(this.storico);
        this.panel.add(this.statistica);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new Scelte();
    }
}
