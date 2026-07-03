package model;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represent the collection of application's Users.
 */

public final class UserManagerImpl implements UserManager {

    private static final long serialVersionUID = 1142863084077365606L;
    private Set<User> users;
    private transient User user;

    private static class ManagerHolder {
        private static final UserManagerImpl SINGLETON = new UserManagerImpl();
    }

    /**
     * Constructor of the UserManagerImpl class.
     */
    private UserManagerImpl() {
        this.users = new HashSet<>();
    }

    /**
     * @return UserManagerImpl Singleton
     */
    public static UserManagerImpl getManager() {
        return ManagerHolder.SINGLETON;
    }

    @Override
    public User currentUser() {
        return this.user;
    }

    @Override
    public void add(final User user) throws IllegalArgumentException {
        Objects.requireNonNull(user);
        if (!this.users.add(user)) {
            throw new IllegalArgumentException("User already registered");
        }
    }

    @Override
    public Boolean login(final User user) {
        if (users.contains(user)) {
            for (final User u : users) {
                if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                    this.user = u;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void logout() throws IllegalArgumentException {
        user.getCalendar().setLastUserLogout();
        try {
            this.remove(this.user);
            this.add(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to logout the user", e);
        }

    }

    @Override
    public Set<User> getAll() {
        return this.users;
    }

    @Override
    public void addAll(final Set<User> s) {
        this.users = s;
    }

    @Override
    public void remove(final User user) throws IllegalArgumentException {
        if (!this.users.remove(user)) {
            throw new IllegalArgumentException("User not registered");
        }
    }

    @Override
    public void changePassword(final User user, final String password)
            throws NoSuchAlgorithmException, IllegalArgumentException {
        if (this.login(user)) {
            for (final User u : users) {
                if (u.equals(user)) {
                    u.setPassword(password);
                }
            }
        } else {
            throw new IllegalArgumentException("Unable to change the password, user not registered");
        }

    }

}
