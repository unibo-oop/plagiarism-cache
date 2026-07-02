package it.unibo.ninjafrog.frog;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

public interface FrogController {

    /**
     * @return the frog's body.
     */
    Body getBody();

    /**
     * @param dt delta of time;
     */
    void update(float dt);

    /**
     * @return true if the game is paused.
     */
    boolean isPaused();

    /**
     * draw the frog.
     * 
     * @param batch the batch;
     */
    void draw(Batch batch);

    /**
     * handle the keyboard's input.
     */
    void handleInput();

    /**
     * @return the frog's model.
     */
    FrogModel getModel();

    /**
     * @return true if the frog is running right.
     */
    boolean isRunningRight();

    /**
     * @return true if the double jump is active.
     */
    boolean isDoubleJumpActive();

    /**
     * @return the frog's state.
     */
    FrogState getState();

    /**
     * @return the frog's prev state.
     */
    FrogState getPrevState();

    /**
     * @param currentState the frog state.
     */
    void setPrevState(FrogState currentState);

    /**
     * @param b the param.
     */
    void setDoubleJumping(boolean b);

}
