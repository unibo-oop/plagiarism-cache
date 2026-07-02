package starcatraz.model.game;

/**
 * Possible choices the player can make when being attacked by a robot.
 */
public enum RobotAttackChoice {
    DISCARD_HAND("Discard your hand"),
    DISCARD_FROM_DECK("Discard five cards from the deck"),
    USE_CHIP("Use a chip"),
    USE_ROCKET("Use a rocket");

    private String name;

    /**
     * Constructor for RobotAttackChoice.
     * @param name
     */
    RobotAttackChoice(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
