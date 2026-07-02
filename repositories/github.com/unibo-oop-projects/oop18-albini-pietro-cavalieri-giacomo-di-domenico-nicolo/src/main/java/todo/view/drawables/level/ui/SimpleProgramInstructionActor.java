package todo.view.drawables.level.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import todo.controller.RoomController;
import todo.utils.UIConstants;
import todo.vm.instructions.Instruction;

/**
 * This class represents a simple program instruction actor, with no particular
 * behavior.
 */
class SimpleProgramInstructionActor extends BaseProgramInstructionActor {
    private final Instruction instruction;

    SimpleProgramInstructionActor(final Instruction instruction, final RoomController roomController,
            final ProgramUI programUI, final NinePatch image, final String text, final float width) {
        super(instruction, roomController, programUI, image, UIConstants.ARIAL_FILE, text, width);
        this.instruction = instruction;
    }

    @Override
    public InstructionActor copy() {
        return new SimpleProgramInstructionActor(this.instruction, super.getRoomController(), super.getProgramUI(),
                super.getImage(), super.getText(), super.getWidth());
    }
}
