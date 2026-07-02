package it.unibo.monoopoly.model.gameboard.impl;

import java.util.Optional;

import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Company;
import it.unibo.monoopoly.model.gameboard.api.Functional;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Proxy of a generic {@link Cell}.
 */
public class CellWrapper implements Functional, Company  {

    private static final String UNSUPPORTED_OPERATION_ERROR_MESSAGE = "This method is not supported by the proxy";
    private static final String NOT_BUYABLE_ERROR_MESSAGE = "Cannot call this method on a not Buyable cell";
    private final Cell cell;

    /**
     * Constructor of the proxy.
     * @param cell the cell wrapped
     */
    public CellWrapper(final Cell cell) {
        this.cell = cell;
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return cell.getName();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isBuyable() {
        return cell.isBuyable();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isBuildable() {
        return cell.isBuildable();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isRailroad() {
        return cell.isRailroad();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isCompany() {
        return cell.isCompany();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).isAvailable();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isMortgaged() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).isMortgaged();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public Optional<Player> getOwner() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).getOwner();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getCost() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).getCost();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void setOwner(final Optional<Player> ownerPlayer) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MESSAGE);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getRentalValue() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).getRentalValue();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getMortgageValue() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).getMortgageValue();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void setMortgage() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MESSAGE);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void removeMortgage() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MESSAGE);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getUnmortgageValue() {
        if (cell.isBuyable()) {
            return ((Buyable) cell).getUnmortgageValue();
        } else {
            throw new IllegalStateException(NOT_BUYABLE_ERROR_MESSAGE);
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void rollAndCalculate() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_ERROR_MESSAGE);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public Optional<Message> getAction() {
        if (!cell.isBuyable()) {
            return ((Functional) cell).getAction();
        } else {
            throw new IllegalStateException("Cannot call this method on a not Functional cell");
        } 
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean hasAction() {
        if (!cell.isBuyable()) {
            return ((Functional) cell).hasAction();
        } else {
            throw new IllegalStateException("Cannot call this method on a not Functional cell");
        } 
    }

}
