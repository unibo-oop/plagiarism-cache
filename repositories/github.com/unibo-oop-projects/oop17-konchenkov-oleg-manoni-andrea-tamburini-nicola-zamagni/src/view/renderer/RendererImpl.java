package view.renderer;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import controller.coloradapter.ColorAdapter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import model.collidable.tank.Tank;
import model.collidable.terrain.Terrain;
import model.explosion.Explosion;
import model.projectile.Projectile;

/**
 *
 * @author Oleg
 *
 */
public final class RendererImpl implements Renderer {

    private final Canvas canvas;
    private final Image backgroundImage;
    private final GraphicsContext graphics;
    private double scaleFactor;
    private final Color terrainColor;

    /**
     *
     * @param canvas
     *            canvas
     * @param backgroundImage
     *            background image
     * @param terrainColor
     *            terrain color
     */
    public RendererImpl(final Canvas canvas, final Image backgroundImage, final Color terrainColor) {
        super();
        this.canvas = canvas;
        this.backgroundImage = backgroundImage;
        graphics = canvas.getGraphicsContext2D();
        scaleFactor = 1;
        this.terrainColor = terrainColor;
    }

    @Override
    public void clearScreen() {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // g.setFill(Color.BLACK);
        graphics.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
        // g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void renderProjectile(final Projectile projectile) {
        final double radius = 2.0;
        final double outlineRadius = 3.0;

        IntStream.range(0, projectile.getNumberOfFragments()).forEach(i -> {
            graphics.setFill(Color.BLACK);
            graphics.fillOval(scaleFactor * (projectile.getPosition().get(i).getX() - outlineRadius),
                    canvas.getHeight() - scaleFactor * projectile.getPosition().get(i).getY() - outlineRadius,
                    2.0 * outlineRadius, 2.0 * outlineRadius);
            graphics.setFill(Color.WHITE);
            graphics.fillOval(scaleFactor * (projectile.getPosition().get(i).getX() - radius),
                    canvas.getHeight() - scaleFactor * projectile.getPosition().get(i).getY() - radius, 2.0 * radius,
                    2.0 * radius);
        });
    }

    @Override
    public void renderTank(final Tank tank) {

        final Color outLineColor = Color.BLACK;
        final List<Vector2D> points = tank.getOutlinePoints();
        final Color fillColor = new ColorAdapter(tank.getColor()).getFxColor();

        /* to draw outline */
        graphics.setStroke(outLineColor);
        graphics.setLineWidth(tank.getScaleFactor() * 2.0);
        graphics.strokeLine(scaleFactor * tank.getTurretPoints().get(0).getX(),
                canvas.getHeight() - scaleFactor * tank.getTurretPoints().get(0).getY(),
                scaleFactor * tank.getTurretPoints().get(1).getX(),
                canvas.getHeight() - scaleFactor * tank.getTurretPoints().get(1).getY());

        graphics.setStroke(outLineColor);
        graphics.setLineWidth(2.0);
        graphics.strokePolygon(
                points.stream().map(v -> scaleFactor * v.getX()).mapToDouble(Double::doubleValue).toArray(),
                points.stream().map(v -> (canvas.getHeight() - scaleFactor * v.getY())).mapToDouble(Double::doubleValue)
                        .toArray(),
                points.size());
        /* to draw outline */

        graphics.setFill(fillColor);
        graphics.fillPolygon(
                points.stream().map(v -> scaleFactor * v.getX()).mapToDouble(Double::doubleValue).toArray(),
                points.stream().map(v -> (canvas.getHeight() - scaleFactor * v.getY())).mapToDouble(Double::doubleValue)
                        .toArray(),
                points.size());

        graphics.setStroke(fillColor);
        graphics.setLineWidth(tank.getScaleFactor());
        graphics.strokeLine(scaleFactor * tank.getTurretPoints().get(0).getX(),
                canvas.getHeight() - scaleFactor * tank.getTurretPoints().get(0).getY(),
                scaleFactor * tank.getTurretPoints().get(1).getX(),
                canvas.getHeight() - scaleFactor * tank.getTurretPoints().get(1).getY());
    }

    @Override
    public void renderTankList(final List<Tank> tankList) {
        tankList.forEach(t -> renderTank(t));
    }

    @Override
    public void renderPolyline(final List<Vector2D> points) {
        graphics.setFill(Color.GREEN);
        points.forEach(po -> {
            graphics.fillOval(po.getX(), canvas.getHeight() - po.getY(), 2, 2);
        });
    }

    @Override
    public void renderTerrain(final Terrain terrain) {
        graphics.setFill(terrainColor);
        terrain.getOutlinesPoints().forEach(f -> {
            graphics.fillPolygon(f.stream().map(v -> scaleFactor * v.getX()).mapToDouble(Double::doubleValue).toArray(),
                    f.stream().map(v -> canvas.getHeight() - scaleFactor * v.getY()).mapToDouble(Double::doubleValue)
                            .toArray(),
                    f.size());
        });
    }

    @Override
    public void renderExplosion(final Explosion explosion) {
        final int seed = (int) Math.round(Math.random());
        if (!explosion.isExploded()) {
            graphics.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop[] { new Stop(0, seed == 0 ? Color.YELLOW : Color.BLUE),
                            new Stop(1, seed == 0 ? Color.DARKRED : Color.LIGHTCYAN) }));
            // g.setFill(Color.ORANGE);
            graphics.fillOval(scaleFactor * (explosion.getPosition().getX() - explosion.getCurrentBlastRadius()),
                    canvas.getHeight() - scaleFactor * explosion.getPosition().getY()
                            - scaleFactor * explosion.getCurrentBlastRadius(),
                    scaleFactor * 2.0 * explosion.getCurrentBlastRadius(),
                    scaleFactor * 2.0 * explosion.getCurrentBlastRadius());
        }
    }

    @Override
    public void renderExplosionList(final List<Explosion> explosionList) {
        explosionList.forEach(e -> renderExplosion(e));
    }

    @Override
    public void setTerrainWidth(final double terrainWidth) {
        scaleFactor = canvas.getWidth() / terrainWidth;
    }

}
