package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represent the User's Address Book.
 */

public class AddressBookImpl implements AddressBook {

    private static final long serialVersionUID = 7394463841452745084L;
    private final Set<Contact> addressBook;

    /**
     * Constructor of the AddressBookImpl class.
     */
    public AddressBookImpl() {
        this.addressBook = new HashSet<>();
    }

    @Override
    public void addContact(final Contact contact) throws IllegalArgumentException {
        Objects.requireNonNull(contact);
        if (!this.addressBook.add(contact)) {
            throw new IllegalArgumentException("Contact already registered in Address Book");
        }
    }

    @Override
    public void removeContact(final Contact contact) throws IllegalArgumentException {
        if (!this.addressBook.remove(contact)) {
            throw new IllegalArgumentException("Contact not registered in Address Book");
        }
    }

    @Override
    public void editContact(final Contact contact, final Contact newContact) throws IllegalArgumentException {
        try {
            this.removeContact(contact);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The contact cannot be edited", e);
        }
        try {
            this.addContact(newContact);
        } catch (IllegalArgumentException e) {
            this.addContact(contact);
            throw new IllegalStateException("The contact cannot be edited", e);
        }
    }

    @Override
    public List<Contact> getAddressBook() {
        return addressBook.stream().sorted((p1, p2) -> p2.getSurnameName().compareTo(p1.getSurnameName()))
                .collect(Collectors.toList());
    }
}
