package testsmanagefilms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.managefilms.ContainerFilmsModel;
import model.managefilms.ManagerIdsFilms;
import modelimpl.managefilms.ContainerFilmsModelImpl;
import modelimpl.managefilms.IdsGeneratorImpl;
import modelimpl.managefilms.ManagerIdsFilmImpl;
import utilities.factory.Film;
import utilities.factory.FilmFactory;
import utilitiesimpl.factoryimpl.FilmFactoryImpl;



class TestFilm {

    @Test
    void testManagerIds() {
        final ManagerIdsFilms manager = new ManagerIdsFilmImpl(new IdsGeneratorImpl());
        assertTrue(manager.getLastGeneratedId().isEmpty());
        assertEquals(manager.getNewFilmID(), 1);
        assertEquals(manager.getLastGeneratedId(), Optional.of(1));
        assertEquals(manager.getNewFilmID(), 2);
        assertEquals(manager.getNewFilmID(), 3);
        assertTrue(List.of(1, 2, 3).containsAll(manager.getUsedIDs()));
        manager.removeFilmId(2);
        assertTrue(List.of(1, 3).containsAll(manager.getUsedIDs()));
        manager.getNewFilmID();
        assertTrue(List.of(1, 3, 4).containsAll(manager.getUsedIDs()));

    }
    @Test
    void testContainerFilms() {
        final ContainerFilmsModel container = new ContainerFilmsModelImpl();
        final ManagerIdsFilms manager = new ManagerIdsFilmImpl(new IdsGeneratorImpl());
        final FilmFactory factory = new FilmFactoryImpl(manager);

        final Film f1 = factory.createBasicFilm("Name1", "Genre1", "Description1", Optional.empty(), 120);
        final Film f2 = factory.createBasicFilm("Name2", "Genre2", "Description2", Optional.empty(), 160);
        final Film f3 = factory.createBasicFilm("Name3", "Genre3", "Description3", Optional.empty(), 100);

        container.addFilm(f1); 
        container.addFilm(f2);
        container.addFilm(f3);

        assertTrue(container.getFilms().containsAll(Set.of(f1, f2, f3)));

        container.removeFilm(f3);
        assertTrue(container.getFilms().containsAll(Set.of(f1, f2)));
    }

}
