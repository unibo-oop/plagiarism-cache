package test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.File;

import model.pkglevels.ImageLoaderImpl;

import org.junit.Test;

/***
 * Test class that tests the loader behavior.
 * 
 * 
 *
 */
public class LoaderTest {
    private final File f = new File(ImageLoaderImpl.getNewPath());
    private final boolean b = f.mkdir();

    /**
     * Test method. tests the if the images are loaded correctly, the created
     * folder exists and it's hidden, tests file's paths.
     * 
     */
    @Test
    public void testImageLoad() {

        assertTrue(f.exists());
        ImageLoaderImpl.getLoaderInstance().loadImages(67, 64);
        assertEquals(model.pkglevels.ImageLoaderImpl.getLoaderInstance().getAllTubes().size(), 4);
        assertTrue(f.isDirectory());
        assertTrue(f.isHidden());
        assertEquals(f.getAbsolutePath(),
                System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder");
        assertEquals(ImageLoaderImpl.getLoaderInstance().getTube("CL"),
                ImageLoaderImpl.getLoaderInstance().getAllTubes().get(0));
        assertEquals(ImageLoaderImpl.getLoaderInstance().getTube("CU"),
                ImageLoaderImpl.getLoaderInstance().getAllTubes().get(1));
        assertEquals(ImageLoaderImpl.getLoaderInstance().getTube("CR"),
                ImageLoaderImpl.getLoaderInstance().getAllTubes().get(2));
        assertEquals(ImageLoaderImpl.getLoaderInstance().getTube("CD"),
                ImageLoaderImpl.getLoaderInstance().getAllTubes().get(3));
        assertEquals(ImageLoaderImpl.getLoaderInstance().getCustomFIle().getAbsolutePath(),
                System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder"
                        + System.getProperty("file.separator") + "customSolutionsFile.txt");
        assertEquals(ImageLoaderImpl.getLoaderInstance().getFixedFile().getAbsolutePath(),
                System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder"
                        + System.getProperty("file.separator") + "plumberSolutionsFile.txt");
        assertEquals(ImageLoaderImpl.getLoaderInstance().getLoginFile().getAbsolutePath(),
                System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder"
                        + System.getProperty("file.separator") + "login.txt");
        assertEquals(ImageLoaderImpl.getLoaderInstance().getScoreFile().getAbsolutePath(),
                System.getProperty("user.home") + System.getProperty("file.separator") + ".PlumberSupportFolder"
                        + System.getProperty("file.separator") + "levelsScoreFile.txt");

    }

}
