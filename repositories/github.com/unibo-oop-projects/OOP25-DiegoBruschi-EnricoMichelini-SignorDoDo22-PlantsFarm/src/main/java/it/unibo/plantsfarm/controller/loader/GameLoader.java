package it.unibo.plantsfarm.controller.loader;

import it.unibo.plantsfarm.controller.MainScreenController;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;

import java.util.List;

/**
 * Manages the initialization of all game data.
 */
public class GameLoader {

    /**
     * Initializes the game by calling specific loaders.
     *
     * @return The initial GameState.
     */
    public final GameStateImpl initializeGame() {

        final PlantLoader plantLoader = new PlantLoader();
        final List<PlantImpl> plants = plantLoader.loadPlants();
        final GameStateImpl gameState = new GameStateImpl(plants);
        final ImplControllerGamePanel gamePanel = new ImplControllerGamePanel(gameState);
        gamePanel.addView();
        gamePanel.start();
        new MainScreenController(gameState, gamePanel);
        return gameState;
    }
}
