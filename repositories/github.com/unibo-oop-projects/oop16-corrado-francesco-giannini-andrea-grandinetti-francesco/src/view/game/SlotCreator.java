package view.game;

import java.io.BufferedReader;
import java.util.Map;

import utility.Position;
/**
 * Interface to create map of slots.
 *
 */
public interface SlotCreator {

    /**
     * Reads the file and creates the map of slots.
     * @param br where there are data of slots
     * @return Map of slots
     */
    Map<Position, Slot> createSlots(BufferedReader br);

}