package it.unibo.isaccoop.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.isaccoop.core.GameEngineImpl;

/**
 * The menu of the game.
 * */
public class GameMenu {

    private static final Logger LOGGER = Logger.getLogger(SwingScene.class.getName());
    private final JFrame frame = new JFrame("Game menu - Isaccoop");
    private final JButton play = new JButton("Play");
    private final JButton help = new JButton("Help");
    private final JButton quit = new JButton("Quit");
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final int sw = (int) screen.getWidth();
    private final int sh = (int) screen.getHeight();
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 1000;
    private static final int FONT_SIZE = 30;

    /**
     * Enum for setting colors.
     * */
    public enum Colors {
        /**
         * Menu background-color.
         */
        MENU(150, 75, 50);

        private final int x;
        private final int y;
        private final int z;
        Colors(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * @return the first parameter in the rgb standard
         */
        public int getX() {
            return this.x;
        }

        /**
         * @return the second parameter in the rgb standard
         */
        public int getY() {
            return this.y;
        }

        /**
         * @return the third parameter in the rgb standard
         */
        public int getZ() {
            return this.z;
        }
    }

    /**
     * GameMenu constructor.
     */
    public GameMenu() {
        this.frame.getContentPane().setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));
        this.frame.getContentPane().setBackground(new java.awt.Color(Colors.MENU.getX(), Colors.MENU.getY(), Colors.MENU.getZ()));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel canvas = new JPanel();
        canvas.setBackground(new java.awt.Color(Colors.MENU.getX(), Colors.MENU.getY(), Colors.MENU.getZ()));
        final JPanel buttons = new JPanel();
        buttons.setBackground(new java.awt.Color(Colors.MENU.getX(), Colors.MENU.getY(), Colors.MENU.getZ()));
        final JLabel title = new JLabel("Isaccoop");
        title.setFont(new Font("Arial", Font.PLAIN, sw / FONT_SIZE));

        canvas.add(title);
        buttons.add(play);
        buttons.add(help);
        buttons.add(quit);

        canvas.setAlignmentX(Component.CENTER_ALIGNMENT);
        canvas.setPreferredSize(new Dimension(sw, sh / 2));
        canvas.setMaximumSize(new Dimension(sw, sh / 2));

        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttons.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        frame.getContentPane().add(canvas);
        frame.getContentPane().add(buttons);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Thread thread = new Thread() {
                    @Override
                    public void run() {
                        new GameEngineImpl().run();
                    }
                };
                thread.start();
                hide();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    Runtime.getRuntime().exit(0);
                } catch (SecurityException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            new HelpGUI().display();
                        } catch (IOException ex) {
                            LOGGER.severe(ex.getMessage());
                        }
                    }
                };
                thread.start();
            }
        });
    }

    /**
     * Method to display the game menu.
     * */
    public void display() {
        this.frame.setSize(sw / 2, sh / 2);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    /**
     * Method to hide the game menu.
     * */
    public void hide() {
        this.frame.dispose();
    }
}
