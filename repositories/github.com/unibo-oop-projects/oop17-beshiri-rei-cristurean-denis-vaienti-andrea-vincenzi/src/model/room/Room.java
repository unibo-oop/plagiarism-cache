package model.room;

import java.util.List;
import model.hitbox.HitBox;
import model.inanimated.Door;
import model.inanimated.Wall;
import utility.ImageType;

/**
 * Interface that represents rooms in the world.
 * 
 */
public interface Room {

    /**
     * Get doors of the room.
     * 
     * @return a list of actual room's doors.
     */
    List<Door> getDoors();

    /**
     * Get hitBox of the room.
     * 
     * @return HitBox of the room.
     */
    HitBox getHitBox();

    /**
     * Get walls of the room.
     * 
     * @return List of walls i the room.
     */
    List<Wall> getWalls();

    /**
     * Getter for background image of the room.
     * 
     * @return image type of background.
     */
    ImageType getBackgroundImage();
}
