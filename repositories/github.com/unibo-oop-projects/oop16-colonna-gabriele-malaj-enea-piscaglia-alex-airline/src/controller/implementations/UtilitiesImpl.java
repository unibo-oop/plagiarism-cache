package controller.implementations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map.Entry;

import controller.interfaces.Utilities;
import model.implementations.AirportImpl;
import model.implementations.IdGeneratorImpl;
import model.implementations.Pair;
import model.interfaces.Airport;
import model.interfaces.Booking;
import model.interfaces.Destination;
import model.interfaces.Flight;
import model.interfaces.FlightAttendant;
import model.interfaces.Luggage;
import model.interfaces.Passenger;
import model.interfaces.Pilot;
import model.interfaces.Plane;
import model.interfaces.Staff;

/**
 * Utility class.
 */
public final class UtilitiesImpl implements Utilities {

    private static final String ID = "res/Id.txt";
    private static final String STAFF = "res/Staff.txt";
    private static final String PLANES = "res/Planes.txt";
    private static final String DEST = "res/Destinations.txt";
    private static final String PILOTS = "res/Pilots.txt";
    private static final String FLIGHT_ATT = "res/FlightAttendants.txt";
    private static final String LUGGAGE = "res/Luggage.txt";
    private static final String FLIGHTS = "res/Flights.txt";
    private static final String RESERVATIONS = "res/Reservations.txt";
    private static final UtilitiesImpl SINGLETON = new UtilitiesImpl();

    private final Airport airport = AirportImpl.getAirport();

    private UtilitiesImpl() {
    }

    /**
     * @return an instance of utilities implementation.
     */
    public static UtilitiesImpl getInstance() {
        return SINGLETON;
    }

    @Override
    public void saveData() throws IOException {
        this.saveId();
        this.saveStaff();
        this.savePlanes();
        this.saveDestinations();
        this.savePilots();
        this.saveFlightAttendants();
        this.saveLuggage();
        this.saveFlights();
        this.saveReservations();
    }

    @Override
    public void loadData() throws IOException, ClassNotFoundException {
        if (this.airport.getStaff().size() == 0) {
            this.loadId();
            this.loadStaff();
            this.loadPlanes();
            this.loadDestinations();
            this.loadPilots();
            this.loadFlightAttendants();
            this.loadLuggage();
            this.loadFlights();
            this.loadReservations();
        }
    }

    @Override
    public void saveId() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(ID)));

        oos.writeChar(IdGeneratorImpl.getIdGenerator().getLetter());
        oos.writeInt(IdGeneratorImpl.getIdGenerator().getCounter());
        oos.flush();
        oos.close();
    }

    private void loadId() throws IOException {
        final ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(ID)));

        IdGeneratorImpl.getIdGenerator().setInitialLetter(ois.readChar());
        IdGeneratorImpl.getIdGenerator().setInitialNumber(ois.readInt());
        ois.close();
    }

    @Override
    public void saveStaff() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(STAFF)));

        oos.writeInt(this.airport.getStaff().size());

        for (final Staff s : this.airport.getStaff()) {
            oos.writeObject(s);
        }
        oos.close();
    }

    private void loadStaff() throws IOException, ClassNotFoundException {
        final File f = new File(STAFF);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addStaff((Staff) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void savePlanes() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(PLANES)));

        oos.writeInt(this.airport.getPlanes().size());

        for (final Plane p : this.airport.getPlanes()) {
            oos.writeObject(p);
        }
        oos.close();
    }

    private void loadPlanes() throws IOException, ClassNotFoundException {
        final File f = new File(PLANES);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addPlane((Plane) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void saveDestinations() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(DEST)));

        oos.writeInt(this.airport.getDestinations().size());

        for (final Destination d : this.airport.getDestinations()) {
            oos.writeObject(d);
        }
        oos.close();
    }

    private void loadDestinations() throws IOException, ClassNotFoundException {
        final File f = new File(DEST);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addDestination((Destination) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void savePilots() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(PILOTS)));

        oos.writeInt(this.airport.getPilots().size());

        for (final Pilot p : this.airport.getPilots()) {
            oos.writeObject(p);
        }
        oos.close();
    }

    private void loadPilots() throws IOException, ClassNotFoundException {
        final File f = new File(PILOTS);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addPilot((Pilot) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void saveFlightAttendants() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(FLIGHT_ATT)));

        oos.writeInt(this.airport.getFlightAttendants().size());

        for (final FlightAttendant fa : this.airport.getFlightAttendants()) {
            oos.writeObject(fa);
        }
        oos.close();
    }

    private void loadFlightAttendants() throws IOException, ClassNotFoundException {
        final File f = new File(FLIGHT_ATT);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addFlightAttendant((FlightAttendant) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void saveLuggage() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(LUGGAGE)));

        oos.writeInt(this.airport.getLuggage().size());

        for (final Entry<Flight, Pair<Luggage, Passenger>> e : this.airport.getLuggage().entrySet()) {
            oos.writeObject(e.getKey());
            oos.writeObject(e.getValue().getFirst());
            oos.writeObject(e.getValue().getSecond());
        }
        oos.close();
    }

    private void loadLuggage() throws IOException, ClassNotFoundException {
        final File f = new File(LUGGAGE);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addLuggage((Flight) ois.readObject(), 
                        (Luggage) ois.readObject(), (Passenger) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void saveFlights() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(FLIGHTS)));

        oos.writeInt(this.airport.getFlights().size());

        for (final Entry<Flight, Pair<Pilot, Pilot>> e : this.airport.getFlights().entrySet()) {
            oos.writeObject(e.getKey());
            oos.writeObject(e.getValue().getFirst());
            oos.writeObject(e.getValue().getSecond());
        }
        oos.close();
    }

    private void loadFlights() throws IOException, ClassNotFoundException {
        final File f = new File(FLIGHTS);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addFlight((Flight) ois.readObject(), 
                        (Pilot) ois.readObject(), (Pilot) ois.readObject());
            }
            ois.close();
        }
    }

    @Override
    public void saveReservations() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(RESERVATIONS)));

        oos.writeInt(this.airport.getReservations().size());

        for (final Booking b : this.airport.getReservations()) {
            oos.writeObject(b);
        }
        oos.close();
    }

    private void loadReservations() throws IOException, ClassNotFoundException {
        final File f = new File(RESERVATIONS);

        if (f.length() != 0) {
            final ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(f)));
            final int size = ois.readInt();

            for (int i = 0; i < size; i++) {
                this.airport.addReservation((Booking) ois.readObject());
            }
            ois.close();
        }
    }

}
