package todo.view.drawables.level.ui;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import todo.vm.instructions.Instruction;

/**
 * This interface represents the styling of the buttons.
 */
public interface InstructionActorStyling {
    /**
     * Get the texture of the button, given the instruction class.
     *
     * @param instructionClass is the instruction class
     * @return the desired texture
     */
    NinePatch patchFromClass(Class<? extends Instruction> instructionClass);

    /**
     * Get the pretty name of the button, given the instruction class.
     *
     * @param instructionClass is the instruction class
     * @return the desired name
     */
    String nameFromClass(Class<? extends Instruction> instructionClass);

    /**
     * Get the order of the instrucions to be displayed.
     *
     * @return the order of instructions
     */
    List<Class<? extends Instruction>> getInstructionActorOrder();
}
