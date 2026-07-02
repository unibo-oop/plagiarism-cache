package todo.view.drawables.level.ui;

import java.util.Objects;
import java.util.function.Supplier;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;

import todo.vm.instructions.Instruction;

/**
 * This class is a decorator for a generic instruction actor that is only a drag
 * source (not a target). Useful for managing the template instruction actors.
 */
class DraggableInstructionActor implements InstructionActor {
    private final InstructionActor instructionActor;
    private final ProgramUI programUI;
    private final Supplier<Instruction> instructionSupplier;
    private final Source dragSource;

    public DraggableInstructionActor(final InstructionActor instructionActor, final ProgramUI programUI,
            final Supplier<Instruction> instructionSupplier) {
        this.instructionActor = Objects.requireNonNull(instructionActor);
        this.programUI = Objects.requireNonNull(programUI);
        this.instructionSupplier = Objects.requireNonNull(instructionSupplier);
        this.dragSource = new Source(this.instructionActor.getActor()) {
            @Override
            public Payload dragStart(final InputEvent event, final float x, final float y, final int pointer) {
                final Payload payload = new Payload();
                payload.setDragActor(DraggableInstructionActor.this.instructionActor.copy().getActor());
                payload.setObject(new DragPayload(instructionSupplier.get()));
                programUI.getDragAndDrop().setDragActorPosition(0f, 0f);
                return payload;
            }
        };
        this.programUI.getDragAndDrop().addSource(this.dragSource);
    }

    @Override
    public void dispose() {
        this.programUI.getDragAndDrop().removeSource(this.dragSource);
        this.instructionActor.dispose();
    }

    @Override
    public InstructionActor copy() {
        return new DraggableInstructionActor(this.instructionActor, this.programUI, this.instructionSupplier);
    }

    @Override
    public Actor getActor() {
        return this.instructionActor.getActor();
    }

    @Override
    public Class<? extends Instruction> getInstructionClass() {
        return this.instructionActor.getInstructionClass();
    }
}
