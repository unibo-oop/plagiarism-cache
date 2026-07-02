package controllerimpl.managefilms;

import java.util.Optional;
import java.util.Set;
import com.google.gson.reflect.TypeToken;

import controller.CinemaController;
import controller.booking.BookingController;
import controller.inputoutput.RWobject;
import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import controllerimpl.booking.BookingControllerImpl;
import controllerimpl.inputoutput.RWobjectImpl;
import controllerimpl.manageprogrammingfilms.ProgrammingFilmsControllerImpl;
import model.managefilms.ContainerFilmsModel;
import model.managefilms.ManagerIdsFilms;
import modelimpl.managefilms.ContainerFilmsModelImpl;
import utilities.ManagerWorkingDir;
import utilities.factory.Film;
import utilitiesimpl.GeneralSettings;
import utilitiesimpl.ManagerWorkingDirImpl;
import view.managefilms.ContainerFilmsView;
import view.managefilms.InfoFilmsView;
import viewimpl.managefilms.ContainerFilmsViewImpl;
import viewimpl.managefilms.InfoFilmsViewImpl;

/**
 * Describes controller to manage films. Operation can be : delete, add, modify
 */
public final class FilmsControllerImpl implements FilmsController {

    private final ContainerFilmsModel model;
    private final ContainerFilmsView viewFilms;
    private final InfoFilmsView infoFilms;
    private final ManagerWorkingDir managerWorkingDIR;
    private ProgrammingFilmsController programmingFilmsController;
    private CinemaController cinemaController;

    public FilmsControllerImpl() { // must be invoked on the first use of application

        final Optional<Set<Film>> films = this.readFilmsFromFile();
        final Optional<ManagerIdsFilms> managerIdsFilm = this.readManagerIdsFilmsFromFile();
        if (films.isEmpty() || managerIdsFilm.isEmpty()) {
            model = new ContainerFilmsModelImpl();
        } else {
            model = new ContainerFilmsModelImpl(films.get(), managerIdsFilm.get());
        }

        viewFilms = new ContainerFilmsViewImpl(); // Empty set, there aren't films
        infoFilms = new InfoFilmsViewImpl();
        managerWorkingDIR = new ManagerWorkingDirImpl();
        programmingFilmsController = new ProgrammingFilmsControllerImpl(this);

        this.viewFilms.setObserver(this);
        this.infoFilms.setObserver(this);
    }

    public FilmsControllerImpl(final ProgrammingFilmsController programmingFilmsController) { // must be invoked on the
                                                                                              // first use of
                                                                                              // application

        final Optional<Set<Film>> films = this.readFilmsFromFile();
        final Optional<ManagerIdsFilms> managerIdsFilm = this.readManagerIdsFilmsFromFile();

        if (films.isEmpty() || managerIdsFilm.isEmpty()) {
            model = new ContainerFilmsModelImpl();
        } else {
            model = new ContainerFilmsModelImpl(films.get(), managerIdsFilm.get());
        }

        viewFilms = new ContainerFilmsViewImpl(); // Empty set, there aren't films
        infoFilms = new InfoFilmsViewImpl();
        managerWorkingDIR = new ManagerWorkingDirImpl();
        this.programmingFilmsController = programmingFilmsController;
        this.viewFilms.setObserver(this);
        this.infoFilms.setObserver(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addFilm(final Film f) {
        this.model.addFilm(f);
        this.writeFilmsOnFile();
        this.writeManagerIdsFilmsOnFile();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFilm(final Film f) {
        final BookingController bookignController = new BookingControllerImpl();
        this.model.removeFilm(f);
        this.writeFilmsOnFile();
        this.writeManagerIdsFilmsOnFile();
        bookignController.deleteTicket(f);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Film> getFilms() {
        return this.model.getFilms();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        cinemaController.showMenu();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showContainerFilmsView() {
        viewFilms.update();
        viewFilms.start();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showInfoFilmView(final Film f) {
        infoFilms.start();
        infoFilms.loadFilm(f);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showNewFilmView() {
        infoFilms.reset();
        infoFilms.start();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ManagerWorkingDir getManagerWorkingDIR() {
        return this.managerWorkingDIR;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ManagerIdsFilms getManagerIdsFilms() {
        return model.getManagerIdsFilms();
    }

    private void writeFilmsOnFile() {
        final RWobject<Set<Film>> rw = new RWobjectImpl<>(GeneralSettings.FILMSPATH);
        final var type = new TypeToken<Set<Film>>() {
        }.getType();
        rw.writeObj(model.getFilms(), type);
    }

    private void writeManagerIdsFilmsOnFile() {
        final RWobject<ManagerIdsFilms> rw = new RWobjectImpl<>(GeneralSettings.MANAGERIDSFILMSPATH);
        final var type = new TypeToken<ManagerIdsFilms>() {
        }.getType();
        rw.writeObj(model.getManagerIdsFilms(), type);
    }

    private Optional<Set<Film>> readFilmsFromFile() {
        final RWobject<Set<Film>> rw = new RWobjectImpl<>(GeneralSettings.FILMSPATH);
        final var type = new TypeToken<Set<Film>>() {
        }.getType();
        return rw.readObj(type);
    }

    private Optional<ManagerIdsFilms> readManagerIdsFilmsFromFile() {
        final RWobject<ManagerIdsFilms> rw = new RWobjectImpl<>(GeneralSettings.MANAGERIDSFILMSPATH);
        final var type = new TypeToken<ManagerIdsFilms>() {
        }.getType();
        return rw.readObj(type);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFilmAndProgrammation(final Film f) {
        this.deleteFilm(f);
        this.programmingFilmsController.deleteAllFilmProgrammation(f);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setProgrammingFilmsController(final ProgrammingFilmsController programmingFilmsController) {
        this.programmingFilmsController = programmingFilmsController;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCinemaController(final CinemaController cinemaController) {
        this.cinemaController = cinemaController;
    }

}
