package adminpackage.roomtype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import adminpackage.mainview.Crea;
import adminpackage.mainview.Modify;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.structure.HotelManager;

public class CreazioneTipoCamera {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JTextField textDescrizione;
    private JTextField textPrezzo;
    private JTextField textPrezzoPersoneMancanti;
    private JTextField textNumeroMaxPersone;
    private JLabel labelDescrizione;
    private JLabel labelPersonaMancante;
    private JLabel labelPrezzo;
    private JLabel labelMaxPersone;
    private JButton conferma;
    private JButton annulla;
    private GridBagConstraints grid;
    private Dimension screenSize;
    private Image okIcon;
    private Image backIcon;
    private Crea opz;
    private HotelManager hotelmanager;
    private String rispTipo;
    private Double prezzoPersonaMancante;
    private Double prezzoTipo;
    private Integer numeroMaxPersone;

    public CreazioneTipoCamera() {
        this.frame = new JFrame("Creazione tipo per camera");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(600, 300));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/add.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.annulla = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.grid = new GridBagConstraints();
        this.labelDescrizione = new JLabel("Crea un nuovo tipo camera");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelDescrizione, this.grid);
        this.textDescrizione = new JTextField(20);
        this.textDescrizione.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textDescrizione, this.grid);
        this.labelPrezzo = new JLabel("Inserisci prezzo del tipo");
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
        this.labelMaxPersone = new JLabel("Inserisci numero massimo persone");
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelMaxPersone, this.grid);
        this.textNumeroMaxPersone = new JTextField(5);
        this.textNumeroMaxPersone.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textNumeroMaxPersone, this.grid);
        this.labelPersonaMancante = new JLabel("Inserisci prezzo per ogni persona mancante");
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelPersonaMancante, this.grid);
        this.textPrezzoPersoneMancanti = new JTextField(5);
        this.textPrezzoPersoneMancanti.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzoPersoneMancanti, this.grid);
        this.conferma.addActionListener(b -> {
            if (this.textDescrizione.getText().length() == 0 || this.textPrezzo.getText().length() == 0
                    || this.textNumeroMaxPersone.getText().length() == 0
                    || this.textPrezzoPersoneMancanti.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Qualcosa è andato storto, riprova per favore",
                        "Errore creazione supplemento camera", JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane
                        .showConfirmDialog(this.frame,
                                "Sei sicuro di volere conferma la creazione di " + this.textDescrizione.getText()
                                        + " a prezzo " + this.textPrezzo.getText() + " ?",
                                "Uscita", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.rispTipo = this.textDescrizione.getText();
                    this.prezzoTipo = Double.parseDouble(this.textPrezzo.getText());
                    this.prezzoPersonaMancante = Double.parseDouble(this.textPrezzoPersoneMancanti.getText());
                    this.numeroMaxPersone = Integer.parseInt(this.textNumeroMaxPersone.getText());
                    boolean val = HotelManager.create()
                            .addPriceDescriber(new RoomTypePriceDescriber(this.rispTipo, (this.prezzoTipo),
                                    (this.prezzoPersonaMancante), this.numeroMaxPersone));
                    if (val) {
                        JOptionPane.showMessageDialog(frame,
                                "Creazione di " + this.rispTipo + " eseguita con successo!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Qualcosa è andato storto, riprova per favore",
                                "Errore creazione supplemento camera", JOptionPane.ERROR_MESSAGE);
                    }
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    this.opz = new Crea();
                }
            }
        });
        this.annulla.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Crea();
            }
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {

        new CreazioneTipoCamera();
    }

}
