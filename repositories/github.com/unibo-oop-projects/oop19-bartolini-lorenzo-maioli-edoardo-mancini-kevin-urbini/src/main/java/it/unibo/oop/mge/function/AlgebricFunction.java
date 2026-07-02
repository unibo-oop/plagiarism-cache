package it.unibo.oop.mge.function;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.oop.mge.libraries.Variable;

/**
 * The Interface AlgebricFunction.
 */
public interface AlgebricFunction {

    /**
     * Resolve the function.
     *
     * @param values containing the values of the variables.
     * @return the value of the function.
     */
    Double resolve(Map<Variable, Double> values);

    /**
     * Gets the parameters.
     *
     * @return an optional that could contains the parameters.
     */
    Optional<List<AlgebricFunction>> getParameters();
}
