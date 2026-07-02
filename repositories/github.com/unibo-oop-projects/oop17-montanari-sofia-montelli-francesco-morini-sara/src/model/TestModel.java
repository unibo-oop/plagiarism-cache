package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import model.navy.RandomNavyFactory;
import model.navy.RandomNavyFactoryImpl;
import model.player.PlayerImpl;

/**
 * Test for {@link ModelImpl}.
 */
public class TestModel {

    /**
     * Test that perform an illegal set on the navy and grid specification.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSpecification() {
        ModelFactory.getModelInteract().setSpecification(Arrays.asList(10, 10, 10, 10), 2);
    }

    /**
     * This method tries to set a navy that is not coherent with the provided specification. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNavy() {
        final Model model = ModelFactory.getModelInteract();
        model.setSpecification(Arrays.asList(1, 0, 0, 0), 10);
        model.setNavy(new RandomNavyFactoryImpl().getClassicRandomFormation());
    }

    /**
     * Test a complete initialization of a match.
     */
    @Test
    public void testMatchInitialization() {
        final Model model = ModelFactory.getModelInteract();
        assertFalse("Assert that con't play", model.canPlay());
        model.setPaths("testModel.bin", "");
        model.setPlayerData(new PlayerImpl("test"), "test1");
        model.setPlayerData(new PlayerImpl("test2"), "test2");
        model.setSpecification(Arrays.asList(4, 3, 2, 1), 10);
        final RandomNavyFactory randomNavyFactory = new RandomNavyFactoryImpl();
        model.setNavy(randomNavyFactory.getClassicRandomFormation());
        model.setNavy(randomNavyFactory.getClassicRandomFormation());
        assertTrue("Assert can play", model.canPlay());
    }
}
