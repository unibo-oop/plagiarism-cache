package todo.view.drawables.level.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import todo.utils.Checks;
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
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;

public class DraggableTemplateInstructionActorFactory implements InstructionActorFactory {
    private static final Map<Class<? extends Instruction>, Supplier<Instruction>> INSTRUCTION_SUPPLIERS;
    private final InstructionActorStyling styling;
    private final ProgramUI programUI;
    private final float width;

    static {
        INSTRUCTION_SUPPLIERS = new HashMap<>();
        INSTRUCTION_SUPPLIERS.put(Input.class, () -> new Input());
        INSTRUCTION_SUPPLIERS.put(Output.class, () -> new Output());
        INSTRUCTION_SUPPLIERS.put(CopyFrom.class, () -> new CopyFrom(0));
        INSTRUCTION_SUPPLIERS.put(CopyTo.class, () -> new CopyTo(0));
        INSTRUCTION_SUPPLIERS.put(Add.class, () -> new Add(0));
        INSTRUCTION_SUPPLIERS.put(Sub.class, () -> new Sub(0));
        INSTRUCTION_SUPPLIERS.put(Incr.class, () -> new Incr(0));
        INSTRUCTION_SUPPLIERS.put(Decr.class, () -> new Decr(0));
        INSTRUCTION_SUPPLIERS.put(Jump.class, () -> new Jump());
        INSTRUCTION_SUPPLIERS.put(JumpZero.class, () -> new JumpZero());
        INSTRUCTION_SUPPLIERS.put(JumpNegative.class, () -> new JumpNegative());
    }

    public DraggableTemplateInstructionActorFactory(final InstructionActorStyling styling, final ProgramUI programUI,
            final float width) {
        this.styling = Objects.requireNonNull(styling);
        this.programUI = Objects.requireNonNull(programUI);
        this.width = width;
    }

    @Override
    public InstructionActor fromInstructionClass(final Class<? extends Instruction> instructionClass) {
        Checks.require(INSTRUCTION_SUPPLIERS.containsKey(instructionClass), NoSuchElementException.class,
                "Unsupported instruction class");
        return new DraggableInstructionActor(createTemplateInstructionActor(instructionClass), this.programUI,
                INSTRUCTION_SUPPLIERS.get(instructionClass));
    }

    @Override
    public List<InstructionActor> getAll() {
        return this.styling.getInstructionActorOrder()
                           .stream()
                           .map(i -> fromInstructionClass(i))
                           .collect(Collectors.toList());
    }

    private InstructionActor createTemplateInstructionActor(final Class<? extends Instruction> instructionClass) {
        return new TemplateInstructionActor(instructionClass, this.styling.patchFromClass(instructionClass),
                UIConstants.ARIAL_FILE, this.styling.nameFromClass(instructionClass), this.width);
    }
}
