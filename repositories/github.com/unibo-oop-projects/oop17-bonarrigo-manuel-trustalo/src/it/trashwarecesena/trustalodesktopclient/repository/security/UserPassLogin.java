package it.trashwarecesena.trustalodesktopclient.repository.security;

/**
 * Interface meant to represent the data clump between the identifier for a
 * generic user and the authentication token he should provide to gain access to
 * any protected service.
 * 
 * @author Manuel Bonarrigo
 */
public interface UserPassLogin {

    /**
     * Retrieve the {@link Username} proposed to log-on.
     * 
     * @return a Username implementor
     */
    Username getUser();

    /**
     * Retrieve the {@link Password} proposed to log-on.
     * 
     * @return a Password implementor
     */
    Password getPassword();

}
