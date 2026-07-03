package model.operations;

import model.admin.Pair;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * Class with the implementation  of a skipass operation.
 */
public class SkipassOperation implements Operation {

    private static final long serialVersionUID = 1L;
    private final Skipass skip;
    private final int numObj;
    private final Season season;

    /**
     * Constructor for skipass operation.
     * 
     * @param sk
     *              enum value which represents the instructor's lesson
     * @param numObjects
     *              integer which represents the number of skipass
     * @param s
     *              enum value which represents the season of the skipass
     */
    public SkipassOperation(final Skipass sk, final int numObjects, final Season s) {
        this.skip = sk;
        this.numObj = numObjects;
        this.season = s;
    }

    @Override
    public String getDescription() {
        return "Acquisto Skipass";
    }

    @Override
    public String getDetail() {
        return "Skipass " + this.skip.getDescription();
    }

    @Override
    public String getInfo() {
        return "Quantita'= " + this.numObj + "  //  " + this.season.getDescription();
    }

    @Override
    public Double getPrice() {
        return this.skip.getPrice() * this.numObj * this.season.getRate();
    }

    @Override
    public Pair<Double, Double> getGain() {
        return new Pair<Double, Double>(this.getPrice(), this.getPrice());
    }
}
