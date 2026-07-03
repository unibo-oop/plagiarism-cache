package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.AddUserController;
import controller.UserInfo;

/**
 * 
 * class for enter a new User.
 *
 */
public class AddUserViewImpl extends JFrame implements AddUserView, ActionListener {

    private final JLabel lblwelcome1;
    private final JLabel lblUsername;
    private AddUserController observer;
    private final JLabel lblPassword;
    private final JTextField txtUser;
    private final JTextField txtPassword;
    private final JButton btnClean;
    private final JButton btnAccess;
    private final JPanel contentPane1;
    private final JPanel firstPanel;
    private final JPanel secondPanel;
    private final JPanel thirdPanel;
    private final JPanel welcomePanel;
    private final JPanel login;
    private final JPanel regist;
    private final JPanel loginRegist;
    private final Color background1;
    private final Color background2;
 
    private static final int DF1 = 25;
    private static final int DF2 = 20;
    private static final int DF3 = 12;
    private static final long serialVersionUID = 1;
    private static final String FONT = "Euphemia";

    /**
     * 
     * @param ctrl
     *          the AddUser controller.
     */
    public AddUserViewImpl(final AddUserController ctrl) {
        super();

        this.setTitle("Registration");
        this.setVisible(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.background1 = new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(), ViewColor.light.getBlue()); 
        this.background2 = new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(), ViewColor.lightblue.getBlue());
        this.setBackground(this.background1);
        this.setMinimumSize(new Dimension(DF1, DF2));
        this.contentPane1 = new JPanel();
        this.add(this.contentPane1);

        this.observer = ctrl;

        this.lblwelcome1 = new JLabel("     Create account");
        this.lblwelcome1.setOpaque(true);
        this.lblwelcome1.setBackground(this.background1);
        this.lblwelcome1.setFont(new Font(FONT, Font.BOLD, DF1));
        this.lblwelcome1.setForeground(this.background2);

        this.contentPane1.add(this.lblwelcome1);
        this.contentPane1.setBackground(this.background1);

        this.lblUsername = new JLabel("Username:");
        this.lblUsername.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.txtUser = new JTextField(DF2);

        this.lblPassword = new JLabel("Password: ");
        this.lblPassword.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.txtPassword = new JTextField(DF2);

        this.btnClean = new JButton("Reset");
        this.btnClean.addActionListener(this);
        this.btnClean.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.btnClean.setBackground(this.background1);

        this.btnAccess = new JButton("Create account");
        this.btnAccess.addActionListener(this);
        this.btnAccess.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.btnAccess.setBackground(this.background2);

        this.welcomePanel = new JPanel(new GridLayout(2, 1));
        this.welcomePanel.setBackground(this.background1);
        this.welcomePanel.add(this.lblwelcome1);

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
        this.login.setBorder(new TitledBorder(new EtchedBorder(this.background1,
                this.background2), "New account"));

        this.login.add(this.firstPanel, BorderLayout.NORTH);
        this.login.add(this.secondPanel, BorderLayout.NORTH);
        this.login.add(this.thirdPanel, BorderLayout.NORTH);

        this.regist = new JPanel();
        this.regist.setBackground(this.background1);

        this.loginRegist = new JPanel(new GridLayout(3, 1));
        this.loginRegist.setBackground(this.background1);
        this.loginRegist.add(this.welcomePanel);
        this.loginRegist.add(this.login);
        this.loginRegist.add(this.regist);

        this.contentPane1.add(this.loginRegist, BorderLayout.SOUTH);
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
            this.observer.confirmRegistration(convertMap());
        } else if (isPressed == this.btnClean) {
            this.clearLogin();
        }
    }

    private Map<UserInfo, String > convertMap() {
        final Map<UserInfo, String> mapToPass = new HashMap<>();

        mapToPass.put(UserInfo.USERNAME, this.txtUser.getText());
        mapToPass.put(UserInfo.PASSWORD, this.txtPassword.getText());
        return mapToPass;
    }

    @Override
    public void init() {
        System.out.println("prova");
    }
    @Override
    public void quit() {
        this.setVisible(false);
        this.dispose();

    }
}


