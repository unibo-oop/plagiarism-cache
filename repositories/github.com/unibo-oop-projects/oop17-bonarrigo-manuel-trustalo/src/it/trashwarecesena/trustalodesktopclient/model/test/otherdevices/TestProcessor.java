package it.trashwarecesena.trustalodesktopclient.model.test.otherdevices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.otherdevices.Processor;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.ProcessorImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link ProcessorImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestProcessor {

    private final Processor identifiedProcessor;
    private final Processor unidentifiedProcessor;
    private final Processor differentIdentifiedProcessor;
    private final Processor differentUnidentifiedProcessor;
    private final Processor sameIdentifiedProcessor;
    private final Processor sameUnidentifiedProcessor;

    private final Executable nullDeviceParameter = () -> {
        new ProcessorImpl.Builder()
            .device(null)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nullFrequencyParameter = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(null)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nullFrequencyUnitParameter = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(null)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nullInstructionSetParameter = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(null)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nonPositiveFrequencyParameter = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.FLO_ZERO)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nonPositiveL3CacheAmountParameter = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.INT_ZERO)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable validCacheAmountNullCacheAmountUnit = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheUnit(TestEntityConstants.INF_UNIT)
            .build();
    };

    private final Executable nullCacheAmountValidCacheAmountUnit = () -> {
        new ProcessorImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .frequency(TestConstants.A_POSITIVE_FLOAT)
            .frequencyUnit(TestEntityConstants.FREQ_UNIT)
            .instructionSet(TestEntityConstants.ISA)
            .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
            .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestProcessor() {
        this.identifiedProcessor = 
                new ProcessorImpl.Builder()
                    .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
                    .frequency(TestConstants.A_POSITIVE_FLOAT)
                    .frequencyUnit(TestEntityConstants.FREQ_UNIT)
                    .instructionSet(TestEntityConstants.ISA)
                    .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
                    .l3CacheUnit(TestEntityConstants.INF_UNIT)
                    .build();
        this.unidentifiedProcessor = 
                new ProcessorImpl.Builder()
                    .device(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)
                    .frequency(TestConstants.A_POSITIVE_FLOAT)
                    .frequencyUnit(TestEntityConstants.FREQ_UNIT)
                    .instructionSet(TestEntityConstants.ISA)
                    .l3CacheAmount(TestConstants.A_POSITIVE_INTEGER)
                    .l3CacheUnit(TestEntityConstants.INF_UNIT)
                    .build();
        this.differentIdentifiedProcessor =
                new ProcessorImpl.Builder()
                    .device(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE)
                    .frequency(TestConstants.A_DIFFERENT_POSITIVE_FLOAT)
                    .frequencyUnit(TestEntityConstants.DIFFERENT_FREQ_UNIT)
                    .instructionSet(TestEntityConstants.DIFFERENT_ISA)
                    .l3CacheAmount(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                    .l3CacheUnit(TestEntityConstants.INF_DIFFERENT_UNIT)
                    .build();
        this.differentUnidentifiedProcessor = 
                new ProcessorImpl.Builder()
                    .device(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)
                    .frequency(TestConstants.A_DIFFERENT_POSITIVE_FLOAT)
                    .frequencyUnit(TestEntityConstants.DIFFERENT_FREQ_UNIT)
                    .instructionSet(TestEntityConstants.DIFFERENT_ISA)
                    .l3CacheAmount(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                    .l3CacheUnit(TestEntityConstants.INF_DIFFERENT_UNIT)
                    .build();
        this.sameIdentifiedProcessor =
                new ProcessorImpl.Builder()
                    .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
                    .frequency(TestConstants.THE_SAME_POSITIVE_FLOAT)
                    .frequencyUnit(TestEntityConstants.SAME_FREQ_UNIT)
                    .instructionSet(TestEntityConstants.SAME_ISA)
                    .l3CacheAmount(TestConstants.THE_SAME_POSITIVE_INTEGER)
                    .l3CacheUnit(TestEntityConstants.INF_SAME_UNIT)
                    .build();
        this.sameUnidentifiedProcessor =
                new ProcessorImpl.Builder()
                .device(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)
                .frequency(TestConstants.THE_SAME_POSITIVE_FLOAT)
                .frequencyUnit(TestEntityConstants.SAME_FREQ_UNIT)
                .instructionSet(TestEntityConstants.SAME_ISA)
                .l3CacheAmount(TestConstants.THE_SAME_POSITIVE_INTEGER)
                .l3CacheUnit(TestEntityConstants.INF_SAME_UNIT)
                .build();
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedProcessor.getGenericDevice().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedProcessor.getFrequency().equals(TestConstants.A_POSITIVE_FLOAT));
        assertTrue(identifiedProcessor.getFrequencyUnit().equals(TestEntityConstants.FREQ_UNIT));
        assertTrue(identifiedProcessor.getInstructionSet().equals(TestEntityConstants.ISA));
        assertTrue(identifiedProcessor.getL3CacheAmount().get().equals(TestConstants.A_POSITIVE_INTEGER));
        assertTrue(identifiedProcessor.getL3CacheInformationUnit().get().equals(TestEntityConstants.INF_UNIT));

        assertThrows(NullPointerException.class, nullDeviceParameter);
        assertThrows(NullPointerException.class, nullFrequencyParameter);
        assertThrows(NullPointerException.class, nullFrequencyUnitParameter);
        assertThrows(NullPointerException.class, nullInstructionSetParameter);

        assertThrows(IllegalArgumentException.class, nonPositiveFrequencyParameter);
        assertThrows(IllegalArgumentException.class, nonPositiveL3CacheAmountParameter);

        assertThrows(IllegalArgumentException.class, validCacheAmountNullCacheAmountUnit);
        assertThrows(IllegalArgumentException.class, nullCacheAmountValidCacheAmountUnit);

    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedProcessor.equals(identifiedProcessor));
        assertTrue(identifiedProcessor.equals(sameIdentifiedProcessor));
        assertTrue(sameIdentifiedProcessor.equals(identifiedProcessor));

        assertFalse(identifiedProcessor.equals(unidentifiedProcessor));
        assertFalse(identifiedProcessor.equals(differentIdentifiedProcessor));
        assertFalse(identifiedProcessor.equals(differentUnidentifiedProcessor));
        assertFalse(identifiedProcessor.equals(sameUnidentifiedProcessor));

        assertFalse(unidentifiedProcessor.equals(sameUnidentifiedProcessor));
        assertFalse(sameUnidentifiedProcessor.equals(unidentifiedProcessor));
    }
}
