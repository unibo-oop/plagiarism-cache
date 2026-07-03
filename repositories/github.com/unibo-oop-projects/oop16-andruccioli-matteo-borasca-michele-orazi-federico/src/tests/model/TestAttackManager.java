package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.AttackManager;
import model.exceptions.GameOverException;

/**
 * A class for testing {@link model.AttackManager}.
 */
public class TestAttackManager {

    private static final String ITALY = "Italy";
    private static final String GERMANY = "Germany";
    private static final int NUMBER_OF_TANKS_AFTER_ATTACK = 5;

    /**
     * 
     */
    @Test
    public void atkMgrTest() {
        final TestFactory tf = new TestFactory();
        final AttackManager am = AttackManager.getInstance();
        am.init(tf.getPlayers());
        try {
            am.attackStateCheck(tf.getStatesMap().get(ITALY), tf.getStatesMap().get(GERMANY), 3);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            am.evaluateResults();
        } catch (GameOverException e) {
            System.out.println(tf.getPlayers().getHead().getName() + " has won the game! His objective was "
                    + tf.getPlayers().getHead().getAim().toString());
        }
        System.out.println("Conquered: " + am.hasConquered());
        if (am.hasConquered()) {
            assertEquals(tf.getPlayers().getHead().getStates().size(), NUMBER_OF_TANKS_AFTER_ATTACK);
            assertEquals(tf.getStatesMap().get(GERMANY).getTanks(), 3);
            assertEquals(tf.getStatesMap().get("Scandinavia").getPlayer().getStates().size(), 1);
            try {
                am.moveTanks(2);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            assertEquals(tf.getStatesMap().get(GERMANY).getTanks(), NUMBER_OF_TANKS_AFTER_ATTACK);
            assertEquals(tf.getStatesMap().get(ITALY).getTanks(), 1);
            try {
                am.attackStateCheck(tf.getStatesMap().get(GERMANY), tf.getStatesMap().get("Scandinavia"), 3);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                am.evaluateResults();
            } catch (GameOverException e) {
                System.out.println(tf.getPlayers().getHead().getName() + " has won the game! His objective was "
                        + tf.getPlayers().getHead().getAim().toString());
            }
            System.out.println("Conquered: " + am.hasConquered());
            if (am.hasConquered()) {
                assertTrue(tf.getPlayers().getHead().getRegions().contains(tf.getStatesMap().get(ITALY).getRegion()));
                assertEquals(tf.getPlayers().getHead().getRegions().size(), 1);
                assertTrue(tf.getPlayers().getHead().getRegions().contains(tf.getStatesMap().get(ITALY).getRegion()));
                assertEquals(tf.getPlayers().getHead().getBonusCardsList().size(), 1);
                assertEquals(tf.getPlayers().size(), 2);
            }
        } else {
            assertEquals(tf.getStatesMap().get(ITALY).getTanks(), NUMBER_OF_TANKS_AFTER_ATTACK);
            assertEquals(tf.getStatesMap().get(GERMANY).getTanks(), 1);
        }
    }

}
