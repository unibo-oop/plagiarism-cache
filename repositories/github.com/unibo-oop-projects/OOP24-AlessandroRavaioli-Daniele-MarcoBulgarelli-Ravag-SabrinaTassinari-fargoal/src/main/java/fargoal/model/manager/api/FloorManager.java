package fargoal.model.manager.api;

import java.util.List;

import fargoal.model.commons.FloorElement;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.player.api.Player;
import fargoal.model.events.api.FloorEvent;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.temple.Temple;
import fargoal.model.map.api.FloorMap;
import fargoal.model.map.api.FloorMask;
import fargoal.view.api.RenderFactory;

/**
 * An interface to model the entirety of the floor.
 */
public interface FloorManager extends SceneManager {

    /**
     * A method to return the instance of the Player.
     * @return - the instance of the player
     */
    Player getPlayer();

    /**
     * A method that returns the list of all the monsters present.
     * @return - the list of monsters
     */
    List<Monster> getMonsters();

    /**
     * A method to obtain the current map.
     * @return - the current floor map
     */
    FloorMap getFloorMap();

    /**
     * A method that returns the current floor level.
     * @return - the current floor level
     */
    int getFloorLevel();

    /**
     * A method that return the list of all the items present.
     * @return - the list of all interactable elements in the floor
     */
    List<Interactable> getInteractables();

    /**
     * Method to increase the level of the floor.
     */
    void increaseFloorLevel();

    /**
     * Method to decrease the level of the floor.
     */
    void decreaseFloorLevel();

    /**
     * A method to obtain the mask of the floor.
     * @return - the current mask
     */
    FloorMask getFloorMask();

    /**
     * A method to obtain the temple.
     * @return - the temple
     */
    Temple getTemple();

    /**
     * A method to obtain all the FloorElements present at the current moment in the floor.
     * @return - a list of all the current FloorElements
     */
    List<FloorElement> getAllElements();

    /**
     * A method to obtain a factory to all the renderers currently used.
     * @return - the render factory
     */
    RenderFactory getRenderFactory();

    /**
     * A method that notifies the listener that an event has occurred.
     * @param floorEvent - the event that has happened {@link FloorEvent}
     */
    void notifyFloorEvent(FloorEvent floorEvent);

    /**
     * A method that returns the elapsed time from the last frame.
     * @return - the elapsed time
     */
    long getTimePassed();

    /**
     * A method to set the game to a state of end.
     * @param isOver - the new value of the field
     */
    void setIsOver(boolean isOver);

    /**
     * A method that add a new {@link Interactable} element
     * to the floor.
     * 
     * @param interactable - the new element
     */
    void addInteractable(Interactable interactable);
}
