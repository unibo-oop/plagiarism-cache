package it.unibo.jurassiko.model.territory.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Implementation of the game territory.
 */
@SuppressWarnings("PMD") // This class is used by the Jackson deserializer for the factory
public final class TerritoryImpl extends AbstractBoardArea<Territory> implements Territory {

    @JsonProperty("continent")
    private String continent;

    @JsonIgnore
    private int dinoAmount;

    private TerritoryImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContinent() {
        return continent;
    }

}
