package model.battlefield;

import javafx.scene.paint.Color;

/**
 *
 * @author Andrea Manoni
 *
 */
public class Fluid implements BattlefieldComponent {

    private final String name;
    private final double density;
    private final Color terrainColor;
    private final double maxFluidSpeed;
    private final int maxProjectileInitialSpeed;
    private final String backgroundImage;

    /**
     * Constructor.
     *
     * @param name
     *            name
     * @param density
     *            density
     * @param terrainColor
     *            terrainColor
     * @param maxFluidSpeed
     *            maxFluidSpeed
     * @param maxProjectileInitialSpeed
     *            maxProjectileInitialSpeed
     * @param backgroundImage
     *            backgroundImage
     */
    public Fluid(final String name, final double density, final Color terrainColor, final double maxFluidSpeed,
            final int maxProjectileInitialSpeed, final String backgroundImage) {
        this.name = name;
        this.density = density;
        this.terrainColor = new Color(terrainColor.getRed(), terrainColor.getBlue(), terrainColor.getGreen(),
                terrainColor.getOpacity());
        this.maxFluidSpeed = maxFluidSpeed;
        this.maxProjectileInitialSpeed = maxProjectileInitialSpeed;
        this.backgroundImage = backgroundImage;
    }

    @Override
    public final String getName() {
        return name;
    }

    /**
     * Gets density of the Fluid.
     *
     * @return density
     */
    public final double getDensity() {
        return density;
    }

    /**
     * Gets terrainColor of the Fluid.
     *
     * @return terrainColor
     */
    public final Color getTerrainColor() {
        return terrainColor;
    }

    /**
     *
     * @return maxFluidSpeed
     */
    public final double getMaxFluidSpeed() {
        return maxFluidSpeed;
    }

    /**
     *
     * @return maxProjectileInitialSpeed
     */
    public final int getMaxProjectileInitialSpeed() {
        return maxProjectileInitialSpeed;
    }

    /**
     *
     * @return backgroundImage
     */
    public final String getBackgroundImage() {
        return backgroundImage;
    }
}
