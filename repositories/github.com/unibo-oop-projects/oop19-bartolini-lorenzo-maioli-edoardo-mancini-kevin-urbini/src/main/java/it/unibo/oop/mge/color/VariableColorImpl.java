package it.unibo.oop.mge.color;

import java.util.Optional;

public class VariableColorImpl implements VariableColor {
    private final Optional<Integer> red, green, blue;

    protected VariableColorImpl(final Optional<Integer> red, final Optional<Integer> green,
            final Optional<Integer> blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public final Optional<Integer> getRed() {
        return red;
    }

    @Override
    public final Optional<Integer> getGreen() {
        return green;
    }

    @Override
    public final Optional<Integer> getBlue() {
        return blue;
    }
}
