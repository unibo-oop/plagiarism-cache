package model.common;

public class DimensionImpl implements Dimension {
    private final double height;
    private final double width;

    public DimensionImpl(final double height, final double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

}
