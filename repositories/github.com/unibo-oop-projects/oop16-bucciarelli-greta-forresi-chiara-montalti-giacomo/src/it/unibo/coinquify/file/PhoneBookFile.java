package it.unibo.coinquify.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.unibo.coinquify.telephonebook.model.Contact;

/**
 * Class for saving PhoneBook.
 */
public final class PhoneBookFile {

    private PhoneBookFile() {
    }

    /**
     * @param listToSave
     *            list of contact to save
     * @throws IOException
     *             if some problems occurs in saving contacts
     */
    public static void saveContacts(final List<Contact> listToSave) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(new File(FilePathsConstants.PHONE_BOOK)));) {
            for (final Contact contact : listToSave) {
                out.writeObject(contact);
            }
            out.writeObject(null);
            out.close();
        }
    }

    /**
     * 
     * @return list of contacts loaded from file
     * @throws ClassNotFoundException if class is not loaded
     * @throws IOException if some problems occurs in saving contacts
     */
    public static List<Contact> readContacts() throws ClassNotFoundException, IOException {
        final List<Contact> list = new ArrayList<>();
        Contact contact;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(new File(FilePathsConstants.PHONE_BOOK)));) {

            contact = (Contact) in.readObject();
            while (contact != null) {
                if (contact.isContactValid()) {
                    list.add(contact);
                }
                contact = (Contact) in.readObject();
            }
            in.close();
            return list;
        } catch (Exception e) {
            return new ArrayList<Contact>();
        }
    }
}
