package it.trashwarecesena.trustalodesktopclient.repository.test.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringUser;
import it.trashwarecesena.trustalodesktopclient.repository.security.Username;

/**
 * A set of test over the {@link Username} interface.
 * <p>
 * The tested functionalities are equality, correctness of the backing up
 * objects, and the throw of exceptions by an abnormal construction.
 * 
 * @author Manuel Bonarrigo
 *
 */
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestStringUser {

    private static final String BRASCO = "Donnie Brasco";
    private static final String DARKO = "Donnie Darko";
    private static final String DEPP = "Donnie Brasco";
    private static final String EMPTY = "";
    private static final String ONE_EMPTY = " ";
    private static final String MORE_EMPTY = "      ";

    /**
     * Testing of the {@link StringUser} value class implementation.
     */
    @Test
    public void stringUserTest() {
        final Username brasco = new StringUser(BRASCO);
        final Username darko = new StringUser(DARKO);
        final Username depp = new StringUser(DEPP);

        assertTrue(brasco.getUsername().equals(BRASCO));
        assertTrue(brasco.getUsername().equals(DEPP));
        assertTrue(brasco.equals(depp));
        assertFalse(brasco.getUsername().equals(DARKO));
        assertFalse(brasco.equals(darko));
        assertThrows(NullPointerException.class, () -> {
            new StringUser(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new StringUser(EMPTY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new StringUser(ONE_EMPTY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new StringUser(MORE_EMPTY);
        });
    }

}
