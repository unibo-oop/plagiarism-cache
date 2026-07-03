package secretary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hotelmaster.database.utilities.RoomExtraUtilities;
import hotelmaster.pricing.RoomExtraPriceDescriber;

public class Filtri {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JLabel labelExtra;
    private List<String> selezione;
    private Set<String> lista;
    private Dimension screenSize;
    private JButton conferma;

    public Filtri() throws SQLException {
        this.frame = new JFrame("Modifica singola camera");
        this.frame.setSize(500, 400);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setBackground(Color.CYAN);
        this.lista = new HashSet<>();
        List<String> extra = new ArrayList<>();
        this.selezione = new ArrayList<>();
        RoomExtraUtilities rm = new RoomExtraUtilities();
        Set<RoomExtraPriceDescriber> set = rm.getAll();
        for (RoomExtraPriceDescriber room : set) {
            extra.add(room.getDescription());
        }
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
        this.conferma = new JButton("");
        this.conferma.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare ?",
                    "Conferma", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                
                //this.selezione(è una lista) contiene gli extra selezionati
                this.panel.setVisible(true);
            }
        });
        this.frame.getContentPane().add(this.panel);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) throws SQLException {
        new Filtri();
    }
}