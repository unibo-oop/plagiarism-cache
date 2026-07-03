package it.unibo.coinquify.balance.controller;


import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 *
 */
public class BalanceControllerImpl implements BalanceController {

    @Override
    public Debt addDebt(final Debt debt, final RoomMate roomMate) {
        roomMate.addDebt(debt);
        return debt;

    }

    @Override
    public void removeDebt(final Debt debt, final RoomMate roomMate) {
        roomMate.removeDebt(debt);
    }

    @Override
    public Lending addLending(final Lending lending, final RoomMate roomMate) {
        roomMate.addLending(lending);
        return lending;
    }

    @Override
    public void removeLending(final Lending lending, final RoomMate roomMate) {
        roomMate.removeLending(lending);
    }

}
