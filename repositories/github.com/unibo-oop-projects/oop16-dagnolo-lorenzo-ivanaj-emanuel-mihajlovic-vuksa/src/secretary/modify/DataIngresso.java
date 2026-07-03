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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import secretary.LogIn;
import secretary.Modifica;
import secretary.mainview.SceltaOpzione;

public class DataIngresso {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JButton indietro;
    private JButton ok;
    private JButton ok2;
    private JButton esci;
    private Image backIcon;
    private Image okIcon;
    private Dimension screenSize;
    private JLabel label;
    private JLabel labelInfo;
    private JLabel label2;
    private JLabel labelInserimento;
    private JTextField text;
    private Image exitIcon;
    private SceltaOpzione sceltaOpzione;
    private LogIn log;
    private Modifica modifica;
    private JDateChooser cal;
    private DateFormat nuovogg;
    private DateFormat nuovomm;
    private DateFormat nuovoaa;
    private Integer gg;
    private Integer mm;
    private Integer aa;
    private LocalDate local;
    private GridBagConstraints grid;

    public DataIngresso() {
        this.frame = new JFrame("Modifica durata soggiorno");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);

        this.frame.setSize(new Dimension(400, 250));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.nuovogg = new SimpleDateFormat("dd");
        this.nuovomm = new SimpleDateFormat("MM");
        this.nuovoaa = new SimpleDateFormat("yyyy");
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.label = new JLabel("Data prevista di ingresso ----> ");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label, this.grid);
        this.labelInfo = new JLabel("data prevista"); // qui inserisco dal data
                                                      // base la data prevista
                                                      // di uscita
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelInfo, this.grid);
        this.label2 = new JLabel("Scegli nuova data ingresso");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.label2, this.grid);
        this.cal = new JDateChooser();
        this.cal.setDateFormatString("dd/mm/aaaa");
        this.cal.setLocale(Locale.ITALY);
        this.cal.setDateFormatString("dd/MM/yyyy");
        this.cal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.cal.setPreferredSize(new Dimension(130, 20));
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.cal, this.grid);
        this.ok = new JButton("");
        this.ok.setIcon(new ImageIcon(this.okIcon));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.ok.addActionListener(a -> {
            if (this.cal.getDate().toString().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Non hai selezionato una data valida", "Errore data",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi confermare la nuova data scelta?",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.gg = Integer.parseInt(this.nuovogg.format(this.cal.getDate()));
                    this.mm = Integer.parseInt(this.nuovomm.format(this.cal.getDate()));
                    this.aa = Integer.parseInt(this.nuovoaa.format(this.cal.getDate()));
                    System.out.println(this.mm);
                    this.local = LocalDate.of(this.aa, this.mm, this.gg);
                    this.frame.setVisible(false);
                    this.sceltaOpzione = new SceltaOpzione();
                }
            }
        });
        this.indietro.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Tornare indietro?", "", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new ModificaDataIngresso("Modifica data ingresso");
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi davvero uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new SceltaOpzione();
            }
        });
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.ok);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
}
