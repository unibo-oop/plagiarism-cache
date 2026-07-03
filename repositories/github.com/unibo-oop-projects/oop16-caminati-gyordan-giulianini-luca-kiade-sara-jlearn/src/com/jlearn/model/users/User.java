package com.jlearn.model.users;

import java.io.Serializable;

import com.jlearn.model.utilities.Levels;

/**
 *
 * The user is actually the player.
 *
 */
public interface User extends Serializable {

    /**
     *
     * @return user's nickname
     */
    String getNickname();

    /**
     *
     * @return user's name
     */
    String getName();

    /**
     *
     * @return user's surname
     */
    String getSurname();

    /**
     *
     * @return user's age
     */
    int getAge();

    /**
     *
     * @return user's email address
     */
    String getEmail();

    /**
     *
     * @return user's telephone number
     */
    String getTel();

    /**
     * This method makes you know which unit has been reached in the given level mode.
     *
     * @param level
     *            the level
     * @return the reached unit ID
     */
    int getReachedUnitID(Levels level);

    /**
     * This method updates the reached unit related to the given level.
     *
     * @param level
     *            the level
     */
    void incrementReachedUnit(Levels level);
}
