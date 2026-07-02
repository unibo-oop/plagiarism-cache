package todo.view.drawables.level.ui;

/**
 * This interface represents a {@link ProgramInstructionActor} with a label.
 */
public interface LabelledProgramInstructionActor extends ProgramInstructionActor {
    /**
     * @return the label of the jump
     */
    int getLabel();
}
