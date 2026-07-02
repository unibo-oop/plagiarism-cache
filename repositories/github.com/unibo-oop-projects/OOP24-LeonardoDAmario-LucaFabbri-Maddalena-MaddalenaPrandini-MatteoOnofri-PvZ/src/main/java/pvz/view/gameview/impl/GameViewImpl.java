package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.GameEntity;
import pvz.utilities.Resolution;
import pvz.view.gameview.api.GameView;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;

/**
 * Implementation of the {@link GameView} interface.
 * This class is responsible for setting up and managing the visual elements of the game,
 * including the toolbar, game grid, and drawable entities.
 * It also connects the view to the controller via the {@link ViewListener} interface.
 */
@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
public final class GameViewImpl extends JPanel implements GameView {

    private static final long serialVersionUID = 1L;
    private static final double SCALING_FACTOR = 0.8;
    private static final double BASE_WIDTH = 640.0;

    /**
     * The game's toolbar displaying controls and stats.
     */
    private final GameToolBar toolBar;

    /**
     * Panel used to draw moving and stationary entities (plants, zombies, bullets, etc.).
     */
    private final DrawPanel drawPanel;

    /**
     * Panel representing the clickable game grid where the player can place plants.
     */
    private final GridPanel gridPanel;

    /**
     * LayeredPane to overlap the grid and drawable components (grid is below, drawPanel above).
     */
    private final JLayeredPane layeredPane = new JLayeredPane();

    /**
     * The main application window.
     */
    private final JFrame frame = new JFrame();

    /**
     * The chosen game resolution used to scale UI elements.
     */
    private final Resolution resolution;

    /**
     * The controller that manages game logic and communication with the view.
     */
    private final transient GameController parentController;


    /**
     * Constructs the game view with the specified controller and resolution.
     *
     * @param controller The {@link GameController} instance managing game logic.
     * @param resolution The {@link Resolution} for scaling UI components.
     */
    private GameViewImpl(final GameController controller, final Resolution resolution) {
        super(new BorderLayout());
        this.parentController = controller;
        this.resolution = resolution;
        final double scaling = SCALING_FACTOR * resolution.getWidth() / BASE_WIDTH;
        this.toolBar = new GameToolBar(scaling);
        this.drawPanel = new DrawPanel(scaling);
        this.gridPanel = new GridPanel(scaling);
    }

    /**
     * Factory method to create and initialize a new GameViewImpl instance.
     *
     * @param controller The {@link GameController} instance managing game logic.
     * @param resolution The {@link Resolution} for scaling UI components.
     * @return A fully initialized GameViewImpl instance.
     */
    public static GameViewImpl createGameViewImpl(final GameController controller, final Resolution resolution) {
        final GameViewImpl view = new GameViewImpl(controller, resolution);
        view.initialize();
        return view;
    }

    /**
     * Initializes the game view by setting up UI components and layout.
     * This method is called after the view is created to ensure all components are ready.
     */
    private void initialize() {
        initComponents();
    }

    /**
     * Initializes and lays out all UI components.
     */
    private void initComponents() {
        layeredPane.setLayout(new OverlayLayout(layeredPane));
        layeredPane.setPreferredSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        drawPanel.setBounds(0, 0, resolution.getWidth(), resolution.getHeight());
        layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(layeredPane, BorderLayout.CENTER);
        configureFrame();
        setViewListener((ViewListener) this.parentController);
    }

    /**
     * Closes the application window and disposes of the frame.
     */
    @Override
    public void close() {
        this.frame.dispose();
    }

    /**
     * Renders the game state, including entity positions and game statistics.
     *
     * @param entities The set of entities to render (plants, zombies, etc.).
     * @param suns     The current sun count.
     * @param kills    The number of zombies defeated.
     */
    @Override
    public void render(final Set<GameEntity> entities, final int suns, final int kills) {
        drawPanel.updateMovingEntities(Set.copyOf(entities));
        toolBar.updateStats(suns, kills);

        SwingUtilities.invokeLater(() -> {
            drawPanel.repaint();
            gridPanel.repaint();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Assigns a listener to receive user input events from the view.
     *
     * @param listener The {@link ViewListener} instance.
     */
    @Override
    public void setViewListener(final ViewListener listener) {
        toolBar.setViewListener(listener);
        gridPanel.setViewListener(listener);
    }

    /**
     * Configures the main application window with title, size, and visibility.
     */
    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(this.resolution.getWidth(), this.resolution.getHeight());
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
