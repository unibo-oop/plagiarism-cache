package model.question;

import model.attribute.Attribute;

/**
 * Modeling interface for a question.
 * Questions can be obtained using QuestionFactory.
 */
public interface Question {

    /**
     * Allows to get the Attribute this question represents.
     * @return the Attribute
     */
    Attribute toAttribute();

    /**
     * Allows to get a String representation of the Question.
     * @return the String
     */
    String toString();

}
