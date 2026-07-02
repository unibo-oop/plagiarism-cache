package model.navy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * Set of test on the {@link ShipImpl}
 * implementation of the {@link Ship} interface.
 */
public class TestNavy {

    /**
     * Test on a ship composition.
     */
    @Test
    public void testSpecification() {
        assertEquals("Check on the composition of a classic formation", new RandomNavyFactoryImpl().getClassicRandomFormation().getNavySpecification(), Arrays.asList(4, 3, 2, 1));
    }
}
