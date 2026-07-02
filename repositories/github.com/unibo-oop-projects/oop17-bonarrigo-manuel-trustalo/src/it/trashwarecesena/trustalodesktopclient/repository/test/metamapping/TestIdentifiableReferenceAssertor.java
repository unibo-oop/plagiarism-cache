package it.trashwarecesena.trustalodesktopclient.repository.test.metamapping;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
import it.trashwarecesena.trustalodesktopclient.repository.metamapping.IdentifiableReferenceAssertor;
import it.trashwarecesena.trustalodesktopclient.repository.query.BiRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.SingleRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.CreateRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.DeleteRequest;
import it.trashwarecesena.trustalodesktopclient.repository.query.concreteness.UpdateRequest;

/**
 * A test over the ability of identifying the illegal behaviour of dispatching
 * an object, containing a reference to an
 * {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} which does not meet the rules described by the interface, for
 * creation, update or deletion.
 * 
 * @author Manuel Bonarrigo
 *
 */
public class TestIdentifiableReferenceAssertor {

    private final PhysicalPerson unidentified = TestIdentifiableConstants.UNIDENTIFIED_PH_PERSON;
    private final PhysicalPerson identified = TestIdentifiableConstants.IDENTIFIED_PH_PERSON;
    private final TrashwareWorker illegalCarrier = 
            new TrashwareWorkerImpl(unidentified, TestEntityConstants.WORKER_CATEGORY, true);
    private final TrashwareWorker legalCarrier = 
            new TrashwareWorkerImpl(identified, TestEntityConstants.WORKER_CATEGORY, true);
    private final Integer randomObject = Integer.valueOf(0);

    /**
     * Test over the reaction of the system from a creational point of view.
     */
    @Test
    public void testCreateRequest() {
        final SingleRequest legalIdentifiableRequest = new CreateRequest(unidentified);
        final SingleRequest illegalIdentifiableRequest = new CreateRequest(identified);
        final SingleRequest legalCarrierRequest = new CreateRequest(legalCarrier);
        final SingleRequest illegalCarrierRequest = new CreateRequest(illegalCarrier);
        final SingleRequest illegalRequest = new CreateRequest(randomObject);

        assertTrue(IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(legalIdentifiableRequest)
                .equals(legalIdentifiableRequest));
        assertTrue(IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(legalCarrierRequest)
                .equals(legalCarrierRequest));

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(illegalIdentifiableRequest);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(illegalCarrierRequest);
        });

        assertThrows(IllegalStateException.class, () -> {
            IdentifiableReferenceAssertor.assertCreationalForeignKeysValidity(illegalRequest);
        });
    }

    /**
     * Test over the reaction of the system from an update point of view.
     */
    @Test
    public void testUpdateRequest() {
        final BiRequest legalIdentifiableRequest = new UpdateRequest(identified, identified);
        final BiRequest illegalIdentifiableRequest = new UpdateRequest(unidentified, unidentified);
        final BiRequest legalCarrierRequest = new UpdateRequest(legalCarrier, legalCarrier);
        final BiRequest illegalCarrierRequest = new UpdateRequest(illegalCarrier, illegalCarrier);
        final BiRequest illegalRequest = new UpdateRequest(randomObject, randomObject);

        assertTrue(IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(legalIdentifiableRequest)
                .equals(legalIdentifiableRequest));
        assertTrue(IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(legalCarrierRequest)
                .equals(legalCarrierRequest));

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(illegalIdentifiableRequest);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(illegalCarrierRequest);
        });

        assertThrows(IllegalStateException.class, () -> {
            IdentifiableReferenceAssertor.assertUpdativeForeignKeysValidity(illegalRequest);
        });
    }

    /**
     * Test over the reaction of the system from a deletion point of view.
     */
    @Test
    public void testDeleteRequest() {
        final SingleRequest legalIdentifiableRequest = new DeleteRequest(identified);
        final SingleRequest illegalIdentifiableRequest = new DeleteRequest(unidentified);
        final SingleRequest legalCarrierRequest = new DeleteRequest(legalCarrier);
        final SingleRequest illegalCarrierRequest = new DeleteRequest(illegalCarrier);
        final SingleRequest illegalRequest = new DeleteRequest(randomObject);

        assertTrue(IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(legalIdentifiableRequest)
                .equals(legalIdentifiableRequest));
        assertTrue(IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(legalCarrierRequest)
                .equals(legalCarrierRequest));

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(illegalIdentifiableRequest);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(illegalCarrierRequest);
        });

        assertThrows(IllegalStateException.class, () -> {
            IdentifiableReferenceAssertor.assertDeletionForeignKeysValidity(illegalRequest);
        });
    }
}
