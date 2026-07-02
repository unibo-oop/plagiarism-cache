package model.genome.factory;

import model.genome.genes.ChangeDirectionGene;
import model.genome.genes.DigestMineralsGene;
import model.genome.genes.HorizontallyAligningGene;
import model.genome.genes.MineralsAbsorptionGene;
import model.genome.genes.MutationGene;
import model.genome.genes.PhotosynthesisGene;
import model.genome.genes.TurnFromCurrentDirectionGene;
import model.genome.genes.externals.EatAllGene;
import model.genome.genes.externals.GiveResourcesGene;
import model.genome.genes.externals.LookAheadGene;
import model.genome.genes.externals.ControlSpaceAroundGene;
import model.genome.genes.externals.DeathGene;
import model.genome.genes.externals.MovementGene;
import model.genome.genes.externals.ReproductionGene;
import model.genome.genes.externals.ShareEnergyGene;
import model.genome.genes.externals.ShareMineralsGene;

/**
 * 
 * The Factory for create genes.
 *
 */
public interface GenesFactory {

    /**
     * @return a new {@link PhotosynthesisGene}.
     */
    PhotosynthesisGene createPhotosynthesisGene();

    /**
     * @return a new {@link MutationGene}.
     */
    MutationGene createMutationGene();

    /**
     * @return a new {@link DeathGene}.
     */
    DeathGene createDeathGene();

    /**
     * @return a new {@link MineralAbsorptionGene}.
     */
    MineralsAbsorptionGene createMineralsAbsorptionGene();

    /**
     * @return a new {@link MovementGene}.
     */
    MovementGene createMovementGene();

    /**
     * @return a new {@link ChangeDirectionGene}.
     */
    ChangeDirectionGene createChangeDirectionGene();

    /**
     * @return a new {@link HorizontallyAligningGene}.
     */
    HorizontallyAligningGene createHorizontallyAligningGene();

    /**
     * @return a new {@link DigestMineralsGene}.
     */
    DigestMineralsGene createDigestMineralsGene();

    /**
     * @return a new {@link EatAllGene}.
     */
    EatAllGene createEatAllGene();

    /**
     * @return a new {@link ReproductionGene}.
     */
    ReproductionGene createReproductionGene();

    /**
     * @return a new {@link LookAheadGene}.
     */
    LookAheadGene createLookAheadGene();

    /**
     * @return a new {@link ControlSpaceAroundGene}.
     */
    ControlSpaceAroundGene createControlSpaceAroundGene();

    /**
     * @return a new {@link ShareEnergyGene}.
     */
    ShareEnergyGene createShareEnergyGene();

    /**
     * @return a new {@link ShareMineralsGene}.
     */
    ShareMineralsGene createShareMineralsGene();

    /**
     * @return a new {@link GiveResourcesGene}.
     */
    GiveResourcesGene createGiveResourcesGene();

    /**
     * @return a new {@link TurnFromCurrentDirectionGene}.
     */
    TurnFromCurrentDirectionGene createTurnFromCurrentDirectionGene();

}
