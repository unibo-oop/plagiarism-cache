package fargoal.model.entity.monsters.api;

/**
 * Enum to classify different types of monsters.
 */
public enum MonsterType {
    /**
     * An enemy that will always steal when attacking.
     */
    ROGUE,
    /**
     * An enemy that simply attack the player.
     */
    BARBARIAN,

    /**
     * An enemy that bring with him healing potions and can drink them.
     */
    MONK,

    /**
     * An enemy who's generally invisible.
     */
    ASSASSIN,

    /**
     * An enemy that often carry a War Shield.
     */
    WAR_LORD,

    /**
     * An enemy who instantly steals spells and disguised as one of the other monsters.
     */
    MAGE,

    /**
     * A monster that have the ability to climb walls.
     */
    SPIDER
}
