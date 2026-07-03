package adminpackage.rooms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import adminpackage.roomtype.ModificaTipoCamera;
import hotelmaster.database.utilities.RoomExtraUtilities;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import secretary.Modifica;

public class SingolaModificaExtraCamera {
    private JFrame frame;
    private JPanel panel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JTextField text;
    private JLabel label;
    private JButton conferma;
    private JButton completa;
    private JButton indietro;
    private JButton esci;
    private Dimension screenSize;
    private String conversione;
    private Integer risp;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;
    private Set<String> lista;
    private List<String> selezione;

    /**
     * 
     * 
     */
    public SingolaModificaExtraCamera() {
        this.frame = new JFrame("Modifica singola camera");
        this.frame.setSize(500, 400);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.lista = new HashSet<>();
        List<String> extra = new ArrayList<>();
        this.selezione = new ArrayList<>();
        RoomExtraUtilities rm = new RoomExtraUtilities();
        /* Da passare tutti gli extra camera in jcheck */
        // TODO
        this.panel = new JPanel(new GridLayout(5, 2));
        this.panel.setBackground(Color.cyan);
        this.panel.setVisible(false);
        this.northPanel = new JPanel();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.northPanel.setBackground(Color.CYAN);
        /*
         * Prendiamo dal data base TUTTI i extra camera e li mettiamo in una
         * lista di stringhe, poi prendiamo dal data base tutti gli extra di una
         * specifica camera e li mettiamo in un altra lista. Dopo facciamo un
         * COMPARE e vediamo quali sono presenti ...poi, quelli presenti li
         * selezioneremo, e poi andremo a modificare la lista della camera.
         */
        for (String str : extra) {
            JCheckBox check = new JCheckBox(str);
            check.addActionListener(e -> {
                if (check.isSelected()) {
                    this.selezione.add(str);
                    System.out.println(this.selezione);
                } else if (!check.isSelected()) {
                    this.selezione.remove(str);
                }
            });
            check.setBackground(Color.CYAN);
            this.panel.add(check);
        }
        this.label = new JLabel("Inserisci numero della camera");
        this.text = new JTextField(3);
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.conferma = new JButton("Conferma");
        this.completa = new JButton("");
        this.completa.setIcon(new ImageIcon(this.okIcon));
        this.completa.setEnabled(false);
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.conferma.addActionListener(b -> {
            this.conversione = this.text.getText();
            int conv = Integer.parseInt(this.conversione);
            System.out.println(conv);
            this.risp = Integer.parseInt(this.conversione);
            // qui metterò il numero massimo di camere
            // controllare anche se non viene inserito niente
            if (this.risp < 1 || this.risp > 200) {
                JOptionPane.showMessageDialog(frame,
                        "Numero di camera non valida, inserisci un numero di camera valido", "Errore login",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi modificare la camera " + this.risp + " ?",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.completa.setEnabled(true);
                    this.frame.dispose();
                    this.panel.setVisible(true);
                }
            }
        });
        this.completa.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame,
                    "Vuoi confermare le modifiche della camera " + this.risp + " ?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                new ModificaTipoCamera();
            }
        });
        this.indietro.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi confermare tornare indietro ?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Modify();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi confermare di uscire ?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Scelte();
            }
        });
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.northPanel.add(this.label);
        this.northPanel.add(this.text);
        this.northPanel.add(this.conferma);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.completa);
        this.frame.getContentPane().add(this.northPanel, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) throws SQLException {
        new SingolaModificaExtraCamera();
    }
}