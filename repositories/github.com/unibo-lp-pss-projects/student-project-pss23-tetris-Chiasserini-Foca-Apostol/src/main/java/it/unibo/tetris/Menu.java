package it.unibo.tetris;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class {@link Menu} that implements the game's Menu.
 * extends {@link JPanel}.
 */
public class Menu extends JPanel{
    /**
     * Variables for {@link JButtons}.
     */
    private JButton startButton;
    private JButton tutorialButton;
    private JButton quitButton;
    
    /**
     * {@link JFrame} and {@link JPanel}.
     */
    private JPanel mainPanel;
    private JFrame frame;

    /**
     * {@link ImageIcon} icon of the window.
     */
    private ImageIcon pageIcon = new ImageIcon("src/res/img/icon.png"); 

    /**
     * Utility for Screensizes.
     */
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = toolkit.getScreenSize();

    /**
     * Screensizes costants.
     */ 
    public final int WIDTH = screenSize.width; 
    public final Integer HEIGHT = screenSize.height; 

    public Menu() {
        /*
         * Creating JButtons.
         */
        this.startButton = this.createButton("START", 
                                            this.WIDTH/9, 
                                            this.HEIGHT/8);
        this.tutorialButton = this.createButton("TUTORIAL", 
                                                this.WIDTH/9, 
                                                this.HEIGHT/8);
        this.quitButton = this.createButton("QUIT", 
                                            this.WIDTH/9,
                                            this.HEIGHT/8);

        /*
         * Creating JPanel setting his background and layout.
         */
        this.mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(new GridLayout(3, 1, 10, 30));

        /*
         * Adding buttons to JPanel.
         */ 
        mainPanel.add(startButton, BorderLayout.NORTH);
        mainPanel.add(tutorialButton, BorderLayout.CENTER);
        mainPanel.add(quitButton, BorderLayout.SOUTH);

        /*
         * Creating the JFrame.
         */
        this.frame = createFrame("Tetris", screenSize);
        frame.add(mainPanel);

        /*
         * Adding border to mainPanel.
         */
        Border border = BorderFactory.createEmptyBorder(300, 
                                                        300,
                                                        300,
                                                        300);
        mainPanel.setBorder(border);
        mainPanel.setPreferredSize(new Dimension(WIDTH / 2, 
                                   (int) Math.round(HEIGHT * 0.9)));

        /*
         * Adding function to startButton.
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFrame gameWindow = createFrame("Tetris", 
                                                new Dimension(WIDTH/2, 
                                                (int)Math.round(HEIGHT*0.9)));

                /*
                 * Adding the GamePanel to the window.
                 */
                GamePanel gp = new GamePanel();
                gameWindow.add(gp);

                /*
                 * Starting the game.
                 */
                gp.launchGame();
                frame.dispose();
            }
        });

        /*
         * Adding function to tutorialButton.
         */
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Tutorial tutorial = new Tutorial(frame);
                Dimension size = tutorial.getImageSize();
                
                frame.setVisible(false);
                JFrame tutorialWindow = createFrame("Tutorial", size);

                tutorialWindow.add(tutorial);
            }
        });

        /*
         * Adding function to quitButton.
         */
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Creates a new {@link JButton}.
     * 
     * @param name
     * @param width
     * @param height
     */
    private JButton createButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0x444444));
        button.setForeground(Color.WHITE);
        return button;
    }

    /**
     * Creates a new {@link JFrame}.
     * 
     * @param title
     * @param screenSize
     */
    private JFrame createFrame(String title, Dimension screenSize ) {

        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(screenSize);
        window.setPreferredSize(screenSize);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setIconImage(pageIcon.getImage());
        return window;
    }
}
