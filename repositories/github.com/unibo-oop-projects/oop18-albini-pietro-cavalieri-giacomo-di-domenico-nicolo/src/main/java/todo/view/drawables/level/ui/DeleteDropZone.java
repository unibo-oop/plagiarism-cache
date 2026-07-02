package todo.view.drawables.level.ui;

import java.util.Objects;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;

import todo.controller.RoomController;

/**
 * This internal use only class represents the delete drop zone, that goes on
 * top of the game itself, allowing instruction dropped here to be deleted.
 */
class DeleteDropZone extends BaseDropZone {
    private final RoomController roomController;

    /**
     * Create a drop zone that deletes everything that's dropped.
     *
     * @param roomController is the room controller
     */
    DeleteDropZone(final RoomController roomController, final ProgramUI programUI) {
        super(programUI.getDragAndDrop());
        this.roomController = Objects.requireNonNull(roomController);
    }

    @Override
    protected void onDrop(final Source source, final Payload payload, final float x, final float y, final int pointer) {
        // Check the payload again, then delete the program actor
        final DragPayload dragPayload = (DragPayload) payload.getObject();
        if (dragPayload.getProgramInstructionActor().isPresent()) {
            this.roomController.remove(dragPayload.getInstruction());
        }
    }

    @Override
    protected boolean onDrag(final Source source, final Payload payload, final float x, final float y,
            final int pointer) {
        // Make sure we're deleting only a program instruction actor by checking its
        // payload
        return ((DragPayload) payload.getObject()).getProgramInstructionActor().isPresent();
    }
}
