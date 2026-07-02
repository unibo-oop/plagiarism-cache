package it.trashwarecesena.trustalodesktopclient.repository.test.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.repository.security.SimpleUserPassLogin;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringPassword;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringUser;
import it.trashwarecesena.trustalodesktopclient.repository.security.UserPassLogin;

/**
 * A set of test over the {@link UserPassLogin} interface.
 * <p>
 * The tested functionalities are equality, correctness of the backing up
 * objects, and the throw of exceptions by an abnormal construction.
 * 
 * @author Manuel Bonarrigo
 *
 */
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestSimpleUserPassLogin {

    private static final String BRASCO = "Donnie Brasco";
    private static final String DARKO = "Donnie Darko";
    private static final String DEPP = "Donnie Brasco";

    private static final String PASS = "pass";
    private static final String PASSWORD = "password";
    private static final String WIRETAPPED = "pass";

    /**
     * Testing of the {@link SimpleUserPassLogin} implementation.
     */
    @Test
    public void simpleUserPassLogin() {
        final UserPassLogin brascoLogin = new SimpleUserPassLogin(new StringUser(BRASCO), new StringPassword(PASS));
        final UserPassLogin darkoLogin = new SimpleUserPassLogin(new StringUser(DARKO), new StringPassword(PASSWORD));
        final UserPassLogin deppLogin = new SimpleUserPassLogin(new StringUser(DEPP), new StringPassword(WIRETAPPED));
        assertTrue(brascoLogin.getUser().equals(new StringUser(BRASCO))
                && brascoLogin.getPassword().equals(new StringPassword(PASS)));
        assertTrue(brascoLogin.getUser().equals(deppLogin.getUser())
                && brascoLogin.getPassword().equals(deppLogin.getPassword()));
        assertTrue(brascoLogin.equals(deppLogin));
        assertFalse(brascoLogin.getUser().equals(darkoLogin.getUser())
                && brascoLogin.getPassword().equals(darkoLogin.getPassword()));
        assertFalse(brascoLogin.equals(darkoLogin));

        assertThrows(NullPointerException.class, () -> {
            new SimpleUserPassLogin(new StringUser(BRASCO), null);
        });
        assertThrows(NullPointerException.class, () -> {
            new SimpleUserPassLogin(null, new StringPassword(PASS));
        });
    }

}
