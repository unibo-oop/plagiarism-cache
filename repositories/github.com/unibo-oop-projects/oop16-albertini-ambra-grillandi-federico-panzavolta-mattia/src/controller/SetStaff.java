package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utilities.CreateCinema;

/**
 * It extends SetUserImpl setting and getting the password for the staff.
 */
public class SetStaff extends SetUser {

    private static final String STAFF_STR = "STAFF: ";

    @Override
    protected String getPassword() {
        if (CreateCinema.getCinema().getStaffPassword() == null) {
            CreateCinema.getCinema().setStaffPassword(read());
        }
        return CreateCinema.getCinema().getStaffPassword();
    }

    @Override
    protected void setPassword(final String password) {
        CreateCinema.getCinema().setStaffPassword(password);
    }

    @Override
    public void save() {
        final String text = STAFF_STR + getPassword();
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.write(text);
        } catch (final IOException e) {
            System.err.println("ERRORE " + e.getMessage());
        }
    }

    @Override
    public String read() {
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            final String[] list = r.lines().filter(l -> l.contains(STAFF_STR)).toArray(String[]::new);
            if (list.length > 0) {
                return list[0].substring(STAFF_STR.length());
            }

        } catch (final IOException e) {
            return null;
        }
        return null;
    }
}
