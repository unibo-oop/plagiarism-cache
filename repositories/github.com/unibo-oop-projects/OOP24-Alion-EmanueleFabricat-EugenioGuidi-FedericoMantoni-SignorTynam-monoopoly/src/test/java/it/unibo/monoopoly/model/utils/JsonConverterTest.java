package it.unibo.monoopoly.model.utils;

import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.utils.api.JsonConverter;
import it.unibo.monoopoly.utils.impl.JsonConverterImpl;

/**
 * Test for the Json deserialization.
 */
class JsonConverterTest {

    @Test
    void testConversion() {
        final JsonConverter<Cell> converter;
        converter = new JsonConverterImpl<>(Cell.class);
        converter.jsonToList("json/data/cells.json");
    }

}
