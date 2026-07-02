package it.trashwarecesena.trustalodesktopclient.repository.test.query;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.QueryRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.CreateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.DeleteRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.ReadRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.UpdateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.test.Queries;

/**
 * A test over the four implementation which allows communication of intents
 * into the system.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP")
public class TestRequests {
    @SuppressWarnings("unused")
    private final ScreenResolution solveStaticCyclicDependency = 
        TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;

    /**
     * Test of construction, extraction of data and equality for a {@link CreateRequest}.
     */
    @Test
    public void testCreateRequest() {
        final SingleRequest request = new CreateRequest(TestEntityConstants.COLOR);
        final SingleRequest differentRequest = new CreateRequest(TestEntityConstants.DIFFERENT_COLOR);
        final SingleRequest sameRequest = new CreateRequest(TestEntityConstants.SAME_COLOR);

        assertTrue(request.getDesiredHandler().equals(TestEntityConstants.COLOR.getClass()));
        assertTrue(request.getPayload().equals(TestEntityConstants.SAME_COLOR));
        assertTrue(request.equals(sameRequest));
        assertTrue(sameRequest.equals(request));
        assertFalse(request.equals(differentRequest));
        assertFalse(sameRequest.equals(differentRequest));

        assertThrows(NullPointerException.class, () -> {
            new CreateRequest(null);
        });
    }

    /**
     * Test of construction, extraction of data and equality for a {@link ReadRequest}.
     */
    @Test
    public void testReadRequest() {
        final QueryRequest request = new ReadRequest(Queries.getAll(TestEntityConstants.COLOR.getClass()));
        final QueryRequest differentRequest = new ReadRequest(Queries.getAll(TestEntityConstants.ISA.getClass()));
        final QueryRequest sameRequest = new ReadRequest(
                Queries.getAll(TestEntityConstants.DIFFERENT_COLOR.getClass()));

        assertTrue(request.getQueryType().equals(Color.class));
        assertTrue(request.getQueryObject().equals(Queries.getAll(TestEntityConstants.DIFFERENT_COLOR.getClass())));
        assertTrue(request.equals(sameRequest));
        assertTrue(sameRequest.equals(request));
        assertFalse(request.equals(differentRequest));
        assertFalse(sameRequest.equals(differentRequest));

        assertThrows(NullPointerException.class, () -> {
            new ReadRequest(null);
        });
    }

    /**
     * Test of construction, extraction of data and equality for a {@link UpdateRequest}.
     */
    @Test
    public void testUpdateRequest() {
        final BiRequest request = new UpdateRequest(TestEntityConstants.COLOR, TestEntityConstants.COLOR);
        final BiRequest differentRequest = new UpdateRequest(TestEntityConstants.DIFFERENT_COLOR, 
                TestEntityConstants.DIFFERENT_COLOR);
        final BiRequest sameRequest = new UpdateRequest(TestEntityConstants.SAME_COLOR, 
                TestEntityConstants.SAME_COLOR);

        assertTrue(request.getDesiredHandler().equals(TestEntityConstants.COLOR.getClass()));
        assertTrue(request.getPayload().equals(TestEntityConstants.SAME_COLOR));
        assertTrue(request.getSecondPayload().equals(TestEntityConstants.SAME_COLOR));
        assertTrue(request.equals(sameRequest));
        assertTrue(sameRequest.equals(request));
        assertFalse(request.equals(differentRequest));
        assertFalse(sameRequest.equals(differentRequest));

        assertThrows(NullPointerException.class, () -> {
            new UpdateRequest(null, TestEntityConstants.COLOR);
        });

        assertThrows(NullPointerException.class, () -> {
            new UpdateRequest(TestEntityConstants.COLOR, null);
        });
    }

    /**
     * Test of construction, extraction of data and equality for a {@link DeleteRequest}.
     */
    @Test
    public void testDeleteRequest() {
        final SingleRequest request = new DeleteRequest(TestEntityConstants.COLOR);
        final SingleRequest differentRequest = new DeleteRequest(TestEntityConstants.DIFFERENT_COLOR);
        final SingleRequest sameRequest = new DeleteRequest(TestEntityConstants.SAME_COLOR);

        assertTrue(request.getDesiredHandler().equals(TestEntityConstants.COLOR.getClass()));
        assertTrue(request.getPayload().equals(TestEntityConstants.SAME_COLOR));
        assertTrue(request.equals(sameRequest));
        assertTrue(sameRequest.equals(request));
        assertFalse(request.equals(differentRequest));
        assertFalse(sameRequest.equals(differentRequest));

        assertThrows(NullPointerException.class, () -> {
            new DeleteRequest(null);
        });
    }
}
