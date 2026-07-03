package it.unibo.coinquify.telephonebook.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.coinquify.file.PhoneBookFile;
import it.unibo.coinquify.utils.CsvFileWriter;
import it.unibo.coinquify.utils.Messages;

/**
 * TelephoneBook Implementation.
 */
public class TelephoneBookImpl implements TelephoneBook {
    private List<Contact> database;
    private final Set<String> databaseForQuickSearch;

    /**
     * Constructor for TelephoneBook.
     */
    public TelephoneBookImpl() {
        this.database = new ArrayList<>(); // Just to prevent anytipe of bugs
        this.databaseForQuickSearch = new HashSet<>();
    }

    @Override
    public void addContact(final Contact contact) {
        if (contact.isContactValid()) {
            this.database.add(contact);
            this.databaseForQuickSearch.add(contact.getPhoneNumber());
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Contact> searchContacts(final String strToSearch) {
        if (strToSearch == null) {
            return Collections.unmodifiableList(this.database);
        } else {
            final String str = strToSearch.toLowerCase(Messages.getMessages().getCurrentLocale());
            final List<Contact> temp = new ArrayList<Contact>();
            for (final Contact contact : this.database) {
                if (contact.getPhoneNumber().toLowerCase().contains(str)
                        || contact.getName().toLowerCase().contains(str)
                        || contact.getSurname().toLowerCase().contains(str) || contact.getName().toLowerCase()
                                .concat(" " + contact.getSurname().toLowerCase()).contains(str)
                        || contact.getPhoneNumber().contains(str)) {
                    temp.add(contact);
                }
            }
            return temp;
        }

    }

    @Override
    public void saveAll() throws IOException {
        PhoneBookFile.saveContacts(this.database);
    }

    @Override
    public void loadAll() throws ClassNotFoundException, IOException {
        this.database = PhoneBookFile.readContacts();
        for (final Contact contact : database) {
            this.databaseForQuickSearch.add(contact.getPhoneNumber());
        }
    }

    @Override
    public boolean isNumberAlreadyInserted(final String phoneNumber) {
        return this.databaseForQuickSearch.contains(phoneNumber);
    }

    @Override
    public void saveToCSV(final String path, final List<String> nameOfColumns, final List<Contact> contactToSave)
            throws IOException {
        CsvFileWriter.writeCsvFile(path, nameOfColumns, contactToSave);
    }

    @Override
    public void removeContact(final Contact contact) throws IllegalArgumentException {
        if (contact == null || contact.getPhoneNumber() == null || contact.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.databaseForQuickSearch.remove(contact.getPhoneNumber());
        this.database.remove(contact);
    }

    @Override
    public void updateIndexForQuickSearch(final String oldNumber, final String newNumber) {
        this.databaseForQuickSearch.remove(oldNumber);
        this.databaseForQuickSearch.add(newNumber);
    }
}