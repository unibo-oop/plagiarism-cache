package it.unibo.oop.mge.libraries;

import java.util.Map;

public class PointNDImpl implements PointND {
    private final Map<Variable, Double> values;
    private final double functionValue;

    public PointNDImpl(final Map<Variable, Double> values, final double functionValue) {
        this.values = values;
        this.functionValue = functionValue;
    }

    private Boolean isNull(final Object obj) {
        return obj == null;
    }

    @Override
    public final double getVariableValue(final Variable name) {
        return isNull(this.values.get(name)) ? 0 : this.values.get(name);
    }

    @Override
    public final double getFunctionValue() {
        return this.functionValue;
    }

    @Override
    public final String toString() {
        return this.values.toString() + " F : " + this.functionValue + "\n";
    }
}
