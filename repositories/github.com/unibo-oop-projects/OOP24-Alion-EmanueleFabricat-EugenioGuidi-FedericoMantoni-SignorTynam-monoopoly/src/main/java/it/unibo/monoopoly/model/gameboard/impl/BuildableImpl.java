package it.unibo.monoopoly.model.gameboard.impl;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import it.unibo.monoopoly.model.gameboard.api.Buildable;

/**
 * Represents a buildable property in the game implementing {@link Buildable}.
 */
@JsonTypeName("Buildable")
public class BuildableImpl extends AbstractBuyable implements Buildable {

    private final Map<Integer, Integer> rentalMap;
    private int houses;
    private final int houseCost;
    private static final int MAX_HOUSES = 5;

    /**
     * Create a new buildable property.
     * 
     * @param rentalMap the map of rent values for the property
     * @param name      the name of the property
     * @param cost      the cost of the property
     * @param houseCost the cost of building a house on the property
     */
    public BuildableImpl(
            @JsonProperty("rentalMap") final Map<Integer, Integer> rentalMap,
            @JsonProperty("name") final String name,
            @JsonProperty("cost") final int cost,
            @JsonProperty("houseCost") final int houseCost) {
        super(name, cost);
        this.rentalMap = new HashMap<>();
        this.rentalMap.putAll(rentalMap);
        this.houses = 0;
        this.houseCost = houseCost;
    }

    /**
     * Return the cost of houses on the property.
     * 
     * @return the houseCost
     */
    @Override
    public int getHouseCost() {
        return this.houseCost;
    }

    /**
     * Add a house to the property.
     * 
     * @throws IllegalStateException if the property is mortgaged or there are
     *                               already 5 houses on the property.
     */
    @Override
    public void buildHouse() {
        this.checkMortgageAndHouseLimit(true);
        this.houses++;
    }

    /**
     * Remove a house from the property.
     * 
     * @return the houses
     * @throws IllegalStateException if the property is mortgaged or there are no
     *                               houses on the property
     */
    @Override
    public int sellHouse() {
        this.checkMortgageAndHouseLimit(false);
        this.houses--;
        return getSellHouseCost();
    }

    /**
     * Verify if the property is mortgaged and if it is possible to build or sell
     * houses.
     * 
     * @param isBuilding true if the operation is building a house, false if it is
     *                   selling a house
     * @throws IllegalStateException if the property is mortgaged or there are
     *                               already 5 houses on the property
     */
    private void checkMortgageAndHouseLimit(final boolean isBuilding) {
        if (this.isMortgaged()) {
            throw new IllegalStateException(
                    "Cannot " + (isBuilding ? "build" : "sell") + " a house on a mortgaged property");
        }
        if (isBuilding) {
            if (this.houses >= MAX_HOUSES) {
                throw new IllegalStateException("Cannot build more than " + MAX_HOUSES + " houses on a property");
            }
        } else {
            if (this.houses <= 0) {
                throw new IllegalStateException("Cannot sell a house if there are no houses on the property");
            }
        }
    }

    /**
     * Return the number of houses on the property.
     * 
     * @return the houses
     */

    @Override
    public int getHousesNumber() {
        return this.houses;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int calculateRentalValue() {
        return this.rentalMap.get(this.houses);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellHouseCost() {
        return this.getHouseCost() / 2;
    }
}
