package controllerimpl.manageprogrammingfilms;

import java.util.List;
import java.util.Optional;

import com.google.gson.reflect.TypeToken;

import controller.CinemaController;
import controller.booking.BookingController;
import controller.inputoutput.RWobject;
import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import controllerimpl.booking.BookingControllerImpl;
import controllerimpl.inputoutput.RWobjectImpl;
import controllerimpl.managefilms.FilmsControllerImpl;
import exceptions.ProgrammationNotAvailableException;
import model.manageprogrammingfilms.ManagerProgrammingFilms;
import model.manageprogrammingfilms.ProgrammedFilmsModel;
import modelimpl.manageprogrammedfilms.ProgrammedFilmsModelImpl;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.GeneralSettings;
import view.manageprogrammingfilms.ProgrammingFilmsView;
import view.manageprogrammingfilms.ScheduleFilmsView;
import viewimpl.manageprogrammingfilms.ProgrammingFilmsViewImpl;
import viewimpl.manageprogrammingfilms.ScheduleFilmViewImpl;
/**
 * This controller manages films programmation.
 *  */
public final class ProgrammingFilmsControllerImpl implements ProgrammingFilmsController {

    private final ProgrammingFilmsView filmsProgrammationView;
    private ScheduleFilmsView scheduleFilmView;
    private final ProgrammedFilmsModel programmedFilmsModel;
    private FilmsController filmsController;
    private CinemaController cinemaController;

    public ProgrammingFilmsControllerImpl() {
        filmsController = new FilmsControllerImpl(this);
        final Optional<List<ProgrammedFilm>> programmedFilms = this.readProgrammedFilmsFromFile();

        if (programmedFilms.isEmpty()) {
            programmedFilmsModel = new ProgrammedFilmsModelImpl();
        } else {
            programmedFilmsModel = new ProgrammedFilmsModelImpl(programmedFilms.get());
        }
        filmsProgrammationView = new ProgrammingFilmsViewImpl(); 
        filmsProgrammationView.setFilmsController(filmsController);
        filmsProgrammationView.setObserver(this);
        scheduleFilmView = new ScheduleFilmViewImpl(filmsController);
        scheduleFilmView.setObserver(this);
    }
    public ProgrammingFilmsControllerImpl(final FilmsController filmsController) {
        this.filmsController = filmsController;
        final Optional<List<ProgrammedFilm>> programmedFilms = this.readProgrammedFilmsFromFile();

        if (programmedFilms.isEmpty()) {
            programmedFilmsModel = new ProgrammedFilmsModelImpl();
        } else {
            programmedFilmsModel = new ProgrammedFilmsModelImpl(programmedFilms.get());
        }
        filmsProgrammationView = new ProgrammingFilmsViewImpl(); 
        filmsProgrammationView.setFilmsController(filmsController);
        filmsProgrammationView.setObserver(this);
        scheduleFilmView = new ScheduleFilmViewImpl(filmsController);
        scheduleFilmView.setObserver(this);
    }

    /** 
     * {@inheritDoc}
     * */
    @Override
    public List<ProgrammedFilm> getAllProgrammedFilms() {
        return programmedFilmsModel.getAllProgrammedFilm();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void addProgrammedFilm(final ProgrammedFilm newProgrammedFilm) throws ProgrammationNotAvailableException {
        programmedFilmsModel.addFilmProgrammation(newProgrammedFilm);
        this.writeProgrammedFilmsOnFile();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void deleteProgrammedFilm(final ProgrammedFilm oldProgrammedFilm) {
        final BookingController bookingController = new BookingControllerImpl();
        programmedFilmsModel.deleteFilmProgrammation(oldProgrammedFilm);
        this.writeProgrammedFilmsOnFile();
        bookingController.deleteTicket(oldProgrammedFilm);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void showProgrammedFilmView() {
        filmsProgrammationView.update();
        filmsProgrammationView.start();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void showMenu() {
        cinemaController.showMenu();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void showScheduleFilmView() {
        scheduleFilmView.start();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public ManagerProgrammingFilms getManagerProgrammingFilms() {
        return this.programmedFilmsModel.getManagerProgrammingFilms();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public FilmsController getFilmsController() {
        return this.filmsController;
    }

    /**
     * Write all programmed films on file where there are some changes.
     */
    private void writeProgrammedFilmsOnFile() {
        final RWobject<List<ProgrammedFilm>> rw = new RWobjectImpl<>(GeneralSettings.PROGRAMMEDFILMSPATH);
        final var type = new TypeToken<List<ProgrammedFilm>>() { }.getType();
        rw.writeObj(programmedFilmsModel.getAllProgrammedFilm(), type);
    }
    /**
     * Read, if there's data, all programmed films from file. Otherwise return an empty optional.
     * @return list of programmed films
     */
    private Optional<List<ProgrammedFilm>> readProgrammedFilmsFromFile() {
        final RWobject<List<ProgrammedFilm>> rw = new RWobjectImpl<>(GeneralSettings.PROGRAMMEDFILMSPATH);
        final var type = new TypeToken<List<ProgrammedFilm>>() { }.getType();
        return rw.readObj(type);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void deleteAllFilmProgrammation(final Film film) {
        this.programmedFilmsModel.deleteAllFilmProgrammation(film);
        this.writeProgrammedFilmsOnFile();
    }

    /** 
     * {@inheritDoc}
     * */
    @Override
    public void update() {
        filmsProgrammationView.update();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void setFilmsController(final FilmsController filmsController) {
        this.filmsController = filmsController;
        filmsProgrammationView.setFilmsController(filmsController);
        this.initScheduleGUI();
    }
    /** 
     * Complete initialization of programming films controller. It creates a new schedule film view and sets itself as observer of this gui.
     * */
    private void initScheduleGUI() {
        scheduleFilmView = new ScheduleFilmViewImpl(this.filmsController);
        scheduleFilmView.setObserver(this);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void setCinemaController(final CinemaController cinemaController) {
        this.cinemaController = cinemaController;
    }


}
