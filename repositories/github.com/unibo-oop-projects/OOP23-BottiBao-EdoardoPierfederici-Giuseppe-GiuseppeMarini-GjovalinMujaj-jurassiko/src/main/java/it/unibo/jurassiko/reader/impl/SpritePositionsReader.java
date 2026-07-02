package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.common.Pair;
import it.unibo.jurassiko.reader.api.JSONFileReader;

/**
 * Abstract class providing a common implementation to read from a JSON file the
 * coordinates of the sprites for the game board, calculated as a percentage of
 * the board panel dimension.
 */
public class SpritePositionsReader implements JSONFileReader<Map<String, Pair<Double, Double>>> {

    private final ObjectMapper mapper;

    /**
     * Creates an AbstractSpritePositionReader.
     */
    public SpritePositionsReader() {
        this.mapper = new ObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Pair<Double, Double>> readFileData(final String filePath) {
        final Map<String, Pair<Double, Double>> data = new HashMap<>();

        try (InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath))) {
            final JsonNode jsonNode = this.mapper.readTree(in);
            jsonNode.forEach(t -> {
                final String spriteName = t.get("name").asText();
                final double x = t.get("x").asDouble();
                final double y = t.get("y").asDouble();
                data.put(spriteName, new Pair<>(x, y));
            });
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read " + filePath + " file", e);
        }

        return Map.copyOf(data);
    }

}
