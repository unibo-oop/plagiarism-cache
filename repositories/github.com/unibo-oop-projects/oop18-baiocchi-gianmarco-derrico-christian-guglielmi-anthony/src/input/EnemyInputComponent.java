package input;

/**
 * This interface models a simply bot that controls the enemy behavior.
 * It extends {@link InputComponent}.
 */
public interface EnemyInputComponent extends InputComponent {

    /**
     * Gets the current difficulty level.
     * @return current difficulty level.
     */
    int getDifficultyLevel();

    /**
     * Increments difficulty level.
     */
    void incrementDifficultyLevel();

    /**
     * Generates a new Command, according to the logic of the Enemy Input Component.
     */
    void generateCommand();

}
