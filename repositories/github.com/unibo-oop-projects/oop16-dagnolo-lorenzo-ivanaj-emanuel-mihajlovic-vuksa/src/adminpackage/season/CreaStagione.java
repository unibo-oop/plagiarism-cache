package adminpackage.season;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import adminpackage.mainview.Crea;
import adminpackage.mainview.Scelte;

public class CreaStagione {
    private JFrame frame;
    private Dimension screenSize;
    private JPanel panel;
    private JPanel southPanel;
    private JButton crea;
    private JButton cancella;
    private JButton esci;
    private JDateChooser dateStart;
    private JDateChooser dateEnd;
    private SimpleDateFormat dataGgInizio;
    private SimpleDateFormat dataMmInizio;
    private SimpleDateFormat dataAaInizio;
    private SimpleDateFormat dataGgFine;
    private SimpleDateFormat dataMmFine;
    private SimpleDateFormat dataAaFine;
    private JTextField textDescrizione;
    private JTextField textPrezzo;
    private JLabel labelDescrizione;
    private JLabel labelPrezzo;
    private JLabel labelInizio;
    private JLabel labelFine;
    private GridBagConstraints grid;
    private int rispggIn;
    private int rispmmIn;
    private int rispaaIn;
    private int rispggFi;
    private int rispmmFi;
    private int rispaaFi;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private String risp;
    private double rispPrezzo;
    private LocalDate ing;
    private LocalDate ex;

    public CreaStagione() {
        this.frame = new JFrame("Hotel Master - Amministratore");
        this.frame.setSize(new Dimension(600, 350));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exit = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.panel.setBackground(Color.cyan);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.labelDescrizione = new JLabel("Inserisci tipo di stagione da creare");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelDescrizione, this.grid);
        this.textDescrizione = new JTextField(20);
        this.textDescrizione.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textDescrizione, this.grid);
        this.labelPrezzo = new JLabel("Inserisci prezzo (percentuale)");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelPrezzo, this.grid);
        this.textPrezzo = new JTextField(5);
        this.textPrezzo.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.textPrezzo, this.grid);
        this.labelInizio = new JLabel("Seleziona data inizio");
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelInizio, this.grid);
        this.dateStart = new JDateChooser();
        this.dateStart.setBackground(Color.YELLOW);
        this.dateStart.setDateFormatString("dd/MM/yyyy");
        this.dateStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.dateStart.setLocale(Locale.ITALY);
        this.dateStart.setPreferredSize(new Dimension(130, 20));
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.dateStart, this.grid);
        this.labelFine = new JLabel("Seleziona data fine");
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelFine, this.grid);
        this.dateEnd = new JDateChooser();
        this.dateEnd.setBackground(Color.YELLOW);
        this.dateEnd.setDateFormatString("dd/MM/yyyy");
        this.dateEnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.dateEnd.setLocale(Locale.ITALY);
        this.dateEnd.setPreferredSize(new Dimension(130, 20));
        this.grid.gridx = 1;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.dateEnd, this.grid);
        this.crea = new JButton("");
        this.crea.setIcon(new ImageIcon(this.ok));
        this.cancella = new JButton("");
        this.cancella.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exit));
        this.crea.addActionListener(e -> {
            int risp = JOptionPane.showConfirmDialog(this.frame,
                    "Sei sicuro di voler confermare la creazione di " + this.textDescrizione.getText() + " ?",
                    "Conferma", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.rispggIn = Integer.parseInt(this.dataGgInizio.format(this.dateStart.getDate()));
                this.rispmmIn = Integer.parseInt(this.dataMmInizio.format(this.dateStart.getDate()));
                this.rispaaIn = Integer.parseInt(this.dataAaInizio.format(this.dateStart.getDate()));
                this.rispggFi = Integer.parseInt(this.dataGgFine.format(this.dateEnd.getDate()));
                this.rispmmFi = Integer.parseInt(this.dataMmFine.format(this.dateEnd.getDate()));
                this.rispaaFi = Integer.parseInt(this.dataAaFine.format(this.dateEnd.getDate()));
                this.ing = LocalDate.of(this.rispaaIn, Month.of(this.rispmmIn), this.rispggIn);
                this.ex = LocalDate.of(this.rispaaFi, Month.of(this.rispmmFi), this.rispggFi);
                this.risp = this.textDescrizione.getText();
                this.rispPrezzo = Double.parseDouble(this.textPrezzo.getText());
            }
        });
        this.cancella.addActionListener(e -> {
            this.frame.setVisible(false);
            new Crea();
        });
        this.esci.addActionListener(e -> {
            this.frame.setVisible(false);
            new Scelte();
        });
        this.southPanel.add(this.cancella);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.crea);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new CreaStagione();
    }
}