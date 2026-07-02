package model.genome.genes;

import model.entity.cell.standard.CellStandard;

/**
 * 
 * A basic interface for all genes.
 *
 */
public interface Gene {
    /**
     * Launches the functions of gene.
     * @param cell that launches the gene.
     */
    void launch(CellStandard cell);

    /**
     * @return Description of gene.
     */
    String getDescription();
}
