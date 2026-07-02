package model.physics;

import model.entitiesutil.Body;

/**
 *class that manages power_up movements.
 *they basically just fall and stay there so the class just extends {@link FallableMovement}
 */
public class ObjectMovement extends FallableMovement {

    /**
     * object movement constructor.
     * @param body 
     * ObjectMovement is associated to {@link Body} of which it changes the position.
     */
    public ObjectMovement(final Body body) {
        super(body);
    }
}
