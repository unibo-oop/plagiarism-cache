package view.controllers;

/**
 * An annotation that represents a {@link TrainingArea}.
 *
 */
public @interface Area {

    /**
     * The training area that belongs to the minigame.
     * 
     * @return trainingArea
     *          the area of belonging
     */
    String trainingArea();
}
