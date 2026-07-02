package unibo.exiled.model.move;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.ElementalType;

/**
 * An enum containing all the moves in the game.
 */
public enum MagicMove {
    /**
     * A normal basic move.
     */
    TACKLE("Tackles the enemy without much enthusiasm.",
            ConstantsAndResourceLoader.LEVEL_BASE_MOVE, 5.0d, ElementalType.NORMAL),
    /**
     * A normal basic move.
     */
    HEADBUTT("Hits the enemy with own head a bit more enthusiasm.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 8.0d, ElementalType.NORMAL),
    /**
     * A normal basic move.
     */
    BODY_SLAM("Slams the enemy with its own body.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.NORMAL),
    /**
     * A fire basic move.
     */
    FIREBALL("Throws a really sad fireball at the enemy.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 5.0d, ElementalType.FIRE),
    /**
     * A grass basic move.
     */
    LEAFBLADE("Summons a blunted blade made of leaves.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 5.0d, ElementalType.GRASS),
    /**
     * A bolt basic move.
     */
    LIGHTBULB("Lit oneself body to blind the enemy, not very effective.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 5.0d, ElementalType.BOLT),
    /**
     * A water basic move.
     */
    WATERPISTOL("Spits water from the mouth, pretty disgusting but nothing more.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 5.0d, ElementalType.WATER),
    /**
     * A flame move.
     */
    FLAMEWHIRL("Creates a roundel of flaming braces to throw",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.FIRE),
    /**
     * A swift slashing move.
     */
    QUICKSLASH("Swiftly slashes through the enemy with precision.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.NORMAL),
    /**
     * A storm of sharp petals.
     */
    PETALSTORM("Summons a storm of sharp petals to damage the enemy.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.GRASS),
    /**
     * A powerful lightning strike.
     */
    THUNDERSTRIKE("Summons a powerful lightning strike to hit the enemy.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.BOLT),
    /**
     * A sphere of water attack.
     */
    AQUAORB("Forms a sphere of water and hurls it at the enemy.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.WATER),
    /**
     * A raging inferno.
     */
    INFERNO("Unleashes a raging inferno to engulf the enemy.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE, 100.0d, ElementalType.FIRE),
    /**
     * Generates a great storm with lots of thunders and shocks.
     */
    THUNDERSTORM("Summons a storm right over the opponent.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE, 100.0d, ElementalType.BOLT),
    /**
     * Covers the body in electricity and charges the opponent.
     */
    LOCOMOVOLT("Covers the body in electricity and charges the opponent.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE, 50.0d, ElementalType.BOLT),
    /**
     * A simple low-voltage zap.
     */
    ZAP("A simple low-voltage zap.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_FIRST_MOVE, 8.0d, ElementalType.BOLT),
    /**
     * Some grassy tentacles that leech life out of the opponent.
     */
    LEECHERS("Some grassy tentacles that leech life out of the opponent.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE, 50.0d, ElementalType.GRASS),
    /**
     * A humongous water wave thrown at the opponent, breathing is almost
     * impossible!
     */
    TSUNAMI(
            "A humongous water wave thrown at the opponent, breathing is almost impossible!",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_BOSS_MOVE, 60.0d, ElementalType.WATER),
    /**
     * Tries to strangle the opponent with a grass latch.
     */
    LEAFLATCH("Tries to strangle the opponent with a grass latch.",
            ConstantsAndResourceLoader.MIN_LEARNING_LEVEL_SECOND_MOVE, 10.0d, ElementalType.GRASS);

    private final String description;
    private final int minimumLevelToLearn;
    private final double power;
    private final ElementalType type;

    /**
     * The constructor of the move names.
     *
     * @param description         The description of the move.
     * @param minimumLevelToLearn the minimum level needed to learn the magic move.
     * @param power               The power of the move.
     * @param type                The ElementalType of the move.
     */
    MagicMove(final String description,
              final int minimumLevelToLearn, final double power, final ElementalType type) {
        this.description = description;
        this.minimumLevelToLearn = minimumLevelToLearn;
        this.power = power;
        this.type = type;
    }

    /**
     * Gets the description of the move.
     *
     * @return A string describing the move.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the type of the move.
     *
     * @return The ElementalType of the move.
     */
    public ElementalType getType() {
        return this.type;
    }

    /**
     * Gets the minimum level needed to be able to learn the move.
     *
     * @return An integer.
     */
    public int getMinimumLevelToLearn() {
        return this.minimumLevelToLearn;
    }

    /**
     * Gets the power of the move.
     *
     * @return A double representing the power value of the move.
     */
    public double getPower() {
        return this.power;
    }
}
