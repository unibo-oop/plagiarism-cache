package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import controller.ControllerMainMenu;
import factory.EnumFactory;

/**
 * Class that implements {@link View} and that is the view of the main menu of
 * the application.
 * 
 * @see View.
 */
public class ViewMainMenuImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    private static final int MINOR_BUTTON_TEXT_SIZE = 60;
    private final ControllerMainMenu controller;

    /**
     * @param controllerMainMenu : controller of the application.
     */
    public ViewMainMenuImpl(final ControllerMainMenu controllerMainMenu) {
        this.controller = controllerMainMenu;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        UIManager.put("Button.select", Color.YELLOW);
        this.setLayout(new BorderLayout());
        final JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        this.add(center, BorderLayout.CENTER);
        final Icon imageTitle = new ImageIcon(ClassLoader.getSystemResource("TetrisLogo.png"));
        final JLabel title = new JLabel(imageTitle);
        this.add(title, BorderLayout.NORTH);
        final JPanel centralButtons = new JPanel(new GridBagLayout());
        centralButtons.setOpaque(false);
        final JButton customPiece = new JButton("CUSTOM PIECE BUILDER");
        setBackgroundAndBorder(customPiece);
        customPiece.setFont(new Font(Font.MONOSPACED, Font.PLAIN, frameDimension.height / 10));
        customPiece.addActionListener(e -> this.controller.changeController(EnumFactory.CUSTOM));
        customPiece.setFocusPainted(false);
        final JButton play = new JButton("PLAY");
        setBackgroundAndBorder(play);
        play.setFont(new Font(Font.MONOSPACED, Font.PLAIN, frameDimension.height / 10));
        play.addActionListener(e -> this.controller.changeController(EnumFactory.SETTINGS));
        play.setFocusPainted(false);
        final JButton description = new JButton("DESCRIPTION");
        setBackgroundAndBorder(description);
        final Dimension bottomCoupleDimension = new Dimension(frameDimension.width / 10, frameDimension.height / 15);
        description.setFont(new Font(Font.MONOSPACED, Font.PLAIN, frameDimension.height / 15));
        description.setPreferredSize(bottomCoupleDimension);
        description.addActionListener(e -> new ViewGameDescription(this.controller.getManager()));
        description.setFocusPainted(false);
        final JButton login = new JButton();
        login.setFont(new Font(Font.MONOSPACED, Font.PLAIN, frameDimension.height / 15));
        if (this.controller.getPlayer().isEmpty()) {
            login.setText("LOGIN");
            login.addActionListener(e -> this.controller.changeController(EnumFactory.LOGIN));
        } else {
            login.setText("PROFILE");
            login.addActionListener(e -> this.controller.changeController(EnumFactory.PROFILE));
        }
        setBackgroundAndBorder(login);
        login.setPreferredSize(bottomCoupleDimension);

        login.setFocusPainted(false);
        login.setFont(new Font(Font.MONOSPACED, Font.PLAIN, MINOR_BUTTON_TEXT_SIZE - 10));

        final Component rigidArea = Box
                .createRigidArea(new Dimension(bottomCoupleDimension.width, bottomCoupleDimension.height * 3));

        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        centralButtons.add(play, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        centralButtons.add(customPiece, c);
        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        centralButtons.add(login, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        c.gridy = 2;
        centralButtons.add(description, c);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        centralButtons.add(rigidArea, c);
        center.add(centralButtons);
    }

    @Override
    public final void paintComponent(final Graphics g) {

        final Image background = new ImageIcon(ClassLoader.getSystemResource("backgroundStartGUI1.jpg")).getImage();
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    private void setBackgroundAndBorder(final JButton button) {
        button.setBackground(Color.CYAN);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        button.setForeground(Color.WHITE);
    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

}
