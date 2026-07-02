package it.unibo.modularcheckers;

import it.unibo.modularcheckers.controller.StartController;
import it.unibo.modularcheckers.util.GameDataSerializer;
import it.unibo.modularcheckers.util.GameDataSerializerImpl;

/**
 * Starts the Application.
 */
final class App {

    private App() {
    }

    public static void main(final String[] args) {
        //TODO find a easier way to bundle the serialized objects.
        final GameDataSerializer gameDataSerializer = new GameDataSerializerImpl();
        gameDataSerializer.serializeCheckersBoard();
        gameDataSerializer.serializeColors();
        new StartController();
    }
}
