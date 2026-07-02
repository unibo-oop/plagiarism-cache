package controller.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import controller.Client.ControllerClient;
import controller.Client.ControllerClientImpl;
import controller.Room.ControllerRoom;
import controller.Room.ControllerRoomImpl;
import model.Reservation.Reservation;
import model.Reservation.ReservationImpl;
import model.client.Client;
import model.file.MyFile;
import model.file.MyFileImpl;
import model.room.Room;


public class ControllerReservationImpl implements ControllerReservation {
    private static final String SEP = System.getProperty("file.separator");
    private static final String DIRUSER = System.getProperty("user.home");
    private static final String NAMEFILE = ("Reservation.txt");
    private static final String PATH = (DIRUSER + SEP + NAMEFILE);
    private MyFile fileManager;
    private final ControllerClient clientController = new ControllerClientImpl();
    private final ControllerRoom roomController = new ControllerRoomImpl();
    private Set<Reservation> allReservation = new HashSet<>();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public ControllerReservationImpl() {
        this.fileManager =  new MyFileImpl(PATH);
        this.getAllReservation();

 
    }

    private Set<String> readReservation() {
        Set<String> reservations = this.fileManager.fileReader();
        return reservations;
    }

    private void writeReservation(final Reservation reservation) {
        this.fileManager.fileWriter(reservation.toString());
    }

    private void deleteReservation(final Reservation reservation) {
        this.fileManager.deleteline(reservation.toString());
    }

    private void createReservationFromString(final Set<String> reservationsStrings) {
        for (String s : reservationsStrings) {
            String[] parts = s.split(Pattern.quote("."));
            Optional<Client> client = Optional.of(clientController.getClient(parts[0]));
            Date dateIn;
            Date dateOut;

            try {
                if (client.isEmpty()) {
                    System.out.println("Cliente non trovato.");
                    throw new IllegalStateException();
                }
            } catch (IllegalStateException exception) {
                System.out.println("Il cliente non è inserito nella lista clienti."
                        + " Prima di procedere inserire il cliente");
                break;
            }
            try {
                dateIn = dateFormatter.parse(parts[1]);
                dateOut = dateFormatter.parse(parts[2]);
            } catch (ParseException e) {
                System.out.println("ERRORE NEL PARSING DELLE DATE createReservationFromString");
                e.printStackTrace();
                break;
            }
            Room room = roomController.getRoom(Integer.parseInt(parts[3]));
            Reservation res = new ReservationImpl(client.get(), dateIn, dateOut, room);
            this.allReservation.add(res);
        }
    }

    @Override
    public final Set<Reservation> getAllReservation() {
        this.allReservation.clear();
        Set<String> reservations = this.readReservation();
        this.createReservationFromString(reservations);
        return this.allReservation;
    }

    @Override
    public final void addReservation(final Reservation reservation) {
        this.allReservation.add(reservation);
        this.writeReservation(reservation);
    }

    public final void addReservation(final String cf, final Date dateIn, final Date dateOut, final int roomNumber) {
        Client client = this.clientController.getClient(cf);
        Room room = this.roomController.getRoom(roomNumber);
        Reservation newReservation = new ReservationImpl(client, dateIn, dateOut, room);
        this.allReservation.add(newReservation);
        this.writeReservation(newReservation);
    }

    @Override
    public final void removeReservation(final Reservation reservation) {
        this.allReservation.remove(reservation);
        this.deleteReservation(reservation);
    }

    @Override
    public final void removeReservation(final String cf, final Date dateIn, final Date dateOut, final int roomNumber) {
        Client client = this.clientController.getClient(cf);
        Room room = this.roomController.getRoom(roomNumber);
        Reservation newReservation = new ReservationImpl(client, dateIn, dateOut, room);
        this.deleteReservation(newReservation);
    }
}
