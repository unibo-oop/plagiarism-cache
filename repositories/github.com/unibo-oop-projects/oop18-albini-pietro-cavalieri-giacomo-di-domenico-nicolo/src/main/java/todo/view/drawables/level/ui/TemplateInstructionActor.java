package todo.view.drawables.level.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import todo.vm.instructions.Instruction;

/**
 * This class represents a template instruction actor, which has no particular
 * behavior and has no program context.
 */
class TemplateInstructionActor extends BaseInstructionActor {
    TemplateInstructionActor(final Class<? extends Instruction> instructionClass, final NinePatch image,
            final FileHandle fontFile, final String text, final float width) {
        super(instructionClass, image, fontFile, text, width);
    }

    @Override
    public InstructionActor copy() {
        return new TemplateInstructionActor(getInstructionClass(), super.getImage(), super.getFontFile(),
                super.getText(), super.getWidth());
    }
}
