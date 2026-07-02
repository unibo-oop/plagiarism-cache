package javagotchi.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javagotchi.model.Javagotchi;
import javagotchi.model.JavagotchiImpl;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;

/**
 * Test class for the model.
 * @author elisa
 *
 */
public class ModelTest {

    private static final double EPS = 0.01;
    private static final double ZERO_DOT_SIX = 0.6;
    private static final double ZERO_DOT_TWO = 0.2;

    /**
     * Test for the class Information.
     */
    @Test
    public void testInformation() {
        final Javagotchi jv = new JavagotchiImpl("Toby", Gender.MALE, Avatar.FOX);

        assertNotEquals(jv.getInformation().getGender(), Gender.FEMALE);
        assertNotEquals(jv.getInformation().getAvatar(), Avatar.CAT);

        assertEquals("Initial age should be 0", jv.getInformation().getAge().toString(), "0");

        jv.getInformation().getOlder();
        assertEquals("Age should be 1", jv.getInformation().getAge().toString(), "1");
    }

    /**
     * Test for the class NeedsImpl.
     */
    @Test
    public void testNeeds() {
        final Javagotchi jv = new JavagotchiImpl("Toby", Gender.MALE, Avatar.FOX);

        assertFalse("Nothing should have reached the death level", jv.getNeeds().somethingHasReachedTheDeathLevel());

        assertEquals(jv.getNeeds().getCleanliness().getLevel(), 1.0, EPS);
        assertEquals(jv.getNeeds().getEnergy().getLevel(), 1.0, EPS);
        assertEquals(jv.getNeeds().getHealth().getLevel(), 1.0, EPS);
        assertNotEquals(jv.getNeeds().getHunger().getLevel(), 2.0, EPS);
        assertNotEquals(jv.getNeeds().getHappiness().getLevel(), 0.0, EPS);
        assertNotEquals(jv.getNeeds().getDiscipline().getLevel(), 1.0, EPS);

        jv.getNeeds().getHealth().reduce();
        assertEquals(jv.getNeeds().getHealth().getLevel(), ZERO_DOT_SIX, EPS);
        jv.getNeeds().getHealth().reduce();
        assertEquals(jv.getNeeds().getHealth().getLevel(), ZERO_DOT_TWO, EPS);
        jv.getNeeds().getHealth().reduce();
        assertTrue("Something should have reached the death level", jv.getNeeds().somethingHasReachedTheDeathLevel());
        assertFalse("Javagotchi should be dead", jv.isAlive());
    }

    /**
     * Test for the class JavagotchiImpl.
     */
    @Test
    public void testJavagotchi() {
        final Javagotchi jv1 = new JavagotchiImpl("Po", Gender.MALE, Avatar.PANDA);

        assertTrue("Javagotchi should be alive", jv1.isAlive());
        assertEquals("Initial age should be zero", jv1.getInformation().getAge().toString(), "0");

        assertEquals(jv1.getNeeds().getEnergy().getLevel(), 1.0, EPS);
        assertEquals(jv1.getNeeds().getCleanliness().getLevel(), 1.0, EPS);
        jv1.play();
        assertNotEquals(jv1.getNeeds().getEnergy().getLevel(), 1.0, EPS);
        assertNotEquals(jv1.getNeeds().getCleanliness().getLevel(), 1.0, EPS);


        final Javagotchi jv2 = new JavagotchiImpl("Kitty", Gender.FEMALE, Avatar.CAT);

        assertNotEquals(jv2.getNeeds().getHunger().getLevel(), 1.0, EPS);
        assertNotEquals(jv2.getNeeds().getHappiness().getLevel(), 1.0, EPS);
        jv2.getNeeds().getHappiness().reduce();
        jv2.getNeeds().getHunger().reduce();
        assertNotEquals(jv2.getNeeds().getHunger().getLevel(), 1.0, EPS);
        assertNotEquals(jv2.getNeeds().getHappiness().getLevel(), 1.0, EPS);
        jv2.feed();
        jv2.feed();
        jv2.feed();
        assertEquals(jv2.getNeeds().getHunger().getLevel(), 1.0, EPS);
        assertEquals(jv2.getNeeds().getHappiness().getLevel(), 1.0, EPS);

    }

}
