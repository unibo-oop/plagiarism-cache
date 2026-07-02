package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ControllerLogin;
import login.LoginTypes;
import javax.swing.Box;
import java.awt.Component;

/**
 * Class that implements {@link View} and is the general view of the general
 * login part.
 * 
 * @see View.
 */
public class ViewLoginImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOR = Color.GREEN;
    private static final Color LETTERS_COLOR = Color.BLACK;

    private final ControllerLogin controllerLogin;

    private Dimension frameDim;

    /**
     * @param controllerLogin : controller of the application.
     */
    public ViewLoginImpl(final ControllerLogin controllerLogin) {
        this.controllerLogin = controllerLogin;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        this.frameDim = frameDimension;

        setLayout(new BorderLayout(0, 0));

        final JPanel pCenter = new JPanel();
        pCenter.setBackground(Color.BLACK);
        add(pCenter, BorderLayout.CENTER);
        final GridBagLayout gblCenter = new GridBagLayout();
        gblCenter.columnWidths = new int[] { 0, 0 };
        gblCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblCenter.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gblCenter.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        pCenter.setLayout(gblCenter);

        final Component verticalGlueAboveTilte = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlueAboveTitle = new GridBagConstraints();
        gbcVerticalGlueAboveTitle.insets = new Insets(0, 0, 5, 0);
        gbcVerticalGlueAboveTitle.gridx = 0;
        gbcVerticalGlueAboveTitle.gridy = 1;
        pCenter.add(verticalGlueAboveTilte, gbcVerticalGlueAboveTitle);

        final JLabel title = new JLabel("CHOOSE LOGIN", SwingConstants.CENTER);
        title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 20));
        title.setForeground(BACKGROUND_COLOR);
        final GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.insets = new Insets(0, 0, 5, 0);
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 2;
        pCenter.add(title, gbcTitle);

        final Component verticalGlueUnderTitle = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlueUnderTitle = new GridBagConstraints();
        gbcVerticalGlueUnderTitle.insets = new Insets(0, 0, 5, 0);
        gbcVerticalGlueUnderTitle.gridx = 0;
        gbcVerticalGlueUnderTitle.gridy = 3;
        pCenter.add(verticalGlueUnderTitle, gbcVerticalGlueUnderTitle);

        final JButton btnExistingPlayer = new JButton("Existing player");
        this.colorsAndFontButton(btnExistingPlayer);
        btnExistingPlayer.addActionListener(e -> this.controllerLogin.switchView(LoginTypes.OLD));
        final GridBagConstraints gbcBtnExistingPlayer = new GridBagConstraints();
        gbcBtnExistingPlayer.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnExistingPlayer.insets = new Insets(0, 0, 5, 0);
        gbcBtnExistingPlayer.gridx = 0;
        gbcBtnExistingPlayer.gridy = 4;
        pCenter.add(btnExistingPlayer, gbcBtnExistingPlayer);

        final JButton btnNewPlayer = new JButton("New player");
        this.colorsAndFontButton(btnNewPlayer);
        btnNewPlayer.addActionListener(e -> this.controllerLogin.switchView(LoginTypes.NEW));
        final GridBagConstraints gbcBtnNewPlayer = new GridBagConstraints();
        gbcBtnNewPlayer.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnNewPlayer.insets = new Insets(0, 0, 5, 0);
        gbcBtnNewPlayer.gridx = 0;
        gbcBtnNewPlayer.gridy = 5;
        pCenter.add(btnNewPlayer, gbcBtnNewPlayer);

        final Component verticalStrut = Box.createVerticalStrut((int) this.frameDim.getHeight() / 15);
        final GridBagConstraints gbcVerticalStrut = new GridBagConstraints();
        gbcVerticalStrut.insets = new Insets(0, 0, 5, 0);
        gbcVerticalStrut.gridx = 0;
        gbcVerticalStrut.gridy = 6;
        pCenter.add(verticalStrut, gbcVerticalStrut);

        final JButton btnMenu = new JButton("Menu");
        this.colorsAndFontButton(btnMenu);
        btnMenu.addActionListener(e -> this.controllerLogin.backToMenu());
        final GridBagConstraints gbcBtnMenu = new GridBagConstraints();
        gbcBtnMenu.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnMenu.insets = new Insets(0, 0, 5, 0);
        gbcBtnMenu.gridx = 0;
        gbcBtnMenu.gridy = 7;
        pCenter.add(btnMenu, gbcBtnMenu);

        final Component verticalGlueUnder = Box.createVerticalGlue();
        final GridBagConstraints gbcVerticalGlueUnder = new GridBagConstraints();
        gbcVerticalGlueUnder.gridx = 0;
        gbcVerticalGlueUnder.gridy = 8;
        pCenter.add(verticalGlueUnder, gbcVerticalGlueUnder);

        final Component structEast = Box.createHorizontalStrut(this.frameDim.width / 3);
        add(structEast, BorderLayout.EAST);

        final Component structWest = Box.createHorizontalStrut(this.frameDim.width / 3);
        add(structWest, BorderLayout.WEST);

        final Component structNorth = Box.createVerticalStrut(this.frameDim.height / 5);
        add(structNorth, BorderLayout.NORTH);

        final Component structSouth = Box.createVerticalStrut(this.frameDim.height / 5);
        add(structSouth, BorderLayout.SOUTH);
    }

    private void colorsAndFontButton(final JButton component) {
        final Font tempFont = new Font(Font.MONOSPACED, Font.PLAIN, this.frameDim.height / 30);
        component.setBackground(BACKGROUND_COLOR);
        component.setForeground(LETTERS_COLOR);
        component.setFont(tempFont);
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

}
