package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;

/**
 * Definition of the NonInteractiveBuilder interface. Creates a
 * {@link it.unibo.ninjafrog.world.NonInteractiveObject NonInteractiveObject}.
 */
public interface NonInteractiveBuilder {
    /**
     * Object setter.
     * 
     * @param object The MapObject object which you want to define.
     * @return The builder object itself.
     */
    NonInteractiveBuilder selectObject(MapObject object);

    /**
     * Setter of the category bit of the object.
     * 
     * @param bit The short you want to set as category bit of the object.
     * @return The builder object itself.
     */
    NonInteractiveBuilder chooseCategoryBit(Short bit);

    /**
     * Build method to be called when you have set the previous two parameters.
     * 
     * @return A new {@link it.unibo.ninjafrog.world.NonInteractiveObject
     *         NonInteractiveObject}.
     */
    NonInteractiveObject build();
}
