package model.operations;

import model.admin.Pair;
import model.admin.products.Object2;

/**
 * Class with the implementation  of a storage operation.
 */
public class StorageOperation implements Operation {

    private static final long serialVersionUID = 1L;
    private static final double REBATE_RATE = 0.05;
    private final Object2 obj;
    private final int numObj;
    private final int numDays;

    /**
     * Constructor for storage operation.
     * 
     * @param object
     *              enum value which represents the type of object to storage
     * @param numObjects
     *              integer which represents the number of objects to storage
     * @param duration
     *              integer which represents the duration of the storage (number of days)
     */
    public StorageOperation(final Object2 object, final int numObjects, final int duration) {
        this.obj = object;
        this.numObj = numObjects;
        this.numDays = duration;
    }

    @Override
    public String getDescription() {
        return "Prenotazione Deposito";
    }

    @Override
    public String getDetail() {
        return "Noleggio " + this.obj.getDescription();
    }

    @Override
    public String getInfo() {
        return "Quantita'= " + this.numObj + "  //  Giorni= " + this.numDays;
    }

    @Override
    public Double getPrice() {
        double price = 0.00, rate = 1.00;
        for (int count = 1; count <= this.numDays; count++) {
            price = price + (this.getSingleDayPrice() * rate);
            rate = rate - REBATE_RATE;
        }
        return price;
    }
    private Double getSingleDayPrice() {
        return this.obj.getStoragePrice() * this.numObj;
    }

    @Override
    public Pair<Double, Double> getGain() {
        return new Pair<Double, Double>(this.getPrice(), this.getPrice());
    }

}
