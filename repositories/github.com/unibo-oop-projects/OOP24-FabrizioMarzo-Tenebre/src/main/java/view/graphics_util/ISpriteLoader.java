package view.graphics_util;

import java.awt.image.BufferedImage;

import java.util.List;

/**
 * Interface for loading sprite animations and images used in the game.
 */
public interface ISpriteLoader {

        /**
         * Loads the animations for a Survivor character.
         *
         * @param nameSurvivor the name identifier of the survivor
         * @param width_frame  width of each animation frame
         * @param height_frame height of each animation frame
         * @return a list of animation frames grouped by animation state
         */
        List<List<BufferedImage>> loadSurvivorAnimations(final String nameSurvivor, final int width_frame,
                        final int height_frame);

        /**
         * Loads the animations for a Zombie character.
         *
         * @param nameZombie   the name identifier of the zombie
         * @param width_frame  width of each animation frame
         * @param height_frame height of each animation frame
         * @return a list of animation frames grouped by animation state
         */
        List<List<BufferedImage>> loadZombieAnimations(final String nameZombie, final int width_frame,
                        final int height_frame);

        /**
         * Loads the shadow image for entities.
         *
         * @param nameObject the name identifier of the object
         * @return the shadow image as a BufferedImage
         */
        BufferedImage loadEntitiesShadow(final String nameObject);

        /**
         * Loads the weapon image for entities.
         *
         * @param nameWeapon the name identifier of the weapon
         * @return the weapon image as a BufferedImage
         */
        BufferedImage loadEntitiesWeapon(final String nameWeapon);

        /**
         * Loads the image for a munition.
         *
         * @param nameMunition the name identifier of the munition
         * @return the munition image as a BufferedImage
         */
        BufferedImage loadMunition(final String nameMunition);

        /**
         * Loads level sprite images sliced into frames.
         *
         * @param nameLevelSprite the name identifier of the level sprite sheet
         * @param width_frame     width of each sprite frame
         * @param height_frame    height of each sprite frame
         * @return a list of sprite frames for the level
         */
        List<BufferedImage> loadLevelSprite(final String nameLevelSprite, final int width_frame,
                        final int height_frame);

        /**
         * Retrieves level data (e.g., tile indices).
         *
         * @param nameLevelData the name identifier of the level data
         * @return a 2D list of integers representing the level tile layout
         */
        List<List<Integer>> getLevelData(final String nameLevelData);
}
