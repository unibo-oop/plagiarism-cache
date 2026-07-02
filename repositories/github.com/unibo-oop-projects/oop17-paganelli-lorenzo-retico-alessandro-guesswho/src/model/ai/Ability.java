package model.ai;

import java.util.function.*;
import model.player.Player;
import model.question.Question;
import utilities.Utilities;
import model.character.Character;

/**
 * Enumeration representing different levels of ability.
 * Each ability has a guessing condition, a guessing function and an asking function, the more
 * the ability level is high the more this functions are tricky.
 */
public enum Ability {
    /***/
    BEGINNER(p -> p.getAvailables().size() < 4, p -> Utilities.getRandom(p.getAvailables()), 
            p -> Utilities.getRandom(p.availableQuestions())),
    /***/
    ADVANCED(p -> p.getAvailables().size() < 2, p -> Utilities.getRandom(p.getAvailables()), new MedianQuestion());

    private final Predicate<Player> guessingCondition;
    private final Function<Player, Character> guessingFunction;
    private final Function<Player, Question> askingFunction;

    Ability(final Predicate<Player> guessingCondition, final Function<Player, Character> guessingFunction,
            final Function<Player, Question> askingFunction) {
        this.guessingCondition = guessingCondition;
        this.guessingFunction = guessingFunction;
        this.askingFunction = askingFunction;
    }

    Predicate<Player> guessingCondition() {
        return guessingCondition;
    }

    Function<Player, Question> askingFunction() {
        return askingFunction;
    }

    Function<Player, Character> guessingFunction() {
        return guessingFunction;
    }

}
