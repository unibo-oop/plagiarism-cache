package javaclimber.worldconstructor.gameobjectspawn.addonspawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.impl.EliCap;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPositionSetterImpl;

/**
 * Test for the {@link AddOnPositionSetterImpl}.
 */
class AddOnPositionSetterTest {

    private static final double ADDON_HEIGHT = 5;
    private static final double ADDON_WIDTH = 5;

    private static final double PLATFORM_WIDTH = 20;

    private static final double POS_X = 400;
    private static final double POS_Y = 700;

    /**
     * The AddOnPositionSetterImpl instance to test.
     */
    private AddOnPositionSetterImpl addOnPositionGenerator;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    void setUp() {
        this.addOnPositionGenerator = new AddOnPositionSetterImpl();
    }

    /**
     * Create a gadget at the given position.
     * 
     * @param pos the position of the gadget.
     * @return the gadget.
     */
    private Gadget createGadget(final Vector2d pos) {
        return new EliCap(ADDON_WIDTH, ADDON_HEIGHT, pos);
    }

    /**
     * Test for generating the position of an add-on.
     */
    @Test
    void generatePosition() {
        final Gadget gadget = createGadget(new Vector2dImpl(POS_X, POS_Y));
        final Gadget gadgetModPos = addOnPositionGenerator.generatePosition(gadget, PLATFORM_WIDTH);
        assertEquals(gadgetModPos, gadget);
        assertEquals(POS_X + ((PLATFORM_WIDTH - ADDON_WIDTH) / 2), gadgetModPos.getPosX());
        assertEquals(POS_Y - ADDON_HEIGHT, gadgetModPos.getPosY());
    }

}
