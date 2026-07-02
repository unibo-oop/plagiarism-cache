package model.room;

public enum RoomType {

    /**
     * 
     */
    SINGLE(25, 1),

    /**
     * 
     */
    DOUBLE(35, 2),

    /**
     * 
     */
    TRIPLE(45, 3),

    /**
     * 
     */
    QUARTUPLE(60, 4),

    /**
     * 
     */
    SUITE(100, 2);

    private double price;
    private int beds;

    RoomType(final int price, final int beds) {
        this.price = price;
        this.beds = beds;
    }

    public double getPrice() {
        return this.price;
    }

    public int getBeds() {
        return this.beds;
    }

}
