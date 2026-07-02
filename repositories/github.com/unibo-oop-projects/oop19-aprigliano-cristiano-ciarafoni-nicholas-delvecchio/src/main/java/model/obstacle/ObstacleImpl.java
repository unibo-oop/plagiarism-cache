package model.obstacle;

/**
 * Implementation of the Interface Obstacle , it handles the Obstacles Objects.
 */
public final class ObstacleImpl implements Obstacle {

    private final double height; //From JSON file
    private final double width; //From JSON File
    private double posX;
    private double posY;
    private final String imagePath;
    private boolean powerUp; // 0 obstacle, 1 power-up

    public ObstacleImpl(final double height, final double width, final double posX, final double posY, final String imagePath) {
        this.height = height;
        this.width = width;
        this.posX = posX;
        this.posY = posY;
        this.imagePath = imagePath;
    }

    @Override
    public double getPosX() {
        return this.posX;
    }

    @Override
    public double getPosY() {
        return this.posY;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean getPowerUp() {
        return this.powerUp;
    }

    @Override
    public void setPowerUp(boolean power) {
        this.powerUp = power;
    }
}
