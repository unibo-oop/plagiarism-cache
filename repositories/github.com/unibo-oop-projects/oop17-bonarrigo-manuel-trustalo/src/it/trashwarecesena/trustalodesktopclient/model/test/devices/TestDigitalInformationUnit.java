package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.DigitalInformationUnit;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DigitalInformationUnitImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link DigitalInformationUnitImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestDigitalInformationUnit {

    private final DigitalInformationUnit unit;
    private final DigitalInformationUnit differentunit;
    private final DigitalInformationUnit sameUnit;

    private final Executable nullParameter = () -> {
        new DigitalInformationUnitImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new DigitalInformationUnitImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new DigitalInformationUnitImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new DigitalInformationUnitImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestDigitalInformationUnit() {
        this.unit = new DigitalInformationUnitImpl(TestConstants.A_STRING);
        this.differentunit = new DigitalInformationUnitImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameUnit = new DigitalInformationUnitImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(unit.getName().equals(TestConstants.A_STRING));

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
        assertTrue(unit.equals(sameUnit));
        assertTrue(sameUnit.equals(unit));
        assertFalse(unit.equals(differentunit));
        assertFalse(sameUnit.equals(differentunit));
    }
}
