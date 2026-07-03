package adminpackage.floor;

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

import adminpackage.mainview.Crea;
import adminpackage.mainview.Scelte;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.HotelManager;

/**
 * 
 * emanu.
 *
 */
public class CreaPiano {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JLabel label;
    private JLabel labelNumPiani;
    private JTextField text;
    private JButton indietro;
    private JButton esci;
    private JButton conferma;
    private Integer numPiani;
    private Dimension screenSize;
    private GridBagConstraints grid;
    private Image backIcon;
    private Image exitIcon;
    private Image okIcon;

    /**
     * 
     */
    public CreaPiano() {
        this.frame = new JFrame("Hotel Master - Modifica");
        this.frame.setSize(new Dimension(350, 300));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setBackground(Color.CYAN);
        this.grid = new GridBagConstraints();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.labelNumPiani = new JLabel(
                "Numero di piani attualmente presenti: " + Hotel.instance().getFloorView().size());
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelNumPiani, this.grid);
        this.label = new JLabel("Inserisci numero di piani da creare");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.text = new JTextField(5);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.text, this.grid);
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.conferma.addActionListener(a -> {
            if (this.text.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di camera", "Errore creazione account",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare?", "Indietro", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.numPiani = Integer.parseInt(this.text.getText());
                    HotelManager.create().addFloors(this.numPiani);
                }
            }
        });
        this.indietro.addActionListener(b -> {
            this.frame.setVisible(false);
            this.frame.dispose();
            new Crea();
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi uscire?", "Uscita", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }
}