package com.biaren.sportclubmanager.corebundle.model.interfaces;

/**
 * Represent a Sponsor, with a name, a description, a logo and the list of Contract.
 * @author nbrunetti
 *
 */
public interface Sponsor {
    
    /**
     * Get the sponsor name
     * @return {@link String} of sponsor name
     */
    String getName();
    
    /**
     * Get the description of the sponsor
     * @return {@link String} description of the sponsor
     */
    String getDescription();
    
    /**
     * Get the logo path in project resources
     * @return {@link String} uri of logo
     */
    String getLogoPath();
    
}
