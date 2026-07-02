package it.unibo.oop.mge.color;

import java.util.Optional;

public final class VariableColorBuilderImpl implements VariableColorBuilder {
    private Optional<Integer> red = Optional.empty();
    private Optional<Integer> green = Optional.empty();
    private Optional<Integer> blue = Optional.empty();
    private static final int MAXRGBVALUE = 255;
    private static final int MINRGBVALUE = 0;
    private Boolean builded = false;

    private Boolean belongInterval(final int value) {
        return value >= MINRGBVALUE && value <= MAXRGBVALUE ? true : false;
    }

    private int countColorsSetted() {
        return (this.red.isPresent() ? 1 : 0) + (this.green.isPresent() ? 1 : 0) + (this.blue.isPresent() ? 1 : 0);
    }

    private void throwIllArgExc() {
        throw new IllegalArgumentException("Error using VariableColorBuilderImpl");
    }

    private void throwIllSttExc() {
        throw new IllegalStateException("Error using VariableColorBuilderImpl");
    }

    @Override
    public VariableColorBuilder red(final int red) {
        if (this.red.isPresent() || countColorsSetted() >= 2) {
            throwIllSttExc();
        } else if (!belongInterval(red)) {
            throwIllArgExc();
        } else {
            this.red = Optional.of(red);
            return this;
        }
        return null;
    }

    @Override
    public VariableColorBuilder green(final int green) {
        if (this.green.isPresent() || countColorsSetted() >= 2) {
            throwIllSttExc();
        } else if (!belongInterval(green)) {
            throwIllArgExc();
        } else {
            this.green = Optional.of(green);
            return this;
        }
        return null;
    }

    @Override
    public VariableColorBuilder blue(final int blue) {
        if (this.blue.isPresent() || countColorsSetted() >= 2) {
            throwIllSttExc();
        } else if (!belongInterval(blue)) {
            throwIllArgExc();
        } else {
            this.blue = Optional.of(blue);
            return this;
        }
        return null;
    }

    @Override
    public VariableColor build() {
        if (this.builded) {
            this.throwIllSttExc();
            return null;
        } else {
            this.builded = true;
            return new VariableColorImpl(this.red, this.green, this.blue);
        }
    }
}
