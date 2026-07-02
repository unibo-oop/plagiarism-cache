package it.unibo.monoopoly.model.gameboard.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import it.unibo.monoopoly.model.gameboard.impl.BuildableImpl;
import it.unibo.monoopoly.model.gameboard.impl.CompanyImpl;
import it.unibo.monoopoly.model.gameboard.impl.FunctionalImpl;
import it.unibo.monoopoly.model.gameboard.impl.RailroadImpl;

/**
 * Cell interface.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = BuildableImpl.class, name = "Buildable"),
        @Type(value = CompanyImpl.class, name = "Company"),
        @Type(value = RailroadImpl.class, name = "Railroad"),
        @Type(value = FunctionalImpl.class, name = "Functional")
})
public interface Cell {

    /**
     * Gets the name of the cell.
     * 
     * @return the name of the cell
     */
    String getName();

    /**
     * Returns whether the cell is a {@link Buyable} property.
     * 
     * @return true if the cell is a {@link Buyable} property, false otherwise
     */
    boolean isBuyable();

    /**
     * Returns whether the cell is a {@link Buildable} property.
     * 
     * @return true if the cell is a {@link Buildable} property, false otherwise
     */
    boolean isBuildable();

    /**
     * Returns whether the cell is a buyable {@link Railroad}.
     * 
     * @return true if the cell is a buyable {@link Railroad}, false otherwise
     */
    boolean isRailroad();

    /**
     * Returns whether the cell is a buyable {@link Company}.
     * 
     * @return true if the cell is a buyable {@link Company}, false otherwise
     */
    boolean isCompany();

}
