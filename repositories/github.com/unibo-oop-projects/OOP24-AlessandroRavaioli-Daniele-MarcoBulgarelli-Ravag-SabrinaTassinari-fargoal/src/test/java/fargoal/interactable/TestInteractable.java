package fargoal.interactable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fargoal.commons.api.Position;
import fargoal.model.commons.FloorElement;
import fargoal.model.core.GameEngine;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.interactable.pickupable.inside_chest.impl.ChestImpl;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.CeilingTrap;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Explosion;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Pit;
import fargoal.model.interactable.pickupable.inside_chest.trap.impl.Teleport;
import fargoal.model.interactable.pickupable.on_ground.BeaconOnGround;
import fargoal.model.interactable.pickupable.on_ground.SackOfMoney;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;

/**
 * This class tests the interactables and the usables of the game.
 */
class TestInteractable {
    private static FloorManager floorManager = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
    private static final int N_DAMAGE = 5;

     /**
     * This test checks the creation of an item when a chest is opened.
     */
     @Test
     void checkCreationItem() {
          final ChestImpl chest = new ChestImpl(new Position(0, 0), floorManager.getRenderFactory());
          chest.interact(floorManager);
    }

     /**
     * This test checks if the drift spell works: it checks the amount in the inventory after and before use
     * and it checks that when the player falls in a pit, but the spell is cast, there is no damage.
     */
     @Test
     void checkDriftSpell() {
          final int healthBefore = floorManager.getPlayer().getCurrentHealth();
          final int levelFloor = floorManager.getFloorLevel();
          floorManager.getPlayer().getInventory().addDriftSpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberDriftSpells();
          floorManager.getPlayer().useDriftSpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberDriftSpells());
          assertTrue(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.DRIFT.getName()));
          new Pit(floorManager);
          assertEquals(floorManager.getFloorLevel(), levelFloor);
          assertFalse(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.DRIFT.getName()));
          assertEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
          new Pit(floorManager);
          assertNotEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
     }

     /**
     * This test checks if the invisibility spell works: it checks the amount in the inventory after and before use
     * and it checks if the player can be seen or not.
     */
     @Test
     void checkInvisibilitySpell() {
          assertTrue(floorManager.getPlayer().isVisible());
          floorManager.getPlayer().getInventory().addInvisibilitySpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberInvisibilitySpells();
          floorManager.getPlayer().useInvisibilitySpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberInvisibilitySpells());
          assertFalse(floorManager.getPlayer().isVisible());
          assertTrue(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.INVISIBILITY.getName()));
     }

     /**
     * This test checks if the light spell works: it checks the amount in the inventory after and before use
     * and it checks if the light is on.
     */
     @Test
     void checkLightSpell() {
          floorManager.getPlayer().getInventory().addLightSpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberLightSpells();
          floorManager.getPlayer().useLightSpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberLightSpells());
          assertTrue(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.LIGHT.getName()));
          assertTrue(floorManager.getPlayer().hasLight());
          floorManager.getPlayer().getInventory().turnLight();
          assertFalse(floorManager.getPlayer().hasLight());
     }

     /**
     * This test checks if the regeneration spell works: it checks the amount in the inventory after and before use
     * and it checks if the regeneratin of the hit points is faster.
     */
     @Test
     void checkRegenerationSpell() {
          floorManager.getPlayer().getInventory().addRegenerationSpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberRegenerationSpell();
          floorManager.getPlayer().useRegenerationSpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberRegenerationSpell());
          assertTrue(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.REGENERATION.getName()));
     }

     /**
     * This test checks if the shield spell works: it checks the amount in the inventory after and before use.
     */
     @Test
     void checkShieldSpell() {
          floorManager.getPlayer().getInventory().addShieldSpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberShieldSpells();
          floorManager.getPlayer().useShieldSpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberShieldSpells());
          assertTrue(floorManager.getPlayer().getInventory().getSpellCasted().get(SpellType.SHIELD.getName()));
     }

     /**
     * This test checks if the teleport spell works: it checks the amount in the inventory after and before use
     * and it checks if the player is teleported.
     */
     @Test
     void checkTeleportSpell() {
          final Position positionBefore = floorManager.getPlayer().getPosition();
          floorManager.getPlayer().getInventory().addTeleportSpell();
          final int numberBefore = floorManager.getPlayer().getInventory().numberTeleportSpells();
          floorManager.getPlayer().useTeleportSpell();
          assertNotEquals(numberBefore, floorManager.getPlayer().getInventory().numberTeleportSpells());
          assertNotEquals(positionBefore, floorManager.getPlayer().getPosition());
     }

     /**
     * This test checks if the ceiling trap works: it checks if the player is damaged.
     */
     @Test
     void checkCeilingTrap() {
          final int healthBefore = floorManager.getPlayer().getCurrentHealth();
          new CeilingTrap(floorManager);
          assertNotEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
     }

     /**
     * This test checks if the explosion trap works: it checks if the player is damaged.
     */
     @Test
     void checkExplosionTrap() {
          final int healthBefore = floorManager.getPlayer().getCurrentHealth();
          new Explosion(floorManager);
          assertNotEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
     }

     /**
     * This test checks if the pit trap works: it checks if the player is damaged.
     */
     @Test
     void checkPitTrap() {
          final int healthBefore = floorManager.getPlayer().getCurrentHealth();
          new Pit(floorManager);
          assertNotEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
     }

     /**
     * This test checks if the teleport trap works: it checks if the player is teleported.
     */
     @Test
     void checkTeleportTrap() {
          final Position positionBefore = floorManager.getPlayer().getPosition();
          new Teleport(floorManager);
          assertNotEquals(positionBefore, floorManager.getPlayer().getPosition());
     }

     /**
     * This test checks if the beacon works: it checks the amount of beacon in the inventory before and after use, it checks if 
     * the beacon on ground make the player immune and it check if the player, when he teleport himself, is on the beacon.
     */
     @Test
     void checkBeacon() {
          floorManager.getPlayer().getInventory().addBeacon();
          final int numberInInventory = floorManager.getPlayer().getInventory().numberBeacons();
          floorManager.getPlayer().useBeacon();
          assertNotEquals(numberInInventory, floorManager.getPlayer().getInventory().numberBeacons());
          final FloorElement beacon = floorManager.getAllElements().stream().
               filter(e -> e instanceof BeaconOnGround).findFirst().get();
          assertEquals(floorManager.getPlayer().getPosition(), beacon.getPosition());
          floorManager.getAllElements().stream().filter(e -> e instanceof BeaconOnGround).findFirst().get().update(floorManager);
          assertTrue(floorManager.getPlayer().isImmune());
          floorManager.getPlayer().move(floorManager.getFloorMap().getRandomTile());
          assertNotEquals(floorManager.getPlayer().getPosition(), beacon.getPosition());
          floorManager.getPlayer().useTeleportSpell();
          assertEquals(beacon.getPosition(), floorManager.getPlayer().getPosition());
     }

     /**
     * This test checks if the enchanted weapon works: it checks the amount of beacon in the inventory before and after the
     * player find it and it checks if the skill of the player is increased.
     */
     @Test
     void checkEnchantedWeapon() {
          final int skillBefore = floorManager.getPlayer().getSkill();
          final int numberInInventory = floorManager.getPlayer().getInventory().numberEnchantedWeapons();
          floorManager.getPlayer().getInventory().addEnchantedWeapon();
          assertNotEquals(skillBefore, floorManager.getPlayer().getSkill());
          assertNotEquals(numberInInventory, floorManager.getPlayer().getInventory().
               numberEnchantedWeapons());
     }

     /**
     * This test checks if the healing potion works: it checks the amount of potions in the inventory before and after use and
     * it checks if the health is increased after use.
     */
     @Test
     void checkHealingPotion() {
          assertEquals(1, floorManager.getPlayer().getInventory().numberHealingPotions());
          floorManager.getPlayer().getInventory().addHealingPotion();
          assertEquals(2, floorManager.getPlayer().getInventory().numberHealingPotions());
          floorManager.getPlayer().decreaseHealth(N_DAMAGE);
          final int healthBefore = floorManager.getPlayer().getCurrentHealth();
          floorManager.getPlayer().useHealingPotion();
          assertNotEquals(healthBefore, floorManager.getPlayer().getCurrentHealth());
          assertEquals(1, floorManager.getPlayer().getInventory().numberHealingPotions());
     }

     /**
     * This test checks if the magic sack works: it checks the amount of magic sack in the inventory before and after the 
     * player find it and it check how much gold the player can carry.
     */
     @Test
     void checkMagicSack() {
          assertEquals(1, floorManager.getPlayer().getInventory().numberMagicSacks());
          assertEquals(100, floorManager.getPlayer().getMaxGoldCapacity());
          floorManager.getPlayer().getInventory().addMagicSack();
          assertEquals(2, floorManager.getPlayer().getInventory().numberMagicSacks());
          assertEquals(floorManager.getPlayer().getInventory().numberMagicSacks() * 100, 
               floorManager.getPlayer().getMaxGoldCapacity());
     }

     /**
     * This test checks if the map works: it checks the amount of maps in the inventory before and after the player find it and
     * it check if the map has been removed or not.
     */
     @Test
     void checkMap() {
          floorManager.getPlayer().getInventory().addMap();
          assertFalse(floorManager.getPlayer().getInventory().isEmpty());
          floorManager.getPlayer().getInventory().removeMap();
          assertTrue(floorManager.getPlayer().getInventory().isEmpty());
     }

     /**
     * This test checks if the sack of moneythe player find on the ground works: it checks the amount of gold
     * in the inventory before and after the sack has been opened.
     */
     @Test
     void checkSackOfMoney() {
          assertEquals(0, floorManager.getPlayer().getCurrentGold());
          final Interactable sackOfGold = floorManager.getInteractables().stream().
               filter(e -> e instanceof SackOfMoney).findFirst().get();
          floorManager.getPlayer().move(sackOfGold.getPosition());
          sackOfGold.interact(floorManager);
          assertNotEquals(0, floorManager.getPlayer().getCurrentGold());
     }
}
