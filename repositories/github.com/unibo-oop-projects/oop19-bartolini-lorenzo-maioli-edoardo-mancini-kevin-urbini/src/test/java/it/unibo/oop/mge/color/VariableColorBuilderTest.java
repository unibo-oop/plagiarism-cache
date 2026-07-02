package it.unibo.oop.mge.color;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class VariableColorBuilderTest {
    @Test
    public void setColorsTest() {
        final int red = 10;
        final int green = 150;
        final int blue = 0;
        /* Set red and green */
        VariableColor vc = new VariableColorBuilderImpl().red(red).green(green).build();
        assertTrue(vc.getRed().isPresent());
        assertTrue(vc.getGreen().isPresent());
        assertTrue(vc.getBlue().isEmpty());
        assertTrue(vc.getRed().get().equals(red));
        assertTrue(vc.getGreen().get().equals(green));
        /* Set red and blue */
        vc = new VariableColorBuilderImpl().red(red).blue(blue).build();
        assertTrue(vc.getRed().isPresent());
        assertTrue(vc.getGreen().isEmpty());
        assertTrue(vc.getBlue().isPresent());
        assertEquals(red, vc.getRed().get().intValue());
        assertEquals(blue, vc.getBlue().get().intValue());
        /* Set blue and green */
        vc = new VariableColorBuilderImpl().blue(blue).green(green).build();
        assertTrue(vc.getRed().isEmpty());
        assertTrue(vc.getGreen().isPresent());
        assertTrue(vc.getBlue().isPresent());
        assertEquals(green, vc.getGreen().get().intValue());
        assertEquals(blue, vc.getBlue().get().intValue());
        /* Set all */
        assertThrows(IllegalStateException.class,
                () -> new VariableColorBuilderImpl().blue(blue).green(green).red(red).build());
        /* Set none */
        vc = new VariableColorBuilderImpl().build();
        assertTrue(vc.getRed().isEmpty());
        assertTrue(vc.getGreen().isEmpty());
        assertTrue(vc.getBlue().isEmpty());
    }

    public final void setMultipleTimes() {
        final int red = 10;
        final int green = 150;
        final int blue = 0;
        /* Set blue 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().blue(blue).blue(blue));
        /* Set red 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().red(red).red(red));
        /* Set green 2 times */
        assertThrows(IllegalStateException.class, () -> new VariableColorBuilderImpl().green(green).green(green));
    }

    public final void buildMultipleTimes() {
        /* Build 2 times */
        final var builder = new VariableColorBuilderImpl();
        builder.build();
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    public final void trySetBadValues() {
        final int red = -5;
        final int green = 1050;
        final int blue = -1000;
        /* Set bad blue */
        assertThrows(IllegalArgumentException.class, () -> new VariableColorBuilderImpl().blue(blue));
        /* Set bad red */
        assertThrows(IllegalArgumentException.class, () -> new VariableColorBuilderImpl().red(red));
        /* Set bad green */
        assertThrows(IllegalArgumentException.class, () -> new VariableColorBuilderImpl().green(green));
    }
}
