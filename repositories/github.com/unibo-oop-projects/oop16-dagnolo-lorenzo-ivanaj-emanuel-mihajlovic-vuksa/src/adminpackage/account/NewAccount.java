package adminpackage.account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import adminpackage.mainview.Crea;
import adminpackage.mainview.Scelte;
import hotelmaster.database.admin.AccountManager;
import hotelmaster.database.admin.AccountManagerImpl;
import hotelmaster.exceptions.AccountException;
import secretary.LogIn;

/* *
 * Qui l'admin crea un nuovo account inserendo una mail e una password. Se non inserisce niente oppure
 * inserisce i dati solo in una textfield e nell'altra no, e preme il bottone
 * conferma, uscira una JOption di errore. Il bottone indietro torna al frame opzioni mentre il bottone esci ti fa 
 * tornare alla schermata di login
 * */
public class NewAccount {
    private JFrame frame;
    private JPanel panel;
    private JPanel southpanel;
    private JPanel centerPanel;
    private JButton conferma;
    private JButton indietro;
    private JButton esci;
    private JTextField textMail;
    private JTextField textPw;
    private JLabel labelMail;
    private JLabel labelPw;
    private Dimension screenSize;
    private String rispMail;
    private String rispPw;
    private Crea opz;
    private LogIn log;
    private Image adduser;
    private Image backIcon;
    private Image exitIcon;
    private AccountManager accountmanager;

    public NewAccount() {
        this.frame = new JFrame("Hotel Master - Crea Account");
        this.frame.setSize(new Dimension(500, 200));
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.accountmanager = new AccountManagerImpl();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridLayout(2, 2));
        this.panel.setBackground(Color.CYAN);
        this.southpanel = new JPanel();
        this.southpanel.setBackground(Color.CYAN);
        this.centerPanel = new JPanel();
        this.centerPanel.setBackground(Color.CYAN);
        this.adduser = new ImageIcon(this.getClass().getResource("/icons/addUser.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.textMail = new JTextField(20);
        this.textMail.setBackground(Color.YELLOW);
        this.labelMail = new JLabel("Inserisci nome utente da creare");
        this.textPw = new JTextField(20);
        this.textPw.setBackground(Color.YELLOW);
        this.labelPw = new JLabel("Inserisci una password");
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.adduser));
        this.indietro = new JButton("");
        this.indietro.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.conferma.addActionListener(a -> {

            if (this.textMail.getText().length() == 0 || this.textPw.getText().length() == 0) {
                JOptionPane.showMessageDialog(frame, "Opsss, qualcosa e andato storto, riprova per favore",
                        "Errore creazione account", JOptionPane.ERROR_MESSAGE);
            } else {
                int risp = JOptionPane.showConfirmDialog(this.frame, "Vuoi confermare la creazione di questo account",
                        "Conferma", JOptionPane.YES_OPTION);
                if (risp == JOptionPane.YES_OPTION) {
                    this.rispMail = (String) this.textMail.getText();
                    this.rispPw = (String) this.textPw.getText();
                    try {
                        this.accountmanager.addUserAccount(this.rispMail, this.rispPw);
                    } catch (AccountException e) {
                        JOptionPane.showMessageDialog(frame, "Account gia esistente", "Errore creazione account",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.indietro.addActionListener(b -> {
                this.frame.setVisible(false);
                this.opz = new Crea();
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                new Scelte();
            }
        });
        this.southpanel.add(this.indietro);
        this.southpanel.add(this.esci);
        this.southpanel.add(this.conferma);
        this.panel.add(this.labelMail);
        this.panel.add(this.textMail);
        this.panel.add(this.labelPw);
        this.panel.add(this.textPw);
        this.frame.getContentPane().add(this.centerPanel);
        this.frame.getContentPane().add(this.panel, BorderLayout.NORTH);
        this.frame.getContentPane().add(this.southpanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new NewAccount();
    }
}