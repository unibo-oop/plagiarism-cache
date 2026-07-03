package secretary.modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.structure.Hotel;
import secretary.mainview.SceltaOpzione;
/**
 * 
 *  emanu.
 *
 */
public class ModifyBoardType {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JLabel label;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private JTextField text;
    private String risp;
    private Dimension screenSize;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private GridBagConstraints grid;
    private Font font;
    private JComboBox<String> combo;
    private String[] str;
    private JLabel labelType;
    private String numDoc;
/**
 * 
 * @param doc
 */
    public ModifyBoardType(final String doc) {
        this.frame = new JFrame();
        this.numDoc = doc;
        this.frame.setSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.southPanel = new JPanel();
        this.str = Hotel.instance().getPriceView(StayTypePriceDescriber.class).stream()
                .map(type -> type.getDescription()).collect(Collectors.toList()).toArray(new String[0]);
        this.labelType = new JLabel("Pensione attuale   " + Hotel.instance().getPriceView(StayTypePriceDescriber.class)
                .stream().filter(type -> type.getDescription().equals(this.numDoc)).findFirst().get());
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelType, this.grid);
        this.label = new JLabel("Scegli il nuovo tipo di pensione");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.combo = new JComboBox<>(this.str);
        this.combo.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.combo, this.grid);
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.font = new Font("Baskerville", Font.HANGING_BASELINE, 60);
        this.annulla.addActionListener(e -> {
            this.frame.setVisible(false);
            new ConfermaPrenotazione("Modifica tipo pensione");
        });
        this.conferma.addActionListener(e -> {
            new SceltaOpzione();
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
}