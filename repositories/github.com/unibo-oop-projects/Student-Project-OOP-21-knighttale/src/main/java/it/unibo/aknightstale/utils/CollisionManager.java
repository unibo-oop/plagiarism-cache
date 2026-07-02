package it.unibo.aknightstale.utils;

import java.util.List;

import it.unibo.aknightstale.controllers.entity.EntityController;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;

public interface CollisionManager {
    /**
     * Checks if a character has collisions with other characters.
     * 
     * @param ec the character to control.
     * @return a list of characters with whom he has collisions.
     */
    List<EntityController<? super Character, ? super AnimatedEntityView>> checkCollision(
            EntityController<? super Character, ? super AnimatedEntityView> ec);

    /**
     * Controls in which directions the character can move.
     * 
     * @param ec the character to control.
     * @return a list of directions in which it can move
     */
    List<Direction> checkDirections(EntityController<? extends Character, ? extends AnimatedEntityView> ec);

    /**
     * Controls the collisions of each character.
     * 
     * @return a list of lists of characters colliding with each other.
     */
    List<List<EntityController<? super Character, ? super AnimatedEntityView>>> update();

    /**
     * Set the width of screen.
     * 
     * @param widthScreen
     */
    void setWidthScreen(double widthScreen);

    /**
     * Set the height of screen.
     * 
     * @param heightScreen
     */
    void setHeightScreen(double heightScreen);

}
