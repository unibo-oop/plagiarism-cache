package model.genome.decryptor;

import model.genome.genes.Gene;

/**
 * 
 * A Gene Decryptor transform a gene index into the gene.
 *
 */
public interface GeneDecryptor {
    /**
     * 
     * @param index of a gene.
     * @return A gene that correspond to the index.
     * @throws IllegalArgumentException if the index has not corresponded gene.
     */
    Gene getGene(int index);

    /**
     * 
     * @param index of a possible gene.
     * @return true if the index has a corresponded gene.
     */
    boolean isGenePresent(int index);

    /**
     * Return index of gene.
     * @param gene 
     * @return index of given gene. If the gene has some indexes then first finded will be returned.
     * @throws IllegalArgumentException if the gene has not an index.
     */
    int getIndexOfGene(Gene gene);
    
}
