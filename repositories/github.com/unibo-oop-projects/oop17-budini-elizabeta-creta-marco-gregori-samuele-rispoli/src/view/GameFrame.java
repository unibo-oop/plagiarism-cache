package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameEngine;
import controller.GameEngineImpl;

public class GameFrame {
    /**
     * This class is the main frame for a new game It displays the gameScreen panel
     * and the score panel in the game frame
     */

    private final GameScreenImpl gsPanel;
    private final ScoreLifePanel scorePanel;
    private final JFrame frame;
    private final GameScreenImpl gamescreen;
    private final DrawableCanvas canvas;
    private final Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Double HEIGHT = 0.5;
    private static final Double WIDHT = 0.25;
    public final static int WORLD_HEIGHT = 540;
    public final static int WORLD_WIDTH = 460;
    private final GameEngine gameEngine;

    public GameFrame() {

        this.canvas = new DrawableCanvasImpl(WORLD_WIDTH, WORLD_HEIGHT, "level1st_backgroud.png");
        this.gamescreen = new GameScreenImpl(canvas);
        this.gameEngine = new GameEngineImpl(gamescreen);
        gameEngine.setCanvas(gamescreen.getCanvas());
        gameEngine.setHandler(gamescreen.getHandler());
        gameEngine.startGame();

        frame = new JFrame();

        final JPanel mainPanel = new JPanel(new BorderLayout());
        // creating new score panel
        this.scorePanel = new ScoreLifePanel();
        mainPanel.add(this.scorePanel, BorderLayout.NORTH);

        frame.setSize((int) (screenRes.getWidth() * WIDHT), (int) (screenRes.getHeight() * HEIGHT));// prova
        frame.setTitle("Game Donkey Kong");

        // creating new gamescreen panel
        this.gsPanel = gamescreen;
        mainPanel.add(this.gsPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, "Vuoi uscire dal gioco?", "Exit Game?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    gameEngine.abortGameLoop();
                    MenuFrame.getMenuFrame().showMenu();

                }
            }
        });

    }
}
