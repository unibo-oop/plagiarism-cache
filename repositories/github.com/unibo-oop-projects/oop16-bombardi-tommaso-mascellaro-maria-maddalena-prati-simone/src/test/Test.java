package test;
//CHECKSTYLE:OFF
import static org.junit.Assert.*;

import java.util.*;

import controller.Controller;
import model.admin.Pair;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * 
 */
public class Test {

    /**
     * test login methods.
     */
    @org.junit.Test //NOPMD
    public void testLogin() {
        // Istanzio la classe user
        final Controller ci = Controller.getController();

        //----------LOGIN ADMIN----------
        //login with correct admin
        ci.loginAdmin("simo96", "96simo");
        assertEquals(ci.getCurrentAdmin(), Optional.of("Simone Prati"));
        //test logout admin
        ci.logoutAdmin();
        assertEquals(ci.getCurrentAdmin(), Optional.empty());
        //login with wrong admin
        try {
            ci.loginAdmin("Triss", "redhead");
            fail("cannot login with a admin that is not present in demo");
        } catch (UnsupportedOperationException e) {
            assertTrue(e.getClass().equals(UnsupportedOperationException.class));
        }

        //----------LOGIN USER----------
        //register a correct new user
        ci.registerUser(new Pair<>("witcher", "killuall"), new Pair<>("Geralt", "of Rivia"));
        assertEquals(ci.getCurrentUser(), Optional.of("Geralt of Rivia")); //NOPMD
        //test logout user
        ci.logoutUser();
        assertEquals(ci.getCurrentUser(), Optional.empty());
        //register an already existing user
        try {
            ci.registerUser(new Pair<>("witcher", "killuall"), new Pair<>("Geralt", "of Rivia"));
            fail("cannot use an olready existing username");
        } catch (IllegalStateException e) {
            assertTrue(e.getClass().equals(IllegalStateException.class));
        }
        assertEquals(ci.getCurrentUser(), Optional.empty());
        //login with a correct user
        ci.loginUser("anto63", "63anto");
        assertEquals(ci.getCurrentUser(), Optional.of("Antonio Bianchi"));
        ci.logoutUser();
        assertEquals(ci.getCurrentUser(), Optional.empty());
        //login with a no existing user
        try {
            ci.loginUser("Yennefer", "magic");
            fail("this user is not registered");
        } catch (UnsupportedOperationException e) {
            assertTrue(e.getClass().equals(UnsupportedOperationException.class));
        }
        assertEquals(ci.getCurrentUser(), Optional.empty());
        ci.resetApplication();
    }

    /**
     * test cart methods.
     */
    @org.junit.Test //NOPMD
    public void testCart() {
        // Istanzio la classe user
        final Controller ci = Controller.getController();

        //buy an object
        ci.addBuyObject(Object1.GLOVES, "5");
        //test buyObject checks
        try {
            ci.addBuyObject(Object1.GLOVES, "11");
            fail("cannot buy more then 10 objects together");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        assertEquals(ci.getCart().keySet().size(), 1);
        //test if total price is correct
        assertEquals(ci.getCartPrice(), "99,50");

        //rent an object
        ci.addRentObject(Object2.SKIS, "3", "1", Season.MID_SEASON);
        //test rentObject checks
        try {
            ci.addRentObject(Object2.SKIS, "11", "4", Season.MID_SEASON);
            fail("cannot rent more then 10 objects together");
            ci.addRentObject(Object2.SKIS, "3", "21", Season.MID_SEASON);
            fail("cannot rent for more than 20 days");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        assertEquals(ci.getCart().keySet().size(), 2);
        //test if total price is correct
        assertEquals(ci.getCartPrice(), "174,50");

        //deposit an object
        ci.addStorage(Object2.HELMET, "3", "1");
        //test storage checks
        try {
            ci.addStorage(Object2.HELMET, "11", "4");
            fail("cannot deposit more then 10 objects together");
            ci.addStorage(Object2.HELMET, "3", "21");
            fail("cannot deposit for more then 20 days");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        assertEquals(ci.getCart().keySet().size(), 3);
        //test if total price is correct
        assertEquals(ci.getCartPrice(), "177,50");

        //buying a skipass
        ci.addSkipass(Skipass.ONE_DAY, "2", Season.MID_SEASON);
        //test buy skipass checks
        try {
            ci.addSkipass(Skipass.ONE_DAY, "11", Season.MID_SEASON);
            fail("cannot buy more then 10 skipass together");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        assertEquals(ci.getCart().keySet().size(), 4);
        //test if total price is correct
        assertEquals(ci.getCartPrice(), "257,50");

        //book a lesson with an instructor
        ci.addInstructor(Instructor.SKI_1HOUR, "1", Season.MID_SEASON);
        //test addInstructor checks
        try {
            ci.addInstructor(Instructor.SKI_1HOUR, "5", Season.MID_SEASON);
            fail("cannot book a lesson for more than 4 students");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        assertEquals(ci.getCart().keySet().size(), 5);
        //test if total price is correct
        assertEquals(ci.getCartPrice(), "287,50");

        //test remove operation
        ci.removeOperation("2");
        assertEquals(ci.getCart().keySet().size(), 4);
        ci.removeOperation("2");
        assertEquals(ci.getCart().keySet().size(), 3);
        //try to remove an operation 
        try {
            ci.removeOperation("4");
            fail("cannot remove the fourth operation, it does not exist");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
 
        //test emptyCart
        ci.removeAllOperations();
        assertEquals(ci.getCart().keySet().size(), 0);
    }
    
    /**
     * test payment checks.
     */
    @org.junit.Test //NOPMD
    public void testPayment() {
        // Istanzio la classe user
        final Controller ci = Controller.getController();
        //test correct credit card details
        ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("12/2020", "123")); //NOPMD
        //test wrong owner name
        try {
            ci.pay(new Pair<>("GR", "1234567891234567"), new Pair<>("12/2020", "123"));
            fail("owner name must contain 3 or more characters");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        //test wrong credit card code
        try {
            ci.pay(new Pair<>("Geralt of Rivia", "12345678912345678"), new Pair<>("12/2020", "123"));
            fail("code cannot be longer then 16 numbers");
            ci.pay(new Pair<>("Geralt of Rivia", "123456789123456"), new Pair<>("12/2020", "123"));
            fail("code cannot be shorter then 16 numbers");
            ci.pay(new Pair<>("Geralt of Rivia", "12345t7891234t67"), new Pair<>("12/2020", "123"));
            fail("code cannot be contain non-numeric characters");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        //test wrong expire date
        try {
            ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("1/2020", "123"));
            fail("cannot inser month with one character");
            ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("12/2000", "123"));
            fail("cannot insert a date in the past");
            ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("1/22020", "123"));
            fail("correct format mm/yyyy");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
        //test wrong cvc
        try {
            
            ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("12/2020", "12"));
            fail("cvc cannot be shorter then 3 numbers");
            ci.pay(new Pair<>("Geralt of Rivia", "1234567891234567"), new Pair<>("12/2020", "1234"));
            fail("cvc cannot be shorter then 3 numbers");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getClass().equals(IllegalArgumentException.class));
        }
    }
}
//CHECKSTYLE:ON