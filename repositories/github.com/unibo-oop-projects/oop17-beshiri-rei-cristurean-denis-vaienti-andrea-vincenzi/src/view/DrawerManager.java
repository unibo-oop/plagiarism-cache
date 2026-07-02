package view;

import java.util.List;

import controller.time.TimeEventListener;
import model.animated.Animated;
import model.room.Room;

/**
 * Class that manage the drawer on the two canvas of the game.
 *
 */
public interface DrawerManager extends TimeEventListener {

    /**
     * Set Player life.
     * @param life life.
     */
    void setPlayerLife(int life);

    /**
     * Set Animated entities.
     * @param entities list of entities.
     */
    void setAnimatedEntities(List<Animated> entities);

    /**
     * Set actual room.
     * @param room actual room.
     */
    void setRoom(Room room);

    /**
     * Draw scene.
     */
    void draw();

    /**
     * Resize dimension of canvas in the scene.
     */
    void resize();

    /**
     * Method used to redraw after a resize event.
     */
    void redrawAfterResize();

    /**
     * Method used for initialize time canvas.
     */
    void initTimeCanvas();
}
