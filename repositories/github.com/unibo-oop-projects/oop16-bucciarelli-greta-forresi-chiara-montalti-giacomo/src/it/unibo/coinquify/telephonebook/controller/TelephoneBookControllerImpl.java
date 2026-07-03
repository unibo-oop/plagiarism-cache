package it.unibo.coinquify.telephonebook.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.telephonebook.model.TelephoneBook;
import it.unibo.coinquify.telephonebook.model.TelephoneBookImpl;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * TelephoneBook Controller Implementation.
 */
public class TelephoneBookControllerImpl implements TelephoneBookController {

    private final TelephoneBook model;
    private final SimpleDateFormat formatter;

    /**
     * a default constructor for Telephonebook Controller.
     * @param formatter a default date formatter for the preferred view of any Date object
     * @throws ClassNotFoundException if class is not loaded
     * @throws IOException if occurs some problem with saving/loading file
     */
    public TelephoneBookControllerImpl(final SimpleDateFormat formatter) throws ClassNotFoundException, IOException {
        this.model = new TelephoneBookImpl();
        this.model.loadAll();
        this.formatter = formatter;
    }

    @Override
    public void addContact(final Contact contact) throws PhoneNumberPresentException {
        if (contact != null && contact.getHomePhoneNumber() != null) {
            if (this.model.isNumberAlreadyInserted(contact.getPhoneNumber())) {
                throw new PhoneNumberPresentException();
            }
            this.model.addContact(contact);
        }
    }

    @Override
    public List<Contact> getContacts(final String strToSearch) {
        return this.model.searchContacts(strToSearch);
    }

    @Override
    public List<Contact> getAllContacts() {
        return this.model.searchContacts(null);
    }

    @Override
    public void saveCSV(final String path, final List<String> nameOfFields, final List<Contact> listToSave)
            throws IllegalArgumentException, IOException {
        if (nameOfFields == null || nameOfFields.isEmpty() || listToSave == null || listToSave.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.model.saveToCSV(path, nameOfFields, listToSave);
        }
    }

    @Override
    public void removeContact(final Contact cntctToRemove) {
        this.model.removeContact(cntctToRemove);
    }

    @Override
    public Optional<Date> makeDate(final String stringToConvert) throws ParseException {
        if (stringToConvert.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(this.formatter.parse(stringToConvert));
        }
    }

    @Override
    public String makeStringFromDate(final Date dateToConvert) {
        return this.formatter.format(dateToConvert);
    }

    @Override
    @SuppressWarnings("all")
    public void editContact(final Contact cntctToEdit, final String name, final String surname, final String fiscalCode,
            final String phoneNumber, final Optional<Date> birthdayDate, final String address, final String email,
            final String homePhoneNumber, final String workPhoneNumber) {
        final String oldNumber = cntctToEdit.getPhoneNumber();
        cntctToEdit.editContact(name, surname, fiscalCode, phoneNumber, birthdayDate, address, email, homePhoneNumber,
                workPhoneNumber);
        this.model.updateIndexForQuickSearch(oldNumber, phoneNumber);           //After the editing of contact, 

    }
}
