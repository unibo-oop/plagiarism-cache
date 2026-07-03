package model;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Character.Colors;
import model.Character.Dress;
import model.Question.TYPE;
import model.exception.NoMoreQuestionAvailableException;
import model.exception.PendingAIGuessingCharacter;
import model.exception.UserLiedException;

/***/
public class JtestModel {

    private Model model;
    private Character chosenCharacter;

    /***/
    @Before
    public void initialise() {
        final Set<Character> characters = new HashSet<>();
        characters.add(new Character.CharacterBuilder().setName("Giacomo")
                .setEyeColor(Colors.BROWN)
                .setHairColor(Colors.BROWN)
                .setHairType(Character.Hair.STRAIGHT)
                .setBeardColor(Colors.BLACK)
                .setMustacheColor(Colors.BLACK)
                .setHasEarrings(false)
                .setHasGlasses(true)
                .setHasHat(false)
                .setIsMale(true)
                .setSkinColor(Colors.PINK)
                .setDressType(Dress.TSHIRT)
                .setPicPath("")
                .build());
        characters.add(new Character.CharacterBuilder().setName("Daniel")
                .setEyeColor(Colors.BLUE)
                .setHairColor(Colors.BROWN)
                .setHairType(Character.Hair.STRAIGHT)
                .setBeardColor(Colors.BLACK)
                .setMustacheColor(Colors.BLACK)
                .setHasEarrings(false)
                .setHasGlasses(true)
                .setHasHat(false)
                .setIsMale(true)
                .setSkinColor(Colors.PINK)
                .setDressType(Dress.JACKET)
                .setPicPath("")
                .build());

        model = new ModelImpl(characters);
        chosenCharacter = (Character) characters.toArray()[0];
        model.humanHasChosenCharacter(chosenCharacter);
    }

    /**
     * Test ai's answer to a specific question.
     */
    @Test
    public void testQuestion() {
        if (model.isHumanTurn()) {
            assertFalse(model.askToAI(new Question("Ha il cappello", x-> x.hasHat(), TYPE.HAT)));
        }
    }

    /**
     * Test the turn switching.
     * @throws NoMoreQuestionAvailableException 
     * @throws PendingAIGuessingCharacter 
     */
    @Test (expected = IllegalStateException.class)
    public void testNotHumanTurnException() throws NoMoreQuestionAvailableException, PendingAIGuessingCharacter {
        if (!model.isHumanTurn()) {
            model.askToAI(new Question("Ha il cappello", x-> x.hasHat(), TYPE.HAT));
        } else {
            model.getAInextQuestion();
        }
    }

    /**
     * Test for user lying exception.
     * @throws NoMoreQuestionAvailableException .
     * @throws UserLiedException .
     */
    @Test (expected = UserLiedException.class)
    public void testUserLie() throws NoMoreQuestionAvailableException, UserLiedException {
        final Question q = new Question("Ha gli occhiali?", x-> x.hasGlasses(), TYPE.GLASSES);
        if (!model.isHumanTurn()) {
            model.humanAnswered(q, false);
        } else {
            model.askToAI(q);
            model.humanAnswered(q, false);
        }
    }

    /**
     * Test the exclusion mechanism of question (based on the answer).
     * @throws UserLiedException 
     * @throws NoMoreQuestionAvailableException */
    @Test
    public void testFilter() throws UserLiedException, NoMoreQuestionAvailableException {
        if (model.isHumanTurn()) {
           model.askToAI(new Question("Ha la tshirt", x-> x.getDressType().equals(Dress.TSHIRT), TYPE.DRESS_TYPE));
           model.getHumanCharacters().values().contains(false);
        } else {
            final Character c;
            try {
                final Question q = model.getAInextQuestion();
                model.humanAnswered(q, q.getFilter().test(chosenCharacter));
            } catch (PendingAIGuessingCharacter e) {
                c = model.getAIcharacterGuess().get();
                model.humanAnswered(c, c.equals(chosenCharacter));
            }
            model.getAICharacters().values().contains(false);
        }
    }

}
