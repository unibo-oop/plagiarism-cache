package modelimpl.manageprogrammedfilms;

import java.util.Comparator;

import model.manageprogrammingfilms.Sorter;
import utilities.factory.ProgrammedFilm;
/** 
 * Describe a sorter by hall on programmed film.
 * */
public final class SorterByHallImpl implements Sorter<ProgrammedFilm> {
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Comparator<ProgrammedFilm> getComparator() {
        //return (fp1, fp2) -> fp1.getHall() - fp2.getHall();
        return (fp1, fp2) -> fp1.getHall().compareTo(fp2.getHall());
    }

}
