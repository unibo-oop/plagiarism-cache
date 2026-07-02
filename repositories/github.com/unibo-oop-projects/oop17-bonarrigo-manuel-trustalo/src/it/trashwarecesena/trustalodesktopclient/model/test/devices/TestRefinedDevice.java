package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.devices.RefinedDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.RefinedDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;


/**
 * A test over the construction and equality behaviours of the
 * {@link RefinedDeviceImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestRefinedDevice {

    private final RefinedDevice identifiedDevice;
    private final RefinedDevice unidentifiedDevice;
    private final RefinedDevice differentIdentifiedDevice;
    private final RefinedDevice differentUnidentifiedDevice;
    private final RefinedDevice sameIdentifiedDevice;
    private final RefinedDevice sameUnidentifiedDevice; 

    private final Executable nonPositiveIdentifierParameter = () -> {
        new RefinedDeviceImpl.Builder()
            .identifier(TestConstants.A_NEGATIVE_INTEGER)
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
            .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .progress(TestEntityConstants.WORK_PROGRESS)
            .available(true)
            .lastCommitter(TestEntityConstants.WORKER)
            .lastUpdate(TestConstants.DATE)
            .build();
    };

    private final Executable nonPositiveCategoryDeviceIdentifierParameter = () -> {
        new RefinedDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .categoryDeviceId(TestConstants.A_NEGATIVE_INTEGER)
            .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .progress(TestEntityConstants.WORK_PROGRESS)
            .available(true)
            .lastCommitter(TestEntityConstants.WORKER)
            .lastUpdate(TestConstants.DATE)
            .build();
    };

    private final Executable nullCategoryDeviceIdentifierParameter = () -> {
        new RefinedDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .categoryDeviceId(null)
            .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .progress(TestEntityConstants.WORK_PROGRESS)
            .available(true)
            .lastCommitter(TestEntityConstants.WORKER)
            .lastUpdate(TestConstants.DATE)
            .build();
    };

    private final Executable nullCategoryParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(null)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .build();
    };

    private final Executable nullGenericDeviceParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(null)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .build();
    };

    private final Executable nullWorkProgressParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(null)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .build();
    };

    private final Executable nullTrashwareWorkerParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(null)
        .lastUpdate(TestConstants.DATE)
        .build();
    };

    private final Executable nullDateParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(null)
        .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .annotations(TestConstants.EMPTY_STRING)
        .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .annotations(TestConstants.SINGLE_SPACE_STRING)
        .build();
    };

    private final Executable multiEmptyAnnotationsParameter = () -> {
        new RefinedDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .categoryDeviceId(TestConstants.A_POSITIVE_INTEGER)
        .refining(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .progress(TestEntityConstants.WORK_PROGRESS)
        .available(true)
        .lastCommitter(TestEntityConstants.WORKER)
        .lastUpdate(TestConstants.DATE)
        .annotations(TestConstants.MULTI_SPACE_STRING)
        .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRefinedDevice() {
        this.identifiedDevice = TestIdentifiableConstants.IDENTIFIED_REFINED;
        this.unidentifiedDevice = TestIdentifiableConstants.UNIDENTIFIED_REFINED;
        this.differentIdentifiedDevice = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REFINED;
        this.differentUnidentifiedDevice = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_REFINED;
        this.sameIdentifiedDevice = TestIdentifiableConstants.SAME_IDENTIFIED_REFINED;
        this.sameUnidentifiedDevice = TestIdentifiableConstants.SAME_UNIDENTIFIED_REFINED;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedDevice.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedDevice.getCategoryDeviceId().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedDevice.getDeviceCategory().equals(TestEntityConstants.DEV_CATEGORY));
        assertTrue(identifiedDevice.getGenericDevice().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedDevice.getWorkProgress().equals(TestEntityConstants.WORK_PROGRESS));
        assertTrue(identifiedDevice.isAvailable());
        assertTrue(identifiedDevice.getLastChangeCommitter().equals(TestEntityConstants.WORKER));
        assertTrue(identifiedDevice.getLastChangeDate().equals(TestConstants.DATE));
        assertTrue(identifiedDevice.getAnnotations().get().equals(TestConstants.A_STRING));

        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveCategoryDeviceIdentifierParameter);
        assertThrows(NullPointerException.class, nullCategoryDeviceIdentifierParameter);
        assertThrows(NullPointerException.class, nullCategoryParameter);
        assertThrows(NullPointerException.class, nullGenericDeviceParameter);
        assertThrows(NullPointerException.class, nullWorkProgressParameter);
        assertThrows(NullPointerException.class, nullTrashwareWorkerParameter);
        assertThrows(NullPointerException.class, nullDateParameter);
        assertThrows(IllegalArgumentException.class, emptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyAnnotationsParameter);

    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedDevice.equals(identifiedDevice));
        assertTrue(identifiedDevice.equals(sameIdentifiedDevice));
        assertTrue(sameIdentifiedDevice.equals(identifiedDevice));

        assertFalse(identifiedDevice.equals(unidentifiedDevice));
        assertFalse(identifiedDevice.equals(differentIdentifiedDevice));
        assertFalse(identifiedDevice.equals(differentUnidentifiedDevice));
        assertFalse(identifiedDevice.equals(sameUnidentifiedDevice));

        assertFalse(unidentifiedDevice.equals(unidentifiedDevice));
        assertFalse(unidentifiedDevice.equals(sameUnidentifiedDevice));
        assertFalse(sameUnidentifiedDevice.equals(unidentifiedDevice));
    }
}
