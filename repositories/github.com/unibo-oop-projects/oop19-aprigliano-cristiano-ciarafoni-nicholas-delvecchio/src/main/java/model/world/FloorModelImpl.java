package model.world;

public final class FloorModelImpl implements FloorModel {

    private static final double POS_X = 0.0;
    private static final double POS_Y = 340.0;
    private static final double WIDTH = 600;
    private static final double HEIGHT = 60.0;

    private final String floorImgPath;
    private final double posX;
    private final double posY;

    public FloorModelImpl() {
        this.posX = POS_X;
        this.posY = POS_Y;
        this.floorImgPath = "images/floor.jpg";
    }

    @Override
    public String getFloorImgPath() {
        return this.floorImgPath;
    }

    @Override
    public double getFloorPosX() {
        return this.posX;
    }

    @Override
    public double getFloorPosY() {
        return this.posY;
    }

    @Override
    public double getFloorWidth() {
        return this.WIDTH;
    }

    @Override
    public double getFloorHeight() {
        return this.HEIGHT;
    }
}
