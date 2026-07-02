package controllers;

import model.mario.MarioType;
import view.MarioView;
import model.mario.MarioModel;

/**
 * This interface starts and controls the Rectangle of the Mario view.
 */
public interface MarioController {

    /**
     * Call {@link MarioView} methods and set the view of Mario.
     */
    void startMarioView();

    /**
     * Get the view of Mario.
     * @return MarioView object
     */
    MarioView getMarioView();

    /**
     * Get the model of Mario.
     * @return MarioModel object
     */
    MarioModel getMarioModel();

    /**
     * Change Mario skin while he is jumping, call {@link MarioView#setImg(String)} to change the
     * image path.
     * }
     * @param type, to know if Mario is jumping.
     */
    void changeMarioSkin(MarioType type);

    /**
     * Mario moves up or down, call {@link MarioModel#marioUpdate(double)} and {@link MarioView#updatePosition(double)}.
     * @param n specifies the range of the movement.
     */
    void marioMovement(double n);
}
