package model.entities;

import controller.input.ControllerInput;
import model.utilities.Position;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;

public interface GameObject {

    /**
     * @param height height to set
     */
    void setHeight(int height);

    /**
     * @param width width to set
     */
    void setWidth(int width);

    /**
     * @return the height
     */
    int getHeight();

    /**
     * @return the width
     */
    int getWidth(); 

    /**
     * @return the position
     */
    Position getPos();

    /**
     * @param pos position to set
     */
    void setPos(Position pos);

    /**
     * @return the direction vector
     */
    DirVector getDirVector();

    /**
     * @param dirVel direction to set
     */
    void setDirVector(DirVector dirVel);

    /**
     * @param speed speed to set
     */
    void setSpeed(double speed);

    /**
     * @return the speed
     */
    double getSpeed();

    /**
     * @param timeElapsed from game loop
     * @param board the board of the game
     */
    void updatePhysics(int timeElapsed, GameBoardImpl board);

    /**
     * @param controller controller of input of this gameObject
     */
    void updateInput(ControllerInput controller);

    /**
     * 
     * @param adapterGraphics adapter used to fit the object to the screen
     */
    void updateGraphics(AdapterGraphics adapterGraphics);

}
