package com.geoquiz.controller.account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.geoquiz.utility.LocalFolder;
import com.geoquiz.utility.Pair;

/**
 * Implementation of Account interface.
 *
 */
public final class AccountImpl implements Account {

    private final File file;
    private Set<Pair<String, String>> users = new HashSet<>();
    private Optional<Pair<String, String>> currentUser = Optional.empty();

    /**
     * @param namefile
     *            the name of the file.
     * @throws IOException
     *             exception.
     */
    public AccountImpl(final String namefile) throws IOException {
        this.file = new File(LocalFolder.getLocalFolderPath() + LocalFolder.getFileSeparator() + namefile);
        file.createNewFile();
        this.read(this.file.toString());
    }

    @Override
    public void register(final Pair<String, String> idPass) throws IllegalStateException {
        for (final Pair<String, String> p : users) {
            if (idPass.getX().equals(p.getX())) {
                // USERNAME ALREADY EXISTS
                throw new IllegalStateException("Username already exists!!");
            }
        }

        users.add(idPass);
        this.save();

    }

    @Override
    public void checkLogin(final String id, final String password) throws IllegalArgumentException {
        boolean login = false;
        for (final Pair<String, String> p : users) {
            if (id.equals(p.getX()) && password.equals(p.getY())) {
                // LOGIN OK
                this.currentUser = Optional.of(new Pair<>(id, password));
                login = true;
            }
        }
        if (!login) {
            throw new IllegalArgumentException("USERNAME O PASSWORD ERRATI!");
        }

    }

    @Override
    public Optional<String> getLoggedPlayerID() {
        return Optional.of(this.currentUser.get().getX());
    }

    @Override
    public void logout() {
        this.currentUser = Optional.empty();

    }

    @Override
    public void read(final String namefile) throws IOException {

        this.users = Files.lines(Paths.get(namefile)).filter(l -> l.trim().length() > 0).map(l -> l.split(","))
                .map(a -> new Pair<>(a[0].trim(), a[1].trim())).collect(Collectors.toSet());
    }

    @Override
    public void save() {
        PrintStream ps;
        try {
            ps = new PrintStream(this.file);
            users.forEach(p -> ps.println(this.myToString(p)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeAllAccount() {

        this.users.clear();
        this.save();

    }

    private String myToString(final Pair<String, String> pair) {
        return pair.getX() + "," + pair.getY();

    }

}
