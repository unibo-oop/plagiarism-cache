package testsmanageprogrammingfilms;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import exceptions.ProgrammationNotAvailableException;
import model.manageprogrammingfilms.ManagerProgrammingFilms;
import model.manageprogrammingfilms.ProgrammedFilmsModel;
import modelimpl.managefilms.IdsGeneratorImpl;
import modelimpl.managefilms.ManagerIdsFilmImpl;
import modelimpl.manageprogrammedfilms.ManagerProgrammingFilmsImpl;
import modelimpl.manageprogrammedfilms.ProgrammedFilmsModelImpl;
import utilities.factory.Film;
import utilities.factory.FilmFactory;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;
import utilitiesimpl.factoryimpl.FilmFactoryImpl;
import utilitiesimpl.factoryimpl.ProgrammedFilmFactoryImpl;
import utilitiesimpl.factoryimpl.TimeSlotImpl;


class TestFilmProgrammation {

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
    private ManagerProgrammingFilms manager;

    @Test
    void testAvailability() {
        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_1, 56, ld1, st1, et1);
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(14, Hall.HALL_1, 56, ld2, st1, et1);

        manager = new ManagerProgrammingFilmsImpl(List.of(pf1, pf3));
        assertFalse(manager.isAvailableProgrammation(new TimeSlotImpl(LocalTime.of(8, 29), LocalTime.of(8,34)), ld1, Hall.HALL_1));
        assertFalse(manager.isAvailableProgrammation(new TimeSlotImpl(LocalTime.of(8, 31), LocalTime.of(8,34)), ld1, Hall.HALL_1));
        assertFalse(manager.isAvailableProgrammation(new TimeSlotImpl(LocalTime.of(8, 20), LocalTime.of(8,56)), ld1, Hall.HALL_1));

        assertTrue(manager.isAvailableProgrammation(new TimeSlotImpl(LocalTime.of(8, 46), LocalTime.of(8,50)), ld1, Hall.HALL_1));
        assertTrue(manager.isAvailableProgrammation(new TimeSlotImpl(LocalTime.of(8, 44), LocalTime.of(8,50)), ld1, Hall.HALL_3));
    }
    @Test
    void testProgrammedFilmsModel() throws ProgrammationNotAvailableException  {
        final FilmFactory filmFactory = new FilmFactoryImpl(new ManagerIdsFilmImpl(new IdsGeneratorImpl()));
        final ProgrammedFilmsModel container = new ProgrammedFilmsModelImpl();
        final ProgrammedFilm pf1 = factory.createProgrammedFilm(12, Hall.HALL_1, 20, ld1, st1, et1);
        final ProgrammedFilm pf2 = factory.createProgrammedFilm(14, Hall.HALL_2, 10, ld2, st1, et1);
        final ProgrammedFilm pf3 = factory.createProgrammedFilm(14, Hall.HALL_5, 15, ld2, st1, et1);
        final ProgrammedFilm pf4 = factory.createProgrammedFilm(19, Hall.HALL_1, 5, ld1, st1, et1);

        final Film f1 = filmFactory.createBasicFilmById("", "", "", Optional.empty(), 100, 14);
        container.addFilmProgrammation(pf1);
        container.addFilmProgrammation(pf2);
        container.addFilmProgrammation(pf3);

        assertTrue(container.getAllProgrammedFilm().containsAll(List.of(pf1, pf2, pf3)));
        container.deleteFilmProgrammation(pf3);
        assertTrue(container.getAllProgrammedFilm().containsAll(List.of(pf1, pf2)));

        assertThrows(ProgrammationNotAvailableException.class, () -> container.addFilmProgrammation(pf4));

        container.addFilmProgrammation(pf3);
        assertTrue(container.getAllProgrammedFilm().containsAll(List.of(pf1, pf2, pf3)));

        container.deleteAllFilmProgrammation(f1);
        assertTrue(container.getAllProgrammedFilm().containsAll(List.of(pf1)));
    }



}
