package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utility.TyreType;

/**
 * Enum for the circuit playable in the game whose files are present.
 */
public enum CircuitPlayable {

    /**
     * Melbourne, Australia.
     */
    MELBOURNE("Melbourne", "Australia", "Albert Park", new ArrayList<>(Arrays.asList(TyreType.SS, TyreType.S, TyreType.M)), 8);

    private final String city;
    private final String country;
    private final String circuitName;
    private final List<TyreType> tyresAvailable;
    private final int laps;

    CircuitPlayable(final String city, final String country, final String circuitName,
                   final List<TyreType> list, final int laps) {
        this.city = city;
        this.country = country;
        this.circuitName = circuitName;
        this.tyresAvailable = list;
        this.laps = laps;
    }

    /**
     * Getter.
     * @return the circuit's city.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Getter.
     * @return the circuit's country.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Getter.
     * @return the circuit name.
     */
    public String getCircuitName() {
        return this.circuitName;
    }

    /**
     * Getter.
     * @return a list with all the tyres that will be playable in this circuit.
     */
    public List<TyreType> getTyresAvailable() {
        return this.tyresAvailable;
    }

    /**
     * Getter.
     * @return the race laps for this circuit
     */
    public int getLaps() {
        return this.laps;
    }

    @Override
    public String toString() {
        return this.circuitName + " [" + this.city + "]";
    }

}
