package it.unibo.monoopoly.model.gameboard.api;

import java.util.Optional;

import it.unibo.monoopoly.model.player.api.Player;

/**
 * Represents the buyable cells of the gameboard implementing
 * {@link Cell} interface.
 */
public interface Buyable extends Cell {

    /**
     * Returns whether the cell is buyable.
     * 
     * @return true if is buyable, false otherwise
     */
    boolean isAvailable();

    /**
     * Returns whether the property is already mortgaged.
     * 
     * @return true if the property is already mortgaged, false otherwise
     */
    boolean isMortgaged();

    /**
     * Gets the owner of property.
     * 
     * @return the owner of property
     */
    Optional<Player> getOwner();

    /**
     * Gets the cost of property.
     * 
     * @return the cost of property.
     */
    int getCost();

    /**
     * Sets the owner of the property.
     * 
     * @param ownerPlayer the {@link Player} that is buying the property
     */
    void setOwner(Optional<Player> ownerPlayer);

    /**
     * Gets the value of rent the property.
     * 
     * @return the value of rent the property
     */
    int getRentalValue();

    /**
     * Gets the mortgage value.
     * 
     * @return the mortgage value
     */
    int getMortgageValue();

    /**
     * method that set the mortgage of property.
     */
    void setMortgage();

    /**
     * remove the mortgage of property.
     */
    void removeMortgage();

    /**
     * Gets the value to remove mortgage on a property.
     * 
     * @return the 10% incremented of mortgage value.
     */
    int getUnmortgageValue();

}
