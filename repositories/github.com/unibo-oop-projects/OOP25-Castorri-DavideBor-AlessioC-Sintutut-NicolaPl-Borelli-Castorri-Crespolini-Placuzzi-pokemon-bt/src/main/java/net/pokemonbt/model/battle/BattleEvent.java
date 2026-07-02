package net.pokemonbt.model.battle;

/**
 * Class that represents fases of a turn.
 */
public enum BattleEvent {
    START_BATTLE("Start_battle"),
    END_BATTLE("End_battle"),
    TURN_START("Turn_start"),
    TURN_END("Turn_end"),
    SWITCH("Switch"),
    BEFORE_DAMAGE("Before_damage"),
    AFTER_DAMAGE("After_damage");

    private final String displayAs;

    BattleEvent(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * @return The string of how the fase of the battle should be displayed
     */
    public String displayAs() {
        return this.displayAs;
    }

    /**
     * @param string the string to convert to a BattleEvent
     * 
     * @return The BattleEvent corresponding to the string
     */
    public static BattleEvent fromString(final String string) {
        if (string.isBlank()) {
            throw new IllegalArgumentException();
        }
        for (final var i : values()) {
            if (i.displayAs().equalsIgnoreCase(string)) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
}
