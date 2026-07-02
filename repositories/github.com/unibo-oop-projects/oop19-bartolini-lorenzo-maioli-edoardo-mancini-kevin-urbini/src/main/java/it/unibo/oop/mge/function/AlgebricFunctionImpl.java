package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Optional;

abstract class AlgebricFunctionImpl implements AlgebricFunction {
    private final Optional<List<AlgebricFunction>> parameters;

    protected AlgebricFunctionImpl(final Optional<List<AlgebricFunction>> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Optional<List<AlgebricFunction>> getParameters() {
        return this.parameters;
    }

}
