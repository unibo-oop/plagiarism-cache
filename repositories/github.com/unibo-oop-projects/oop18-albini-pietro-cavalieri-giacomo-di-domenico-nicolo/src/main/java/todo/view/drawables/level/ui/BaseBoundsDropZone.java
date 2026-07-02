package todo.view.drawables.level.ui;

import java.util.Objects;
import java.util.function.Supplier;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;

import todo.controller.RoomController;
import todo.utils.UIConstants;

/**
 * This internal use only abstract class is used to create a drop zone before
 * and after the program, to make sure that the user can add an instruction as
 * first or as last more easily.
 */
abstract class BaseBoundsDropZone extends BaseDropZone {
    private final RoomController roomController;
    private final Supplier<Integer> addPositionGetter;
    private final Supplier<Integer> movePositionGetter;

    BaseBoundsDropZone(final RoomController roomController, final ProgramUI programUI, final float width,
            final float height, final Supplier<Integer> addPositionGetter, final Supplier<Integer> movePositionGetter) {
        this(roomController, programUI, new Image(UIConstants.TRANSPARENT_PATCH), width, height, addPositionGetter,
                movePositionGetter);
    }

    BaseBoundsDropZone(final RoomController roomController, final ProgramUI programUI, final Image image,
            final float width, final float height, final Supplier<Integer> addPositionGetter,
            final Supplier<Integer> movePositionGetter) {
        super(Objects.requireNonNull(programUI).getDragAndDrop(), image, width, height);
        this.roomController = Objects.requireNonNull(roomController);
        this.addPositionGetter = Objects.requireNonNull(addPositionGetter);
        this.movePositionGetter = Objects.requireNonNull(movePositionGetter);
    }

    @Override
    protected final void onDrop(final Source source, final Payload payload, final float x, final float y,
            final int pointer) {
        final DragPayload dragPayload = (DragPayload) payload.getObject();
        if (dragPayload.getProgramInstructionActor().isPresent()) {
            // Move to the desired position
            this.roomController.move(dragPayload.getInstruction(), this.movePositionGetter.get());
        } else {
            // Add to the desired position
            this.roomController.add(dragPayload.getInstruction(), this.addPositionGetter.get());
        }
    }

    @Override
    protected final boolean onDrag(final Source source, final Payload payload, final float x, final float y,
            final int pointer) {
        return true;
    }

    @Override
    protected Container<Image> buildContainer(final Image image, final float width, final float height) {
        return new Container<>(Objects.requireNonNull(image)).width(width).height(height);
    }
}
