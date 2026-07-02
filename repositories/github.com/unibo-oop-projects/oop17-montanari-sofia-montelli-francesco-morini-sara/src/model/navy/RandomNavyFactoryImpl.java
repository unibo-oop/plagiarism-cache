package model.navy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.basic_component.StaticPoint2D;
import model.ship.BuilderShip;
import model.ship.BuilderShipImpl;
import model.ship.Ship;

/**
 * Basic implementation of the {@link RandomNavyFactory}.
 */

public final class RandomNavyFactoryImpl implements RandomNavyFactory {
    /**
     * filed for logging.
     */
    private static final Logger LOGGER = LogManager.getLogger(RandomNavyFactoryImpl.class.getName());

    @Override
    public Navy getClassicRandomFormation() {
        LOGGER.trace("Creating classic random formation");
        return getCostumRandomFormation(Arrays.asList(4, 3, 2, 1), 10);
    }

    @Override
    public Navy getCostumRandomFormation(final List<Integer> sizes, final int gridSide) throws IllegalArgumentException {
        LOGGER.trace("Creating personalized random formation");
        return getCostumRandomFormation(new NavyBuilderImpl(sizes, gridSide));
    }

    @Override
    public Navy getCostumRandomFormation(final NavyBuilder builder) {
        LOGGER.trace("Crete random formation with a given builder");
        final BuilderShip builderShip = new BuilderShipImpl();

        LOGGER.debug("Begin building");
        while (!builder.canBuild()) {
            LOGGER.debug("Missing ships: {}", builder.getMissingShips());
            final int currentSize = IntStream.iterate(0, i -> i + 1).limit(Ship.MAX_SHIP_SIZE)
                                       .filter(i -> builder.getMissingShips().get(i) > 0)
                                       .map(i -> i + 1)
                                       .max()
                                       .getAsInt();

            LOGGER.debug("Current size {}: ", currentSize);
            LOGGER.debug("Avaiable cell #: {}", builder.getAvailablePositions().size());
            if (builder.getAvailablePositions().size() < currentSize) {
                LOGGER.debug("Impossibile to build, beginning reset");
                builder.reset();
                builderShip.reset();
            }

            builderShip.setSizeShip(currentSize);
            LOGGER.debug("Current ship size: {}", builderShip.getSize());
            LOGGER.debug("Avaiable position for first coord: {}", builder.getAvailablePositions());
            builderShip.setFirstCoord(getRandomFromSet(builder.getAvailablePositions()));
            LOGGER.debug("Setted cord: {}", builderShip.getFirstCoord());
            if (currentSize != 1) {
                LOGGER.debug("Second coord required");
                final Set<StaticPoint2D> secondCordSet = builder.getAvailablePositionsSecondCord(builderShip.getFirstCoord().get(), currentSize);
                LOGGER.debug("Possible second coord: {}", secondCordSet);
                if (secondCordSet.isEmpty()) {
                    LOGGER.debug("No second coord avaiable, reset");
                    builder.reset();
                    builderShip.reset();
                } else {
                    builderShip.setSecondCoord(getRandomFromSet(secondCordSet));
                    LOGGER.debug("Second cord setted: {}", builderShip.getSecondCoord());
                }
            }
            if (builderShip.canBuild()) {
                LOGGER.debug("Ship builded!");
                builder.addShip(builderShip.build());
            }
        }
        return builder.buildNavy();
    }

    private StaticPoint2D getRandomFromSet(final Set<StaticPoint2D> set) {
        final Random random = new Random();
        return set.stream().collect(Collectors.toList()).get(random.nextInt(set.size()));
    }

}
