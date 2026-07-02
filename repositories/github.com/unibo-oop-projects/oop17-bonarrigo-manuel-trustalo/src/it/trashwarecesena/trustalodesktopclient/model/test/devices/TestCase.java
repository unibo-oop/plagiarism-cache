package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.Case;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.CaseImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link CaseImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestCase {

    private final Case identifiedCaseModel;
    private final Case unidentifiedCaseModel;
    private final Case differentIdentifiedCaseModel;
    private final Case differentUnidentifiedCaseModel;
    private final Case sameIdentifiedCaseModel;
    private final Case sameUnidentifiedCaseModel; 

    private final Executable nullFirstParameter = () -> {
        new CaseImpl(null, TestEntityConstants.COLOR);
    };

    private final Executable nullSecondParameter = () -> {
        new CaseImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, null);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestCase() {
        this.identifiedCaseModel = new CaseImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestEntityConstants.COLOR);
        this.unidentifiedCaseModel = new CaseImpl(TestIdentifiableConstants.UNIDENTIFIED_DEVICE,
                TestEntityConstants.COLOR);
        this.differentIdentifiedCaseModel = new CaseImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE,
                TestEntityConstants.DIFFERENT_COLOR);
        this.differentUnidentifiedCaseModel = new CaseImpl(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE,
                TestEntityConstants.DIFFERENT_COLOR);
        this.sameIdentifiedCaseModel = new CaseImpl(TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE,
                TestEntityConstants.SAME_COLOR);
        this.sameUnidentifiedCaseModel = new CaseImpl(TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE,
                TestEntityConstants.SAME_COLOR);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedCaseModel.getGenericDevice()
                                      .equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedCaseModel.getColor()
                                      .equals(TestEntityConstants.COLOR));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedCaseModel.equals(identifiedCaseModel));
        assertTrue(identifiedCaseModel.equals(sameIdentifiedCaseModel));
        assertTrue(sameIdentifiedCaseModel.equals(identifiedCaseModel));

        assertFalse(identifiedCaseModel.equals(unidentifiedCaseModel));
        assertFalse(identifiedCaseModel.equals(differentIdentifiedCaseModel));
        assertFalse(identifiedCaseModel.equals(differentUnidentifiedCaseModel));
        assertFalse(identifiedCaseModel.equals(sameUnidentifiedCaseModel));

        assertFalse(unidentifiedCaseModel.equals(sameUnidentifiedCaseModel));
        assertFalse(sameUnidentifiedCaseModel.equals(unidentifiedCaseModel));
    }
}
