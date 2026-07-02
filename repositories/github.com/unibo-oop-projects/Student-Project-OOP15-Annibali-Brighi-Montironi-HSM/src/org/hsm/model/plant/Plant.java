package org.hsm.model.plant;

import java.util.List;

/**
 *
 *
 *
 */
public interface Plant {

    /**
     *
     * @return the PlantModel
     */
    PlantModel getModel();

    /**
     *
     * @return the cost of the plant
     */
    double getCost();

    /**
     *
     * @return the list of pH values
     */
    List<Double> getPhList();

    /**
     *
     * @return the list of Brightness values
     */
    List<Double> getBrightList();

    /**
     *
     * @return the list of Conductivity values
     */
    List<Double> getConductList();

    /**
     *
     * @return the list of Temperature values
     */
    List<Double> getTempList();

    /**
     *
     * @param value
     *            Brightness value (lumen)
     */
    void addBrightValue(final double value);

    /**
     *
     * @param value
     *            Countuctivity value (cF)
     */
    void addConductValue(final double value);

    /**
     *
     * @param value
     *            Temperatur value (�C)
     */
    void addTempValue(final double value);

    /**
     *
     * @param value
     *            pH value (pH)
     */
    void addPhValue(final double value);

    /**
     * @return the last bright value added in brightList
     */
    double getLastBrightValue();

    /**
     * @return the last conduct value added in conductList
     */
    double getLastConductValue();

    /**
     * @return the last temperature value added in tempList
     */
    double getLastTempValue();

    /**
     * @return the last PH value added in phList
     */
    double getLastPhValue();

    /**
     * @return the number of elements in the lists of the plant
     */
    int nUpdate();

    /**
     *
     * @return the list of pH values of a traditional cultivation
     */
    List<Double> getPhListTraditional();

    /**
     *
     * @return the list of Brightness values of a traditional cultivation
     */
    List<Double> getBrightListTraditional();

    /**
     *
     * @return the list of Conductivity values of a traditional cultivation
     */
    List<Double> getConductListTraditional();

    /**
     *
     * @return the list of Temperature values of a traditional cultivation
     */
    List<Double> getTempListTraditional();

    /**
     *
     * @param value
     *            Brightness value (lumen) of a traditional cultivation
     */
    void addBrightValueTraditional(final double value);

    /**
     *
     * @param value
     *            Countuctivity value (cF) of a traditional cultivation
     */
    void addConductValueTraditional(final double value);

    /**
     *
     * @param value
     *            Temperatur value (�C) of a traditional cultivation
     */
    void addTempValueTraditional(final double value);

    /**
     *
     * @param value
     *            pH value (pH) of a traditional cultivation
     */
    void addPhValueTraditional(final double value);

    /**
     * @return the last bright value added in brightListTrad
     */
    double getLastBrightValueTraditional();

    /**
     * @return the last conduct value added in conductListTrad
     */
    double getLastConductValueTraditional();

    /**
     * @return the last temperature value added in tempListTrad
     */
    double getLastTempValueTraditional();

    /**
     * @return the last PH value added in phListTrad
     */
    double getLastPhValueTraditional();
}
