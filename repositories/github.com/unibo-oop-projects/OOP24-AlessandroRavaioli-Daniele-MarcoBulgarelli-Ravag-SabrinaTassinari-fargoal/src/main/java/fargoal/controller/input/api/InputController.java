package fargoal.controller.input.api;

/**
 * KEYS:
 * SPACE BAR -> INTERACT
 * ARROW KEYS -> MOVE
 * H -> HEALING POTION
 * B -> PLACE BEACON
 * T -> TELEPORT SPELL
 * S -> SHIELD SPELL
 * D -> DRIFT SPELL
 * R -> REGENERATION SPELL
 * I -> INVISIBILITY SPELL
 * L -> LIGHT SPELL
 * O -> if the LIGHT SPELL is on it turns on and off the light
 */

/**
 * Interface that decide how to process inputs.
 */
public interface InputController {

    /**
     * Method that return if the player is moving up.
     * 
     * @return - true if the player is moving up, false otherwise
     */
    boolean isMoveUp();

    /**
     * Method that return if the player is moving down.
     * 
     * @return - true if the player is moving down, false otherwise
     */
    boolean isMoveDown();

    /**
     * Method that return if the player is moving left.
     * 
     * @return - true if the player is moving left, false otherwise
     */
    boolean isMoveLeft();

    /**
     * Method that return if the player is moving right.
     * 
     * @return - true if the player is moving right, false otherwise
     */
    boolean isMoveRight();

    /**
     * Method that return if the player is interacting with
     * a FloorElement.
     * 
     * @return - true if the player is interacting with a FloorElement, false otherwise
     */
    boolean isInteracting();

    /**
     * Method that return if the player is using an HealingPotion.
     * 
     * @return - true if the player is using an HealingPotion, false otherwise
     */
    boolean isUsingHealingPotion();

    /**
     * Method that return if the player is placing a Beacon.
     * 
     * @return - true if the player is placing a Beacon, false otherwise
     */
    boolean isPlacingBeacon();

    /**
     * Method that return if the player is using a TeleportSpell.
     * 
     * @return - true if the player is using a TeleportSpell, false otherwise
     */
    boolean isUsingTeleportSpell();

    /**
     * Method that return if the player is using a ShieldSpell.
     * 
     * @return - true if the player is using a ShieldSpell, false otherwise
     */
    boolean isUsingShieldSpell();

    /**
     * Method that return if the player is using a DriftSpell.
     * 
     * @return - true if the player is using a DriftSpell, false otherwise
     */
    boolean isUsingDriftSpell();

    /**
     * Method that return if the player is using a RegenerationSpell.
     * 
     * @return - true if the player is using a RegenerationSpell, false otherwise
     */
    boolean isUsingRegenerationSpell();

    /**
     * Method that return if the player is using an InvisibilitySpell.
     * 
     * @return - true if the player is using an InvisibilitySpell, false otherwise
     */
    boolean isUsingInvisibilitySpell();

    /**
     * Method that return if the player is using a LightSpell.
     * 
     * @return - true if the player is using a LightSpell, false otherwise
     */
    boolean isUsingLightSpell();

    /**
     * Method that return if the player is turning light.
     * 
     * @return - true if the player is turning light, false otherwise
     */
    boolean isTurningLight();
}
