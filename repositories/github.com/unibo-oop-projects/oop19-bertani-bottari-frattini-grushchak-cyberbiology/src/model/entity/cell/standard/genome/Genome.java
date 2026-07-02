package model.entity.cell.standard.genome;

import java.util.List;

/**
 * 
 * the standard interface for the manipulation of the gene integrated in the cell.
 *
 */
public interface Genome {
    /**
     * is a standard set.
     * @param index
     * the index of the gene
     * @param gene
     * the new gene
     */
    void mutateGene(int index, int gene);
    /**
     * get the gene int of a specific index.
     * 
     * @param index a standard integer (is a circular array)
     * @return the value of the gene
     */
    int getGeneValue(int index);
    /**
     * 
     * @param value the value of the gene
     * @return -1 if there isnt a gene with the value in the genome index of the
     *         first gene with the value in the genome
     */
    int getGeneIndex(int value);
    /**
     * 
     * @param offset a int for the "Jump"
     * @return the value of the gene index
     */
    int getGeneValueWithOffsetAndJump(int offset);
    /**
     * 
     * @param index index of genome
     * @param value value of the gene
     * 
     */
    void setGene(int index, int value);
    /**
     * a getter.
     * @return
     * a int of the numbers of type of genes
     */
    int getNumberOfGenes();
    /**
     * 
     * @return
     * the genome list
     */
    List<Integer> getGenome();
    /**
     * this method change the length of the genome and help with some error.
     * 
     * @param value the change of the length of the genome
     */
    void changeSideLength(int value);


}
