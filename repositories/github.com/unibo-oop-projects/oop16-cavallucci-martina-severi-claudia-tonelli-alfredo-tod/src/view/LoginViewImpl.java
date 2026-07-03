package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.MainController;
import controller.UserInfo;
/**
 * 
 * Login Panel that permits user to enter in a personal profile.
 *
 */
public class LoginViewImpl extends JPanel implements LoginView, ActionListener {

    private final JLabel lblwelcome1;
    private final JLabel lblwelocome2;
    private final JLabel lblUsername;
    private final MainController observer;
    private final JLabel lblPassword;
    private final JLabel lblAnswer;
    private final JTextField txtUser;
    private final JPasswordField txtPassword;
    private final JFrame fr;
    private final JButton btnRegistration;
    private final JButton btnClean;
    private final JButton btnAccess;
    private final JButton btnModifedPsw;
    private final JButton btnDeleteUser;
    private final  JPanel contentPane1;
    private final JPanel firstPanel;
    private final JPanel secondPanel;
    private final JPanel thirdPanel;
    private final JPanel fourthPanel;
    private final JPanel welcomePanel;
    private final JPanel login;
    private final JPanel regist;
    private final EtchedBorder border;

    private final Color background1;
    private final Color background2;
    private final Font font1;
    private final JButton btnLogout;
    private static final long serialVersionUID = 1;
    private static final int DF1 = 13;
    private static final int DF2 = 10;

 
 /**
  * Class that implements the Login GUI.
  * @param ctrl
  *             the controller
  * @param frame
  *             the frame where the view is enabled. 
  */
    public LoginViewImpl(final MainController ctrl, final MainViewTabbedImpl frame) {
        super();
        this.observer = ctrl;
        this.fr = frame;
        this.background1 = new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(), ViewColor.light.getBlue()); 
        this.background2 = new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(), ViewColor.lightblue.getBlue()); 
        this.font1 = new Font("Euphemia", Font.BOLD, DF1);
        this.setBackground(this.background1);
        this.setLayout(new BorderLayout());
        this.contentPane1 = new JPanel(new GridLayout(4, 1));
        this.add(this.contentPane1, BorderLayout.NORTH);

        this.lblwelcome1 = new JLabel("Welcome!");
        this.lblwelocome2 = new JLabel("Log in to your calendar");
        this.lblwelocome2.setOpaque(true);
        this.lblwelcome1.setOpaque(true);
        this.lblwelcome1.setBackground(this.background1);
        this.lblwelocome2.setBackground(this.background1);
        this.lblwelcome1.setFont(this.font1);
        this.lblwelocome2.setFont(this.font1);
        this.lblwelcome1.setForeground(this.background2);
        this.lblwelocome2.setForeground(this.background2);
        this.contentPane1.setBackground(this.background1);

        this.lblUsername = new JLabel("Username:");
        this.lblUsername.setFont(this.font1);
        this.txtUser = new JTextField(DF2);

        this.lblPassword = new JLabel("Password: ");
        this.lblPassword.setFont(this.font1);
        this.txtPassword = new JPasswordField(DF2);

        this.btnClean = new JButton("Reset");
        this.btnClean.addActionListener(this);
        this.btnClean.setFont(this.font1);
        this.btnClean.setBackground(this.background1);
        this.btnAccess = new JButton("Login");
        this.btnAccess.addActionListener(this);
        this.btnAccess.setFont(this.font1);
        this.btnAccess.setBackground(this.background2);
        this.btnAccess.setEnabled(true);
        this.btnModifedPsw = new JButton("Change password");
        this.btnModifedPsw.addActionListener(this);
        this.btnModifedPsw.setFont(this.font1);
        this.btnModifedPsw.setBackground(this.background1);
        this.btnModifedPsw.setEnabled(true);

        this.btnDeleteUser = new JButton("Delete user");
        this.btnDeleteUser.addActionListener(this);
        this.btnDeleteUser.setFont(this.font1);
        this.btnDeleteUser.setBackground(this.background1);
        this.btnDeleteUser.setEnabled(true);

        this.btnLogout = new JButton("Logout");
        this.btnLogout.addActionListener(this);
        this.btnLogout.setFont(this.font1);
        this.btnLogout.setBackground(this.background1);
        this.btnLogout.setEnabled(true);

        this.lblAnswer = new JLabel("you are not registered?");
        this.lblAnswer.setFont(this.font1);

        this.btnRegistration = new JButton("Create account");
        this.btnRegistration.addActionListener(this);
        this.btnRegistration.setBackground(this.background2);
        this.btnRegistration.setFont(this.font1);

        this.welcomePanel = new JPanel();
        this.welcomePanel.setBackground(this.background1);
        this.welcomePanel.add(this.lblwelcome1);
        this.welcomePanel.add(this.lblwelocome2);

        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(this.background1);
        this.firstPanel.add(this.lblUsername);
        this.firstPanel.add(this.txtUser);
        this.secondPanel = new JPanel();
        this.secondPanel.setBackground(this.background1);
        this.secondPanel.add(this.lblPassword);
        this.secondPanel.add(this.txtPassword);

        this.thirdPanel = new JPanel();
        this.thirdPanel.setBackground(this.background1);
        this.thirdPanel.add(this.btnClean);
        this.thirdPanel.add(this.btnAccess);

        this.login = new JPanel(new GridLayout(3, 1));
        this.login.setBackground(this.background1);
        border  = new EtchedBorder(this.background1, this.background2);
        this.login.setBorder(new TitledBorder(border, "Access"));
        this.login.add(this.firstPanel, BorderLayout.NORTH);
        this.login.add(this.secondPanel, BorderLayout.NORTH);
        this.login.add(this.thirdPanel, BorderLayout.NORTH);
        this.regist = new JPanel();
        this.regist.setBackground(this.background1);
        this.regist.add(this.lblAnswer);
        this.regist.add(this.btnRegistration);
        this.fourthPanel = new JPanel();
        this.fourthPanel.setBackground(this.background1);
        this.fourthPanel.add(this.btnModifedPsw);
        this.fourthPanel.add(this.btnDeleteUser);
        this.fourthPanel.add(this.btnLogout);
        this.contentPane1.add(this.welcomePanel);
        this.contentPane1.add(this.login);
        this.contentPane1.add(this.regist);
        this.contentPane1.add(this.fourthPanel);

    }

    @Override
    public void clearLogin() {

        this.txtUser.setText("");
        this.txtPassword.setText("");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Object isPressed = e.getSource();

        if (isPressed == this.btnAccess) {
            this.observer.confirmLogin(convertMap());
            this.txtPassword.setText("");
        } else if (isPressed == this.btnClean) {
            this.clearLogin();
        } else if (isPressed == this.btnRegistration) {
            this.observer.registerUserCliecked();
        } else if (isPressed == this.btnModifedPsw) {
            this.observer.changePassword();
        } else if (isPressed == this.btnDeleteUser) {

            final Object[] options = {"Yes", "No"};
            final int cancel = JOptionPane.showOptionDialog(this,
                            "Really want to delete this account?\n", "Attention",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            options,
                            null);

             if (cancel == JOptionPane.YES_OPTION) {
                  this.observer.cancelUser();
                  this.observer.writeValues();
                  fr.setVisible(false);
                  fr.dispose();
                 } else {
                       ((JFrame) e.getSource()).setDefaultCloseOperation(
                       JFrame.DO_NOTHING_ON_CLOSE);
                }
        } else if (isPressed == this.btnLogout) {
            this.observer.logout();
            fr.setVisible(false);
        }
    }
    private Map<UserInfo, String> convertMap() {
        final Map<UserInfo, String> mapToPass = new HashMap<>();
        mapToPass.put(UserInfo.USERNAME, this.txtUser.getText());
        final String passText = new String(this.txtPassword.getPassword());
        mapToPass.put(UserInfo.PASSWORD, passText);
        return mapToPass;
    }

    @Override
    public void init() {
        this.btnAccess.setEnabled(true);
    }
}