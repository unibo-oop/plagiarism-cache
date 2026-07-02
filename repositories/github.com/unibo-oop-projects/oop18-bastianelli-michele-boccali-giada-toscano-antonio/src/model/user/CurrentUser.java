package model.user;

import java.util.Optional;

import enumerators.Level;

/**
 * Class that describe the User currently connected in the application. This
 * class is singleton.
 */

public final class CurrentUser {
    private static CurrentUser instance = new CurrentUser();
    private User user;
    private Level currentLevel;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        return instance;
    }

    /**
     * Returns the persistent informations of the current User
     * 
     * @return the user persistent information
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user's persistent informations in the current user
     * 
     * @param user : the user to set as Current User
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Returns an optional describing the level to play, if not-null. Otherwise
     * return an empty Optional
     * 
     * @return an optional describing the level to play, if not-null. Otherwise
     *         return an empty Optional.
     */
    public Optional<Level> getCurrentLevel() {
        return Optional.ofNullable(currentLevel);
    }

    /**
     * Set the level to play
     * 
     * @param currentLevel : the Level to play
     */
    public void setCurrentLevel(final Level currentLevel) {
        this.currentLevel = currentLevel;
    }

}
