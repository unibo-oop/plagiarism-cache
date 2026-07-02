package model.word;

import javafx.util.Pair; 

public class WordImpl implements Word {

    private char firstLetter;
    private String word;
    private boolean isActive;
    private int length;
    private int typed;
    private double speed;
    private Pair<Double, Double> position;

    public WordImpl(final String word, final double speed, final Pair<Double, Double> position) {
        this.firstLetter = word.charAt(0);
        this.word = word;
        this.isActive = false;
        this.length = this.word.length();
        this.typed = 0;
        this.speed = speed;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getFirstLetter() {
        return firstLetter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFirstLetter(final char firstLetter) {
        this.firstLetter = firstLetter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWord() {
        return this.word;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWord(final String word) {
        this.word = word;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLength() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLength(final int length) {
        this.length = length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTyped() {
        return this.typed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTyped(final int typed) {
        this.typed = typed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double wordGetY() {
        return this.getPosition().getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double wordGetX() {
        return this.getPosition().getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public char getCharAt(final int index) {
        return this.word.charAt(index);
    }

}
