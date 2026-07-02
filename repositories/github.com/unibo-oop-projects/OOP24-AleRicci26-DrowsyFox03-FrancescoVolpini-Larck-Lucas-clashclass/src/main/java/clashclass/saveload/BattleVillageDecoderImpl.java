package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BattleBuildingFactoryImpl;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.VillageElementData;

/**
 * Represents a {@link AbstractVillageDecoder} extension for battle village.
 */
public class BattleVillageDecoderImpl extends AbstractVillageDecoder {
    private final BuildingFactoryMapper<?> buildingFactoryMapper;

    /**
     * Constructs the decoder.
     */
    public BattleVillageDecoderImpl() {
        this.buildingFactoryMapper = new BuildingFactoryMapper<>(new BattleBuildingFactoryImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject createGameObject(final VillageElementData type, final VectorInt2D position) {
        return buildingFactoryMapper.getFactoryFor(type).apply(position);
    }
}
