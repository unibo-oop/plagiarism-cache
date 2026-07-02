package model.properties.cells;

import model.genome.decryptor.GeneDecryptor;
import model.genome.factory.GenesTable;

/**
 * 
 * The interface with methods that giving access to cells data.
 *
 */
public interface CellData {
    /**
     * 
     * @return genes' decryptor used for read the cell's genome.
     */
    GeneDecryptor getGeneDecryptor();

    /**
     * @return the amount of energy that cell spends making its turn.
     */
    int getTurnCost();

    /**
     * @return the number of genes in genome of cell.
     */
    int getGenomeSize();

    /**
     * @return the maximum number of energy that can have a cell.
     */
    int getMaxEnergy();

    /**
     * @return the maximum number of minerals that can have a cell.
     */
    int getMaxMinerals();

    /**
     * When a cell reaches this age it dies.
     * 
     * @return maximum age.
     */
    int getMaxAge();

    /**
     * The number of possible genes.
     * 
     * @return a multiple of 8.
     */
    int getNumberOfGenes();

    /**
     * @return a {@link GenesTable}.
     */
    GenesTable getGenesTable();

}
