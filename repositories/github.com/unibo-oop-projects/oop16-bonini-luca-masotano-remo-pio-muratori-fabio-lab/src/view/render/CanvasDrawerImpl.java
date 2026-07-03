package view.render;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import controller.ModelWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import util.ImageLoaderProxy;
import util.ImageLoaderProxy.ImageID;
import util.Pair;
import view.util.ViewStaticUtilities;

/**
 * Class implementing CanvasDrawer.
 */
public final class CanvasDrawerImpl implements CanvasDrawer {
    private static final int CANVAS_STD_WIDTH = ViewStaticUtilities.getStandardResolution().getX();
    private static final int CANVAS_STD_HEIGHT = ViewStaticUtilities.getStandardResolution().getY();
    private static final int LIFE_WIDTH = 40;
    private static final double MAP_ROOM_WIDTH = 20;

    private final GraphicsContext gcRoom;
    private final GraphicsContext gcMap;

    private static double modelWidth;
    private static double modelHeight;
    private Map<Pair<Integer, Integer>, ImageID> world;
    private Collection<ModelWrapper> staticCollection;
    private Collection<ModelWrapper> dynamicCollection;
    private double playerLife;

    /**
     * Constructor of the class CanvasDrawerImpl.
     * 
     * @param roomCanvas
     *            the main canvas used to render objects
     * @param mapCanvas
     *            the canvas used to render the map
     */
    public CanvasDrawerImpl(final Canvas roomCanvas, final Canvas mapCanvas) {
        this.gcRoom = roomCanvas.getGraphicsContext2D();
        this.gcMap = mapCanvas.getGraphicsContext2D();
    }

    @Override
    public void draw() {
        gcRoom.drawImage(ImageLoaderProxy.get().getImage(ImageID.FLOOR), 0, 0, gcRoom.getCanvas().getWidth(),
                gcRoom.getCanvas().getHeight());

        gcMap.drawImage(ImageLoaderProxy.get().getImage(ImageID.MAP_BACKGROUND), 0, 0, gcMap.getCanvas().getWidth(),
                gcMap.getCanvas().getHeight());

        this.drawDynamicRoomContents();
        this.drawStaticRoomContents();
        this.drawPlayerLife();
        this.drawMap();
    }

    @Override
    public void setStaticCollection(final List<ModelWrapper> collection) {
        this.staticCollection = collection;
    }

    @Override
    public void setDynamicCollection(final List<ModelWrapper> collection) {
        this.dynamicCollection = collection;
    }

    @Override
    public void setMap(final Map<Pair<Integer, Integer>, ImageID> world) {
        this.world = world;
    }

    @Override
    public void setPlayerLife(final double life) {
        this.playerLife = life;
    }

    private void drawStaticRoomContents() {
        staticCollection.stream().filter(e -> !Objects.isNull(e)).forEach(elem -> {
            gcRoom.drawImage(ImageLoaderProxy.get().getImage(elem.getImg()), this.convertModelX(elem.getX()),
                    this.convertModelY(elem.getY()), this.convertModelX(elem.getWidth()),
                    this.convertModelY(elem.getHeight()));
        });
    }

    private void drawDynamicRoomContents() {
        dynamicCollection.stream().filter(e -> !Objects.isNull(e)).forEach(elem -> {
            double layoutX;
            double layoutY;
            if (elem.isCircle()) {
                layoutX = elem.getX() - elem.getWidth() / 2;
                layoutY = elem.getY() - elem.getHeight() / 2;
            } else {
                layoutX = elem.getX();
                layoutY = elem.getY();
            }
            gcRoom.drawImage(ImageLoaderProxy.get().getImage(elem.getImg()), this.convertModelX(layoutX),
                    this.convertModelY(layoutY), this.convertModelX(elem.getWidth()),
                    this.convertModelY(elem.getHeight()));
        });
    }

    private void drawPlayerLife() {
        double i = 0;
        for (i = 0; i < (int) (playerLife / 2); i++) {
            gcRoom.drawImage(ImageLoaderProxy.get().getImage(ImageID.FULL_HEART), 10 + (LIFE_WIDTH / 2 + 10) * i, 10,
                    LIFE_WIDTH / 2, LIFE_WIDTH / 2);
        }
        if (playerLife % 2 != 0) {
            gcRoom.drawImage(ImageLoaderProxy.get().getImage(ImageID.HALF_HEART), 10 + (LIFE_WIDTH / 2 + 10) * i, 10,
                    LIFE_WIDTH / 2, LIFE_WIDTH / 2);
        }
    }

    private void drawMap() {
        for (final Entry<Pair<Integer, Integer>, ImageID> elem : world.entrySet()) {
            final double xPos = elem.getKey().getX() * MAP_ROOM_WIDTH + gcMap.getCanvas().getWidth() / 2 - MAP_ROOM_WIDTH / 2;
            final double yPos = elem.getKey().getY() * MAP_ROOM_WIDTH + gcMap.getCanvas().getHeight() / 2
                    - MAP_ROOM_WIDTH / 2;
            gcMap.drawImage(ImageLoaderProxy.get().getImage(elem.getValue()), xPos, yPos, MAP_ROOM_WIDTH,
                    MAP_ROOM_WIDTH);
        }
    }

    private double convertModelX(final double value) {
        return (CANVAS_STD_WIDTH / modelWidth) * value;
    }

    private double convertModelY(final double value) {
        return (CANVAS_STD_HEIGHT / modelHeight) * value;
    }

    /**
     * Provides the model room dimensions to scale objects properly.
     * 
     * @param width
     *            the width of a model room
     * @param height
     *            the height of a model room
     */
    public static void setModelSizes(final double width, final double height) {
        modelWidth = width;
        modelHeight = height;
    }
}
