package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.oop.mge.libraries.MathFunction;
import it.unibo.oop.mge.libraries.Variable;
import it.unibo.oop.mge.libraries.Constant;

/**
 * A factory for creating AlgebricFunction objects.
 */
public interface AlgebricFunctionFactory {

    /**
     * Gets a value function.
     *
     * @param value of the function.
     * @return an AlgebricFunction that correspond to the given value.
     */
    static AlgebricFunction getValueFunction(Double value) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return value;
            }
        };
    }

    /**
     * Gets a constant function.
     *
     * @param c is a constant.
     * @return an AlgebricFunction that correspond to the given constant.
     */
    static AlgebricFunction getConstantFunction(final Constant c) {
        return getValueFunction(c.resolve());
    }

    /**
     * Gets a parameter function.
     *
     * @param name is the name of the variable.
     * @return an AlgebricFunction that correspond to a Variable with the given
     *         name.
     */
    static AlgebricFunction getParameterFunction(final Variable name) {
        return new AlgebricFunctionImpl(Optional.empty()) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return values.get(name);
            }
        };
    }

    /**
     * Gets a math function with some parameters.
     *
     * @param id   is the MathFunction.
     * @param pars is a list of the parameters of the MathFunction.
     * @return an AlgebricFunction that is a Mathematical Function with the given
     *         parameters.
     */
    static AlgebricFunction getMathFunction(final MathFunction id, final List<AlgebricFunction> pars) {
        return new AlgebricFunctionImpl(Optional.of(pars)) {
            @Override
            public Double resolve(final Map<Variable, Double> values) {
                return id.resolve(
                        this.getParameters().get().stream().map(i -> i.resolve(values)).collect(Collectors.toList()));
            }
        };
    }
}
