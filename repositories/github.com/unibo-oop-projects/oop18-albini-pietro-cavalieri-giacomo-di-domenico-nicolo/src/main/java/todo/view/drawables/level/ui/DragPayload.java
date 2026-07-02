package todo.view.drawables.level.ui;

import java.util.Objects;
import java.util.Optional;

import todo.vm.instructions.Instruction;

/**
 * This internal use only class is used to send information to the drop target
 * of a button; it holds the instruction to be added/moved/deleted to the
 * program and its representation as a {@link ProgramInstructionActor} if
 * present.
 */
class DragPayload {
    private final Optional<ProgramInstructionActor> programInstructionActor;
    private final Instruction instruction;

    DragPayload(final ProgramInstructionActor programInstructionActor) {
        this.programInstructionActor = Optional.of(programInstructionActor);
        this.instruction = programInstructionActor.getInstruction();
    }

    DragPayload(final Instruction instruction) {
        this.instruction = Objects.requireNonNull(instruction);
        this.programInstructionActor = Optional.empty();
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    public Optional<ProgramInstructionActor> getProgramInstructionActor() {
        return this.programInstructionActor;
    }
}
