package it.unibo.coinquify.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

import org.junit.Test;

import it.unibo.coinquify.telephonebook.controller.TelephoneBookController;
import it.unibo.coinquify.telephonebook.controller.TelephoneBookControllerImpl;
import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.telephonebook.model.ContactImpl;
import it.unibo.coinquify.telephonebook.model.TelephoneBook;
import it.unibo.coinquify.telephonebook.model.TelephoneBookImpl;
import it.unibo.coinquify.utils.DateLabelFormatter;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PhoneNumberPresentException;

//CHECKSTYLE:OFF
/**
 * Test PhoneBook.
 */
public class TestPhoneBook {
    /**
     * test a phonebook.
     * 
     * @throws PhoneNumberPresentException
     * @throws IllegalArgumentException
     */
    @Test
    public void testPhoneBookSimple() throws IllegalArgumentException, PhoneNumberPresentException {
        final Calendar cal = Calendar.getInstance();
        final TelephoneBook contactList = new TelephoneBookImpl();
        Messages.getMessages().setCurrentLocale(new Locale("it"));
        final int size = contactList.searchContacts(null).size();
        cal.set(1996, 7, 28);
        final Contact giacomo = new ContactImpl("Giacomo", "Montalti", "", "3311069864", Optional.of(cal.getTime()),
                "Via Strada Casale 22/A", "giacomo@montalti.it", "054688097", "");
        contactList.addContact(giacomo);

        final Contact mauro = new ContactImpl("Mauro", "Daddi", "", "596950494", Optional.empty(), "Via Campidori 34",
                "maurito456@liverani.it", "", "");
        contactList.addContact(mauro);
        assertTrue(contactList.searchContacts(null).size() == size+2);
        assertTrue(contactList.isNumberAlreadyInserted("596950494"));
        assertEquals(contactList.searchContacts("MAURO Daddi").size(), 1);
        assertFalse(contactList.isNumberAlreadyInserted("2020"));
        assertEquals(contactList.searchContacts("giacomo").size(), 1);
        contactList.removeContact(giacomo);
        assertEquals(contactList.searchContacts("giacomo").size(), 0);
        assertFalse(contactList.isNumberAlreadyInserted("3311069864")); // Giacomo's_number
                                                                        // ->No
                                                                        // deleted
        assertEquals(contactList.searchContacts(null).size(), size+1);
        contactList.addContact(giacomo);
        assertEquals(contactList.searchContacts(null).size(), size+2);
    }

    /**
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws PhoneNumberPresentException
     * @throws IllegalArgumentException
     */
    @Test
    public void testEditAndException()
            throws ClassNotFoundException, IOException, IllegalArgumentException, PhoneNumberPresentException {
        Messages.getMessages().setCurrentLocale(new Locale("it"));
        final TelephoneBookController controller = new TelephoneBookControllerImpl(
                new DateLabelFormatter().getDateFormatter());
        final Calendar cal = Calendar.getInstance();
        final int size = controller.getAllContacts().size();

        cal.set(1996, 7, 28);
        final Contact giacomo = new ContactImpl("Fabrizio", "Allievi", "", "5884385943", Optional.of(cal.getTime()),
                "", "", "", "");
        controller.addContact(giacomo);

        final Contact mauro = new ContactImpl("Roberto", "Rossi", "", "0493384953", Optional.empty(),
                "", "", "", "");

        final Contact mauroCopy = new ContactImpl("Roberto", "Blu", "", "0493384953", Optional.empty(),
                "", "", "", "");
        controller.addContact(mauro);

        assertEquals(controller.getAllContacts().size(), size+2);

        /*
         * Try to add a different contact but with same phonenumber
         * ->PhoneNumberPresent
         */
        try {
            controller.addContact(mauroCopy);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(PhoneNumberPresentException.class));
        }

        /*
         * Try to add a contact with no phonenumber
         */
        try {
            final Contact a = new ContactImpl("A", "B", "", "", Optional.empty(), "", "", "", "");
            controller.addContact(a);
        } catch (Exception e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
            assertFalse(e.getClass().equals(PhoneNumberPresentException.class));
        }
    }
}
//CHECKSTILE:ON