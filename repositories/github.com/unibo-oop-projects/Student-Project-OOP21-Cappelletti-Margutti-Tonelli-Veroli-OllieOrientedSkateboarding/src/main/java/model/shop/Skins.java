package model.shop;

/**
 * Lists all of the prices.
 *
 */
public enum Skins {

    /**
     * Default player's skin. 
     */
    PLAYER("Player.png", 0),
    /**
     * Programmer skin and its price.
     */
    PROGRAMMER("Programmer.png", 1000), 

    /**
     * Dinosaur skin and its price. 
     */
    DINOSAUR("Dinosaur.png", 2000); 

    private final int price; 
    private final String name; 

    Skins(final String name, final int price) {
        this.price = price; 
        this.name = name; 
    }

    public int getPrice() {
        return price;
    }

    public String getSkinName() {
        return name;
    }
}
