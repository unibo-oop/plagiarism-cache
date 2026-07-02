package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDeviceCompoundWithGeneric;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceCompoundWithGenericImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link RefinedDeviceCompoundWithGenericImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestRefinedDeviceCompoundWithGeneric {

    private final RefinedDeviceCompoundWithGeneric compound;
    private final RefinedDeviceCompoundWithGeneric differentCompound;
    private final RefinedDeviceCompoundWithGeneric sameCompound;

    private final Executable nullFirstParameter = () -> {
        new RefinedDeviceCompoundWithGenericImpl(null, TestIdentifiableConstants.IDENTIFIED_DEVICE, 
                TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nullSecondParameter = () -> {
        new RefinedDeviceCompoundWithGenericImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, null, 
                TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable negativeQuantityParameter = () -> {
        new RefinedDeviceCompoundWithGenericImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, 
                TestIdentifiableConstants.IDENTIFIED_DEVICE,  TestConstants.A_NEGATIVE_INTEGER);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRefinedDeviceCompoundWithGeneric() {
        this.compound = new RefinedDeviceCompoundWithGenericImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, 
                TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.A_POSITIVE_INTEGER);
        this.differentCompound = 
                new RefinedDeviceCompoundWithGenericImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REFINED, 
                TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE, TestConstants.A_DIFFERENT_POSITIVE_INTEGER);
        this.sameCompound = new RefinedDeviceCompoundWithGenericImpl(TestIdentifiableConstants.SAME_IDENTIFIED_REFINED, 
                TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE, TestConstants.THE_SAME_POSITIVE_INTEGER);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(compound.getComponent().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(compound.getCompound().equals(TestIdentifiableConstants.IDENTIFIED_REFINED));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(IllegalArgumentException.class, negativeQuantityParameter);
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
