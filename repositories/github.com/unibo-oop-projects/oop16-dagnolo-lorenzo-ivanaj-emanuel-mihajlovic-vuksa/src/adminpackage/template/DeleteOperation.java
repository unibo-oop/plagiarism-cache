package adminpackage.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adminpackage.mainview.Cancella;
import adminpackage.mainview.Scelte;

/**
 * 
 * emanu.
 *
 */
public abstract class DeleteOperation {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JComboBox<String> combo;
    private JLabel label;
    private Dimension screenSize;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private String rispEliminazione;
    private GridBagConstraints grid;
    private Font font;

    /**
     * 
     * @return
     */
    public abstract String[] getElements();

    /**
     * 
     */
    public abstract void sendData();

    /**
     * 
     * @param testo
     *            label text
     * @param titolo
     *            frame text
     */
    public DeleteOperation(final String testo, final String titolo) {
        this.frame = new JFrame(titolo);
        this.frame.setSize(new Dimension(600, 250));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.panel.setBackground(Color.cyan);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
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
        this.font = new Font("Baskerville", Font.ROMAN_BASELINE, 20);
        this.label = new JLabel(testo);
        this.label.setFont(font);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.combo = new JComboBox<>(this.getElements());
        this.combo.setPreferredSize(new Dimension(50, 200));
        this.combo.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.combo, this.grid);
        this.conferma.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame,
                    "Confermare la cancellazione di " + this.combo.getSelectedItem() + " ?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                // System.out.println(this.combo.getSelectedItem().toString());
                this.sendData();
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Tornare indietro", "Indietro",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Cancella();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi uscire?", "Uscita", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
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
/**
 * 
 * @return
 */
    public String getElementoScelto() {
        return this.combo.getSelectedItem().toString();
    }
}
