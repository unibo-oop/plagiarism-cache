package it.unibo.javajump.model.states.ingame;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.physics.MovementDirection;
import it.unibo.javajump.model.states.GameState;
import it.unibo.javajump.model.states.GameStateHandler;
import it.unibo.javajump.model.states.pause.PauseState;

import static it.unibo.javajump.model.states.ingame.gameutilities.InGameUtilities.applyPacManEffect;
import static it.unibo.javajump.model.states.ingame.gameutilities.InGameUtilities.checkGameOver;
import static it.unibo.javajump.model.states.ingame.gameutilities.InGameUtilities.convertIntToMovementDirection;
import static it.unibo.javajump.utility.Constants.LEFT_DIRECTION;
import static it.unibo.javajump.utility.Constants.NULL_DIRECTION;
import static it.unibo.javajump.utility.Constants.RIGHT_DIRECTION;

/**
 * The class that implements the in game state.
 */
public final class InGameState implements GameStateHandler {

    /**
     * The Game state.
     */
    static final GameState GAME_STATE = GameState.IN_GAME;

    private int horizontalDirection = NULL_DIRECTION;
    private float deltaTime;

    /**
     * {@inheritDoc} If the action corresponds to a directional key, the direction changes. If the action corresponds
     * to Pause, pauses the game.
     */
    @Override
    public void handleAction(final GameModel model, final GameAction action) {
        switch (action) {
            case MOVE_LEFT -> horizontalDirection = LEFT_DIRECTION;
            case MOVE_RIGHT -> horizontalDirection = RIGHT_DIRECTION;
            case STOP_HORIZONTAL -> horizontalDirection = NULL_DIRECTION;
            case PAUSE_GAME -> model.setState(new PauseState());
            default -> { }
        }
    }

    /**
     * {@inheritDoc} The implementation updates all Game Objects, and applies Pac-Man effect to the playable character.
     * Then, in real-time, it checks the various managers, and keeps generating the level. In update is also checked
     * the game-over condition.
     */
    @Override
    public void update(final GameModel model, final float deltaTime) {
        this.deltaTime = deltaTime;
        final Character player = model.getPlayer();
        final MovementDirection md = convertIntToMovementDirection(horizontalDirection);
        model.getPhysicsManager().updateCharacterMovement(player, deltaTime, md);

        for (final GameObject go : model.getGameObjects()) {
            go.update(deltaTime);
            if (go instanceof CharacterImpl characterImpl) {
                applyPacManEffect(characterImpl, model.getScreenWidth());
            }
        }

        model.getCollisionManager().checkCollisions(model);

        model.getCameraManager().updateCamera(model, deltaTime);

        model.getSpawnManager().generateOnTheFly(model);

        model.getCleanupManager().cleanupObjects(model);

        model.getDifficultyManager().updateDifficulty(model.getScore());

        checkGameOver(model, player);

        model.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return GAME_STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getDeltaTime() {
        return deltaTime;
    }
}
