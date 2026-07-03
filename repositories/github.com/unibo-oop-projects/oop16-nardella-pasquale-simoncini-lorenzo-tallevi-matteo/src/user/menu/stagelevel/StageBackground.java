package user.menu.stagelevel;

import java.util.ArrayList;
import java.util.List;

import user.enums.MenuSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the common background for the game stages rooms. Put it
 * in a room and it does the magic. 3D effect is triggered when moving or
 * zooming the camera.
 */
public class StageBackground extends GameObject {

    // describe the minimum and maximum spawn distance of the back stars
    private static final double Z_MIN = -3000;
    private static final double Z_MAX = 175;
    private static final double Z_OFFSET_RANGE = 300;

    // small shift to the x and y coordinates of each chunk of stars to give
    // illusion of randomness
    private static final double XY_OFFSET_RANGE = 100;

    // distance from the camera of the front layer of space dust
    private static final double Z_FRONTLAYER = 650;

    // alpha of the furthest stars to the nearest stars
    private static final double ALPHA_MIN = 0.3;
    private static final double ALPHA_MAX = 0.8;

    // alpha of the front layer of dust
    private static final double ALPHA_FRONTLAYER = 0.265;

    // layers of background stars
    private static final int LAYERS = 4;

    // high depth it means it will be rendered behind everything
    private static final int DEPTH = 1000;

    // stores infor of the generated background
    private final List<BackTile> clusterList = new ArrayList<>();

    @Override
    public void create() {
        setSpriteIndex(MenuSprites.STAGE_BACKGROUND.getValue());
        setDepth(DEPTH);
        setX(0);
        setY(0);

        // size in pixels of a chunk
        final int tileWidth = z().spriteGetWidth(getSpriteIndex());
        final int tileHeight = z().spriteGetHeight(getSpriteIndex());
        // number of chunks
        final int horSteps = ((int) z().roomWidth() / tileWidth) + 2;
        final int verSteps = ((int) z().roomHeight() / tileHeight) + 2;
        // z of the current layer
        double layerZ;
        // factor of the current layer (0=furthest, 1=nearest)
        double layerFactor;

        // generate background, iterating layers
        for (int i = 0; i < LAYERS; i++) {
            layerFactor = ((double) i) / ((double) LAYERS - 1);
            layerZ = z().lerp(Z_MIN, Z_MAX, layerFactor);
            // iterate chunks
            for (int xx = 0; xx < horSteps; xx++) {
                for (int yy = 0; yy < verSteps; yy++) {
                    final double actualZ = layerZ + z().randomRange(-Z_OFFSET_RANGE, Z_OFFSET_RANGE);
                    final double actualAlpha = z().lerp(ALPHA_MIN, ALPHA_MAX, layerFactor);
                    clusterList.add(new BackTile(
                            (double) xx * tileWidth - tileWidth / 2 + z().randomRange(-XY_OFFSET_RANGE, Z_OFFSET_RANGE),
                            (double) yy * tileHeight - tileHeight / 2
                                    + z().randomRange(-XY_OFFSET_RANGE, XY_OFFSET_RANGE),
                            actualZ, actualAlpha));
                }
            }
        }

        // generate front layer of space dust, iterating chunks
        for (int xx = 0; xx < horSteps; xx++) {
            for (int yy = 0; yy < verSteps; yy++) {
                final double actualZ = Z_FRONTLAYER + z().randomRange(-Z_OFFSET_RANGE / 4, Z_OFFSET_RANGE / 4);
                clusterList.add(new BackTile((double) xx * tileWidth - tileWidth / 2,
                        (double) yy * tileHeight - tileHeight / 2, actualZ, ALPHA_FRONTLAYER));
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        for (final BackTile b : clusterList) {
            b.drawSelf(getX(), getY());
        }
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {

    }

    /**
     * a single cluster of stars.
     */
    private class BackTile {
        private final double x;
        private final double y;
        private final double z;
        private final double alpha;

        BackTile(final double x, final double y, final double z, final double alpha) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.alpha = alpha;
        }

        /**
         * draws the cluster at its position, shifted by a certain amount
         * 
         * @param xShift
         *            horizontal shift
         * @param yShift
         *            vertical shift
         */
        private void drawSelf(final double xShift, final double yShift) {
            z().drawSprite3D(getSpriteIndex(), 0, x + xShift, y + yShift, z, 1, 1, 0, alpha);
        }
    }
}
