package view;

import javax.swing.JPanel;

import controller.ControllerProfile;
import controller.ProfileImpl;
import login.Player;
import utility.UtilsRank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 * Class that implements the graphic of the profile part of a player in the application.
 */
public class ViewProfileImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private final ControllerProfile controller;
    private final Player currentPlayer;
    private Dimension frameDim;
    private JTextField txtName;
    private JTextField txtEt;
    private JTextField txtPaese;
    private JTextField txtPunteggio;
    private JTextField txtRankfield;

    /**
     * @param profileImpl : controller of the player's profile.
     */
    public ViewProfileImpl(final ProfileImpl profileImpl) {
        this.controller = profileImpl;
        this.currentPlayer = this.controller.getPlayer();
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.frameDim = frameDimension;

        setLayout(new BorderLayout(0, 0));

        final JPanel pCenter = new JPanel();
        pCenter.setBackground(Color.BLACK);
        add(pCenter, BorderLayout.CENTER);
        final GridBagLayout gblPanel1 = new GridBagLayout();
        gblPanel1.columnWidths = new int[] { 0, 0, 0 };
        gblPanel1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblPanel1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        gblPanel1.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0,
                Double.MIN_VALUE };
        pCenter.setLayout(gblPanel1);

        final Component verticalGlue4 = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue4 = new GridBagConstraints();
        gbcVerticalGlue4.gridwidth = 2;
        gbcVerticalGlue4.insets = new Insets(0, 0, 5, 0);
        gbcVerticalGlue4.gridx = 0;
        gbcVerticalGlue4.gridy = 0;
        pCenter.add(verticalGlue4, gbcVerticalGlue4);

        final JLabel titleLabel = new JLabel("PLAYER PROFILE:");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30));
        titleLabel.setForeground(Color.GREEN);
        final GridBagConstraints gbcLblPlayerProfile = new GridBagConstraints();
        gbcLblPlayerProfile.anchor = GridBagConstraints.SOUTH;
        gbcLblPlayerProfile.gridwidth = 2;
        gbcLblPlayerProfile.insets = new Insets(0, 0, 5, 0);
        gbcLblPlayerProfile.gridx = 0;
        gbcLblPlayerProfile.gridy = 1;
        pCenter.add(titleLabel, gbcLblPlayerProfile);

        final JLabel userLabel = new JLabel("Username:");
        this.colorsAndFontMinorLabel(userLabel);
        final GridBagConstraints gbcLblUsername = new GridBagConstraints();
        gbcLblUsername.fill = GridBagConstraints.VERTICAL;
        gbcLblUsername.anchor = GridBagConstraints.EAST;
        gbcLblUsername.insets = new Insets(0, 0, 5, 5);
        gbcLblUsername.gridx = 0;
        gbcLblUsername.gridy = 2;
        pCenter.add(userLabel, gbcLblUsername);

        this.txtName = new JTextField();
        this.txtName.setText(this.currentPlayer.getName());
        final GridBagConstraints gbcTxtName = new GridBagConstraints();
        gbcTxtName.anchor = GridBagConstraints.WEST;
        gbcTxtName.insets = new Insets(0, 0, 5, 0);
        gbcTxtName.gridx = 1;
        gbcTxtName.gridy = 2;
        pCenter.add(txtName, gbcTxtName);
        this.txtName.setColumns(10);

        final JLabel ageLabel = new JLabel("Age: ");
        this.colorsAndFontMinorLabel(ageLabel);
        final GridBagConstraints gbcLblAge = new GridBagConstraints();
        gbcLblAge.fill = GridBagConstraints.VERTICAL;
        gbcLblAge.anchor = GridBagConstraints.EAST;
        gbcLblAge.insets = new Insets(0, 0, 5, 5);
        gbcLblAge.gridx = 0;
        gbcLblAge.gridy = 3;
        pCenter.add(ageLabel, gbcLblAge);

        this.txtEt = new JTextField();
        this.txtEt.setText(String.valueOf(this.currentPlayer.getAge()));
        final GridBagConstraints gbcTxtEt = new GridBagConstraints();
        gbcTxtEt.anchor = GridBagConstraints.WEST;
        gbcTxtEt.insets = new Insets(0, 0, 5, 0);
        gbcTxtEt.gridx = 1;
        gbcTxtEt.gridy = 3;
        pCenter.add(txtEt, gbcTxtEt);
        this.txtEt.setColumns(10);

        final JLabel countryLabel = new JLabel("Country: ");
        this.colorsAndFontMinorLabel(countryLabel);
        final GridBagConstraints gbcLblCountry = new GridBagConstraints();
        gbcLblCountry.anchor = GridBagConstraints.EAST;
        gbcLblCountry.insets = new Insets(0, 0, 5, 5);
        gbcLblCountry.gridx = 0;
        gbcLblCountry.gridy = 4;
        pCenter.add(countryLabel, gbcLblCountry);

        this.txtPaese = new JTextField();
        this.txtPaese.setText(this.currentPlayer.getCountry());
        final GridBagConstraints gbcTxtPaese = new GridBagConstraints();
        gbcTxtPaese.anchor = GridBagConstraints.WEST;
        gbcTxtPaese.insets = new Insets(0, 0, 5, 0);
        gbcTxtPaese.gridx = 1;
        gbcTxtPaese.gridy = 4;
        pCenter.add(txtPaese, gbcTxtPaese);
        this.txtPaese.setColumns(10);

        final JLabel scoreLabel = new JLabel("Best score:");
        this.colorsAndFontMinorLabel(scoreLabel);
        final GridBagConstraints gbcLblBestScore = new GridBagConstraints();
        gbcLblBestScore.anchor = GridBagConstraints.EAST;
        gbcLblBestScore.insets = new Insets(0, 0, 5, 5);
        gbcLblBestScore.gridx = 0;
        gbcLblBestScore.gridy = 5;
        pCenter.add(scoreLabel, gbcLblBestScore);

        this.txtPunteggio = new JTextField();
        this.txtPunteggio.setText(String.valueOf(this.currentPlayer.getBestScore()));
        final GridBagConstraints gbcTxtPunteggio = new GridBagConstraints();
        gbcTxtPunteggio.anchor = GridBagConstraints.WEST;
        gbcTxtPunteggio.insets = new Insets(0, 0, 5, 0);
        gbcTxtPunteggio.gridx = 1;
        gbcTxtPunteggio.gridy = 5;
        pCenter.add(txtPunteggio, gbcTxtPunteggio);
        this.txtPunteggio.setColumns(10);

        final JButton modifyButton = new JButton("modify");
        this.colorsAndFontButton(modifyButton);
        modifyButton.addActionListener(e -> this.changeState((JButton) e.getSource()));

        final JLabel lblRankPosition = new JLabel("rank position: ");
        this.colorsAndFontMinorLabel(lblRankPosition);
        final GridBagConstraints gbcLblRankPosition = new GridBagConstraints();
        gbcLblRankPosition.anchor = GridBagConstraints.EAST;
        gbcLblRankPosition.insets = new Insets(0, 0, 5, 5);
        gbcLblRankPosition.gridx = 0;
        gbcLblRankPosition.gridy = 6;
        pCenter.add(lblRankPosition, gbcLblRankPosition);

        this.txtRankfield = new JTextField();
        this.txtRankfield.setText(String.valueOf(UtilsRank.getRankPosition(this.currentPlayer.getName())));
        final GridBagConstraints gbcTxtRankfield = new GridBagConstraints();
        gbcTxtRankfield.anchor = GridBagConstraints.WEST;
        gbcTxtRankfield.insets = new Insets(0, 0, 5, 0);
        gbcTxtRankfield.gridx = 1;
        gbcTxtRankfield.gridy = 6;
        pCenter.add(txtRankfield, gbcTxtRankfield);
        txtRankfield.setColumns(10);

        final JLabel lblFirstInRank = new JLabel("GLOBAL RANK FIRST POSITION: "
                + UtilsRank.getFirstInRank().getY() + " " + UtilsRank.getFirstInRank().getX());
        lblFirstInRank.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 40));
        lblFirstInRank.setForeground(Color.RED);
        final GridBagConstraints gbcLblNewLabel = new GridBagConstraints();
        gbcLblNewLabel.gridwidth = 2;
        gbcLblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbcLblNewLabel.gridx = 0;
        gbcLblNewLabel.gridy = 7;
        pCenter.add(lblFirstInRank, gbcLblNewLabel);
        final GridBagConstraints gbcBtnModify = new GridBagConstraints();
        gbcBtnModify.anchor = GridBagConstraints.EAST;
        gbcBtnModify.insets = new Insets(0, 0, 5, 5);
        gbcBtnModify.gridx = 0;
        gbcBtnModify.gridy = 8;
        pCenter.add(modifyButton, gbcBtnModify);

        final JButton logoutButton = new JButton("logout");
        this.colorsAndFontButton(logoutButton);
        logoutButton.addActionListener(e -> this.controller.logout());
        final GridBagConstraints gbcBtnLogout = new GridBagConstraints();
        gbcBtnLogout.anchor = GridBagConstraints.WEST;
        gbcBtnLogout.insets = new Insets(0, 0, 5, 0);
        gbcBtnLogout.gridx = 1;
        gbcBtnLogout.gridy = 8;
        pCenter.add(logoutButton, gbcBtnLogout);

        final JButton menuButton = new JButton("Menu");
        this.colorsAndFontButton(menuButton);
        menuButton.addActionListener(e -> this.controller.backToMenu());
        final GridBagConstraints gbcBtnMenu = new GridBagConstraints();
        gbcBtnMenu.insets = new Insets(0, 0, 5, 0);
        gbcBtnMenu.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnMenu.gridwidth = 2;
        gbcBtnMenu.gridx = 0;
        gbcBtnMenu.gridy = 9;
        pCenter.add(menuButton, gbcBtnMenu);

        final Component verticalGlue3 = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlue3 = new GridBagConstraints();
        gbcVerticalGlue3.gridwidth = 2;
        gbcVerticalGlue3.gridx = 0;
        gbcVerticalGlue3.gridy = 10;
        pCenter.add(verticalGlue3, gbcVerticalGlue3);

        Component verticalStrut = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrut, BorderLayout.SOUTH);

        Component horizontalStrutW = Box.createHorizontalStrut(this.frameDim.width / 15);
        add(horizontalStrutW, BorderLayout.WEST);

        Component horizontalStrutE = Box.createHorizontalStrut(this.frameDim.width / 15);
        add(horizontalStrutE, BorderLayout.EAST);

        Component verticalStrutN = Box.createVerticalStrut(this.frameDim.height / 5);
        add(verticalStrutN, BorderLayout.NORTH);

        this.notEditableAllFields();
    }

    private void changeState(final JButton stateButton) {
        if (stateButton.getText().equals("modify") && !this.txtEt.isEnabled()) {
            this.modifyState();
            stateButton.setText("save");
        } else {
            try {
                this.controller.saveChanges(this.txtPaese.getText(), Integer.parseInt(this.txtEt.getText()));
                stateButton.setText("modify");
                this.notEditableAllFields();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Age has to be a number.", "Age", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException wrongFileds) {
                JOptionPane.showMessageDialog(this, "All fileds have to be fill\n and age > 0 \n to save changes.",
                        "Empty field/s", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void notEditableAllFields() {
        this.txtName.setEnabled(false);
        this.txtEt.setEnabled(false);
        this.txtPaese.setEnabled(false);
        this.txtPunteggio.setEnabled(false);
        this.txtRankfield.setEnabled(false);
    }

    private void modifyState() {
        this.txtEt.setEnabled(true);
        this.txtPaese.setEnabled(true);
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
