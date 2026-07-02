package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.devices.Screen;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestEntityConstants;
import it.trashwarecesena.trustalodesktopclient.model.test.TestIdentifiableConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link ScreenImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestScreen {

    private final Screen identifiedScreen;
    private final Screen unidentifiedScreen;
    private final Screen differentIdentifiedScreen;
    private final Screen differentUnidentifiedScreen;
    private final Screen sameIdentifiedScreen;
    private final Screen sameUnidentifiedScreen;

    private final Executable nullDeviceParameter = () -> {
        new ScreenImpl.Builder()
            .device(null)
            .category(TestEntityConstants.SCREEN_CATEGORY)
            .resolution(TestIdentifiableConstants.IDENTIFIED_SCREEN_RESOLUTION)
            .build();
    };

    private final Executable nullCategoryParameter = () -> {
        new ScreenImpl.Builder()
            .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
            .category(null)
            .resolution(TestIdentifiableConstants.IDENTIFIED_SCREEN_RESOLUTION)
            .build();
    };

    private final Executable nullResolutionParameter = () -> {
        new ScreenImpl.Builder()
        .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
        .category(TestEntityConstants.SCREEN_CATEGORY)
        .resolution(null)
        .build();
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestScreen() {
        this.identifiedScreen = 
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.IDENTIFIED_DEVICE)
                    .category(TestEntityConstants.SCREEN_CATEGORY)
                    .color(TestEntityConstants.COLOR)
                    .resolution(TestIdentifiableConstants.IDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
        this.unidentifiedScreen = 
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.UNIDENTIFIED_DEVICE)
                    .category(TestEntityConstants.SCREEN_CATEGORY)
                    .color(TestEntityConstants.COLOR)
                    .resolution(TestIdentifiableConstants.UNIDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
        this.differentIdentifiedScreen =
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_DEVICE)
                    .category(TestEntityConstants.DIFFERENT_SCREEN_CATEGORY)
                    .color(TestEntityConstants.DIFFERENT_COLOR)
                    .resolution(TestIdentifiableConstants.DIFFERENT_IDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
        this.differentUnidentifiedScreen =
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_DEVICE)
                    .category(TestEntityConstants.DIFFERENT_SCREEN_CATEGORY)
                    .color(TestEntityConstants.DIFFERENT_COLOR)
                    .resolution(TestIdentifiableConstants.DIFFERENT_UNIDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
        this.sameIdentifiedScreen = 
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.SAME_IDENTIFIED_DEVICE)
                    .category(TestEntityConstants.SAME_SCREEN_CATEGORY)
                    .color(TestEntityConstants.SAME_COLOR)
                    .resolution(TestIdentifiableConstants.SAME_IDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
        this.sameUnidentifiedScreen = 
                new ScreenImpl.Builder()
                    .device(TestIdentifiableConstants.SAME_UNIDENTIFIED_DEVICE)
                    .category(TestEntityConstants.SAME_SCREEN_CATEGORY)
                    .color(TestEntityConstants.SAME_COLOR)
                    .resolution(TestIdentifiableConstants.SAME_UNIDENTIFIED_SCREEN_RESOLUTION)
                    .hasAudioSpeakers(true)
                    .hasDviSocket(true)
                    .hasFrame(true)
                    .hasVgaSocket(true)
                    .build();
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal
     * initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(identifiedScreen.getGenericDevice().equals(TestIdentifiableConstants.IDENTIFIED_DEVICE));
        assertTrue(identifiedScreen.getCategory().equals(TestEntityConstants.SCREEN_CATEGORY));
        assertTrue(identifiedScreen.getMaximumResolution()
                .equals(TestIdentifiableConstants.IDENTIFIED_SCREEN_RESOLUTION));
        assertTrue(identifiedScreen.getColor().get().equals(TestEntityConstants.COLOR));
        assertTrue(identifiedScreen.isWithAudioSpeakers());
        assertTrue(identifiedScreen.isWithDviSocket());
        assertTrue(identifiedScreen.isWithFrame());
        assertTrue(identifiedScreen.isWithVgaSocket());

        assertThrows(NullPointerException.class, nullDeviceParameter);
        assertThrows(NullPointerException.class, nullResolutionParameter);
        assertThrows(NullPointerException.class, nullCategoryParameter);

    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(identifiedScreen.equals(identifiedScreen));
        assertTrue(identifiedScreen.equals(sameIdentifiedScreen));
        assertTrue(sameIdentifiedScreen.equals(identifiedScreen));

        assertFalse(identifiedScreen.equals(unidentifiedScreen));
        assertFalse(identifiedScreen.equals(differentIdentifiedScreen));
        assertFalse(identifiedScreen.equals(differentUnidentifiedScreen));
        assertFalse(identifiedScreen.equals(sameUnidentifiedScreen));

        assertFalse(unidentifiedScreen.equals(sameUnidentifiedScreen));
        assertFalse(sameUnidentifiedScreen.equals(unidentifiedScreen));
    }

}
