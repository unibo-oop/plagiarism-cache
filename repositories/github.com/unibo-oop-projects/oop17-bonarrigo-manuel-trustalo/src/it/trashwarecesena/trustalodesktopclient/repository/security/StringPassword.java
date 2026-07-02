package it.trashwarecesena.trustalodesktopclient.repository.security;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of the {@link Password} backed-up by a {@link String} and
 * without any encryption provided by the class itself.
 *
 * @author Manuel Bonarrigo
 */
public final class StringPassword implements Password {

    private final String password;

    /**
     * Constructs a StringUser.
     * 
     * @param password
     *            The password required by clients
     * @author Manuel Bonarrigo
     * 
     */
    public StringPassword(final String password) {
        super();
        Objects.requireNonNull(password, ErrorString.STRING_NULL);
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        final StringPassword other = (StringPassword) obj;
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StringPassword [password=" + password + "]";
    }

}
