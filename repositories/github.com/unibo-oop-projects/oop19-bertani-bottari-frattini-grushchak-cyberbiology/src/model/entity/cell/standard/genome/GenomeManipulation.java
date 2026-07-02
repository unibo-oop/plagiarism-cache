package model.entity.cell.standard.genome;


import model.entity.cell.standard.CellStandard;

/**
 * interface used for gene manipulation.
 *
 */
public interface GenomeManipulation extends Genome {
    /**
     * Generate a random int in the genome.
     * 
     * @return a int in the range of gene;
     * 
     */
    int generateRandomGene();

    /**
     * start with the current gene. add 1 to count. refresh if out of bound.
     * 
     * @param currentCell the current cell that call the gene.
     */
    void startGene(CellStandard currentCell);

    /**
     * 
     * @return the size of the genome
     */
    int getSize();
    /**
     * 
     * @param value
     * the new value of current pointer
     */
    void setCurrent(int value);
    /**
     * @return
     * the value of current
     */
    int getCurrent();

}
