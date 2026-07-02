package view;

import javax.swing.JPanel;

import controller.ControllerLogin;
import login.LoginTypes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 * Implements the {@link View} to show the graphic of a new user registration.
 */
public class ViewNewUserLoginImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private final ControllerLogin controller;

    private Dimension frameDim;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField countryField;
    private JTextField ageField;

    /**
     * @param controllerLogin 
     */
    public ViewNewUserLoginImpl(final ControllerLogin controllerLogin) {
        this.controller = controllerLogin;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.frameDim = frameDimension;

        setLayout(new BorderLayout(0, 0));

        final JPanel pCenter = new JPanel();
        pCenter.setBackground(Color.BLACK);
        add(pCenter, BorderLayout.CENTER);
        final GridBagLayout gblPanel = new GridBagLayout();
        gblPanel.columnWidths = new int[] { 0, 0, 0, 0 };
        gblPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
        gblPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0 }; // Double.MIN_VALUE};
        pCenter.setLayout(gblPanel);

        final Component verticalGlue = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue = new GridBagConstraints();
        gbcVerticalGlue.insets = new Insets(0, 0, 5, 5);
        gbcVerticalGlue.gridx = 1;
        gbcVerticalGlue.gridy = 0;
        pCenter.add(verticalGlue, gbcVerticalGlue);

        final JLabel newPlayerLabel = new JLabel("NEW PLAYER", SwingConstants.CENTER);
        newPlayerLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30));
        newPlayerLabel.setForeground(Color.GREEN);
        final GridBagConstraints gbcLblNewLabel = new GridBagConstraints();
        gbcLblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbcLblNewLabel.gridx = 1;
        gbcLblNewLabel.gridy = 1;
        pCenter.add(newPlayerLabel, gbcLblNewLabel);

        final JLabel userLabel = new JLabel("User name: ");
        this.colorsAndFontMinorLabel(userLabel);
        final GridBagConstraints gbcLblNewLabel1 = new GridBagConstraints();
        gbcLblNewLabel1.anchor = GridBagConstraints.EAST;
        gbcLblNewLabel1.insets = new Insets(0, 0, 5, 5);
        gbcLblNewLabel1.gridx = 0;
        gbcLblNewLabel1.gridy = 2;
        pCenter.add(userLabel, gbcLblNewLabel1);

        this.nameField = new JTextField();
        final GridBagConstraints gbcTextField = new GridBagConstraints();
        gbcTextField.fill = GridBagConstraints.HORIZONTAL;
        gbcTextField.insets = new Insets(0, 0, 5, 5);
        gbcTextField.gridx = 1;
        gbcTextField.gridy = 2;
        pCenter.add(nameField, gbcTextField);
        nameField.setColumns(10);

        final JLabel lblNewLabel5 = new JLabel("New label");
        this.setIconLabel(lblNewLabel5);
        final GridBagConstraints gbcLblNewLabel5 = new GridBagConstraints();
        gbcLblNewLabel5.anchor = GridBagConstraints.WEST;
        gbcLblNewLabel5.insets = new Insets(0, 0, 5, 0);
        gbcLblNewLabel5.gridx = 2;
        gbcLblNewLabel5.gridy = 2;
        pCenter.add(lblNewLabel5, gbcLblNewLabel5);

        final JLabel passwordLabel = new JLabel("Password: ");
        this.colorsAndFontMinorLabel(passwordLabel);
        final GridBagConstraints gbcLblNewLabel2 = new GridBagConstraints();
        gbcLblNewLabel2.anchor = GridBagConstraints.EAST;
        gbcLblNewLabel2.insets = new Insets(0, 0, 5, 5);
        gbcLblNewLabel2.gridx = 0;
        gbcLblNewLabel2.gridy = 3;
        pCenter.add(passwordLabel, gbcLblNewLabel2);

        this.passwordField = new JPasswordField();
        final GridBagConstraints gbcpasswordField = new GridBagConstraints();
        gbcpasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcpasswordField.insets = new Insets(0, 0, 5, 5);
        gbcpasswordField.gridx = 1;
        gbcpasswordField.gridy = 3;
        pCenter.add(passwordField, gbcpasswordField);

        final JLabel lblNewLabel6 = new JLabel("New label1");
        this.setIconLabel(lblNewLabel6);
        final GridBagConstraints gbcLblNewLabel6 = new GridBagConstraints();
        gbcLblNewLabel6.anchor = GridBagConstraints.WEST;
        gbcLblNewLabel6.insets = new Insets(0, 0, 5, 0);
        gbcLblNewLabel6.gridx = 2;
        gbcLblNewLabel6.gridy = 3;
        pCenter.add(lblNewLabel6, gbcLblNewLabel6);

        final JLabel countryLabel = new JLabel("Country");
        this.colorsAndFontMinorLabel(countryLabel);
        final GridBagConstraints gbcLblNewLabel3 = new GridBagConstraints();
        gbcLblNewLabel3.anchor = GridBagConstraints.EAST;
        gbcLblNewLabel3.insets = new Insets(0, 0, 5, 5);
        gbcLblNewLabel3.gridx = 0;
        gbcLblNewLabel3.gridy = 4;
        pCenter.add(countryLabel, gbcLblNewLabel3);

        this.countryField = new JTextField();
        final GridBagConstraints gbcTextField1 = new GridBagConstraints();
        gbcTextField1.fill = GridBagConstraints.HORIZONTAL;
        gbcTextField1.insets = new Insets(0, 0, 5, 5);
        gbcTextField1.gridx = 1;
        gbcTextField1.gridy = 4;
        pCenter.add(countryField, gbcTextField1);
        countryField.setColumns(10);

        final JLabel lblNewLabel7 = new JLabel("New label2");
        this.setIconLabel(lblNewLabel7);
        final GridBagConstraints gbcLblNewLabel7 = new GridBagConstraints();
        gbcLblNewLabel7.anchor = GridBagConstraints.WEST;
        gbcLblNewLabel7.insets = new Insets(0, 0, 5, 0);
        gbcLblNewLabel7.gridx = 2;
        gbcLblNewLabel7.gridy = 4;
        pCenter.add(lblNewLabel7, gbcLblNewLabel7);

        final JLabel ageLabel = new JLabel("Age");
        this.colorsAndFontMinorLabel(ageLabel);
        final GridBagConstraints gbcLblAge = new GridBagConstraints();
        gbcLblAge.anchor = GridBagConstraints.EAST;
        gbcLblAge.insets = new Insets(0, 0, 5, 5);
        gbcLblAge.gridx = 0;
        gbcLblAge.gridy = 5;
        pCenter.add(ageLabel, gbcLblAge);

        this.ageField = new JTextField();
        final GridBagConstraints gbcTextField2 = new GridBagConstraints();
        gbcTextField2.fill = GridBagConstraints.HORIZONTAL;
        gbcTextField2.insets = new Insets(0, 0, 5, 5);
        gbcTextField2.gridx = 1;
        gbcTextField2.gridy = 5;
        pCenter.add(ageField, gbcTextField2);
        ageField.setColumns(10);

        final JLabel lblNewLabel8 = new JLabel("New label3");
        this.setIconLabel(lblNewLabel8);
        final GridBagConstraints gbcLblNewLabel8 = new GridBagConstraints();
        gbcLblNewLabel8.anchor = GridBagConstraints.WEST;
        gbcLblNewLabel8.insets = new Insets(0, 0, 5, 0);
        gbcLblNewLabel8.gridx = 2;
        gbcLblNewLabel8.gridy = 5;
        pCenter.add(lblNewLabel8, gbcLblNewLabel8);

        final JButton btnCreate = new JButton("create");
        this.colorsAndFontButton(btnCreate);
        btnCreate.addActionListener(e -> this.checkCreate());
        final GridBagConstraints gbcBtnCreate = new GridBagConstraints();
        gbcBtnCreate.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnCreate.insets = new Insets(0, 0, 5, 5);
        gbcBtnCreate.gridx = 1;
        gbcBtnCreate.gridy = 6;
        pCenter.add(btnCreate, gbcBtnCreate);

        final JButton bntBack = new JButton("BACK");
        this.colorsAndFontButton(bntBack);
        bntBack.addActionListener(e -> this.controller.switchView(LoginTypes.GENERAL));
        final GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
        gbcBtnNewButton.anchor = GridBagConstraints.EAST;
        gbcBtnNewButton.insets = new Insets(0, 0, 5, 5);
        gbcBtnNewButton.gridx = 0;
        gbcBtnNewButton.gridy = 7;
        pCenter.add(bntBack, gbcBtnNewButton);

        final JButton btnMenu = new JButton("MENU");
        this.colorsAndFontButton(btnMenu);
        btnMenu.addActionListener(e -> this.controller.backToMenu());
        final GridBagConstraints gbcBtnNewButton1 = new GridBagConstraints();
        gbcBtnNewButton1.anchor = GridBagConstraints.WEST;
        gbcBtnNewButton1.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewButton1.gridx = 2;
        gbcBtnNewButton1.gridy = 7;
        pCenter.add(btnMenu, gbcBtnNewButton1);

        final Component verticalGlue1 = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue1 = new GridBagConstraints();
        gbcVerticalGlue1.insets = new Insets(0, 0, 0, 5);
        gbcVerticalGlue1.gridx = 1;
        gbcVerticalGlue1.gridy = 8;
        pCenter.add(verticalGlue1, gbcVerticalGlue1);

        Component verticalStrut = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrut, BorderLayout.SOUTH);

        Component horizontalStrut1 = Box.createHorizontalStrut(this.frameDim.width / 5);
        add(horizontalStrut1, BorderLayout.WEST);

        Component horizontalStrut2 = Box.createHorizontalStrut(this.frameDim.width / 5);
        add(horizontalStrut2, BorderLayout.EAST);

        Component verticalStrut1 = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrut1, BorderLayout.NORTH);
    }

    private boolean checkAllFieldFill() {
        Boolean answer = true;
        if (this.nameField.getText().isBlank() || this.passwordField.getPassword().length == 0
                || this.countryField.getText().isBlank() || this.ageField.getText().isBlank()) {
            answer = false;
        }
        return answer;
    }

    private void checkCreate() {
        if (this.checkAllFieldFill()) {
            try {
                final byte[] temp = new String(this.passwordField.getPassword()).getBytes(StandardCharsets.UTF_8);
                this.controller.createPlayer(this.nameField.getText(), Base64.getEncoder().encodeToString(temp),
                        this.countryField.getText(), Integer.parseInt(this.ageField.getText()));
                this.controller.backToMenu();
            } catch (NumberFormatException ageNotNumber) {
                JOptionPane.showMessageDialog(this, "Age has to be a number.", "Age", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException wrongAge) {
                JOptionPane.showMessageDialog(this, "Age has to be > 0.", "Age", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException playerPresent) {
                JOptionPane.showMessageDialog(this, "This username already exists.", "Change username",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "All fileds have to be fill to create a new player.", "Empty field/s",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

    @Override
    public final void paintComponent(final Graphics g) {

        final Image background = new ImageIcon(ClassLoader.getSystemResource("backgroundLogin.jpg")).getImage();
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

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

    private void setIconLabel(final JLabel component) {
        component.setForeground(Color.BLACK);
    }
}
