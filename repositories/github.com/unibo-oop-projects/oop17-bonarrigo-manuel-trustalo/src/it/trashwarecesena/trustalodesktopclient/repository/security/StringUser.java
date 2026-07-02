package it.trashwarecesena.trustalodesktopclient.repository.security;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link Username} backed-up by a {@link String}. No
 * encryption is provided by the class itself.
 * 
 * @author Manuel Bonarrigo
 */
public final class StringUser implements Username {

    private final String user;

    /**
     * Constructs a StringUser.
     * 
     * @param user
     *            The username required by clients
     */
    public StringUser(final String user) {
        super();
        Objects.requireNonNull(user, ErrorString.STRING_NULL);
        if (user.equals("")) {
            throw new IllegalArgumentException("User string can't be empty");
        }
        this.user = user;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        final StringUser other = (StringUser) obj;
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StringUser [user=" + user + "]";
    }

}
