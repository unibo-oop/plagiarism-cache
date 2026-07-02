package it.unibo.modularcheckers.util;

import java.util.List;

import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.Color;

/**
 * Used to deserialize classes from files.
 */
public interface GameDataDeserializer {

    /**
     * @return The list of color deserialized.
     */
    List<Color> deserializeColor();

    /**
     * @return The Checkers board configuration deserialized.
     */
    Chessboard deserializeCheckersBoard();

}
