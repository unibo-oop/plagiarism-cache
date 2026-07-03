package model;

import java.security.NoSuchAlgorithmException;

/**
 * This class represents a User of the Calendar.
 */

public class UserImpl implements User {

    private static final long serialVersionUID = 7394463841452745083L;
    private final String username;
    private String password;
    private final AddressBook addressBook;
    private final ToDo todo;
    private final Calendar calendar;

    /**
     * Builds a user of the calendar.
     * 
     * @param username
     *            user's username
     * @param password
     *            user's password
     * @throws NoSuchAlgorithmException
     *             this exception is thrown when a particular cryptographic
     *             algorithm is requested but is not available in the
     *             environment
     */
    public UserImpl(final String username, final String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = PasswordUtility.hashPassword(password);
        this.addressBook = new AddressBookImpl();
        this.todo = new ToDoImpl();
        this.calendar = new CalendarImpl();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public AddressBook getAddressBook() {
        return this.addressBook;
    }

    @Override
    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override
    public ToDo getTodo() {
        return this.todo;
    }

    @Override
    public void setPassword(final String password) throws NoSuchAlgorithmException {
        this.password = PasswordUtility.hashPassword(password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        final UserImpl other = (UserImpl) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserImpl [username=" + username + "]";
    }
}
