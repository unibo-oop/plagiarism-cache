package it.unibo.oop.test.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.unibo.oop.crossline.launcher.LauncherControllerImpl;
import it.unibo.oop.crossline.launcher.LauncherModelImpl;
import it.unibo.oop.crossline.launcher.LauncherViewImpl;

/**
 * This is the test for Main.java.
 */
public class MainTest {

    /**
     * Main test.
     */
    @Test
    public void test() {
        final LauncherModelImpl model = new LauncherModelImpl();
        final LauncherViewImpl view = new LauncherViewImpl();
        assertNotNull("model is not null", model);
        assertNotNull("view is not null", view);
        final LauncherControllerImpl controller = new LauncherControllerImpl(view, model);
        assertNotNull("controller is not null", controller);
    }
}
