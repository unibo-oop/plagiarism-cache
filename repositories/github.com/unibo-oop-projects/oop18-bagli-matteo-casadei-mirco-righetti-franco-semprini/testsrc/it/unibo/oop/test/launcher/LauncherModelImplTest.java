package it.unibo.oop.test.launcher;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.GraphicsEnvironment;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop.crossline.launcher.LauncherModelImpl;

/**
 * Test class for LauncherModelImpl.
 */
public class LauncherModelImplTest {

    /**
     * Default file path.
     */
    public static final String DEFAULT_PATH = new File(".").getAbsolutePath().toString() + "settings.ser";
    /**
     * First model.
     */
    private LauncherModelImpl model1;

    /**
     * SetUp method for instance models.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        model1 = new LauncherModelImpl();
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherModelImpl#getSettings()}.
     */
    @Test
    public final void testGetSettings() {
        assertNotNull("model1's settings are not null", model1.getSettings());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherModelImpl#saveSettings()}.
     */
    @Test
    public final void testSaveSettings() {
        model1.saveSettings();
        assertTrue("model1's settings have been writed well", new File(DEFAULT_PATH).exists());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherModelImpl#getAvailableScreens()}.
     */
    @Test
    public final void testGetAvailableScreens() {
        assertNotNull("model1's has available screens", model1.getAvailableScreens());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherModelImpl#getDefaultScreen()}.
     */
    @Test
    public final void testGetDefaultScreen() {
        assertNotNull("model1's has default screen", model1.getDefaultScreen());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherModelImpl#getScreenResolution(java.awt.GraphicsDevice)}.
     */
    @Test
    public final void testGetScreenResolution() {
        assertNotNull("model1's first screen is not null",
                model1.getScreenResolution(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()));
    }

}
