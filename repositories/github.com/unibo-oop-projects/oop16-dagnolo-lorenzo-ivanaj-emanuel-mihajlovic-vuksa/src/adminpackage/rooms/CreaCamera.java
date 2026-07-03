package adminpackage.rooms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adminpackage.mainview.Crea;
import adminpackage.mainview.Scelte;
import secretary.LogIn;

public class CreaCamera {
    private JFrame frame;
    private JPanel panel;
    private JPanel southpanel;
    private JPanel northPanel;
    private JButton indietro;
    private JButton esci;
    private JButton crea;
    private JLabel label;
    private JLabel labelCamere;
    private JLabel labelPiani;
    private JTextField numeroCamere;
    private JTextField numeroPiani;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;
    private Dimension screenSize;
    private LogIn log;
    private Crea opz;
    private Integer cont = 0;
    private int temp;
    private String rispostaCamere;
    private String rispostaPiani;
    private Integer rispCamere;
    private Integer rispPiani;
    private Integer contatore = 0;
    private AggiuntaInterni aggiuntainterni;

    public CreaCamera() {
        this.frame = new JFrame("Hotel Master - Crea camera");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(700, 200);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(1, 3));
        this.southpanel = new JPanel();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/add.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.labelCamere = new JLabel("Inserisci numero di camere da creare");
        this.labelPiani = new JLabel("Inserisci numero del piano a cui assegnare le camere");
        this.numeroCamere = new JTextField(3);
        this.numeroPiani = new JTextField(3);
        this.northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.crea = new JButton("");
        this.crea.setIcon(new ImageIcon(okIcon));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(exitIcon));
        this.label = new JLabel();
        this.crea.addActionListener(a -> {
            if (this.numeroCamere.getText().length() == 0 || this.numeroPiani.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Opsss, qualcosa e¨ andato storto, riprova per favore",
                        "Errore login", JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare?", "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.rispostaCamere = this.numeroCamere.getText();
                    this.rispCamere = Integer.parseInt(this.rispostaCamere);
                    this.rispostaPiani = this.numeroPiani.getText();
                    this.rispPiani = Integer.parseInt(this.rispostaPiani);
                    this.aggiuntainterni = new AggiuntaInterni(this.rispCamere, this.rispPiani);
                    this.frame.setVisible(false);
                }
            }
        });
        this.indietro.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Crea();
            }
        });

        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere disconnettere?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.northPanel.add(this.labelCamere);
        this.northPanel.add(this.numeroCamere);
        this.northPanel.add(this.labelPiani);
        this.northPanel.add(this.numeroPiani);
        this.southpanel.add(this.label);
        this.panel.add(this.indietro);
        this.panel.add(this.esci);
        this.panel.add(this.crea);
        this.frame.getContentPane().add(this.northPanel, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.southpanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new CreaCamera();
    }
}
