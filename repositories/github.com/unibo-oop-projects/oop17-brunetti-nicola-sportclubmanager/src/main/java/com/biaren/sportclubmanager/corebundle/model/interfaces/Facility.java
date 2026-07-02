package com.biaren.sportclubmanager.corebundle.model.interfaces;

/**
 * Defines a generic structure in Club Properties Context, so a Sport Facility, 
 * a Sport Venue, a Playing Field, a Gym.
 * 
 * @author nbrunetti
 *
 */
public interface Facility {
    
    /**
     * Get the structure's name.
     * 
     * @return structure's name.
     */
    String getName();
    
    /**
     * Get the structure's address.
     * 
     * @return structure's address.
     */
    String getAddress();
    
    /**
     * Get structure's description.
     * 
     * @return structure's description
     */
    String getDescription();
}
