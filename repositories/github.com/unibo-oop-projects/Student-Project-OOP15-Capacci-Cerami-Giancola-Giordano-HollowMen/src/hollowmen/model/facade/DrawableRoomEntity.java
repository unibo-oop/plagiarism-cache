package hollowmen.model.facade;

import hollowmen.enumerators.ActorState;

/**
 * Give information about {@code RoomEntity}
 * 
 * @author Giordo
 *
 */
public interface DrawableRoomEntity {
    /**
     * @return Gives the name of the RE
     */
    public String getName();
    /**
     * @return Gives the position of the RE
     */
    public Point2D getPosition();
    /**
     * @return Gives the facing of the RE
     */
    public boolean isFacingRight();
    /**
     * @return Gives the state of the RE
     */
    public ActorState getState();
}
