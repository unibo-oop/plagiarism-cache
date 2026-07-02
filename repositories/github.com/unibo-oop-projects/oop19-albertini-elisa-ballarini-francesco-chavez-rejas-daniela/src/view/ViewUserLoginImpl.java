package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerLogin;
import login.LoginTypes;

import java.awt.Insets;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.awt.Component;
import javax.swing.Box;

/**
 * Implementation of {@link View} to show the graphic of the login of a existing player.
 */
public class ViewUserLoginImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private final ControllerLogin controllerLogin;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Dimension frameDim;

    /**
     * @param controllerLogin : controller of the application.
     */
    public ViewUserLoginImpl(final ControllerLogin controllerLogin) {
        this.controllerLogin = controllerLogin;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.frameDim = frameDimension;

        setLayout(new BorderLayout(0, 0));

        final JPanel pCenter = new JPanel();
        pCenter.setBackground(Color.BLACK);
        add(pCenter, BorderLayout.CENTER);
        final GridBagLayout gblPCenter = new GridBagLayout();
        gblPCenter.columnWidths = new int[] { 0, 0, 0 };
        gblPCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblPCenter.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        gblPCenter.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        pCenter.setLayout(gblPCenter);

        final Component verticalGlue = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue = new GridBagConstraints();
        gbcVerticalGlue.insets = new Insets(0, 0, 5, 5);
        gbcVerticalGlue.gridx = 0;
        gbcVerticalGlue.gridy = 0;
        pCenter.add(verticalGlue, gbcVerticalGlue);

        final JLabel titleLabel = new JLabel("LOGIN EXISTING PLAYER");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30));
        titleLabel.setForeground(Color.GREEN);
        final GridBagConstraints gbcLblLoginExistingPlayer = new GridBagConstraints();
        gbcLblLoginExistingPlayer.gridwidth = 2;
        gbcLblLoginExistingPlayer.insets = new Insets(0, 0, 5, 0);
        gbcLblLoginExistingPlayer.gridx = 0;
        gbcLblLoginExistingPlayer.gridy = 1;
        pCenter.add(titleLabel, gbcLblLoginExistingPlayer);

        final JLabel lblUsername = new JLabel("username:");
        this.colorsAndFontMinorLabel(lblUsername);
        final GridBagConstraints gbcLblUsername = new GridBagConstraints();
        gbcLblUsername.anchor = GridBagConstraints.EAST;
        gbcLblUsername.insets = new Insets(0, 0, 5, 5);
        gbcLblUsername.gridx = 0;
        gbcLblUsername.gridy = 2;
        pCenter.add(lblUsername, gbcLblUsername);

        this.usernameField = new JTextField();
        this.usernameField.setText("insert username");
        final GridBagConstraints gbcTxtInsertYourUsername = new GridBagConstraints();
        gbcTxtInsertYourUsername.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtInsertYourUsername.insets = new Insets(0, 0, 5, 0);
        gbcTxtInsertYourUsername.gridx = 1;
        gbcTxtInsertYourUsername.gridy = 2;
        pCenter.add(usernameField, gbcTxtInsertYourUsername);
        this.usernameField.setColumns(10);

        final JLabel lblPassword = new JLabel("password:");
        this.colorsAndFontMinorLabel(lblPassword);
        final GridBagConstraints gbcLblPassword = new GridBagConstraints();
        gbcLblPassword.anchor = GridBagConstraints.EAST;
        gbcLblPassword.insets = new Insets(0, 0, 5, 5);
        gbcLblPassword.gridx = 0;
        gbcLblPassword.gridy = 3;
        pCenter.add(lblPassword, gbcLblPassword);

        this.passwordField = new JPasswordField();
        this.passwordField.setText("insert password");
        final GridBagConstraints gbcPwdInsertYourPassword = new GridBagConstraints();
        gbcPwdInsertYourPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcPwdInsertYourPassword.insets = new Insets(0, 0, 5, 0);
        gbcPwdInsertYourPassword.gridx = 1;
        gbcPwdInsertYourPassword.gridy = 3;
        pCenter.add(passwordField, gbcPwdInsertYourPassword);

        final JButton loginButton = new JButton("LOGIN");
        this.colorsAndFontButton(loginButton);
        loginButton.addActionListener(e -> this.checkLogin());
        final GridBagConstraints gbcBtnLogin = new GridBagConstraints();
        gbcBtnLogin.gridwidth = 2;
        gbcBtnLogin.insets = new Insets(0, 0, 5, 0);
        gbcBtnLogin.gridx = 0;
        gbcBtnLogin.gridy = 4;
        pCenter.add(loginButton, gbcBtnLogin);

        final JButton backButton = new JButton("BACK");
        this.colorsAndFontButton(backButton);
        backButton.addActionListener(e -> this.controllerLogin.switchView(LoginTypes.GENERAL));
        final GridBagConstraints gbcBackButton = new GridBagConstraints();
        gbcBackButton.gridwidth = 2;
        gbcBackButton.insets = new Insets(0, 0, 5, 0);
        gbcBackButton.gridx = 0;
        gbcBackButton.gridy = 5;
        pCenter.add(backButton, gbcBackButton);

        final JButton menuButton = new JButton("MENU");
        this.colorsAndFontButton(menuButton);
        menuButton.addActionListener(e -> this.backMainMenu());
        final GridBagConstraints gbcMenuButton = new GridBagConstraints();
        gbcMenuButton.gridwidth = 2;
        gbcMenuButton.insets = new Insets(0, 0, 5, 0);
        gbcMenuButton.gridx = 0;
        gbcMenuButton.gridy = 6;
        pCenter.add(menuButton, gbcMenuButton);

        final Component verticalGlue1 = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue1 = new GridBagConstraints();
        gbcVerticalGlue1.insets = new Insets(0, 0, 0, 5);
        gbcVerticalGlue1.gridx = 0;
        gbcVerticalGlue1.gridy = 7;
        pCenter.add(verticalGlue1, gbcVerticalGlue1);

        Component verticalStrut = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrut, BorderLayout.NORTH);

        Component verticalStrut1 = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrut1, BorderLayout.SOUTH);

        Component horizontalStrut = Box.createHorizontalStrut(this.frameDim.width / 20);
        add(horizontalStrut, BorderLayout.WEST);

        Component horizontalStrut1 = Box.createHorizontalStrut(this.frameDim.width / 20);
        add(horizontalStrut1, BorderLayout.EAST);
    }

    private void checkLogin() {
        if (this.usernameField.getText().isBlank() || this.passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "All fields have to be fill", "Fill", JOptionPane.ERROR_MESSAGE);
        } else {
            byte[] temp = new String(this.passwordField.getPassword()).getBytes(StandardCharsets.UTF_8);
            this.controllerLogin.tryLog(this.usernameField.getText(), Base64.getEncoder().encodeToString(temp));
            this.isPlayerLog();
        }
    }

    private void isPlayerLog() {
        if (this.controllerLogin.getPlayer().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Try again.",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Successfull log!", "Log in", JOptionPane.INFORMATION_MESSAGE);
            this.controllerLogin.backToMenu();
        }
    }

    private void backMainMenu() {
        if (this.controllerLogin.getPlayer().isEmpty()) {
            final int choice = JOptionPane.showConfirmDialog(null, "Come back to main menu?\n" + "You have not logged yet.",
                    "Main Menu", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == 0) {
                this.controllerLogin.backToMenu();
            }
        } else {
            this.controllerLogin.backToMenu();
        }
    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

    private void colorsAndFontButton(final JComponent component) {
        final Font tempFont = new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30);
        component.setBackground(Color.GREEN);
        component.setForeground(Color.BLACK);
        component.setFont(tempFont);
    }

    private void colorsAndFontMinorLabel(final JLabel component) {
        final Font tempFont = new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 40);
        component.setBackground(Color.BLACK);
        component.setForeground(Color.GREEN);
        component.setFont(tempFont);
    }

    @Override
    public final void paintComponent(final Graphics g) {

        final Image background = new ImageIcon(ClassLoader.getSystemResource("backgroundLogin.jpg")).getImage();
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

    }

}
