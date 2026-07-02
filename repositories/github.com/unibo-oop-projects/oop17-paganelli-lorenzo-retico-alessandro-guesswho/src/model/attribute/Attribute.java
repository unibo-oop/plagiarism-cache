package model.attribute;

import java.util.Set;
import model.question.Question;

/** 
 * Modeling interface for an Attribute, composed by a Trait and a Set of Feature.
 * Attributes can be obtained using AttributeFactory.
 */
public interface Attribute {

    /**
     * Getter method.
     * @return the Trait this attribute describes 
     *          for instance: Hairs, Beard..
     */
    Trait getTrait();

    /**
     * Getter method.
     * @return an unmodifiable Set of Enum<?> representing the Attribute's Set of features
     *          for instance: Color.BLACK, HairStyle.CURLY..
     */
    Set<Enum<?>> getFeatures();

    /**
     * Allows to get all the possible questions from this Attribute.
     * @return a Set of Question each one a possible question about this Attribute (one for each feature).
     */
    Set<Question> possibleQuestions();

}
