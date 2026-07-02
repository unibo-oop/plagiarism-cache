package test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.AppointmentsImpl;
import model.users.Clients;
import model.users.ClientsImpl;
import controller.CompanyImpl;

public class TestCompanyAppointments {

    private static CompanyImpl company = CompanyImpl.getInstance();
    private static final String DATE = "2021-04-30";
    private static final String HOUR = "15:00";
    private static final Clients CLIENT = new ClientsImpl("01772160402", "Emporio da Mario", "Via Antonio Montanari 11/13", "Meldola", 47014, "3467529933", "emporiodamario@libero.it", 150);
    private static final double TIME = 95.0;
    private static final double EARN = 102.0;
    private AppointmentsImpl appointment = null;

    @BeforeAll
    public void testAppointmentsImpl() {
        appointment = new AppointmentsImpl(DATE, HOUR, CLIENT, TIME, EARN);
    }

    @Test
    public void testAppointment() {
        Assertions.assertTrue(appointment.getDate().equals(DATE));
        Assertions.assertTrue(appointment.getHour().equals(HOUR));
        Assertions.assertTrue(appointment.getClient().equals(CLIENT));
        Assertions.assertTrue(Double.compare(appointment.getTime(), TIME) == 0);
        Assertions.assertTrue(Double.compare(appointment.getEarn(), EARN) == 0);
    }

    @Test
    public void testCompanyAppointments() {
        Assertions.assertTrue(company.getAppointment().isEmpty());
        company.addAppointment(appointment);
        Assertions.assertFalse(company.getAppointment().isEmpty());
        Assertions.assertTrue(company.getAppointment().get(0).equals(company.searchAppointment(DATE, HOUR).get()));
        company.removeAppointment(appointment);
        Assertions.assertTrue(company.getAppointment().isEmpty());
    }
}
