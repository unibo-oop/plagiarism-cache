package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import exceptions.PasswordException;

/**
 * The class to initialize passwords.
 */
public class SetPasswordImpl extends JDialog implements SetPassword {

    private static final long serialVersionUID = -7992526992229229393L;

    private static final int COLS = 3;
    private static final int ROWS = 2;
    private static final String ERROR_MESSAGE = "Invalid Input";
    private static final String OWNER_TITLE = "Set password for the owner";
    private static final String STAFF_TITLE = "Set password for the staff";

    private final JButton submit = new JButton("submit");
    private final JLabel textOne = new JLabel("Enter password:");
    private final JLabel textTwo = new JLabel("Re-enter password:");
    private final JLabel passwordError = new JLabel(ERROR_MESSAGE);
    private final JLabel verifyPasswordError = new JLabel(ERROR_MESSAGE);
    private final JPasswordField password = new JPasswordField(15);
    private final JPasswordField verifyPassword = new JPasswordField(15);

    /**
     * Build the standard GUI to setting passwords.
     * This constructor is without the application of method setVisible;
     * if you want that this GUI became visible, call one method of this class,
     * to associate an action listener at submit button.
     */
    public SetPasswordImpl() {
        super();

        //Create a panel and add the submit button to it
        final JPanel panel = new JPanel();
        panel.add(this.submit);

        //Set layout and background color for the dialog
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        //Add component to dialog
        this.getContentPane().add(this.centralPanel(), BorderLayout.CENTER);
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        //Fix last setting
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
    }

    /**
     * @return the panel to set as central panel.
     */
    private JPanel centralPanel() {
        final JPanel centralPanel = new JPanel();

        //Set layout for panel
        centralPanel.setLayout(new GridLayout(ROWS, COLS));

        //Add component to dialog
        centralPanel.add(this.textOne);
        centralPanel.add(this.password);
        centralPanel.add(this.passwordError);
        centralPanel.add(this.textTwo);
        centralPanel.add(this.verifyPassword);
        centralPanel.add(this.verifyPasswordError);

        //Fix passwordError and verifyPasswordError's setting
        this.passwordError.setForeground(Color.RED);
        this.verifyPasswordError.setForeground(Color.RED);
        this.passwordError.setVisible(false);
        this.verifyPasswordError.setVisible(false);

        return centralPanel;
    }

    @Override
    public void listenerForOwner() {
        this.submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final String pass = new String(password.getPassword());

                if (!pass.isEmpty() && pass.equals(new String(verifyPassword.getPassword()))) {
                    try {
                        //Set the owner password and this GUI not visible
                        ViewImpl.getController().insertPasswordForOwner(new String(password.getPassword()));
                        setVisible(false);
                    } catch (PasswordException e) {
                        passwordWrong();
                    }
                } else {
                    passwordWrong();
                }
            }
        });
        //Set dialog's title and position and the GUI's visibility
        this.setTitle(OWNER_TITLE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void listenerForStaff() {
        this.submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final String pass = new String(password.getPassword());

                if (!pass.isEmpty() && pass.equals(new String(verifyPassword.getPassword()))) {
                    try {
                        //Set the staff password and this GUI not visible
                        ViewImpl.getController().insertPasswordForStaff(new String(password.getPassword()));
                        setVisible(false);
                    } catch (PasswordException e) {
                        passwordWrong();
                    }
                } else {
                    passwordWrong();
                }
            }
        });
        //Set dialog's title and position and the GUI's visibility
        this.setTitle(STAFF_TITLE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method set visible the error label and null the text of TextField if password is wrong.
     */
    private void passwordWrong() {
        this.password.setText(null);
        this.verifyPassword.setText(null);
        this.passwordError.setVisible(true);
        this.verifyPasswordError.setVisible(true);
    }
}
