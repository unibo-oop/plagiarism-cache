package it.unibo.coinquify.telephonebook.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * TelephoneBook of all room mates contacts.
 */
public interface TelephoneBookController {

    /**
     * Add a Contact to TelephoneBook.
     * 
     * @param contact
     *            to add to phonebook
     * @throws PhoneNumberPresentException if number is already present in phonebook
     */
    void addContact(Contact contact) throws PhoneNumberPresentException;

    /**
     * 
     * @param strToSearch the string to search in the contact list
     * @return a list of contacts that match the string passed
     */
    List<Contact> getContacts(String strToSearch);

    /**
     * 
     * @return all the contacts in phonebook
     */
    List<Contact> getAllContacts();

    /**
     * 
     * @param path where save file
     * @param listOfArguments list of arguments that form header of CSV file
     * @param listToSave list of contact to save to CSV file
     * @throws IllegalArgumentException if occurs some problems with field of one contact
     * @throws IOException if occurs some problems with saving file
     */
    void saveCSV(String path, List<String> listOfArguments, List<Contact> listToSave)
            throws IllegalArgumentException, IOException;

    /**
     * 
     * @param cntctToRemove a contact to remove
     */
    void removeContact(Contact cntctToRemove);

    /**
     * 
     * @param cntctToEdit the contact to edit
     * @param name new name of contact
     * @param surname new surname of contact
     * @param fiscalCode new fiscal code of contact
     * @param phoneNumber new phonenumber of contact
     * @param birthdayDate new birthday date of contact
     * @param address new address of contact
     * @param email new email of contact
     * @param homePhoneNumber new home phone number of contact
     * @param workPhoneNumber new work phone number of contact
     */
    @SuppressWarnings("all")
    void editContact(Contact cntctToEdit, String name, String surname, String fiscalCode, String phoneNumber,
            Optional<Date> birthdayDate, String address, String email, String homePhoneNumber, String workPhoneNumber);

    /**
     * 
     * @param stringToConvert a string date that need to be parsed
     * @return a optional of date 
     * @throws ParseException if method can not parse date
     */
    Optional<Date> makeDate(String stringToConvert) throws ParseException;

    /**
     * 
     * @param dateToConvert the date that need to be converted to string
     * @return a string that rapresent the date passed
     */
    String makeStringFromDate(Date dateToConvert);

}
