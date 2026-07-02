package it.trashwarecesena.trustalodesktopclient.model.test.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.people.JuridicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.JuridicalPersonImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link JuridicalPerson} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestJuridicalPerson {

    private final JuridicalPerson identifiedJuridicalPerson;
    private final JuridicalPerson unidentifiedJuridicalPerson;
    private final JuridicalPerson differentIdentifiedJuridicalPerson;
    private final JuridicalPerson differentUnidentifiedJuridicalPerson;
    private final JuridicalPerson sameIdentifiedJuridicalPerson;
    private final JuridicalPerson sameUnidentifiedJuridicalPerson;

    private final Executable nullNameParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(null)
            .build();
    };

    private final Executable nullCategoryParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(null)
            .name(TestConstants.A_STRING)
            .build();
    };

    private final Executable emptyNameParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable emptyFiscalCodeParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyNameParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable singleEmptyFiscalCodeParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyNameParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyFiscalCodeParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .fiscalCode(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyAnnotationsParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .annotations(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable nonPositiveIdentifierParameter = () -> {
        new JuridicalPersonImpl.Builder()
            .identifier(TestConstants.INT_ZERO)
            .category(TestEntityConstants.PER_CATEGORY)
            .name(TestConstants.A_STRING)
            .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestJuridicalPerson() {
        this.identifiedJuridicalPerson = TestIdentifiableConstants.IDENTIFIED_JU_PERSON;
        this.unidentifiedJuridicalPerson = TestIdentifiableConstants.UNIDENTIFIED_JU_PERSON;
        this.differentIdentifiedJuridicalPerson = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_JU_PERSON;
        this.differentUnidentifiedJuridicalPerson = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_JU_PERSON;
        this.sameIdentifiedJuridicalPerson = TestIdentifiableConstants.SAME_IDENTIFIED_JU_PERSON;
        this.sameUnidentifiedJuridicalPerson = TestIdentifiableConstants.SAME_UNIDENTIFIED_JU_PERSON;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedJuridicalPerson.getNumericIdentifier()
                                            .get()
                                            .equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedJuridicalPerson.getName()
                                            .equals(TestConstants.A_STRING));
        assertTrue(identifiedJuridicalPerson.getCategory()
                                            .equals(TestEntityConstants.PER_CATEGORY));
        assertTrue(identifiedJuridicalPerson.getFiscalCode()
                                            .get()
                                            .equals(TestConstants.A_STRING));
        assertTrue(identifiedJuridicalPerson.getAnnotations()
                                            .get()
                                            .equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullNameParameter);
        assertThrows(NullPointerException.class, nullCategoryParameter);
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
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedJuridicalPerson.equals(identifiedJuridicalPerson));
        assertTrue(identifiedJuridicalPerson.equals(sameIdentifiedJuridicalPerson));
        assertTrue(sameIdentifiedJuridicalPerson.equals(identifiedJuridicalPerson));

        assertFalse(identifiedJuridicalPerson.equals(unidentifiedJuridicalPerson));
        assertFalse(identifiedJuridicalPerson.equals(differentIdentifiedJuridicalPerson));
        assertFalse(identifiedJuridicalPerson.equals(differentUnidentifiedJuridicalPerson));
        assertFalse(identifiedJuridicalPerson.equals(sameUnidentifiedJuridicalPerson));

        assertFalse(unidentifiedJuridicalPerson.equals(unidentifiedJuridicalPerson));
        assertFalse(unidentifiedJuridicalPerson.equals(sameUnidentifiedJuridicalPerson));
        assertFalse(sameUnidentifiedJuridicalPerson.equals(unidentifiedJuridicalPerson));
    }
}
