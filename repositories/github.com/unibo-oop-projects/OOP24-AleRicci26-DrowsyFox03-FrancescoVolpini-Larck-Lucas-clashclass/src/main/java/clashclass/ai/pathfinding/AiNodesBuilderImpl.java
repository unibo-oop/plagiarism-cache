package clashclass.ai.pathfinding;

import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.GameConstants;
import clashclass.commons.GridTileData2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;
import clashclass.village.Village;


import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a {@link AiNodesBuilder} implementation.
 */
public class AiNodesBuilderImpl implements AiNodesBuilder {
    private PathNodeGrid pathNodeGrid;

    /**
     * {@inheritDoc}
     */
    @Override
        public PathNodeGrid buildPathNodeList(final Village village) {
        this.pathNodeGrid = new PathNodeGridImpl(GameConstants.VILLAGE_SIZE, village.getBuildings().stream()
                .map(gameObject -> {
                    final var position = gameObject.getComponentOfType(GridTileData2D.class).get().getPosition();
                    final var buildingType = gameObject.getComponentOfType(BuildingTypeComponent.class).get().getBuildingType();
                    return new PathNodeImpl(
                            position,
                            buildingType.equals(VillageElementData.WALL) ? 1000.0f : 1.0f,
                            Optional.of(gameObject));
                })
                .collect(Collectors.toSet()));

        return this.pathNodeGrid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public PathNodeGrid buildPathNodeList(final GameObject destroyedBuilding) {
        if (this.pathNodeGrid != null) {
            final var gridPosition = destroyedBuilding.getComponentOfType(GridTileData2D.class).get().getPosition();
            this.pathNodeGrid.removeAtPosition(gridPosition, 1, 1);
        }
        return this.pathNodeGrid;
    }
}
