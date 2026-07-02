package it.unibo.jurassiko.model.dice.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.jurassiko.model.dice.api.Dice;

/**
 * Implementation of {@link Dice} interface.
 */
public class DiceImpl implements Dice {

    private static final int DICE_FACES = 6;
    private final Random random = new Random(System.currentTimeMillis());

    /**
     * {@inheritDoc}
     */
    @Override
    public int roll() {
        return random.nextInt(DICE_FACES) + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> rollMultiple(final int amount) {
        return IntStream.range(0, amount)
                .mapToObj(e -> roll())
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());

    }

}
