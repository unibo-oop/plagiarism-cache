package viewimpl.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

import model.manageprogrammingfilms.Filter;
import utilities.factory.ProgrammedFilm;

public class FilterOldDateImpl implements Filter<ProgrammedFilm> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate<ProgrammedFilm> getPredicate() {
        return f -> f.getDate().isAfter(LocalDate.now()) 
        || f.getDate().equals(LocalDate.now()) 
        && f.getStartTime().isAfter(LocalTime.now());
    }

}
