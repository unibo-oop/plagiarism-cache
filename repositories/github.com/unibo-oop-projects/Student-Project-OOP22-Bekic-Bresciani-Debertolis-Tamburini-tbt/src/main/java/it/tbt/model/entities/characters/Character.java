package it.tbt.model.entities.characters;

import java.util.Set;
import java.util.Optional;

import it.tbt.model.entities.Entity;
import it.tbt.model.entities.items.Armor;
import it.tbt.model.entities.items.Weapon;

/**
 * Generic Character.
 */
public interface Character extends Entity {

    /**
     * Get the current character's health.
     * @return current character's health
     */
    int getHealth();

    /**
     * Get the maximum character's health.
     * @return maximum character's health
     */
    int getMaxHealth();

    /**
     * Get the attack of the character.
     * @return character's attack
     */
    int getAttack();

    /**
     * Get the character's speed.
     * @return character's speed
     */
    int getSpeed();

    /**
     * The character take damage, decreasing the health if INVINCIBLE status is found.
     * @param damage        the damage taken by the character
     */
    void takeDamage(int damage);

    /**
     * set custom health, the character health must be always under maxHealth.
     * @param health
     */
    void setHealth(int health);

    /**
     * Get all the active statuses.
     * @return a Set of the active statuses
     */
    Set<Status> getStatuses();

    /**
     * Add a status to the character.
     * @param status
     */
    void addStatus(Status status);

    /**
     * Remove the given status.
     * @param status
     * @return true if the status was found and removed, false otherwise
     */
    boolean removeStatus(Status status);

    /**
     * Get the character's equipped weapon.
     * @return Optional<Weapon>
     */
    Optional<Weapon> getWeapon();

    /**
     * Get the weapon attack.
     * @return int
     */
    int getWeaponAttack();

    /**
     * Equipe the given weapon.
     * @param weapon
     */
    void equipeWeapon(Weapon weapon);

    /**
     * Get the character's equipped armor.
     * @return Optional<Armor>
     */
    Optional<Armor> getArmor();

    /**
     * Get the armor defence.
     * @return int
     */
    int getArmorDefence();

    /**
     * Equipe the given armor.
     * @param armor
     */
    void equipeArmor(Armor armor);
}
