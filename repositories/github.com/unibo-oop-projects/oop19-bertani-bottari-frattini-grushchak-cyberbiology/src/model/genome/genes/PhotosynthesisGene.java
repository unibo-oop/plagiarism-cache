package model.genome.genes;


import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;

/**
 * The photosynthesis gene give to a cell energy if it is in the sun penetration zone.
 */
public class PhotosynthesisGene extends AbstractGeneDescription {
    private final int worldHeight;
    private final int sunEnergy;
    private final float sunPenetration;

    /**
     * @param worldHeight the world height.
     * @param sunPenetration at what depth cells can perform photosynthesis.
     * @param sunEnergy how effective is photosynthesis.
     */
    public PhotosynthesisGene(final int worldHeight,
                              final float sunPenetration,
                              final int sunEnergy) {
        super();
        this.worldHeight = worldHeight;
        this.sunPenetration = sunPenetration;
        this.sunEnergy = sunEnergy;
    }

    @Override
    public final void launch(final CellStandard cell) {
        final float depth = (float)cell.getY() / (float)this.worldHeight;
        if (depth  <= this.sunPenetration) {
            final float relativeDepth = depth / this.sunPenetration;
            final int energy = Math.round((1 -  relativeDepth) * this.sunEnergy);
            cell.incrementEnergy(energy, EnergyTypeEnum.PHOTOSYNTHESIS);
        }
    }

    @Override
    public final String getDescription() {
        return "Photosynthesis";
    }

}
