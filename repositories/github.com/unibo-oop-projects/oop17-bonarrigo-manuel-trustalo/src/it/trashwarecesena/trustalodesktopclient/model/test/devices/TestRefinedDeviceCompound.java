package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompound;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link RefinedDeviceCompoundImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestRefinedDeviceCompound {

    private final RefinedDeviceCompound compound;
    private final RefinedDeviceCompound differentCompound;
    private final RefinedDeviceCompound sameCompound;

    private final Executable nullFirstParameter = () -> {
        new RefinedDeviceCompoundImpl(null, TestIdentifiableConstants.IDENTIFIED_REFINED);
    };

    private final Executable nullSecondParameter = () -> {
        new RefinedDeviceCompoundImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, null);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRefinedDeviceCompound() {
        @SuppressWarnings("unused") // This fancy call is needed in order to break a circular dependency created by
                                    // the static initializers of the test constants.
        final Object obj = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_JU_PERSON;

        this.compound = new RefinedDeviceCompoundImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, 
                TestIdentifiableConstants.IDENTIFIED_REFINED);
        this.differentCompound = new RefinedDeviceCompoundImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REFINED,
                TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REFINED);
        this.sameCompound = new RefinedDeviceCompoundImpl(TestIdentifiableConstants.SAME_IDENTIFIED_REFINED,
                TestIdentifiableConstants.SAME_IDENTIFIED_REFINED);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(compound.getComponent().equals(TestIdentifiableConstants.IDENTIFIED_REFINED));
        assertTrue(compound.getCompound().equals(TestIdentifiableConstants.IDENTIFIED_REFINED));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(compound.equals(sameCompound));
        assertTrue(sameCompound.equals(compound));
        assertFalse(compound.equals(differentCompound));
        assertFalse(sameCompound.equals(differentCompound));
    }
}
