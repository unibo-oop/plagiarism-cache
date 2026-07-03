package tests.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import model.DeployManager;
import model.exceptions.GameOverException;
import model.player.Player;
import model.state.StateInfo;

/**
 * A class for testing {@link model.DeployManager}.
 */
public class TestDeployManager {

    private static final int NUMBER_OF_TANKS_AFTER_DEPLOYMENT = 26;
    private static final int NUMBER_OF_TANKS_AFTER_MOVEMENT = 13;
    private static final int NUMBER_OF_TANKS_GERMANY = 21;
    private static final int NUMBER_OF_TANKS_SPAIN = 18;
    private static final String ITALY = "Italy";

    /**
     * 
     */
    @Test
    public void deployMgrTest() {
        final TestFactory tf = new TestFactory();
        final DeployManager dm = DeployManager.getInstance();
        dm.init(tf.getPlayers());
        while (!dm.isDeploymentPhaseFinished()) {
            dm.setTanksToDeploy();
            final Map<StateInfo, Integer> choice1 = new HashMap<>();
            choice1.put(tf.getStatesMap().get(ITALY), tf.getStatesMap().get(ITALY).getPlayer().getTanksToDeploy());
            try {
                dm.assignmentCheck(choice1);
            } catch (IllegalArgumentException | GameOverException e) {
                e.printStackTrace();
            }
            choice1.clear();
            tf.getPlayers().shift();
            dm.setTanksToDeploy();
            final Map<StateInfo, Integer> choice2 = new HashMap<>();
            choice2.put(tf.getStatesMap().get("Germany"), tf.getStatesMap().get("Germany").getPlayer().getTanksToDeploy());
            try {
                dm.assignmentCheck(choice2);
            } catch (IllegalArgumentException | GameOverException e) {
                e.printStackTrace();
            }
            choice2.clear();
            tf.getPlayers().shift();
            dm.setTanksToDeploy();
            final Map<StateInfo, Integer> choice3 = new HashMap<>();
            choice3.put(tf.getStatesMap().get("China"), tf.getStatesMap().get("China").getPlayer().getTanksToDeploy());
            try {
                dm.assignmentCheck(choice3);
            } catch (IllegalArgumentException | GameOverException e) {
                e.printStackTrace();
            }
            choice3.clear();
            tf.getPlayers().shift();
        }
        for (final Player p : tf.getPlayers()) {
            assertEquals(p.getTanksToDeploy(), 0);
            assertEquals(p.getTotalTanksToDeploy(), 0);
        }
        assertEquals(tf.getStatesMap().get(ITALY).getTanks(), NUMBER_OF_TANKS_AFTER_DEPLOYMENT);
        assertEquals(tf.getStatesMap().get("Germany").getTanks(), NUMBER_OF_TANKS_GERMANY);
        assertEquals(tf.getStatesMap().get("China").getTanks(), NUMBER_OF_TANKS_AFTER_DEPLOYMENT);

        try {
            dm.movementStateCheck(tf.getStatesMap().get(ITALY), tf.getStatesMap().get("Spain"), NUMBER_OF_TANKS_AFTER_MOVEMENT);
        } catch (IllegalArgumentException | GameOverException e) {
            e.printStackTrace();
        }
        assertEquals(tf.getStatesMap().get(ITALY).getTanks(), NUMBER_OF_TANKS_AFTER_MOVEMENT);
        assertEquals(tf.getStatesMap().get("Spain").getTanks(), NUMBER_OF_TANKS_SPAIN);

    }

}
