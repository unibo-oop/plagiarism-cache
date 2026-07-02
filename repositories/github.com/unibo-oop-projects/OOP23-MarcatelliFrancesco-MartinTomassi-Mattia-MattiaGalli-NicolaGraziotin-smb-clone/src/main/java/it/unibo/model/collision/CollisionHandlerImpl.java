package it.unibo.model.collision;

import it.unibo.model.GameModel;

/**
 * Implementation of the CollisionHandler interface responsible for handling collisions
 * and updating the game model accordingly.
 */
public class CollisionHandlerImpl implements CollisionHandler {
    private final CollisionChecker collisionChecker;
    private final GameModel gameModel;

    /**
     * Constructs the CollisionHandlerImpl with the given GameModel.
     * 
     * @param gameModel the game model to be used for collision handling
     */
    public CollisionHandlerImpl(final GameModel gameModel) {
        this.gameModel = gameModel;
        this.collisionChecker = new CollisionCheckerImpl(this);
    }

    /**
     * Retrieves the associated GameModel.
     * 
     * @return the GameModel associated with this collision handler
     */
    @Override
    public GameModel getGameModel() {
        return this.gameModel;
    }

    /**
     * Checks collisions and updates the game model based on the collision state.
     */
    @Override
    public void check() {
        if (collisionChecker.inWindow().equals(CollisionChecker.CollisionState.FALL)) {
            gameModel.getMeatBoy().setX(gameModel.getMeatBoyStartCoord().getX());
            gameModel.getMeatBoy().setY(gameModel.getMeatBoyStartCoord().getY());
            gameModel.died();
        } else {
            if (collisionChecker.getState().equals(CollisionChecker.CollisionState.SAW)) {
                gameModel.getMeatBoy().setX(gameModel.getMeatBoyStartCoord().getX());
                gameModel.getMeatBoy().setY(gameModel.getMeatBoyStartCoord().getY());
                gameModel.died();
            } else if (collisionChecker.getState().equals(CollisionChecker.CollisionState.BANDAGE_GIRL)) {
                gameModel.victory();
            }
        }
    }

    /**
     * Passes the key code to the CollisionChecker for moving MeatBoy.
     * 
     * @param keyCode the key code representing the pressed key
     */
    @Override
    public void moveMeatBoy(final int keyCode) {
        this.collisionChecker.moveMeatBoy(keyCode);
    }

    /**
     * Passes the key code to the CollisionChecker to stop MeatBoy's movement.
     * 
     * @param keyCode the key code representing the released key
     */
    @Override
    public void stopMovingMeatBoy(final int keyCode) {
        this.collisionChecker.stopMovingMeatBoy(keyCode);
    }

    /**
     * Updates MeatBoy's position using the CollisionChecker.
     */
    @Override
    public void updateMeatBoy() {
        this.collisionChecker.updateMeatBoy();
    }

    /**
     * Initializes the states of the game.
     * This method is responsible for setting up or resetting the various states of the game.
     */
    @Override
    public void initializeStates() {
        this.collisionChecker.initializeStates();
    }
}
