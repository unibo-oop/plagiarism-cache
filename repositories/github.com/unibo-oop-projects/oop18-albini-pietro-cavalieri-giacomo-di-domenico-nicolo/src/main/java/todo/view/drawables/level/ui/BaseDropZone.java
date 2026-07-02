package todo.view.drawables.level.ui;

import java.util.Objects;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import todo.utils.Disposable;
import todo.utils.UIConstants;

/**
 * Internal use only abstract class for creating some safe drop zones (i.e.
 * expanding the drop zone for the program or adding the "trash" zone, where any
 * dropped instruction is discarded).
 */
abstract class BaseDropZone implements Disposable, ActorHolder {
    private final DragAndDrop dragAndDrop;
    private final Target dropTarget;
    private final Container<Image> image;

    /**
     * Create a base drop zone with a transparent image and undefined width and
     * height.
     *
     * @param dragAndDrop is the drag and drop utility of the UI
     */
    BaseDropZone(final DragAndDrop dragAndDrop) {
        this(dragAndDrop, new Image(UIConstants.TRANSPARENT_PATCH));
    }

    /**
     * Create a base drag and drop zone with undefined width and height.
     *
     * @param dragAndDrop is the drag and drop utility of the UI
     * @param image is the image of the zone
     */
    BaseDropZone(final DragAndDrop dragAndDrop, final Image image) {
        this(dragAndDrop, image, 0f, 0f);
    }

    /**
     * Create a base drag and drop zone.
     *
     * @param dragAndDrop is the drag and drop utility of the UI
     * @param image is the image of the zone
     * @param width is the width of the zone
     * @param height is the height of the zone
     */
    BaseDropZone(final DragAndDrop dragAndDrop, final Image image, final float width, final float height) {
        this.dragAndDrop = Objects.requireNonNull(dragAndDrop);
        this.image = buildContainer(image, width, height);
        this.dropTarget = new Target(this.image) {
            @Override
            public void drop(final Source source, final Payload payload, final float x, final float y,
                    final int pointer) {
                BaseDropZone.this.onDrop(source, payload, x, y, pointer);
            }

            @Override
            public boolean drag(final Source source, final Payload payload, final float x, final float y,
                    final int pointer) {
                return BaseDropZone.this.onDrag(source, payload, x, y, pointer);
            }
        };
        this.dragAndDrop.addTarget(this.dropTarget);
    }

    @Override
    public void dispose() {
        this.dragAndDrop.removeTarget(this.dropTarget);
    }

    @Override
    public Actor getActor() {
        return this.image;
    }

    protected abstract void onDrop(final Source source, final Payload payload, final float x, final float y,
            final int pointer);

    protected abstract boolean onDrag(final Source source, final Payload payload, final float x, final float y,
            final int pointer);

    protected Container<Image> buildContainer(final Image image, final float width, final float height) {
        return new Container<>(image).fill();
    }
}
