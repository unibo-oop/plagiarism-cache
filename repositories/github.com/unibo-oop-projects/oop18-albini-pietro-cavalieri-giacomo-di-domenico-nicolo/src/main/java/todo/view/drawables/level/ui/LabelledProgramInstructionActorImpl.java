package todo.view.drawables.level.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import todo.controller.RoomController;
import todo.utils.UIConstants;
import todo.vm.instructions.Instruction;

class LabelledProgramInstructionActorImpl extends BaseProgramInstructionActor
        implements LabelledProgramInstructionActor {
    private static final String LABEL_FORMAT = "[%d]";
    private static final String INSTRUCTION_WITH_LABEL_FORMAT = "%s " + LABEL_FORMAT;
    private final int label;

    LabelledProgramInstructionActorImpl(final Instruction instruction, final RoomController roomController,
            final ProgramUI programUI, final NinePatch image, final String text, final float width, final int label) {
        super(instruction, roomController, programUI, image, UIConstants.ARIAL_FILE,
                String.format(INSTRUCTION_WITH_LABEL_FORMAT, text, label), width);
        this.label = label;
    }

    @Override
    public InstructionActor copy() {
        return new LabelledProgramInstructionActorImpl(super.getInstruction(), super.getRoomController(),
                super.getProgramUI(), super.getImage(), super.getText(), super.getWidth(), this.label);
    }

    @Override
    public int getLabel() {
        return this.label;
    }
}
