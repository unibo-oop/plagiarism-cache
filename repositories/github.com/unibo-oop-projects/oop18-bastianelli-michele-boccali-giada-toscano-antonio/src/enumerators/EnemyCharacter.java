package enumerators;

/**
 * Contain all the enemies of the game. Enemies dimension will be used to
 * calculate collisions and placing. Be sure that the linked image has the same
 * width and height. A new enemy has to contain at least the character
 * dimension.
 */
public enum EnemyCharacter implements SpecificType {
    GOOMBA("goomba.png", 40, 40), FROSTY("frosty.png", 30, 30), BOMB("bomb.png", 30, 30),
    PARABEETLE("parabeetle.png", 52, 32);

    private int width;
    private int height;
    private String imageName;

    private EnemyCharacter(final String imageName, final int width, final int height) {
        this.imageName = imageName;
        this.width = width;
        this.height = height;
    }

    /**
     * Return the character width
     * 
     * @return the width of the character
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return the character height
     * 
     * @return the height of the character
     */
    public int getHeight() {
        return height;
    }

    @Override
    public String getImageName() {
        return imageName;
    }
}
