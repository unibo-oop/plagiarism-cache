package it.unibo.jurassiko.model.territory.impl;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.Ocean;

/**
 * Implementation of the game Ocean.
 */
@SuppressWarnings("PMD") // This class is used by the Jackson deserializer for the factory
public final class OceanImpl extends AbstractBoardArea<Ocean> implements Ocean {

    @JsonProperty("territories")
    private Set<String> adjTerritories;

    // NOPMD using the class for Jackson deserializer for the factory
    private OceanImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAdjTerritories() {
        return Set.copyOf(adjTerritories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjTerritory(final String territoryName) {
        Objects.requireNonNull(adjTerritories);
        return adjTerritories.stream()
                .anyMatch(territoryName::equals);
    }

}
