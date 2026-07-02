package ballblast.view.rendering;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectType;
import ballblast.view.rendering.gameobject.RendererFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

/**
 * A canvas drawer.
 */
public class CanvasDrawer {
    private static final Map<GameObjectType, Function<Pair<Sprite, GameObject>, Renderer>> RENDERER_MAP;
    private final Canvas canvas;

    static {
        RENDERER_MAP = ImmutableMap.of(GameObjectType.BALL,
                p -> RendererFactory.createBallRenderer(p.getKey(), p.getValue()), GameObjectType.BULLET,
                p -> RendererFactory.createBulletRenderer(p.getKey(), p.getValue()), GameObjectType.PLAYER,
                p -> RendererFactory.createPlayerRenderer(p.getKey(), p.getValue()), GameObjectType.WALL,
                p -> RendererFactory.createWallRenderer(p.getKey(), p.getValue()), GameObjectType.POWERUP,
                p -> RendererFactory.createPowerUpRenderer(p.getKey(), p.getValue()));
    }

    /**
     * @param canvas The {@link Canvas}.
     */
    public CanvasDrawer(final Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @param gameObjects A list of {@link GameObject}s.
     */
    public void draw(final List<GameObject> gameObjects) {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.convertToRenderers(gameObjects).forEach(r -> {
            gc.save();
            r.render();
            gc.restore();
        });
    }

    private List<Renderer> convertToRenderers(final List<GameObject> gameObjects) {
        return gameObjects.stream().map(this::getRenderer).collect(ImmutableList.toImmutableList());
    }

    /**
     * @return the {@link Canvas} the file not found..
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    private Sprite generateSprite(final GameObject gameObject) {
        return new ImageSprite(this.canvas.getGraphicsContext2D(), gameObject);
    }

    private Renderer getRenderer(final GameObject gameObject) {
        return RENDERER_MAP.get(gameObject.getType()).apply(new Pair<>(this.generateSprite(gameObject), gameObject));
    }
}
