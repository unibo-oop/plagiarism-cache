package controller.ballscontroller.launch;

import element.Vector2D;

public interface LaunchController {


    /**
     * @param v the vector to check
     * @return true if the vector is valid
     */
    boolean isValidVector(Vector2D v);

    /**
     * @param direction the direction of the launch
     * @throws IllegalArgumentException if the vector is null, arithmetic null or with yComponent <=0 or not valid
     */

    void setVector(Vector2D direction);


    /**
     * launch the balls
     *
     * @throws IllegalStateException if there is no direction
     */
    void launch();

}
