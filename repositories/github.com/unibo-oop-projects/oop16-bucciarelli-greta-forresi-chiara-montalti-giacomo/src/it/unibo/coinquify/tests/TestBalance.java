package it.unibo.coinquify.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.coinquify.balance.controller.BalanceController;
import it.unibo.coinquify.balance.controller.BalanceControllerImpl;
import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.DebtImpl;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.balance.model.LendingImpl;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.roommates.model.RoomMateImpl;

/**
 * Test the Balance and the Material Lending (Model e Controller).
 *
 */
public class TestBalance {

    private static final String NO_EXCEPTION = "Shouldn't have thrown an exception";
    private static final String DEBT = "Prestito per Tabacchino";
    private static final String LENDING = "Giacca";
    private static final String LENDING1 = "TostaPane";
    private static final String QUANT = "56.0";
    private static final String QUANT1 = "80.0";


    /**
     * test the Single Debt.
     */
    @Test
    public void testSingleDebt() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        try {
        final RoomMate marioRossi = new RoomMateImpl("Mario", "Rossi", "MRARSS00A01H501B", Room.ROOM_A, "3332343359",
                dateFormat.parse("01/01/1990"));
        final Debt debtPersonal = new DebtImpl(DEBT, marioRossi, Double.valueOf(QUANT));

        assertTrue(debtPersonal.getDebitor().equals(marioRossi));
        assertNotEquals(debtPersonal.getQuantity(), Double.valueOf(QUANT1));
        assertEquals(debtPersonal.getQuantity(), Double.valueOf(QUANT));
        assertFalse(debtPersonal.getDescription().equals("Comprare Riviste"));

        } catch (ParseException e) {
            Assert.fail(TestBalance.NO_EXCEPTION);
        }
    }

    /**
     * Test the Single Lending.
     */
    @Test
    public void testSingleLending() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        try {
        final RoomMate marioRossi = new RoomMateImpl("Mario", "Rossi", "MRARSS00A01H501B", Room.ROOM_A, "3332343359",
                dateFormat.parse("01/01/1990"));
        final Lending lendingPersonal = new LendingImpl(marioRossi, LENDING);

        assertTrue(lendingPersonal.getDebitor().equals(marioRossi));
        assertNotEquals(lendingPersonal.getDescription(), LENDING1);

        } catch (ParseException e) {
            Assert.fail(TestBalance.NO_EXCEPTION);
        }
    }

    /**
     *
     */
    @Test
    public void testBalance() {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);

        RoomMate marioRossi;
        try {
            marioRossi = new RoomMateImpl("Mario", "Rossi", "MRARSS00A01H501B", Room.ROOM_A, "3332343359",
                    dateFormat.parse("01/01/1990"));

            assertTrue(marioRossi.getMansions().isEmpty());

            final Set<RoomMate> roomMateSet = new HashSet<>();
            roomMateSet.add(marioRossi);

            final Debt debtPersonal = new DebtImpl(DEBT, marioRossi, Double.valueOf(QUANT));

            final Lending lendingPersonal = new LendingImpl(marioRossi, LENDING);

            final BalanceController controller = new BalanceControllerImpl();

            try {
                controller.addDebt(debtPersonal, marioRossi);

            } catch (Exception e) {
                Assert.fail(TestBalance.NO_EXCEPTION);
            }

            try {
                controller.addLending(lendingPersonal, marioRossi);


            } catch (Exception e) {
                Assert.fail(TestBalance.NO_EXCEPTION);
            }

            try {
                controller.removeDebt(debtPersonal, marioRossi);

            } catch (Exception e) {
                Assert.fail(TestBalance.NO_EXCEPTION);
            }

        } catch (ParseException e) {
            Assert.fail(TestBalance.NO_EXCEPTION);
        }
    }

}
