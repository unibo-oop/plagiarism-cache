package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.PasswordException;
import utilities.CreateCinema;

/**
 * Implementation of SetUser.
 *
 */
public abstract class SetUser implements SaveAndRead<String> {

    /**
     * It is the file used to save the passwords.
     */
    protected static final String FILE = CreateCinema.FILE_O;

    // It checks the password: if is null, the check is false.
    private boolean check;

    /**
     * It is the constructor of this class.
     */
    public SetUser() {
        if (getPassword() == null) {
            check = false;
        } else {
            check = true;
        }
    }

    /**
     * It looks at the user's password. If it is null, the method set a new password. If the password is wrong, it
     * throws an exception.
     *
     * @param password
     *            the string passed by the user
     * @throws PasswordException
     *             if the password is wrong
     */
    public void insertPassword(final String password) throws PasswordException {
        if (getPassword() == null) {
            // The password is null, so it is to set.
            setPassword(password);
            save();
            // After setting the password the check is to turn in true.
            check = true;
        } else {
            if (!getPassword().equals(password)) {
                // There is a password but the user inserted the wrong string.
                throw new PasswordException();
            }
        }
    }

    /**
     * @return true if the user has a password, false if the password is null.
     */
    public boolean checkPassword() {
        return check;
    }

    @Override
    public void reset() {
        check = false;
        setPassword(null);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            w.flush();
        } catch (final IOException e) {
            System.out.println("ERRORE " + e.getMessage());
        }
    }

    /**
     * It is used to get the owner password or the staff's one.
     *
     * @return the password required.
     */
    protected abstract String getPassword();

    /**
     * It is used to set the owner password or the staff's one.
     *
     * @param password
     *            the string that will be used as password.
     */
    protected abstract void setPassword(String password);

    @Override
    public abstract void save();

    @Override
    public abstract String read();

}
