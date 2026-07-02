package it.unibo.jurassiko.model.territory.impl;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.OceanFactory;
import it.unibo.jurassiko.reader.impl.BoardDataReader;

/**
 * Implementation of the factory for the game oceans.
 */
public class OceanFactoryImpl implements OceanFactory {

    private static final String PATH = "config/oceans.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ocean> createOceans() {
        final var oceanReader = new BoardDataReader<>(Ocean.class);
        final Set<Ocean> oceans = oceanReader.readFileData(PATH);
        return Set.copyOf(oceans);
    }

}
