package reservation;
import org.junit.Test;

import controller.Reservation.ControllerReservation;
import controller.Reservation.ControllerReservationImpl;
import controller.Room.ControllerRoom;
import controller.Room.ControllerRoomImpl;
import model.Reservation.Reservation;
import model.Reservation.ReservationImpl;
import model.client.ClientImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class ControllerReservationTest {


    private ControllerReservation resContr = new ControllerReservationImpl();
    private ControllerRoom roomContr = new ControllerRoomImpl();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
 
    @Test
    public void tryUsingFormatter() throws ParseException {
        Set<Reservation> reservations = this.resContr.getAllReservation();
        for (Reservation res : reservations) {
           System.out.println(dateFormatter.format(res.getDateIn()));
           }
    }

    @Test
    public void tryWriteAndDeleteOnFile() throws ParseException {
        Date dateIn = this.dateFormatter.parse("10/12/2020");
        Date dateOut = this.dateFormatter.parse("15/12/2020");
        Reservation newRes = new ReservationImpl(new ClientImpl("Chiara", "Tozzi", "tzzchr00a50r458l"), dateIn, dateOut, this.roomContr.getRoom(1));
        resContr.addReservation(newRes);
        System.out.println("Aggiungo questa prenotazione: " + newRes.toString());
        this.resContr.removeReservation(newRes);
        System.out.println("Rimosso questa prenotazione: " + newRes.toString());
    }

    @Test
    public void tryGetAllReservation() throws ParseException {
        Set<Reservation> allReservations = this.resContr.getAllReservation();
        for (Reservation res : allReservations) {
            System.out.println(res.toString());
        }
    }
}

