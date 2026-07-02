package model.genome.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import model.genome.genes.Gene;

/**
 * 
 * This instance allow fast access to created genes.
 *
 */
public class GenesTableImpl implements GenesTable {
    private final EnumMap<GenesEnum, Gene> map;

    /**
     * @param factory that can create different genes.
     */
    public GenesTableImpl(final GenesFactory factory) {
        this.map = new EnumMap<>(GenesEnum.class) {
            private static final long serialVersionUID = -1274143813439230557L;
            {
                put(GenesEnum.REPRODUCTION, factory.createReproductionGene());
                put(GenesEnum.MUTATION, factory.createMutationGene());
                put(GenesEnum.PHOTOSYNTHESIS, factory.createPhotosynthesisGene());
                put(GenesEnum.MINERALS_ABSORPTION, factory.createMineralsAbsorptionGene());
                put(GenesEnum.DIGEST_MINERALS, factory.createDigestMineralsGene());
                put(GenesEnum.MOVEMENT, factory.createMovementGene());
                put(GenesEnum.CHANGE_DIRECTION, factory.createChangeDirectionGene());
                put(GenesEnum.HORIZONTALLY_ALIGNING, factory.createHorizontallyAligningGene());
                put(GenesEnum.ATTACK_ALL, factory.createEatAllGene());
                put(GenesEnum.LOOK_AHEAD, factory.createLookAheadGene());
                put(GenesEnum.CONTROL_SPACE_AROUND, factory.createControlSpaceAroundGene());
                put(GenesEnum.SHARE_ENERGY, factory.createShareEnergyGene());
                put(GenesEnum.SHARE_MINERALS, factory.createShareMineralsGene());
                put(GenesEnum.GIVE_RESOURCES, factory.createGiveResourcesGene());
                put(GenesEnum.TURN_FROM_CURRENT_DIRECTION, factory.createTurnFromCurrentDirectionGene());
                put(GenesEnum.DEATH, factory.createDeathGene());
            }
        };
    }

    @Override
    public final Gene getGene(final GenesEnum geneEnum) {
        if (!map.containsKey(geneEnum)) {
            throw new IllegalArgumentException("The key " + geneEnum + " is not present in map");
        }
        return map.get(geneEnum);
    }

    @Override
    public final List<Gene> getGenesList(final List<GenesEnum> geneEnumList) {
        return geneEnumList.stream().map(m -> getGene(m)).collect(Collectors.toList());
    }

}
