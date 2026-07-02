package model.character;

import java.util.Set;
import model.attribute.Attribute;

/**
 * Modeling interface for a Character, composed by a Name and a Set of Attribute.
 * Characters can be obtained using CharacterBuilder.
 */
public interface Character {

    /**
     * Getter method.
     * @return a String representing Character's name
     */
    String getName();

    /**
     * Getter method.
     * @return an unmodifiable Set of Attributes representing Character's attributes 
     */
    Set<Attribute> getAttributes();

    /**
     * Checks if the Character has a particular Attribute.
     * @throws IllegalArgumentException 
     *              in case of null argument
     * @param attribute
     *              the Attribute to seek for
     * @return a boolean 
     *              true if the Character has the attribute, false otherwise
     */
    boolean has(Attribute attribute);

}
