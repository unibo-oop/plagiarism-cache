package frogger.model.interfaces;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;

/**
 * The factory for moving object, you can create all the type of moving object, Car, Eagle, Trunk.
 */
public interface MovingObjectFactory {

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
    <X extends MovingObject> X createMovingObject(Position pos, Pair dimension,
            float speed, Direction direction, Class<X> c);

}
