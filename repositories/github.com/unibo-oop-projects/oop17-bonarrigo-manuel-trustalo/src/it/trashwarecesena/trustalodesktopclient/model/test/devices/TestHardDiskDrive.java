package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.HardDiskDrive;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.HardDiskDriveImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link HardDiskDriveImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestHardDiskDrive {
    private final HardDiskDrive identifiedHdd;
    private final HardDiskDrive unidentifiedHdd;
    private final HardDiskDrive differentIdentifiedHdd;
    private final HardDiskDrive differentUnidentifiedHdd;
    private final HardDiskDrive sameIdentifiedHdd;
    private final HardDiskDrive sameUnidentifiedHdd;

    private final Executable nullFirstParameter = () -> {
        new HardDiskDriveImpl(null, TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
    };

    private final Executable nullSecondParameter = () -> {
        new HardDiskDriveImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, null, TestEntityConstants.INF_UNIT);
    };

    private final Executable nullThirdParameter = () -> {
        new HardDiskDriveImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.A_POSITIVE_INTEGER, null);
    };

    private final Executable nonPositiveSecondParameter = () -> {
        new HardDiskDriveImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.INT_ZERO,
                TestEntityConstants.INF_UNIT);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestHardDiskDrive() {
        this.identifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE,
                TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
        this.unidentifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.UNIDENTIFIED_DEVICE,
                TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_UNIT);
        this.differentIdentifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE,
                TestConstants.A_DIFFERENT_POSITIVE_INTEGER, TestEntityConstants.INF_DIFFERENT_UNIT);
        this.differentUnidentifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE,
                TestConstants.A_POSITIVE_INTEGER, TestEntityConstants.INF_DIFFERENT_UNIT);
        this.sameIdentifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE,
                TestConstants.THE_SAME_POSITIVE_INTEGER, TestEntityConstants.INF_SAME_UNIT);
        this.sameUnidentifiedHdd = new HardDiskDriveImpl(TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE,
                TestConstants.THE_SAME_POSITIVE_INTEGER, TestEntityConstants.INF_SAME_UNIT);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedHdd.getGenericDevice()
                                .equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedHdd.getCapacity()
                                .equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedHdd.getCapacityUnit()
                                .equals(TestEntityConstants.INF_UNIT));

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
        assertTrue(identifiedHdd.equals(identifiedHdd));
        assertTrue(identifiedHdd.equals(sameIdentifiedHdd));
        assertTrue(sameIdentifiedHdd.equals(identifiedHdd));

        assertFalse(identifiedHdd.equals(unidentifiedHdd));
        assertFalse(identifiedHdd.equals(differentIdentifiedHdd));
        assertFalse(identifiedHdd.equals(differentUnidentifiedHdd));
        assertFalse(identifiedHdd.equals(sameUnidentifiedHdd));

        assertFalse(unidentifiedHdd.equals(sameUnidentifiedHdd));
        assertFalse(sameUnidentifiedHdd.equals(unidentifiedHdd));
    }

}
