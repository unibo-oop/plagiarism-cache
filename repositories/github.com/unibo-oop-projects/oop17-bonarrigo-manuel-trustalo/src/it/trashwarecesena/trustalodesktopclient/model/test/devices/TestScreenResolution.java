package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenResolution;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenResolutionImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link ScreenResolutionImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */

public class TestScreenResolution {

    private final ScreenResolution identifiedScreenResolution;
    private final ScreenResolution unidentifiedScreenResolution;
    private final ScreenResolution differentIdentifiedScreenResolution;
    private final ScreenResolution differentUnidentifiedScreenResolution;
    private final ScreenResolution sameIdentifiedScreenResolution;
    private final ScreenResolution sameUnidentifiedScreenResolution; 

    private final Executable nullWidthParameter = () -> {
        new ScreenResolutionImpl.Builder()
        .width(null)
        .height(TestConstants.A_POSITIVE_INTEGER)
        .aspectRatio(TestEntityConstants.RATIO)
        .build();
    };

    private final Executable nullHeightParameter = () -> {
        new ScreenResolutionImpl.Builder()
        .width(TestConstants.A_POSITIVE_INTEGER)
        .height(null)
        .aspectRatio(TestEntityConstants.RATIO)
        .build();
    };

    private final Executable nullRatioParameter = () -> {
        new ScreenResolutionImpl.Builder()
        .width(TestConstants.A_POSITIVE_INTEGER)
        .height(TestConstants.A_POSITIVE_INTEGER)
        .aspectRatio(null)
        .build();
    };

    private final Executable nonPositiveIdentifierField = () -> {
        new ScreenResolutionImpl.Builder()
        .identifier(TestConstants.INT_ZERO)
        .width(TestConstants.A_POSITIVE_INTEGER)
        .height(TestConstants.A_POSITIVE_INTEGER)
        .aspectRatio(TestEntityConstants.RATIO)
        .build();
    };

    private final Executable negativeWidthField = () -> {
        new ScreenResolutionImpl.Builder()
        .identifier(TestConstants.A_POSITIVE_INTEGER)
        .width(TestConstants.A_NEGATIVE_INTEGER)
        .height(TestConstants.A_POSITIVE_INTEGER)
        .aspectRatio(TestEntityConstants.RATIO)
        .build();
    };

    private final Executable negativeHeightField = () -> {
        new ScreenResolutionImpl.Builder()
        .identifier(TestConstants.A_POSITIVE_INTEGER)
        .width(TestConstants.A_POSITIVE_INTEGER)
        .height(TestConstants.A_NEGATIVE_INTEGER)
        .aspectRatio(TestEntityConstants.RATIO)
        .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestScreenResolution() {
        this.identifiedScreenResolution = TestIdentifiableConstants.IDENTIFIED_SCREEN_RESOLUTION;
        this.unidentifiedScreenResolution = TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION;
        this.differentIdentifiedScreenResolution = TestIdentifiableConstants.DIFFERENT_IDENTIFIED_SCREEN_RESOLUTION;
        this.differentUnidentifiedScreenResolution = TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION;
        this.sameIdentifiedScreenResolution = TestIdentifiableConstants.SAME_IDENTIFIED_SCREEN_RESOLUTION;
        this.sameUnidentifiedScreenResolution = TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION;
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedScreenResolution.getNumericIdentifier().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedScreenResolution.getWidth().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedScreenResolution.getHeight().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedScreenResolution.getAspectRatio().equals(TestEntityConstants.RATIO));

        assertThrows(NullPointerException.class, nullWidthParameter);
        assertThrows(NullPointerException.class, nullHeightParameter);
        assertThrows(NullPointerException.class, nullRatioParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveIdentifierField);
        assertThrows(IllegalArgumentException.class, negativeWidthField);
        assertThrows(IllegalArgumentException.class, negativeHeightField);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedScreenResolution.equals(identifiedScreenResolution));
        assertTrue(identifiedScreenResolution.equals(sameIdentifiedScreenResolution));
        assertTrue(sameIdentifiedScreenResolution.equals(identifiedScreenResolution));

        assertFalse(identifiedScreenResolution.equals(unidentifiedScreenResolution));
        assertFalse(identifiedScreenResolution.equals(differentIdentifiedScreenResolution));
        assertFalse(identifiedScreenResolution.equals(differentUnidentifiedScreenResolution));
        assertFalse(identifiedScreenResolution.equals(sameUnidentifiedScreenResolution));

        assertFalse(unidentifiedScreenResolution.equals(unidentifiedScreenResolution));
        assertFalse(unidentifiedScreenResolution.equals(sameUnidentifiedScreenResolution));
        assertFalse(sameUnidentifiedScreenResolution.equals(unidentifiedScreenResolution));
    }
}
