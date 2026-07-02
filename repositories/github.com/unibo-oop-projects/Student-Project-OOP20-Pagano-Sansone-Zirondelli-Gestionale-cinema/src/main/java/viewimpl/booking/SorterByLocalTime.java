package viewimpl.booking;

import java.util.Comparator;

import model.manageprogrammingfilms.Sorter;
import utilities.factory.ProgrammedFilm;

public class SorterByLocalTime implements Sorter<ProgrammedFilm> {
    public SorterByLocalTime() {}
    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<ProgrammedFilm> getComparator() {
        return (f1, f2) -> {
            return f1.getStartTime().compareTo(f2.getStartTime());
        };
    }

}
