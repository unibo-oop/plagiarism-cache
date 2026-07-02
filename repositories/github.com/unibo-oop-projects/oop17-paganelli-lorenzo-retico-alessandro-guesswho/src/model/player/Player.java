package model.player;

import java.util.*;
import model.attribute.Attribute;
import model.character.Character;
import model.question.Question;

/**
 * Modeling interface for a Player.
 * Players can be obtained using PlayerBuilder.
 */
public interface Player {

    /**
     * Getter method.
     * @throws IllegalStateException in case the Player has not selected yet
     * @return the Character selected by the Player
     */
    Character getSelected();

    /**
     * Getter method.
     * @return the Set of remaining possible Characters
     */
    Set<Character> getAvailables();

    /**
     * Getter method.
     * @return the number of remaining attempts to guess opponent's Character
     */
    int getRemainingAttempts();

    /**
     * Allows to select a Character by giving his/her name, it can be called just once.
     * @throws IllegalStateException 
     *              in case the Character has already been selected
     *         IllegalArgumentException 
     *              in case of null argument or 
     *              in case the name does not match any Character
     * @param name 
     *              the Character's name
     */
    void select(String name);

    /**
     * Allows to get the possible questions from the Set of remaining Characters.
     * Note that some questions may appear several times
     * @return a List of String, each one a possible question
     */
    List<Question> availableQuestions();

    /**
     * Allows to decrease the number of remaining attempts.
     * @throws IllegalStateException 
     *              in case of no remaining attempts
     */
    void decreaseAttempts();

    /**
     * Allows to filter the possible remaining Characters through an Attribute they should have or not.
     * @throws IllegalArgumentException 
     *              in case of null argument
     * @param attribute
     *              the attribute
     * @param b
     *              a boolean that indicates if the Characters should have or not the attribute
     */
    void filter(Attribute attribute, Boolean b);

    /**
     * Allows to remove a particular Character by giving his/her name.
     * @throws IllegalArgumentException 
     *              in case of null argument or if the name does not match with any remaining Character
     * @param name
     *              Character's name
     */
    void remove(String name);

}
