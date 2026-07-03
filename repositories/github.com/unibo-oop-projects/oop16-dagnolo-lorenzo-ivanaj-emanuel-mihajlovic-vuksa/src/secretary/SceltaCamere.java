package secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.reservations.StayBuilder;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.Room;
import secretary.mainview.SceltaOpzione;

/**
 * In alto abbiamo i filtri che in base al numero delle persone, le camere,
 * vengono aggiornate. Cliccando sopra una camera, appare una JOption, che ti
 * avvisa se vuoi registrare/prenotare quella camera
 */

public class SceltaCamere {

    private JFrame frame;
    private JFrame frameReg;
    private List<JButton> button;
    private JButton indietro;
    private JButton esci;
    private JComboBox<Integer> boxAdulti;
    private JComboBox<Integer> boxRagazzi;
    private JComboBox<Integer> piani;
    private JCheckBox filtri;
    private JPanel pannelloFiltri;
    private JPanel pannelloBottoni;
    private JPanel southPanel;
    private JLabel numeroPiano;
    private JLabel labelNumeroAdulti;
    private JLabel labelNumeroRagazzi;
    private final Integer[] str;
    private LogIn log;
    private SceltaOpzione scelta;
    private FaseRegistrazione faseregistrazione;
    private Integer numAdulti;
    private Integer numRagazzi;
    private Integer[] strPiani;

    /**
     * 
     * */
    public SceltaCamere(
            final StayBuilder staybuilder/* StayBuilder staybuilder */) {
        this.frame = new JFrame("Hotel Master");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pannelloFiltri = new JPanel(new GridLayout(1, 1));
        this.pannelloFiltri.setPreferredSize(new Dimension(0, 40));
        this.pannelloFiltri.setBackground(Color.CYAN);
        this.pannelloBottoni = new JPanel(new GridLayout(15, 10));
        
        /* int maxpersone = Hotel.instance().getRoomView().stream() .filter(room
          -> room.getOccupationsView().stream() .allMatch(occ ->
          occ.getStay().getDates().overlaps(staybuilder.getDates()))) .map(room
          -> room.getType().getMaxPeople()).max(Integer::max).get(); for
          (PersonPriceDescriber person :
          Hotel.instance().getPriceView(PersonPriceDescriber.class)) {
          person.getDescription(); }
         */
        this.filtri = new JCheckBox("Utilizza filtri");
        this.filtri.addActionListener(e -> {
            new Filtri();
        });
        this.southPanel = new JPanel();
        this.str = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        button = new ArrayList<>();
        Set<Room> camereSelezionate = new HashSet<>();
        for (final Room stanza : Hotel.instance().getRoomView()) {
            JButton b = new JButton(stanza.getID().getFullID());
            button.add(b);
            if (stanza.getOccupationsView().isEmpty()) {
                b.addActionListener(a -> {
                    b.setBackground(Color.RED);
                    camereSelezionate.add(stanza);
                    b.setBackground(Color.GREEN);
                });
            } else {
                b.setBackground(Color.RED);
            }
            this.pannelloBottoni.add(b);
        }

        for (int i = 1; i <= 200; i++) {
            JButton b = new JButton(String.valueOf(i));
            button.add(b);
            b.setBackground(Color.GREEN);
            b.addActionListener(a -> {
                int risp = JOptionPane.showConfirmDialog(this.frame,
                        "Sei sicuro di voler registrare la camera " + b.getText() + "?", "Conferma",
                        JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    b.setBackground(Color.RED);
                }
            });
            this.pannelloBottoni.add(b);
        }
        this.esci = new JButton("Esci");
        this.indietro = new JButton("Indietro");
        this.esci.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                // d = new frameCamere();
                this.log = new LogIn();
            }
        });
        this.indietro.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Conferma",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.faseregistrazione = new FaseRegistrazione();
            }
        });
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.pannelloFiltri.add(this.filtri);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.pannelloFiltri, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.pannelloBottoni);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SceltaCamere(null);
    }
}