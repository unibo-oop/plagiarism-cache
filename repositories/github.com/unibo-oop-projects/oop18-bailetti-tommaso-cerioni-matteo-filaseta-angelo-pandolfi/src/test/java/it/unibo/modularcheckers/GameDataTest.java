package it.unibo.modularcheckers;

import static junit.framework.TestCase.assertNotNull;

import org.junit.Test;

import it.unibo.modularcheckers.util.GameDataDeserializer;
import it.unibo.modularcheckers.util.GameDataDeserializerImpl;
import it.unibo.modularcheckers.util.GameDataSerializer;
import it.unibo.modularcheckers.util.GameDataSerializerImpl;

/**
 * Testing the default data for the games.
 */
public class GameDataTest {

    /**
     * Deserialize the default objects, than tries to serialize them again.
     */
    @Test
    public void testDeserializeCheckers() {
        final GameDataSerializer gameDataSerializer = new GameDataSerializerImpl();
        final GameDataDeserializer gameDataDeserializer = new GameDataDeserializerImpl();
        gameDataSerializer.serializeCheckersBoard();
        gameDataSerializer.serializeColors();
        assertNotNull("Checking the Default Board isn't null.",
                gameDataDeserializer.deserializeCheckersBoard());
        assertNotNull("Checking the Default Colors aren't null.", gameDataDeserializer.deserializeColor());
    }
}
