package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.devices.GenericDevice;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.GenericDeviceImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link GenericDeviceImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestGenericDevice {

    private final GenericDevice identifiedDevice;
    private final GenericDevice unidentifiedDevice;
    private final GenericDevice differentIdentifiedDevice;
    private final GenericDevice differentUnidentifiedDevice;
    private final GenericDevice sameIdentifiedDevice;
    private final GenericDevice sameUnidentifiedDevice; 

    private final Executable nullCategoryParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(null)
            .build();
    };

    private final Executable nonPositiveIdentifierParameter = () -> {
        new GenericDeviceImpl.Builder()
            .identifier(TestConstants.A_NEGATIVE_INTEGER)
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .build();
    };

    private final Executable emptyVendorModelIdentifierParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .vendorModelIdentifier(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyVendorModelIdentifierParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .vendorModelIdentifier(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyVendorModelIdentifierParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .vendorModelIdentifier(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable negativeQuantityAvailableParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .available(TestConstants.A_NEGATIVE_INTEGER)
            .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .description(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new GenericDeviceImpl.Builder()
            .deviceCategory(TestEntityConstants.DEV_CATEGORY)
            .description(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };
    private final Executable multiEmptyAnnotationsParameter = () -> {
        new GenericDeviceImpl.Builder()
        .deviceCategory(TestEntityConstants.DEV_CATEGORY)
        .description(TestConstants.MULTI_SPACE_STRING)
        .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestGenericDevice() {
        this.identifiedDevice = TestIdentifiableConstants.IDENTIFIED_DEVICE;
        this.unidentifiedDevice = TestIdentifiableConstants.UNIDENTIFIED_DEVICE;
        this.differentIdentifiedDevice = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE;
        this.differentUnidentifiedDevice = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE;
        this.sameIdentifiedDevice = TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE;
        this.sameUnidentifiedDevice = TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE;
    }
    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedDevice.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedDevice.getDeviceCategory().equals(TestEntityConstants.DEV_CATEGORY));
        assertTrue(identifiedDevice.getVendor().get().equals(TestEntityConstants.VENDOR));
        assertTrue(identifiedDevice.getVendorModelIdentifier().get().equals(TestConstants.A_STRING));
        assertTrue(identifiedDevice.getNumberOfAvailableDevices().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedDevice.getDeviceDescription().get().equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullCategoryParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierParameter);
        assertThrows(IllegalArgumentException.class, negativeQuantityAvailableParameter);
        assertThrows(IllegalArgumentException.class, emptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, emptyVendorModelIdentifierParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyVendorModelIdentifierParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyVendorModelIdentifierParameter);
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
