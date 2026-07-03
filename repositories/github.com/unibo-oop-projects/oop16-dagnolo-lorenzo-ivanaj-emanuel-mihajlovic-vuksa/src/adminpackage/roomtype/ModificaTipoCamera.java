package adminpackage.roomtype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admin.AggiuntaTipoCamera;
import adminpackage.mainview.Modify;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.structure.Hotel;

/*Da fare i controlli se non viene inserito niente nelle textField*/
public class ModificaTipoCamera {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JComboBox<String> descrizione;
    private JLabel labelDescrizione;
    private JLabel labelCamera;
    private JTextField textCamera;
    private JButton conferma;
    private JButton annulla;
    private GridBagConstraints grid;
    private Dimension screenSize;
    private Image okIcon;
    private Image backIcon;

    private String[] str;
    private String rispSupp;
    private Integer numCamera;

    public ModificaTipoCamera() {
        this.frame = new JFrame("Tipo Camera");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(300, 300));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        Hotel.instance().getPriceView(RoomTypePriceDescriber.class)
                .forEach(type -> System.out.println(type.getDescription()));
        this.str = Hotel.instance().getPriceView(RoomTypePriceDescriber.class).stream()
                .map(type -> type.getDescription()).collect(Collectors.toList())
                .toArray(new String[Hotel.instance().getPriceView(RoomTypePriceDescriber.class).size()]);
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
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
        this.labelCamera = new JLabel("Inserisci numero camera");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelCamera, this.grid);
        this.textCamera = new JTextField(5);
        this.textCamera.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textCamera, this.grid);
        this.labelDescrizione = new JLabel("Scegli tipo di camera");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelDescrizione, this.grid);
        this.descrizione = new JComboBox<>(this.str);
        this.descrizione.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.descrizione, this.grid);
        this.conferma.addActionListener(b -> {
            if (this.textCamera.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci un numero di camera", "Errore registrazione",
                        JOptionPane.ERROR_MESSAGE);
                this.numCamera = Integer.parseInt(this.textCamera.getText());
                this.rispSupp = (String) this.descrizione.getSelectedItem();
                int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare?", "Uscita", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    new AggiuntaTipoCamera(this.numCamera);
                    this.frame.setVisible(false);
                }
            }
        });
        this.annulla.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Modify();
            }
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.panel);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new ModificaTipoCamera();
    }
}