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
 * The class to verify passwords.
 */
public class VerifyPasswordImpl extends JDialog implements VerifyPassword {

    private static final long serialVersionUID = 6142786591543372289L;

    private static final int COLS = 3;
    private static final int ROWS = 1;
    private static final String TITLE = "Enter the correct password";

    private final JButton submit = new JButton("Submit");
    private final JLabel text = new JLabel("Insert the correct password:");
    private final JLabel passwordError = new JLabel("Invalid Input");
    private final JPasswordField password = new JPasswordField(15);

    /**
     * Build the standard GUI to verify passwords.
     * This constructor is without the application of method setVisible;
     * if you want that this GUI became visible, call one method of this class,
     * to associate an action listener at submit button.
     */
    public VerifyPasswordImpl() {
        super();

        //Create a panel and add the submit button to it
        final JPanel panel = new JPanel();
        panel.add(this.submit);

        //Set layout and background color for dialog
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        //Add component to dialog
        this.getContentPane().add(this.centralPanel(), BorderLayout.CENTER);
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        //Fix last setting
        this.setTitle(TITLE);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
    }

    /**
     * @return the panel to set as central panel.
     */
    private JPanel centralPanel() {
        final JPanel centralPanel = new JPanel();

        //Set layout for panel
        centralPanel.setLayout(new GridLayout(ROWS, COLS));

        //Add component to panel
        centralPanel.add(this.text);
        centralPanel.add(this.password);
        centralPanel.add(this.passwordError);

        //Set passwordError's setting
        this.passwordError.setForeground(Color.RED);
        this.passwordError.setVisible(false);

        return centralPanel;
    }

    @Override
    public void listenerForOwner(final StartingView startingView) {
        this.submit.addActionListener(new ActionListener() {
            /*Verify the input password with owner's password, call the view's method roomManagement
            and set this GUI not visible*/

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ViewImpl.getController().insertPasswordForOwner(new String(password.getPassword()));
                    setVisible(false);
                    startingView.setVisible(false);
                    ViewImpl.getView().ownerMenu();
                } catch (PasswordException e1) {
                    passwordWrong();
                }
            }
        });
        //Set dialog's position and the GUI's visibility
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void listenerForStaff(final StartingView startingView) {
        this.submit.addActionListener(new ActionListener() {
            /*Verify the input password with staff's password, call the view's method setDiscount
            and set this GUI not visible*/

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ViewImpl.getController().insertPasswordForStaff(new String(password.getPassword()));
                    setVisible(false);
                    startingView.setVisible(false);
                    ViewImpl.getView().setDiscount(false);
                } catch (PasswordException e1) {
                    passwordWrong();
                }
            }
        });
        //Set dialog's position and the GUI's visibility
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method set visible the error label and null the text of TextField if password is wrong.
     */
    private void passwordWrong() {
        this.password.setText(null);
        this.passwordError.setVisible(true);
    }
}