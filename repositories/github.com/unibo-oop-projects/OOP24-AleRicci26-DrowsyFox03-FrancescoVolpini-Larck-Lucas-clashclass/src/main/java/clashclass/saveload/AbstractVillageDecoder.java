package clashclass.saveload;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.buildings.VillageElementData;
import clashclass.elements.troops.TroopType;
import clashclass.resources.Player;
import clashclass.resources.ResourceType;
import clashclass.village.Village;

import java.util.Objects;

/**
 * Represents an Abstract implementation of {@link VillageDecoder}.
 */
public abstract class AbstractVillageDecoder implements VillageDecoder {
        private ComponentFactory componentFactory;

        /**
         * {@inheritDoc}
         */
        @Override
        public void setComponentFactory(final ComponentFactory componentFactory) {
            this.componentFactory = componentFactory;
        }

        /**
         * Gets the component factory.
         *
         * @return the component factory
         */
        protected ComponentFactory getComponentFactory() {
            return componentFactory;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Village decode(final String encodedVillage) {
            Objects.requireNonNull(componentFactory, "ComponentFactory must be set before decoding");

            final Player player = new Player();
            final Village village = new Village(player);

            final var lines = encodedVillage.split("\\R");

            for (final String lineRaw : lines) {
                final String line = lineRaw.strip();
                if (line.isEmpty() || line.startsWith("ResourceType")) {
                    continue;
                }
                if (line.startsWith("TroopType")) {
                    break;
                }

                final String[] parts = line.split(",", -1);
                if (parts.length < 3) {
                    continue;
                }

                try {
                    final ResourceType type = ResourceType.valueOf(parts[0].trim());
                    final int current = Integer.parseInt(parts[1].trim());
                    // final int max = Integer.parseInt(parts[2].trim());

                    player.getPlayerResources().get(type).increase(current);
                } catch (final IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid resource type or values: " + line, e);
                }
            }

            boolean troopsSectionStarted = false;

            for (final String lineRaw : lines) {
                final String line = lineRaw.strip();
                if (!troopsSectionStarted) {
                    if (!line.startsWith("TroopType")) {
                        continue;
                    }

                    troopsSectionStarted = true;
                    continue;
                }

                if (line.startsWith("TYPE")) {
                    break;
                }

                final String[] parts = line.split(",", -1);
                if (parts.length < 2) {
                    continue;
                }

                final TroopType type = TroopType
                        .getValueFromName(parts[0])
                        .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + parts[0]));

                final int count = Integer.parseInt(parts[1].trim());
                player.addArmyCampTroop(type, count);
            }

            boolean buildingsSectionStarted = false;

            for (final String lineRaw : lines) {
                final String line = lineRaw.strip();
                if (!buildingsSectionStarted) {
                    if (!line.startsWith("TYPE")) {
                        continue;
                    }

                    buildingsSectionStarted = true;
                    continue;
                }

                final String[] parts = line.split(",", -1);
                if (parts.length < 4) {
                    continue;
                }

                final VillageElementData type = VillageElementData
                        .getValueFromName(parts[0])
                        .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + parts[0]));

                final int x = Integer.parseInt(parts[2].trim());
                final int y = Integer.parseInt(parts[3].trim());

                final GameObject go = createGameObject(type, new VectorInt2D(x, y));
                village.placeBuilding(go);
            }
            return village;
        }

        /**
         * Creates a game object representation of a building.
         *
         * @param type the type of the building
         * @param position the position of the building
         *
         * @return the game object
         */
        protected abstract GameObject createGameObject(VillageElementData type, VectorInt2D position);
}
