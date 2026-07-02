package model.question;

import model.attribute.*;

/**
 * Factory for Questions.
 */
public final class QuestionFactory {

    private QuestionFactory() {
    }

    /**
     * Allows to get a Question given an Attribute.
     * @throws IllegalArgumentException
     *                  in case of null argument
     * @param attribute the Attribute
     * @return the Question
     */
    public static Question from(final Attribute attribute) {
        return new QuestionImpl(attribute);
    }

    /**
     * Allows to get a Question given a Trait and a Set of Features.
     * @throws IllegalArgumentException
     *                  in case of null argument
     *                  or in case the Trait does not support the Features
     * @param trait the Trait
     * @param features the Features
     * @return the Question
     */
    public static Question from(final Trait trait, final Enum<?>...features) {
        return new QuestionImpl(AttributeFactory.from(trait, features));
    }

}
