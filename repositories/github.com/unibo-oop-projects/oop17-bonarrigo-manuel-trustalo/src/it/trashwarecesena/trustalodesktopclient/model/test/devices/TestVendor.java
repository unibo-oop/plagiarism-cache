package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.Vendor;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.VendorImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link VendorImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestVendor {

    private final Vendor vendor;
    private final Vendor differentVendor;
    private final Vendor sameVendor;

    private final Executable nullParameter = () -> {
        new VendorImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new VendorImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new VendorImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new VendorImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestVendor() {
        this.vendor = new VendorImpl(TestConstants.A_STRING);
        this.differentVendor = new VendorImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameVendor = new VendorImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(vendor.getName().equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullParameter);
        assertThrows(IllegalArgumentException.class, emptyParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(vendor.equals(sameVendor));
        assertTrue(sameVendor.equals(vendor));
        assertFalse(vendor.equals(differentVendor));
        assertFalse(sameVendor.equals(differentVendor));
    }
}
