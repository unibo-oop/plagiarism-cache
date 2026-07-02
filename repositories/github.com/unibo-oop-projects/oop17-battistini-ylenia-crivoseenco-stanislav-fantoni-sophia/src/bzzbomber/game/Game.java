package bzzbomber.game;

import java.awt.Toolkit;

import bzzbomber.controller.Controller;
import bzzbomber.model.Model;
import bzzbomber.model.utilities.FileLoader;
import bzzbomber.model.utilities.Pair;
import bzzbomber.view.menu.ViewManager;
import bzzbomber.view.menu.ViewManagerImpl;

/**
 * Class for Game, that create instance of Controller, View and Model.
 */
public class Game {

    /**
     * Game title.
     */
    public static final String GAME_TITLE = "BzzBomber";
    private static final String MAP_DIMENSION_FILE = "/Map/mapDimension.map";

    private static final Pair<Integer, Integer> MAP_DIMENSIONS = FileLoader.loadMapDimension(MAP_DIMENSION_FILE);
    /**
     * Map Columns.
     */
    public static final int MAP_COLUMNS = (Integer) MAP_DIMENSIONS.getSecond();
    /**
     * Map rows.
     */
    public static final int MAP_ROWS = (Integer) MAP_DIMENSIONS.getFirst();
    /**
     * Window width.
     */
    public static final int WINDOW_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
    /**
     * Window height.
     */
    public static final int WINDOW_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);

    /**
     * HUD width percentage.
     */
    public static final double HUD_WIDTH_PERCENTAGE = 1.0;
    /**
     * HUD height percentage.
     */
    public static final double HUD_HEIGHT_PERCENTAGE = 0.1;
    private static final int HUD_W = (int) (WINDOW_WIDTH * HUD_WIDTH_PERCENTAGE);
    /**
     * HUD width.
     */
    public static final int HUD_WIDTH = HUD_W - (HUD_W % MAP_COLUMNS);
    private static final int HUD_H = (int) (WINDOW_HEIGHT * HUD_HEIGHT_PERCENTAGE);
    /**
     * HUD height.
     */
    public static final int HUD_HEIGHT = HUD_H;

    /**
     * Game field width percentage.
     */
    public static final double GAME_FIELD_WIDTH_PERCENTAGE = 1.0;
    /**
     * Game field height percentage.
     */
    public static final double GAME_FIELD_HEIGHT_PERCENTAGE = 0.9 - HUD_HEIGHT_PERCENTAGE;
    private static final int GAME_FIELD_W = (int) (WINDOW_WIDTH * GAME_FIELD_WIDTH_PERCENTAGE);
    /**
     * Game field width.
     */
    public static final int GAME_FIELD_WIDTH = GAME_FIELD_W - (GAME_FIELD_W % MAP_COLUMNS);
    private static final int GAME_FIELD_H = (int) (WINDOW_HEIGHT * GAME_FIELD_HEIGHT_PERCENTAGE);
    /**
     * Game field height.
     */
    public static final int GAME_FIELD_HEIGHT = GAME_FIELD_H - (GAME_FIELD_H % MAP_ROWS);

    /**
     * Tile width.
     */
    public static final int TILE_WIDTH = GAME_FIELD_WIDTH / MAP_COLUMNS;
    /**
     * Tile height.
     */
    public static final int TILE_HEIGHT = GAME_FIELD_HEIGHT / MAP_ROWS;

    private final Controller controller;

    /**
     * Constructor of Game.
     */
    public Game() {
        final Model model = new Model();
        final ViewManager view = new ViewManagerImpl();
        this.controller = new Controller();

        this.controller.setModel(model);
        this.controller.setView(view);
        model.setController(this.controller);
        view.setController(this.controller);
    }

    /**
     * Method to start the game.
     */
    public void start() {
        this.controller.startFromMainView();
    }

}
