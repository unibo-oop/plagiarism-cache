package it.trashwarecesena.trustalodesktopclient.repository.security;

/**
 * Interface representing the authentication token passed along with a user
 * identifier to obtain access to a protected service.
 * 
 * @author Manuel Bonarrigo
 */
public interface Password {

    /**
     * Retrieve a {@link String} representation of the class content.
     * 
     * @return a String containing the password.
     */
    String getPassword();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

}
