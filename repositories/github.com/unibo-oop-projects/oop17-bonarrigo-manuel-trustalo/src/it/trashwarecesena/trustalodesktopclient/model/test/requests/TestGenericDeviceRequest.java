package it.trashwarecesena.trustalodesktopclient.model.test.requests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.requests.GenericDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.GenericDeviceRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link GenericDeviceRequest} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestGenericDeviceRequest {

    private final GenericDeviceRequest request;
    private final GenericDeviceRequest differentRequest;
    private final GenericDeviceRequest sameRequest;

    private final Executable nullFirstParameter = () -> {
        new GenericDeviceRequestImpl(null, TestIdentifiableConstants.IDENTIFIED_DEVICE, 
                TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nullSecondParameter = () -> {
        new GenericDeviceRequestImpl(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL, null, 
                TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nullThirdParameter = () -> {
        new GenericDeviceRequestImpl(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL, 
                TestIdentifiableConstants.IDENTIFIED_DEVICE, null);
    };

    private final Executable negativeQuantityParameter = () -> {
        new GenericDeviceRequestImpl(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL, 
                TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.A_NEGATIVE_INTEGER);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestGenericDeviceRequest() {
        request = new GenericDeviceRequestImpl(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL, 
                TestIdentifiableConstants.IDENTIFIED_DEVICE, TestConstants.A_POSITIVE_INTEGER);
        differentRequest = new GenericDeviceRequestImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REQUEST_DETAIL, 
                TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE, TestConstants.A_DIFFERENT_POSITIVE_INTEGER);
        sameRequest = new GenericDeviceRequestImpl(TestIdentifiableConstants.SAME_IDENTIFIED_REQUEST_DETAIL, 
                TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE, TestConstants.THE_SAME_POSITIVE_INTEGER);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(request.getRequestDetail().equals(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL));
        assertTrue(request.getDeviceRequested().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(request.getQuantityRequested().equals(TestConstants.A_POSITIVE_INTEGER));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(NullPointerException.class, nullThirdParameter);
        assertThrows(IllegalArgumentException.class, negativeQuantityParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(request.equals(sameRequest));
        assertTrue(sameRequest.equals(request));
        assertFalse(request.equals(differentRequest));
        assertFalse(request.equals(differentRequest));
    }
}
