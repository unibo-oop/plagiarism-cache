package it.unibo.oop.mge.color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class ColorGeneratorTest {
    @Test
    void staticColorGeneratorTest() {
        final double minlimit = -10.0;
        final double maxlimit = 10.0;
        final double incr = 0.1;
        ColorGenerator cg = new ColorGeneratorImpl(Color.RED);
        for (double i = minlimit; i < maxlimit; i += incr) {
            assertEquals(Color.RED, cg.getColorFromDouble(i));
        }
        cg = new ColorGeneratorImpl(Color.GREEN);
        for (double i = minlimit; i < maxlimit; i += incr) {
            assertEquals(Color.GREEN, cg.getColorFromDouble(i));
        }
        cg = new ColorGeneratorImpl(Color.BLUE);
        for (double i = minlimit; i < maxlimit; i += incr) {
            assertEquals(Color.BLUE, cg.getColorFromDouble(i));
        }
    }
}
