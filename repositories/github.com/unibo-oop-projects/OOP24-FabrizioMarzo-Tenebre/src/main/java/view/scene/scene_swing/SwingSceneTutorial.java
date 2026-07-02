package view.scene.scene_swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.game_model.game_level.IGameLevel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import input.input_controller.InputController;
import view.graphics.GraphicsLevel;
import view.graphics.GraphicsMunition;
import view.graphics.GraphicsSurvivor;
import view.graphics.GraphicsZombie;
import view.graphics.graphics_swing.SwingGraphicsLevel;
import view.graphics.graphics_swing.SwingGraphicsMunition;
import view.graphics.graphics_swing.SwingGraphicsSurvivor;
import view.graphics.graphics_swing.SwingGraphicsZombie;
import view.graphics_util.Scaler;
import view.graphics_util.ScalerFactory;
import view.graphics_util.ScalerFactoryImpl;
import view.scene.Scene;

/**
 * A Swing-based implementation of a tutorial scene.
 * Creates a resizable window displaying the game level and entities,
 * handling user input via keyboard events.
 */
public class SwingSceneTutorial implements Scene {

    private JFrame frame;
    private SceneTutorialPanel panel;
    private IGameLevel tutLevel;
    private InputController inputContrl;
    private Scaler viewScale;
    private ScalerFactory scaleFactory;
    private int w, h;

    /**
     * Constructs the tutorial scene with the given game level, input controller,
     * and initial dimensions.
     *
     * @param tutLevel    the tutorial game level to display
     * @param inputContrl the controller handling user inputs
     * @param w           the initial width of the window
     * @param h           the initial height of the window
     */
    public SwingSceneTutorial(final IGameLevel tutLevel, final InputController inputContrl, final int w, final int h) {

        frame = new JFrame("L'armata delle Tenebre");
        frame.setMinimumSize(new Dimension(w, h));
        frame.setResizable(true);

        this.tutLevel = tutLevel;
        this.inputContrl = inputContrl;
        this.w = w;
        this.h = h;
        this.scaleFactory = new ScalerFactoryImpl();
        this.viewScale = scaleFactory.viewScaler((int) tutLevel.getLevel().getLevelHeight(),
                (int) tutLevel.getLevel().getLevelWidth(), h, w);

        panel = new SceneTutorialPanel();
        panel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                viewScale.setScaleDimensions(panel.getHeight(), panel.getWidth());
            }
        });

        frame.getContentPane().add(panel);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }

            public void windowClosed(WindowEvent ev) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Renders the scene by repainting the frame on the Swing event thread.
     */
    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                frame.repaint();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inner JPanel class responsible for drawing the game scene
     * and forwarding keyboard events to the input controller.
     */
    public class SceneTutorialPanel extends JPanel implements KeyListener {

        /**
         * Initializes the panel with preferred size and keyboard listeners.
         */
        public SceneTutorialPanel() {
            setPanelSize();
            this.addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        /**
         * Sets the preferred size of the panel.
         */
        public void setPanelSize() {
            Dimension size = new Dimension(w, h);
            setPreferredSize(size);
        }

        /**
         * Paints the game components (level, survivor, zombies, munitions).
         *
         * @param g the Graphics context
         */
        @Override
        public void paintComponent(final Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            GraphicsLevel graphLvl = new SwingGraphicsLevel(g2d, viewScale);
            GraphicsSurvivor graphSur = new SwingGraphicsSurvivor(g2d, viewScale);
            GraphicsZombie graphZob = new SwingGraphicsZombie(g2d, viewScale);
            GraphicsMunition graphMun = new SwingGraphicsMunition(g2d, viewScale);

            tutLevel.updateGraphics(graphLvl);
            tutLevel.getGameSurvivor().updateGraphics(graphSur);
            tutLevel.getGameZombie().stream()
                    .filter(Objects::nonNull)
                    .forEach(gameZombie -> gameZombie.updateGraphics(graphZob));

            tutLevel.getGameMunitions().stream()
                    .filter(Objects::nonNull)
                    .forEach(gameMunition -> gameMunition.updateGraphics(graphMun));

        }

        /**
         * Forwards key pressed events to the input controller.
         *
         * @param e the key event
         */
        @Override
        public void keyPressed(KeyEvent e) {
            inputContrl.notifyInput(e.getKeyCode());
        }

        /**
         * Notifies the input controller that no key is pressed when a key is released.
         *
         * @param e the key event
         */
        @Override
        public void keyReleased(KeyEvent e) {
            inputContrl.notifyNoInput();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

}
