package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceWorkProgressImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link DeviceWorkProgressImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestDeviceWorkProgress {

    private final DeviceWorkProgress progress;
    private final DeviceWorkProgress differentProgress;
    private final DeviceWorkProgress sameProgress;

    private final Executable nullParameter = () -> {
        new DeviceWorkProgressImpl(null);
    };
    private final Executable emptyFirstParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleFirstEmptyParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiFirstEmptyParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.MULTI_SPACE_STRING);
    };
    private final Executable emptySecondParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.A_STRING, TestConstants.EMPTY_STRING);
    };
    private final Executable singleSecondEmptyParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.A_STRING, TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiSecondEmptyParameter = () -> {
        new DeviceWorkProgressImpl(TestConstants.A_STRING, TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestDeviceWorkProgress() {
        this.progress = new DeviceWorkProgressImpl(TestConstants.A_STRING, null);
        this.differentProgress = 
                new DeviceWorkProgressImpl(TestConstants.A_DIFFERENT_STRING, TestConstants.A_DIFFERENT_STRING);
        this.sameProgress = new DeviceWorkProgressImpl(TestConstants.THE_SAME_STRING, null);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(progress.getName().equals(TestConstants.A_STRING));
        assertTrue(progress.getDescription().equals(Optional.empty()));
        assertTrue(differentProgress.getDescription().get().equals(TestConstants.A_DIFFERENT_STRING));

        assertThrows(NullPointerException.class, nullParameter);
        assertThrows(IllegalArgumentException.class, emptyFirstParameter);
        assertThrows(IllegalArgumentException.class, singleFirstEmptyParameter);
        assertThrows(IllegalArgumentException.class, multiFirstEmptyParameter);
        assertThrows(IllegalArgumentException.class, emptySecondParameter);
        assertThrows(IllegalArgumentException.class, singleSecondEmptyParameter);
        assertThrows(IllegalArgumentException.class, multiSecondEmptyParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(progress.equals(sameProgress));
        assertTrue(sameProgress.equals(progress));
        assertFalse(progress.equals(differentProgress));
        assertFalse(sameProgress.equals(differentProgress));
    }

}
