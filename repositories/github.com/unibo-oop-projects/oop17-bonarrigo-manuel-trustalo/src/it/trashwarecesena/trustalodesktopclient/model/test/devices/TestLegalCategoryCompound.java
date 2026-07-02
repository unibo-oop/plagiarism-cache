package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.LegalCategoryCompound;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.LegalCategoryCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link LegalCategoryCompoundImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestLegalCategoryCompound {

    private final LegalCategoryCompound legalCompound;
    private final LegalCategoryCompound differentCompound;
    private final LegalCategoryCompound sameCompound;

    private final Executable nullFirstParameter = () -> {
        new LegalCategoryCompoundImpl(null, TestEntityConstants.DEV_CATEGORY);
    };

    private final Executable nullSecondParameter = () -> {
        new LegalCategoryCompoundImpl(TestEntityConstants.DEV_CATEGORY, null);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestLegalCategoryCompound() {
        @SuppressWarnings("unused") // This fancy call is needed in order to break a circular dependency created by
                                    // the static initializers of the test constants.
        final Object obj = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_JU_PERSON;

        this.legalCompound = 
                new LegalCategoryCompoundImpl(TestEntityConstants.DEV_CATEGORY, TestEntityConstants.DEV_CATEGORY);
        this.differentCompound = 
                new LegalCategoryCompoundImpl(TestEntityConstants.DIFFERENT_DEV_CATEGORY, 
                        TestEntityConstants.DIFFERENT_DEV_CATEGORY);
        this.sameCompound = new LegalCategoryCompoundImpl(TestEntityConstants.SAME_DEV_CATEGORY, 
                TestEntityConstants.SAME_DEV_CATEGORY);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(legalCompound.getComponent().equals(TestEntityConstants.DEV_CATEGORY));
        assertTrue(legalCompound.getCompound().equals(TestEntityConstants.DEV_CATEGORY));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(legalCompound.equals(sameCompound));
        assertTrue(sameCompound.equals(legalCompound));
        assertFalse(legalCompound.equals(differentCompound));
        assertFalse(sameCompound.equals(differentCompound));
    }
}
