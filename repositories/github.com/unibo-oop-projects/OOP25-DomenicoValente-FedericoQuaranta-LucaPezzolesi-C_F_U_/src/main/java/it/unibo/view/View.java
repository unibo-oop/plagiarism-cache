package it.unibo.view;

import java.util.Optional;

import it.unibo.api.Position;
import it.unibo.api.enigmas.Enigma;
import it.unibo.api.rooms.Room;
import it.unibo.input.Controller;

/**
 * interface for view
 */
public interface View {

    /**
     * update the view with new parameter
     * @param room the actual room
     * @param playerPosition the actual positon of the player
     * @param enigma the enigma if it is present
     */
    void updateView(Room room, Position playerPosition, Optional<Enigma> enigma);

    /**
     * set the controller
     * @param controller the controller
     */
    void setController(Controller controller);
}
