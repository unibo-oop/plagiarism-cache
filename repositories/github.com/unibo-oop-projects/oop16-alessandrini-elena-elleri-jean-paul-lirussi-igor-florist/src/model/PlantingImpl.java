package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * the planting act.
 */
public class PlantingImpl implements Planting, Serializable {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -3357855598931634512L;
    private final Plant plant;
    private final Employee emp;
    private final LocalDate start; //periodo in cui pianti la pianta
    private final LocalDate end;    //periodo fino a cui lasci la pianta

    /**
     * @param plant the plant which is planted
     * @param emp the employee who plants the plant in the planting
     * @param start the start date of the planting
     * @param end the end date of the planting
     */
    public PlantingImpl(final Plant plant, final Employee emp, final LocalDate start, final LocalDate end) {
        this.plant = plant;
        this.emp = emp;
        this.start = start;
        this.end = end;
    }

    @Override
    public Employee getEmployee() {
        return this.emp;
    }

    @Override
    public Pair<LocalDate, LocalDate> getDates() {
        return new Pair<LocalDate, LocalDate>(this.start, this.end);
    }

    @Override
    public Period getPeriod() {
        return Period.between(this.start, this.end);
    }

    @Override
    public Period getTime() {
        return Period.between(start, LocalDate.now());
    }

    @Override
    public Plant getPlant() {
        return this.plant;
    }

}
