package model;

import java.util.List;

/**
 * plants store.
 */
public interface Greenhouse {

    /**
     * fa la update da solo del numero dei fiori non serve inserirla prima.
     * @return the total number of flowers
     */
    int getNumFlowers();


    /**
     * sets the total number of flowers.
     * ATTENZIONE a usarlo!!!
     * preferibile settare i signoli fiori con setAllFlowerNum
     * e poi fare la update!
     * @param numFlowers number of flowers
     */ 
    void setNumFlowers(int numFlowers);

    /**
     * @return the free space
     * (the flowers available)
     */
    int getNumFreeSpaces();

    /**
     * 
     * @return true if the greenhouse is empty
     */
    boolean isEmpty(); 

    /**
     * This method return the minimum free position in the list.
     * 
     * @return a integer that represent the spot free in the list.
     */
    int minPosFree();

    /**
     * 
     * @return list of plant
     */
    List<PlantImpl> getGreenHouse(); //ritornare lista di plant

    //setter fiori
    /**
     * 
     * @param roseNum number of roses.
     * @param tulipNum number of tulip.
     * @param sunflowerNum number of sunflower.
     * @param lilyNum number of lily.
     * @param genericPTNum number of genericPlants.
     */
    void setAllFlowerNum(int roseNum, int tulipNum, int sunflowerNum, int lilyNum, int genericPTNum); //sunfloewr?

    /**
     * 
     * @param roseNum the number of tulips in the greenhouse
     */
    void setRoseNum(int roseNum);

    /**
     * 
     * @param tulipNum the number of tulips in the greenhouse
     */
    void setTulipNum(int tulipNum);

    /**
     * 
     * @param sunflowerNum the number of sunflowers in the greenhouse
     */
    void setSunflowerNum(int sunflowerNum);

    /**
     * 
     * @param lilyNum the number of lilies in the greenhouse
     */
    void setLilyNum(int lilyNum);

    /**
     * 
     * @param genericPTNum the number of generic plants in the greenhouse
     */
    void setGenericPTNum(int genericPTNum);




    //getter fiori

    /**
     * 
     * @return the number of roses in the greenhouse
     */
    int getRoseNum();


    /**
     * 
     * @return the number of tulips in the greenhouse
     */
    int getTulipNum();

    /**
     * 
     * @return the number of sunflowers in the greenhouse
     */
    int getSunflowerNum();

    /**
     * 
     * @return the number of lilies in the greenhouse
     */
    int getLilyNum();

    /**
     * 
     * @return the number of generic plants in the greenhouse
     */
    int getGenericPTNum();


}
