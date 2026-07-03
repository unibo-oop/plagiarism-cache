package model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.exception.NoMoreQuestionAvailableException;

/**
 * Defines the structure of a generic player.
 */
public class PlayerImpl implements Player {

    private static final int ATTEMPT = 3;

    private Character chosenCharacter;
    private final Map<Character, Boolean> characters;
    private final Set<Question> questionsLeft;
    private int attemptLeft; //attempt left to guess opponent's character

    /**
     * @param characters the character used for this match by this player
     * @param questions the question used by this player for the current match
     */
    public PlayerImpl(final Set<Character> characters, final Set<Question> questions) {
        super();
        this.characters = new LinkedHashMap<>();
        characters.stream().forEach(c -> this.characters.put(c, true));
        this.questionsLeft = new LinkedHashSet<>(questions);
        this.attemptLeft = ATTEMPT;
    }

    @Override
    public Map<Character, Boolean> getCharacters() {
        return Collections.unmodifiableMap(characters);
    }

    @Override
    public Set<Question> getQuestionsLeft() throws NoMoreQuestionAvailableException {
        if (questionsLeft.isEmpty()) {
            throw new NoMoreQuestionAvailableException(this.getClass().getName());
        }
        return questionsLeft;
    }

    @Override
    public Character getChosenCharacter() {
        return chosenCharacter;
    }

    /**
     * Set the character chosen by this player.
     * @param chosenCharacter the character chosen by this player
     */
    protected void setChosenCharacter(final Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    @Override
    public boolean answerQuestion(final Question q) {
        return q.getFilter().test(chosenCharacter);
    }

    @Override
    public void questionAsked(final Question q, final boolean answer) {
        questionsLeft.remove(q);
        if (answer) {   //if the answer is true, it means that all the character that doesn't comply the question filter, 
                        //must be marked as unavailble
            characters.entrySet().stream().filter(x-> q.getFilter().negate().test(x.getKey()))
                    .forEach(y-> characters.replace(y.getKey(), false));
            questionsLeft.removeAll(questionsLeft.stream().filter(x-> x.getType().equals(q.getType()))
                    .collect(Collectors.toSet()));
        } else {
            characters.entrySet().stream().filter(x-> q.getFilter().test(x.getKey())).forEach(y-> characters.replace(y.getKey(), false));
        }
    }

    @Override
    public void triedToGuess(final Character c, final boolean wasHim) {
        if (wasHim) {
            this.characters.entrySet().stream().filter(x-> !x.getKey().equals(c)).forEach(x-> characters.replace(x.getKey(), false));
        } else {
            this.characters.replace(c, false);
        }
        this.attemptLeft--;
    }

    @Override
    public int getAttemptLeft() {
        return this.attemptLeft;
    }
}
