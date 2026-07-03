package com.geoquiz.controller.account;

import java.util.Optional;

import com.geoquiz.utility.Pair;

/**
 * Account interface that provides methods to manage accounts.
 *
 */
public interface Account extends FileOp {

    /**
     * @param idPass
     *            the credentials of the player.
     * @throws IllegalStateException
     *             exception.
     */
    void register(Pair<String, String> idPass) throws IllegalStateException;

    /**
     * @param id
     *            the id of the player
     * @param password
     *            the password of the player.
     * @throws IllegalArgumentException
     *             exception.
     */
    void checkLogin(String id, String password) throws IllegalArgumentException;

    /**
     * @return the current logged player.
     */
    Optional<String> getLoggedPlayerID();

    /**
     * Allows the player to exit from his account.
     */
    void logout();

}
