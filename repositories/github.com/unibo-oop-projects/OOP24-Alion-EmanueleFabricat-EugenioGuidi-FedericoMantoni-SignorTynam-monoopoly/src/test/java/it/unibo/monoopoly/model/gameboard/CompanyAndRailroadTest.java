package it.unibo.monoopoly.model.gameboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Company;
import it.unibo.monoopoly.model.gameboard.api.Railroad;
import it.unibo.monoopoly.model.gameboard.impl.CellFactoryImpl;
import it.unibo.monoopoly.model.gameboard.impl.RailroadImpl;
import it.unibo.monoopoly.model.notary.api.Notary;
import it.unibo.monoopoly.model.notary.impl.NotaryImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;

/**
 * Test class for {@link Railroad} and {@link Company} classes.
 */
class CompanyAndRailroadTest {

    private static final int RENT_ONE_RAILROAD = 25;
    private static final int MIN_RENT_ONE_COMPANY = 8;
    private static final int MAX_RENT_ONE_COMPANY = 48;
    private static final int MIN_RENT_TWO_COMPANY = 20;
    private static final int MAX_RENT_TWO_COMPANY = 120;
    private static final Buyable RAILROAD_N = new RailroadImpl("Stazione Nord", 200);
    private static final Buyable RAILROAD_E = new RailroadImpl("Stazione Est", 200);
    private static final Buyable RAILROAD_S = new RailroadImpl("Stazione Sud", 200);
    private static final Buyable RAILROAD_O = new RailroadImpl("Stazione Ovest", 200);
    private static final int START_MONEY = 1500;
    private static final int TRIES = 10;

    private Set<Buyable> railroads;
    private Buyable company1;
    private Buyable company2;

    private Player owner;

    private Notary notary;
    private final List<Cell> cells = new CellFactoryImpl().createCells();

    @BeforeEach
    void init() {
        this.railroads = Set.of(RAILROAD_E, RAILROAD_N, RAILROAD_O, RAILROAD_S);
        this.company1 = (Company) cells.stream()
                .filter(c -> c.isCompany() && "Società acqua potabile".equals(c.getName())).findFirst().get();
        this.company2 = (Company) cells.stream()
                .filter(c -> c.isCompany() && "Società elettrica".equals(c.getName())).findFirst().get();
        this.owner = new PlayerImpl("Franco", START_MONEY, 0, false);
        this.notary = new NotaryImpl();
    }

    @Test
    void testSubinterface() {
        assertInstanceOf(Company.class, company1);
        assertInstanceOf(Company.class, company2);
        for (final Buyable buyable : railroads) {
            assertInstanceOf(Railroad.class, buyable);
        }
    }

    @Test
    void testRailroad() {
        assertThrows(IllegalStateException.class, () -> railroads.stream().findAny().get().getRentalValue());
        int expectedRent = RENT_ONE_RAILROAD;
        for (final Buyable buyable : railroads) {
            this.notary.buyProperty(owner, buyable);
            assertEquals(expectedRent, buyable.getRentalValue());
            expectedRent *= 2;
        }
    }

    @Test
    void testCompany() {
        final Company company1 = (Company) this.company1;
        final Company company2 = (Company) this.company2;
        final Exception exception1 = assertThrows(IllegalStateException.class, company1::getRentalValue);
        final Exception exception2 = assertThrows(IllegalStateException.class, company1::rollAndCalculate);
        assertEquals("The property must be owned by a player", exception1.getMessage());
        assertEquals("The property must be owned by a player", exception2.getMessage());
        notary.buyProperty(owner, company1);
        final Exception exception3 = assertThrows(IllegalStateException.class, company1::getRentalValue);
        assertEquals("Rental value need to be first calculated for Companies",
                exception3.getMessage());
        this.checkRental(company1, MIN_RENT_ONE_COMPANY, MAX_RENT_ONE_COMPANY);
        notary.buyProperty(owner, company2);
        this.checkRental(company1, MIN_RENT_TWO_COMPANY, MAX_RENT_TWO_COMPANY);
        this.checkRental(company2, MIN_RENT_TWO_COMPANY, MAX_RENT_TWO_COMPANY);
    }

    private void checkRental(final Company company, final int min, final int max) {
        for (int i = 0; i < TRIES; i++) {
            company.rollAndCalculate();
            final int rentalValue = company.getRentalValue();
            assertTrue(rentalValue >= min && rentalValue <= max);
        }
    }

}
