package test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.ImageLoaderProxy;
import util.ImageLoaderProxy.ImageID;

/**
 * Basic test of the class ImageLoaderProxy.
 */
public class TestImageProxy {

    /**
     * Test some standard behaviors.
     */
    @Test
    public void testImageLoading() {
        ImageLoaderProxy img = ImageLoaderProxy.get();

        assertTrue(img.getLoadedImages().isEmpty());

        img.getImage(ImageID.BOSS);
        assertEquals(1, img.getLoadedImages().values().size());

        img.getImage(ImageID.PLAYER);
        assertEquals(2, img.getLoadedImages().values().size());

        img.getImage(ImageID.PLAYER);
        assertEquals(2, img.getLoadedImages().values().size());

        assertTrue(img.getLoadedImages().containsKey(ImageID.PLAYER));
        assertTrue(img.getLoadedImages().containsKey(ImageID.BOSS));

        img.getImage(ImageID.FLOOR);
        assertEquals(3, img.getLoadedImages().values().size());

        assertEquals(img.getImage(ImageID.BULLET), img.getImage(ImageID.BULLET));
    }

}
