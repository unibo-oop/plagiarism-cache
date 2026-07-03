package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.exception.NoMoreQuestionAvailableException;
import model.exception.PendingAIGuessingCharacter;

/**
 * Defines the player with artificial intelligence.
 */
public class AI extends PlayerImpl {

    private static final int GUESS_LOWER_THRESHOLD = 5;

    private Optional<Character> pendingCharacter;
    private final DistributionStrategy<Double> ds;

    /**
     * @param characters The set of characters for the current match.
     * @param questions Questions available for the current match.
     * @param ds The distribution that should be used to computer the next question
     */
    public AI(final Set<Character> characters, final Set<Question> questions, final DistributionStrategy<Double> ds) {
        super(characters, questions);
        pendingCharacter = Optional.empty();
        chooseCharacter();
        this.ds = ds;
    }

    private void chooseCharacter() {
        final int r = new Random(System.currentTimeMillis()).nextInt(this.getCharacters().keySet().size());
        this.setChosenCharacter((Character) this.getCharacters().keySet().toArray()[r]); 
    }

    /**
     * Determines the next question that will be asked to the human player (or throws an exception in case the AI want to try the human player character).
     * @return the question chosen by the AI player
     * @throws NoMoreQuestionAvailableException in case AI finished his questions (match should be already ended).
     * @throws PendingAIGuessingCharacter if the AI player want to try a guess
     */
    public Question nextQuestion() throws NoMoreQuestionAvailableException, PendingAIGuessingCharacter {
        final Set<Character> charactersLeft = this.getCharacters().entrySet().stream()
                                                                             .filter(x-> x.getValue()).map(x-> x.getKey())
                                                                             .collect(Collectors.toSet());
        final Random r = new Random();

        if (pendingCharacter.isPresent() || getAttemptLeft() >= 0 && charactersLeft.size() < AI.GUESS_LOWER_THRESHOLD && r.nextBoolean()) {
            chooseGuessCharacter(charactersLeft);
            throw new PendingAIGuessingCharacter();
        }

        final Map<Question, Double> m = new HashMap<>();

        // Associate each question with the ratio between the number of characters which physically match 
        // the question filter over the amount of characters left
        this.getQuestionsLeft().forEach(
                q-> m.put(q, ((Long) charactersLeft.stream().filter(c -> q.getFilter().test(c)).count()).doubleValue() / charactersLeft.size()));

        return ds.computeDistribution(m)
                .orElseThrow(()-> new NoMoreQuestionAvailableException(this.getClass().getName()));
    }

    /**
     * 
     * @return the character that AI player believes is chosen by the opponent
     */
    public Optional<Character> getAIpendingGuess() {
        final Optional<Character> c = pendingCharacter;
        pendingCharacter = Optional.empty();
        return c;
    }

    private void chooseGuessCharacter(final Set<Character> set) {
        if (!pendingCharacter.isPresent()) {
            final Random r = new Random();
            pendingCharacter = Optional.of((Character) set.toArray()[r.nextInt(set.size())]);
        }
    }
}
