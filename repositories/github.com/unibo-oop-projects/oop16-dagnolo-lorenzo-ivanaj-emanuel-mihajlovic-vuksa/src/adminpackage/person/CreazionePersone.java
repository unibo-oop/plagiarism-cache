package adminpackage.person;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adminpackage.mainview.Cancella;
import adminpackage.mainview.Crea;
import adminpackage.mainview.Scelte;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.structure.HotelManager;

public class CreazionePersone {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JTextField text;
    private JTextField textPrezzo;
    private JTextField textDa;
    private JTextField textA;
    private JLabel label;
    private JLabel labelPrezzo;
    private JLabel labelDa;
    private JLabel labelA;
    private Dimension screenSize;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private String rispCreazionePersona;
    private Double prezzo;
    private String da;
    private String a;
    private Double rispPrezzo;
    private GridBagConstraints grid;

    public CreazionePersone() {
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(600, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.panel.setBackground(Color.cyan);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        String[] ciao = { "ciao", "boh", "non lo so" };
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exit = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exit));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));

        this.label = new JLabel("Inserisci tipo di persona da creare");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.text = new JTextField(20);
        this.text.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.text, this.grid);
        this.labelPrezzo = new JLabel("Inserisci il prezzo per il tipo di persona");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelPrezzo, this.grid);
        this.textPrezzo = new JTextField(5);
        this.textPrezzo.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzo, this.grid);
        this.labelDa = new JLabel("Inserisci età DA");
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelDa, this.grid);
        this.textDa = new JTextField(3);
        this.textDa.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textDa, this.grid);
        this.labelA = new JLabel("Inserisci età A");
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelA, this.grid);
        this.textA = new JTextField(3);
        this.textA.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textA, this.grid);
        this.conferma.addActionListener(a -> {
            if (this.text.getText().length() == 0 || this.textPrezzo.getText().length() == 0
                    || this.textDa.getText().length() == 0 || this.textA.getText().length() == 0) {
                JOptionPane.showMessageDialog(this.frame, "Completa tutti i campi", "Errore registrazione",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame,
                        "Vuoi confermare la cancellazione di di " + this.text.getText() + " ?", "Conferma",
                        JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.rispCreazionePersona = this.text.getText();
                    this.prezzo = Double.parseDouble(this.textPrezzo.getText());
                    this.da = this.textDa.getText();
                    this.a = this.textA.getText();
                    HotelManager.create()
                            .addPriceDescriber(new PersonPriceDescriber(
                                    this.rispCreazionePersona + " (" + this.da + "-" + this.a + ")",
                                    (this.prezzo)));
                }
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Tornare indietro", "Indietro",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Crea();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi uscire?", "Uscita", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Scelte();
            }

        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new CreazionePersone();
    }

}
