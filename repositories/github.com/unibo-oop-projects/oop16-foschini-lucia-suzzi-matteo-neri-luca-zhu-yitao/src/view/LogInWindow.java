package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import controller.LogInControllerImpl;
import controller.PlumberControllerImpl;
import model.pkglevels.ImageLoaderImpl;
import model.pkgplayer.Player;
import model.pkgplayer.PlayerImpl;

/**
 * Login window, implements observer so it can be notified.
 * 
 * 
 *
 */
public class LogInWindow extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2310048579636825382L;

    private final JTextField tfUsername;
    private final JPasswordField tfPassword;
    private final JLabel niubPlumberLogo = new JLabel();

    private final JButton btnLogin;

    private final int playerScore = 0;
    private Player currP;

    private final LogInControllerImpl controller;

    /**
     * Class constructor.
     * 
     */
    public LogInWindow() {

        final JPanel loginPanel = new JPanel(new GridBagLayout());
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        niubPlumberLogo.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("PlumberLogo.jpeg")));
        final GridBagConstraints cs = new GridBagConstraints();
        mainPanel.add(niubPlumberLogo, BorderLayout.NORTH);

        final JLabel lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        loginPanel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;

        loginPanel.add(tfUsername, cs);

        final JLabel lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        loginPanel.add(lbPassword, cs);

        tfPassword = new JPasswordField(20);

        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        loginPanel.add(tfPassword, cs);
        loginPanel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Login");
        final JButton btnRegister = new JButton("Register");
        final JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnRegister);
        this.getContentPane().add(niubPlumberLogo, BorderLayout.NORTH);
        getContentPane().add(loginPanel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        this.getRootPane().setDefaultButton(btnLogin);
        this.setVisible(true);
        controller = LogInControllerImpl.getLICController();
        PlumberControllerImpl.centreWindow(this);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                obtainInputs();

                if (controller.check(currP) == 0) {

                    update(LogInControllerImpl.getLICController().readData(currP, false,
                            ImageLoaderImpl.getLoaderInstance().getLoginFile()));

                }

            }
        });
        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                obtainInputs();

                if (controller.check(currP) == 0 && controller.registerPlayerData(currP,
                        ImageLoaderImpl.getLoaderInstance().getLoginFile()) == 3) {
                    update(3);
                    btnLogin.setEnabled(true);
                } else {
                    update(1);
                }

            }
        });
    }

    private void obtainInputs() {
        final String inputName = tfUsername.getText(), inputPw = getPassword(tfPassword.getPassword());

        this.currP = new PlayerImpl(inputName, inputPw, 1, playerScore);
    }

    /**
     * Method used to retrieve the user password.
     * 
     * @param pw
     *            char array
     * @return the string that references the password
     */
    public String getPassword(final char[] pw) {
        return new String(pw);

    }

    /**
     * Used to clear the input textFields.
     * 
     */
    public void clearInputFields() {
        tfUsername.setText("");
        tfPassword.setText("");
    }

    /**
     * Used to show a message in a JoptionPane.
     * 
     * @param message
     *            message to show
     */
    public void displayMessage(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Method used to update the GUI.
     * 
     * @param input
     *            chooser value
     */
    public void update(final int input) {

        switch (input) {

        case 0: {

            displayMessage("Accesso effettuato con successo...Benvenuto: " + currP.getName());
            clearInputFields();

            new GameWindow(currP);

            this.dispose();
            break;

        }
        case 1: {
            displayMessage("Utente già presente con lo stesso nome, cambia username");
            clearInputFields();
            break;

        }
        case 2: {
            displayMessage("Utente non presente, registrati prima di fare il login");

            break;
        }
        case 3: {
            displayMessage("Utente registrato con successo");
            clearInputFields();
            new GameWindow(currP);
            this.dispose();
            break;
        }
        default:
            break;

        }

    }

}
