package org.vise.model.user;

/**
 * Represents a canonic user.
 *
 */
public interface User {
    /**
     * 
     * @return
     *          The ID of current user.
     */
    int getUserID();

    /**
     * 
     * @return
     *          The username of current user.
     */
    String getUsername();
}
