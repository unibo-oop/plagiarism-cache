package it.unibo.coinquify.telephonebook.model;

import java.io.IOException;
import java.util.List;

import it.unibo.coinquify.utils.PhoneNumberPresentException;

/**
 * TelephoneBook interface.
 *
 */
public interface TelephoneBook {

    /**
     * 
     * @param contact to add to phonebook
     * @throws PhoneNumberPresentException if phonenumber is already present
     * @throws IllegalArgumentException if some field of contact was not valid
     */
    void addContact(Contact contact) throws PhoneNumberPresentException, IllegalArgumentException;

    /**
     * 
     * @param strToSearch string to search in phonebook
     * @return list of contact that match strToSearch as a pattern
     */
    List<Contact> searchContacts(String strToSearch);

    /**
     * 
     * @param phoneNumber to control
     * @return if phoneNumber is already present in phonebook
     */
    boolean isNumberAlreadyInserted(String phoneNumber);

    /**
     * Save all contacts.
     * @throws IOException if some problems occurs in saving file
     */
    void saveAll() throws IOException;

    /**
     * load all contacts.
     * @throws ClassNotFoundException if class is not loaded
     * @throws IOException if some problem occurs with loading from file
     */
    void loadAll() throws ClassNotFoundException, IOException;

    /**
     * 
     * @param path where save file
     * @param nameOfFields the header titles for fields of contacts
     * @param contactToSave list of contact to save
     * @throws IOException if some problems occurs in saving to files
     */
    void saveToCSV(String path, List<String> nameOfFields, List<Contact> contactToSave) throws IOException;

    /**
     * 
     * @param contact to remove from phonebook
     */
    void removeContact(Contact contact);

    /**
     * Update the index used to quicksearching for phonebook.
     * @param oldNumber of contact to remove from index
     * @param newNumber of contact to add to index
     */
    void updateIndexForQuickSearch(String oldNumber, String newNumber);
}
