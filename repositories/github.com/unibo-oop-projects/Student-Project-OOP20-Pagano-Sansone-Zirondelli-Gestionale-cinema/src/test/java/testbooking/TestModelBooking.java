package testbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.booking.BookingModel;
import modelimpl.booking.BookingModelImpl;
import modelimpl.managefilms.IdsGeneratorImpl;
import modelimpl.managefilms.ManagerIdsFilmImpl;
import utilities.Seat;
import utilities.Ticket;
import utilities.factory.Film;
import utilities.factory.FilmFactory;
import utilities.factory.ProgrammedFilm;
import utilities.factory.ProgrammedFilmFactory;
import utilitiesimpl.Hall;
import utilitiesimpl.Row;
import utilitiesimpl.SeatImpl;
import utilitiesimpl.TicketImpl;
import utilitiesimpl.factoryimpl.FilmFactoryImpl;
import utilitiesimpl.factoryimpl.ProgrammedFilmFactoryImpl;

public class TestModelBooking {

    @Test
    public void testModelgetSeatsFromProgrammedFilm() {
        final LocalDate date = LocalDate.of(2021, 8, 29);
        final LocalTime timeStart = LocalTime.of(18, 45);
        final LocalTime timeEnd = LocalTime.of(19, 45);
        final Set<Seat> setSeat = new HashSet<>();
        final Set<Seat> setSeat1 = new HashSet<>();
        final Set<Ticket> setTicket = new HashSet<>();
        final Seat seat1 = new SeatImpl(Row.C, 10);
        final Seat seat2 = new SeatImpl(Row.A, 11);
        final Seat seat3 = new SeatImpl(Row.B, 12);
        setSeat.add(seat1);
        setSeat.add(seat2);
        setSeat1.add(seat1);
        setSeat1.add(seat2);
        setSeat1.add(seat3);
        final Ticket ticket = new TicketImpl(date, timeStart, setSeat, 8.0, 1, Hall.HALL_1);
        final Ticket ticket1 = new TicketImpl(date, timeStart, setSeat1, 8.0, 2, Hall.HALL_2);
        setTicket.add(ticket);
        setTicket.add(ticket1);
        final BookingModel modelBooking = new BookingModelImpl(setTicket);
        final ProgrammedFilmFactory factoryProgrammedFilm = new ProgrammedFilmFactoryImpl();
        final ProgrammedFilm filmProgrammed = factoryProgrammedFilm.createProgrammedFilm(1, Hall.HALL_1, 8.0, date, timeStart, timeEnd);
        final Set<Seat> setSeatFilter = modelBooking.getSeatsFromProgrammedFilm(filmProgrammed);
        assertEquals(setSeat, setSeatFilter);
    }
    
    @Test
    public void testModelDeleteFilm() {
        final LocalDate date = LocalDate.of(2021, 8, 29);
        final LocalTime timeStart = LocalTime.of(18, 45);
        final Set<Seat> setSeat = new HashSet<>();
        final Set<Seat> setSeat1 = new HashSet<>();
        final Set<Ticket> setTicket = new HashSet<>();
        final Seat seat1 = new SeatImpl(Row.C, 10);
        final Seat seat2 = new SeatImpl(Row.A, 11);
        final Seat seat3 = new SeatImpl(Row.B, 12);
        setSeat.add(seat1);
        setSeat.add(seat2);
        setSeat1.add(seat1);
        setSeat1.add(seat2);
        setSeat1.add(seat3);
        final Ticket ticket = new TicketImpl(date, timeStart, setSeat, 8.0, 1, Hall.HALL_1);
        final Ticket ticket1 = new TicketImpl(date, timeStart, setSeat1, 8.0, 1, Hall.HALL_2);
        setTicket.add(ticket);
        setTicket.add(ticket1);
        final BookingModel modelBooking = new BookingModelImpl(setTicket);
        final FilmFactory factoryFilm = new FilmFactoryImpl(new ManagerIdsFilmImpl(new IdsGeneratorImpl()));
        final Film film = factoryFilm.createBasicFilmById("Holly", "action", "Good", Optional.empty(), 120, 1);
        modelBooking.deleteTicket(film);
        assertEquals(new HashSet<>(), modelBooking.getTicket());
    }
    @Test
    public void testModelDeleteProgrammedFilm() {
        final LocalDate date = LocalDate.of(2021, 8, 29);
        final LocalTime timeStart = LocalTime.of(18, 45);
        final LocalTime timeEnd = LocalTime.of(19, 45);
        final Set<Seat> setSeat = new HashSet<>();
        final Set<Seat> setSeat1 = new HashSet<>();
        final Set<Ticket> setTicket = new HashSet<>();
        final Seat seat1 = new SeatImpl(Row.C, 10);
        final Seat seat2 = new SeatImpl(Row.A, 11);
        final Seat seat3 = new SeatImpl(Row.B, 12);
        setSeat.add(seat1);
        setSeat.add(seat2);
        setSeat1.add(seat1);
        setSeat1.add(seat2);
        setSeat1.add(seat3);
        final Ticket ticket = new TicketImpl(date, timeStart, setSeat, 8.0, 1, Hall.HALL_1);
        final Ticket ticket1 = new TicketImpl(date, timeStart, setSeat1, 8.0, 2, Hall.HALL_2);
        setTicket.add(ticket);
        setTicket.add(ticket1);
        final BookingModel modelBooking = new BookingModelImpl(setTicket);
        final ProgrammedFilmFactory factoryProgrammedFilm = new ProgrammedFilmFactoryImpl();
        final ProgrammedFilm filmProgrammed = factoryProgrammedFilm.createProgrammedFilm(1, Hall.HALL_1, 8.0, date, timeStart, timeEnd);
        modelBooking.deleteTicket(filmProgrammed);
        assertEquals(ticket1, modelBooking.getTicket().stream().findAny().get());
    }
}
