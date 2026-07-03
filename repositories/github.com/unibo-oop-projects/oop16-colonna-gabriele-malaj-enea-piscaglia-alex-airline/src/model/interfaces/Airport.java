package model.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.implementations.Pair;

/**
 * 
 * Represents the airport.
 */
public interface Airport {

    /**
     * Adds a staff member to the staff list.
     * 
     * @param s    a staff member
     */
    void addStaff(Staff s);

    /**
     * Removes a staff member from the staff list.
     * 
     * @param s    a staff member
     */
    void removeStaff(Staff s);

    /**
     * Searches for a staff member within the staff list.
     * 
     * @param username    the staff member's username
     * 
     * @return the searched staff member, if exists
     */
    Optional<Staff> searchStaff(String username);

    /**
     * 
     * @return the protective copy of the staff list
     */
    List<Staff> getStaff();

    /**
     * Adds a plane to the list of planes.
     * 
     * @param p    a plane
     */
    void addPlane(Plane p);

    /**
     * Removes a plane from the list of planes.
     * 
     * @param p    a plane
     */
    void removePlane(Plane p);

    /**
     * Searches for a plane within the list of planes.
     * 
     * @param planeId    the plane identifier
     * 
     * @return the searched plane, if exists
     */
    Optional<Plane> searchPlane(String planeId);

    /**
     * 
     * @return the protective copy of the list of planes
     */
    List<Plane> getPlanes();

    /**
     * Adds a destination to the list of destinations.
     * 
     * @param d    a destination
     */
    void addDestination(Destination d);

    /**
     * Removes a destination from the list of destinations.
     * 
     * @param d    a destination
     */
    void removeDestination(Destination d);

    /**
     * Searches for a destination within the list of destinations.
     * 
     * @param destId    the destination identifier
     * 
     * @return the searched destination, if exists
     */
    Optional<Destination> searchDestination(String destId);

    /**
     * 
     * @return the protective copy of the list of destinations
     */
    List<Destination> getDestinations();

    /**
     * Adds a pilot to the list of pilots.
     * 
     * @param p    a pilot
     */
    void addPilot(Pilot p);

    /**
     * Removes a pilot from the list of pilots.
     * 
     * @param p    a pilot
     */
    void removePilot(Pilot p);

    /**
     * Searches for a pilot within the list of pilots.
     * 
     * @param pilotId    the pilot identifier
     * 
     * @return the searched pilot, if exists
     */
    Optional<Pilot> searchPilot(String pilotId);

    /**
     * 
     * @return the protective copy of the list of pilots
     */
    List<Pilot> getPilots();

    /**
     * Adds a flight attendant to the list of flight attendants.
     * 
     * @param fa    a flight attendant
     */
    void addFlightAttendant(FlightAttendant fa);

    /**
     * Removes a flight attendant from the list of flight attendants.
     * 
     * @param fa    a flight attendant
     */
    void removeFlightAttendant(FlightAttendant fa);

    /**
     * Searches for a flight attendant within the list of flight attendants.
     * 
     * @param flightAttendantId    the flight attendant identifier
     * 
     * @return the searched flight attendant, if exists
     */
    Optional<FlightAttendant> searchFlightAttendant(String flightAttendantId);

    /**
     * 
     * @return the protective copy of the list of flight attendants
     */
    List<FlightAttendant> getFlightAttendants();

    /**
     * Adds a booking to the list of reservations.
     * 
     * @param b    a booking
     */
    void addReservation(Booking b);

    /**
     * Removes a booking from the list of reservations.
     * 
     * @param b    a booking
     */
    void removeReservation(Booking b);

    /**
     * Searches for a booking within the list of reservations.
     * 
     * @param bookingId    the booking identifier
     * 
     * @return the searched booking, if exists
     */
    Optional<Booking> searchReservation(String bookingId);

    /**
     * 
     * @return the protective copy of the list of reservations
     */
    List<Booking> getReservations();

    /**
     * Adds a luggage and passenger pair associated with a flight to the map of luggage.
     * 
     * @param f    a flight
     * @param l    a luggage
     * @param p    a passenger
     */
    void addLuggage(Flight f, Luggage l, Passenger p);

    /**
     * Removes a luggage from the map of luggage.
     * 
     * @param l    a luggage
     */
    void removeLuggage(Luggage l);

    /**
     * Searches for a luggage within the map of luggage.
     * 
     * @param luggageId    the luggage identifier
     * 
     * @return a luggage and passenger pair associated with a flight, if exists
     */
    Optional<Pair<Flight, Pair<Luggage, Passenger>>> searchLuggage(String luggageId);

    /**
     * 
     * @return the protective copy of the map of luggage
     */
    Map<Flight, Pair<Luggage, Passenger>> getLuggage();

    /**
     * Adds a flight associated with a pilot and copilot pair to the map of flights.
     * 
     * @param f    a flight
     * @param p    a pilot
     * @param c    a copilot
     */
    void addFlight(Flight f, Pilot p, Pilot c);

    /**
     * Removes a flight from the map of flights.
     * 
     * @param f    a flight
     */
    void removeFlight(Flight f);

    /**
     * Searches for a flight within the map of flights.
     * 
     * @param flightId    the flight identifier
     * 
     * @return a flight associated with a pilot and copilot pair, if exists
     */
    Optional<Pair<Flight, Pair<Pilot, Pilot>>> searchFlight(String flightId);

    /**
     * 
     * @return the protective copy of the map of flights
     */
    Map<Flight, Pair<Pilot, Pilot>> getFlights();

    /**
     * Calculates the final price of the reservation on the basis of the travel class chosen, the passenger's age
     * and the luggage's weight.
     * 
     * @param b    the booking
     * @param l    the luggage
     */
    void calculateFinalPrice(Booking b, Luggage l);
}