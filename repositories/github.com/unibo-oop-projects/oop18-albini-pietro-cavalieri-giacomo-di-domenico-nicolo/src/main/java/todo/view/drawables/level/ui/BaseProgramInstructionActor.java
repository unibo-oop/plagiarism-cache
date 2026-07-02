package todo.view.drawables.level.ui;

import java.util.Objects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import todo.controller.RoomController;
import todo.vm.instructions.Instruction;

/**
 * This abstract class models a base {@link ProgramInstructionActor} that can be
 * dragged and dropped.
 */
abstract class BaseProgramInstructionActor extends BaseInstructionActor implements ProgramInstructionActor {
    private final Instruction instruction;
    private final ProgramUI programUI;
    private final RoomController roomController;
    private final Source dragSource;
    private final Target dragTarget;

    BaseProgramInstructionActor(final Instruction instruction, final RoomController roomController,
            final ProgramUI programUI, final NinePatch image, final FileHandle fontFile, final String text,
            final float width) {
        super(instruction.getClass(), image, fontFile, text, width);
        this.instruction = Objects.requireNonNull(instruction);
        this.roomController = Objects.requireNonNull(roomController);
        this.programUI = Objects.requireNonNull(programUI);
        this.dragSource = new Source(getActor()) {
            @Override
            public Payload dragStart(final InputEvent event, final float x, final float y, final int pointer) {
                final Payload payload = new Payload();
                payload.setDragActor(BaseProgramInstructionActor.this.getActor());
                payload.setObject(new DragPayload(BaseProgramInstructionActor.this));
                // Disable the target for this actor while it's dragged around
                BaseProgramInstructionActor.this.programUI.getDragAndDrop()
                                                          .removeTarget(BaseProgramInstructionActor.this.dragTarget);
                // Set the drag position to the mouse X and Y relative to the source
                BaseProgramInstructionActor.this.programUI.getDragAndDrop().setDragActorPosition(width - x, -y);
                return payload;
            }

            @Override
            public void dragStop(final InputEvent event, final float x, final float y, final int pointer,
                    final Payload payload, final Target target) {
                final DragPayload dragPayload = (DragPayload) payload.getObject();
                // Reenable the target for this actor while it's dragged around
                BaseProgramInstructionActor.this.programUI.getDragAndDrop()
                                                          .addTarget(BaseProgramInstructionActor.this.dragTarget);
                // If the drop target is not valid, the parameter is set to null
                if (target == null && dragPayload.getProgramInstructionActor().isPresent()) {
                    // Delete the program actor if the target is not valid
                    roomController.remove(dragPayload.getInstruction());
                }
            }
        };
        this.dragTarget = new Target(getActor()) {
            @Override
            public boolean drag(final Source source, final Payload payload, final float x, final float y,
                    final int pointer) {
                return true;
            }

            @Override
            public void drop(final Source source, final Payload payload, final float x, final float y,
                    final int pointer) {
                final DragPayload dragPayload = (DragPayload) payload.getObject();
                final int targetPosition = roomController.getPosition(
                        BaseProgramInstructionActor.this.getInstruction());
                if (dragPayload.getProgramInstructionActor().isPresent()) {
                    // Move
                    final int sourcePosition = roomController.getPosition(dragPayload.getInstruction());
                    // If the target position is after the source position, we have to compensate
                    // the index to move the source actor in the right place
                    roomController.move(dragPayload.getInstruction(),
                            targetPosition > sourcePosition ? targetPosition : targetPosition + 1);
                } else {
                    // Add
                    roomController.add(dragPayload.getInstruction(), targetPosition + 1);
                }
            }
        };
        this.programUI.getDragAndDrop().addSource(this.dragSource);
        this.programUI.getDragAndDrop().addTarget(this.dragTarget);
    }

    @Override
    public Instruction getInstruction() {
        return this.instruction;
    }

    @Override
    public void dispose() {
        this.programUI.getDragAndDrop().removeSource(this.dragSource);
        this.programUI.getDragAndDrop().removeTarget(this.dragTarget);
        super.dispose();
    }

    protected final RoomController getRoomController() {
        return this.roomController;
    }

    protected final ProgramUI getProgramUI() {
        return this.programUI;
    }
}
