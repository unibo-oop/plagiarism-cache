package model;

import model.score.Progress;
import model.score.ProgressImpl;

/**
 * The implementation of the {@link UserPlayer}.
 *
 */
public final class UserPlayerImpl implements UserPlayer {

    private String username;
    private String password;
    private ProgressImpl progress;

    private UserPlayerImpl(final PlayerBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    /**
     * Default constructor for {@link UserPlayerImpl}.
     */
    public UserPlayerImpl() {
        // This is intentionally empty, nothing needed here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Progress getProgress() {
        return this.progress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProgress(final ProgressImpl progress) {
        this.progress = progress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Builder for UserPlayer. 
     * 
     */
    public static class PlayerBuilder {
        private String username;
        private String password; 

        /**
         * Constructs a player builder with a specified name.
         * 
         * @param username the name of the player.
         * @return playerBuilder
         */
        public PlayerBuilder username(final String username) {
            this.username = username;
            return this;
        }

        /**
         * Constructs a player builder with a specified password.
         * 
         * @param password the password of the player.
         * @return playerBuilder
         */
        public PlayerBuilder password(final String password) {
            this.password = password;
            return this;
        }

        /**
         * Builds the UserPlayer.
         * 
         * @return userPlayer
         */
        public UserPlayer build() {
            return new UserPlayerImpl(this);
        }
    }

}
