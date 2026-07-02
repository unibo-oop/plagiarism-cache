package it.trashwarecesena.trustalodesktopclient.model.test.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.people.Contact;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.ContactImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link Contact} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestContact {

    private final Contact contact;
    private final Contact differentContact;
    private final Contact sameContact;

    private final Executable nullFirstParameter = () -> {
        new ContactImpl(null, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, TestConstants.A_STRING);
    };

    private final Executable nullSecondParameter = () -> {
        new ContactImpl(TestEntityConstants.CON_CATEGORY, null, TestConstants.A_STRING);
    };

    private final Executable nullThirdParameter = () -> {
        new ContactImpl(TestEntityConstants.CON_CATEGORY, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, null);
    };

    private final Executable emptyValueParameter = () -> {
        new ContactImpl(TestEntityConstants.CON_CATEGORY, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, 
                TestConstants.EMPTY_STRING);
    };

    private final Executable singleEmptyValueParameter = () -> {
        new ContactImpl(TestEntityConstants.CON_CATEGORY, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, 
                TestConstants.SINGLE_SPACE_STRING);
    };

    private final Executable multiEmptyValueParameter = () -> {
        new ContactImpl(TestEntityConstants.CON_CATEGORY, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, 
                TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestContact() {
        @SuppressWarnings("unused") // This fancy call is needed in order to break a circular dependency created by
        // the static initializers of the test constants.
        final Object obj = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_JU_PERSON;

        contact = new ContactImpl(TestEntityConstants.CON_CATEGORY, TestIdentifiableConstants.IDENTIFIED_PH_PERSON, 
                TestConstants.A_STRING);
        differentContact = new ContactImpl(TestEntityConstants.CON_DIFFERENT_CATEGORY, 
                TestIdentifiableConstants.DIFFERENT_IDENTIFIED_PH_PERSON, 
                TestConstants.A_DIFFERENT_STRING);
        sameContact = new ContactImpl(TestEntityConstants.CON_SAME_CATEGORY, 
                TestIdentifiableConstants.SAME_IDENTIFIED_PH_PERSON, TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(contact.getCategory().equals(TestEntityConstants.CON_CATEGORY));
        assertTrue(contact.getOwner().equals(TestIdentifiableConstants.IDENTIFIED_PH_PERSON));
        assertTrue(contact.getValue().equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(NullPointerException.class, nullThirdParameter);
        assertThrows(IllegalArgumentException.class, emptyValueParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyValueParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyValueParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(contact.equals(sameContact));
        assertTrue(sameContact.equals(contact));
        assertFalse(contact.equals(differentContact));
        assertFalse(sameContact.equals(differentContact));
    }
}
