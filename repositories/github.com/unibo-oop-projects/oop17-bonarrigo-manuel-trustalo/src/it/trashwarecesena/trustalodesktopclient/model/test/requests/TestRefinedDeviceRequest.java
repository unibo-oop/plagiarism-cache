package it.trashwarecesena.trustalodesktopclient.model.test.requests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.requests.RefinedDeviceRequest;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RefinedRequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link RefinedDeviceImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestRefinedDeviceRequest {
    private final RefinedDeviceRequest request;
    private final RefinedDeviceRequest differentRequest;
    private final RefinedDeviceRequest sameRequest;

    private final Executable nullFirstParameter = () -> {
        new RefinedRequestImpl(null, TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL);
    };

    private final Executable nullSecondParameter = () -> {
        new RefinedRequestImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, null);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRefinedDeviceRequest() {
        request = new RefinedRequestImpl(TestIdentifiableConstants.IDENTIFIED_REFINED, 
                TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL);
        differentRequest = new RefinedRequestImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REFINED, 
                TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REQUEST_DETAIL);
        sameRequest = new RefinedRequestImpl(TestIdentifiableConstants.SAME_IDENTIFIED_REFINED, 
                TestIdentifiableConstants.SAME_IDENTIFIED_REQUEST_DETAIL);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(request.getRequestDetail().equals(TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL));
        assertTrue(request.getDeviceRequested().equals(TestIdentifiableConstants.IDENTIFIED_REFINED));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
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
