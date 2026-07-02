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
import viewimpl.booking.SorterByLocalTime;

public class TestSortTime {
    @Test
    public void testControllerHandlerProgrammedFilm() {
        final LocalDate date1 = LocalDate.of(2000, 5, 1);
        final LocalTime time1s = LocalTime.of(1, 25);
        final LocalTime time2s = LocalTime.of(4, 30);
        final LocalTime time3s = LocalTime.of(5, 15);
        final LocalTime time4s = LocalTime.of(19, 25);
        final LocalTime time1f = LocalTime.of(2, 25);
        final LocalTime time2f = LocalTime.of(5, 25);
        final LocalTime time3f = LocalTime.of(6, 25);
        final LocalTime time4f = LocalTime.of(21, 25);
        final ProgrammedFilmFactory factoryProgrammedFilm = new ProgrammedFilmFactoryImpl();
        final ProgrammedFilm filmProgrammed = factoryProgrammedFilm.createProgrammedFilm(1, Hall.HALL_1, 8.0, date1, time1s, time1f);
        final ProgrammedFilm filmProgrammed1 = factoryProgrammedFilm.createProgrammedFilm(2, Hall.HALL_2, 10.0, date1, time2s, time2f);
        final ProgrammedFilm filmProgrammed2 = factoryProgrammedFilm.createProgrammedFilm(3, Hall.HALL_3, 12.0, date1, time3s, time3f);
        final ProgrammedFilm filmProgrammed3 = factoryProgrammedFilm.createProgrammedFilm(4, Hall.HALL_4, 14.0, date1, time4s, time4f);
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
        final List<ProgrammedFilm> listProgrammedSorted = listProgrammedFilm.stream().sorted(new SorterByLocalTime().getComparator()).collect(Collectors.toList());
        assertEquals(listOrdered, listProgrammedSorted);

    }
}
