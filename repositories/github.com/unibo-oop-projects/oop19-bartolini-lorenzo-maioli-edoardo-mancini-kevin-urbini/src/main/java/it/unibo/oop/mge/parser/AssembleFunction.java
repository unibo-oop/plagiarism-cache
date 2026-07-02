package it.unibo.oop.mge.parser;

import it.unibo.oop.mge.function.AlgebricFunction;

/**
 * The Interface FinalParser.
 */
public interface AssembleFunction {

    /**
     * The following method permit to create the AlgebricFunction from a parsed string.
     *
     * @param fstring the parsed String
     * @return the AlgebricFunction
     */
    AlgebricFunction resolveFunction(String fstring);
}
