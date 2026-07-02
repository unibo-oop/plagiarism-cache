package it.trashwarecesena.trustalodesktopclient.model.test.requests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.requests.RequestDetail;
import it.trashwarecesena.trustalodesktopclient.model.requests.concreteness.RequestDetailImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;
/**
 * A test over the construction and equality behaviours of the
 * {@link RequestDetail} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestRequestDetail {

    private final RequestDetail identifiedRequestDetail;
    private final RequestDetail unidentifiedRequestDetail;
    private final RequestDetail differentIdentifiedRequestDetail;
    private final RequestDetail differentUnidentifiedRequestDetail;
    private final RequestDetail sameIdentifiedRequestDetail;
    private final RequestDetail sameUnidentifiedRequestDetail;

    private final Executable nonPositiveIdentifierParameter = () -> {
        new RequestDetailImpl.Builder()
            .identifier(TestConstants.INT_ZERO)
            .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
            .category(TestEntityConstants.DEV_CATEGORY)
            .annotations(TestConstants.A_STRING)
            .quantity(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    private final Executable negativeQuantityParameter = () -> {
        new RequestDetailImpl.Builder()
            .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
            .category(TestEntityConstants.DEV_CATEGORY)
            .annotations(TestConstants.A_STRING)
            .quantity(TestConstants.A_NEGATIVE_INTEGER)
            .build();
    };

    private final Executable emptyAnnotationsParameter = () -> {
        new RequestDetailImpl.Builder()
            .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
            .category(TestEntityConstants.DEV_CATEGORY)
            .annotations(TestConstants.EMPTY_STRING)
            .build();
    };

    private final Executable singleEmptyAnnotationsParameter = () -> {
        new RequestDetailImpl.Builder()
            .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
            .category(TestEntityConstants.DEV_CATEGORY)
            .annotations(TestConstants.SINGLE_SPACE_STRING)
            .build();
    };

    private final Executable multiEmptyAnnotationsParameter = () -> {
        new RequestDetailImpl.Builder()
            .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
            .category(TestEntityConstants.DEV_CATEGORY)
            .annotations(TestConstants.MULTI_SPACE_STRING)
            .build();
    };

    private final Executable nullCategoryParameter = () -> {
        new RequestDetailImpl.Builder()
        .associatedRequest(TestIdentifiableConstants.IDENTIFIED_REQUEST)
        .category(null)
        .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestRequestDetail() {
        this.identifiedRequestDetail = TestIdentifiableConstants.IDENTIFIED_REQUEST_DETAIL;
        this.unidentifiedRequestDetail = TestIdentifiableConstants.UNIDENTIFIED_REQUEST_DETAIL;
        this.differentIdentifiedRequestDetail = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_REQUEST_DETAIL;
        this.differentUnidentifiedRequestDetail = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_REQUEST_DETAIL;
        this.sameIdentifiedRequestDetail = TestIdentifiableConstants.SAME_IDENTIFIED_REQUEST_DETAIL;
        this.sameUnidentifiedRequestDetail = TestIdentifiableConstants.SAME_UNIDENTIFIED_REQUEST_DETAIL;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedRequestDetail.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedRequestDetail.getAssociatedRequest().get().equals(
                TestIdentifiableConstants.IDENTIFIED_REQUEST));
        assertTrue(identifiedRequestDetail.getDeviceCategory().equals(TestEntityConstants.DEV_CATEGORY));
        assertTrue(identifiedRequestDetail.getAnnotations().get().equals(TestConstants.A_STRING));
        assertTrue(identifiedRequestDetail.getRequestedQuantity().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedRequestDetail.getCompositeRequestDetail().equals(Optional.empty()));

        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierParameter);
        assertThrows(IllegalArgumentException.class, emptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyAnnotationsParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyAnnotationsParameter);
        assertThrows(NullPointerException.class, nullCategoryParameter);
        assertThrows(IllegalArgumentException.class, negativeQuantityParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedRequestDetail.equals(identifiedRequestDetail));
        assertTrue(identifiedRequestDetail.equals(sameIdentifiedRequestDetail));
        assertTrue(sameIdentifiedRequestDetail.equals(identifiedRequestDetail));

        assertFalse(identifiedRequestDetail.equals(unidentifiedRequestDetail));
        assertFalse(identifiedRequestDetail.equals(differentIdentifiedRequestDetail));
        assertFalse(identifiedRequestDetail.equals(differentUnidentifiedRequestDetail));
        assertFalse(identifiedRequestDetail.equals(sameUnidentifiedRequestDetail));

        assertFalse(unidentifiedRequestDetail.equals(unidentifiedRequestDetail));
        assertFalse(unidentifiedRequestDetail.equals(sameUnidentifiedRequestDetail));
        assertFalse(sameUnidentifiedRequestDetail.equals(unidentifiedRequestDetail));
    }
}
