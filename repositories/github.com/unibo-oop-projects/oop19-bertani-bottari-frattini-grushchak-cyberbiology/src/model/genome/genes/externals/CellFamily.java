package model.genome.genes.externals;

import model.entity.cell.standard.CellStandard;

/**
 * 
 * The interface for the genes classes that must determine if two cells are relatives.
 *
 */
public interface CellFamily {
    /**
     * If two cells has more different genes in their genomes than TOLERANCE, so they are not relatives.
     */
    int TOLERANCE = 1;

    /**
     * Controls if two cells are relatives to each other.
     * @param cellA first cell.
     * @param cellB second cell.
     * @return true if cells are relatives to each other.
     */
    default boolean areRelative(final CellStandard cellA, final CellStandard cellB) {
        if (cellA.getCellTypeName() != cellB.getCellTypeName()
                || cellA.getGenome().size() != cellB.getGenome().size()) {
            return false;
        }
        int numberOfDifferentGenes = 0;
        for (int i = 0; i < cellA.getGenome().size(); i++) {
            if (cellA.getGenome().get(i) != cellB.getGenome().get(i)) {
                numberOfDifferentGenes++;
                if (numberOfDifferentGenes > TOLERANCE) {
                    return false;
                }
            }
        }
        return true;
    }

}
