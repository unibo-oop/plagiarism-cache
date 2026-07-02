package org.vise.model.user;

/**
 * Implementation of {@link CurrentUser}.
 *
 */
public class CurrentUserImpl extends UserImpl implements CurrentUser {
    private final String email;
    private final String password;
    private String activity;

    /**
     * Create user object.
     * @param id
     *          User ID.
     * @param username
     *          Username.
     * @param email
     *          Email.
     * @param password
     *          Password.
     */
    public CurrentUserImpl(final int id, final String username, final String email, final String password) {
        super(id, username);
        this.email = email;
        this.password = password;
        this.activity = "";
    }

    /**
     * 
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @author nazarhnatyshyn
     *
     */
    public static class UserBuilder {
        private int id;
        private String username;
        private String email;
        private String password;

        /**
         * 
         * @param id
         *          The user ID
         * @return
         *          A current user
         */
        public UserBuilder id(final int id) {
            this.id = id;
            return this;
        }

        /**
         * 
         * @param username
         *          The username.
         * @return
         *          A current user.
         */
        public UserBuilder username(final String username) {
            this.username = username;
            return this;
        }

        /**
         * 
         * @param email
         *          The username.
         * @return
         *          A current user.
         */
        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * 
         * @param password
         *          The username.
         * @return
         *          A current user.
         */
        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        /**
         * 
         * @return
         *      A new user.
         * @throws IllegalStateException
         *      IllegalStateException.
         */
        public CurrentUser build() throws IllegalStateException {
            if (this.id == 0 || this.username == null || this.email == null || this.password == null) {
                throw new IllegalStateException("");
            }
            return new CurrentUserImpl(this.id, this.username, this.email, this.password);
        }
    }

    /**
     * 
     */
    @Override
    public void setActivity(final String activity) {
        this.activity = activity;
    }

    /**
     * 
     */
    @Override
    public String getActivity() {
        return this.activity;
    }
}
