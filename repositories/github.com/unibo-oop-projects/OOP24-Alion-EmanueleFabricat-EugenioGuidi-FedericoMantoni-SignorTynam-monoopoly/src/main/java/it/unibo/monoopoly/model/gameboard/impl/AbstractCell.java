package it.unibo.monoopoly.model.gameboard.impl;

import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Company;
import it.unibo.monoopoly.model.gameboard.api.Railroad;

/**
 * Abstract class implementing common methods of a generic {@link Cell}.
 */
public abstract class AbstractCell implements Cell {

    private final String name;

    /**
     * Constructor in common for any cell.
     * 
     * @param name the name of the cell
     */
    public AbstractCell(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBuildable() {
        return this instanceof Buildable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBuyable() {
        return this instanceof Buyable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompany() {
        return this instanceof Company;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRailroad() {
        return this instanceof Railroad;
    }

}
