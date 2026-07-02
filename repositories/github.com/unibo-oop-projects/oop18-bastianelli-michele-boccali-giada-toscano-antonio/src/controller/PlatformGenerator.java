package controller;

import java.util.Random;
import java.util.Set;

import org.jbox2d.common.Vec2;

import common.CommonStrings;
import controller.entities.Platform;
import enumerators.Level;
import enumerators.PlatformType;
import factories.AbstractFactory;

/**
 * Generates platforms based on player height and difficulty level.
 */
public class PlatformGenerator implements Generator {

    private static final int MAX_PLATFORMS = 20;
    private static final int SPECIAL_PLATFORM_CHANCE = 5;

    private static final double PLATFORM_SPACING = 100.0;
    private static final double MAX_JUMP = 100;
    private static final double SAFE_SPACE_FACTOR = 0.2;
    private static final double MAX_DISTANCE_FACTOR = 1.8;
    private static final double MIN_DISTANCE_FACTOR = 0.8;

    private static final Random RANDOM = new Random();

    private Level level;

    /**
     * Generates the starting platforms.
     * 
     * @param level to generate
     */
    public void init(final Level level) {
        this.level = level;
        double fill = 0;
        // stub platform to get height
        @SuppressWarnings("unused")
        final double platformHeight = AbstractFactory
                .createPlatform(PlatformType.SIMPLE, new Vec2(CommonStrings.WINDOW_HEIGHT, CommonStrings.WINDOW_WIDTH))
                .getDimension().y;

        while (fill < CommonStrings.WINDOW_WIDTH) {
            final Vec2 position = new Vec2((float) fill,
                    (float) (CommonStrings.WINDOW_HEIGHT - PLATFORM_SPACING));
            //Log.add(position.y + " = y");

            createPlatform(PlatformType.SIMPLE, position);
            final Platform topPlatform =  GameController.getInstance().getGameModel().getTopPlatform();
            fill += topPlatform.getDimension().x;
        }
        while (GameController.getInstance().getGameModel().getPlatformSet().size() < MAX_PLATFORMS) {
            final Platform topPlatform =  GameController.getInstance().getGameModel().getTopPlatform();
            final Vec2 position = calculatePlatformPosition((PlatformType) topPlatform.getModel().getSpecificType());
            createPlatform(PlatformType.SIMPLE, position);
        }
    }

    /**
     * Checks if new platforms are needed, and in that case it generates some.
     */
    public void update() {
        final Set<Platform> platforms = GameController.getInstance().getGameModel().getPlatformSet();
        if (platforms.size() < MAX_PLATFORMS) {
            final Platform topPlatform =  GameController.getInstance().getGameModel().getTopPlatform();
            final PlatformType topPlatformType = (PlatformType) topPlatform.getModel().getSpecificType();
            if (topPlatformType.equals(PlatformType.SIMPLE)) {
                if (RANDOM.nextInt(SPECIAL_PLATFORM_CHANCE) == 0) {
                    final Vec2 position = calculatePlatformPosition(topPlatformType);
                    createPlatform(PlatformType.SUPERJUMP, position);
                } else {
                    final Vec2 position = calculatePlatformPosition(topPlatformType);
                    createPlatform(PlatformType.SIMPLE, position);
                }
            } else {
                if (RANDOM.nextInt(SPECIAL_PLATFORM_CHANCE) == 0) {
                    final Vec2 oneJumpPosition = calculateOneJumpPlatformPosition(topPlatform);
                    createPlatform(PlatformType.ONEJUMP, oneJumpPosition);
                } else {
                    final Vec2 position = calculatePlatformPosition(topPlatformType);
                    createPlatform(PlatformType.SIMPLE, position);
                }
            }
        }
    }

    private void createPlatform(final PlatformType platformType, final Vec2 position) {
        final Platform newPlatform = AbstractFactory.createPlatform(platformType, position);
        GameController.getInstance().getGameModel().addEntityToMap(newPlatform, newPlatform.getBody());
        GameController.getInstance().getGameModel().setTopPlatform(newPlatform);
    }

    private Vec2 calculateOneJumpPlatformPosition(final Platform basePlatform) {
        final double side = RANDOM.nextInt(2) == 0 ? 1 : -1; 
        final float oneJumpPlatformX = (float) (basePlatform.getPhysicPosition().x + ((basePlatform.getDimension().x * 2) * side));
        final double yDistanceFactor = MIN_DISTANCE_FACTOR + RANDOM.nextDouble() * (MAX_DISTANCE_FACTOR - MIN_DISTANCE_FACTOR);
        return new Vec2(oneJumpPlatformX, (float) (basePlatform.getPhysicPosition().y * yDistanceFactor));
    }

    /**
     * Calculates the new platform position based on the last platform PlatformType.
     * @param previousPlatformType
     * @return the new platform position
     */
    private Vec2 calculatePlatformPosition(final PlatformType previousPlatformType) {
        final double maxHeight = GameController.getInstance().getGameModel().getMaxHeight();
        final double levelDifficulty = level.getDifficulty();
        final double heightFactor = (maxHeight * levelDifficulty) / GameController.VICTORY_HEIGHT; 
        final Platform topPlatform = GameController.getInstance().getGameModel().getTopPlatform();

        double spacing = PLATFORM_SPACING * (1 + heightFactor);

        if (spacing > MAX_JUMP - topPlatform.getDimension().y) {
            spacing = MAX_JUMP - topPlatform.getDimension().y;
        }
        if (previousPlatformType.equals(PlatformType.SUPERJUMP)) {
            spacing = spacing * 2;
        }

        final double xDistanceFactor = MIN_DISTANCE_FACTOR + RANDOM.nextDouble() * (MAX_DISTANCE_FACTOR - MIN_DISTANCE_FACTOR);
        double platformX = topPlatform.getViewPosition().x * xDistanceFactor * levelDifficulty;
        platformX = recenterX(platformX, topPlatform.getDimension().x);

        final double platformY = topPlatform.getPhysicPosition().y - spacing;
        return new Vec2((float) platformX, (float) platformY);
    }

    private double recenterX(final double platformX, final double platformXDimension) {
        final double screenWidth = CommonStrings.WINDOW_WIDTH - platformXDimension;
        if (platformX > screenWidth) {
            final double deviation = platformX - screenWidth * SAFE_SPACE_FACTOR;
            return platformX - deviation;
        }
        if (platformX < 0) {
            return screenWidth * SAFE_SPACE_FACTOR;
        }
        return platformX;
    }
}
