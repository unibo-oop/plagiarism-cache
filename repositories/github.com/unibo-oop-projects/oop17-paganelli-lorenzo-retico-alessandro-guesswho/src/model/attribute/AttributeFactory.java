package model.attribute;

import java.util.stream.Collectors;
import model.attribute.Feature.Gender;
import java.util.*;
import utilities.Messages;

/**
 * Factory for Attributes.
 * Note that there is no way to include Features that do not belong to the Trait you are describing,
 * in that case an IllegalArgumentException will be thrown.
 */
public final class AttributeFactory {

    private AttributeFactory() {
    }

    /**
     * Allows to build an Attribute, given a Trait and a Set of feature.
     * @param trait 
     *              the trait the Attribute will represent
     * @param features
     *              the features of the Attribute
     * @return the Attribute 
     */
    public static Attribute from(final Trait trait, final Set<Enum<?>> features) {
        return new AttributeImpl(trait, features);
    }

    /**
     * Allows to build an Attribute, given a Trait and a variable number of features.
     * @param trait 
     *              the trait the Attribute will represent
     * @param features
     *              the features of the Attribute
     * @return the Attribute 
     */
    public static Attribute from(final Trait trait, final Enum<?>... features) {
        return AttributeFactory.from(trait, new HashSet<>(Arrays.asList(features)));
    }

    /**
     * Allows to get the Set of Features from a String representation of them, given the Trait they describe.
     * @param features
     *                  the String representation of the Features.
     * @param trait
     *                  the Trait described
     * @return the Set of Features
     */
    public static Set<Enum<?>> getFeatures(final String features, final Trait trait) {
        if (trait.equals(Trait.GENDER)) {
            return new HashSet<>(Arrays.asList(features.contains(Gender.FEMALE.toString().toLowerCase(Locale.ITALIAN)) 
                    ? Gender.FEMALE : Gender.MALE));
        }
        return trait.getPossibleFeatures().stream()
            .flatMap(possibleFeature -> Arrays.stream((Enum<?>[]) possibleFeature.getEnumConstants()))
            .filter(feature -> features.contains(feature.toString().toLowerCase(Locale.ITALIAN)))
            .collect(Collectors.toSet());
    }

    /*------------ NOT USED ------------*/
    /**
     * Allows to get the Trait from a String representation of an Attribute.
     * @param attribute
     *                  the String representation of the Attribute
     * @return the Trait the Attribute describes
     */
    public static Trait getTrait(final String attribute) {
        return Arrays.stream(Trait.values()).filter(trait -> attribute.contains(trait.toString()))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("String does not contain a Trait: " + attribute));
    }

    /**
     * Allows to get an Attribute from a String, words order does not matter.
     * @throws IllegalArgumentException 
     *              in case the String is not an Attribute
     * @param string
     *              the String representing the Attribute
     * @return the Attribute
     */
    public static Attribute fromString(final String string) {
        final Trait trait = AttributeFactory.getTrait(string); 
        return AttributeFactory.from(trait, AttributeFactory.getFeatures(string, trait));
    }

    /**
     * Allows to get an Attribute from the String representation of a Question.
     * @param question
     *              the String representing the question
     * @return the Attribute
     */
    public static Attribute fromQuestion(final String question) {
        return AttributeFactory.fromString(question.replace(Messages.QUESTION_SUFFIX, "")
                .replace(Messages.QUESTION_PREFIX, "")
                .replace(Messages.GENDER_QUESTION_PREFIX, ""));
    }

}
