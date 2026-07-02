package it.unibo.oop.test.launcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop.crossline.io.Settings;
import it.unibo.oop.crossline.launcher.LauncherModelImpl;
import it.unibo.oop.crossline.launcher.LauncherViewImpl;
import it.unibo.oop.crossline.launcher.LauncherControllerImpl;

/**
 * Test class for LauncherControllerImpl.
 */
public class LauncherControllerImplTest {

    /**
     * Default file path.
     */
    public static final String DEFAULT_PATH = new File(".").getAbsolutePath().toString() + "testSettings.ser";
    /**
     * The controller.
     */
    private LauncherControllerImpl controller;
    /**
     * The view.
     */
    private LauncherViewImpl view;
    /**
     * The settings.
     */
    private Settings settings;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        view = new LauncherViewImpl();
        final LauncherModelImpl model = new LauncherModelImpl();
        controller = new LauncherControllerImpl(view, model);
        settings = new Settings();
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherControllerImpl#LauncherControllerImpl(it.unibo.oop.crossline.launcher.LauncherView, it.unibo.oop.crossline.launcher.LauncherModel)}.
     */
    @Test
    public final void testLauncherControllerImpl() {
        assertNotNull("controller is not null", controller);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherControllerImpl#loadSettings(it.unibo.oop.crossline.io.Settings)}.
     */
    @Test
    public final void testLoadSettings() {
        settings = new LauncherModelImpl().getSettings();
        controller.loadSettings(settings);
        assertEquals("controller's settings fullscreen boolean equals to settings", settings.isFullscreen(),
                view.isFullscreen());
        assertEquals("controller's settings resolution equals to settings", settings.getResolution(),
                view.getResolution());
    }
}
