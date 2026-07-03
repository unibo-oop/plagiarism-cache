package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.AIManager;
import model.DeployManager;

/**
 * A class for testing {@link model.AIManager}.
 */
public class TestAIManager {

    private final TestFactory tf = new TestFactory();
    private final AIManager ai = AIManager.getInstance();

    /**
     * Test.
     */
    @Test
    public void aiTest() {
        ai.init(tf.getPlayers(), tf.getStates());
        DeployManager.getInstance().init(tf.getPlayers());
        deployTest();
        DeployManager.getInstance().clear();
        ai.reset();
    }

    /**
     * DeployTest for AI.
     */
    public void deployTest() {
        DeployManager.getInstance().setTanksToDeploy();
        ai.deployTanks();
        assertEquals(tf.getStatesMap().get("Japan").getTanks(), 4);
    }

    /**
     * Tests {@link model.AIManager#moveTanks()} method.
     */
    @Test
    public void testMoveTanks() {
        ai.init(tf.getPlayers(), tf.getStates());
        DeployManager.getInstance().init(tf.getPlayers());
        assertTrue(ai.moveTanks());
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Japan")).findFirst().get().getTanks(), 4);
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Middle east")).findFirst().get().getTanks(), 1);
        assertTrue(ai.moveTanks());
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Italy")).findFirst().get().getTanks(), 3);
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Spain")).findFirst().get().getTanks(), 8);
        assertTrue(ai.moveTanks());
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Italy")).findFirst().get().getTanks(), 8);
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Spain")).findFirst().get().getTanks(), 3);

        tf.getPlayers().shift();
        assertFalse(ai.moveTanks());
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Scandinavia")).findFirst().get().getTanks(), 1);
        assertEquals(tf.getPlayers().getHead().getStates().stream().filter(st -> st.getName().equals("Germany")).findFirst().get().getTanks(), 1);

        DeployManager.getInstance().clear();
        ai.reset();
    }
}
