package it.unibo.oop.test.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop.crossline.io.Settings;

/**
 * Test class for Settings.
 */
public class SettingsTest {

    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * Default file path.
     */
    public static final String DEFAULT_PATH = new File(".").getAbsolutePath().toString() + "testSettings.ser";
    /**
     * First settings object.
     */
    private Settings setting1;
    /**
     * Same as first settings object.
     */
    private Settings sameAsSetting1;
    /**
     * Unimplemented settings object.
     */
    private static final Settings UNIMPLEMENTED_SETTING = null;

    /**
     * Set up for instance settings.
     * 
     * @throws java.lang.Exception eneric exception
     */
    @Before
    public void setUp() throws Exception {
        setting1 = new Settings();
        sameAsSetting1 = new Settings();
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#hashCode()}.
     */
    @Test
    public final void testHashCode() {
        assertNotEquals(setting1.hashCode(), ZERO);
        assertNotEquals(sameAsSetting1.hashCode(), ZERO);
        assertEquals("setting1 equals to sameAsSetting1", setting1.hashCode(), sameAsSetting1.hashCode());

    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#Settings()}.
     */
    @Test
    public final void testSettings() {
        assertNotNull("setting1 is not null", setting1);
        assertNotNull("sameAsSetting1 is not null", sameAsSetting1);
        assertNull("UNIMPLEMENTED_SETTING is null", UNIMPLEMENTED_SETTING);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#getSettings(java.lang.String)}.
     * 
     * @throws IOException when file is missing
     */
    @Test
    public final void testGetSettings() throws IOException {
        assertNotNull("setting1 has settings", setting1.getSettings(DEFAULT_PATH));
        assertEquals("setting1's settings equals to sameAsSettings1's settings", setting1.getSettings(DEFAULT_PATH),
                this.sameAsSetting1.getSettings(DEFAULT_PATH));
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#isFullscreen()}.
     */
    @Test
    public final void testIsFullscreen() {
        assertFalse("setting1 is not fullscreen", setting1.isFullscreen());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#setFullscreen(boolean)}.
     */
    @Test
    public final void testSetFullscreen() {
        setting1.setFullscreen(true);
        assertTrue("setting1 is not fullscreen", setting1.isFullscreen());
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#getScreen()}.
     */
    @Test
    public final void testGetScreen() {
        assertEquals("setting1's screen equals to ZERO", setting1.getScreen(), ZERO);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#setScreen(int)}.
     */
    @Test
    public final void testSetScreen() {
        final int screen = 1;
        setting1.setScreen(screen);
        assertEquals("setting1's screen equals to screen", setting1.getScreen(), screen);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#getResolution()}.
     */
    @Test
    public final void testGetResolution() {
        assertNull("setting1 has no resolution", setting1.getResolution());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#setResolution(java.awt.Dimension)}.
     */
    @Test
    public final void testSetResolution() {
        final int width = 900; // px
        final int height = 600; // px
        final Dimension resolution = new Dimension(width, height);
        setting1.setResolution(resolution);
        assertEquals("setting1's resolution equals to resolution", setting1.getResolution(), resolution);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#getVolume()}.
     */
    @Test
    public final void testGetVolume() {
        assertEquals("setting1's volume equals ZERO", setting1.getVolume(), ZERO);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#setVolume(int)}.
     */
    @Test
    public final void testSetVolume() {
        final int volume = 50;
        setting1.setVolume(volume);
        assertEquals("setting1's volume equals volume", setting1.getVolume(), volume);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#load(java.lang.String)}.
     * 
     * @throws IOException when file is missing
     */
    @Test
    public final void testLoad() throws IOException {
        final Settings newSetting1 = Settings.load(DEFAULT_PATH);
        final Settings sameAsNewSetting1 = Settings.load(DEFAULT_PATH);
        assertNotNull("newSetting1 is not null", newSetting1);
        assertNotNull("sameAsNewSetting1 is not null", sameAsNewSetting1);
        assertEquals("newSetting1 equals to setting1", newSetting1, setting1);
        assertEquals("newSetting1 equals to sameAsNewSetting1", newSetting1, sameAsNewSetting1);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#save(java.lang.String)}.
     * 
     * @throws IOException when file is missing
     */
    @Test
    public final void testSave() throws IOException {
        setting1.save(DEFAULT_PATH);
        assertTrue("setting1's save has done well", new File(DEFAULT_PATH).exists());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Settings#equals(java.lang.Object)}.
     */
    @Test
    public final void testEqualsObject() {
        assertEquals("setting1 equals to sameAsSetting1", setting1, sameAsSetting1);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Settings#toString()}.
     */
    @Test
    public final void testToString() {
        assertNotNull("setting1's string is not null", setting1.toString());
    }

}
