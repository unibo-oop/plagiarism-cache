package secretary;

/**
 /* Interfaccia di login, la mail  e la password sono provvisorie e succssivamente saranno scambiate con i dati
 * nel database, se la mail o la password è sbagliata comparira un JOptionPane di errore altrimenti si aprira 
 * la classe di sceltaOpzione.java
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.common.base.Optional;

import adminpackage.mainview.Crea;
import hotelmaster.database.login.LoginManager;
import hotelmaster.database.login.LoginManagerImpl;
import hotelmaster.login.AccountLevel;
import hotelmaster.login.Login;
import secretary.mainview.SceltaOpzione;

//import hotelmaster.login.LoginManager;
public class LogIn {
    private JFrame frame;
    private JPanel northPanel;
    private JPanel pannelloDati;
    private JPanel southPanel;
    private JPanel south;
    private JButton log;
    private JTextField text;
    private JTextField text2;
    private JPasswordField pass;
    private JLabel labelUtente;
    private JLabel labelPw;
    private JLabel titolo;
    private JLabel icona;
    private String rispMail;
    private String password;
    private SceltaOpzione scelta;
    private Login logManager;
    private Crea opz;
    private Dimension screenSize;
    private Image logg;
    private LoginManager loginmanager;
    private Optional<AccountLevel> account;
    private GridBagConstraints grid;

    public LogIn() {
        this.frame = new JFrame("Login");
        this.frame.setSize(500, 300);
        this.frame.setMinimumSize(new Dimension(200, 400));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.loginmanager = new LoginManagerImpl();
        this.northPanel = new JPanel(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.northPanel.setBackground(Color.cyan);
        this.pannelloDati = new JPanel(new GridBagLayout());
        this.pannelloDati.setBackground(Color.cyan);
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.cyan);
        this.logg = new ImageIcon(this.getClass().getResource("/icons/log.png")).getImage();
        this.log = new JButton("Login");
        this.titolo = new JLabel("Hotel Master");
        this.icona = new JLabel("");
        this.icona.setIcon(new ImageIcon(this.logg));
        this.southPanel.add(this.icona);
        this.southPanel.add(this.log);
        Font font = new Font("Baskerville", Font.HANGING_BASELINE, 60);
        this.titolo.setFont(font);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.northPanel.add(this.titolo, this.grid);
        this.labelUtente = new JLabel("Inserisci nome utente");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloDati.add(this.labelUtente, this.grid);
        this.text = new JTextField(20);
        this.text.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloDati.add(this.text, this.grid);
        this.labelPw = new JLabel("Inserisci password");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloDati.add(this.labelPw, this.grid);
        this.pass = new JPasswordField(20);
        this.pass.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.pannelloDati.add(this.pass, this.grid);
        this.log.addActionListener(e -> {
            this.password = new String(this.pass.getPassword());
            this.text.selectAll();
            this.rispMail = this.text.getText();
            this.account = this.loginmanager.logIn(this.rispMail, this.password);
            if (account.isPresent()) {
                switch (account.get()) {
                case ADMIN:
                    this.frame.setVisible(false);
                    this.opz = new Crea();
                    break;
                case SECRETARY:
                    this.scelta = new SceltaOpzione();
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Opsss, qualcosa e andato storto, riprova per favore",
                        "Errore login", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.frame.getContentPane().add(this.northPanel, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.pannelloDati);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);

        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new LogIn();
    }
}