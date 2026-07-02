package clashclass.saveload;

import clashclass.commons.BuildingTypeComponent;
import clashclass.commons.GridTileData2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;
import clashclass.village.Village;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a {@link VillageEncoder} implementation.
 */
public class VillageEncoderImpl implements VillageEncoder {
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    private static final int APPENDER_WIDTH = 800;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHeader() {
        return "TYPE,INSTANCE_ID,POS_X,POS_Y" + NEW_LINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public String encode(final Village village) {
        final StringBuilder builder = new StringBuilder(APPENDER_WIDTH);

        // Resources
        builder.append("ResourceType,CurrentValue,MaxValue").append(NEW_LINE);
        village.getPlayer().getPlayerResources().forEach((type, resource) -> {
            final String line = type.name().toUpperCase(Locale.getDefault())
                    + CSV_DELIMITER
                    + (int) resource.getCurrentValue()
                    + CSV_DELIMITER
                    + (int) resource.getMaxValue()
                    + NEW_LINE;
            builder.append(line);
        });
        builder.append(NEW_LINE);

        // Army-camp troops
        builder.append("TroopType,Count").append(NEW_LINE);
        village.getPlayer().getArmyCampTroopTypes().forEach(troopType -> {
            final var count = village.getPlayer().getArmyCampTroopCount(troopType);
            builder
                    .append(troopType.getName().toUpperCase(Locale.getDefault()))
                    .append(CSV_DELIMITER)
                    .append(count)
                    .append(NEW_LINE);
                }
        );
        builder.append(NEW_LINE);

        // Buildings
        builder.append(getHeader());

        final Map<VillageElementData, Integer> counters = new EnumMap<>(VillageElementData.class);

        for (final GameObject gameObject : village.getGameObjects()) {
            determineType(gameObject).ifPresent(type -> {
                final int progressive = counters.merge(type, 1, (prev, inc) -> prev + 1);
                final var gridPosition = gameObject.getComponentOfType(GridTileData2D.class).get()
                        .getPosition();
                final int x = gridPosition.x();
                final int y = gridPosition.y();

                builder.append(type.getName().toUpperCase(Locale.getDefault()))
                        .append(CSV_DELIMITER)
                        .append(progressive)
                        .append(CSV_DELIMITER)
                        .append(x)
                        .append(CSV_DELIMITER)
                        .append(y)
                        .append(NEW_LINE);
            });
        }

        return builder.toString();
    }

    private Optional<VillageElementData> determineType(final GameObject gameObject) {
        return gameObject.getComponentOfType(BuildingTypeComponent.class)
                .map(BuildingTypeComponent::getBuildingType);
    }
}
