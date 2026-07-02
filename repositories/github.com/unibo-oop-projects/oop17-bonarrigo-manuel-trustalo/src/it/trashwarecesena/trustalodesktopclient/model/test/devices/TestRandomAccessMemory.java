package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.RandomAccessMemory;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RandomAccessMemoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;


/**
 * A test over the construction and equality behaviours of the
 * {@link RandomAccessMemoryImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestRandomAccessMemory {

    private final RandomAccessMemory identifiedRam;
    private final RandomAccessMemory unidentifiedRam;
    private final RandomAccessMemory differentIdentifiedRam;
    private final RandomAccessMemory differentUnidentifiedRam;
    private final RandomAccessMemory sameIdentifiedRam;
    private final RandomAccessMemory sameUnidentifiedRam;

    private final Executable nullFirstParameter = () -> {
        new RandomAccessMemoryImpl(null, TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
    };

    private final Executable nullSecondParameter = () -> {
        new RandomAccessMemoryImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, null, TestEntityConstants.INF_UNIT);
    };

    private final Executable nullThirdParameter = () -> {
        new RandomAccessMemoryImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.A_POSITIVE_INTEGER, null);
    };

    private final Executable nonPositiveSecondParameter = () -> {
        new RandomAccessMemoryImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.INT_ZERO, 
                TestEntityConstants.INF_UNIT);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRandomAccessMemory() {
        this.identifiedRam = new RandomAccessMemoryImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE,
                TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
        this.unidentifiedRam = new RandomAccessMemoryImpl(TestIdentifiableConstants.UNIDENTIFIED_DEVICE,
                TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
        this.differentIdentifiedRam = new RandomAccessMemoryImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE,
                TestConstants.A_DIFFERENT_POSITIVE_INTEGER, TestEntityConstants.INF_DIFFERENT_UNIT);
        this.differentUnidentifiedRam = new RandomAccessMemoryImpl(
                TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE, TestConstants.A_POSITIVE_INTEGER,
                TestEntityConstants.INF_DIFFERENT_UNIT);
        this.sameIdentifiedRam = new RandomAccessMemoryImpl(TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE,
                TestConstants.THE_SAME_POSITIVE_INTEGER, TestEntityConstants.INF_SAME_UNIT);
        this.sameUnidentifiedRam = new RandomAccessMemoryImpl(TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE,
                TestConstants.THE_SAME_POSITIVE_INTEGER, TestEntityConstants.INF_SAME_UNIT);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedRam.getGenericDevice().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedRam.getCapacity().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedRam.getCapacityUnit().equals(TestEntityConstants.INF_UNIT));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(NullPointerException.class, nullThirdParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedRam.equals(identifiedRam));
        assertTrue(identifiedRam.equals(sameIdentifiedRam));
        assertTrue(sameIdentifiedRam.equals(identifiedRam));

        assertFalse(identifiedRam.equals(unidentifiedRam));
        assertFalse(identifiedRam.equals(differentIdentifiedRam));
        assertFalse(identifiedRam.equals(differentUnidentifiedRam));
        assertFalse(identifiedRam.equals(sameUnidentifiedRam));

        assertFalse(unidentifiedRam.equals(sameUnidentifiedRam));
        assertFalse(sameUnidentifiedRam.equals(unidentifiedRam));
    }

}
