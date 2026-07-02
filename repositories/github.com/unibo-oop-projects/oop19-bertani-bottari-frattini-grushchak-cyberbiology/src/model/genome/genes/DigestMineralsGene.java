package model.genome.genes;



import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;


/**
 * 
 * This instance transform minerals of cell to energy. 
 *
 */
public class DigestMineralsGene extends AbstractGeneDescription {
    private static final int MINERALS_TO_DIGEST = 100;

    @Override
    public final  void launch(final CellStandard cell) {
        if (cell.getMineral() > MINERALS_TO_DIGEST) {
            cell.decrementMineral(MINERALS_TO_DIGEST);
            cell.incrementEnergy(MINERALS_TO_DIGEST, EnergyTypeEnum.CONVERTING_MINERAL);
        }else {
            cell.incrementEnergy(cell.getMineral(), EnergyTypeEnum.CONVERTING_MINERAL);
            cell.decrementMineral(cell.getMineral());
        }
    }

    @Override
    public final String getDescription() {
        return "Digest minerals";
    }

}
