package bzzbomber.view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bzzbomber.game.Game;

/**
 * This class uses a Factory pattern to define some aspects of
 * the GUI.
 *
 */

public class GUIFactoryImpl implements GUIFactory {
    /**
     * Foreground's color.
     */
    public static final Color COLOR_FOREGROUND = Color.BLUE;
    /**
     * Font for button.
     */
    public static final Font BUTTON_FONT = new Font("Candy Round BTN Lt", Font.BOLD, (int) (Game.WINDOW_WIDTH * 0.04));
    /**
     * Color for button.
     */
    public static final Color COLOR_BUTTON = Color.WHITE;

    @Override
    public final JFrame createBasicFrame() {
        final JFrame frame = new JFrame(Game.GAME_TITLE);
        frame.setSize(new Dimension(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ImageIcon icona = new ImageIcon(this.createPathImage("/main/bomba.png"));
        frame.setIconImage(icona.getImage());
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        return frame;
    }

    @Override
    public final JButton createButton(final String name, final String pathImage) {
        final JButton generic = new JButton();
        final ImageIcon imageicon = new ImageIcon(this.createPathImage(pathImage));
        final Image scaledImage = imageicon.getImage().getScaledInstance((int) (Game.WINDOW_WIDTH * 0.07),
                (int) (Game.WINDOW_HEIGHT * 0.1), Image.SCALE_DEFAULT);
        imageicon.setImage(scaledImage);
        generic.setIcon(imageicon);
        generic.setText(name);
        generic.setForeground(COLOR_FOREGROUND);
        generic.setBackground(COLOR_BUTTON);
        generic.setFont(BUTTON_FONT);
        return generic;
    }

    @Override
    public final JPanel createTitleButton(final String image, final int top, final int position, final int distance,
            final int right, final Color colore) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints constant = new GridBagConstraints();
        constant.insets = new Insets(top, position, distance, right);
        final JLabel lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        final ImageIcon imageicon = new ImageIcon(this.createPathImage(image));
        final Image scaledImage = imageicon.getImage().getScaledInstance((int) (Game.WINDOW_WIDTH * 0.45),
                (int) (Game.WINDOW_HEIGHT * 0.25), Image.SCALE_DEFAULT);
        imageicon.setImage(scaledImage);
        lblTitle.setIcon(imageicon);
        constant.gridwidth = 0;
        panel.add(lblTitle, constant);
        constant.gridy++;
        panel.setBackground(colore);
        return panel;
    }

    @Override
    public final JButton createImageButton(final String pathImage, final int width, final int height) {
        final JButton genericbutton = new JButton();
        final ImageIcon imageicon = new ImageIcon(this.createPathImage(pathImage));
        final Image scaledImage = imageicon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        imageicon.setImage(scaledImage);
        genericbutton.setIcon(imageicon);
        genericbutton.setBorderPainted(false);
        genericbutton.setContentAreaFilled(false);

        genericbutton.setOpaque(true);

        return genericbutton;
    }

    @Override
    public final JButton createBackButton() {

        return createButton("Return", "/return.png");
    }

    @Override
    public final Image createPathImage(final String nameImage) {
        BufferedImage bufferImage = null;
        try {
            bufferImage = ImageIO.read(getClass().getResource(nameImage));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferImage;
    }

}
