package model.genome.factory;

import java.util.List;

import model.genome.genes.Gene;

/**
 * 
 * A interface for handle created genes.
 *
 */
public interface GenesTable {

    /**
     * @param geneEnum one enum that represent the gene.
     * @return the gene that is associated with param.
     */
    Gene getGene(GenesEnum geneEnum);

    /**
     * @param geneEnumList a list with enums that represent the genes.
     * @return the list with genes that are associate with param values.
     */
    List<Gene> getGenesList(List<GenesEnum> geneEnumList);
}
