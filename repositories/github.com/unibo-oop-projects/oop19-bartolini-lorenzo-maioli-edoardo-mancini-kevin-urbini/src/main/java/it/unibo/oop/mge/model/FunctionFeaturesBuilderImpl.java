package it.unibo.oop.mge.model;

import java.awt.Color;
import java.util.Optional;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;
import it.unibo.oop.mge.libraries.Pair;

public final class FunctionFeaturesBuilderImpl implements FunctionFeaturesBuilder {
    private Optional<AlgebricFunction> function = Optional.empty();
    private Optional<Pair<Double, Double>> interval = Optional.empty();
    private Optional<Integer> decimalPrecision = Optional.empty();
    private Optional<Double> rate = Optional.empty();
    private Optional<VariableColor> varColor = Optional.empty();
    private Optional<Color> staticColor = Optional.empty();
    private Boolean builded = false;

    private void throwIllArgExc() {
        throw new IllegalArgumentException("Error using FunctionFeaturesBuilderImpl");
    }

    private void throwIllSttExc() {
        throw new IllegalStateException("Error using FunctionFeaturesBuilderImpl");
    }

    private Boolean isNull(final Object obj) {
        return obj == null;
    }

    private Boolean ready() {
        return this.function.isPresent() && this.interval.isPresent() && this.rate.isPresent()
                && this.decimalPrecision.isPresent() && (this.staticColor.isPresent() || this.varColor.isPresent())
                && (this.staticColor.isEmpty() || this.varColor.isEmpty()) && !this.builded;
    }

    @Override
    public FunctionFeaturesBuilder function(final AlgebricFunction function) {
        if (this.function.isPresent()) {
            this.throwIllSttExc();
        } else if (this.isNull(function)) {
            this.throwIllArgExc();
        } else {
            this.function = Optional.of(function);
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesBuilder intervals(final double min, final double max) {
        if (this.interval.isPresent()) {
            this.throwIllSttExc();
        } else if (min >= max) {
            this.throwIllArgExc();
        } else {
            this.interval = Optional.of(new Pair<Double, Double>(min, max));
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesBuilder rate(final double rate) {
        if (this.rate.isPresent()) {
            this.throwIllSttExc();
        } else if (rate <= 0) {
            this.throwIllArgExc();
        } else {
            this.rate = Optional.of(rate);
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesBuilder dynamicColor(final VariableColor varColor) {
        if (this.varColor.isPresent() || this.staticColor.isPresent()) {
            this.throwIllSttExc();
        } else if (this.isNull(varColor)) {
            this.throwIllArgExc();
        } else {
            this.varColor = Optional.of(varColor);
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesBuilder decimalPrecision(final int decimalPrecision) {
        if (this.decimalPrecision.isPresent()) {
            this.throwIllSttExc();
        } else if (decimalPrecision < 0) {
            this.throwIllArgExc();
        } else {
            this.decimalPrecision = Optional.of(decimalPrecision);
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesBuilder staticColor(final Color color) {
        if (this.varColor.isPresent() || this.staticColor.isPresent()) {
            this.throwIllSttExc();
        } else if (this.isNull(color)) {
            this.throwIllArgExc();
        } else {
            this.staticColor = Optional.of(color);
            return this;
        }
        return null;
    }

    @Override
    public FunctionFeaturesImpl build() {
        if (this.ready()) {
            this.builded = true;
            return new FunctionFeaturesImpl(this.function.get(), this.interval.get(), this.rate.get(), this.varColor,
                    this.staticColor, this.decimalPrecision.get());
        } else {
            this.throwIllSttExc();
            return null;
        }
    }
}
