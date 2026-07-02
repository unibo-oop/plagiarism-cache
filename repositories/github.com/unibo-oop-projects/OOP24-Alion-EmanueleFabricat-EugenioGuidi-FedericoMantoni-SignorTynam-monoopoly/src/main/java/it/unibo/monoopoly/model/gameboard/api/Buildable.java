package it.unibo.monoopoly.model.gameboard.api;

/**
 * This interface rapresents a buildable property in the game that implements
 * {@link Buyable}.
 * The class is used to construct and sell houses.
 */
public interface Buildable extends Buyable {

    /**
     * Gets the cost of the house.
     * 
     * @return the cost of the house
     */
    int getHouseCost();

    /**
     * build a house if possible.
     */
    void buildHouse();

    /**
     * Gets the value of selling a house.
     * 
     * @return the value of selling a house
     */
    int sellHouse();

    /**
     * Gets the number of house constructed.
     * 
     * @return the number of house constructed
     */
    int getHousesNumber();

    /**
     * Gets the cost of selling house.
     * 
     * @return the cost of selling house
     */
    int getSellHouseCost();

}
