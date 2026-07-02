package model.world_state;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import model.Character;
import model.CharacterImpl;
import model.SoundManager;
import model.SoundManagerImpl;
import model.components.GameTimerImpl;
import model.components.Score;
import model.components.ScoreImpl;
import model.components.WordSetPause;
import model.difficulty.DiffValSpeedOffsetIncrement;
import model.difficulty.Difficulty;
import model.difficulty.SimpleIncreasingDifficulty;
import model.generator.StringManager;
import model.generator.StringManagerImpl;
import model.generator.WordGenerator;
import model.generator.WordGeneratorImpl;
import model.word.Word;

public class WorldImpl implements World, WordSetPause {

    private static final double RANGE = 26.0;
    private static final double POS = RANGE / 2;
    private static final int LETTERS_GAP = 4;

    private final Set<Word> wordSet = new HashSet<>();
    private final Character unicorn;
    private GameState gameState;
    private final Difficulty diff;
    private final WordGenerator wordGenerator;
    private final Score score;
    private final GameTimerImpl timer;
    private final SoundManager wrongHitSound;
    private final SoundManager correctWordHitSound;
    private boolean wordSetPause;
    private boolean settings = true;

    public WorldImpl() {
        final Pair<Double, Double> pos = new Pair<>(POS, 100.0);
        this.unicorn = new CharacterImpl(pos);
        this.gameState = new GameStateImpl(this.wordSet, this.unicorn);
        final StringManager stringManager = new StringManagerImpl("file/parole.txt", LETTERS_GAP);
        this.diff = new SimpleIncreasingDifficulty(stringManager.getMaxNWord(), new DiffValSpeedOffsetIncrement());
        this.wordGenerator = new WordGeneratorImpl(wordSet, diff, stringManager);
        score = new ScoreImpl();
        timer = new GameTimerImpl();
        wrongHitSound = new SoundManagerImpl("music/Button-sound-wrong.wav");
        correctWordHitSound = new SoundManagerImpl("music/Collect-chimes-sound-effect.wav");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Word> getWordSet() {
        return this.wordSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character getUnicorn() {
        return this.unicorn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameState(final GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Score getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameTimerImpl getTimer() {
        return this.timer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoundManager getWrongHitSound() {
        return this.wrongHitSound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoundManager getCorrectHitSound() {
        return this.correctWordHitSound;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSettings() {
        return settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSettings(final boolean settings) {
        this.settings = settings;
    }

    /**
     * {@inheritDoc}
     */
     @Override
    public void addWords() {
        wordGenerator.spawnWords(0, RANGE, 0);
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public boolean isOver() {
        return wordSet.isEmpty();
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public Optional<Word> currentActiveWord() {
        for (var i : this.wordSet) {
            if (i.isActive()) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public void changeActive() {
        final Optional<Word> activeWordTemp = currentActiveWord();
        if (activeWordTemp.isPresent()) {
            final Word activeWord = activeWordTemp.get();
            if (activeWord.getLength() > activeWord.getTyped()) {
                this.score.resetCombo();
                activeWord.setLength(activeWord.getLength() - activeWord.getTyped());
                activeWord.setWord(activeWord.getWord().substring(activeWord.getTyped()));
                activeWord.setTyped(0);
                activeWord.setActive(false);
                this.score.setPoints(activeWord.getTyped());
            }
        }
        unicorn.changeAiming(false);
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public void damageActiveWord() {
        final Word activeWord = currentActiveWord().get();
        if (activeWord.isActive()) {
            activeWord.setTyped(activeWord.getTyped() + 1);

            if (activeWord.getLength() == activeWord.getTyped()) {
                if (settings) {
                    correctWordHitSound.play();
                }
                this.score.incCombo();
                this.score.setPoints(activeWord.getTyped());
                wordSet.remove(activeWord);
                unicorn.changeAiming(false);
            } else {
                activeWord.setFirstLetter(activeWord.getCharAt(activeWord.getTyped()));
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public void resetSet() {
        wordSet.clear();
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public void update() {
        if (isOver()) {
            unicorn.changeAiming(false);
            getTimer().setWordSetPause(true);
            getScore().setWordSetPause(true);
            this.wordSetPause = true;
            diff.increase();
            wordGenerator.spawnWords(0.0, RANGE, 0.0);
        } else {
            for (var i : this.wordSet) {
                i.setPosition(new Pair<>(i.getPosition().getKey(), i.getPosition().getValue() + i.getSpeed()));
            }
        }
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public boolean isWordSetPause() {
        return wordSetPause;
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public void setWordSetPause(final boolean wordSetPause) {
        this.wordSetPause = wordSetPause;
    }
}