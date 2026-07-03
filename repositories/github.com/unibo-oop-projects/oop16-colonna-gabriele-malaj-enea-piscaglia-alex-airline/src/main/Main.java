package main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import controller.implementations.LoginControllerImpl;
import controller.implementations.UtilitiesImpl;
import model.implementations.AirportImpl;
import model.implementations.DestinationImpl;
import model.implementations.FlightAttendantImpl;
import model.implementations.IdGeneratorImpl;
import model.implementations.PilotImpl;
import model.implementations.PlaneImpl;
import model.implementations.StaffImpl;
import model.interfaces.Destination;
import model.interfaces.FlightAttendant;
import model.interfaces.Pilot;
import model.interfaces.Plane;
import model.interfaces.Staff;
import view.implementations.LoginViewImpl;

/**
 * Main class.
 */
public final class Main {

    private static final int CURRENT_ID = 9;
    private static final String DIR = "res";
    private static final String ID = "res/Id.txt";
    private static final String STAFF = "res/Staff.txt";
    private static final String PLANES = "res/Planes.txt";
    private static final String DEST = "res/Destinations.txt";
    private static final String PILOTS = "res/Pilots.txt";
    private static final String FLIGHT_ATT = "res/FlightAttendants.txt";
    private static final  DateFormat FORMAT = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
    private static Date date1 = null;
    private static Date date2 = null;

    private Main() {
    }

    /**
     * Main.
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        new File(DIR).mkdir();
        final File id = new File(ID);
        final File staff = new File(STAFF);
        final File planes = new File(PLANES);
        final File dest = new File(DEST);
        final File pilots = new File(PILOTS);
        final File flightAtt = new File(FLIGHT_ATT);

        try {
            date1 = FORMAT.parse("07/04/1966");
            date2 = FORMAT.parse("21/07/1984");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (id.length() == 0) {
            try {
                initializeId();
            } catch (IOException e) {
                e.printStackTrace();
            }
            IdGeneratorImpl.getIdGenerator().setInitialLetter('A');
            IdGeneratorImpl.getIdGenerator().setInitialNumber(0);
        }

        if (staff.length() == 0) {
            try {
                initializeStaff(date1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (planes.length() == 0) {
            try {
                initializePlanes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dest.length() == 0) {
            try {
                initializeDestinations();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (pilots.length() == 0) {
            try {
                initializePilots(date1, date2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (flightAtt.length() == 0) {
            try {
                initializeFlightAtt(date1, date2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            new LoginControllerImpl(new LoginViewImpl());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeId() throws IOException {
        final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(ID)));

        oos.writeChar('A');
        oos.writeInt(CURRENT_ID);
        oos.flush();
        oos.close();
    }

    private static void initializeStaff(final Date date) throws IOException {
        final Staff s = new StaffImpl("Mario", "Rossi", "Maschio", date, "rssmra66x14n159w", "3332154962", "mrossi@gmail.com", "admin", "root");
        AirportImpl.getAirport().addStaff(s);
        UtilitiesImpl.getInstance().saveStaff();
        AirportImpl.getAirport().removeStaff(s);
    }

    private static void initializePlanes() throws IOException {
        final Plane plane1 = new PlaneImpl("Alitalia", 300, 200, 100);
        final Plane plane2 = new PlaneImpl("Delta", 200, 150, 80);
        final Plane plane3 = new PlaneImpl("Emirates", 300, 200, 200);
        AirportImpl.getAirport().addPlane(plane1);
        AirportImpl.getAirport().addPlane(plane2);
        AirportImpl.getAirport().addPlane(plane3);

        UtilitiesImpl.getInstance().savePlanes();

        AirportImpl.getAirport().removePlane(plane1);
        AirportImpl.getAirport().removePlane(plane2);
        AirportImpl.getAirport().removePlane(plane3);
    }

    private static void initializeDestinations() throws IOException {
        final Destination destination1 = new DestinationImpl("LIN", "Italia", "Milano", "Linate");
        final Destination destination2 = new DestinationImpl("LHR", "Regno Unito", "Londra", "Londra-Heathrow");
        final Destination destination3 = new DestinationImpl("BCN", "Spagna", "Barcellona", "El Prat");
        AirportImpl.getAirport().addDestination(destination1);
        AirportImpl.getAirport().addDestination(destination2);
        AirportImpl.getAirport().addDestination(destination3);

        UtilitiesImpl.getInstance().saveDestinations();

        AirportImpl.getAirport().removeDestination(destination1);
        AirportImpl.getAirport().removeDestination(destination2);
        AirportImpl.getAirport().removeDestination(destination3);
    }

    private static void initializePilots(final Date d1, final Date d2) throws IOException {
        final Pilot pilot1 = new PilotImpl("Marco", "Verdi", "M", d1, "VRDMRC67E30H291I", "3347894733", "mverdi@gmail.com", "FL01");
        final Pilot pilot2 = new PilotImpl("Luca", "Neri", "M", d2, "NRELCU69M06H294N", "3387683999", "lneri@gmail.com", "FL02");
        final Pilot pilot3 = new PilotImpl("Andrea", "Rosati", "M", d1, "RSTNDR75T23C573V", "3469934552", "arosati@gmail.com", "FL03");
        AirportImpl.getAirport().addPilot(pilot1);
        AirportImpl.getAirport().addPilot(pilot2);
        AirportImpl.getAirport().addPilot(pilot3);

        UtilitiesImpl.getInstance().savePilots();

        AirportImpl.getAirport().removePilot(pilot1);
        AirportImpl.getAirport().removePilot(pilot2);
        AirportImpl.getAirport().removePilot(pilot3);
    }

    private static void initializeFlightAtt(final Date d1, final Date d2) throws IOException {
        final FlightAttendant flightAttendant1 = new FlightAttendantImpl("Giovanni", "Celli", "M", d1, "CLLGNN88T27D142B", "3498769453", "gcelli@gmail.com");
        final FlightAttendant flightAttendant2 = new FlightAttendantImpl("Paola", "Rasi", "F", d1, "RSAPLA89E53E507Z", "3478736485", "prasi@gmail.com");
        final FlightAttendant flightAttendant3 = new FlightAttendantImpl("Lucia", "Galli", "F", d2, "GLLLCU75T63D643O", "3667398100", "lgalli@gmail.com");
        AirportImpl.getAirport().addFlightAttendant(flightAttendant1);
        AirportImpl.getAirport().addFlightAttendant(flightAttendant2);
        AirportImpl.getAirport().addFlightAttendant(flightAttendant3);

        UtilitiesImpl.getInstance().saveFlightAttendants();

        AirportImpl.getAirport().removeFlightAttendant(flightAttendant1);
        AirportImpl.getAirport().removeFlightAttendant(flightAttendant2);
        AirportImpl.getAirport().removeFlightAttendant(flightAttendant3);
    }

}
