package it.trashwarecesena.trustalodesktopclient.repository.security;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Implementation o.f the {@link UserPassLogin}.
 * 
 * @author Manuel Bonarrigo
 */
public final class SimpleUserPassLogin implements UserPassLogin {

    private final Username user;
    private final Password password;

    /**
     * Constructs a new SimpleUserPassLogin.
     * 
     * @param user
     *            the {@link Username} to be instantiated.
     * @param password
     *            the {@link Password} to be instantiated.
     */
    public SimpleUserPassLogin(final Username user, final Password password) {
        super();
        Objects.requireNonNull(user, "User" + ErrorString.CUSTOM_NULL);
        Objects.requireNonNull(password, "Password" + ErrorString.CUSTOM_NULL);
        this.user = user;
        this.password = password;
    }

    @Override
    public Username getUser() {
        return new StringUser(user.getUsername());
    }

    @Override
    public Password getPassword() {
        return new StringPassword(password.getPassword());

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleUserPassLogin other = (SimpleUserPassLogin) obj;
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

}
