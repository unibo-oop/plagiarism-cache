package it.unibo.oop.mge.color;

import java.awt.Color;
import java.util.function.Function;

public class ColorGeneratorImpl implements ColorGenerator {
    private final Function<Double, Color> colorFunction;
    private static final int MAXRGBVALUE = 255;

    public ColorGeneratorImpl(final VariableColor varColor, final double min, final double max) {
        /*
         * Define a line named 'linearFunction' that passes between 2 points : a =
         * (min,0) b = (max,MAXRGBVALUE)
         */
        final double m = min == max ? 0 : MAXRGBVALUE / (max - min);
        final double q = -m * min;
        final Function<Double, Integer> linearFunction = x -> (int) (m * x + q);
        colorFunction = i -> {
            /* This check convert 'i' to a value that belong to the interval [min,max] */
            i = i > max ? max : i < min ? min : i;
            return new Color(varColor.getRed().isPresent() ? varColor.getRed().get() : linearFunction.apply(i),
                    varColor.getGreen().isPresent() ? varColor.getGreen().get() : linearFunction.apply(i),
                    varColor.getBlue().isPresent() ? varColor.getBlue().get() : linearFunction.apply(i));
        };
    }

    public ColorGeneratorImpl(final Color color) {
        this.colorFunction = i -> color;
    }

    @Override
    public final Color getColorFromDouble(final Double value) {
        return this.colorFunction.apply(value);
    }
}
