package fargoal.model.entity.player.api;

import fargoal.commons.api.Position;
import fargoal.model.entity.commons.api.Entity;
import fargoal.model.entity.monsters.api.Monster;

/**
 * Interface that manages the player character.
 */

public interface Player extends Entity {

    /**
     * Moves the player to a new position.
     * 
     * @param newPosition - The new position to move the player to.
     */
    void move(Position newPosition);

    /**
     * Gets the deepest floor level the player has reached.
     * <p>
     * If the player's current floor level is greater than the previously recorded
     * deepest descent, it updates the deepest descent value.
     * </p>
     * 
     * @return the {@link Integer} that represents the deepest floor level the player has reached.
     */
    Integer getDeepestDescent();

    /**
     * Gets the current level of the player.
     * 
     * @return the {@link Integer} representing the level of the player.
     */
    Integer getLevel();

    /**
     * Gets the current experience points of the player.
     * 
     * @return the {@link Integer} representing the experience points of the player.
     */
    Integer getExperiencePoints();

    /**
     * Gets the required experience points for the next level up.
     * 
     * @return the {@link Integer} representing the required experience points for the next level.
     */
    Integer getExperiencePointsRequired();

    /**
     * Adds experience points to the player's total experience.
     * Ensures that the amount added is non-negative and not null.
     * 
     * @param experiencePointsToAdd - The amount of experience points to be added.
     * 
     * @throws IllegalArgumentException if the experience points to add is null or negative.
     */
    void addExperiencePoints(Integer experiencePointsToAdd);

    /**
     * Gets the player's inventory.
     * 
     * @return The {@link Inventory} of the player.
     */
    Inventory getInventory();

    /**
     * Retrieves the number of monsters the player has defeated.
     * 
     * @return The {@link Integer} representing the number of slain foes.
     */
    Integer getNumberOfSlainFoes();

    /**
     * Retrieves the object that represents player's gold pouch.
     * 
     * @return the {@link Gold} that represents the player gold pouch.
     */
    Gold getPlayerGold();

    /**
     * Gets the gold amount the player is carrying.
     * 
     * @return the {@link Integer} that represents the gold amount player is currently carrying.
     */
    Integer getCurrentGold();

    /**
     * Gets the maximum amount of gold the player can carry.
     * 
     * @return the {@link Integer} that represents the maximum amount of gold the player is able to carry.
     */
    Integer getMaxGoldCapacity();

    /**
     * Checks if the player is carrying the legendary sword.
     * 
     * @return {@code true} if the player has the legendary sword,{@code false} otherwise.
     */
    boolean hasSword();

    /**
     * Sets the player's legendary sword possession status.
     * 
     * @param condition - the new player's legendary sword possession status to set.
     */
    void setHasSword(boolean condition);

    /**
     * Returns the visibility status of the player.
     * 
     * @return {@code true} if the player is visible, {@code false} otherwise.
     */
    boolean isVisible();

    /**
     * Sets the visibility status of the player.
     * 
     * @param condition - the new visibility status to set.
     */
    void setIsVisible(boolean condition);

    /**
     * Returns the light spell status of the player.
     * <p>
     * This checks whether the light spell is active. If true, the light spell is active;
     * otherwise, the light spell is not active.
     * </p>
     * 
     * @return {@code true} if the light spell is active,{@code false} otherwise.
     */
    boolean hasLight();

    /**
     * Sets the light spell status of the player.
     * 
     * @param condition - the new light spell status to set.
     */
    void setHasLight(boolean condition);

    /**
     * Sets the player's skill to the amount given.
     * 
     * @param amount - the new value to set player's skill to.
     */
    void setPlayerSkill(Integer amount);

    /**
     * Increases the player's skill of the amount given.
     * 
     * @param amount - the value to add to player's skill.
     */
    void increasePlayerSkill(Integer amount);

    /**
     * Calculates the amount of damage the player does against the monster 
     * they are currently fighting in a single attack.
     * <p>
     * This method calculates damage based on the player's skill and the 
     * monster's skill, factoring in a random element. The damage is also
     * influenced by the player's level and a predefined damage multiplier.
     * </p>
     * 
     * @param monster - The enemy monster the player is currently fighting.
     * 
     * @return the {@link Integer} that represents the amount of damage dealt to the monster.
     * 
     * @throws IllegalArgumentException if the passed monster is null.
     */
    Integer doDamage(Monster monster);

    /**
     * Handles the damage recieved by the player from a monster's attack.
     * <p>
     * If the player has activated the shield spell, the damage will be negated
     * and the shield spell will be deactivated. Otherwise, the player recieves 
     * the damage, and the player's current health decreases by the damage dealt
     * by the monster.
     * </p>
     * 
     * @param monster - The {@link Monster} whose attack causes the damage.
     */
    void receiveDamage(Monster monster);

    /**
     * This method manages the character level up, given
     * they have enough experience points.
     * Whenever the player levels up their max health and
     * their skill increase, the level increases by one and
     * the next required experience points double.
     * 
     * @return {@code false} if the player has not enough experience points to level up, {@code false} otherwise. 
     */
    boolean levelUp();

    /**
     * Returns the immunity status of the player.
     * 
     * @return {@code true} if the player is immune, {@code false} otherwise.
     */
    boolean isImmune();

    /**
     * Sets the immunity status of the player.
     * 
     * @param condition - the new immunity status to set.
     */
    void setIsImmune(boolean condition);

    /**
     * Uses the invisibility spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one invisibility spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useInvisibilitySpell();

    /**
     * Uses the teleport spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one teleport spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useTeleportSpell();

    /**
     * Uses the shield spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one shield spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useShieldSpell();

    /**
     * Uses the regeneration spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one regeneration spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useRegenerationSpell();

    /**
     * Uses the drift spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one drift spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useDriftSpell();

    /**
     * Uses the light spell from the player's inventory if available.
     * <p>
     * Checks if the player has at least one light spell in their inventory.
     * If the condition is met, it uses the spell.
     * </p>
     */
    void useLightSpell();

    /**
     * Uses the healing potion from the player's inventory if available.
     * <p>
     * Checks if the player has at least one healing potion in their inventory.
     * If the condition is met, it uses the potion.
     * </p>
     */
    void useHealingPotion();

    /**
     * Uses the beacon from the player's inventory if available.
     * <p>
     * Checks if the player has at least one beacon in their inventory.
     * If the condition is met, it uses the beacon.
     * </p>
     */
    void useBeacon();

    /**
     * Starts a battle between the player and the specified monster.
     * <p>
     * The battle consists of a sequence of attacks where both the player and the monster
     * take turns dealing damage. The battle ends when either the player or the monster dies.
     * The method also handles the following actions:
     * <p>
     *  - Notifying the floor manager of the battle or attack events.
     * </p><p>
     *  - Updating the player's skill and experience points based on the battle outcome.
     * </p>
     * 
     * @param monster - The enemy {@link Monster} to battle against.
     */
    void battle(Monster monster);

    /**
     * Sets the attacked status of the player.
     * 
     * @param value - the new immunity status to set. True if the player has been attacked, false otherwise.
     */
    void setIsAttacked(boolean value);

    /**
     * Checks if the player is currently engaged in combat.
     * This method returns {@code true} if the player is in a fight, otherwise {@code false}.
     * 
     * @return {@code true} if the player is fighting, {@code false} otherwise.
     */
    boolean isFighting();
}
