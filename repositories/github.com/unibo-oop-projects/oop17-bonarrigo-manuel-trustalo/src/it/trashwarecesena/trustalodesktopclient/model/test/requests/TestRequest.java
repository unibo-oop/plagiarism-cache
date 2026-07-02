package it.trashwarecesena.trustalodesktopclient.model.test.requests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.requests.Request;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
/**
 * A test over the construction and equality behaviours of the
 * {@link Request} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestRequest {

    private final Request identifiedRequest;
    private final Request unidentifiedRequest;
    private final Request differentIdentifiedRequest;
    private final Request differentUnidentifiedRequest;
    private final Request sameIdentifiedRequest;
    private final Request sameUnidentifiedRequest;

    private final Executable nonPositiveIdentifierParameter = () -> {
        new RequestImpl.Builder()
            .identifier(TestConstants.INT_ZERO)
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nullApplicantParameter = () -> {
        new RequestImpl.Builder()
            .applicant(null)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nullCreationDateParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(null)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nullRequestProgressParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(null)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nullLastUpdateParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(null)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nullLastCommitterParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(null)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .annotations(TestConstants.EMPTY_STRING)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .annotations(TestConstants.SINGLE_SPACE_STRING)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable multiEmptyAnnotationsParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .annotations(TestConstants.MULTI_SPACE_STRING)
            .priority(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable nonPositivePriorityParameter = () -> {
        new RequestImpl.Builder()
            .applicant(TestIdentifiableConstants.IDENTIFIED_PH_PERSON)
            .creationDate(TestConstants.DATE)
            .progress(TestEntityConstants.REQ_PROGRESS)
            .lastUpdate(TestConstants.DATE)
            .lastCommitter(TestEntityConstants.WORKER)
            .priority(TestConstants.A_NEGATIVE_INTEGER)
            .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRequest() {
        this.identifiedRequest = TestIdentifiableConstants.IDENTIFIED_REQUEST;
        this.unidentifiedRequest = TestIdentifiableConstants.UNIDENTIFIED_REQUEST;
        this.differentIdentifiedRequest = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REQUEST;
        this.differentUnidentifiedRequest = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_REQUEST;
        this.sameIdentifiedRequest = TestIdentifiableConstants.SAME_IDENTIFIED_REQUEST;
        this.sameUnidentifiedRequest = TestIdentifiableConstants.SAME_UNIDENTIFIED_REQUEST;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedRequest.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedRequest.getApplicant().equals(TestIdentifiableConstants.IDENTIFIED_PH_PERSON));
        assertTrue(identifiedRequest.getCreationDate().equals(TestConstants.DATE));
        assertTrue(identifiedRequest.getRequestProgress().equals(TestEntityConstants.REQ_PROGRESS));
        assertTrue(identifiedRequest.getLastUpdate().equals(TestConstants.DATE));
        assertTrue(identifiedRequest.getLastCommitter().equals(TestEntityConstants.WORKER));
        assertTrue(identifiedRequest.getReferee().get().equals(TestIdentifiableConstants.IDENTIFIED_PH_PERSON));
        assertTrue(identifiedRequest.getSigner().get().equals(TestIdentifiableConstants.IDENTIFIED_PH_PERSON));
        assertTrue(identifiedRequest.getTrelloLink().get().toExternalForm().equals(
                TestConstants.U_R_L.toExternalForm()));
        assertTrue(identifiedRequest.getAnnotations().get().equals(TestConstants.A_STRING));
        assertTrue(identifiedRequest.getPriority().equals(TestConstants.A_POSITIVE_INTEGER));

        assertThrows(IllegalArgumentException.class, emptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierParameter);
        assertThrows(IllegalArgumentException.class, nonPositivePriorityParameter);
        assertThrows(NullPointerException.class, nullApplicantParameter);
        assertThrows(NullPointerException.class, nullCreationDateParameter);
        assertThrows(NullPointerException.class, nullLastCommitterParameter);
        assertThrows(NullPointerException.class, nullLastUpdateParameter);
        assertThrows(NullPointerException.class, nullRequestProgressParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedRequest.equals(identifiedRequest));
        assertTrue(identifiedRequest.equals(sameIdentifiedRequest));
        assertTrue(sameIdentifiedRequest.equals(identifiedRequest));

        assertFalse(identifiedRequest.equals(unidentifiedRequest));
        assertFalse(identifiedRequest.equals(differentIdentifiedRequest));
        assertFalse(identifiedRequest.equals(differentUnidentifiedRequest));
        assertFalse(identifiedRequest.equals(sameUnidentifiedRequest));

        assertFalse(unidentifiedRequest.equals(unidentifiedRequest));
        assertFalse(unidentifiedRequest.equals(sameUnidentifiedRequest));
        assertFalse(sameUnidentifiedRequest.equals(unidentifiedRequest));
    }
}
