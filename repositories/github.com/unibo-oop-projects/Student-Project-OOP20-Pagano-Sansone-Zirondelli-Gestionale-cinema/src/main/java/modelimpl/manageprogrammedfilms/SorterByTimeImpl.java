package modelimpl.manageprogrammedfilms;

import java.util.Comparator;

import model.manageprogrammingfilms.Sorter;
import utilities.factory.ProgrammedFilm;
/** 
 * 
 * Describe a sorter by time on programmed film.
 * */

public final class SorterByTimeImpl implements Sorter<ProgrammedFilm> {
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Comparator<ProgrammedFilm> getComparator() {
        return (pf1, pf2) -> { //a negative integer, zero, or a positive integer as pf1 is less than, equal to, or greater than pf2
            return pf1.getStartTime().compareTo(pf2.getStartTime());
        };
    }

}
