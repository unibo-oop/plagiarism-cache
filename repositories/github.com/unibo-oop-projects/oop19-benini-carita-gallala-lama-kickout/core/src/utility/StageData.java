package utility;

import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * The class in which the data needed for generating the stage is contained
 */
public class StageData {
    private String namePlayer1;
    private String namePlayer2;
    private int indexPlayer1;
    private int indexPlayer2;
    private Sprite imgMap;

    /**
     * @param indexPlayer1
     * 				The first chosen Fighter
     * @param indexPlayer2
     * 				The second chosen Fighter
     * @param imgMap
     * 				The chosen background
     */
    public StageData(final int indexPlayer1, final int indexPlayer2, final Sprite imgMap) {
        this.indexPlayer1 = indexPlayer1;
        this.indexPlayer2 = indexPlayer2;
        this.imgMap = imgMap;
    }

    /**
     * @return the first player's name
     */
    public String getPlayer1() {
        return this.namePlayer1;
    }

    /**
     * @return the second player's name
     */
    public String getPlayer2() {
        return this.namePlayer2;
    }

    /**
     * @return the index of the first player
     */
    public int getIndexPlayer1() {
        return this.indexPlayer1;
    }

    /**
     * @return the index of the second player
     */
    public int getIndexPlayer2() {
        return this.indexPlayer2;
    }

    /**
     * @return the chosen map
     */
    public Sprite getImgMap() {
        return this.imgMap;
    }

    /**
     * Sets the first player's name
     * @param namePlayer1
     */
    public void setNamePlayer1(final String namePlayer1) {
        this.namePlayer1 = namePlayer1;
    }

    /**
     * Sets the second player's name
     * @param namePlayer2
     */
    public void setNamePlayer2(final String namePlayer2) {
        this.namePlayer2 = namePlayer2;
    }

    /**
     * Sets the first player's index
     * @param indexPlayer1
     */
    public void setIndexPlayer1(final int indexPlayer1) {
        this.indexPlayer1 = indexPlayer1;
    }

    /**
     * Sets the second player's index
     * @param indexPlayer2
     */
    public void setIndexPlayer2(final int indexPlayer2) {
        this.indexPlayer2 = indexPlayer2;
    }

    /**
     * Sets the chosen background
     * @param imgMap
     */
    public void setImgMap(final Sprite imgMap) {
        this.imgMap = imgMap;
    }
}
