package casim.model.wator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link WatorCell}.
 */
class WatorCellTest {
    private static final int MAX_HEALTH = 10;
    private static final int DEAD_HEALTH = 0;
    private static final int PREY_HEAL = 1;
    private static final int PRED_HEAL = 5;

    private WatorCell getPrey() {
        return new WatorCell(WatorCellState.PREY, PREY_HEAL);
    }

    private WatorCell getPred() {
        return new WatorCell(WatorCellState.PREDATOR, PREY_HEAL);
    }

    private WatorCell getDead() {
        return new WatorCell(WatorCellState.DEAD, DEAD_HEALTH);
    }

    /**
     * Test for {@link WatorCell#heal} method.
     */
    @Test
    void testHeal() {
        final var prey = this.getPrey();
        prey.heal();
        assertEquals(PREY_HEAL + PREY_HEAL, prey.getHealth());
        final var pred = this.getPred();
        pred.heal();
        assertEquals(PREY_HEAL + PRED_HEAL, pred.getHealth());
        final var dead = this.getDead();
        dead.heal();
        assertEquals(DEAD_HEALTH, dead.getHealth());
    }

    /**
     * Test for {@link WatorCell#setHealth(int)} method.
     */
    @Test
    void testSetHealth() {
        final var prey = this.getPrey();
        prey.setHealth(PRED_HEAL);
        assertEquals(PRED_HEAL, prey.getHealth());
        assertThrows(IllegalArgumentException.class, () -> {
            prey.setHealth(MAX_HEALTH + PREY_HEAL);
        });
    }

    /**
     * Test for {@link WatorCell#starve} method.
     */
    @Test
    void testStarve() {
        final var pred = this.getPred();
        final var initHealth = pred.getHealth();
        pred.starve();
        assertEquals(initHealth - PREY_HEAL, pred.getHealth());
        pred.setHealth(DEAD_HEALTH);
        pred.starve();
        assertEquals(DEAD_HEALTH, pred.getHealth());
        final var prey = this.getPrey();
        assertThrows(IllegalStateException.class, () -> {
            prey.starve();
        });
    }

    /**
     * Test for {@link WatorCell#reproduce()} method.
     * {@link WatorCellState#PREY} case.
     */
    @Test
    void testPreyReproduce() {
        final var prey = this.getPrey();
        final var deadPreySpawn = prey.reproduce();
        assertTrue(deadPreySpawn.isDead());
        assertEquals(WatorCellState.DEAD, deadPreySpawn.getState());
        prey.setHealth(MAX_HEALTH);
        final var preySpawn = prey.reproduce();
        assertEquals(PREY_HEAL, prey.getHealth());
        assertEquals(WatorCellState.PREY, preySpawn.getState());
        assertEquals(PREY_HEAL, preySpawn.getHealth());
    }

    /**
     * Test for {@link WatorCell#reproduce()} method.
     * {@link WatorCellState#PREDATOR} case.
     */
    @Test
    void testPredReproduce() {
        final var pred = this.getPred();
        final var deadPredSpawn = pred.reproduce();
        assertTrue(deadPredSpawn.isDead());
        assertEquals(WatorCellState.DEAD, deadPredSpawn.getState());
        pred.setHealth(MAX_HEALTH);
        final var predSpawn = pred.reproduce();
        assertEquals(MAX_HEALTH / 2, predSpawn.getHealth());
        assertEquals(WatorCellState.PREDATOR, predSpawn.getState());
        assertEquals(MAX_HEALTH / 2, pred.getHealth());
    }

    /**
     * Test for {@link WatorCell#reproduce()} method.
     * {@link WatorCellState#DEAD} case.
     */
    @Test
    void testDeadReproduce() {
        final var dead = this.getDead();
        final var deadSpawn = dead.reproduce();
        assertEquals(WatorCellState.DEAD, deadSpawn.getState());
        dead.setHealth(MAX_HEALTH);
        assertThrows(UnsupportedOperationException.class, () -> {
            dead.reproduce();
        });
    }

    /**
     * Test for {@link WatorCell#clone(WatorCell)} method.
     */
    @Test
    void testClone() {
        final var dead = this.getDead();
        final var prey = this.getPrey();
        dead.clone(prey);
        assertEquals(prey.getState(), dead.getState());
        assertEquals(prey.getHealth(), dead.getHealth());
        assertEquals(prey.hasMoved(), dead.hasMoved());
    }
}
