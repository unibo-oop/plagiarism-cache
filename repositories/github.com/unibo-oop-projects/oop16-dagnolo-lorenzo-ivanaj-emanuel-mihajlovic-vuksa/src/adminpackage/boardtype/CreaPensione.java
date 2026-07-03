package adminpackage.boardtype;

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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import adminpackage.mainview.Crea;
import adminpackage.mainview.Modify;
import adminpackage.mainview.Scelte;

public class CreaPensione {
    private JFrame frame;
    private JPanel panel;
    private JPanel secondPanel;
    private JPanel southPanel;
    private JPanel secondSouthPanel;
    private JLabel labelTipoSoggiorno;
    private JLabel labelPrezzoSoggiorno;
    private JTextField textTipoSoggiorno;
    private JTextField textPrezzoSoggiorno;
    private JCheckBox scelta;
    private JCheckBox aPersona;
    private JButton conferma;
    private JButton annulla;
    private JButton indietro;
    private String rispTipoSogg;
    private Double rispPrezzoSogg;
    private GridBagConstraints grid;
    private Image ok;
    private Dimension screenSize;
    private Image exit;
    private Image backIcon;

    public CreaPensione() {
        this.frame = new JFrame("Modifica - Tipo pensione");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(500, 250));
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exit = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.exit));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridBagLayout());
        this.secondPanel = new JPanel(new GridBagLayout());
        this.secondPanel.setBackground(Color.cyan);
        this.secondSouthPanel = new JPanel();
        this.secondSouthPanel.setBackground(Color.cyan);
        this.grid = new GridBagConstraints();
        this.southPanel = new JPanel();
        this.labelTipoSoggiorno = new JLabel("Inserisci il tipo di pensione da creare");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelTipoSoggiorno, this.grid);
        this.textTipoSoggiorno = new JTextField(20);
        this.textTipoSoggiorno.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textTipoSoggiorno, this.grid);
        this.labelPrezzoSoggiorno = new JLabel("Inserisci il prezzo (es. 10.10)");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelPrezzoSoggiorno, this.grid);
        this.textPrezzoSoggiorno = new JTextField(5);
        this.textPrezzoSoggiorno.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzoSoggiorno, this.grid);
        this.conferma.addActionListener(a -> {
            if (this.textPrezzoSoggiorno.getText().length() == 0 || this.textTipoSoggiorno.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Qualcosa è andato storto, riprova per favore", "Errore",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                this.rispTipoSogg = this.textTipoSoggiorno.getText();
                this.rispPrezzoSogg = Double.parseDouble(this.textPrezzoSoggiorno.getText());
                int risp = JOptionPane.showConfirmDialog(
                        this.frame, "Vuoi confermare creazione di " + this.textTipoSoggiorno.getText()
                                + " al prezzo di " + this.textPrezzoSoggiorno.getText() + " ?",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.frame.setVisible(false);
                    new Scelte();
                }
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Uscire?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.indietro.addActionListener(c -> {
                this.frame.setVisible(false);
                new Crea();
        });
        this.panel.setBackground(Color.CYAN);
        this.southPanel.setBackground(Color.CYAN);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CreaPensione();
    }
}
