package it.unibo.monoopoly.model.notary.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Company;
import it.unibo.monoopoly.model.notary.api.Notary;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Implementation of {@link Notary} interface.
 */
public class NotaryImpl implements Notary {

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyProperty(final Player player, final Buyable cell) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(cell);
        if (cell.isAvailable()) {
            player.pay(cell.getCost());
            cell.setOwner(Optional.of(player));
            player.addProperty(cell);
        } else {
            throw new IllegalStateException("Property must be owned by the bank to be buyable");
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isActionBuy(final Cell cell, final Player actualPlayer) {
        Objects.requireNonNull(actualPlayer);
        Objects.requireNonNull(cell);
        if (cell instanceof Buyable) {
            final Buyable buyableCell = (Buyable) cell;
            return buyableCell.isAvailable() && actualPlayer.isPayable(buyableCell.getCost());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> checkProperty(final Player player, final Cell cell) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(cell);
        if (!(cell instanceof Buyable)) {
            throw new IllegalArgumentException("Expected a buyable cell in input");
        }
        final Buyable buyableCell = (Buyable) cell;
        if (checkRentPayment(player, buyableCell)) {
            payOwner(player, buyableCell);
            return Optional.of(Event.RENT_PAYMENT);
        } else {
            return Optional.empty();
        }
    }

    private boolean checkRentPayment(final Player player, final Buyable cell) {
        return !cell.isAvailable() && !cell.isMortgaged() && !cell.getOwner().get().equals(player);
    }

    private void payOwner(final Player player, final Buyable buyableCell) {
        if (buyableCell.isCompany()) {
            ((Company) buyableCell).rollAndCalculate();
        }
        final int actualRentalValue = buyableCell.getRentalValue();
        buyableCell.getOwner().get().receive(actualRentalValue);
        player.pay(actualRentalValue);
    }

}
