package it.unibo.oop.mge.model;

import it.unibo.oop.mge.function.AlgebricFunction;

/**
 * The Interface StringComposer.
 */
public interface StringComposer {

    /**
     * Parses the function given in input.
     *
     * @param str the function.
     * @return the algebric function.
     */
    AlgebricFunction parse(String str);
}
