package adminpackage.rooms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adminpackage.mainview.Cancella;
import adminpackage.mainview.Scelte;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.Room;
import secretary.FaseRegistrazione;
import secretary.LogIn;
import secretary.mainview.SceltaOpzione;

public class CancellaCamera {
    private JFrame frame;
    private JFrame frameReg;
    private List<JButton> button;
    private JButton indietro;
    private JButton esci;
    private JButton conferma;
    private JComboBox<Integer> piani;
    private JPanel pannelloFiltri;
    private JPanel pannelloBottoni;
    private JPanel southPanel;
    private JLabel label;
    private LogIn log;
    private SceltaOpzione scelta;
    private FaseRegistrazione faseregistrazione;
    private Integer[] strPiani;
    private List<String> listaSelezione;
    private Cancella cancella;

    public CancellaCamera() {

        this.frame = new JFrame("Hotel Master");
        // this.frameReg = new JFrame("Hotel Master - Registrazione");
        // this.frame.setPreferredSize(new Dimension(800,800));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pannelloFiltri = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.pannelloBottoni = new JPanel(new GridLayout(15, 10));
        this.southPanel = new JPanel();
        this.button = new ArrayList<>();
        this.listaSelezione = new ArrayList<>();
        for (final Room stanza : Hotel.instance().getRoomView()) {
            JButton b = new JButton(stanza.getID().getFullID());
            button.add(b);
            if (stanza.getOccupationsView().isEmpty()) {
                b.addActionListener(a -> {
                    b.setBackground(Color.RED);
                    this.listaSelezione.add(b.getText());
                    b.setBackground(Color.GREEN);
                });
            } else {
                b.setBackground(Color.RED);
            }
            this.pannelloBottoni.add(b);
        }
        this.label = new JLabel("Scegli il numero del piano");
        this.strPiani = Hotel.instance().getFloorView().keySet()
                .toArray(new Integer[Hotel.instance().getFloorView().keySet().size()]);
        this.piani = new JComboBox<>(this.strPiani);
        this.esci = new JButton("Esci");
        this.indietro = new JButton("Indietro");
        this.conferma = new JButton("Conferma");
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                // d = new frameCamere();
                new Scelte();
            }
        });
        this.indietro.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Cancella();
            }
        });
        this.conferma.addActionListener(d -> {
            int risp = JOptionPane.showConfirmDialog(this.frame,
                    "Sei sicuro di volere cancellare le camere " + this.listaSelezione + " ?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
            }
        });
        this.pannelloFiltri.add(this.label);
        this.pannelloFiltri.add(this.piani);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.pannelloFiltri, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.pannelloBottoni);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new CancellaCamera();
    }
}
