package it.unibo.jurassiko.model.objective.impl;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementation of objectives based on conquest of continents.
 */
public class ConquerContinentsObjective extends AbstractObjective {

    private static final long serialVersionUID = -2525216873039367492L;
    private static final String TYPE = "conquerContinents";

    @JsonProperty("value")
    private Set<String> continents;
    @JsonProperty("selectable")
    private boolean selectableContinent;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return a copy of the continents to conquer
     */
    public Set<String> getContinents() {
        return Set.copyOf(continents);
    }

    /**
     * Defines whether any additional continent has to be conquered.
     * 
     * @return true if any additional continent must be conquered, false otherwise
     */
    public boolean isSelectableContinent() {
        return selectableContinent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeDescription() {
        String result = "Conquista interamente i seguenti continenti: " + String.join(", ", this.continents);
        result = result.concat(selectableContinent ? " e un continente a scelta." : ".");
        setDescription(result);
    }

}
