package it.dpg.maingame.model.character;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;
import java.util.Set;

/**
 * Basic implementation of Cpu interface
 * @see Cpu
 * @author Davide Picchiotti
 * */

public class CpuImpl implements Cpu {

    private final Character controlledCharacter;
    private final Difficulty difficulty;

    public CpuImpl(final Character controlledCharacter, final Difficulty difficulty) {
        this.controlledCharacter = controlledCharacter;
        this.difficulty = difficulty;
    }

    @Override
    public Character getControlledCharacter() {
        return this.controlledCharacter;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public Pair<Integer, Integer> getRandomDirection() {
        Set<Pair<Integer, Integer>> s = getControlledCharacter().getAdjacentPositions();
        int n = new Random().nextInt(s.size());
        
        return s.stream().skip(n).findFirst().orElseThrow(
                () -> new IllegalStateException("Can't create random direction")
        );
    }
}
