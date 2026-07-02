package model.genome.genes.steps;

/**
 * 
 * Enumeration that can return the relative to the next genome index of a cell
 * to decrypt.
 *
 */
public enum GenomeStepEnum implements GenomeStep {

    ONE, TWO, THREE, FOUR, FIVE;

    @Override
    public final int getStep() {
        return this.ordinal() + 1;
    }

}
