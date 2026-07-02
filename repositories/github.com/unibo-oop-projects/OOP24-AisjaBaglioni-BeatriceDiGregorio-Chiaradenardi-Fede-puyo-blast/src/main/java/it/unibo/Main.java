package it.unibo;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import it.unibo.controller.GameManager;
import it.unibo.controller.MenuManager;
import it.unibo.model.Scale;

public class Main extends JFrame implements GameListener {
    /**
     * The graphics scaling of the window
     */
    private final Scale scale;
    /**
     * The controller responsible for handling the Menu events
     */
    private MenuManager menuManager;
    /**
     * The controller responsible for handling the Game events
     */
    private GameManager gameManager;

    /**
     * The Main class is the entry point of the application and serves as the main
     * window for the game. It extends JFrame and implements the GameListener
     * interface, meaning it both creates the graphical window and responds to
     * game-related events.
     * 
     * This window is square, non-resizable, and its size is dynamically set to
     * 3/4 of the user's screen resolution.
     * The application initially opens with the main menu.
     * 
     * @param title The title of the application
     */
    public Main(String title) {
        super(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int dim = (int) (Math.min(width, height) * 0.75);
        this.scale = new Scale(dim);
        this.setSize(scale.getScale(), scale.getScale());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menuManager = new MenuManager(this, scale);
        this.add(menuManager);
        this.menuManager.setVisible(true);
    }

    /**
     * This method removes any current game or menu managers from the frame
     * and sets their references to null. This ensures that only one manager (either
     * game or menu) is active at any given time.
     */
    private void resetManager() {
        if (this.gameManager != null) {
            this.gameManager.setVisible(false);
            this.remove(gameManager);
            this.gameManager = null;
        }
        if (this.menuManager != null) {
            this.menuManager.setVisible(false);
            this.remove(menuManager);
            this.menuManager = null;
        }
    }

    /**
     * the startGame method nitializes a new game manager
     * with the appropriate level settings, adds it to the main window, and makes it
     * visible to start the game.
     */
    @Override
    public void startGame(GameEvent e) {
        this.resetManager();
        this.gameManager = new GameManager(this, scale, e.levelConfig);
        this.add(this.gameManager);
        this.gameManager.setVisible(true);
    }

    /**
     * This method transitions the application back to the
     * main menu by clearing the current view and initializing a new visible menu
     * view.
     */
    @Override
    public void goToMainMenu(GameEvent e) {
        this.resetManager();
        this.menuManager = new MenuManager(this, scale);
        this.add(this.menuManager);
        this.menuManager.setVisible(true);
    }

    /**
     * The main method uses EventQueue.invokeLater() to ensure that the creation and
     * updating of the GUI occur on the Event Dispatch Thread, which is the correct
     * thread for Swing components.
     * 
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main("Puyo Pop: Blast!");
            main.setVisible(true);
        });
    }
}