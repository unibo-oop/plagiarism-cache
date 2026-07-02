package test;


import model.users.ClientsImpl;
import model.users.StaffImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.CompanyImpl;

public class TestCompanyUsers {

    private static CompanyImpl company = CompanyImpl.getInstance();
    private static final String CF = "DBSVSS96S62D000U";
    private static final String NAME = "Vanessa Di Biasi";
    private static final String ADDRESS = "Via Roma 44";
    private static final String CITY = "Roma";
    private static final int CAP = 12345;
    private static final String TEL = "3331234567";
    private static final String EMAIL = "vanessa@unibo.it";
    private static final Boolean ADMIN = true;
    private static final int MQ = 200;
    private ClientsImpl client = null;
    private StaffImpl staff = null;

    @BeforeAll
    public void testClientsImpl() {
        client = new ClientsImpl(CF, NAME, ADDRESS, CITY, CAP, TEL, EMAIL, MQ);
    }

    @BeforeAll
    public void testStaffImpl() {
        staff = new StaffImpl(CF, NAME, ADDRESS, CITY, CAP, TEL, EMAIL, ADMIN);
    }

    @Test
    public void testClient() {
       Assertions.assertTrue(client.getCFPIVA().equals(CF));
       Assertions.assertTrue(client.getName().equals(NAME));
       Assertions.assertTrue(client.getAddress().equals(ADDRESS));
       Assertions.assertTrue(client.getCity().equals(CITY));
       Assertions.assertTrue(Integer.compare(client.getCAP(), CAP) == 0);
       Assertions.assertTrue(client.getTel().equals(TEL));
       Assertions.assertTrue(client.getEmail().equals(EMAIL));
       Assertions.assertTrue(Integer.compare(client.getMqStructure(), MQ) == 0);
    }

    @Test
    public void testStaff() {
        Assertions.assertTrue(staff.getCFPIVA().equals(CF));
        Assertions.assertTrue(staff.getName().equals(NAME));
        Assertions.assertTrue(staff.getAddress().equals(ADDRESS));
        Assertions.assertTrue(staff.getCity().equals(CITY));
        Assertions.assertTrue(Integer.compare(staff.getCAP(), CAP) == 0);
        Assertions.assertTrue(staff.getTel().equals(TEL));
        Assertions.assertTrue(staff.getEmail().equals(EMAIL));
        Assertions.assertTrue(staff.isAdmin());
    }

    @Test
    public void testCompanyClients() {
        Assertions.assertTrue(company.getClients().isEmpty());
        company.addClient(client);
        Assertions.assertFalse(company.getClients().isEmpty());
        Assertions.assertTrue(company.getClients().get(0).equals(company.searchClient(CF).get()));
        company.removeClient(client);
        Assertions.assertTrue(company.getClients().isEmpty());
    }

    @Test
    public void testCompanyStaff() {
        Assertions.assertTrue(company.getStaff().isEmpty());
        company.addStaff(staff);
        Assertions.assertFalse(company.getStaff().isEmpty());
        Assertions.assertTrue(company.getStaff().get(0).equals(company.searchStaffbyCF(CF).get()));
        Assertions.assertTrue(company.getStaff().get(0).equals(company.searchStaffbyEmail(EMAIL).get()));
        company.removeStaff(staff);
        Assertions.assertTrue(company.getStaff().isEmpty());
    }
}
