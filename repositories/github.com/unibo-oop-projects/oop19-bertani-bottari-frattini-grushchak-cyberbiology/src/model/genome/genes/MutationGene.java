package model.genome.genes;

import java.util.Random;
import model.entity.cell.standard.CellStandard;


/**
 * 
 * Mutation gene in some cases changes one gene in genome of cell.
 *
 */
public class MutationGene extends AbstractGeneDescription {
    private final Random rand = new Random();
    private final float mutatioinRate;

    /**
     * @param mutationRate a mutation rate during reproduction.
     */
    public MutationGene(final float mutationRate) {
        super();
        this.mutatioinRate = mutationRate;
    }

    @Override
    public final void launch(final CellStandard cell) {
        if (Math.random() < this.mutatioinRate) {
            final int position = rand.nextInt(cell.getNumberOfGenes());
            final int newGeneIndex = rand.nextInt(cell.getGenome().size());
            cell.mutateGene(position, newGeneIndex);
        }
    }

    @Override
    public final String getDescription() {
        return "Mutation";
    }

}
