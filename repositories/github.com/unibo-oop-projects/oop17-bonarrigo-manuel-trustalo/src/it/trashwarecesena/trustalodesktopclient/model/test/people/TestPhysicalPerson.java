package it.trashwarecesena.trustalodesktopclient.model.test.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PersonCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.PhysicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link PhysicalPerson} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestPhysicalPerson {

    private final PhysicalPerson identifiedPhysicalPerson;
    private final PhysicalPerson unidentifiedPhysicalPerson;
    private final PhysicalPerson differentIdentifiedPhysicalPerson;
    private final PhysicalPerson differentUnidentifiedPhysicalPerson;
    private final PhysicalPerson sameIdentifiedPhysicalPerson;
    private final PhysicalPerson sameUnidentifiedPhysicalPerson;

    private final Executable nullNameParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(null)
            .build();
    };

    private final Executable emptyNameParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable emptyFiscalCodeParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyNameParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable singleEmptyFiscalCodeParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyNameParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyFiscalCodeParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyAnnotationsParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable nonPositiveIdentifierParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .identifier(TestConstants.INT_ZERO)
            .name(TestConstants.A_STRING)
            .build();
    };

    private final Executable emptyBirthLocationParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .birthLocation(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyBirthLocationParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .birthLocation(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyBirthLocationParameter = () -> {
        new PhysicalPersonImpl.Builder()
            .name(TestConstants.A_STRING)
            .birthLocation(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestPhysicalPerson() {
        this.identifiedPhysicalPerson = TestIdentifiableConstants.IDENTIFIED_PH_PERSON;
        this.unidentifiedPhysicalPerson = TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON;
        this.differentIdentifiedPhysicalPerson = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_PH_PERSON;
        this.differentUnidentifiedPhysicalPerson = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_PH_PERSON;
        this.sameIdentifiedPhysicalPerson = TestIdentifiableConstants.SAME_IDENTIFIED_PH_PERSON;
        this.sameUnidentifiedPhysicalPerson = TestIdentifiableConstants.SAME_UNIDENTIFIED_PH_PERSON;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedPhysicalPerson.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedPhysicalPerson.getName().equals(TestConstants.A_STRING));
        assertTrue(identifiedPhysicalPerson.getCategory().equals(new PersonCategoryImpl("Privato")));
        assertTrue(identifiedPhysicalPerson.getFiscalCode().get().equals(TestConstants.A_STRING));
        assertTrue(identifiedPhysicalPerson.getAnnotations().get().equals(TestConstants.A_STRING));
        assertTrue(identifiedPhysicalPerson.getBirthDate().get().equals(TestConstants.DATE));
        assertTrue(identifiedPhysicalPerson.getBirthLocation().get().equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullNameParameter);
        assertThrows(IllegalArgumentException.class, emptyNameParameter);
        assertThrows(IllegalArgumentException.class, emptyFiscalCodeParameter);
        assertThrows(IllegalArgumentException.class, emptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyNameParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyFiscalCodeParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyNameParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyFiscalCodeParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierParameter);
        assertThrows(IllegalArgumentException.class, emptyBirthLocationParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyBirthLocationParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyBirthLocationParameter);

    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedPhysicalPerson.equals(identifiedPhysicalPerson));
        assertTrue(identifiedPhysicalPerson.equals(sameIdentifiedPhysicalPerson));
        assertTrue(sameIdentifiedPhysicalPerson.equals(identifiedPhysicalPerson));

        assertFalse(identifiedPhysicalPerson.equals(unidentifiedPhysicalPerson));
        assertFalse(identifiedPhysicalPerson.equals(differentIdentifiedPhysicalPerson));
        assertFalse(identifiedPhysicalPerson.equals(differentUnidentifiedPhysicalPerson));
        assertFalse(identifiedPhysicalPerson.equals(sameUnidentifiedPhysicalPerson));

        assertFalse(unidentifiedPhysicalPerson.equals(unidentifiedPhysicalPerson));
        assertFalse(unidentifiedPhysicalPerson.equals(sameUnidentifiedPhysicalPerson));
        assertFalse(sameUnidentifiedPhysicalPerson.equals(unidentifiedPhysicalPerson));
    }
}
