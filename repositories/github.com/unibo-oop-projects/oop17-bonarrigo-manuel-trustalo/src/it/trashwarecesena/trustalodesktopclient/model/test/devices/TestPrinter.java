package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.Printer;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.PrinterImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link PrinterImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestPrinter {

    private final Printer identifiedPrinter;
    private final Printer unidentifiedPrinter;
    private final Printer differentIdentifiedPrinter;
    private final Printer differentUnidentifiedPrinter;
    private final Printer sameIdentifiedPrinter;
    private final Printer sameUnidentifiedPrinter;

    private final Executable nullFirstParameter = () -> {
        new PrinterImpl(null, TestEntityConstants.CATEGORY, TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nullSecondParameter = () -> {
        new PrinterImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, null, TestConstants.A_POSITIVE_INTEGER);
    };

    private final Executable nonPositiveThirdParameter = () -> {
        new PrinterImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE, TestEntityConstants.CATEGORY, 
                TestConstants.INT_ZERO);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestPrinter() {
        this.identifiedPrinter = new PrinterImpl(TestIdentifiableConstants.IDENTIFIED_DEVICE,
                TestEntityConstants.CATEGORY, TestConstants.A_POSITIVE_INTEGER);
        this.unidentifiedPrinter = new PrinterImpl(TestIdentifiableConstants.UNIDENTIFIED_DEVICE,
                TestEntityConstants.CATEGORY, TestConstants.A_POSITIVE_INTEGER);
        this.differentIdentifiedPrinter = new PrinterImpl(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE,
                TestEntityConstants.DIFFERENT_CATEGORY, TestConstants.A_DIFFERENT_POSITIVE_INTEGER);
        this.differentUnidentifiedPrinter = new PrinterImpl(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE,
                TestEntityConstants.DIFFERENT_CATEGORY, TestConstants.A_DIFFERENT_POSITIVE_INTEGER);
        this.sameIdentifiedPrinter = new PrinterImpl(TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE,
                TestEntityConstants.SAME_CATEGORY, TestConstants.THE_SAME_POSITIVE_INTEGER);
        this.sameUnidentifiedPrinter = new PrinterImpl(TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE,
                TestEntityConstants.SAME_CATEGORY, TestConstants.THE_SAME_POSITIVE_INTEGER);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedPrinter.getGenericDevice().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedPrinter.getPrinterCategory().equals(TestEntityConstants.CATEGORY));
        assertTrue(identifiedPrinter.getResolution().get().equals(TestConstants.A_POSITIVE_INTEGER));

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveThirdParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedPrinter.equals(identifiedPrinter));
        assertTrue(identifiedPrinter.equals(sameIdentifiedPrinter));
        assertTrue(sameIdentifiedPrinter.equals(identifiedPrinter));

        assertFalse(identifiedPrinter.equals(unidentifiedPrinter));
        assertFalse(identifiedPrinter.equals(differentIdentifiedPrinter));
        assertFalse(identifiedPrinter.equals(differentUnidentifiedPrinter));
        assertFalse(identifiedPrinter.equals(sameUnidentifiedPrinter));

        assertFalse(unidentifiedPrinter.equals(sameUnidentifiedPrinter));
        assertFalse(sameUnidentifiedPrinter.equals(unidentifiedPrinter));
    }
}
