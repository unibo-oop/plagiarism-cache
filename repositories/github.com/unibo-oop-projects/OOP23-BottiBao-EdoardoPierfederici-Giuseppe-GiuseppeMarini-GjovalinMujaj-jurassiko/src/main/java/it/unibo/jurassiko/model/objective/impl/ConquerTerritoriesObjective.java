package it.unibo.jurassiko.model.objective.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementation of objectives based on conquest of a number of territories.
 */
public class ConquerTerritoriesObjective extends AbstractObjective {

    private static final long serialVersionUID = 4319485590237283359L;
    private static final String TYPE = "conquerTerritories";

    @JsonProperty("value")
    private int numTerritories;
    private int minDinos;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return the amount of territories to conquer
     */
    public final int getNumTerritories() {
        return numTerritories;
    }

    /**
     * @return the amount of dinos each territory must have (0 if there is no such
     *         restriction)
     */
    public int getMinDinos() {
        return minDinos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeDescription() {
        String result = "Conquista " + this.numTerritories + " territori";
        if (this.minDinos > 1) {
            result = result.concat(" con almeno " + this.minDinos + " Dino ciascuno");
        }
        result = result.concat(".");

        setDescription(result);
    }

}
