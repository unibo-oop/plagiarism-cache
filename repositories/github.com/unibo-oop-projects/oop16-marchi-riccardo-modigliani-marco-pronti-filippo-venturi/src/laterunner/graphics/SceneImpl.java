package laterunner.graphics;

import laterunner.core.GameEngine;
import laterunner.input.Controller;
import laterunner.input.MoveLeft;
import laterunner.input.MoveRight;
import laterunner.input.Stop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Scene Implementation.
 *
 */
public class SceneImpl extends JFrame implements Scene, KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 960;
    private static final int HEIGHT = 720;
    private static CardLayout cl = new CardLayout();
    private static JPanel cont = new JPanel(cl);
    private Road road;
    private Menu menu;
    private Market shop;
    private Stats stats;
    private Controller controller;
    private GameEngine gameEngine;

    /**
     * Common Scene Constructor: instantiates all the panels and set Menu as first view.
     * 
     * @param gmEngine
     *          instance of Controller class GameEngine
     * @param contrl
     *          instance of Controller class Controller
     */
    public SceneImpl(final GameEngine gmEngine, final Controller contrl) {

        this.addKeyListener(this);
        this.controller = contrl;
        this.gameEngine = gmEngine;
        road = new Road(this.gameEngine);
        menu = new Menu(this.road, this.gameEngine);
        shop = new Market();
        stats = new Stats();

        cont.add(road, "road");
        cont.add(menu, "menu");
        cont.add(shop, "shop");
        cont.add(stats, "stats");
        changePanel("menu");

        add(cont, BorderLayout.CENTER);
        setSize(WIDTH, HEIGHT);
        setTitle("Late Runner");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
        setVisible(true);
    }

    @Override
    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.repaint();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.notifyCommand(new MoveRight());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.notifyCommand(new MoveLeft());
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) { }

    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.notifyCommand(new Stop());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.notifyCommand(new Stop());
        }
    }

    @Override
    public void setInputController(final Controller c) {
        controller = c;
    }

    @Override
    public Road getRoad() {
        return road;
    }

    /**
     * Switches between panels in order to show the desired one.
     * 
     * @param name
     *          the name of the panel to show
     */
    public static void changePanel(final String name) {
        cl.show(cont, name);
    }

    /**
     * Gets the content panel.
     * 
     * @return
     *          content panel previously defined
     */
    public static JPanel getCont() {
        return cont;
    }
}