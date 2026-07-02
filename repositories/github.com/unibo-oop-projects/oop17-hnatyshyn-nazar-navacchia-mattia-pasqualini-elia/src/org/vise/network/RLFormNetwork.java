package org.vise.network;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.CurrentUserImpl;

import javafx.util.Pair;

/**
 * 
 * A network class for registration and login.
 *
 */
public final class RLFormNetwork {

    private RLFormNetwork() { }

    /**
     * Makes the registration.
     * 
     * @param nomeUtente
     *          The username used to make the registration
     * @param email
     *          The email used to make the registration
     * @param pwd
     *          The password used to make the registration
     * @throws IOException
     *          if the URL is not correct
     * @throws JSONException
     *          if there is a syntax error in the source string or a duplicated key.
     * @return
     *           if the registration is success, and a message that explain if all is ok or not
     */
    public static Pair<Boolean, Pair<String, CurrentUser>> makeRegistration(final String nomeUtente, final String email, final String pwd) throws JSONException, IOException {
        String error = null;
        CurrentUser user = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "registration.php?username=" + nomeUtente + "&email=" + email + "&pwd=" + pwd);
        final boolean success = json.getBoolean("success");
        if (!success) {
          error = json.getJSONObject("error").getString("message");

        } else {
          error = "Registrazione avvenuta con successo.";
          user = new CurrentUserImpl(json.getJSONObject("utente").getInt("id"), nomeUtente, email, pwd);
        }
        return new Pair<>(success, new Pair<>(error, user));
    }
    /**
     * Make the login.
     * 
     * @param email
     *          The email used to make the login
     * @param pwd
     *          The password used to make the login
     * @throws IOException
     *          if the URL is not correct
     * @throws JSONException
     *          if there is a syntax error in the source string or a duplicated key.
     * @return
     *           if the login is success, and a message that explain if all is ok or not
     */
    public static Pair<Boolean, Pair<String, CurrentUser>> makeLogin(final String email, final String pwd) throws JSONException, IOException {
        String error = null;
        CurrentUser user = null;
        final JSONObject json = NetworkUtility.run(NetworkUtility.URL + "login.php?email=" + email + "&pwd=" + pwd);
        if (NetworkUtility.isNetworkingAvailable()) {
            final boolean success = json.getBoolean("success");
            if (!success) {
              error = json.getJSONObject("error").getString("message");
            } else {
              error = "Login avvenuto con successo.";
              user = new CurrentUserImpl.UserBuilder()
                      .id(json.getJSONObject("utente").getInt("id"))
                      .username(json.getJSONObject("utente").getString("username"))
                      .email(email)
                      .password(pwd)
                      .build();
            }
            return new Pair<>(success, new Pair<>(error, user));
        }
        return new Pair<>(false, new Pair<>("Nessuna connessione", null));
    }
}
