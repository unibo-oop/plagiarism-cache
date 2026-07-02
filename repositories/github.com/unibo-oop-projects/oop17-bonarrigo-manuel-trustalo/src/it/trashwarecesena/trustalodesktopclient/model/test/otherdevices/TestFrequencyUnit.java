package it.trashwarecesena.trustalodesktopclient.model.test.otherdevices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.FrequencyUnit;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.FrequencyUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link FrequencyUnitImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestFrequencyUnit {

    private final FrequencyUnit frequencyUnit;
    private final FrequencyUnit differentFrequencyUnit;
    private final FrequencyUnit sameFrequencyUnit;

    private final Executable nullParameter = () -> {
        new FrequencyUnitImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new FrequencyUnitImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new FrequencyUnitImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new FrequencyUnitImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestFrequencyUnit() {
        this.frequencyUnit = new FrequencyUnitImpl(TestConstants.A_STRING);
        this.differentFrequencyUnit = new FrequencyUnitImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameFrequencyUnit = new FrequencyUnitImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(frequencyUnit.getName().equals(TestConstants.A_STRING));

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
        assertTrue(frequencyUnit.equals(sameFrequencyUnit));
        assertTrue(sameFrequencyUnit.equals(frequencyUnit));
        assertFalse(frequencyUnit.equals(differentFrequencyUnit));
        assertFalse(sameFrequencyUnit.equals(differentFrequencyUnit));
    }
}
