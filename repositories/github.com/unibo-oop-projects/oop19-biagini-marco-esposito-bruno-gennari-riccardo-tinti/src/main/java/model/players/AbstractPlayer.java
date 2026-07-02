package model.players;

import java.io.Serializable;

/**
 * Represents the abstract model for all {@link Player}s and gives a basic implementation
 * of the main methods. 
 * Basically each {@link Player} has a name, a password and could be online or offline.
 * 
 */
public abstract class AbstractPlayer implements Player, Serializable {

    /**
     * Represents the default Serial Version ID for the player.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Standard initial value for the statistics.
     */
    protected static final double DEF_INIT_STATS_VAL = 0.00;

    private String userName;
    private String password;
    private transient boolean online;

    /**
     * Initializes the basic data for this player.
     * 
     * @param userName
     *          the name of the player.
     * @param password
     *          the password of the player.
     */
    public AbstractPlayer(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
        this.online = false;
    }

    /**
     * Returns the player name.
     */
    @Override
    public final String getUsername() {
        return this.userName;
    }

    /**
     * Returns the player password.
     */
    @Override
    public final String getPassword() {
        return this.password;
    }

    /**
     * Returns the string value of the player.
     */
    @Override
    public final String toString() {
        return super.toString();
    }

    /**
     * Sets the login value for the player.
     */
    @Override
    public final void setLogin(final boolean value) {
        this.online = value;
    }

    /**
     * Returns the corresponding true or false value 
     * of the login state.
     */
    @Override
    public final boolean isPlaying() {
        return this.online;
    }

}
