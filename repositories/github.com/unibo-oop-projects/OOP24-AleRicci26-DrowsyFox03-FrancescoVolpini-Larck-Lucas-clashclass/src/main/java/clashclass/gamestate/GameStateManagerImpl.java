package clashclass.gamestate;

import clashclass.elements.ComponentFactoryImpl;
import clashclass.engine.GameEngine;

import clashclass.saveload.PlayerVillageDecoderImpl;


import java.util.function.Supplier;

/**
 * Represents a {@link GameStateManager} implementation.
 */
public class GameStateManagerImpl implements GameStateManager {
    private final Supplier<GameStateController> playerVillageStateCreator;
    private final Supplier<GameStateController> battleStateCreator;
    private final GameEngine gameEngine;
    private GameStateController currentGameStateController;

    /**
     * Constructs the game state manager.
     *
     * @param gameEngine the game engine
     * @param playerVillageStateCreator the player village state creator
     * @param battleStateCreator the battle state creator
     */
        public GameStateManagerImpl(
            final GameEngine gameEngine,
            final Supplier<GameStateController> playerVillageStateCreator,
            final Supplier<GameStateController> battleStateCreator) {
        this.gameEngine = gameEngine;
        this.playerVillageStateCreator = playerVillageStateCreator;
        this.battleStateCreator = battleStateCreator;

        final var decoder = new PlayerVillageDecoderImpl();
        decoder.setComponentFactory(new ComponentFactoryImpl());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatePlayerVillage() {
        this.clearCurrentState();
        this.currentGameStateController = this.playerVillageStateCreator.get();
        this.currentGameStateController.setGameStateManager(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateBattle() {
        this.clearCurrentState();
        this.currentGameStateController = this.battleStateCreator.get();
        this.currentGameStateController.setGameStateManager(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startEngine() {
        this.gameEngine.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopEngine() {
        this.gameEngine.stop();
    }

    private void clearCurrentState() {
        if (this.currentGameStateController != null) {
            this.currentGameStateController.clearScene();
        }
    }
}
