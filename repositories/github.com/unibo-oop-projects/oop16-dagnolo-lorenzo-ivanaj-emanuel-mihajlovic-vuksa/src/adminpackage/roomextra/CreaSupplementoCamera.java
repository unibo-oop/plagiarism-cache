package adminpackage.roomextra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import adminpackage.mainview.Scelte;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.HotelManager;
import hotelmaster.pricing.RoomExtraPriceDescriber;

public class CreaSupplementoCamera {

    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JTextField textDescrizione;
    private JTextField textPrezzo;
    private JLabel labelDescrizione;
    private JLabel labelPrezzo;
    private JButton conferma;
    private JButton annulla;
    private GridBagConstraints grid;
    private Dimension screenSize;
    private Image okIcon;
    private Image backIcon;
    private String rispSupp;
    private String rispPrezzo;
    private double risprezzo;
    private Crea opz;
    private HotelManager hotelmanager;

    public CreaSupplementoCamera() {
        this.frame = new JFrame("Creazione supplemento per camera");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(600, 140));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridLayout(2, 2));
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
        this.labelDescrizione = new JLabel("Inserisci un nuovo supplemento");
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
        this.labelPrezzo = new JLabel("Inserisci prezzo del supplemento");
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
        this.conferma.addActionListener(b -> {
            this.rispSupp = this.textDescrizione.getText();
            this.rispPrezzo = this.textPrezzo.getText();
            this.risprezzo = Double.parseDouble(this.rispPrezzo);
            if (this.textDescrizione.getText().length() == 0 || this.textPrezzo.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Qualcosa è andato storto, riprova per favore",
                        "Errore creazione supplemento camera", JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane
                        .showConfirmDialog(this.frame,
                                "Sei sicuro di volere conferma la creazione di " + this.textDescrizione.getText()
                                        + " a prezzo " + this.textPrezzo.getText() + " ?",
                                "Uscita", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    boolean val = HotelManager.create()
                            .addPriceDescriber(new RoomExtraPriceDescriber(this.rispSupp, (this.risprezzo)));
                    if (val) {
                        JOptionPane.showMessageDialog(frame,
                                "Creazione di " + this.rispSupp + " eseguita con successo!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Qualcosa è andato storto, riprova per favore",
                                "Errore creazione supplemento camera", JOptionPane.ERROR_MESSAGE);
                    }
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    new Scelte();
                }
            }
        });
        this.annulla.addActionListener(a -> {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Crea();
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

    }
    public static void main(String[] args) {
        new CreaSupplementoCamera();
    }
}
