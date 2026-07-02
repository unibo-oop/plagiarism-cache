package it.unibo.monoopoly.model.gameboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.model.gameboard.impl.BuildableImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;

/**
 * This class test the class BuildableImpl.
 */
class BuildableImplTest {

    private static final int MAX_HOUSES = 5;
    static final int ZERO_HOUSE = 0;
    static final int ONE_HOUSE = 1;
    static final int TWO_HOUSE = 2;
    static final int THREE_HOUSE = 3;
    static final int FOUR_HOUSE = 4;
    static final int FIVE_HOUSE = 5;

    static final int RENT_ZERO_HOUSE = 45;
    static final int RENT_ONE_HOUSE = 225;
    static final int RENT_TWO_HOUSE = 625;
    static final int RENT_THREE_HOUSE = 1750;
    static final int RENT_FOUR_HOUSE = 2200;
    static final int RENT_FIVE_HOUSE = 2625;

    static final int PROPERTY_COST = 550;
    static final int HOUSE_COST = 100;
    static final String PROPERTY_NAME = "Corso Magellano";
    static final Optional<Player> FIRSTOWNER = Optional.of(new PlayerImpl("Mario", 1500, 0, false));
    static final Optional<Player> SECONDOWNER = Optional.of(new PlayerImpl("Franco", 1500, 0, false));
    static final Map<Integer, Integer> RENTAL_MAP = initializeRentalMap();

    private BuildableImpl property;

    private static Map<Integer, Integer> initializeRentalMap() {
        final Map<Integer, Integer> rentalMap = new HashMap<>();
        rentalMap.put(ZERO_HOUSE, RENT_ZERO_HOUSE);
        rentalMap.put(ONE_HOUSE, RENT_ONE_HOUSE);
        rentalMap.put(TWO_HOUSE, RENT_TWO_HOUSE);
        rentalMap.put(THREE_HOUSE, RENT_THREE_HOUSE);
        rentalMap.put(FOUR_HOUSE, RENT_FOUR_HOUSE);
        rentalMap.put(FIVE_HOUSE, RENT_FIVE_HOUSE);
        return rentalMap;
    }

    /**
     * This is executed before every test to initialize the field property to be
     * tested.
     */
    @BeforeEach
    public void initialization() {
        this.property = new BuildableImpl(RENTAL_MAP, PROPERTY_NAME, PROPERTY_COST, HOUSE_COST);
    }

    /**
     * Test the right initialization of field property.
     */
    @Test
    void testCell() {
        assertEquals(PROPERTY_NAME, this.property.getName());
        assertTrue(this.property.isBuildable());
        assertTrue(this.property.isAvailable());
    }

    /**
     * Test the method getRentalValue.
     */
    @Test
    void testGetRentalValue() {
        this.property.setOwner(FIRSTOWNER);
        assertEquals(RENTAL_MAP.get(ZERO_HOUSE), this.property.getRentalValue());
        this.property.buildHouse();
        assertEquals(RENTAL_MAP.get(ONE_HOUSE), this.property.getRentalValue());
        this.property.sellHouse();
        assertEquals(RENTAL_MAP.get(ZERO_HOUSE), this.property.getRentalValue());
    }

    /**
     * Test the method getCost.
     */
    @Test
    void testGetCost() {
        assertEquals(PROPERTY_COST, property.getCost());
    }

    /**
     * Test the methods setOwner and getOwner.
     */
    @Test
    void testOwner() {
        assertEquals(Optional.empty(), this.property.getOwner());
        this.property.setOwner(FIRSTOWNER);
        assertNotEquals(Optional.empty(), this.property.getOwner());
        assertNotEquals(SECONDOWNER, this.property.getOwner());
        assertEquals(FIRSTOWNER, this.property.getOwner());
        this.property.setOwner(Optional.empty());
        assertNotEquals(FIRSTOWNER, this.property.getOwner());
        assertNotEquals(SECONDOWNER, this.property.getOwner());
        assertEquals(Optional.empty(), this.property.getOwner());
    }

    /**
     * Test the method isAvailable.
     */
    @Test
    void testIsAvailable() {
        assertTrue(this.property.isAvailable());
        this.property.setOwner(FIRSTOWNER);
        assertFalse(this.property.isAvailable());
    }

    /**
     * Test the methods getMortgageValue, isMortgaged, setMortgage and
     * removeMortgage.
     */
    @Test
    void testIsMortgaged() {
        assertEquals(PROPERTY_COST / 2, this.property.getMortgageValue());
        assertFalse(this.property.isMortgaged());
        this.property.setMortgage();
        assertTrue(this.property.isMortgaged());
        this.property.removeMortgage();
        assertFalse(this.property.isMortgaged());
    }

    /**
     * Test the method buildHouse.
     */
    @Test
    void testBuildHouse() {
        this.property.setOwner(FIRSTOWNER);
        this.property.buildHouse();
        assertEquals(1, this.property.getHousesNumber());
        assertEquals(RENTAL_MAP.get(1), this.property.getRentalValue());

        for (int i = 0; i < 4; i++) {
            this.property.buildHouse();
        }

        assertEquals(MAX_HOUSES, this.property.getHousesNumber());
        assertThrows(IllegalStateException.class, this.property::buildHouse);
    }

    /**
     * Test the method sellHJouse.
     */
    @Test
    void testSellHouse() {
        this.property.setOwner(FIRSTOWNER);
        this.property.buildHouse();
        this.property.buildHouse();
        assertEquals(2, this.property.getHousesNumber());

        final int sellValue = this.property.sellHouse();
        assertEquals(HOUSE_COST / 2, sellValue);
        assertEquals(1, this.property.getHousesNumber());
        assertEquals(RENTAL_MAP.get(1), this.property.getRentalValue());

        this.property.sellHouse();
        assertEquals(0, this.property.getHousesNumber());
        assertEquals(RENTAL_MAP.get(0), this.property.getRentalValue());
        assertThrows(IllegalStateException.class, this.property::sellHouse);
    }

    /**
     * Test methods buildHouse and sellHouse in mortgage.
     */
    @Test
    void testBuildAndSellHouseOnMortgagedProperty() {
        this.property.setMortgage();
        assertTrue(this.property.isMortgaged());

        assertThrows(IllegalStateException.class, this.property::buildHouse);
        assertThrows(IllegalStateException.class, this.property::sellHouse);
    }
}
