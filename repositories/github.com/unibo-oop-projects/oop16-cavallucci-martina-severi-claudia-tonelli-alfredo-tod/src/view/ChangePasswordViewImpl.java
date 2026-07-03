package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ChangePasswordController;

/**
 * 
 * Class for enter a new User.
 *
 */
public class ChangePasswordViewImpl extends JFrame implements ChangePasswordView,
ActionListener {

    private final JLabel lblwelcome1;
    private final JLabel lblUsername;
    private ChangePasswordController observer;
    private final JLabel lblPassword;
    private final JLabel lblNewPassword;
    private final JLabel txtUser;
    private final JTextField txtPassword;
    private final JTextField txtNewPassword;
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
    private final JPanel change;
    private static final int R1 = 224;
    private static final int G1 = 242;
    private static final int B1 = 241;
    private static final int R2 = 77;
    private static final int G2 = 182;
    private static final int B2 = 172;
    private static final int DF1 = 25;
    private static final int DF2 = 20;
    private static final int DF3 = 12;
    private static final long serialVersionUID = 1;
    private static final String FONT = "Euphemia";

    /**
     * Class that make a view for change the password.
     * @param ctrl
     *          the AddUser controller.
     * @param username
     *          the name of the user that would change the password.
     */
    public ChangePasswordViewImpl(final ChangePasswordController ctrl, final  String username) {
        super();

        this.setTitle("Change password");
        this.setVisible(true);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        this.background1 = new Color(R1, G1, B1); // light blue
        this.background2 = new Color(R2, G2, B2); //dark light blue for the title
        this.setBackground(this.background1);
        this.setMinimumSize(new Dimension(DF1, DF2));
        this.contentPane1 = new JPanel();
        this.add(this.contentPane1);

        this.observer = ctrl;

        this.lblwelcome1 = new JLabel("     Change password");
        this.lblwelcome1.setOpaque(true);
        this.lblwelcome1.setBackground(this.background1);
        this.lblwelcome1.setFont(new Font(FONT, Font.BOLD, DF1));
        this.lblwelcome1.setForeground(this.background2);

        this.contentPane1.add(this.lblwelcome1);
        this.contentPane1.setBackground(this.background1);

        this.lblUsername = new JLabel("Username:");
        this.lblUsername.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.txtUser = new JLabel(username);

        this.lblPassword = new JLabel("Old password: ");
        this.lblPassword.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.txtPassword = new JTextField(DF2);

        this.lblNewPassword = new JLabel("New password: ");
        this.lblPassword.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.txtNewPassword = new JTextField(DF2);

        this.btnClean = new JButton("Reset");
        this.btnClean.addActionListener(this);
        this.btnClean.setFont(new Font(FONT, Font.PLAIN, DF3));
        this.btnClean.setBackground(this.background1);

        this.btnAccess = new JButton("Change password");
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

        this.login = new JPanel();
        this.login.setBackground(this.background1);
        this.login.add(this.lblNewPassword);
        this.login.add(this.txtNewPassword);


        this.change = new JPanel(new GridLayout(4, 1));
        this.change.setBackground(this.background1);
        this.change.setBorder(new TitledBorder(new EtchedBorder(this.background1,
                this.background2), "Change"));

        this.change.add(this.firstPanel, BorderLayout.NORTH);
        this.change.add(this.secondPanel, BorderLayout.NORTH);
        this.change.add(this.login, BorderLayout.NORTH);
        this.change.add(this.thirdPanel, BorderLayout.NORTH);
 

        this.regist = new JPanel();
        this.regist.setBackground(this.background1);

        this.loginRegist = new JPanel(new GridLayout(3, 1));
        this.loginRegist.setBackground(this.background1);
        this.loginRegist.add(this.welcomePanel);
        this.loginRegist.add(this.change);
        this.loginRegist.add(this.regist);

        this.contentPane1.add(this.loginRegist, BorderLayout.SOUTH);
    }

    @Override
    public void clearFields() {

        this.txtPassword.setText("");
        this.txtNewPassword.setText("");
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Object isPressed = e.getSource();

        if (isPressed == this.btnAccess) {
            System.out.println("ciao");
            this.observer.confirmChange(this.txtPassword.getText(),
                    this.txtNewPassword.getText());
        } else if (isPressed == this.btnClean) {
            this.clearFields();
        }
    }

 
    @Override
    public void init() {
        this.setEnabled(true);
    }
    @Override
    public void quit() {
        this.setVisible(false);
        this.dispose();

    }
}