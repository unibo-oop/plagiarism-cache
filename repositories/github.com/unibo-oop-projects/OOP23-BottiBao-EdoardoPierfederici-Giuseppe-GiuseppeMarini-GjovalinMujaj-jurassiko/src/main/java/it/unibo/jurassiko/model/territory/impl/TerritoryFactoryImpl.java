package it.unibo.jurassiko.model.territory.impl;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Territory;
import it.unibo.jurassiko.model.territory.api.TerritoryFactory;
import it.unibo.jurassiko.reader.impl.BoardDataReader;

/**
 * Implementation of the factory for the game territories.
 */
public class TerritoryFactoryImpl implements TerritoryFactory {

    private static final String PATH = "config/territories.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Territory> createTerritories() {
        final var territoryReader = new BoardDataReader<>(Territory.class);
        final Set<Territory> territories = territoryReader.readFileData(PATH);
        return Set.copyOf(territories);
    }

}
