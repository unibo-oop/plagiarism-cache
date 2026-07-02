package model.genome.genes.steps;

/**
 * 
 * The interface for transfer a relative distance from current genome index of
 * cell to the next genome index to decrypt.
 *
 */
public interface GenomeStep {

    /**
     * @return the relative distance to next genome index.
     */
    int getStep();
}
