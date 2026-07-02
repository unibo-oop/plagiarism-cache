package it.unibo.scat.view.game.canvas;

import java.awt.Image;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.ImageIcon;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.game.api.SpriteManager;

/**
 * Centralized loader + scaler + saver for all sprites.
 * Images are loaded, scaled and then saved.
 */
public final class SpriteManagerImpl implements SpriteManager {
        private final Map<EntityType, Image[]> scaledImages = new EnumMap<>(EntityType.class);

        /**
         * Initializes the sprite manager and triggers the loading process.
         *
         * @param scaleX horizontal scaling factor.
         * @param scaleY vertical scaling factor.
         */
        public SpriteManagerImpl(final int scaleX, final int scaleY) {
                loadAndScaleSprites(scaleX, scaleY);
        }

        /**
         * Gets the sprite associated with the specified entity and animation frame.
         * 
         * @param type  the type of entity.
         * @param frame the index of the animation frame to retrieve.
         * @return the image to draw.
         */
        @Override
        public Image getSprite(
                        final EntityType type,
                        final int frame) {

                final Image[] images = scaledImages.get(type);
                return images[Math.max(Math.min(frame, images.length - 1), 0)];
        }

        /**
         * Loads the images for every game object and resizes them.
         * 
         * @param scaleX the horizontal zoom.
         * @param scaleY the vertical zoom.
         */
        private void loadAndScaleSprites(final int scaleX, final int scaleY) {
                put(EntityType.PLAYER,
                                UIConstants.PLAYER_PATHS,
                                Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.PLAYER_SHOT,
                                UIConstants.PLAYER_SHOT_PATHS,
                                Constants.SHOT_WIDTH, Constants.SHOT_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_1,
                                UIConstants.INVADER1_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_2,
                                UIConstants.INVADER2_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.INVADER_3,
                                UIConstants.INVADER3_PATHS,
                                Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                                scaleX, scaleY);
                put(EntityType.BUNKER,
                                UIConstants.BUNKER_PATHS,
                                Constants.BUNKER_WIDTH, Constants.BUNKER_HEIGHT,
                                scaleX, scaleY);

                scaledImages.put(
                                EntityType.INVADER_SHOT,
                                new Image[] {
                                                scale(loadSingle(
                                                                UIConstants.INVADER_SHOT_PATH),
                                                                Constants.SHOT_WIDTH * scaleX,
                                                                Constants.SHOT_HEIGHT * scaleY),
                                });
                scaledImages.put(EntityType.BONUS_INVADER, new Image[] {
                                scale(loadSingle(
                                                UIConstants.BONUS_INVADER_PATH),
                                                Constants.BONUS_INVADER_WIDTH * scaleX,
                                                Constants.BONUS_INVADER_HEIGHT * scaleY),
                });
        }

        /**
         * Helper method to load and resize a list of images for a specific entity.
         * 
         * @param type   the entity type.
         * @param paths  the list of file paths.
         * @param baseW  the original width.
         * @param baseH  the original height.
         * @param scaleX the horizontal zoom.
         * @param scaleY the vertical zoom.
         */
        private void put(
                        final EntityType type,
                        final List<String> paths,
                        final int baseW,
                        final int baseH,
                        final int scaleX,
                        final int scaleY) {

                final Image[] out = new Image[paths.size()];
                for (int i = 0; i < paths.size(); i++) {
                        out[i] = scale(
                                        loadSingle(paths.get(i)),
                                        baseW * scaleX,
                                        baseH * scaleY);
                }
                scaledImages.put(type, out);
        }

        /**
         * Loads a single image file.
         * 
         * @param path location of the file.
         * @return the loaded image.
         */
        private static Image loadSingle(final String path) {
                return new ImageIcon(
                                Objects.requireNonNull(SpriteManagerImpl.class.getResource(path))).getImage();
        }

        /**
         * Changes the size of an image.
         * 
         * @param img the original image.
         * @param w   the new width.
         * @param h   the new height.
         * @return the resized image.
         */
        private static Image scale(final Image img, final int w, final int h) {
                return img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        }
}
