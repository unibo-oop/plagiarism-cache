package testbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import utilities.factory.ProgrammedFilm;
import utilities.factory.ProgrammedFilmFactory;
import utilitiesimpl.Hall;
import utilitiesimpl.factoryimpl.ProgrammedFilmFactoryImpl;
import viewimpl.booking.SorterByLocalDate;

public class TestSortDate {
    @Test
    public void testControllerHandlerProgrammedFilm() {
        final LocalDate date1 = LocalDate.of(2000, 5, 1);
        final LocalDate date2 = LocalDate.of(2000, 6, 1);
        final LocalDate date3 = LocalDate.of(2000, 6, 23);
        final LocalDate date4 = LocalDate.of(2000, 9, 10);
        final LocalTime timeStart = LocalTime.of(9, 10);
        final LocalTime timeEnd = LocalTime.of(19, 25);
        final ProgrammedFilmFactory factoryProgrammedFilm = new ProgrammedFilmFactoryImpl();
        final ProgrammedFilm filmProgrammed = factoryProgrammedFilm.createProgrammedFilm(1, Hall.HALL_1, 8.0, date1, timeStart, timeEnd);
        final ProgrammedFilm filmProgrammed1 = factoryProgrammedFilm.createProgrammedFilm(2, Hall.HALL_2, 10.0, date2, timeStart, timeEnd);
        final ProgrammedFilm filmProgrammed2 = factoryProgrammedFilm.createProgrammedFilm(3, Hall.HALL_3, 12.0, date3, timeStart, timeEnd);
        final ProgrammedFilm filmProgrammed3 = factoryProgrammedFilm.createProgrammedFilm(4, Hall.HALL_4, 14.0, date4, timeStart, timeEnd);
        final List<ProgrammedFilm> listProgrammedFilm = new ArrayList<>();
        final List<ProgrammedFilm> listOrdered = new ArrayList<>();
        listProgrammedFilm.add(filmProgrammed3);
        listProgrammedFilm.add(filmProgrammed2);
        listProgrammedFilm.add(filmProgrammed1);
        listProgrammedFilm.add(filmProgrammed);
        listOrdered.add(filmProgrammed);
        listOrdered.add(filmProgrammed1);
        listOrdered.add(filmProgrammed2);
        listOrdered.add(filmProgrammed3);
        final List<ProgrammedFilm> listProgrammedSorted = listProgrammedFilm.stream().sorted(new SorterByLocalDate().getComparator()).collect(Collectors.toList());
        assertEquals(listOrdered, listProgrammedSorted);

    }
}
