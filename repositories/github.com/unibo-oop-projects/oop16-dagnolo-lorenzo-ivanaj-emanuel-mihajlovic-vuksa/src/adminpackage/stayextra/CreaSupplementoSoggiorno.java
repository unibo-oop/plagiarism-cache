package adminpackage.stayextra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adminpackage.mainview.Crea;
import adminpackage.mainview.Modify;
import adminpackage.mainview.Scelte;
/**
 * 
 * emanu.
 *
 */
public class CreaSupplementoSoggiorno {
    private JFrame frame;
    private JPanel southPanel;
    private JPanel panel;
    private JTextField textDescrizione;
    private JTextField textPrezzo;
    private JCheckBox aPersona;
    private GridBagConstraints grid;
    private JLabel labelDescrizione;
    private JLabel labelPrezzo;
    private JButton crea;
    private JButton annulla;
    private JButton esci;
    private Dimension screenSize;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;
    private Crea opz;
    private String supplemento;
    private double prezzo;

    /**
     * 
     */
    public CreaSupplementoSoggiorno() {
        this.frame = new JFrame("Creazione di un supplemento soggiorno");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(500, 250));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/add.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        this.crea = new JButton("");
        this.crea.setIcon(new ImageIcon(this.okIcon));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.labelDescrizione = new JLabel("Inserisci nuovo tipo supplemento");
        this.labelDescrizione.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
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
        this.labelPrezzo = new JLabel("Inserisci prezzo");
        this.labelPrezzo.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelPrezzo, this.grid);
        this.textPrezzo = new JTextField(5);
        this.textPrezzo.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzo, this.grid);
        this.aPersona = new JCheckBox("Prezzo per persona");
        this.aPersona.setBackground(Color.CYAN);
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.aPersona, this.grid);
        this.crea.addActionListener(a -> {
            if (this.textDescrizione.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un supplemento valido", "Errore di inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else if (this.textPrezzo.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un prezzo valido", "Errore di inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare?", "Tornare indietro",
                        JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.supplemento = this.textDescrizione.getText();
                    this.prezzo = Double.parseDouble(this.textPrezzo.getText());
                    if (this.aPersona.isSelected()) {
                        // Se la checkbox è selezionata succede qualcosa
                    } else {
                        this.aPersona.setSelected(false);
                    }
                }
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?",
                    "Tornare indietro", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Crea();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Uscire?", "Uscita", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.crea);
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
        new CreaSupplementoSoggiorno();
    }

}
