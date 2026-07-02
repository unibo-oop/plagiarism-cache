package model.gameObject;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import model.Direction;
import model.ID;
import other.Pair;

public interface GameObjectInterface {

    /**
     * @return the images
     * 
     *         method to get Map of texture
     * 
     */
    Map<Direction, List<BufferedImage>> getImages();

    /**
     * @return single texture image
     * 
     *         getter texture image
     * 
     */
    BufferedImage getImage();

    /**
     * @param images
     * 
     *               set images
     * 
     */
    void setImages(List<Pair<Direction, BufferedImage>> images);

    /**
     * @return coordinates pair
     * 
     *         getter coordinates
     * 
     */
    Pair<Integer, Integer> getCoord();

    /**
     * @return dimension pair
     * 
     *         getter dimension
     * 
     */
    Pair<Integer, Integer> getDimension();

    /**
     * @param dimension
     * 
     *                  setter dimension
     * 
     */
    void setDimension(Pair<Integer, Integer> dimension);

    /**
     * @param coord
     * 
     *              setter coordinates
     * 
     */
    void setCoord(Pair<Integer, Integer> coord);

    /**
     * @return pair velocity
     * 
     *         getter velocity
     * 
     */
    Pair<Double, Double> getVelocity();

    /**
     * @param vel
     * 
     *            setter velocity
     * 
     */
    void setVelocity(Pair<Double, Double> vel);

    /**
     * @return id
     * 
     *         getter ID
     * 
     */
    ID getId();

    /**
     * @param id
     * 
     *           setter ID
     * 
     */
    void setId(ID id);

    /**
     * @return texture list
     * 
     *         getter list of UP texture
     * 
     */
    List<BufferedImage> getListUP();

    /**
     * @return texture list
     * 
     *         getter list of DOWN texture
     * 
     */
    List<BufferedImage> getListDOWN();

    /**
     * @return texture list
     * 
     *         getter list of RIGHT texture
     * 
     */
    List<BufferedImage> getListRIGHT();

    /**
     * @return texture list
     * 
     *         getter list of LEFT texture
     * 
     */
    List<BufferedImage> getListLEFT();

    /**
     * @return rectangle
     * 
     *         getter rectangle
     * 
     */
    Rectangle getRectangle();

    /**
     * 
     * @param direction
     * @return list of texture given a direction
     * 
     *         getter texture by direction
     */
    List<BufferedImage> getTextureByDirection(Direction direction);

    /**
     * @return true if is visible, no otherwise
     * 
     *         check visibility
     * 
     */
    boolean isVisible();

    /**
     * @param visible
     * 
     *                setter visible
     * 
     */
    void setVisible(boolean visible);

    /**
     * method to refresh values.
     */
    void tick();

    /**
     * 
     * @param newImage
     * 
     * setter new single texture image
     */
    void setImage(BufferedImage newImage);
}
