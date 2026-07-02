package it.unibo.monoopoly.model.gameboard.impl;

import java.util.List;

import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.CellFactory;
import it.unibo.monoopoly.utils.api.JsonConverter;
import it.unibo.monoopoly.utils.impl.JsonConverterImpl;

/**
 * Implementation of {@link CellFactory interface}.
 */
public class CellFactoryImpl implements CellFactory {

    private static final String PATH = "json/data/cells.json";

    private final JsonConverter<Cell> deserializer;

    /**
     * Initialize the JsonConverter with the {@link Cell} class.
     */
    public CellFactoryImpl() {
        this.deserializer = new JsonConverterImpl<>(Cell.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> createCells() {
        return this.deserializer.jsonToList(PATH);
    }

}
