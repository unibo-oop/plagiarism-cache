package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ControllerSettings;
import factory.EnumFactory;
import level.Levels;
import java.awt.Insets;

/**
 * Implementation of {@link View} to show the menu of the settings.
 */
public class ViewSettingsImpl extends JPanel implements View {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int MAX_LEVEL = 5;
    private static final int MIN_LEVEL = 1;
    private final ControllerSettings controllerSettings;
    private Dimension frameDim;

    /**
     * @param controller : controller of settings part of the application.
     */
    public ViewSettingsImpl(final ControllerSettings controller) {
        this.controllerSettings = controller;

    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.frameDim = frameDimension;

        setLayout(new BorderLayout(0, 0));

        final JPanel pCenter = new JPanel();
        pCenter.setBackground(Color.CYAN);
        add(pCenter, BorderLayout.CENTER);
        final GridBagLayout gblCenter = new GridBagLayout();
        gblCenter.columnWidths = new int[] { 0, 0, 0 };
        gblCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblCenter.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
        gblCenter.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
        pCenter.setLayout(gblCenter);

        final Component verticalBox = Box.createVerticalGlue();
        final GridBagConstraints verticalGlueOverTitle = new GridBagConstraints();
        verticalGlueOverTitle.gridwidth = 2;
        verticalGlueOverTitle.insets = new Insets(0, 0, 5, 0);
        verticalGlueOverTitle.gridx = 0;
        verticalGlueOverTitle.gridy = 0;
        pCenter.add(verticalBox, verticalGlueOverTitle);

        final JLabel lblSettings = new JLabel("SETTINGS");
        lblSettings.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 10));
        lblSettings.setForeground(Color.WHITE);
        final GridBagConstraints gbcLabelSettings = new GridBagConstraints();
        gbcLabelSettings.gridwidth = 2;
        gbcLabelSettings.insets = new Insets(0, 0, 5, 0);
        gbcLabelSettings.gridx = 0;
        gbcLabelSettings.gridy = 1;
        pCenter.add(lblSettings, gbcLabelSettings);

        final Component verticalGlue = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlueUnderTitle = new GridBagConstraints();
        gbcVerticalGlueUnderTitle.gridwidth = 2;
        gbcVerticalGlueUnderTitle.insets = new Insets(0, 0, 5, 0);
        gbcVerticalGlueUnderTitle.gridx = 0;
        gbcVerticalGlueUnderTitle.gridy = 2;
        pCenter.add(verticalGlue, gbcVerticalGlueUnderTitle);

        final JLabel lblUseCustomPiece = new JLabel("use custom piece?");
        this.colorsAndFontMinorLabel(lblUseCustomPiece);
        final GridBagConstraints gbcLabelUseCustomPiece = new GridBagConstraints();
        gbcLabelUseCustomPiece.anchor = GridBagConstraints.EAST;
        gbcLabelUseCustomPiece.insets = new Insets(0, 0, 5, 5);
        gbcLabelUseCustomPiece.gridx = 0;
        gbcLabelUseCustomPiece.gridy = 3;
        pCenter.add(lblUseCustomPiece, gbcLabelUseCustomPiece);

        final Checkbox checkCustomBox = new Checkbox();
        if (!this.controllerSettings.checkCustomsPresent()) {
            checkCustomBox.setLabel("no custom pieces present to be used");
            checkCustomBox.setEnabled(false);
        }
        final GridBagConstraints gbcCheckBoxCustom = new GridBagConstraints();
        gbcCheckBoxCustom.anchor = GridBagConstraints.WEST;
        gbcCheckBoxCustom.insets = new Insets(0, 0, 5, 0);
        gbcCheckBoxCustom.gridx = 1;
        gbcCheckBoxCustom.gridy = 3;
        pCenter.add(checkCustomBox, gbcCheckBoxCustom);

        final JLabel lblStartSpeed = new JLabel("start speed");
        this.colorsAndFontMinorLabel(lblStartSpeed);
        final GridBagConstraints gbcLabelStartSpeed = new GridBagConstraints();
        gbcLabelStartSpeed.insets = new Insets(0, 0, 5, 5);
        gbcLabelStartSpeed.anchor = GridBagConstraints.EAST;
        gbcLabelStartSpeed.gridx = 0;
        gbcLabelStartSpeed.gridy = 4;
        pCenter.add(lblStartSpeed, gbcLabelStartSpeed);

        final JComboBox<String> listStartSpeed = new JComboBox<>();
        int j = 1;
        for (int i = MIN_LEVEL; i <= MAX_LEVEL; i++) {
            listStartSpeed.addItem("Level " + j);
            j++;
        }
        listStartSpeed.addActionListener(e -> {
            final int chosenLevel = listStartSpeed.getSelectedIndex() + 1;
            switch (chosenLevel) {
            case 1:
                controllerSettings.setStartLevel(Levels.LEVEL_1);
                break;
            case 2:
                controllerSettings.setStartLevel(Levels.LEVEL_2);
                break;
            case 3:
                controllerSettings.setStartLevel(Levels.LEVEL_3);
                break;
            case 4:
                controllerSettings.setStartLevel(Levels.LEVEL_4);
                break;
            default:
                controllerSettings.setStartLevel(Levels.LEVEL_5);
                break;

            }
        });
        final GridBagConstraints gbcComboBox = new GridBagConstraints();
        gbcComboBox.anchor = GridBagConstraints.WEST;
        gbcComboBox.insets = new Insets(0, 0, 5, 0);
        gbcComboBox.gridx = 1;
        gbcComboBox.gridy = 4;
        pCenter.add(listStartSpeed, gbcComboBox);

        final Component verticalGlue1 = Box.createVerticalGlue();
        final GridBagConstraints gbcSpaceOverButtons = new GridBagConstraints();
        gbcSpaceOverButtons.gridwidth = 2;
        gbcSpaceOverButtons.insets = new Insets(0, 0, 5, 0);
        gbcSpaceOverButtons.gridx = 0;
        gbcSpaceOverButtons.gridy = 5;
        pCenter.add(verticalGlue1, gbcSpaceOverButtons);

        final JButton btnBack = new JButton("BACK");
        this.colorsAndFontButton(btnBack);
        btnBack.addActionListener(e -> this.controllerSettings.backToMenu());
        final GridBagConstraints gbcButtonBack = new GridBagConstraints();
        gbcButtonBack.anchor = GridBagConstraints.EAST;
        gbcButtonBack.insets = new Insets(0, 0, 5, 5);
        gbcButtonBack.gridx = 0;
        gbcButtonBack.gridy = 6;
        pCenter.add(btnBack, gbcButtonBack);

        final JButton btnStart = new JButton("START");
        this.colorsAndFontButton(btnStart);
        btnStart.addActionListener(e -> {
            if (checkCustomBox.getState()) { 
                this.controllerSettings.setCustomList();
            }
            this.controllerSettings.chageController(EnumFactory.GAME);
        });
        final GridBagConstraints gbcButtonStart = new GridBagConstraints();
        gbcButtonStart.insets = new Insets(0, 0, 5, 0);
        gbcButtonStart.anchor = GridBagConstraints.WEST;
        gbcButtonStart.gridx = 1;
        gbcButtonStart.gridy = 6;
        pCenter.add(btnStart, gbcButtonStart);

        final Component verticalGlue2 = Box.createVerticalGlue();
        final GridBagConstraints gbcUnderButtons = new GridBagConstraints();
        gbcUnderButtons.gridwidth = 2;
        gbcUnderButtons.gridx = 0;
        gbcUnderButtons.gridy = 7;
        pCenter.add(verticalGlue2, gbcUnderButtons);

        final Component structEast = Box.createHorizontalStrut(this.frameDim.width / 7);
        add(structEast, BorderLayout.EAST);

        final Component structWest = Box.createHorizontalStrut(this.frameDim.width / 7);
        add(structWest, BorderLayout.WEST);

        final Component structNorth = Box.createVerticalStrut(this.frameDim.height / 5);
        add(structNorth, BorderLayout.NORTH);

        final Component structSouth = Box.createVerticalStrut(this.frameDim.height / 5);
        add(structSouth, BorderLayout.SOUTH);
    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

    private void colorsAndFontButton(final JComponent component) {
        final Font tempFont = new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30);
        component.setBackground(Color.WHITE);
        component.setForeground(Color.CYAN);
        component.setFont(tempFont);
    }

    private void colorsAndFontMinorLabel(final JLabel component) {
        final Font tempFont = new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 20);
        component.setBackground(Color.CYAN);
        component.setForeground(Color.WHITE);
        component.setFont(tempFont);
    }

    @Override
    public final void paintComponent(final Graphics g) {

        final Image background = new ImageIcon(ClassLoader.getSystemResource("backgroundStartGUI1.jpg")).getImage();
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

    }

}
