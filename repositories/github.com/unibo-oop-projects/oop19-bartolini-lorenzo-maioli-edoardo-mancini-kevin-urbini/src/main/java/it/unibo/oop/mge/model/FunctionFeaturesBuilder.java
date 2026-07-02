package it.unibo.oop.mge.model;

import java.awt.Color;

import it.unibo.oop.mge.color.VariableColor;
import it.unibo.oop.mge.function.AlgebricFunction;

/**
 * The Interface FunctionFeaturesBuilder.
 */
public interface FunctionFeaturesBuilder {

    /**
     * Sets the function.
     *
     * @param function the function that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder function(AlgebricFunction function);

    /**
     * Sets the intervals.
     *
     * @param min the minimum that will be setted.
     * @param max the maximum that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder intervals(double min, double max);

    /**
     * Sets the rate.
     *
     * @param rate the rate that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder rate(double rate);

    /**
     * Sets the dynamic color.
     *
     * @param varColor the VariableColor that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder dynamicColor(VariableColor varColor);

    /**
     * Sets the static color.
     *
     * @param color the static color that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder staticColor(Color color);

    /**
     * Sets the decimal precision.
     *
     * @param decimalPrecision the decimal precision that will be setted.
     * @return a FunctionFeaturesBuilder
     */
    FunctionFeaturesBuilder decimalPrecision(int decimalPrecision);

    /**
     * Builds the FunctionFeatures object.
     *
     * @return the FunctionFeaturesImpl.
     */
    FunctionFeaturesImpl build();
}
