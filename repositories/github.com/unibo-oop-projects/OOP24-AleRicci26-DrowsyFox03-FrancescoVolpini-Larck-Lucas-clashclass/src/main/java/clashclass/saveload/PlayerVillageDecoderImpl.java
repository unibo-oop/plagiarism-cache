package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.BuildingFactoryMapper;
import clashclass.elements.buildings.PlayerBuildingFactoryImpl;
import clashclass.elements.buildings.VillageElementData;

/**
 * Represents a {@link AbstractVillageDecoder} extension for player village.
 */
public class PlayerVillageDecoderImpl extends AbstractVillageDecoder {
    private final BuildingFactoryMapper<?> buildingFactoryMapper;

    /**
     * Constructs the decoder.
     */
    public PlayerVillageDecoderImpl() {
        this.buildingFactoryMapper = new BuildingFactoryMapper<>(new PlayerBuildingFactoryImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject createGameObject(final VillageElementData type, final VectorInt2D position) {
        return buildingFactoryMapper.getFactoryFor(type).apply(position);
    }
}
