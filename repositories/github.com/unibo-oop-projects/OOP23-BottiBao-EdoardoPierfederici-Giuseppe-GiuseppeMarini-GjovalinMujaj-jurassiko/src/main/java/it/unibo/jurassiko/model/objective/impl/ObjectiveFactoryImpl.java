package it.unibo.jurassiko.model.objective.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jurassiko.model.objective.api.Objective;
import it.unibo.jurassiko.model.objective.api.ObjectiveFactory;
import it.unibo.jurassiko.reader.impl.BoardDataReader;

/**
 * Implementation of {@link ObjectiveFactory}.
 */
public class ObjectiveFactoryImpl implements ObjectiveFactory {

    private static final String PATH = "config/objectives.json";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Objective> createObjectives() {
        final var objectiveReader = new BoardDataReader<>(Objective.class);
        final Set<Objective> objectives = objectiveReader.readFileData(PATH);
        writeDescriptions(objectives);
        return new HashSet<>(objectives);
    }

    /**
     * Sets the description on each objective.
     * 
     * @param objectives the set containing the objectives
     */
    private void writeDescriptions(final Set<Objective> objectives) {
        for (final var objective : objectives) {
            objective.writeDescription();
        }
    }

}
