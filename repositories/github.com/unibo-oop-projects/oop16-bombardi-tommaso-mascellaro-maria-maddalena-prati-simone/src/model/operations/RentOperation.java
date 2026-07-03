package model.operations;

import model.admin.Pair;
import model.admin.products.Object2;
import model.admin.products.Season;

/**
 * Class with the implementation  of a rent operation.
 */
public class RentOperation implements Operation {

    private static final long serialVersionUID = 1L;
    private static final double MAINTENANCE_RATE = 0.40;
    private static final double REBATE_RATE = 0.05;
    private final Object2 obj;
    private final int numObj;
    private final int numDays;
    private final Season season;

    /**
     * Constructor for rent operation.
     * 
     * @param object
     *              enum value which represents the type of object to rent
     * @param numObjects
     *              integer which represents the number of objects to rent
     * @param duration
     *              integer which represents the duration of the rent (number of days)
     * @param s
     *              enum value which represents the season of the rent
     */
    public RentOperation(final Object2 object, final int numObjects, final int duration, final Season s) {
        this.obj = object;
        this.numObj = numObjects;
        this.numDays = duration;
        this.season = s;
    }

    @Override
    public String getDescription() {
        return "Noleggio Articoli";
    }

    @Override
    public String getDetail() {
        return "Noleggio " + this.obj.getDescription();
    }

    @Override
    public String getInfo() {
        return "Quantita'= " + this.numObj + "  //  Giorni= " + this.numDays + "  //  " + this.season.getDescription();
    }

    @Override
    public Double getPrice() {
        double price = 0.00, rate = 1.00;
        for (int count = 1; count <= numDays; count++) {
            price = price + (this.getSingleDayPrice() * rate);
            rate = rate - REBATE_RATE;
        }
        return price;
    }
    private Double getSingleDayPrice() {
        return this.obj.getRentPrice() * this.numObj * this.season.getRate();
    }

    @Override
    public Pair<Double, Double> getGain() {
        return new Pair<Double, Double>(this.getPrice(), this.getPrice() - this.getSingleDayPrice() * MAINTENANCE_RATE);
    }

}
