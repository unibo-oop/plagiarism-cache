package controllerimpl.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.gson.reflect.TypeToken;
import controller.CinemaController;
import controller.booking.BookingController;

import controller.booking.BookingViewObserver;
import controller.booking.ListFilmViewObserver;
import controller.booking.TimeTableViewObserver;
import controller.inputoutput.RWobject;
import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import controllerimpl.inputoutput.RWobjectImpl;
import controllerimpl.managefilms.FilmsControllerImpl;
import controllerimpl.manageprogrammingfilms.ProgrammingFilmsControllerImpl;
import model.booking.BookingModel;
import model.manageprogrammingfilms.Filter;
import model.manageprogrammingfilms.HandlerList;
import model.manageprogrammingfilms.Sorter;
import modelimpl.booking.BookingModelImpl;
import modelimpl.manageprogrammedfilms.HandlerListImpl;
import utilities.Seat;
import utilities.Ticket;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.GeneralSettings;
import utilitiesimpl.SeatImpl;
import view.booking.BookingView;
import view.booking.ListFilmView;
import view.booking.TimeTableView;
import viewimpl.booking.BookingViewImpl;
import viewimpl.booking.ListFilmViewImpl;
import viewimpl.booking.TimeTableViewImpl;

public class BookingControllerImpl implements BookingController, ListFilmViewObserver, TimeTableViewObserver, BookingViewObserver {
    private BookingModel modelBooking;
    private final Set<Film> setFilm;
    private final Set<ProgrammedFilm> setProgrammedFilm;
    private CinemaController observer;
    private BookingView viewBooking; 
    private TimeTableView viewTimeTable;
    private ListFilmView viewFilm;
    public BookingControllerImpl() {
        final Optional<Set<Ticket>> opSetTicket = this.readTicketOnFile();
        final FilmsController controllerFilms = new FilmsControllerImpl();
        final ProgrammingFilmsController controllerFilmProgrammed = new ProgrammingFilmsControllerImpl();

        if (opSetTicket.isEmpty()) {
            modelBooking = new BookingModelImpl(new HashSet<>());
        } else {
            modelBooking = new BookingModelImpl(opSetTicket.get());
        }
        setProgrammedFilm = new HashSet<>(controllerFilmProgrammed.getAllProgrammedFilms());
        setFilm = controllerFilms.getFilms();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.showListFilmView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void selectedFilm(final Film film) {
        final Set<ProgrammedFilm> setFiltered = setProgrammedFilm.stream()
                .filter(i -> i.getIdProgrammation() == film.getID())
                .collect(Collectors.toSet());
        this.showTimeTableView(setFiltered, film);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        this.observer.showMenu();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Film> getFilm() {
        return setFilm;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bookTicketForFilm(final ProgrammedFilm film) {
        this.showBookingView(film);
        viewBooking.refresh();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Film getFilmByProgrammedFilm(final ProgrammedFilm film) {
        return setFilm.stream().filter(f -> f.getID() == film.getIdProgrammation()).findAny().get();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showBackFromTimeTable() {
        this.showListFilmView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showBackFromBooking(final ProgrammedFilm programmedFilm) {
        final Set<ProgrammedFilm> setPF = setProgrammedFilm.stream()
                .filter(i -> i.getIdProgrammation() == programmedFilm.getIdProgrammation())
                .collect(Collectors.toSet());
        final Film film = setFilm.stream().filter(f -> f.getID() == programmedFilm.getIdProgrammation())
                .findAny()
                .get();
        this.showTimeTableView(setPF, film);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Seat> getSeatsFromProgrammedFilm(final ProgrammedFilm film) {
        return this.modelBooking.getSeatsFromProgrammedFilm(film);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bookSeat(final ProgrammedFilm film) {
        modelBooking.bookSeat(film);
        this.writeTicketOnFile(modelBooking.getTicket());
        viewBooking.refresh();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void buttonSelected(final SeatImpl seat, final ProgrammedFilm film) {
        modelBooking.buttonSelected(seat, film);
        viewBooking.refresh();
    }

    @Override
    public void newBooking() {
        modelBooking.newBooking();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Seat> getSeatsSelected() {
        return modelBooking.getSeatsSelected();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProgrammedFilm> handlerProgrammedFilm(final Collection<ProgrammedFilm> coll, final Filter<ProgrammedFilm> filter) {
        final HandlerList<ProgrammedFilm> handler = new HandlerListImpl<>();
        List<ProgrammedFilm> listFilm = new ArrayList<>(coll);
        listFilm = handler.filterBy(listFilm, filter);
        return listFilm;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProgrammedFilm> handlerProgrammedFilm(final Collection<ProgrammedFilm> coll, final Sorter<ProgrammedFilm> sorter) {
        final HandlerList<ProgrammedFilm> handler = new HandlerListImpl<>();
        List<ProgrammedFilm> listFilm = new ArrayList<>(coll);
        listFilm = handler.sortBy(listFilm, sorter);
        return listFilm;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ticket> getTicket() {
        return modelBooking.getTicket();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTicket(final Film film) {
        this.modelBooking.deleteTicket(film);
        this.writeTicketOnFile(modelBooking.getTicket());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTicket(final ProgrammedFilm programmedFilm) {
        this.modelBooking.deleteTicket(programmedFilm);
        this.writeTicketOnFile(modelBooking.getTicket());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCinemaController(final CinemaController observer) {
        this.observer = observer;
    }
    /**
     * Method to write set of ticket on file.
     * @param set of ticket to write
     */
    private void writeTicketOnFile(final Set<Ticket> set) {
        final Set<Ticket> setToWrite = set;
        final var type = new TypeToken<Set<Ticket>>() {
        }.getType();
        final RWobject<Set<Ticket>> rw = new RWobjectImpl<>(GeneralSettings.TICKET_FILE_PATH);
        rw.writeObj(setToWrite, type);
    }
    /**
     * Read ticket on file.
     * @return an optional of set of ticket
     */
    private Optional<Set<Ticket>> readTicketOnFile() {
        final RWobject<Set<Ticket>> rw = new RWobjectImpl<>(GeneralSettings.TICKET_FILE_PATH);
        final var type = new TypeToken<Set<Ticket>>() {
        }.getType();
        return rw.readObj(type);
    }
    /**
     * Show ListFilmView and used checkEmpyFilm after show.
     */
    private void showListFilmView() {
        this.viewFilm = new ListFilmViewImpl(this);
        viewFilm.show();
        viewFilm.checkEmptyFilm();
    }
    /**
     * Show TimeTableView for a specific set of programmed film; film used to take general info.
     * @param setProgrammedFilm to show 
     * @param film used to take general info
     */
    private void showTimeTableView(final Set<ProgrammedFilm> setProgrammedFilm, final Film film) {
        this.viewTimeTable = new TimeTableViewImpl(this, setProgrammedFilm, film);
        viewTimeTable.show();
        viewTimeTable.checkEmptyProgrammation(setProgrammedFilm);
    }
    /**
     * Show BookingView for a specific film programmed.
     * @param film programmed used to book seat
     */
    private void showBookingView(final ProgrammedFilm programmedFilm) {
        this.viewBooking = new BookingViewImpl(this, programmedFilm);
        viewBooking.show();
    }
}
