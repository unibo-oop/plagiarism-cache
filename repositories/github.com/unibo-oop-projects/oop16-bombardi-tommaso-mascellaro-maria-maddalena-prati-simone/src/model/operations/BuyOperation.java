package model.operations;

import model.admin.Pair;
import model.admin.products.Object1;

/**
 * Class with the implementation  of a buy operation.
 */
public class BuyOperation implements Operation {

    private static final long serialVersionUID = 1L;
    private static final double BUY_GAIN_RATE = 0.65;
    private final Object1 obj;
    private final int numObj;

    /**
     * Constructor for buy operation.
     * 
     * @param object
     *              enum value which represents the type of object to buy
     * @param numObjects
     *              integer which represents the number of objects to buy
     */
    public BuyOperation(final Object1 object, final int numObjects) {
        this.obj = object;
        this.numObj = numObjects;
    }

    @Override
    public String getDescription() {
        return "Acquisto Articoli";
    }

    @Override
    public String getDetail() {
        return "Acquisto " + this.obj.getDescription();
    }

    @Override
    public String getInfo() {
        return "Quantita'= " + this.numObj;
    }

    @Override
    public Double getPrice() {
        return this.obj.getBuyPrice() * this.numObj;
    }

    @Override
    public Pair<Double, Double> getGain() {
        return new Pair<Double, Double>(this.getPrice(), this.getPrice() * BUY_GAIN_RATE);
    }

}
