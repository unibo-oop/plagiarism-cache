package model.abilities;

/**
 * Abilities grants a bonus to the Unit that gets them.
 */
public enum BasicAbilities implements Ability {

    /**
     * The Ability to walk on normal terrains.
     */
    WALKONLAND("WalkOnLand"),
    /**
     * The Ability to walk on water terrains.
     */
    WALKONWATER("WalkOnWater"),
    /**
     * The Ability to walk on mountain terrains.
     */
    WALKONMOUNTAIN("WalkOnMountain");

    private final String id;

    BasicAbilities(final String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
