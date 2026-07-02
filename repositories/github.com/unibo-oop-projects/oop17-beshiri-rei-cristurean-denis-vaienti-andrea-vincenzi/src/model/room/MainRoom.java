package model.room;

import java.util.List;

import model.hitbox.HitBox;
import model.inanimated.Button;
import model.inanimated.Door;
import model.inanimated.Wall;
import utility.ImageType;

/** 
 * MainRoom implements.
 *
 */
public class MainRoom extends AbstractRoom {
    private final Button button;

    /**
     * Constructor for the room.
     * @param h HitBox of MainRoom.
     * @param doors MainRoom's doors.
     * @param button MainRoom's button.
     * @param walls list of the walls in the room.
     * @param backImg background image.
     */
    public MainRoom(final HitBox h, final List<Door> doors, final Button button, final List<Wall> walls, final ImageType backImg) {
        super(h, doors, walls, backImg);
        this.button = button;
    }

    /**
     * Return button in main room.
     * @return MainRoom's button.
     */
    public Button getButton() {
        return button;
    }
}
