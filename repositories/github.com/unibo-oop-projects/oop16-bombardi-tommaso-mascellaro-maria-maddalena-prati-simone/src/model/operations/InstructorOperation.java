package model.operations;

import model.admin.Pair;
import model.admin.products.Instructor;
import model.admin.products.Season;

/**
 * Class with the implementation  of an instructor operation.
 */
public class InstructorOperation implements Operation {

    private static final long serialVersionUID = 1L;
    private static final double INSTRUCTOR_HOUR_PAY = 15.00;
    private static final double REBATE_RATE = 0.25;
    private final Instructor inst;
    private final int numSkiers;
    private final Season season;

    /**
     * Constructor for instructor operation.
     * 
     * @param in
     *              enum value which represents the instructor's lesson
     * @param numSk
     *              integer which represents the number of skiers at the lesson
     * @param s
     *              enum value which represents the season of the lesson
     */
    public InstructorOperation(final Instructor in, final int numSk, final Season s) {
        this.inst = in;
        this.numSkiers = numSk;
        this.season = s;
    }

    @Override
    public String getDescription() {
        return "Prenotazione Maestro";
    }

    @Override
    public String getDetail() {
        return this.inst.getDescription();
    }

    @Override
    public String getInfo() {
        return "Studenti= " + this.numSkiers + "  //  " + this.season.getDescription();
    }

    @Override
    public Double getPrice() {
        double price = 0.00, rate = 1.00;
        for (int count = 1; count <= numSkiers; count++) {
            price = price + (this.getSingleStudentPrice() * rate);
            rate = rate - REBATE_RATE;
        }
        return price;
    }
    private Double getSingleStudentPrice() {
        return this.inst.getPrice() * this.season.getRate();
    }

    @Override
    public Pair<Double, Double> getGain() {
        return new Pair<Double, Double>(this.getPrice(), this.getPrice() - (INSTRUCTOR_HOUR_PAY * this.inst.getDuration()));
    }

}
