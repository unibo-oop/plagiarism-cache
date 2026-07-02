package aoc.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import aoc.utilities.Images;

public class ViewTest {
    
    @Test
    public void Images() {
        assertNotNull(Images.MOTHER.getImage());
        Images.ATHLETIC_CHILD.getImage();
        Images.DUMB_CHILD.getImage();
        Images.FAT_CHILD.getImage();
        Images.NERD_CHILD.getImage();
        Images.RICH_CHILD.getImage();
        Images.SLIPPER.getImage();
    }
}
