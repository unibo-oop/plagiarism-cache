package net.pokemonbt.controller.resources;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * This class handles some of the session data for the game as settings, pokemon
 * teams during the pokemon selection and others.
 */
public final class GameSession {

    private static final int MAX_TEAM_SIZE = 6;
    private static final int MIN_TEAM_SIZE = 1;
    private static final int DEFAULT_TEAM_SIZE = 6;
    private static final List<String> DIFFICULTIES = List.of("Easy", "Medium", "Hard");
    private static final String DEFAULT_DIFFICULTY = DIFFICULTIES.getFirst();

    private static String playerName = "Player";
    private static String enemyName = "Enemy";

    private static int enemyTeamSize = DEFAULT_TEAM_SIZE;
    private static String enemyDifficulty = DEFAULT_DIFFICULTY;

    private static GameSpeed speed = GameSpeed.NORMAL;

    private static transient List<Pokemon> teamBuffer = new LinkedList<>();

    /** Empty builder for the utility GameSession class. */
    private GameSession() {
    }

    /**
     * @return the copy of the player's pokemon team during pokemon selection.
     */
    public static synchronized List<Pokemon> getCopyOfTeam() {
        return List.copyOf(teamBuffer);
    }

    /**
     * Adds a new selected member to the player's pokemon team list.
     * 
     * @param pokemon the pokemon to be added.
     */
    public static void addMember(final Pokemon pokemon) {
        teamBuffer.add(pokemon);
    }

    /**
     * Removes the last pokemon added to the team.
     */
    public static void removeLast() {
        teamBuffer.removeLast();
    }

    /**
     * Removes a precise pokemon from the player's pokemon team.
     * 
     * @param pokemon the pokemon to be removed.
     */
    public static void removeMember(final Pokemon pokemon) {
        teamBuffer.remove(pokemon);
    }

    /**
     * Removes all the copy of a member of the team.
     * 
     * @param pokemon the pokemon to be removed.
     */
    public static void removeAllCopyOfMember(final Pokemon pokemon) {
        teamBuffer.removeAll(getCopyOfTeam().stream()
                .filter(t -> t.getID().equals(pokemon.getID())).toList());
    }

    /**
     * Clears the player's team.
     */
    public static void clearTeam() {
        teamBuffer.clear();
    }

    /**
     * @return Getter.
     */
    public static synchronized int getMaxTeamSize() {
        return MAX_TEAM_SIZE;
    }

    /**
     * @return Getter.
     */
    public static synchronized int getMinTeamSize() {
        return MIN_TEAM_SIZE;
    }

    /**
     * @return Getter.
     */
    public static synchronized int getEnemyTeamSize() {
        return enemyTeamSize;
    }

    /**
     * @return Getter.
     */
    public static synchronized List<String> getDifficultyList() {
        return List.copyOf(DIFFICULTIES);
    }

    /**
     * @return Getter.
     */
    public static synchronized String getCurrentEnemyDifficulty() {
        return enemyDifficulty;
    }

    /**
     * @param selectedTeamSize Value to set.
     */
    public static void setEnemyTeamSize(final int selectedTeamSize) {
        enemyTeamSize = Objects.requireNonNull(selectedTeamSize);
    }

    /**
     * @param enemyDifficultyClass Value to set.
     */
    public static void setEnemyDifficulty(final String enemyDifficultyClass) {
        if (DIFFICULTIES.contains(enemyDifficultyClass)) {
            enemyDifficulty = Objects.requireNonNull(enemyDifficultyClass);
        }
    }

    /**
     * @return Getter.
     */
    public static synchronized String getPlayerName() {
        return playerName;
    }

    /**
     * @param name Value to set.
     */
    public static void setPlayerName(final String name) {
        playerName = Objects.requireNonNull(name);
    }

    /**
     * @return Getter.
     */
    public static synchronized String getEnemyName() {
        return enemyName;
    }

    /**
     * @param name Value to set.
     */
    public static void setEnemyName(final String name) {
        enemyName = Objects.requireNonNull(name);
    }

    /**
     * @return Getter.
     */
    public static synchronized GameSpeed getSpeed() {
        return speed;
    }

    /**
     * @param speedSelected Value to set.
     */
    public static void setSpeed(final GameSpeed speedSelected) {
        speed = Objects.requireNonNull(speedSelected);
    }

    /**
     * An enum indicating the speed of the battle.
     */
    public enum GameSpeed {
        FAST("Fast"),
        NORMAL("Normal"),
        SLOW("Slow");

        private static final float MODIFIER = 1.5f;

        private final String value;

        GameSpeed(final String value) {
            this.value = value;
        }

        /**
         * @param str The speed to obtain
         * @return The corresponding value of this enum.
         */
        public static Optional<GameSpeed> stringToSpeed(final String str) {
            Objects.requireNonNull(str);
            if (str.isBlank()) {
                return Optional.empty();
            }
            for (final var spe : values()) {
                if (spe.displayAs().equals(str)) {
                    return Optional.of(spe);
                }
            }
            return Optional.empty();
        }

        /**
         * @return The {@link String} associated to the speed.
         */
        public String displayAs() {
            return this.value;
        }

        /**
         * @return A {@link Float} with the amount of seconds to wait between
         *         an action and the next during the battle.
         */
        public Float timeSpan() {
            return (1 + this.ordinal()) * MODIFIER;
        }
    }
}
