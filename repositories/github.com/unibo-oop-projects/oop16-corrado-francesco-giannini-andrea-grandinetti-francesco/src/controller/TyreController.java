package controller;

import java.util.List;

import utility.TyreType;

/**
 * Interface for classes that have to control the players' tyres. 
 */
public interface TyreController {

    /**
     * Know if the current tyre is usable or not in the circuit.
     * @param tyre the tyre
     * @return if the tyre is usable or not
     */
    boolean isTheWrongTyre(TyreType tyre);

    /**
     * Know if the tyre used respect the politics of the game.
     * @param list the list of tyre used
     * @return if the player has to be disqualified or not.
     */
    boolean hasToBeDisqualified(List<TyreType> list);

    /**
     * Get the tyres that are allowed to use in this circuit.
     * @return the list of tyres permitted
     */
    List<TyreType> getTyresPermitted();

}