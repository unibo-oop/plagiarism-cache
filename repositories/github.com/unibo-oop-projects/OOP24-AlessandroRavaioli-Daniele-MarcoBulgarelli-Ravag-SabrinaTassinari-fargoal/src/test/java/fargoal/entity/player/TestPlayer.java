package fargoal.entity.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import fargoal.commons.api.Position;
import fargoal.model.core.GameEngine;
import fargoal.model.entity.player.impl.PlayerImpl;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.monsters.impl.MonsterFactoryImpl;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;

//CHECKSTYLE: MagicNumber OFF
//Deactivates all magic numbers as they are needed for test porpouses.
class TestPlayer {

    private static final Logger LOGGER = Logger.getLogger(TestPlayer.class.getName());

    private final FloorManager manager = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
    private final PlayerImpl player = (PlayerImpl) manager.getPlayer();

    @Test
    void testInitialization() {
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getExperiencePoints());
        assertEquals(200, player.getExperiencePointsRequired());
        assertNotNull(player.getCurrentHealth());
        assertNotNull(player.getSkill());
        assertEquals(0, player.getCurrentGold());
        assertEquals(100, player.getMaxGoldCapacity());
        assertNotNull(player.getInventory());
        assertEquals(0, player.getNumberOfSlainFoes());
        assertNotNull(player.getPlayerGold());
        assertEquals("PLAYER", player.getTag());
        assertFalse(player.hasSword());
        assertFalse(player.isFighting());
        assertFalse(player.isAttacked());
        assertTrue(player.isVisible());
        assertFalse(player.hasLight());
        assertFalse(player.isImmune());
        assertFalse(player.isDead());
    }

    @Test
    void testSetters() {
        player.setPlayerSkill(0);
        assertEquals(0, player.getSkill());
        player.setIsAttacked(true);
        assertTrue(player.isAttacked());
        player.setHasSword(true);
        assertTrue(player.hasSword());
        player.setIsVisible(false);
        assertFalse(player.isVisible());
        player.setHasLight(true);
        assertTrue(player.hasLight());
        player.setIsImmune(true);
        assertTrue(player.isImmune());
    }

    @Test
    void testIncreasePlayerValues() {
        player.setPlayerSkill(0);
        player.increasePlayerSkill(10);
        assertEquals(10, player.getSkill());
        player.addExperiencePoints(22);
        assertEquals(22, player.getExperiencePoints());
        for (int i = 0; i < 10; i++) {
            player.increaseNumberOfSlainFoes();
        }
        assertEquals(10, player.getNumberOfSlainFoes());
    }

    @Test
    void testLevelUp() {
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getExperiencePoints());
        assertEquals(200, player.getExperiencePointsRequired());
        player.addExperiencePoints(200);
        player.levelUp();
        assertEquals(2, player.getLevel());
        assertEquals(200, player.getExperiencePoints());
        assertEquals(400, player.getExperiencePointsRequired());
        player.addExperiencePoints(1000);
        player.levelUp();
        player.levelUp();
        assertEquals(4, player.getLevel());
        assertEquals(1200, player.getExperiencePoints());
        assertEquals(1600, player.getExperiencePointsRequired());
        player.addExperiencePoints(399);
        player.levelUp();
        assertEquals(4, player.getLevel());
        assertEquals(1599, player.getExperiencePoints());
        assertEquals(1600, player.getExperiencePointsRequired());
    }

    @Test
    void testMovement() {
        player.move(new Position(0, 0));
        assertEquals(0, player.getPosition().x());
        assertEquals(0, player.getPosition().y());
        player.move(new Position(1, 0));
        assertEquals(1, player.getPosition().x());
        assertEquals(0, player.getPosition().y());
        player.move(new Position(1, 1));
        assertEquals(1, player.getPosition().x());
        assertEquals(1, player.getPosition().y());
        player.move(new Position(15, 15));
        assertEquals(15, player.getPosition().x());
        assertEquals(15, player.getPosition().y());
    }

    @Test
    void testBattleWon() {
        final var monsterFactory = new MonsterFactoryImpl(1);
        final Monster monster = monsterFactory.generate(new Position(0, 0), manager, manager.getRenderFactory());
        assertNotNull(monster);

        monster.setHealth(1);
        final int initialExp = player.getExperiencePoints();
        final int initialFoes = player.getNumberOfSlainFoes();

        player.battle(monster);

        assertFalse(player.isFighting());
        assertFalse(player.isAttacked());
        assertTrue(player.getExperiencePoints() > initialExp);
        assertEquals(initialFoes + 1, player.getNumberOfSlainFoes());
    }

    @Test
    void testBattleLost() {
        final var monsterFactory = new MonsterFactoryImpl(1);
        final Monster monster = monsterFactory.generate(new Position(0, 0), manager, manager.getRenderFactory());
        assertNotNull(monster);

        player.getInventory().removeHealingPotion();
        player.setHealth(1);

        player.battle(monster);

        assertFalse(player.isFighting());
        assertTrue(player.isDead());
    }

    @Test
    void testPlayerDamage() {
        final var monsterFactory = new MonsterFactoryImpl(1);
        final Monster monster = monsterFactory.generate(new Position(0, 0), manager, manager.getRenderFactory());
        assertNotNull(monster);

        final int damage = player.doDamage(monster);
        assertNotNull(damage);

        monster.receiveDamage();
        assertFalse(monster.isHealthy());

        player.receiveDamage(monster);
        assertFalse(player.isHealthy());
    }

    @Test
    void testPlayerDeath() {
        player.setHealth(0);
        assertTrue(player.isDead());
    }

    @Test
    void testRegenerationNormalCondition() {
        player.decreaseHealth(1);
        final int hpBefore = player.getCurrentHealth();

        try {
            Thread.sleep(11_000);
        } catch (InterruptedException e) {
            LOGGER.warning("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        player.passiveRegeneration();
        final int hpAfter = player.getCurrentHealth();

        assertEquals(hpBefore + 1, hpAfter);
    }

    @Test
    void testRegenerationSpellandTemple() {
        player.decreaseHealth(1);
        final int hpBefore = player.getCurrentHealth();

        player.getInventory().getSpellCasted().put(SpellType.REGENERATION.getName(), true);
        player.move(manager.getTemple().getPosition());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.warning("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        player.passiveRegeneration();
        final int hpAfter = player.getCurrentHealth();

        assertEquals(hpBefore + 1, hpAfter);
    }

    @Test
    void testUseItems() {
        final int invisibilityBefore = player.getInventory().numberInvisibilitySpells();
        final int teleportBefore = player.getInventory().numberTeleportSpells();
        final int shieldBefore = player.getInventory().numberShieldSpells();
        final int regenerationBefore = player.getInventory().numberRegenerationSpell();
        final int driftBefore = player.getInventory().numberDriftSpells();
        final int lightBefore = player.getInventory().numberLightSpells();
        final int potionBefore = player.getInventory().numberHealingPotions();
        final int beaconBefore = player.getInventory().numberBeacons();

        player.useInvisibilitySpell();
        player.useTeleportSpell();
        player.useShieldSpell();
        player.useRegenerationSpell();
        player.useDriftSpell();
        player.useLightSpell();
        player.useBeacon();
        player.useHealingPotion();

        final int invisibilityAfter = player.getInventory().numberInvisibilitySpells();
        final int teleportAfter = player.getInventory().numberTeleportSpells();
        final int shieldAfter = player.getInventory().numberShieldSpells();
        final int regenerationAfter = player.getInventory().numberRegenerationSpell();
        final int driftAfter = player.getInventory().numberDriftSpells();
        final int lightAfter = player.getInventory().numberLightSpells();
        final int potionAfter = player.getInventory().numberHealingPotions();
        final int beaconAfter = player.getInventory().numberBeacons();

        assertEquals(Math.max(0, invisibilityBefore - 1), invisibilityAfter);
        assertEquals(Math.max(0, teleportBefore - 1), teleportAfter);
        assertEquals(Math.max(0, shieldBefore - 1), shieldAfter);
        assertEquals(Math.max(0, regenerationBefore - 1), regenerationAfter);
        assertEquals(Math.max(0, driftBefore - 1), driftAfter);
        assertEquals(Math.max(0, lightBefore - 1), lightAfter);
        assertEquals(Math.max(0, potionBefore - 1), potionAfter);
        assertEquals(Math.max(0, beaconBefore - 1), beaconAfter);

    }

    @Test
    void testDeepestDescent() {
        assertEquals(1, player.getDeepestDescent());
        player.setDeepestDescent(3);
        assertEquals(3, player.getDeepestDescent());
    }
}
// CHECKSTYLE:ON MagicNumber
