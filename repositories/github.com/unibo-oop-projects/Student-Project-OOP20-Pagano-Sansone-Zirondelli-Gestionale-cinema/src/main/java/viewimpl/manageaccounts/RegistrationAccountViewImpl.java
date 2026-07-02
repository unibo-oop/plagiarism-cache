package viewimpl.manageaccounts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import controller.manageaccounts.AccountsController;
import utilities.Account;
import utilities.TypeAccount;
import utilitiesimpl.ViewSettings;
import utilitiesimpl.AccountImpl;
import view.manageaccounts.RegistrationAccountView;

import java.awt.event.*;

/**
 * Implements registration view.
 */
public class RegistrationAccountViewImpl implements RegistrationAccountView{
    //GRID BAG LAYOUT + FLOW LAYOUT

    private static final String FRAME_NAME = "Registration";
    private static final double PROPORTION = 1.15;
    public static final String TITLE = "Add account";
    public static final String USERNAME_STRING = "Username:";
    public static final String NAME_STRING = "Name:";
    public static final String PASSWORD_STRING = "Password:";
    public static final String SURNAME_STRING = "Surname:";
    public static final String SECONDPWD_STRING = "Repeat Password:";
    public static final String TYPE_STRING = "Type:";
    
    private static final int HORIZONTAL = 500;
    private static final int VERTICAL = 400;
    public static final int SPACE = 5;
    
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;

    //components
    private final JLabel title = new JLabel(TITLE); 
    private final JLabel username = new JLabel(USERNAME_STRING);
    private final TextField textUsername = new TextField("Username", 12);
    private final JLabel name = new JLabel(NAME_STRING);
    private final TextField textName = new TextField("Name", 12);
    private final JLabel surname = new JLabel(SURNAME_STRING);
    private final TextField textSurname = new TextField("Surname", 12);
    private final JLabel password = new JLabel(PASSWORD_STRING);
    private final JPasswordField textPassword = new JPasswordField("Password", 12);
    private final JLabel secondPwd = new JLabel(SECONDPWD_STRING);
    private final JPasswordField textSecondPwd = new JPasswordField("Repeat Password", 12);
    private final JLabel isAdmin = new JLabel(TYPE_STRING);

    private final String [] stringType = new String [] {"Administrator", "Operator"};
    private final JComboBox type = new JComboBox<String>(stringType);


    private final JButton save = new JButton("Save");
    private final JButton cancel = new JButton("Cancel"); 
    private final JButton reset = new JButton("Reset");

    private AccountsController observer;


    // real dimension of the screen
    private static final  double PROPORTION_HEIGHT = 2;
    private static final  double PROPORTION_WIDTH = 4;
    private final int screenWidth = (int) ViewSettings.DIMENSION_WIDTH_SCREEN;
    private final int screenHeight = (int) ViewSettings.DIMENSION_HEIGTH_SCREEN;
    private final int frameWidth = (int) (screenWidth / PROPORTION_WIDTH);
    private final int frameHeight = (int) (screenHeight / PROPORTION_HEIGHT);

    /**
     * Constructor for the view registration Account.
     */
    public RegistrationAccountViewImpl() {

        //I create the frame and set the title and other properties
        this.frame = new JFrame();
        frame.setTitle(FRAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel pWestInternal = new JPanel(new GridBagLayout()); // Griglia flessibile
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(SPACE, SPACE, SPACE, SPACE);
        cnst.fill = GridBagConstraints.HORIZONTAL;

        //I create the secondary panels for the various parts and add the components
        final JPanel pNorth = new JPanel(new FlowLayout());

        pNorth.add(title, cnst);

        cnst.gridy++; //next line

        pWestInternal.add(username, cnst);
        pWestInternal.add(textUsername, cnst);
        cnst.gridy++;


        pWestInternal.add(name, cnst);
        pWestInternal.add(textName, cnst);
        cnst.gridy++; 
        pWestInternal.add(surname, cnst);
        pWestInternal.add(textSurname, cnst);
        cnst.gridy++; 
        pWestInternal.add(password, cnst);
        pWestInternal.add(textPassword, cnst);
        cnst.gridy++; 
        pWestInternal.add(secondPwd, cnst);
        pWestInternal.add(textSecondPwd, cnst);
        cnst.gridy++;

        pWestInternal.add(isAdmin, cnst);
        pWestInternal.add(type, cnst);
        cnst.gridy++;

        final JPanel pWest = new JPanel(new FlowLayout());
        pWest.add(pWestInternal);


        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));


        pSouth.add(save);
        pSouth.add(reset);
        pSouth.add(cancel);

        frame.add(pWest, BorderLayout.CENTER);
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(pSouth, BorderLayout.SOUTH);



       //method to remove descriptive writing
        textUsername.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                if ("Username".equals(textUsername.getText())) { 
                        textUsername.setText("");
                }
            }
            public void focusLost(final FocusEvent e) {
            }
        });

       //method to remove descriptive writing
        textPassword.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                if ("Password".equals(textPassword.getText())) { 
                    textPassword.setText("");
                }
            }

            public void focusLost(final FocusEvent e) {
            }
        });

       //method to remove descriptive writing
        textName.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                if ("Name".equals(textName.getText())) { 
                    textName.setText("");
                }
            }
            public void focusLost(final FocusEvent e) {
            }
        });

       //method to remove descriptive writing
        textSurname.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                if ("Surname".equals(textSurname.getText())) { 
                    textSurname.setText("");
                }
            }
            public void focusLost(final FocusEvent e) {
            }
        });

      //method to remove descriptive writing
        textSecondPwd.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) {
                if ("Repeat Password".equals(textSecondPwd.getText())) { 
                    textSecondPwd.setText("");
                }
            }
            public void focusLost(final FocusEvent e) {
            }
        });

        //method for returning to the previous page
        cancel.addActionListener(event -> {
            frame.setVisible(false);
            observer.showManagementAccountView();
         });


        //method to remove writing
        reset.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent e) { 
                        textUsername.setText("");
                        textName.setText("");
                        textSurname.setText("");
                        textPassword.setText("");
                        textSecondPwd.setText("");
            }

            public void focusLost(final FocusEvent e) {
            }
        });

      //method to add new account
        save.addActionListener(event -> {
            if (this.checkAccount()) {
                TypeAccount typeAccount;
                if (type.getSelectedItem().equals("Administrator")) {
                    typeAccount = TypeAccount.ADMINISTRATOR;
                } else {
                    typeAccount = TypeAccount.OPERATOR;
                }

                final String checkName = textName.getText();
                if (Pattern.matches("[a-zA-Z]+", checkName)) {
                    final String checkSurname = textSurname.getText();
                    if (Pattern.matches("[a-zA-Z]+", checkSurname)) {
                        Account account = new AccountImpl(textName.getText(), textSurname.getText(), textUsername.getText(), textPassword.getText(), typeAccount);
                        this.observer.addAccount(account);
                        frame.setVisible(false);
                        this.observer.showManagementAccountView();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please insert a Surname without number");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please insert a Name without number");
                }

            }


        });

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(HORIZONTAL, VERTICAL));
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final AccountsController observer) {
        this.observer = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        textUsername.setText("Username");
        textName.setText("Name");
        textSurname.setText("Surname");
        textPassword.setText("Password");
        textSecondPwd.setText("Repeat Password"); 
    }

    private boolean checkAccount() {
        Set<Account> setAccount = observer.getAccounts();
        if (!(textPassword.getText()).equals(textSecondPwd.getText())) {
            JOptionPane.showMessageDialog(frame, "Password doesn't match", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (setAccount.stream().filter(a -> a.getUsername().equals(textUsername.getText())).findAny().isPresent()) {
            JOptionPane.showMessageDialog(frame, "Username already present", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String user = "";
        try {
            user = textUsername.getText();
            if (user.isEmpty()) {
                throw new IllegalArgumentException();
            }
          } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(frame, "Please insert a username");
            return false;
          }

        String name = "";
        try {
            name = textName.getText();
            if (name.isEmpty()) {
                throw new IllegalArgumentException();
            }
          } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(frame, "Please insert a name");
            return false;
          }

        String surname = "";
        try {
            surname = textSurname.getText();
            if (surname.isEmpty()) {
                throw new IllegalArgumentException();
            }
          } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(frame, "Please insert a surname");
            return false;
          }

        String password = "";
        try {
            password = textPassword.getText();
            if (password.isEmpty()) {
                throw new IllegalArgumentException();
            }
          } catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(frame, "Please insert a password");
            return false;
          }

        return true;
    }

}