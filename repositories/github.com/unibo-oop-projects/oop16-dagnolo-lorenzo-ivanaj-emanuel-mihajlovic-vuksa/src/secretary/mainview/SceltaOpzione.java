package secretary.mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import secretary.FaseRegistrazione;
import secretary.LogIn;
import secretary.Modifica;
import secretary.SceltaCamere;
import secretary.pay.Pagamento;

/**
 * Qui ci sono 3 diverse scelte: 1 - Registrazione / Prenotazione, qui si
 * registra sul momento oppure in un determinato giorno l'ingresso di una
 * famiglia 2 - Modifica
 * 
 * 
 * 3 - pagamento , qui inseriamo il numero della camera o del documento della
 * persona a cui era intestato quella specifica camera, e appare il prezzo
 */
public class SceltaOpzione {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JButton prenotazione;
    private JButton registrazione;
    private JButton modifica;
    private JButton pagamento;
    private JButton indietro;
    private JButton esci;
    private SceltaCamere camere;
    private FaseRegistrazione faseregistrazione;
    private Dimension screenSize;
    private SceltaOpzione opz;
    private LogIn log;
    private Modifica modific;
    private Pagamento paga;

    public SceltaOpzione() {
        this.frame = new JFrame("Hotel Master");
        this.frame.setMinimumSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(3, 1));
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.prenotazione = new JButton("Prenotazione / Registrazione");
        this.modifica = new JButton("Modifica");
        this.pagamento = new JButton("Pagamento");
        this.indietro = new JButton("Indietro");
        this.esci = new JButton("Esci");
        this.esci.setPreferredSize(new Dimension(100, 50));
        this.panel.add(prenotazione);
        this.panel.add(modifica);
        this.panel.add(pagamento);
        this.prenotazione.addActionListener(a -> {
            this.frame.setVisible(false);
            this.faseregistrazione = new FaseRegistrazione();
        });
        this.modifica.addActionListener(c -> {
            this.frame.setVisible(false);
            this.modific = new Modifica();
        });
        this.pagamento.addActionListener(d -> {
            this.frame.setVisible(false);
            this.paga = new Pagamento();
        });
        this.esci.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.log = new LogIn();
            }
        });
        this.southPanel.add(this.esci);
        this.frame.add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SceltaOpzione();
    }
}
