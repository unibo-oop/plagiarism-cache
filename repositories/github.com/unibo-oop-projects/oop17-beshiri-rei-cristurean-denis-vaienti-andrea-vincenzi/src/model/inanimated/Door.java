package model.inanimated;

import model.utility.RoomEnum;
import utility.ImageType;

/**
 * 
 * Interface that represents inanimate object in the world.
 *
 */
public interface Door extends Inanimated {

    /**
     * Check door status.
     * 
     * @return if the door is open or not.
     */
    boolean isOpen();

    /**
     * Set door status.
     * 
     * @param open
     *            The state of the door.
     */
    void setOpen(boolean open);

    /**
     * Get the destination of door.
     * 
     * @return the destination of the door.
     */
    RoomEnum getDestination();

    /**
     * Set the image of the door.
     * 
     * @param img
     *            the new image to set.
     */
    void setImgDoor(ImageType img);
}
