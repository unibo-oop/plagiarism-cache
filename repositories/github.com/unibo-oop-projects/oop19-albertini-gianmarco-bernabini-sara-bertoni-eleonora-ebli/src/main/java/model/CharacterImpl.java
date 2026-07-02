package model;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import javafx.util.Pair;
import model.components.Score;
import model.word.Word;
import model.world_state.World;

public class CharacterImpl implements Character {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -6056792446390736641L;

    private Pair<Double, Double> location;
    private boolean aiming;

    /**
     * 
     * @param pos
     *      Starting character position
     */
    public CharacterImpl(final Pair<Double, Double> pos) {
        this.location = pos;
        this.aiming = false;
    }

    /**
     * 
     * @return location
     *      Location of the character at this moment
     */
    @Override
    public final Pair<Double, Double> getLocation() {
        return this.location;
    }

    /**
     * 
     * @param newPos
     *      New position of the character
     */
    @Override
    public final void setLocation(final Pair<Double, Double> newPos) {
        this.location = newPos;
    }

    /**
     * 
     * @return aiming
     *      Returns the aiming state
     */
    @Override
    public final boolean isAiming() {
        return this.aiming;
    }

    /**
     * 
     * @param aiming
     *      Setter for the aiming state
     */
    @Override
    public final void changeAiming(final boolean aiming) {
        this.aiming = aiming;
    }

    /**
     * 
     * @param letter
     *      Letter pressed by the player
     * 
     * @param world
     *      Set of words present on the screen at the time
     */
    @Override
    public final void hit(final char letter, final World world) {
        if (this.isAiming()) {
            if (this.isCorrect(letter, world.currentActiveWord())) {
                world.damageActiveWord();
            } else {
                if (world.isSettings()) {
                    world.getWrongHitSound().play();
                }
                this.wrongInput(world.getScore());
            }
        } else {
            if (this.checkPresence(letter, world.getWordSet())) {
                final Optional<Word> correctWord = getCorrectWord(letter, world.getWordSet());
                this.setLocation(new Pair<Double, Double>(correctWord.get().getPosition().getKey(), this.getLocation().getValue()));
                correctWord.get().setActive(true);
                this.changeAiming(true);
                this.hit(letter, world);
            } else {
                if (world.isSettings()) {
                    world.getWrongHitSound().play();
                }
                this.wrongInput(world.getScore());
            }
        }
    }

    private boolean isCorrect(final char letter, final Optional<Word> word) {
        if (word.isPresent()) {
            return letter == word.get().getFirstLetter();
        } else {
            System.out.println("Error, active word not present in the current set");
            return false;
        }
    }

    private boolean checkPresence(final char letter, final Set<Word> wordSet) {
        return wordSet.stream()
                .anyMatch(l -> l.getFirstLetter() == letter);
    }

    private Optional<Word> getCorrectWord(final char letter, final Set<Word> wordSet) {
        return wordSet.stream()
                .filter(l -> l.getFirstLetter() == letter)
                .sorted(Comparator.comparingDouble(Word::wordGetY).reversed())
                .findFirst();
    }

    private void wrongInput(final Score score) {
        score.resetCombo();
    }
}
