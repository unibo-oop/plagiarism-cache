package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utilities.CreateCinema;

/**
 * It extends SetUserImpl setting and getting the password for the owner.
 */
public class SetOwner extends SetUser {

    private static final String OWNER_STR = "OWNER: ";

    @Override
    protected String getPassword() {
        if (CreateCinema.getCinema().getOwnerPassword() == null) {
            CreateCinema.getCinema().setOwnerPassword(read());
        }
        return CreateCinema.getCinema().getOwnerPassword();
    }

    @Override
    protected void setPassword(final String password) {
        CreateCinema.getCinema().setOwnerPassword(password);

    }

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE, true))) {
            w.append(OWNER_STR + getPassword());
            w.newLine();
        } catch (final IOException e) {
            System.err.println("ERRORE " + e.getMessage());
        }
    }

    @Override
    public String read() {
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            final String[] list = r.lines().filter(l -> l.contains(OWNER_STR)).toArray(String[]::new);
            if (list.length != 0) {
                return list[0].substring(OWNER_STR.length());
            }
        } catch (final Exception e) {
            return null;
        }
        return null;
    }
}
