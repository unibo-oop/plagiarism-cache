package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.AspectRatio;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.AspectRatioImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link AspectRatioImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestAspectRatio {

    private final AspectRatio aspectRatio;
    private final AspectRatio differentAspectRatio;
    private final AspectRatio sameAspectRatio;

    private final Executable nullFirstParameter = () -> {
        new AspectRatioImpl(null, TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nullSecondParameter = () -> {
        new AspectRatioImpl(TestConstants.A_POSITIVE_INTEGER, null);
    };

    private final Executable zeroedFirstParameter = () -> {
        new AspectRatioImpl(TestConstants.INT_ZERO, TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable zeroedSecondParameter = () -> {
        new AspectRatioImpl(TestConstants.A_POSITIVE_INTEGER, TestConstants.INT_ZERO);
    };

    private final Executable negativeFirstParameter = () -> {
        new AspectRatioImpl(TestConstants.A_NEGATIVE_INTEGER, TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable negativeSecondParameter = () -> {
        new AspectRatioImpl(TestConstants.A_POSITIVE_INTEGER, TestConstants.A_NEGATIVE_INTEGER);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestAspectRatio() {
        this.aspectRatio = new AspectRatioImpl(TestConstants.A_POSITIVE_INTEGER, TestConstants.A_POSITIVE_INTEGER);
        this.differentAspectRatio = 
            new AspectRatioImpl(TestConstants.A_DIFFERENT_POSITIVE_INTEGER, TestConstants.A_DIFFERENT_POSITIVE_INTEGER);
        this.sameAspectRatio = 
            new AspectRatioImpl(TestConstants.THE_SAME_POSITIVE_INTEGER, TestConstants.THE_SAME_POSITIVE_INTEGER);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {

        assertTrue(aspectRatio.getScreenRatio().equals("1:1"));
        assertTrue(differentAspectRatio.getScreenRatio().equals("2:2"));
        assertTrue(sameAspectRatio.getScreenRatio().equals("1:1"));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(IllegalArgumentException.class, zeroedFirstParameter);
        assertThrows(IllegalArgumentException.class, zeroedSecondParameter);
        assertThrows(IllegalArgumentException.class, negativeFirstParameter);
        assertThrows(IllegalArgumentException.class, negativeSecondParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(aspectRatio.equals(sameAspectRatio));
        assertTrue(sameAspectRatio.equals(aspectRatio));
        assertFalse(aspectRatio.equals(differentAspectRatio));
        assertFalse(sameAspectRatio.equals(differentAspectRatio));
    }
}
