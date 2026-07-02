package it.unibo.superpeach.graphics;

import it.unibo.superpeach.game.Game;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Implementation of the starting menu of the game.
 * 
 * @author Miriam Sonaglia
 */
public final class PeachMenu {

    private final JFrame frame;
    private Clip clip;

    /**
     * It creates a new menu with a title and a dimention that can be resized.
     * 
     * @param title
     * @param width
     * @param height
     * @param scale
     * @param game
     */
    public PeachMenu(final String title, final int width, final int height, final int scale, final Game game) {
        final Dimension size = new Dimension(width * scale, height * scale);
        frame = new JFrame(title);
        final ImageIcon imageIcon = new ImageIcon("src/main/resources/it/unibo/superpeach/icon/peach_icon.png");
        frame.setIconImage(imageIcon.getImage());
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Color customColor = new Color(218, 165, 32); // SFONDO BOTTONI
        final Color customColor1 = new Color(101, 67, 33); // PER LE SCRITTE
        final Border border = BorderFactory.createLineBorder(customColor1, 2, true);

        final String imagePath = "src/main/resources/it/unibo/superpeach/tiles/background.png";
        final ImagePanel panel = new ImagePanel(imagePath);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        final int fontSize = 40;
        final int labelHeight = 20;
        final JLabel titleLabel = new JLabel("SUPER PEACH");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, fontSize * scale));
        titleLabel.setForeground(Color.PINK);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(labelHeight * scale));

        /**
         * Creation of the START button.
         * If clicked it starts the game.
         */
        final CustomButton startButton = new CustomButton("START GAME", customColor, customColor1, scale);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                panel.setVisible(false);
                frame.remove(panel);
                frame.add(game);
                game.init();
                game.requestFocusInWindow();
            }
        });
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10 * scale));

        final String[] guiScaleList = { "GUI SCALE", "Tiny", "Small", "Medium", "Large" };
        final JComboBox<String> guiComboBox = new JComboBox<>(guiScaleList);
        guiComboBox.setLayout(new FlowLayout());
        guiComboBox.setBackground(customColor);
        guiComboBox.setForeground(customColor1);
        guiComboBox.setFont(new Font("Monospaced", Font.BOLD, 10 * scale));
        guiComboBox.setBorder(border);
        guiComboBox.setFocusable(false);

        guiComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList<?> list, final Object value,
                    final int index, final boolean isSelected, final boolean cellHasFocus) {
                final JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        /**
         * Creation of the combo box that permits to choose the size of the menu.
         */
        guiComboBox.setPreferredSize(startButton.getPreferredSize());
        guiComboBox.setMaximumSize(startButton.getPreferredSize());
        guiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stopBackgroundMusic();
                guiComboBox.setPreferredSize(startButton.getPreferredSize());
                guiComboBox.setMaximumSize(startButton.getPreferredSize());
                final String selectedScale = (String) guiComboBox.getSelectedItem();
                if (!"GUI SCALE".equals(selectedScale)) {
                    int scalerange = 2;
                    switch (selectedScale) {
                        case "Tiny":
                            scalerange = 1;
                            break;
                        case "Small":
                            scalerange = 2;
                            break;
                        case "Medium":
                            scalerange = 3;
                            break;
                        case "Large":
                            scalerange = 4;
                            break;
                        default:
                            break;
                    }
                    game.changeScale(scalerange);
                }
            }
        });
        frame.getContentPane().add(guiComboBox, BorderLayout.CENTER);
        panel.add(guiComboBox);
        panel.add(Box.createVerticalStrut(10 * scale));

        /**
         * Creation of the combo box that permits to choose between 3 songs to be
         * played.
         */
        final String sound1 = "src/main/resources/it/unibo/superpeach/music/sound1.wav";
        final String sound2 = "src/main/resources/it/unibo/superpeach/music/sound2.wav";
        final String sound3 = "src/main/resources/it/unibo/superpeach/music/sound3.wav";
        final String[] songList = { "MUSIC", "n. 1", "n. 2", "n. 3", "no music" };
        final JComboBox<String> songComboBox = new JComboBox<>(songList);
        songComboBox.setLayout(new FlowLayout());
        songComboBox.setBackground(customColor);
        songComboBox.setForeground(customColor1);
        songComboBox.setFont(new Font("Monospaced", Font.BOLD, 10 * scale));
        songComboBox.setBorder(border);
        songComboBox.setFocusable(false);

        songComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList<?> list, final Object value,
                    final int index, final boolean isSelected, final boolean cellHasFocus) {
                final JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        songComboBox.setPreferredSize(startButton.getPreferredSize());
        songComboBox.setMaximumSize(startButton.getPreferredSize());
        songComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String selectedSong = (String) songComboBox.getSelectedItem();
                if (!"MUSIC".equals(selectedSong)) {
                    if (!"no music".equals(selectedSong)) {
                        String path = "";
                        switch (selectedSong) {
                            case "n. 1":
                                path = sound1;
                                break;
                            case "n. 2":
                                path = sound2;
                                break;
                            case "n. 3":
                                path = sound3;
                                break;
                            default:
                                break;
                        }
                        playSong(path);
                    } else {
                        stopBackgroundMusic();
                    }
                }
            }
        });

        frame.getContentPane().add(songComboBox, BorderLayout.CENTER);
        panel.add(songComboBox);
        panel.add(Box.createVerticalStrut(10 * scale));

        /**
         * Creation of the START button.
         * If clicked it closes the menu.
         */
        final CustomButton exitButton = new CustomButton("EXIT", customColor, customColor1, scale);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                closeWindow();
            }
        });
        panel.add(exitButton);

        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * It plays the selected song if there's no other song playing.
     * If there's one playing it stops it.
     * 
     * @param songFilePath
     */
    private void playSong(final String songFilePath) {
        stopBackgroundMusic();
        try {
            final File audioFile = new File(songFilePath);
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger("Exception in PeachMenu.playSong()");
        }
    }

    /**
     * It stops the music already playing in background.
     */
    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    /**
     * It closes the window.
     */
    public void closeWindow() {
        frame.setVisible(false);
        frame.dispose();
    }
}
