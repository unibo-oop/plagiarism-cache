package secretary.modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.common.base.Optional;

import hotelmaster.database.login.LoginManager;
import hotelmaster.database.login.LoginManagerImpl;
import hotelmaster.login.AccountLevel;
import secretary.Modifica;
import secretary.mainview.SceltaOpzione;

public class ModificaPassword {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private JTextField userMail;
    private JPasswordField oldPw;
    private JPasswordField newPw;
    private JLabel labelMail;
    private JLabel labelOld;
    private JLabel labelNew;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private GridBagConstraints grid;
    private LoginManager loginManager;
    private Dimension screenSize;
    private Image ok;
    private Image exit;
    private Image backIcon;
    private String user;
    private String oldpw;
    private String newpw;

    public ModificaPassword() {
        this.loginManager = new LoginManagerImpl();
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(600, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((this.screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.ok = new ImageIcon(this.getClass().getResource("/icons/ok.png")).getImage();
        this.exit = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.ok));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exit));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setBackground(Color.CYAN);
        this.grid = new GridBagConstraints();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.CYAN);
        this.labelMail = new JLabel("Inserisci il tuo username");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelMail, this.grid);
        this.userMail = new JTextField(20);
        this.userMail.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.userMail, this.grid);
        this.labelOld = new JLabel("Inserisci vecchia password");
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelOld, this.grid);
        this.oldPw = new JPasswordField(20);
        this.oldPw.setBackground(Color.YELLOW);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.oldPw, this.grid);
        this.labelNew = new JLabel("Inserisci nuova password");
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.labelNew, this.grid);
        this.newPw = new JPasswordField(20);
        this.newPw.setBackground(Color.yellow);
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.insets = new Insets(10, 10, 10, 10);
        this.panel.add(this.newPw, this.grid);
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.conferma.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare ?", "Conferma", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.user = this.userMail.getText();
                this.oldpw = new String(this.oldPw.getPassword());
                this.newpw = new String(this.newPw.getPassword());
                Optional<AccountLevel> opt = this.loginManager.changePassword(this.user, this.oldpw, this.newpw);
                if (opt.isPresent()) {
                    JOptionPane.showMessageDialog(frame, "Modifica password eseguita con successo!");

                } else {
                    JOptionPane.showMessageDialog(this.frame,
                            "Qualcosa è andato storto nella modifica della password, riprova per favore",
                            "Errore modifica password", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.annulla.addActionListener(e -> {
            this.frame.setVisible(false);
            new Modifica();
        });
        this.esci.addActionListener(e -> {
            this.frame.setVisible(false);
            new SceltaOpzione();
        });
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new ModificaPassword();
    }
}
