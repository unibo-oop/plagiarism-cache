package frogger.model.implementations;

import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;

/**
 * Represents a Car object in the game, which moves in a specified direction and speed.
 * Cars may vary in size (e.g., regular car or truck) and are assigned the appropriate image
 * based on their dimension and direction.
 */
public class Car extends MovingObjectImpl {

    /**
     * Constructs a Car object.
     *
     * @param pos       the initial position of the car
     * @param dimension the size of the car (width and height in blocks)
     * @param speed     the movement speed of the car
     * @param direction the initial movement direction of the car
     */
    public Car(final Position pos, final Pair dimension, final float speed, final Direction direction) {
        super(pos, dimension, speed, direction);
        super.setImage(findImg().toString());
    }

    /**
     * Determines the appropriate image filename for the car based on its size and direction.
     *
     * @return a StringBuilder containing the image filename (e.g., "carLeft.png", "trukRight.png")
     */
    private StringBuilder findImg() {
        final StringBuilder result = new StringBuilder();
        if (super.getDimension().width() > 1) {
            result.append("truk");
        } else {
            result.append("car");
        }

        if (super.getDirection().equals(Direction.LEFT)) {
            result.append("Left");
        } else {
            result.append("Right");
        }
        result.append(".png");
        return result;
    }
}
