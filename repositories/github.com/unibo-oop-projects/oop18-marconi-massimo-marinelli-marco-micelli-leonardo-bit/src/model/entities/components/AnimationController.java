package model.entities.components;
/**
 * Models a basic controller for entities which have an animated texture.
 * An AnimationController is designed to loop a specific animation on-demand.
 */
public interface AnimationController {

    /**
     * Loops an AnimationChannel in which the enitity stays idle.
     * Every animated enitity must have at least this method in it's moveset.
     */
    void stayIdle();
}
