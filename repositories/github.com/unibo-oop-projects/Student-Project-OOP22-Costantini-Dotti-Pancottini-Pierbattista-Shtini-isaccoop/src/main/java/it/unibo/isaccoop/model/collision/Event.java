package it.unibo.isaccoop.model.collision;

import it.unibo.isaccoop.model.room.Room;

/**
 * Event interface which represents a general concept of game event.
 * */
public interface Event {

    /**
     * Execute the event body.
     *
     * @param room container object in order to update
     *  the room state in the event body
     * */
    void execute(Room room);

}
