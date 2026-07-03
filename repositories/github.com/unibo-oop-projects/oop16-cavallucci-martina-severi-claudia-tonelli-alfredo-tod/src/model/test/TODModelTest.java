package model.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import org.joda.time.LocalDate;
import org.junit.Test;

import model.AddressBook;
import model.AddressBookImpl;
import model.Contact;
import model.ContactImpl;
import model.Task;
import model.TaskImpl;
import model.ToDo;
import model.ToDoImpl;
import model.User;
import model.UserImpl;
import model.UserManager;
import model.UserManagerImpl;

/**
 * Class Test.
 *
 */
public class TODModelTest {

    /**
     * Checks whether the UserManagerImpl class works correctly by testing the
     * class's main functions.
     */
    @Test
    public void testUserManagerImpl() {
        boolean check = false;
        final UserManager usersList = UserManagerImpl.getManager();
        User user1 = null;
        User user2 = null;

        /* USER CREATION */

        try {
            user1 = new UserImpl("alfredo", "161996");
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            fail("Required fields are set");
        }
        try {
            user2 = new UserImpl("alfredo", "161997");
        } catch (NoSuchAlgorithmException | IllegalArgumentException e1) {
            fail("Required fields are set");
        }

        /* INSERTING USER IN THE COLLECTION */
        assertEquals("No users are present in the collection", Collections.EMPTY_SET, usersList.getAll());

        try {
            usersList.add(user1);
        } catch (IllegalStateException e) {
            fail("The user has not been entered");
        }
        assertSame("There is a user in the collection", 1, usersList.getAll().size());

        /* INSERTING DUPLICATE USER IN THE COLLECTION */
        try {
            usersList.add(user2);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("Can not insert an existing user", check);

        /* USER CREDENTIAL CONTROL */
        assertTrue("The credentials are valid", usersList.login(user1));

        assertFalse("Credentials are not valid", usersList.login(user2));

    }

    /**
     * Checks whether the AddressBookImpl class works correctly by testing the
     * class's main functions.
     */
    @Test
    public void testAddressBookImpl() {
        boolean check = false;
        final AddressBook addressBook = new AddressBookImpl();
        Contact contact1 = null;
        Contact contact2 = null;
        Contact contact3 = null;
        final LocalDate date1 = LocalDate.parse("1996-01-16");
        final LocalDate date2 = LocalDate.parse("1995-03-06");

        /* CONTACT CREATION */
        contact1 = new ContactImpl.Builder().name("Alfredo").surname("Tonelli").dateOfBirth(date1).address("Via Gorolo")
                .phoneNumber("3339341595").build();
        contact2 = new ContactImpl.Builder().name("Aldo").surname("Tonelli").dateOfBirth(date2).address("Via Borghi")
                .build();
        contact3 = new ContactImpl.Builder().name("Aldo").surname("Tonelli").dateOfBirth(date2).email("aldo@unibo.it")
                .build();

        /* INSERTING CONTACT IN THE ADDRESS BOOK */

        assertEquals("No contact are prensent in address book", Collections.EMPTY_LIST, addressBook.getAddressBook());

        try {
            addressBook.addContact(contact1);
            addressBook.addContact(contact3);
        } catch (IllegalStateException e) {
            fail("Required fields are set");
        }
        assertSame("There are two users in the address book", 2, addressBook.getAddressBook().size());

        /* INSERTING DUPLICATE CONTACT IN THE ADDRESS BOOK */

        check = false;
        try {
            addressBook.addContact(contact2);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("Can not insert an existing contact", check);

        /* REMOVING A CONTACT */
        assertSame("There are two users in the address book", 2, addressBook.getAddressBook().size());

        addressBook.removeContact(contact1);

        assertSame("There is one user in the address book", 1, addressBook.getAddressBook().size());

        /* REMOVING A NOT-EXISTENT CONTACT */

        check = false;
        try {
            addressBook.removeContact(contact1);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("Can not remove a contact that does not exist", check);

        /* EDIT A CONTACT */

        assertTrue("The Address Book contains contact3", addressBook.getAddressBook().contains(contact3));

        try {
            addressBook.editContact(contact3, contact2);
        } catch (IllegalStateException e) {
            fail("The contact can be modified");
        }

        /* EDIT A DUPLICATED CONTACT */

        addressBook.addContact(contact1);

        check = false;
        try {
            addressBook.editContact(contact3, contact1);
        } catch (IllegalStateException e) {
            check = true;
        }
        assertTrue("Can not modify a contact with the same data as an existing one", check);
    }

    /**
     * Checks whether the ToDoListImpl class works correctly by testing the
     * class's main functions.
     */
    @Test
    public void testToDoListImpl() {
        boolean check = false;
        final ToDo tasksList = new ToDoImpl();

        final int notExistingIndex = 5;

        /* TO DO CREATION */
        final Task task1 = new TaskImpl("Go shopping");
        final Task task2 = new TaskImpl("Clean the bedroom");
        final Task task3 = new TaskImpl("Clean the bedroom");

        /* INSERTING TO DO IN THE TASKS LIST */
        try {
            tasksList.addTask(task1);
            tasksList.addTask(task2);
        } catch (IllegalArgumentException e) {
            fail("Required fields are set ");
        }

        /* INSERTING DUPLICATED TO DO IN THE TASKS LIST */
        check = false;
        try {
            tasksList.addTask(task3);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("Can not insert an existing to do", check);
        assertSame("There are two to do in tasks list", 2, tasksList.getTodo().size());

        /* COMPLETE A TO DO */
        tasksList.setTaskComplete(0);
        assertTrue("The 1st to is completed", tasksList.getTodo().get(0).isCompleted());

        tasksList.setTaskIncomplete(1);
        assertFalse("The 3rd to do is not completed", tasksList.getTodo().get(1).isCompleted());

        check = false;
        try {
            tasksList.setTaskComplete(notExistingIndex);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("The index is not in list", check);

        /* DELETE A TO DO */
        tasksList.removeTask(task2);
        tasksList.removeTask(task1);
        check = false;
        try {
            tasksList.removeTask(task3);
        } catch (IllegalArgumentException e) {
            check = true;
        }
        assertTrue("Can not remove a to do does not exist", check);

        assertSame("The tasks list is empty", 0, tasksList.getTodo().size());
    }

}
