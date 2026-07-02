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
import model.properties.genes.GenesData;
import model.world.World;

/**
 * 
 * A simple instance of GenesFactory.
 *
 */
public class GenesFactoryImpl implements GenesFactory {
    private final World world;
    private final int worldHeight;
    private final int sunEnergy;
    private final int mineralsAbsorption;
    private final int nutritionOfDeadCell;
    private final float sunPenetration;
    private final float mutationRate;
    private final float mineralsDepth;
    private final int reproductionCost;

    /**
     * @param genesData {@link GenesData} is a instance with the genes data utilized
     *                  for creating genes.
     */
    public GenesFactoryImpl(final GenesData genesData) {
        this.world = genesData.getWorld();
        this.worldHeight = this.world.getWorldHeight();
        this.sunPenetration = genesData.getSunPenetration();
        this.sunEnergy = genesData.getSunEnergy();
        this.mutationRate = genesData.getMutationRate();
        this.mineralsAbsorption = genesData.getMineralsAbsorption();
        this.mineralsDepth = genesData.getMineralsDepth();
        this.nutritionOfDeadCell = genesData.getNutritionOfDeadCell();
        this.reproductionCost = genesData.getReproductionCost();
    }

    @Override
    public final PhotosynthesisGene createPhotosynthesisGene() {
        return new PhotosynthesisGene(worldHeight, sunPenetration, sunEnergy);
    }

    @Override
    public final MutationGene createMutationGene() {
        return new MutationGene(mutationRate);
    }

    @Override
    public final DeathGene createDeathGene() {
        return new DeathGene(world);
    }

    @Override
    public final MineralsAbsorptionGene createMineralsAbsorptionGene() {
        return new MineralsAbsorptionGene(worldHeight, mineralsAbsorption, mineralsDepth);
    }

    @Override
    public final MovementGene createMovementGene() {
        return new MovementGene(world);
    }

    @Override
    public final ChangeDirectionGene createChangeDirectionGene() {
        return new ChangeDirectionGene();
    }

    @Override
    public final DigestMineralsGene createDigestMineralsGene() {
        return new DigestMineralsGene();
    }

    @Override
    public final HorizontallyAligningGene createHorizontallyAligningGene() {
        return new HorizontallyAligningGene();
    }

    @Override
    public final EatAllGene createEatAllGene() {
        return new EatAllGene(world, nutritionOfDeadCell);
    }

    @Override
    public final ReproductionGene createReproductionGene() {
        return new ReproductionGene(world, createMutationGene(), reproductionCost);
    }

    @Override
    public final LookAheadGene createLookAheadGene() {
        return new LookAheadGene(world);
    }

    @Override
    public final ControlSpaceAroundGene createControlSpaceAroundGene() {
        return new ControlSpaceAroundGene(world);
    }

    @Override
    public final ShareEnergyGene createShareEnergyGene() {
        return new ShareEnergyGene(world);
    }

    @Override
    public final ShareMineralsGene createShareMineralsGene() {
        return new ShareMineralsGene(world);
    }

    @Override
    public final GiveResourcesGene createGiveResourcesGene() {
        return new GiveResourcesGene(world);
    }

    @Override
    public final TurnFromCurrentDirectionGene createTurnFromCurrentDirectionGene() {
        return new TurnFromCurrentDirectionGene();
    }

}
