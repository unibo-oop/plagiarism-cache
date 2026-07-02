package controllers.texture;

import java.awt.image.BufferedImage;
import java.util.List;

import model.Direction;
import model.ID;
import other.Pair;

public interface GetTextureInterface {

    /**
     * @return texture list
     * 
     *         method to get supportEnemy texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getSupportEnemyListTexture();

    /**
     * @return texture list
     * 
     *         method to get Player texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getPlayerListTexture();

    /**
     * @return texture list
     * 
     *         method to get baseEnemy texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getBaseEnemyListTexture();

    /**
     * @return texture list
     * 
     *         method to get avarageEnemyList texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getAvarageEnemyListTexture();

    /**
     * @return texture list
     * 
     *         method to get advanceEnemy texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getAdvanceEnemyListTexture();

    /**
     * @return texture list
     * 
     *         method to get dragonEnemy texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getDragonEnemyListTexture();

    /**
     * @param level
     * 
     *              method to get chest texture by level
     * 
     * @return chest texture
     */
    BufferedImage getChest(int level);

    /**
     * @param level
     * 
     *              method to get wall texture by level
     * 
     * @return chest texture
     */
    BufferedImage getWall(int level);

    /**
     * @param level
     * 
     *              method to get floor texture by level
     * 
     * @return floor texture
     */
    BufferedImage getFloor(int level);

    /**
     * @param level
     * 
     *              method to get level structure
     * 
     * @return level structure
     */
    BufferedImage getLevel(int level);

    /**
     * @return texture list
     * 
     *         method to get baseEnemyRay texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getBaseEnemyRayListTexture();

    /**
     * @return texture list
     * 
     *         method to get dragonEnemyRay texture list
     * 
     */
    List<Pair<Direction, BufferedImage>> getDragonEnemyRayListTexture();

    /**
     * @param id
     * 
     *           method to get Debuff texture by ID
     * 
     * @return Debuff texture
     */
    BufferedImage getDebuffImageByID(ID id);

    /**
     * @param id
     * 
     *           method to get powerUP texture by ID
     * 
     * @return powerUP texture
     */
    BufferedImage getPowerUPImageByID(ID id);

    /**
     * @return life texture
     * 
     *         method to get hearth texture
     * 
     */
    BufferedImage getLifeImage();

    /**
     * @return BaseEnemyRay texture
     * 
     *         method to get BaseRay texture
     * 
     */
    BufferedImage getBaseRayImage();

    /**
     * @return ComplexRay texture
     * 
     *         method to get ComplexRay texture
     * 
     */
    BufferedImage getComplexRayImage();

    /**
     * @return Lava texture
     * 
     *         method to get Lava texture
     * 
     */
    BufferedImage getLavaImage();

    /**
     * @return DarkWall texture
     * 
     *         method to get DarkWall texture
     * 
     */
    BufferedImage getDarkWallImage();

    /**
     * @return FinalDoor texture
     * 
     *         method to get FinalDoor texture
     * 
     */
    BufferedImage getFinalDoorImage();

    /**
     * @return InitialDoor texture
     * 
     *         method to get InitialDoor texture
     * 
     */
    BufferedImage getInitialDoorImage();

    /**
     * @return Portal texture
     * 
     *         method to get Portal texture
     * 
     */
    BufferedImage getPortalImage();

    /**
     * @return texture
     * 
     *         method to get towerEnemy texture
     * 
     */
    BufferedImage getTowerEnemyImage();

    /**
     * @return texture
     * 
     *         method to get towerEnemyRay list
     * 
     */
    BufferedImage getTowerEnemyRayImage();

}
