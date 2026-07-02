package it.unibo.oop.mge.libraries;

/**
 * The Interface PointND.
 */
public interface PointND {

    /**
     * Gets the variable value.
     *
     * @param name of the Variable.
     * @return the value of the given variable.
     */
    double getVariableValue(Variable name);

    /**
     * Gets the function value.
     *
     * @return the value of the function in this point.
     */
    double getFunctionValue();

    /**
     * To string.
     *
     * @return a string that represent this pointND.
     */
    String toString();
}
