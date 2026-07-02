package it.trashwarecesena.trustalodesktopclient.repository.security;

/**
 * Interface representing the user identifier passed along with an
 * authentication token to obtain access to a protected service.
 * 
 * @author Manuel Bonarrigo
 */
public interface Username {
    /**
     * Retrieve a {@link String} representation of the class content.
     * 
     * @return a String containing the username.
     */
    String getUsername();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

}
