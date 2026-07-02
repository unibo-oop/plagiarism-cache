package com.biaren.sportclubmanager.corebundle.model.interfaces;

/**
 * Represent a player.
 * @author nbrunetti
 *
 * @param <T> player role in field
 */
public interface Player<T> {
    /**
     * Get player's height.
     * 
     * @return player's height
     */
    double getHeight();

    /**
     * Get player's weight.
     * 
     * @return player's weight.
     */
    double getWeight();

    /**
     * Get main player role. Main role is intended as main field role, 
     * and not the specific role on then appropriate field zone.
     * 
     * @return main player role
     */
    T getMainRole();
}
