package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.IModel;
import observer.LoginObserver;
/**
 * A Login Dialog to log into the application
 * @author lucadalseno
 *
 */
public class LoginDialog extends JDialog implements ObserverInterface<LoginObserver> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    public JTextField userTextField;
    public JPasswordField pswTextfield;
    private LoginObserver obs;
    private JLabel userLbl;
    private JLabel passLbl;
    private JPanel buttonPane;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * Create the dialog.
     * @param model 
     */
    public LoginDialog(final IModel model) {
        this.setTitle("Login");
        this.setResizable(false);
        setBounds(100, 100, 401, 162);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            userTextField = new JTextField();
            userTextField.setBounds(133, 6, 134, 28);
            contentPanel.add(userTextField);
            userTextField.setColumns(10);
        }
        {
            userLbl = new JLabel("Username");
            userLbl.setBounds(23, 12, 84, 16);
            contentPanel.add(userLbl);
        }
        {
            passLbl = new JLabel("Password");
            passLbl.setBounds(23, 56, 68, 16);
            contentPanel.add(passLbl);
        }
        
        pswTextfield = new JPasswordField();
        pswTextfield.setBounds(133, 50, 134, 28);
        contentPanel.add(pswTextfield);
        pswTextfield.setColumns(10);
        {
            buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(e->{
                    if(obs.doLogin(userTextField.getText(),new String( pswTextfield.getPassword()))){
                		this.setVisible(false);
                	} else {
                            JOptionPane.showMessageDialog(this, "Login Error", "Error",
                                    JOptionPane.ERROR_MESSAGE);                    
                	}
                });
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(e->{
                	    this.setVisible(false);
                });
            }
        }
    }
  
    public void attachObserver(LoginObserver observer){
        this.obs = observer;
    }
}