package controllers.texture.imageLoader;

import java.awt.image.BufferedImage;
import java.util.List;

import model.Direction;
import other.Pair;

public interface ImageLoaderInterface {

    /**
     * @param image
     * @param row
     * @param column
     * @param width
     * @param height
     * 
     *               method to get an image from tilesheet by row and column
     * 
     * @return image
     *
     */
    BufferedImage getImageByRowColumn(BufferedImage image, int row, int column, int width, int height);

    /**
     * @param image
     * @param row
     * @param width
     * @param height
     * 
     *               method to get an image from tilesheet by row
     * 
     * @return image list
     *
     */
    List<BufferedImage> getImageByRow(BufferedImage image, int row, int width, int height);

    /**
     * @param image
     * @param column
     * @param width
     * @param height
     * 
     *               method to get an image from tilesheet by column
     * 
     * @return image list
     *
     */
    List<BufferedImage> getImageByColumn(BufferedImage image, int column, int width, int height);

    /**
     * @param image
     * @param direction
     * @param row
     * @param width
     * @param height
     * 
     *                  method to get an image from tilesheet by row and column
     * 
     * @return image
     *
     */
    List<Pair<Direction, BufferedImage>> textureByDirectionList(BufferedImage image, Direction direction, int row,
            int width, int height);

    /**
     * @param angle
     * @param image
     * 
     *              method to rotate an image given an angle
     * 
     * @return image
     *
     */
    BufferedImage rotateImage(int angle, BufferedImage image);

    /**
     * @param image
     * 
     *              method to optimize a BufferedImage
     * 
     * @return image
     *
     */
    BufferedImage getOptimizedImage(BufferedImage image);
}
