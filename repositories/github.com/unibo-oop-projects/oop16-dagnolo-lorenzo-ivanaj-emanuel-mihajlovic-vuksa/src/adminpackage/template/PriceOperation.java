package adminpackage.template;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adminpackage.mainview.ModificaPrezzo;
import adminpackage.mainview.Scelte;

public abstract class PriceOperation {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JLabel labelScelta;
    private JLabel prezzo;
    private JComboBox<String> sceltaDescrizione;
    private JTextField textPrezzo;
    private Dimension screenSize;
    private GridBagConstraints grid;
    private JButton conferma;
    private JButton esci;
    private JButton indietro;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;
    private String scelta;
    private Double prezzoScelto;

    public abstract String[] getElements();

    public PriceOperation(final String descrizione) {

        this.frame = new JFrame();
        this.frame.setSize(new Dimension(400, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        this.grid = new GridBagConstraints();
        this.labelScelta = new JLabel("" + descrizione);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelScelta, this.grid);
        this.sceltaDescrizione = new JComboBox<>(this.getElements());
        this.sceltaDescrizione.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.sceltaDescrizione, this.grid);
        this.prezzo = new JLabel("Inserisci prezzo");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.prezzo, this.grid);
        this.textPrezzo = new JTextField(5);
        this.textPrezzo.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzo, this.grid);
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.conferma.addActionListener(e -> {
            if (this.textPrezzo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Inserisci un prezzo", "Errore inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Modificare "
                        + this.sceltaDescrizione.getSelectedItem() + " al prezzo " + this.textPrezzo.getText() + " ?",
                        "Modifica Prezzo", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.scelta = (String) this.sceltaDescrizione.getSelectedItem();
                    this.prezzoScelto = Double.parseDouble(this.textPrezzo.getText());
                }
            }
        });
        this.indietro.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Tornare indietro?", "Indietro",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new ModificaPrezzo();
            }
        });
        this.esci.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi uscire?", "Uscita", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Scelte();
            }
        });
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

    }

}
