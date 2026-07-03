package model.implementations;

import model.interfaces.IdGenerator;

/**
 * 
 * Implements the identifier generator.
 */
public final class IdGeneratorImpl implements IdGenerator {

    private static final IdGeneratorImpl SINGLETON = new IdGeneratorImpl();
    private char letter;
    private int counter;

    private IdGeneratorImpl() { }

    /**
     * 
     * @return the identifier generator
     */
    public static IdGeneratorImpl getIdGenerator() {
        return SINGLETON;
    }

    @Override
    public char getLetter() {
        return this.letter;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    @Override
    public void setInitialLetter(final char c) {
        this.letter = c;
    }

    @Override
    public void setInitialNumber(final int n) {
        this.counter = n;
    }

    @Override
    public String generate() {
        if (this.counter == Integer.MAX_VALUE) {
            this.letter++;
            this.counter = 0;
        }
        return Character.toString(this.letter) + Integer.toString(this.counter++);
    }

}
