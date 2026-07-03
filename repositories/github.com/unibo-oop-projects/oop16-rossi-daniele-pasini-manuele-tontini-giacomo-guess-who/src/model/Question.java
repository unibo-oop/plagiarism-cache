package model;

import java.util.function.Predicate;

/**
 *  It represents the questions that are used inside the game between the human player and the AI.
 */
public class Question {

    private final String text;
    private final Predicate<Character> filter;
    private final Question.TYPE type;

    /**
     * @param text question's text
     * @param filter question's filter
     * @param type question's type
     */
    public Question(final String text, final Predicate<Character> filter, final TYPE type) {
        this.text = text;
        this.filter = filter;
        this.type = type;
    }

    /**
     * @return the question's text
     */
    public String getText() {
        return text;
    }

    /**
     * @return The filter needed to get the characters that reflect the desired physical characteristics.
     */
    public Predicate<Character> getFilter() {
        return filter;
    }

    /**
     * @return the question type
     */
    public TYPE getType() { 
        return this.type;
    }

    /**
     * It defines the question types, it is used to exclude question that have relationships with each other.
     */
    public enum TYPE {
        /***/
        GENDER, EYE_COLOR, SKIN_COLOR, HAIR_COLOR, BEARD_COLOR, MUSTACHE_COLOR, HAT, EARINGS, GLASSES, HAIR_TYPE, DRESS_TYPE;
    }

    @Override
    public String toString() {
        return "Question [text=" + text + "]";
    }
}
