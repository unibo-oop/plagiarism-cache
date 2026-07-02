package model.generator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;
import model.difficulty.Difficulty;
import model.word.Word;
import model.word.WordImpl;

/**
 * 
 * This class task is to add the words to the game updating the "global" set. It
 * adds the words all together.
 *
 */
public class WordGeneratorImpl implements WordGenerator {

    private final Set<Word> words;
    private final Difficulty difficulty;
    private final StringManager wordsSelector;
    private final Random random;

    public WordGeneratorImpl(final Set<Word> words, final Difficulty difficulty, final StringManager sm) {
        this.random = new Random();
        this.words = words;
        this.difficulty = difficulty;
        this.wordsSelector = sm;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spawnWords(final double minX, final double maxX, final double minY) { // me li passa il model
        if (!words.isEmpty()) {
            throw new IllegalStateException("You can spawn words only if the set is empty");
        }

        final Set<String> strings = this.wordsSelector.getWords(difficulty.getNShort(), difficulty.getNLong());
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            double speed = selectSpeed(difficulty.getMinSpeed(), difficulty.getMaxSpeed());
            Pair<Double, Double> position = new Pair<>(selectX(minX, maxX), minY);
            this.words.add(new WordImpl(it.next(), speed, position));

        }
    }

    private double randomRangeInclusive(final double minValue, final double maxValue) {
        // with 3 significant digits
        double d = random.doubles(1, minValue, maxValue).findFirst().getAsDouble();
        return new BigDecimal(d).round(new MathContext(3)).doubleValue();
    }

    private double selectX(final double minX, final double maxX) {
        double d;
        do {
            d = randomRangeInclusive(minX, maxX);
        } while (this.words.stream().map(w -> w.getPosition().getKey()).collect(Collectors.toList()).contains(d));
        return d;

    }

    private double selectSpeed(final double minSpeed, final double maxSpeed) {
        return randomRangeInclusive(difficulty.getMinSpeed(), difficulty.getMaxSpeed());

    }
}
