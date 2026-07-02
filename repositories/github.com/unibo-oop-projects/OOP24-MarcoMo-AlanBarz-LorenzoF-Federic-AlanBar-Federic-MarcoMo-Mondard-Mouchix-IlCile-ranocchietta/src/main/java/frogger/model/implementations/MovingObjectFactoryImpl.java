package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The factory for moving object, you can create all the type of moving object, Car, Eagle, Trunk.
 * implement MovingObjectFactory
 */
public class MovingObjectFactoryImpl implements MovingObjectFactory {
    private static final Logger LOGGER = Logger.getLogger(MovingObjectFactoryImpl.class.getName());

    /**
     * generic method to create a MovingObject element.
     * @param <X> type of the MovingObject
     * @param pos
     * @param dimension
     * @param speed
     * @param direction
     * @param c java.lang.Class to instance the new MovingObject
     * @return An object of type X that extends MovingObject
     */
    @Override
    public <X extends MovingObject> X createMovingObject(final Position pos, final Pair dimension, final float speed,
        final Direction direction, final Class<X> c) {
        try {
            return c.getConstructor(Position.class, Pair.class, float.class, Direction.class)
            .newInstance(pos, dimension, speed, direction);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            LOGGER.log(Level.SEVERE, "Error creating MovingObject: " + ex.getMessage(), ex);
            return null;
        }
    }
}
