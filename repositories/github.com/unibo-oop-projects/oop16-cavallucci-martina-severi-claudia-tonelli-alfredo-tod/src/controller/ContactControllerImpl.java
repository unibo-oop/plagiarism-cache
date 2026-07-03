package controller;

import static controller.ContactInfo.ADDRESS;
import static controller.ContactInfo.EMAIL;
import static controller.ContactInfo.NAME;
import static controller.ContactInfo.PHONENUMBER;
import static controller.ContactInfo.SURNAME;

import java.util.List;
import java.util.Map;
//import com.google.common.base.Optional;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Contact;
import model.ContactImpl;
import view.ContactView;
import view.ContactViewImpl;

/**
 * 
 * contact controller.
 *
 */
public final class ContactControllerImpl implements ContactController {
    private ContactViewImpl contactView;
    private Optional<Contact> selectedContact;
    private static final ContactController SINGLETON = new ContactControllerImpl();
    private static final int COLUMN = 5;
    /**
     * update view.
     */
    public void update() {
        this.contactView = new ContactViewImpl(this, this.getContactTable());
        this.contactView.init();
    }
    private ContactControllerImpl() {
        this.update();
    }
    /**
     * 
     * @return SINGLETON
     * singleton
     */
    public static ContactController getInstance() {
        return SINGLETON;
    }
    /**
     * 
     * @return contactView
     * contact view
     */
    public ContactView getContactView() {
        return this.contactView;
    }
    /**
     * @param map
     * map
     * @param data
     * data
     */
    public void confirmChanges(final Map<ContactInfo, String> map, final org.joda.time.LocalDate data) {
        System.out.println(map.toString()); 
                Contact contact;
                try {
                final ContactImpl.Builder b = new ContactImpl.Builder();
                if (!map.get(NAME).isEmpty()) {
                b.name(map.get(NAME));
                b.surname(map.get(SURNAME));
                if (!map.get(ADDRESS).isEmpty()) {
                    b.address(map.get(ADDRESS));
                }
                if (!map.get(PHONENUMBER).isEmpty()) {
                    b.phoneNumber(map.get(PHONENUMBER));
                }
                if (!map.get(EMAIL).isEmpty()) {
                    b.email(map.get(EMAIL));
                }
                if (!data.equals(org.joda.time.LocalDate.now())) {
                    b.dateOfBirth(data);
                }
                    contact = b.build();
                    MainControllerImpl.manager.currentUser().getAddressBook().addContact(contact);
                    if (!data.equals(org.joda.time.LocalDate.now())) {
                        try {
                            MainControllerImpl.manager.currentUser().getCalendar().addBirthday(contact);
                        } catch (Exception e) {
                            this.contactView.showMessage(e.getMessage(), this.contactView);
                        }
                    }
                }
                } catch (Exception e) {
                    this.contactView.showMessage(e.getMessage(), this.contactView);
                }
                this.contactView.rebuildTable(this.getContactTable());
    }
    /**
     * getContactTable.
     * get contact table
     * @return contactTable
     * contact table
     * 
     */
    public Object[][] getContactTable() {
        final List<Contact> list = MainControllerImpl.manager.currentUser().getAddressBook().getAddressBook();
        final Object[][] contactTable = new Object[list.size()][ContactViewImpl.getContactTableTitles().length];
        for (int i = 0; i < list.size(); i++) {
            final Contact cl = list.get(i);
                 contactTable[i][0] = cl.getName();
                 contactTable[i][1] = cl.getSurname();
                 contactTable[i][2] = cl.getDateOfBirth();
                 contactTable[i][3] = cl.getAddress();
                 contactTable[i][4] = cl.getPhoneNumber();
                 contactTable[i][COLUMN] = cl.getEmail();
         }
         return contactTable;
    }
    /**
     * 
     * @param map
     * map
     * @param data
     * data
     */
    public void modifyContact(final Map<ContactInfo, String> map, final org.joda.time.LocalDate data) {
        Contact contact;
        try {
        final ContactImpl.Builder b = new ContactImpl.Builder();
        if (!map.get(NAME).isEmpty()) {
        b.name(map.get(NAME));
        b.surname(map.get(SURNAME));
        if (!map.get(ADDRESS).isEmpty()) {
            b.address(map.get(ADDRESS));
        }
        if (!map.get(PHONENUMBER).isEmpty()) {
            b.phoneNumber(map.get(PHONENUMBER));
        }
        if (!map.get(EMAIL).isEmpty()) {
            b.email(map.get(EMAIL));
        }
        if (!data.equals(org.joda.time.LocalDate.now())) {
            b.dateOfBirth(data);
        }
            contact = b.build();

            this.checkIfSelection();
            this.selectedContact.ifPresent(g -> {
                MainControllerImpl.manager.currentUser().getCalendar().removeBirthday(g);
                MainControllerImpl.manager.currentUser().getAddressBook().editContact(g, contact);
                this.selectedContact = Optional.empty();
                this.contactView.rebuildTable(this.getContactTable());
                this.contactView.showMessage("Contact successfully modified", this.contactView);

                if (!data.equals(org.joda.time.LocalDate.now())) {
                    MainControllerImpl.manager.currentUser().getCalendar().addBirthday(contact);
                }
            });
        }
    } catch (Exception e) {
        this.contactView.showMessage(e.getMessage(), this.contactView);
    }
    }
    /**
     * remove contact.
     */
    public void removeContact() {
        this.checkIfSelection();
        this.selectedContact.ifPresent(g -> {
            MainControllerImpl.manager.currentUser().getCalendar().removeBirthday(g);
            MainControllerImpl.manager.currentUser().getAddressBook().removeContact(g);
            this.selectedContact = Optional.empty();
            this.contactView.rebuildTable(this.getContactTable());
            this.contactView.showMessage("Contact successfully removed", this.contactView);
        });
    }
    private void checkIfSelection() {
        if (!this.selectedContact.isPresent()) {
            this.contactView.showMessage("No contact selected", this.contactView);
        }
    }
    /**
     * 
     * @return collect
     * collect
     */
    public List<Contact> getAllContacts() {
        return MainControllerImpl.manager.currentUser().getAddressBook().getAddressBook().stream()
                .sorted((g1, g2) -> g1.getName().compareTo(g2.getName()))
                .collect(Collectors.toList());
    }
    @Override
    public void selectContact(final int index) {
        this.selectedContact = Optional.of(this.getAllContacts().get(index));
    }
}
