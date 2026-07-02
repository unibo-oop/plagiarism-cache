package it.unibo.oop.mge.parser;

/**
 * The Interface RealParser.
 */
public interface ResolveOperator {

    /**
     * The following method allows to substitute all basic operators " + , - , * ,
     * to a certain string.
     *
     * @return the string
     */

    String funcRewriter();

    /**
     * Sets the string.
     *
     * @param str the new string
     */
    void setString(String str);
}
