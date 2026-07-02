package it.unibo.jurassiko.model.territory.impl;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.jurassiko.model.territory.api.BoardArea;

/**
 * Abstract class implementing common functionality for both Territory and
 * Ocean.
 * 
 * @param <T> The type of the specific area (Territory or Ocean)
 */
public abstract class AbstractBoardArea<T extends BoardArea<T>> implements BoardArea<T> {

    @JsonProperty("name")
    private String name;
    @JsonProperty("neighbours")
    private Set<String> neighbours;

    /**
     * Default constructor used by Jackson JSON parser.
     */
    protected AbstractBoardArea() {
        // Intentionally blank constructor for the Factory class
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getNeighbours() {
        return Set.copyOf(neighbours);
    }

}
