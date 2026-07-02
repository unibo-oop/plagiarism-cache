package todo.view.drawables.level.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import todo.utils.UIConstants;
import todo.vm.instructions.Add;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Incr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpNegative;
import todo.vm.instructions.JumpTargetInstruction;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;

/**
 * This class sets the default styling for the buttons.
 */
public class InstructionActorStylingImpl implements InstructionActorStyling {
    private static final Map<Class<? extends Instruction>, NinePatch> CLASS_TO_PATCH;
    private static final Map<Class<? extends Instruction>, String> CLASS_TO_NAME;
    private static final List<Class<? extends Instruction>> CLASS_ORDER;

    static {
        CLASS_TO_PATCH = new HashMap<>();
        CLASS_TO_NAME = new HashMap<>();
        CLASS_ORDER = new ArrayList<>();
        initClassToPatchMap();
        initClassToNameMap();
        initClassOrder();
    }

    @Override
    public NinePatch patchFromClass(final Class<? extends Instruction> instructionClass) {
        if (CLASS_TO_PATCH.containsKey(Objects.requireNonNull(instructionClass))) {
            return CLASS_TO_PATCH.get(instructionClass);
        } else if (JumpTargetInstruction.class.isAssignableFrom(instructionClass)) {
            // Special case for jump targets
            return UIConstants.BLUE_BUTTON_PATCH;
        } else {
            throw new NoSuchElementException("The class has no nine patch associated to it");
        }
    }

    @Override
    public String nameFromClass(final Class<? extends Instruction> instructionClass) {
        if (CLASS_TO_NAME.containsKey(Objects.requireNonNull(instructionClass))) {
            return CLASS_TO_NAME.get(instructionClass);
        } else if (JumpTargetInstruction.class.isAssignableFrom(instructionClass)) {
            // Special case for jump targets
            return "";
        } else {
            throw new NoSuchElementException("The class has no nine patch associated to it");
        }
    }

    @Override
    public List<Class<? extends Instruction>> getInstructionActorOrder() {
        return Collections.unmodifiableList(CLASS_ORDER);
    }

    private static void initClassToPatchMap() {
        CLASS_TO_PATCH.put(Input.class, UIConstants.GREEN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Output.class, UIConstants.GREEN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(CopyFrom.class, UIConstants.RED_BUTTON_PATCH);
        CLASS_TO_PATCH.put(CopyTo.class, UIConstants.RED_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Add.class, UIConstants.BROWN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Sub.class, UIConstants.BROWN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Incr.class, UIConstants.BROWN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Decr.class, UIConstants.BROWN_BUTTON_PATCH);
        CLASS_TO_PATCH.put(Jump.class, UIConstants.BLUE_BUTTON_PATCH);
        CLASS_TO_PATCH.put(JumpZero.class, UIConstants.BLUE_BUTTON_PATCH);
        CLASS_TO_PATCH.put(JumpNegative.class, UIConstants.BLUE_BUTTON_PATCH);
    }

    private static void initClassToNameMap() {
        CLASS_TO_NAME.put(Input.class, "Input");
        CLASS_TO_NAME.put(Output.class, "Output");
        CLASS_TO_NAME.put(CopyFrom.class, "CopyFrom");
        CLASS_TO_NAME.put(CopyTo.class, "CopyTo");
        CLASS_TO_NAME.put(Add.class, "Add");
        CLASS_TO_NAME.put(Sub.class, "Sub");
        CLASS_TO_NAME.put(Incr.class, "Incr");
        CLASS_TO_NAME.put(Decr.class, "Decr");
        CLASS_TO_NAME.put(Jump.class, "Jump");
        CLASS_TO_NAME.put(JumpZero.class, "Jump if zero");
        CLASS_TO_NAME.put(JumpNegative.class, "Jump if neg");
    }

    private static void initClassOrder() {
        CLASS_ORDER.add(Input.class);
        CLASS_ORDER.add(Output.class);
        CLASS_ORDER.add(CopyFrom.class);
        CLASS_ORDER.add(CopyTo.class);
        CLASS_ORDER.add(Add.class);
        CLASS_ORDER.add(Sub.class);
        CLASS_ORDER.add(Incr.class);
        CLASS_ORDER.add(Decr.class);
        CLASS_ORDER.add(Jump.class);
        CLASS_ORDER.add(JumpZero.class);
        CLASS_ORDER.add(JumpNegative.class);
    }
}
