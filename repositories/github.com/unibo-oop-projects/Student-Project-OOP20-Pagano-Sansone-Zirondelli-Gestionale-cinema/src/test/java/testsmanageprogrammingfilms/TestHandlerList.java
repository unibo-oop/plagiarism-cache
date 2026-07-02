package testsmanageprogrammingfilms;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.manageprogrammingfilms.HandlerList;
import modelimpl.manageprogrammedfilms.FilterByDateHallImpl;
import modelimpl.manageprogrammedfilms.FilterByDateImpl;
import modelimpl.manageprogrammedfilms.HandlerListImpl;
import modelimpl.manageprogrammedfilms.SorterByHallImpl;
import modelimpl.manageprogrammedfilms.SorterByTimeImpl;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;
import utilitiesimpl.factoryimpl.ProgrammedFilmFactoryImpl;


class TestHandlerList {

    private final LocalDate ld1 = LocalDate.of(1999, 3, 6); 
    private final LocalDate ld2 = LocalDate.of(1999, 3, 7); 
    private final LocalDate ld3 = LocalDate.of(1999, 3, 6); 

    private final LocalTime st1 = LocalTime.of(8, 30);
    private final LocalTime st2 = LocalTime.of(6, 50);
    private final LocalTime st3 = LocalTime.of(8, 27);

    private final LocalTime et1 = LocalTime.of(8, 45);
    private final LocalTime et2 = LocalTime.of(7, 22);
    private final LocalTime et3 = LocalTime.of(8, 29);

    private final ProgrammedFilmFactoryImpl factory = new ProgrammedFilmFactoryImpl();
    private final HandlerList<ProgrammedFilm> handler = new HandlerListImpl<>();

    @Test 
    public void testFilterByDate() { /* Ensure correct filtering by date*/

        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld1, st1, et1);
        final ProgrammedFilm pf2 = factory.createProgrammedFilm(13, Hall.HALL_1, 56, ld1, st1, et1);
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld2, st1, et1);

        final List<ProgrammedFilm> filteredList = handler.filterBy(List.of(pf1, pf2, pf3), new FilterByDateImpl(ld2));
        assertEquals(filteredList, List.of(pf3));
    }

    @Test 
    public void testFilterByDateHall() { /* Ensure correct filtering by hall*/

        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld1, st1, et1); // different hall , same date
        final ProgrammedFilm pf2 = factory.createProgrammedFilm(12, Hall.HALL_2, 56, ld2, st2, st2); // different hall, different date
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld3, st3, et3);
        final ProgrammedFilm pf4 = factory.createProgrammedFilm(12, Hall.HALL_4, 31, ld1, st2, et2);
        final ProgrammedFilm pf5 = factory.createProgrammedFilm(12, Hall.HALL_4, 56, ld2, st1, et1); // same hall , different date
        final ProgrammedFilm pf6 = factory.createProgrammedFilm(12, Hall.HALL_4, 56, ld1, st1, et1);

        final List<ProgrammedFilm> filteredList = handler.filterBy(List.of(pf1, pf2, pf3, pf4, pf5, pf6), new FilterByDateHallImpl(ld1, Hall.HALL_4));
        assertTrue(filteredList.containsAll(List.of(pf4, pf6)));
    }

    @Test 
    public void testSorterByHall() { 

        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_5, 56, ld1, st1, et1); // different hall , same date
        final ProgrammedFilm pf2 = factory.createProgrammedFilm(12, Hall.HALL_2, 56, ld2, st1, et1); // different hall, different date
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld2, st1, et1);
        final ProgrammedFilm pf4 = factory.createProgrammedFilm(12, Hall.HALL_3, 56, ld2, st1, et1); // same hall , different date
        final ProgrammedFilm pf5 = factory.createProgrammedFilm(12, Hall.HALL_4, 56, ld1, st1, et1);

        final List<ProgrammedFilm> sortedList = handler.sortBy(List.of(pf1, pf2, pf3, pf4, pf5), new SorterByHallImpl());

        assertEquals(sortedList, List.of(pf3, pf2, pf4, pf5, pf1));
    }

    @Test
    void testSorterByTime() {
        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_5, 56, ld3, st2, et1); // different hall , same date
        final ProgrammedFilm pf2 = factory.createProgrammedFilm(12, Hall.HALL_2, 56, ld1, st1, et1); // different hall, different date
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld2, st3, et1);

        final List<ProgrammedFilm> sortedList = handler.sortBy(List.of(pf1, pf2, pf3), new SorterByTimeImpl());
        assertEquals(sortedList, List.of(pf1, pf3, pf2));
    }

}
