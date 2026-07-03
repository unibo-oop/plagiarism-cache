package model.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Optional;

import model.enumerations.TravelClass;
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
 * 
 * Implements the airport.
 */
public final class AirportImpl implements Airport {

    private static final AirportImpl SINGLETON = new AirportImpl();
    private static final double BUSINESS_CLASS_PERCENT = 1.1;
    private static final double FIRST_CLASS_PERCENT = 1.3;
    private static final double AGE_PERCENT = 0.6;
    private static final int AGE = 12;
    private static final double WEIGHT_PERCENT = 1.1;
    private static final int BASIC_WEIGHT = 10;

    private final List<Staff> staffMembers = new ArrayList<>();
    private final List<Plane> planes = new ArrayList<>();
    private final List<Destination> destinations = new ArrayList<>();
    private final List<Pilot> pilots = new ArrayList<>();
    private final List<FlightAttendant> flightAttendants = new ArrayList<>();
    private final List<Booking> reservations = new ArrayList<>();
    private final Map<Flight, Pair<Luggage, Passenger>> luggage = new HashMap<>();
    private final Map<Flight, Pair<Pilot, Pilot>> flights = new HashMap<>();

    private AirportImpl() { }

    /**
     * 
     * @return the airport
     */
    public static AirportImpl getAirport() {
        return SINGLETON;
    }

    @Override
    public void addStaff(final Staff s) {
        this.staffMembers.add(s);
    }

    @Override
    public void removeStaff(final Staff s) {
        this.staffMembers.remove(s);
    }

    @Override
    public Optional<Staff> searchStaff(final String username) {
        for (final Staff s : this.staffMembers) {
            if (s.getUsername().equals(username)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Staff> getStaff() {
        return Collections.unmodifiableList(this.staffMembers);
    }

    @Override
    public void addPlane(final Plane p) {
        this.planes.add(p);
    }

    @Override
    public void removePlane(final Plane p) {
        this.planes.remove(p);
    }

    @Override
    public Optional<Plane> searchPlane(final String planeId) {
        for (final Plane p : this.planes) {
            if (p.getPlaneId().equals(planeId)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Plane> getPlanes() {
        return Collections.unmodifiableList(this.planes);
    }

    @Override
    public void addDestination(final Destination d) {
        this.destinations.add(d);
    }

    @Override
    public void removeDestination(final Destination d) {
        this.destinations.remove(d);
    }

    @Override
    public Optional<Destination> searchDestination(final String destId) {
        for (final Destination d : this.destinations) {
            if (d.getDestinationId().equals(destId)) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Destination> getDestinations() {
        return Collections.unmodifiableList(this.destinations);
    }

    @Override
    public void addPilot(final Pilot p) {
        this.pilots.add(p);
    }

    @Override
    public void removePilot(final Pilot p) {
        this.pilots.remove(p);
    }

    @Override
    public Optional<Pilot> searchPilot(final String pilotId) {
        for (final Pilot p : this.pilots) {
            if (p.getPilotId().equals(pilotId)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Pilot> getPilots() {
        return Collections.unmodifiableList(this.pilots);
    }

    @Override
    public void addFlightAttendant(final FlightAttendant fa) {
        this.flightAttendants.add(fa);
    }

    @Override
    public void removeFlightAttendant(final FlightAttendant fa) {
        this.flightAttendants.remove(fa);
    }

    @Override
    public Optional<FlightAttendant> searchFlightAttendant(final String flightAttendantId) {
        for (final FlightAttendant fa : this.flightAttendants) {
            if (fa.getFlightAttendantId().equals(flightAttendantId)) {
                return Optional.of(fa);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<FlightAttendant> getFlightAttendants() {
        return Collections.unmodifiableList(this.flightAttendants);
    }

    @Override
    public void addReservation(final Booking b) {
        this.reservations.add(b);
    }

    @Override
    public void removeReservation(final Booking b) {
        this.reservations.remove(b);
    }

    @Override
    public Optional<Booking> searchReservation(final String bookingId) {
        for (final Booking b : this.reservations) {
            if (b.getBookingId().equals(bookingId)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Booking> getReservations() {
        return Collections.unmodifiableList(this.reservations);
    }

    @Override
    public void addLuggage(final Flight f, final Luggage l, final Passenger p) {
        this.luggage.put(f, new Pair<>(l, p));
    }

    @Override
    public void removeLuggage(final Luggage l) {
        for (final Entry<Flight, Pair<Luggage, Passenger>> e : this.luggage.entrySet()) {
            if (e.getValue().getFirst().equals(l)) {
                this.luggage.remove(e.getKey());
                break;
            }
        }
    }

    @Override
    public Optional<Pair<Flight, Pair<Luggage, Passenger>>> searchLuggage(final String luggageId) {
        for (final Entry<Flight, Pair<Luggage, Passenger>> e : this.luggage.entrySet()) {
            if (e.getValue().getFirst().getLuggageId().equals(luggageId)) {
                return Optional.of(new Pair<>(e.getKey(), e.getValue()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<Flight, Pair<Luggage, Passenger>> getLuggage() {
        return Collections.unmodifiableMap(this.luggage);
    }

    @Override
    public void addFlight(final Flight f, final Pilot p, final Pilot c) {
        this.flights.put(f, new Pair<>(p, c));
    }

    @Override
    public void removeFlight(final Flight f) {
        this.flights.remove(f);
    }

    @Override
    public Optional<Pair<Flight, Pair<Pilot, Pilot>>> searchFlight(final String flightId) {
        for (final Entry<Flight, Pair<Pilot, Pilot>> e : this.flights.entrySet()) {
            if (e.getKey().getFlightId().equals(flightId)) {
                return Optional.of(new Pair<>(e.getKey(), e.getValue()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<Flight, Pair<Pilot, Pilot>> getFlights() {
        return Collections.unmodifiableMap(this.flights);
    }

    @Override
    public void calculateFinalPrice(final Booking b, final Luggage l) {
        double price = b.getFlight().getBasicPrice();
        if (b.getTravelClass() == TravelClass.BUSINESS) {
            price *= BUSINESS_CLASS_PERCENT;
        } else if (b.getTravelClass() == TravelClass.FIRST) {
            price *= FIRST_CLASS_PERCENT;
        }
        if (b.getPassenger().getAge() <= AGE) {
            price *= AGE_PERCENT;
        }
        if (l.getWeight() >= BASIC_WEIGHT) {
            price *= WEIGHT_PERCENT;
        }
        b.setFinalPrice(price);
    }

}
