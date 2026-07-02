package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.Color;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ColorImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link ColorImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestColor {

    private static final String COLOR = "FFFFFF";
    private static final String DIFFERENT_COLOR = "000000";
    private static final String SAME_COLOR = "FFFFFF";
    private static final String ILLEGAL_COLOR = "GGGGGG";
    private static final String ILLEGAL_LENGTH_COLOR = "FFFFFFFF";

    private final Color color;
    private final Color differentColor;
    private final Color sameColor;

    private final Executable nullParameter = () -> {
        new ColorImpl(null);
    };
    private final Executable illegalParameter = () -> {
        new ColorImpl(ILLEGAL_COLOR);
    };
    private final Executable illegalLengthParameter = () -> {
        new ColorImpl(ILLEGAL_LENGTH_COLOR);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestColor() {
        this.color = new ColorImpl(COLOR);
        this.differentColor = new ColorImpl(DIFFERENT_COLOR);
        this.sameColor = new ColorImpl(SAME_COLOR);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(color.getColor().equals(COLOR.toLowerCase(TestConstants.IT)));
        assertTrue(differentColor.getColor().equals(DIFFERENT_COLOR.toLowerCase(TestConstants.IT)));
        assertTrue(sameColor.getColor().equals(SAME_COLOR.toLowerCase(TestConstants.IT)));

        assertThrows(NullPointerException.class, nullParameter);
        assertThrows(NumberFormatException.class, illegalParameter);
        assertThrows(IllegalArgumentException.class, illegalLengthParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test 
    public void equalityTest() {
        assertTrue(color.equals(sameColor));
        assertTrue(sameColor.equals(color));
        assertFalse(color.equals(differentColor));
        assertFalse(color.equals(differentColor));
    }

}
