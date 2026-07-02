package org.vise.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.vise.view.UI;
import org.vise.view.UIRLForm;

/**
 * 
 * Represent the controller for registration and login.
 *
 */
public interface RLFormController {

    /**
     * Make login for a user already registered.
     * 
     * @param email
     *              the email used to make the login
     * @param pwd
     *            the password used to make the login 
     * @throws IOException
     *          if the URL is not correct
     * @throws JSONException
     *          if there is a syntax error in the source string or a duplicated key
     */
    void login(String email, String pwd) throws JSONException, IOException;

    /**
     * Make registration for a new user.
     * 
     * @param nomeUtente
     *              the username used to make the registration
     * @param email
     *              the email used to make the registration
     * @param pwd
     *            the password used to make the registration 
     * @throws IOException
     *          if the URL is not correct
     * @throws JSONException
     *          if there is a syntax error in the source string or a duplicated key
     */
    void registration(String nomeUtente, String email, String pwd) throws JSONException, IOException;

    /**
     * Try to do automatic login when open a session.
     * @throws ParseException
     *         if the file doesn't exist
     * @throws IOException 
     *         if there is an I/O error
     * @throws JSONException 
     *         if the json doesn't has the field expected
     */
    void automaticLogin() throws JSONException, IOException, ParseException;

    /**
     * Connect the view with this controller.
     * 
     * @param ui
     *          The UI attached to the Controller.
     */
    void bindUI(UI ui);

    /**
     * Connect the RLFormScreenController to the relative controller.
     * 
     * @param ui
     *          The UI attached to the Controller.
     */
    void bindRLForm(UIRLForm ui);

    /**
     * Check if there is a connection.
     * 
     * @return
     *          true - if connection is available.
     */
    boolean isNetworkingAvailable(); 
}
