package viewimpl.booking;

import java.util.Comparator;

import model.manageprogrammingfilms.Sorter;
import utilities.factory.ProgrammedFilm;

public class SorterByLocalDate implements Sorter<ProgrammedFilm>{
    public SorterByLocalDate() {}
    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<ProgrammedFilm> getComparator() {
       return (f1, f2) -> {
            return f1.getDate().compareTo(f2.getDate());
        };
    }

}
