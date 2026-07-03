package secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.reservations.Client;
import hotelmaster.reservations.DocumentType;
import hotelmaster.reservations.StayBuilder;
import hotelmaster.structure.Hotel;
import hotelmaster.utility.time.FixedPeriod;

/**
 * - Questa e la fase di registrazione o prenotazione, dove il segretario
 * inserisce i dati relativi al capo famiglia 1)Ci sono alcuni campi obbligatori
 * che se non vengono completati compare una JOption error che ti avvisa appunto
 * del fatto che non sono stati completati. 2)Ogni tipo di documento ha un certo
 * numero di caratteri, e quindi, se il numero di caratteri non corrisponde al
 * rispettivo documento, appare una JOption che avvisa che ce stato un errore
 * esempio: Passaporto: 9 caratteri (fra lettere e numeri) patente: 10 caratteri
 * (fra lettere e numeri) tessera sanitaria: 16 caratteri " " carta d'identita
 * (tradizionale): 7 caratteri carta d'identità (elettronica): 9 caratteri
 * permesso di soggiorno: 10 caratteri
 */
public class FaseRegistrazione {
    private JFrame frame;
    private JPanel pannelloPrenotazione;
    private JPanel sud;
    private JPanel nord;
    private JLabel labelNome;
    private JLabel labelCognome;
    private JLabel labelLuogo;
    private JLabel labelDocumento;
    private JLabel labelNumeroDocumento;
    private JLabel labelTelefono;
    private JLabel tipoDiSoggiorno;
    private JLabel labelDurataDelSoggiorno;
    private JLabel titolo;
    private JLabel uscita;
    private JLabel gg;
    private JButton continua;
    private JButton esci;
    private JTextField nome;
    private JTextField cognome;
    private JTextField data;
    private JTextField luogo;
    private JTextField giorno;
    private JTextField mese;
    private JTextField anno;
    private JTextField giornoUscita;
    private JTextField meseUscita;
    private JTextField annoUscita;
    private JComboBox documento;
    private JTextField numeroDocumento;
    private JTextField telefono;
    private JComboBox tipoSoggiorno;
    private JComboBox tipoPensione;
    private JTextField durataSoggiorno;
    private SceltaCamere scelta;
    private LogIn log;
    private String document;
    private String name;
    private String surname;
    private String birthPlace;
    private String docNumber;
    private String telNumber;
    private Integer daysOfHoliday;
    private String giorniSogg;
    private String boardType;
    private GridBagConstraints grid;
    private GridBagConstraints grid2;
    private Font font;
    private Dimension screenSize;
    private JDateChooser dateEnter;
    private JDateChooser dateExit;
    private SimpleDateFormat dataGgIngresso;
    private SimpleDateFormat dataMmIngresso;
    private SimpleDateFormat dataAaIngresso;
    private SimpleDateFormat dataGgUscita;
    private SimpleDateFormat dataMmUscita;
    private SimpleDateFormat dataAaUscita;
    private Integer ggIngresso;
    private Integer mmIngresso;
    private Integer aaIngresso;
    private Integer ggUscita;
    private Integer mmUscita;
    private Integer aaUscita;
    private Integer rispggEn;
    private Integer rispmmEn;
    private Integer rispaaEn;
    private Integer rispggEx;
    private Integer rispmmEx;
    private Integer rispaaEx;
    private LocalDate ing;
    private LocalDate ex;
    private JTextField text;
    private StayBuilder staybuilder;
    private List<String> isoCountry;
    private List<String> countries;
    private JComboBox<Object> paese;
    private String[] strDocuments;
    private String[] strBoard;

    /**
     * 
     */
    public FaseRegistrazione() {
        this.frame = new JFrame("Hotel Master - Registrazione/Prenotazione");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pannelloPrenotazione = new JPanel(new GridBagLayout());
        this.pannelloPrenotazione.setBackground(Color.cyan);
        this.nord = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.nord.setBackground(Color.cyan);
        this.sud = new JPanel();
        this.sud.setBackground(Color.CYAN);
        this.esci = new JButton("Esci");
        this.continua = new JButton("Continua");
        this.sud.add(this.esci);
        this.sud.add(this.continua);
        this.grid = new GridBagConstraints();
        this.grid2 = new GridBagConstraints();
        // Array di documenti e array di tipo pensione;
        final String[] strTipoDiSoggiorno = Hotel.instance().getPriceView(StayTypePriceDescriber.class).stream()
                .map(stayType -> stayType.getDescription()).collect(Collectors.toList())
                .toArray(new String[Hotel.instance().getPriceView(StayTypePriceDescriber.class).size()]);
        this.isoCountry = new ArrayList<>(Arrays.asList(Locale.getISOCountries()));
        this.countries = new ArrayList<>();
        for (String code : this.isoCountry) {
            Locale country = new Locale("", code);
            countries.add(country.getDisplayCountry());
        }
        this.dataGgIngresso = new SimpleDateFormat("dd");
        this.dataMmIngresso = new SimpleDateFormat("MM");
        this.dataAaIngresso = new SimpleDateFormat("yyyy");
        this.dataGgUscita = new SimpleDateFormat("dd");
        this.dataMmUscita = new SimpleDateFormat("MM");
        this.dataAaUscita = new SimpleDateFormat("yyyy");
        this.titolo = new JLabel("Registrazione / Prenotazione");
        this.font = new Font("Baskerville", Font.HANGING_BASELINE, 50);
        this.titolo.setFont(font);
        this.nord.add(this.titolo);
        this.labelNome = new JLabel("Inserisci nome* ");
        this.labelNome.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelNome, this.grid);
        this.nome = new JTextField(20);
        this.nome.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.nome, this.grid);
        this.labelCognome = new JLabel("Inserisci Cognome* ");
        this.labelCognome.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 2;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelCognome, this.grid);
        this.cognome = new JTextField(20);
        this.cognome.setBackground(Color.yellow);
        this.grid.gridx = 3;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.cognome, this.grid);
        this.labelLuogo = new JLabel("Inserisci luogo di nascita ");
        this.labelLuogo.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelLuogo, this.grid);
        this.paese = new JComboBox<>(this.countries.toArray());
        this.paese.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.paese, this.grid);
        this.labelDocumento = new JLabel("Inserisci tipo di documento* ");
        this.labelDocumento.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 2;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelDocumento, this.grid);
        this.documento = new JComboBox<>();
        this.documento.setBackground(Color.yellow);
        this.grid.gridx = 3;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.documento, this.grid);
        this.labelNumeroDocumento = new JLabel("Inserisci numero documento* ");
        this.labelNumeroDocumento.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelNumeroDocumento, this.grid);
        this.numeroDocumento = new JTextField(20);
        this.numeroDocumento.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.numeroDocumento, this.grid);
        this.labelTelefono = new JLabel("Inserisci Numero di telefono ");
        this.labelTelefono.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 2;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelTelefono, this.grid);
        this.telefono = new JTextField(20);
        this.telefono.setBackground(Color.yellow);
        this.grid.gridx = 3;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.telefono, this.grid);
        this.tipoDiSoggiorno = new JLabel("Scegli il tipo di soggiorno*");
        this.tipoDiSoggiorno.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.tipoDiSoggiorno, this.grid);
        this.tipoSoggiorno = new JComboBox<>(strTipoDiSoggiorno);
        this.tipoSoggiorno.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.tipoSoggiorno, this.grid);
        this.labelDurataDelSoggiorno = new JLabel("Scegli la durata del soggiorno");
        this.labelDurataDelSoggiorno.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 2;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.labelDurataDelSoggiorno, this.grid);
        this.durataSoggiorno = new JTextField(5);
        this.durataSoggiorno.setBackground(Color.yellow);
        this.grid.gridx = 3;
        this.grid.gridy = 3;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloPrenotazione.add(this.durataSoggiorno, this.grid);
        this.gg = new JLabel("Inserisci data di ingresso*");
        this.gg.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 0;
        this.grid.gridy = 4;
        this.grid.insets = new Insets(80, 10, 10, 10);
        this.pannelloPrenotazione.add(this.gg, this.grid);
        this.dateEnter = new JDateChooser();
        this.dateEnter.setBackground(Color.YELLOW);
        this.dateEnter.setDateFormatString("dd/MM/yyyy");
        this.dateEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.dateEnter.setLocale(Locale.ITALY);
        this.dateEnter.setPreferredSize(new Dimension(130, 20));
        this.grid.gridx = 1;
        this.grid.gridy = 4;
        this.pannelloPrenotazione.add(this.dateEnter, this.grid);
        this.uscita = new JLabel("Inserisci data di uscita*");
        this.uscita.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        this.grid.gridx = 2;
        this.grid.gridy = 4;
        this.grid.insets = new Insets(80, 10, 10, 10);
        this.pannelloPrenotazione.add(this.uscita, this.grid);
        this.dateExit = new JDateChooser();
        this.dateExit.setDateFormatString("dd/MM/yyyy");
        this.dateExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.dateExit.setLocale(Locale.ITALY);
        this.dateExit.setPreferredSize(new Dimension(130, 20));
        this.grid.gridx = 3;
        this.grid.gridy = 4;
        this.pannelloPrenotazione.add(this.dateExit, this.grid);
        this.continua.addActionListener(a -> {
            if (this.numeroDocumento.getText().length() == 0 || this.nome.getText().length() == 0
                    || this.cognome.getText().length() == 0 || this.telefono.getText().length() == 0) {
                JOptionPane.showMessageDialog(this.frame, "Completa tutti i campi con *", "Errore registrazione",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di voler confermare?", "Conferma",
                        JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.name = this.nome.getText();
                    this.surname = this.cognome.getText();
                    this.document = this.documento.getSelectedItem().toString();
                    this.docNumber = this.numeroDocumento.getText();
                    this.telNumber = this.telefono.getText();
                    this.boardType = this.tipoSoggiorno.getSelectedItem().toString();
                    this.giorniSogg = this.durataSoggiorno.getText();
                    this.numeroDocumento.setBackground(Color.GREEN);
                    this.birthPlace = this.paese.getSelectedItem().toString();
                    this.rispggEn = Integer.parseInt(this.dataGgIngresso.format(this.dateEnter.getDate()));
                    this.rispmmEn = Integer.parseInt(this.dataMmIngresso.format(this.dateEnter.getDate()));
                    this.rispaaEn = Integer.parseInt(this.dataAaIngresso.format(this.dateEnter.getDate()));
                    this.rispggEx = Integer.parseInt(this.dataGgUscita.format(this.dateExit.getDate()));
                    this.rispmmEx = Integer.parseInt(this.dataMmUscita.format(this.dateExit.getDate()));
                    this.rispaaEx = Integer.parseInt(this.dataAaUscita.format(this.dateExit.getDate()));
                    this.ing = LocalDate.of(this.rispaaEn, Month.of(this.rispmmEn), this.rispggEn);
                    this.ex = LocalDate.of(this.rispaaEx, Month.of(this.rispmmEx), this.rispggEx);
                    this.staybuilder = StayBuilder.create();
                    this.staybuilder.setClient(Client.create("" + this.name + " " + this.surname, this.birthPlace,
                            Hotel.instance().getDocuments().stream()
                                    .filter(document -> document.getDescription().equals(this.document)).findAny()
                                    .get(),
                            this.docNumber, this.telNumber));
                    this.staybuilder.setDates(FixedPeriod.of(this.ing, this.ex));
                    try {
                        this.staybuilder.setType(Hotel.instance().getPriceView(StayTypePriceDescriber.class).stream()
                                .filter(type -> type.getDescription().equals(this.boardType)).findAny().get());
                    } catch (MissingEntityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    // TODO: supplementi
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    this.scelta = new SceltaCamere(this.staybuilder);
                }
            }
        });
        this.esci.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                this.log = new LogIn();
            }
        });

        // this.pannelloPrenotazione.add(this.annoUscita, this.grid2);
        this.frame.getContentPane().add(this.sud, BorderLayout.SOUTH);
        this.frame.getContentPane().add(this.nord, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.pannelloPrenotazione, BorderLayout.WEST);
        this.frame.pack();
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new FaseRegistrazione();
    }

}