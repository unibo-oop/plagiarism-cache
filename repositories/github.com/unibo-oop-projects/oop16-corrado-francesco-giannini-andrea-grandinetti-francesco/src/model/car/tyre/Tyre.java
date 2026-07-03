package model.car.tyre;

import utility.TyreType;

/**
 * Interface to manage the tyre level of worn out and the tyre changes.
 */
public interface Tyre {
    /**
     * Decrease the level of worn out every time the dice is thrown. 
     */
    void tyreDeg();
    /**
     * @return the worn out level (percentual).
     */
    double getDeg();
    /**
     * Change the type of tyre used before.
     * @param  tyre new type of tyre
     */
    void change(TyreType tyre);
    /**
     * 
     * @return the type of the tyre
     */
    TyreType getTyreType();
    /**
     * 
     * @return the tyre used
     */
    TTByDegrade getTyre();
}
