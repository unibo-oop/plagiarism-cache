package model.implementations;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

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
 * Tests the airport.
 */
public class AirportTest {

    private final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
    private final Airport airport = AirportImpl.getAirport();

    /**
     * Tests the list of staff members.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void staffMembersTest() throws ParseException {

        final Date birthDate1 = this.format.parse("01/04/1978");
        final Date birthDate2 = this.format.parse("17/09/1983");

        final Staff staff1 = new StaffImpl("Mario", "Rossi", "M", birthDate1, "RSSMRA78D01D641I", "3339486388", "mrossi@gmail.com", "mario1", "rossi1");
        final Staff staff2 = new StaffImpl("Luigi", "Bianchi", "M", birthDate2, "BNCLGU83P17D689Y", "3459377322", "lbianchi@gmail.com", "luigi2", "bianchi2");

        this.airport.addStaff(staff1);
        this.airport.addStaff(staff2);
        assertEquals(this.airport.getStaff().size(), 2);
        assertEquals(this.airport.getStaff(), Arrays.asList(staff1, staff2));
        assertEquals(this.airport.searchStaff("mario1").get(), staff1);
        assertEquals(this.airport.searchStaff("giacomo2"), Optional.empty());
        this.airport.removeStaff(staff2);
        assertEquals(this.airport.searchStaff("luigi2"), Optional.empty());
        assertEquals(this.airport.getStaff().size(), 1);
        assertEquals(this.airport.getStaff(), Arrays.asList(staff1));
        this.airport.removeStaff(staff2);
        assertEquals(this.airport.getStaff().size(), 1);
        assertEquals(this.airport.getStaff(), Arrays.asList(staff1));
    }

    /**
     * Tests the list of planes.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void planesTest() throws ParseException {

        final Plane plane1 = new PlaneImpl("Alitalia", 300, 200, 100);
        final Plane plane2 = new PlaneImpl("Delta", 200, 150, 80);
        final Plane plane3 = new PlaneImpl("Emirates", 300, 200, 200);

        this.airport.addPlane(plane1);
        this.airport.addPlane(plane2);
        this.airport.addPlane(plane3);
        assertEquals(this.airport.getPlanes().size(), 3);
        assertEquals(this.airport.getPlanes(), Arrays.asList(plane1, plane2, plane3));
        assertEquals(this.airport.searchPlane(plane1.getPlaneId()).get(), plane1);
        this.airport.removePlane(plane2);
        assertEquals(this.airport.searchPlane(plane2.getPlaneId()), Optional.empty());
        assertEquals(this.airport.getPlanes().size(), 2);
        assertEquals(this.airport.getPlanes(), Arrays.asList(plane1, plane3));
        this.airport.removePlane(plane2);
        assertEquals(this.airport.getPlanes().size(), 2);
        assertEquals(this.airport.getPlanes(), Arrays.asList(plane1, plane3));
    }

    /**
     * Tests the list of destinations.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void destinationsTest() throws ParseException {

        final Destination destination1 = new DestinationImpl("D8", "Italy", "Rome", "Aeroporto internazionale Leonardo da Vinci");
        final Destination destination2 = new DestinationImpl("D10", "Norway", "Oslo", "Airport of Oslo-Gardermoen");
        final Destination destination3 = new DestinationImpl("D5", "Germany", "Berlin", "Flughafen Berlin-Schönefeld");

        this.airport.addDestination(destination1);
        this.airport.addDestination(destination2);
        this.airport.addDestination(destination3);
        assertEquals(this.airport.getDestinations().size(), 3);
        assertEquals(this.airport.getDestinations(), Arrays.asList(destination1, destination2, destination3));
        assertEquals(this.airport.searchDestination(destination3.getDestinationId()).get(), destination3);
        assertEquals(this.airport.searchDestination("D7"), Optional.empty());
        this.airport.removeDestination(destination3);
        assertEquals(this.airport.searchDestination(destination3.getDestinationId()), Optional.empty());
        assertEquals(this.airport.getDestinations().size(), 2);
        assertEquals(this.airport.getDestinations(), Arrays.asList(destination1, destination2));
        this.airport.removeDestination(destination3);
        assertEquals(this.airport.getDestinations().size(), 2);
        assertEquals(this.airport.getDestinations(), Arrays.asList(destination1, destination2));
    }

    /**
     * Tests the list of pilots.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void pilotsTest() throws ParseException {

        final Date birthDate1 = this.format.parse("30/05/1967");
        final Date birthDate2 = this.format.parse("06/08/1969");
        final Date birthDate3 = this.format.parse("23/12/1975");

        final Pilot pilot1 = new PilotImpl("Marco", "Verdi", "M", birthDate1, "VRDMRC67E30H291I", "3347894733", "mverdi@gmail.com", "FL01");
        final Pilot pilot2 = new PilotImpl("Luca", "Neri", "M", birthDate2, "NRELCU69M06H294N", "3387683999", "lneri@gmail.com", "FL02");
        final Pilot pilot3 = new PilotImpl("Andrea", "Rosati", "M", birthDate3, "RSTNDR75T23C573V", "3469934552", "arosati@gmail.com", "FL03");

        this.airport.addPilot(pilot1);
        this.airport.addPilot(pilot2);
        this.airport.addPilot(pilot3);
        assertEquals(this.airport.getPilots().size(), 3);
        assertEquals(this.airport.getPilots(), Arrays.asList(pilot1, pilot2, pilot3));
        assertEquals(this.airport.searchPilot(pilot3.getPilotId()).get(), pilot3);
        this.airport.removePilot(pilot1);
        assertEquals(this.airport.searchPilot(pilot1.getPilotId()), Optional.empty());
        assertEquals(this.airport.getPilots().size(), 2);
        assertEquals(this.airport.getPilots(), Arrays.asList(pilot2, pilot3));
        this.airport.removePilot(pilot1);
        assertEquals(this.airport.getPilots().size(), 2);
        assertEquals(this.airport.getPilots(), Arrays.asList(pilot2, pilot3));
    }

    /**
     * Tests the list of flight attendants.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void flightAttendantsTest() throws ParseException {

        final Date birthDate1 = this.format.parse("27/12/1988");
        final Date birthDate2 = this.format.parse("13/05/1989");
        final Date birthDate3 = this.format.parse("23/12/1975");

        final FlightAttendant flightAttendant1 = new FlightAttendantImpl("Giovanni", "Celli", "M", birthDate1, "CLLGNN88T27D142B", "3498769453", "gcelli@gmail.com");
        final FlightAttendant flightAttendant2 = new FlightAttendantImpl("Paola", "Rasi", "F", birthDate2, "RSAPLA89E53E507Z", "3478736485", "prasi@gmail.com");
        final FlightAttendant flightAttendant3 = new FlightAttendantImpl("Lucia", "Galli", "F", birthDate3, "GLLLCU75T63D643O", "3667398100", "lgalli@gmail.com");

        this.airport.addFlightAttendant(flightAttendant1);
        this.airport.addFlightAttendant(flightAttendant2);
        this.airport.addFlightAttendant(flightAttendant3);
        assertEquals(this.airport.getFlightAttendants().size(), 3);
        assertEquals(this.airport.getFlightAttendants(), Arrays.asList(flightAttendant1, flightAttendant2, flightAttendant3));
        assertEquals(this.airport.searchFlightAttendant(flightAttendant3.getFlightAttendantId()).get(), flightAttendant3);
        this.airport.removeFlightAttendant(flightAttendant1);
        assertEquals(this.airport.searchFlightAttendant(flightAttendant1.getFlightAttendantId()), Optional.empty());
        assertEquals(this.airport.getFlightAttendants().size(), 2);
        assertEquals(this.airport.getFlightAttendants(), Arrays.asList(flightAttendant2, flightAttendant3));
        this.airport.removeFlightAttendant(flightAttendant1);
        assertEquals(this.airport.getFlightAttendants().size(), 2);
        assertEquals(this.airport.getFlightAttendants(), Arrays.asList(flightAttendant2, flightAttendant3));
    }

    /**
     * Tests the list of reservations.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void reservationsTest() throws ParseException {

        final Date birthDate1 = this.format.parse("03/04/1990");
        final Date birthDate2 = this.format.parse("06/03/1987");

        final Passenger passenger1 = new PassengerImpl("134R7P", "Greta", "Sarti", "F", birthDate1, 27, "SRTGRT90D43D447S", "3337568669", "gsarti@gmail.com");
        final Passenger passenger2 = new PassengerImpl("218U9I", "Giacomo", "Nanni", "M", birthDate2, 30, "NNNGCM87C06D451J", "3893004345", "gnanni@gmail.com");

        final Plane plane1 = new PlaneImpl("Alitalia", 400, 200, 100);
        final Plane plane2 = new PlaneImpl("American Airlines", 300, 150, 75);

        final Destination destination1 = new DestinationImpl("D5", "Germany", "Berlin", "Flughafen Berlin-Schönefeld");
        final Destination destination2 = new DestinationImpl("D8", "Italy", "Rome", "Aeroporto internazionale Leonardo da Vinci");

        final Date departureDate1 = this.format.parse("01/12/2017");
        final Date departureDate2 = this.format.parse("09/10/2017");

        final Date arrivalDate1 = this.format.parse("02/12/2017");
        final Date arrivalDate2 = this.format.parse("10/10/2017");

        final List<String> flightAttendantIdentifierslist1 = Arrays.asList("A0", "A1", "A2");
        final List<String> flightAttendantIdentifierslist2 = Arrays.asList("B0", "B1", "B2");

        final Flight flight1 = new FlightImpl(plane1, destination1, departureDate1, arrivalDate1, "22:00", "00:30", 60.8, flightAttendantIdentifierslist1);
        final Flight flight2 = new FlightImpl(plane2, destination2, departureDate2, arrivalDate2, "23:30", "02:45", 90.5, flightAttendantIdentifierslist2);


        final Booking booking1 = new BookingImpl(flight1, TravelClass.BUSINESS, passenger1);
        final Booking booking2 = new BookingImpl(flight2, TravelClass.FIRST, passenger2);

        this.airport.addReservation(booking1);
        this.airport.addReservation(booking2);
        assertEquals(this.airport.getReservations().size(), 2);
        assertEquals(this.airport.getReservations(), Arrays.asList(booking1, booking2));
        assertEquals(this.airport.searchReservation(booking2.getBookingId()).get(), booking2);
        this.airport.removeReservation(booking1);
        assertEquals(this.airport.searchReservation(booking1.getBookingId()), Optional.empty());
        assertEquals(this.airport.getReservations().size(), 1);
        assertEquals(this.airport.getReservations(), Arrays.asList(booking2));
        this.airport.removeReservation(booking1);
        assertEquals(this.airport.getReservations().size(), 1);
        assertEquals(this.airport.getReservations(), Arrays.asList(booking2));
    }

    /**
     * Tests the map of luggage.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void luggageTest() throws ParseException {

        final Date birthDate1 = this.format.parse("03/04/1990");
        final Date birthDate2 = this.format.parse("06/03/1987");

        final Plane plane1 = new PlaneImpl("Ryanair", 300, 200, 200);
        final Plane plane2 = new PlaneImpl("Lufthansa", 200, 150, 80);

        final Destination destination1 = new DestinationImpl("D3", "France", "Paris", "Aéroport de Paris - Le Bourget");
        final Destination destination2 = new DestinationImpl("D4", "Spain", "Barcelona", "Aeroport de Barcelona - El Prat de Llobregat");

        final Date departureDate1 = this.format.parse("23/11/2017");
        final Date departureDate2 = this.format.parse("13/09/2017");

        final Date arrivalDate1 = this.format.parse("23/11/2017");
        final Date arrivalDate2 = this.format.parse("14/09/2017");

        final List<String> flightAttendantIdentifierslist1 = Arrays.asList("D0", "D5", "D8");
        final List<String> flightAttendantIdentifierslist2 = Arrays.asList("A7", "A9", "A21");

        final Flight flight1 = new FlightImpl(plane1, destination1, departureDate1, arrivalDate1, "16:00", "21:00", 34.0, flightAttendantIdentifierslist1);
        final Flight flight2 = new FlightImpl(plane2, destination2, departureDate2, arrivalDate2, "23:00", "03:00", 55.0, flightAttendantIdentifierslist2);

        final Luggage luggage1 = new LuggageImpl(10.0);
        final Luggage luggage2 = new LuggageImpl(30.0);

        final Passenger passenger1 = new PassengerImpl("134R7P", "Greta", "Sarti", "F", birthDate1, 27, "SRTGRT90D43D447S", "3337568669", "gsarti@gmail.com");
        final Passenger passenger2 = new PassengerImpl("218U9I", "Giacomo", "Nanni", "M", birthDate2, 30, "NNNGCM87C06D451J", "3893004345", "gnanni@gmail.com");

        final Set<Flight> set = new HashSet<>();
        set.add(flight1);
        set.add(flight2);

        this.airport.addLuggage(flight1, luggage1, passenger1);
        this.airport.addLuggage(flight2, luggage2, passenger2);
        assertEquals(this.airport.getLuggage().size(), 2);
        assertEquals(this.airport.getLuggage().keySet(), set);
        assertEquals(this.airport.searchLuggage(luggage1.getLuggageId()).get().getSecond().getFirst(), luggage1);
        this.airport.removeLuggage(luggage1);
        assertEquals(this.airport.searchLuggage(luggage1.getLuggageId()), Optional.empty());
        assertEquals(this.airport.getLuggage().size(), 1);
        set.remove(flight1);
        assertEquals(this.airport.getLuggage().keySet(), set);
        this.airport.removeLuggage(luggage1);
        assertEquals(this.airport.getLuggage().size(), 1);
        assertEquals(this.airport.getLuggage().keySet(), set);
    }

    /**
     * Tests the map of flights.
     * 
     * @throws ParseException    the exception thrown if the beginning of the string specified in the parse method cannot be parsed.
     */
    @Test
    public void flightsTest() throws ParseException {

        final Date birthDate1 = this.format.parse("30/05/1967");
        final Date birthDate2 = this.format.parse("06/08/1969");
        final Date birthDate3 = this.format.parse("05/05/1973");
        final Date birthDate4 = this.format.parse("14/02/1985");

        final Pilot pilot1 = new PilotImpl("Marco", "Verdi", "M", birthDate1, "VRDMRC67E30H291I", "3347894733", "mverdi@gmail.com", "FL01");
        final Pilot pilot2 = new PilotImpl("Luca", "Neri", "M", birthDate2, "NRELCU69M06H294N", "3387683999", "lneri@gmail.com", "FL02");

        final Pilot copilot1 = new PilotImpl("Simone", "Monti", "M", birthDate3, "MNTSMN73E05H294K", "3478998301", "smonti@gmail.com", "FL10");
        final Pilot copilot2 = new PilotImpl("Samuele", "Ranieri", "M", birthDate4, "RNRSML85B14H146G", "3405874321", "sranieri@gmail.com", "FL11");


        final Plane plane1 = new PlaneImpl("Ryanair", 300, 200, 200);
        final Plane plane2 = new PlaneImpl("Lufthansa", 200, 150, 80);

        final Destination destination1 = new DestinationImpl("D3", "France", "Paris", "Aéroport de Paris - Le Bourget");
        final Destination destination2 = new DestinationImpl("D4", "Spain", "Barcelona", "Aeroport de Barcelona - El Prat de Llobregat");

        final Date departureDate1 = this.format.parse("09/09/2017");
        final Date departureDate2 = this.format.parse("21/08/2017");

        final Date arrivalDate1 = this.format.parse("09/09/2017");
        final Date arrivalDate2 = this.format.parse("22/08/2017");

        final List<String> flightAttendantIdentifierslist1 = Arrays.asList("A9", "D11", "E0");
        final List<String> flightAttendantIdentifierslist2 = Arrays.asList("A4", "A6", "A9");

        final Flight flight1 = new FlightImpl(plane1, destination1, departureDate1, arrivalDate1, "13:00", "18:00", 38.0, flightAttendantIdentifierslist1);
        final Flight flight2 = new FlightImpl(plane2, destination2, departureDate2, arrivalDate2, "23.35:", "03.45", 60.2, flightAttendantIdentifierslist2);

        final Set<Flight> set = new HashSet<>();
        set.add(flight1);
        set.add(flight2);

        this.airport.addFlight(flight1, pilot1, copilot1);
        this.airport.addFlight(flight2, pilot2, copilot2);
        assertEquals(this.airport.getFlights().size(), 2);
        assertEquals(this.airport.getFlights().keySet(), set);
        assertEquals(this.airport.searchFlight(flight1.getFlightId()).get().getFirst(), flight1);
        this.airport.removeFlight(flight2);
        assertEquals(this.airport.searchFlight(flight2.getFlightId()), Optional.empty());
        assertEquals(this.airport.getFlights().size(), 1);
        set.remove(flight2);
        assertEquals(this.airport.getFlights().keySet(), set);
        this.airport.removeFlight(flight2);
        assertEquals(this.airport.getFlights().size(), 1);
        assertEquals(this.airport.getFlights().keySet(), set);
    }

}
