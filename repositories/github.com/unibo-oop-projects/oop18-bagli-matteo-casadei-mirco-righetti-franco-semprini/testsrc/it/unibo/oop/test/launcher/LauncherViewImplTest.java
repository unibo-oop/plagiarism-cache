package it.unibo.oop.test.launcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;

import it.unibo.oop.crossline.launcher.LauncherViewImpl;

/**
 * Test class for LauncherViewImpl.
 */
public class LauncherViewImplTest {
    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First view.
     */
    private LauncherViewImpl view1;
    /**
     * Second view.
     */
    private LauncherViewImpl view2;
    /**
     * Same as first view.
     */
    private LauncherViewImpl sameAsView1;
    /**
     * Unimplemented view.
     */
    private static final LauncherViewImpl UNIMPLEMENTED_VIEW = null;

    /**
     * SetUp method for instance view.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        view1 = new LauncherViewImpl();
        view2 = new LauncherViewImpl();
        sameAsView1 = view1;
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#LauncherViewImpl()}.
     */
    @Test
    public final void testLauncherViewImpl() {
        assertNotNull("view1 is not null", view1);
        assertNotNull("view2 is not null", view2);
        assertNotNull("sameAsView1 is not null", sameAsView1);
        assertNull("UNIMPLEMENTED_VIEW is not null", UNIMPLEMENTED_VIEW);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#getSelectedScreen()}.
     */
    @Test
    public final void testGetSelectedScreen() {
        assertEquals("view1's selected screen equals to -1", view1.getSelectedScreen(), -1);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#setSelectedScreen(int)}.
     */
    @Test
    public final void testSetSelectedScreen() {
        view1.setSelectedScreen(-1);
        assertEquals("view1's selected screen equals to -1", view1.getSelectedScreen(), -1);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#getResolution()}.
     */
    @Test
    public final void testGetResolution() {
        final Dimension dimension = new Dimension(ZERO, ZERO);
        assertNotNull("view1's resolution is not null", view1.getResolution());
        assertEquals("view1's resolution equals to dimension", view1.getResolution(), dimension);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#setResolution(java.awt.Dimension)}.
     */
    @Test
    public final void testSetResolution() {
        final Dimension resolution = new Dimension(ZERO, ZERO);
        view1.setResolution(resolution);
        assertEquals("view1's resolution equals to resolution", view1.getResolution(), resolution);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#isFullscreen()}.
     */
    @Test
    public final void testIsFullscreen() {
        assertFalse("view1 is not fullscreen", view1.isFullscreen());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#setFullscreen(boolean)}.
     */
    @Test
    public final void testSetFullscreen() {
        view1.setFullscreen(true);
        assertTrue("view1 is fullscreen", view1.isFullscreen());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#getVolume()}.
     */
    @Test
    public final void testGetVolume() {
        assertNotNull("view1's volume is not null", view1.getVolume());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#setVolume(int)}.
     */
    @Test
    public final void testSetVolume() {
        final int volume = 50;
        view1.setVolume(volume);
        assertEquals("view1's volume equals to volume", view1.getVolume(), volume);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.launcher.LauncherViewImpl#setVisibility(boolean)}.
     */
    @Test
    public final void testSetVisibility() {
        view1.setVisibility(true);
        assertTrue("view1 is visible", view1.isVisible());
    }

}
