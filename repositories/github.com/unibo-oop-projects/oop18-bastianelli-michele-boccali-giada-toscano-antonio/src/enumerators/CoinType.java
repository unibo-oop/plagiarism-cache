package enumerators;

/**
 * Contain all the coin types of the game.
 */
public enum CoinType implements SpecificType {
    SIMPLE("coin.png", 40, 40, 1);

    private String imageName;
    private int width;
    private int height;
    private int value;

    private CoinType(final String imageName, final int width, final int height, final int value) {
        this.imageName = imageName;
        this.width = width;
        this.height = height;
        this.value = value;
    }

    @Override
    public String getImageName() {
        return imageName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }

}
