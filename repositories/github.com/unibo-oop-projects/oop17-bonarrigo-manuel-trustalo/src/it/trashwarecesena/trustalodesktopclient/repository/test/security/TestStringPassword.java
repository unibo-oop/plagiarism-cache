package it.trashwarecesena.trustalodesktopclient.repository.test.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.repository.security.Password;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringPassword;

/**
 * A set of test over the {@link Password} interface.
 * <p>
 * The tested functionalities are equality, correctness of the backing up
 * objects, and the throw of exceptions by an abnormal construction.
 * 
 * @author Manuel Bonarrigo
 *
 */
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestStringPassword {

    private static final String PASS = "pass";
    private static final String PASSWORD = "password";
    private static final String WIRETAPPED = "pass";

    /**
     * Testing of the {@link StringPassword} value class implementation.
     */
    @Test
    public void stringUserTest() {
        final Password pass = new StringPassword(PASS);
        final Password password = new StringPassword(PASSWORD);
        final Password wireTapped = new StringPassword(WIRETAPPED);

        assertTrue(pass.getPassword().equals(PASS));
        assertTrue(pass.getPassword().equals(WIRETAPPED));
        assertTrue(pass.equals(wireTapped));
        assertFalse(pass.getPassword().equals(PASSWORD));
        assertFalse(pass.equals(password));

        assertThrows(NullPointerException.class, () -> {
            new StringPassword(null);
        });
    }

}
