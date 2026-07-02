package utilitiesimpl.factoryimpl;

import java.time.LocalDate;
import java.time.LocalTime;

import utilities.factory.ProgrammedFilm;
import utilities.factory.ProgrammedFilmFactory;
import utilitiesimpl.Hall;

public final class ProgrammedFilmFactoryImpl implements ProgrammedFilmFactory {
    /**
     * {@inheritDoc}
     * */
    @Override
    public ProgrammedFilm createProgrammedFilm(final int idFilm, final Hall hall, final double price, final LocalDate date, final LocalTime startTime, final  LocalTime endTime) {
        return new ProgrammedFilmImpl(idFilm, hall, price, date, new TimeSlotImpl(startTime, endTime));
    }
 

}
