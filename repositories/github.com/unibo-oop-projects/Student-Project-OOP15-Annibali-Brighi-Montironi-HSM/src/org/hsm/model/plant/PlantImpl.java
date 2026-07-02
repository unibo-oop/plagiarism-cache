package org.hsm.model.plant;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *implementation of Plant interface
 *
 */
public class PlantImpl implements Plant, Serializable {

    private static final long serialVersionUID = 1101353617623045838L;
    private static final int CENT_FACTOR = 100;
    private static final int MAX_NUMB_OF_VALUE = 1000; // THIS VALUE MUST BE
                                                       // GREATER THAN
                                                       // NUMB_OF_ERASABLE_ELEMENTS
    private static final int NUMB_OF_ERASABLE_ELEMENTS = 200;
    private final PlantModel model;
    private final int cost;
    private final List<Double> phList;
    private final List<Double> brightList;
    private final List<Double> conductList;
    private final List<Double> tempList;

    private final List<Double> phListTrad;
    private final List<Double> brightListTrad;
    private final List<Double> conductListTrad;
    private final List<Double> tempListTrad;

    /**
     *
     * @param model
     *            PlantModel
     * @param cost
     *            Cost in euro
     */
    public PlantImpl(final PlantModel model, final int cost) {
        super();
        this.model = model;
        this.cost = cost;

        this.phList = new LinkedList<>();
        this.brightList = new LinkedList<>();
        this.conductList = new LinkedList<>();
        this.tempList = new LinkedList<>();

        this.phListTrad = new LinkedList<>();
        this.brightListTrad = new LinkedList<>();
        this.conductListTrad = new LinkedList<>();
        this.tempListTrad = new LinkedList<>();
    }

    @Override
    public PlantModel getModel() {
        return this.model;
    }

    @Override
    public double getCost() {
        return (double) cost / CENT_FACTOR;
    }

    @Override
    public List<Double> getPhList() {
        return new LinkedList<>(this.phList);
    }

    @Override
    public List<Double> getBrightList() {
        return new LinkedList<>(this.brightList);
    }

    @Override
    public List<Double> getConductList() {
        return new LinkedList<>(this.conductList);
    }

    @Override
    public List<Double> getTempList() {
        return new LinkedList<>(this.tempList);
    }

    @Override
    public double getLastBrightValue() {
        return getLastValue(brightList);
    }

    @Override
    public double getLastConductValue() {
        return getLastValue(conductList);
    }

    @Override
    public double getLastTempValue() {
        return getLastValue(tempList);
    }

    @Override
    public double getLastPhValue() {
        return getLastValue(phList);
    }

    @Override
    public List<Double> getPhListTraditional() {
        return new LinkedList<>(this.phListTrad);
    }

    @Override
    public List<Double> getBrightListTraditional() {
        return new LinkedList<>(this.brightListTrad);
    }

    @Override
    public List<Double> getConductListTraditional() {
        return new LinkedList<>(this.conductListTrad);
    }

    @Override
    public List<Double> getTempListTraditional() {
        return new LinkedList<>(this.tempListTrad);
    }

    @Override
    public double getLastBrightValueTraditional() {
        return getLastValue(brightListTrad);
    }

    @Override
    public double getLastConductValueTraditional() {
        return getLastValue(conductListTrad);
    }

    @Override
    public double getLastTempValueTraditional() {
        return getLastValue(tempListTrad);
    }

    @Override
    public double getLastPhValueTraditional() {
        return getLastValue(phListTrad);
    }

    @Override
    public void addPhValue(final double value) {
        addElement(this.phList, value);
    }

    @Override
    public void addBrightValue(final double value) {
        addElement(this.brightList, value);
    }

    @Override
    public void addConductValue(final double value) {
        addElement(this.conductList, value);
    }

    @Override
    public void addTempValue(final double value) {
        addElement(this.tempList, value);
    }

    @Override
    public void addBrightValueTraditional(final double value) {
        addElement(this.brightListTrad, value);
    }

    @Override
    public void addConductValueTraditional(final double value) {
        addElement(this.conductListTrad, value);

    }

    @Override
    public void addTempValueTraditional(final double value) {
        addElement(this.tempListTrad, value);
    }

    @Override
    public void addPhValueTraditional(final double value) {
        addElement(this.phListTrad, value);
    }

    @Override
    public int nUpdate() {
        return this.phList.size();
    }

    /*
     * method used by some getters to return a double
     */
    private Double getLastValue(final List<Double> l) {
        if (l.isEmpty()) {
            return 0.0;
        } else {
            return l.get(l.size() - 1);
        }
    }

    /*
     * method used to add a value in the list. If the list is too big, then
     * delete old values
     */
    private void addElement(List<Double> l, final Double value) {
        if (l.size() >= MAX_NUMB_OF_VALUE) {
            final List<Double> tmp = new LinkedList<>(l.subList(NUMB_OF_ERASABLE_ELEMENTS, l.size() - 1));
            l = tmp;
        }
        l.add(value);
    }

    @Override
    public String toString() {
        return "PlantImpl [model=" + model + ", cost=" + cost + ", phList=" + phList + ", brightList=" + brightList
                + ", conductList=" + conductList + ", tempList=" + tempList + "]";
    }

}
