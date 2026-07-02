package clashclass.view.graphic;

import clashclass.commons.ConversionUtility;
import clashclass.commons.GameConstants;
import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;
import clashclass.elements.buildings.VillageElementData;
import clashclass.elements.troops.TroopType;
import clashclass.view.graphic.components.AbstractGraphicComponent;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;
import clashclass.commons.Rect2D;

/**
 * Class that implements Graphic using JavaFX.
 */
public class GraphicJavaFXImpl implements Graphic {
    private final GraphicsContext gc;
    private Map<String, Image> spritesMap;
    private final double dpiW;
    private final double dpiH;
    private final Canvas canvas;

    /**
     * Constructs the graphics.
     *
     * @param gc the graphics context
     * @param canvas the canvas
     * @param dpiW the window width
     * @param dpiH the window height
     */
        public GraphicJavaFXImpl(
            final GraphicsContext gc,
            final Canvas canvas,
            final double dpiW,
            final double dpiH) {
        this.gc = gc;
        this.canvas = canvas;
        this.dpiW = dpiW;
        this.dpiH = dpiH;
        storeSprites();
    }

    private void storeSprites() {
        this.spritesMap = new HashMap<>();
        this.spritesMap.put("grass-tile", this.loadImage("grass-tile"));
        this.spritesMap.put(VillageElementData.ARCHER_TOWER.getName(),
                this.loadImage(VillageElementData.ARCHER_TOWER.getName()));
        this.spritesMap.put(VillageElementData.BARRACKS.getName(),
                this.loadImage(VillageElementData.BARRACKS.getName()));
        this.spritesMap.put(VillageElementData.ARMY_CAMP.getName(),
                this.loadImage(VillageElementData.ARMY_CAMP.getName()));
        this.spritesMap.put(VillageElementData.CANNON.getName(),
                this.loadImage(VillageElementData.CANNON.getName()));
        this.spritesMap.put(VillageElementData.ELIXIR_EXTRACTOR.getName(),
                this.loadImage(VillageElementData.ELIXIR_EXTRACTOR.getName()));
        this.spritesMap.put(VillageElementData.ELIXIR_STORAGE.getName(),
                this.loadImage(VillageElementData.ELIXIR_STORAGE.getName()));
        this.spritesMap.put(VillageElementData.GOLD_EXTRACTOR.getName(),
                this.loadImage(VillageElementData.GOLD_EXTRACTOR.getName()));
        this.spritesMap.put(VillageElementData.GOLD_STORAGE.getName(),
                this.loadImage(VillageElementData.GOLD_STORAGE.getName()));
        this.spritesMap.put(VillageElementData.TOWN_HALL.getName(),
                this.loadImage(VillageElementData.TOWN_HALL.getName()));
        this.spritesMap.put(VillageElementData.WALL.getName(),
                this.loadImage(VillageElementData.WALL.getName()));
        this.spritesMap.put(TroopType.BARBARIAN.getName(),
                this.loadImage(TroopType.BARBARIAN.getName()));
        this.spritesMap.put(TroopType.ARCHER.getName(),
                this.loadImage(TroopType.ARCHER.getName()));
    }

    private Image loadImage(final String path) {
        return new Image(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("sprites/" + path + ".png")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearRect() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    private void drawGameObjects(final Set<AbstractGraphicComponent> graphicComponents) {
        graphicComponents.stream()
                .sorted(Comparator
                        .comparingInt(this::getSortingLayer)
                        .thenComparingDouble(this::getSortingIsometricCoordinates)
                )
                .forEach(graphicComponent -> graphicComponent.draw(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Set<AbstractGraphicComponent> graphicComponents) {
        Platform.runLater(() -> {
            this.clearRect();
            this.drawGameObjects(graphicComponents);
        });
    }

    private int getSortingLayer(final AbstractGraphicComponent graphicComponent) {
        return graphicComponent.getLayer();
    }

    private double getSortingIsometricCoordinates(final AbstractGraphicComponent graphicComponent) {
        final var tileData = graphicComponent.getGameObject()
                .getComponentOfType(Transform2D.class).get();
        final var bottom = ConversionUtility.convertWorldToGridPosition(tileData.getPosition());
        return bottom.x() + bottom.y();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSprites(final GameObject go, final String spriteName) {
        go.getComponentOfType(AbstractGraphicComponent.class).ifPresent(component -> {
            final var position = go.getComponentOfType(Transform2D.class).get().getPosition();
            final var image = spritesMap.get(spriteName);

            final double scaleX = canvas.getWidth() / dpiW;
            final double scaleY = canvas.getHeight() / dpiH;
            this.gc.save();
            this.gc.scale(scaleX, scaleY);

            this.gc.drawImage(
                    image,
                    position.x() - image.getWidth() * GameConstants.TILE_SCALE / 2,
                    position.y() - image.getHeight() * GameConstants.TILE_SCALE,
                    image.getWidth() * GameConstants.TILE_SCALE,
                    image.getHeight() * GameConstants.TILE_SCALE);
            this.gc.restore();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final GameObject go, final String colorEx, final Rect2D rect) {
        go.getComponentOfType(AbstractGraphicComponent.class).ifPresent(graphicComponent -> {
            final double scaleX = canvas.getWidth() / dpiW;
            final double scaleY = canvas.getHeight() / dpiH;
            this.gc.save();
            this.gc.scale(scaleX, scaleY);

            final var color = Color.web(colorEx);
            this.gc.setFill(color);
            this.gc.fillRect(
                    rect.x(),
                    rect.y(),
                    rect.width(),
                    rect.height());
            this.gc.restore();
        });
    }
}
