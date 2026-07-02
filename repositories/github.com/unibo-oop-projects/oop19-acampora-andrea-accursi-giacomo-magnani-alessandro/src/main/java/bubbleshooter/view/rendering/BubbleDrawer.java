package bubbleshooter.view.rendering;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.view.images.ImagePath;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class used to draw {@link Bubble} images in the {@link View}.
 *
 */
public class BubbleDrawer {

    private final Canvas canvas;
    private final Map<BubbleColor, ImagePath> colorMap = Map.of(BubbleColor.BLUE, ImagePath.BLUE_BUBBLE,
            BubbleColor.GREEN, ImagePath.GREEN_BUBBLE, BubbleColor.PURPLE, ImagePath.PURPLE_BUBBLE, BubbleColor.RED,
            ImagePath.RED_BUBBLE, BubbleColor.LIGHT_BLUE, ImagePath.LIGHT_BLUE_BUBBLE, BubbleColor.YELLOW,
            ImagePath.YELLOW_BUBBLE);

    /**
     * 
     * @param canvas The canvas of the game scene.
     */
    public BubbleDrawer(final Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Draw all {@link Sprite}s.
     * 
     * @param bubbles The {@link Bubble}s of the game.
     */
    public final void draw(final List<Bubble> bubbles) {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.createSpriteList(bubbles).forEach(s -> {
            gc.save();
            s.draw();
            gc.restore();
        });
    }

    /**
     * Generates and sets the {@link BubbleSprite}.
     * 
     * @param bubble to generate the sprite.
     * @return new Sprite.
     */
    private Sprite generateBubbleSprite(final Bubble bubble) {
        final Sprite sprite = new BubbleSprite(this.canvas.getGraphicsContext2D());
        try {
            sprite.setSource(this.colorMap.get(bubble.getColor()));
            sprite.setPosition(bubble.getPosition());
            sprite.setHeight(bubble.getWidth());
            sprite.setWidth(bubble.getHeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sprite;
    }

    /**
     * @return canvas.
     */
    public final Canvas getCanvas() {
        return canvas;
    }

    /**
     * Creates {@link Sprite} list.
     * 
     * @param list of {@link Bubble}s.
     * @return list of {@link Sprite}s.
     */
    private List<Sprite> createSpriteList(final List<Bubble> bubbles) {
        return Collections
                .unmodifiableList(bubbles.stream().map(this::generateBubbleSprite).collect(Collectors.toList()));
    }

}
