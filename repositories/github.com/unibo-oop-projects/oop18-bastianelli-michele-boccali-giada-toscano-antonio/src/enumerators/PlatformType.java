package enumerators;

/**
 * Contain all the platform those can be found in the game. A platform type has
 * to contain at least its width and height
 */
public enum PlatformType implements SpecificType {
    SIMPLE("platf.png", 70, 10),
    SUPERJUMP("superplatf.png", 70, 10),
    ONEJUMP("oneplatf.png", 70, 10);

    private int width;
    private int height;
    private String imageName;

    private PlatformType(final String imageName, final int width, final int height) {
        this.imageName = imageName;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String getImageName() {
        return imageName;
    }
}