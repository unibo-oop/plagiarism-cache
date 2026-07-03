package it.unibo.coinquify.balance.controller;

import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.roommates.model.RoomMate;

/**
 *
 *
 */
public interface BalanceController {

    /**
     * @param debt 
     * @param roomMate 
     * @return the debt
     */
    Debt addDebt(Debt debt, RoomMate roomMate);

    /**
     * @param debt 
     * @param roomMate 
     */
    void removeDebt(Debt debt, RoomMate roomMate);

    /**
     * @param lending 
     * @param roomMate 
     * @return  the lenfing
     */
    Lending addLending(Lending lending, RoomMate roomMate);

    /**
     * @param lending 
     * @param roomMate 
     */
    void removeLending(Lending lending, RoomMate roomMate);
}
