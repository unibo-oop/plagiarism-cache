package main.java.com.view;

import java.awt.Rectangle;

import main.java.com.utility.Position;

/**
 * Interface that models the view for the apple entity.
 */
public interface AppleView extends DrawableGameEntity {

    /**
     * 
     * @return the {@link Rectangle} that graphically represents the apple.
     */
    Rectangle getRect();

    /**
     * Sets the position of the apple.
     * 
     * @param p the position
     */
    void setPosition(Position p);
}
