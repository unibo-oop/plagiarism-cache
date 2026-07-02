package model.world_state;

import java.util.Optional;
import java.util.Set;

import model.Character;
import model.SoundManager;
import model.components.GameTimerImpl;
import model.components.Score;
import model.word.Word;

public interface World {

    /**
     * 
     * @return wordSet: a set containing all the words of this play set
     */
    Set<Word> getWordSet();

    /**
     * 
     * @return unicorn
     */
    Character getUnicorn();

    /**
     * 
     * @return the current game's state
     */
    GameState getGameState();

    /**
     * 
     * @param gameState
     *
     */
    void setGameState(GameState gameState);

    /**
     * 
     * @return the current score
     */
    Score getScore();

    /**
     * 
     * @return the current time
     */
    GameTimerImpl getTimer();

    /**
     * 
     * @return sound when you hit a wrong letter.
     */
    SoundManager getWrongHitSound();

    /**
     * 
     * @return sound when you finish to write a full word
     */
    SoundManager getCorrectHitSound();

    /**
     * @return settings
     */
    boolean isSettings();

    /**
     * @param settings
     */
    void setSettings(boolean settings);

    /**
     * with the spawnWords method, add all the words generated for this set in the
     * words set.
     */
    void addWords();

    /**
     * 
     * @return true :if the wordSet is empty.
     */
    boolean isOver();

    /**
     *
     * @return activeWord (optional) the unique active word (if present) in the set.
     */
    Optional<Word> currentActiveWord();

    /**
     * When you choose to change the word to type, this method set all the param to
     * save the state of the word. Then, it change the state of the character, that
     * doesn't have a word's position reference anymore
     */
    void changeActive();

    /**
     * 
     * If you're typing the right letter of the active word this method set which is
     * the next letter to control. If you complete a full word, you increase your
     * combo and score. Else, it throw an IllegalArgumentException.
     */
    void damageActiveWord();

    /**
     * It clear this set.
     */
    void resetSet();

    /**
     * this method make and update over: unicorn's position set score and timer in a
     * break pause increase the difficulty spawn a new set of words.
     */
    void update();

    /**
     * @return true if you finish a set, so at the next render there must be the
     * break.
     */
    boolean isWordSetPause();

    /**
     * 
     * @param wordSetPause
     * 
     *set wordSetPause value
     */
    void setWordSetPause(boolean wordSetPause);

}
