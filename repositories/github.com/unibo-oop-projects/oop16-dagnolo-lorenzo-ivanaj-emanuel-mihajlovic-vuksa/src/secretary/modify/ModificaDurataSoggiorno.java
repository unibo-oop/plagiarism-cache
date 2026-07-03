package secretary.modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import hotelmaster.reservations.Client;
import hotelmaster.reservations.StayState;
import hotelmaster.structure.Hotel;
import secretary.LogIn;
import secretary.Modifica;
import secretary.mainview.SceltaOpzione;

public class ModificaDurataSoggiorno {
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

    public ModificaDurataSoggiorno() {
        this.frame = new JFrame("Modifica durata soggiorno");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setBackground(Color.CYAN);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.panel.setVisible(false);
        this.northPanel = new JPanel();
        this.northPanel.setBackground(Color.CYAN);
        this.frame.setSize(new Dimension(400, 350));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.nuovogg = new SimpleDateFormat("dd");
        this.nuovomm = new SimpleDateFormat("MM");
        this.nuovoaa = new SimpleDateFormat("yyyy");
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.text = new JTextField(5);
        this.text.setBackground(Color.yellow);
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.labelInfo = new JLabel("data prevista");
        this.label = new JLabel("Data prevista di uscita ----> ");
        this.label2 = new JLabel("Scegli nuova data");
        this.labelInserimento = new JLabel("Inserisci il numero della camera");
        this.cal = new JDateChooser();
        this.cal.setDateFormatString("dd/mm/aaaa");
        this.cal.setLocale(Locale.ITALY);
        this.cal.setBackground(Color.YELLOW);
        this.cal.setDateFormatString("dd/MM/yyyy");
        this.cal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.cal.setLocale(Locale.ITALY);
        this.cal.setPreferredSize(new Dimension(130, 20));
        this.ok2 = new JButton("Conferma");
        this.ok = new JButton("");
        this.ok.setIcon(new ImageIcon(this.okIcon));
        this.ok.setEnabled(false);
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.ok2.addActionListener(d -> {
            if (this.text.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Inserisci il numero di una camera", "Errore inserimento",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame,
                        "Vuoi confermare la modifica del soggiorno della camera " + this.text.getText() + " ?",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.ok.setEnabled(true);
                    this.northPanel.setVisible(false);
                    this.panel.setVisible(true);
                }
            }
        });
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
                this.modifica = new Modifica();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi davvero uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.log = new LogIn();
            }
        });
        this.panel.add(this.label);
        this.panel.add(this.labelInfo);
        this.panel.add(this.label2);
        this.panel.add(this.cal);
        this.southPanel.add(this.indietro);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.ok);
        this.northPanel.add(this.labelInserimento);
        this.northPanel.add(this.text);
        this.northPanel.add(this.ok2);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.northPanel, BorderLayout.NORTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new ModificaDurataSoggiorno();
    }
}