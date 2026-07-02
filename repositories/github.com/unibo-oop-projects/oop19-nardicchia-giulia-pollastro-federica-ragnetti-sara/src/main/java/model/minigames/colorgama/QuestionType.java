package model.minigames.colorgama;

/**
 * Enumeration of the possible question.
 *
 */
public enum QuestionType {

    /**
     * The darkest color.
     */
    DARKEST("Find the darkest color"),

    /**
     * The lightest color.
     */
    LIGHTEST("Find the lightest color"),

    /**
     * The color indicated.
     */
    COLOR("Find the color indicated");

    private String question;

    QuestionType(final String question) {
        this.question = question;
    }

    /**
     * Getter for the sentence of the question.
     * 
     * @return name
     *          the name of the question type
     */
    public final String getQuestionName() {
        return this.question;
    }
}
