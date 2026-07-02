package org.vise.model.user;

/**
 * Implementation of {@link User}.
 *
 */
public class UserImpl implements User {
    private final int id;
    private final String username;

    /**
     * 
     * @param id
     *          User ID.
     * @param username
     *          Username.
     */
    public UserImpl(final int id, final String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * 
     */
    @Override
    public int getUserID() {
        return this.id;
    }

    /**
     * 
     */
    @Override
    public String getUsername() {
        return this.username;
    }
}
