package enumerators;

/**
 * Contain all the player characters those can be chosen. Be sure that the
 * linked image has the same width and height. A player character has to contain
 * at least its with, height, image name and money cost to be unlocked.
 */
public enum PlayerCharacter implements SpecificType {

    BIRD(60, 44, "bird.png", 0), SHEEP(58, 60, "sheep.png", 50), TUX(49, 63, "tux.png", 100);

    private String imageName;
    private int cost;
    private int width;
    private int height;

    PlayerCharacter(final int width, final int height, final String imageName, final int cost) {
        this.width = width;
        this.height = height;
        this.imageName = imageName;
        this.cost = cost;
    }

    /**
     * @return the image name of the character
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @return the money cost of the character
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the character's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the character's height
     */
    public int getHeight() {
        return height;
    }

}
