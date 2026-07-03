package secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import secretary.mainview.SceltaOpzione;
import secretary.modify.ChiudiPrenotazione;
import secretary.modify.ConfermaPrenotazione;
import secretary.modify.ModificaDataIngresso;
import secretary.modify.ModificaDurataSoggiorno;
import secretary.modify.ModificaPassword;
import secretary.modify.ModifyBoardType;
/**
 * 
 *  emanu.
 *
 */
public class Modifica {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JCheckBox checkSeleziona;
    private JCheckBox checkModificaSogg;
    private JCheckBox checkPassword;
    private JCheckBox checkConfermaPrenotazione;
    private JCheckBox checkModificaIng;
    private JCheckBox checkBoardType;
    private JButton indietro;
    private JButton ok;
    private Image backIcon;
    private Image okIcon;
    private Dimension screenSize;
    private ChiudiPrenotazione chiudiprenotazione;
    private ModificaDurataSoggiorno modificaduratasogg;
    private SceltaOpzione sceltaopzioni;
/**
 * 
 */
    public Modifica() {
        this.frame = new JFrame("Modifica");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(400, 400));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(5, 1));
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.checkSeleziona = new JCheckBox("Chiudi prenotazione");
        this.checkSeleziona.setBackground(Color.cyan);
        this.checkSeleziona.addActionListener(e -> {
            this.frame.setVisible(false);
            new ChiudiPrenotazione("Chiudi prenotazione");
        });
        this.checkModificaSogg = new JCheckBox("Modifica data uscita");
        this.checkModificaSogg.setBackground(Color.cyan);
        this.checkModificaSogg.addActionListener(e -> {
            this.frame.setVisible(false);
            new ModificaDurataSoggiorno("Modifica durata soggiorno");
        });
        this.checkPassword = new JCheckBox("Modifica Password");
        this.checkPassword.setBackground(Color.cyan);
        this.checkPassword.addActionListener(e -> {
            this.frame.setVisible(false);
            new ModificaPassword();
        });
        this.checkConfermaPrenotazione = new JCheckBox("Conferma Prenotazione");
        this.checkConfermaPrenotazione.setBackground(Color.cyan);
        this.checkConfermaPrenotazione.addActionListener(e -> {
            this.frame.setVisible(false);
            new ConfermaPrenotazione("Conferma prenotazione");
        });
        this.checkModificaIng = new JCheckBox("Modifica data ingresso");
        this.checkModificaIng.setBackground(Color.cyan);
        this.checkModificaIng.addActionListener(e -> {
            this.frame.setVisible(false);
            new ModificaDataIngresso("Modifica data ingresso");
        });
        this.checkBoardType = new JCheckBox("Modifica Tipo Pensione");
        this.checkBoardType.setBackground(Color.CYAN);
        this.checkBoardType.addActionListener(e -> {
            this.frame.setVisible(false);
            new ModifyBoardType("Modifica tipo pensione");
        });
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.ok = new JButton("");
        this.ok.setIcon(new ImageIcon(this.okIcon));
        this.panel.add(this.checkModificaSogg);
        this.panel.add(this.checkModificaIng);
        this.panel.add(this.checkConfermaPrenotazione);
        this.panel.add(this.checkSeleziona);
        this.panel.add(this.checkPassword);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.ok);
        this.indietro.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sicuro di volere tornare indietro?", "",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.sceltaopzioni = new SceltaOpzione();
            }
        });
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
/**
 * 
 * @param args
 */
    public static void main(final String[] args) {
        new Modifica();
    }

}
