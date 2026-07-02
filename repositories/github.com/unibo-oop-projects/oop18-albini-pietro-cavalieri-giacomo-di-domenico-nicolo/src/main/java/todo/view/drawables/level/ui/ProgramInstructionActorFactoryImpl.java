package todo.view.drawables.level.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import todo.controller.RoomController;
import todo.utils.UniqueId;
import todo.vm.instructions.Add;
import todo.vm.instructions.AddressableInstruction;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Incr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpTargetInstruction;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;

public class ProgramInstructionActorFactoryImpl implements ProgramInstructionActorFactory {
    private static final Map<Class<? extends Instruction>, Function<Integer, AddressableInstruction>> ADDRESSABLE_SUPPLIERS;
    private static final Set<Class<? extends Instruction>> SIMPLE_INSTRUCTIONS;
    private final InstructionActorStyling styling;
    private final RoomController roomController;
    private final ProgramUI programUI;
    private final float width;
    private final List<Integer> addressSpace;
    private final Supplier<Integer> labelsSupplier;
    private Map<UniqueId, Integer> instructionsToLabels;

    static {
        ADDRESSABLE_SUPPLIERS = new HashMap<>();
        ADDRESSABLE_SUPPLIERS.put(CopyFrom.class, i -> new CopyFrom(i));
        ADDRESSABLE_SUPPLIERS.put(CopyTo.class, i -> new CopyTo(i));
        ADDRESSABLE_SUPPLIERS.put(Add.class, i -> new Add(i));
        ADDRESSABLE_SUPPLIERS.put(Sub.class, i -> new Sub(i));
        ADDRESSABLE_SUPPLIERS.put(Incr.class, i -> new Incr(i));
        ADDRESSABLE_SUPPLIERS.put(Decr.class, i -> new Decr(i));
        SIMPLE_INSTRUCTIONS = new HashSet<>();
        SIMPLE_INSTRUCTIONS.add(Input.class);
        SIMPLE_INSTRUCTIONS.add(Output.class);
    }

    public ProgramInstructionActorFactoryImpl(final InstructionActorStyling styling,
            final RoomController roomController, final ProgramUI programUI, final float width,
            final List<Integer> addressSpace, final Supplier<Integer> labelsSupplier) {
        this.styling = Objects.requireNonNull(styling);
        this.roomController = Objects.requireNonNull(roomController);
        this.programUI = Objects.requireNonNull(programUI);
        this.addressSpace = Objects.requireNonNull(addressSpace);
        this.labelsSupplier = Objects.requireNonNull(labelsSupplier);
        this.instructionsToLabels = new HashMap<>();
        this.width = width;
    }

    @Override
    public ProgramInstructionActor fromInstruction(final Instruction instruction) {
        Objects.requireNonNull(instruction);
        // Create the actor
        ProgramInstructionActor result;
        if (SIMPLE_INSTRUCTIONS.contains(instruction.getClass())) {
            result = simple(instruction);
        } else if (instruction instanceof JumpInstruction) {
            result = jump((JumpInstruction) instruction);
        } else if (ADDRESSABLE_SUPPLIERS.containsKey(instruction.getClass())
                && instruction instanceof AddressableInstruction) {
            result = addressable((AddressableInstruction) instruction,
                    ADDRESSABLE_SUPPLIERS.get(instruction.getClass()));
        } else if (instruction instanceof JumpTargetInstruction) {
            result = jumpTarget((JumpTargetInstruction) instruction);
        } else {
            throw new NoSuchElementException("The instruction isn't supported by the factory");
        }
        return result;
    }

    @Override
    public List<Integer> getUsedLabels() {
        return this.instructionsToLabels.values().stream()
                                                 .distinct()
                                                 .collect(Collectors.toList());
    }

    @Override
    public void refreshUsedLabels(final List<Instruction> instructions) {
        this.instructionsToLabels = instructions.stream()
                                                .map(i -> i.getId())
                                                .filter(i -> this.instructionsToLabels.containsKey(i))
                                                .collect(Collectors.toMap(i -> i,
                                                        i -> this.instructionsToLabels.get(i)));
    }

    private ProgramInstructionActor simple(final Instruction instruction) {
        return new SimpleProgramInstructionActor(instruction, this.roomController, this.programUI,
                this.styling.patchFromClass(instruction.getClass()), this.styling.nameFromClass(instruction.getClass()),
                this.width);
    }

    private ProgramInstructionActor addressable(final AddressableInstruction instruction,
            final Function<Integer, AddressableInstruction> instructionFunction) {
        return new AddressableProgramInstructionActorImpl(instruction, instructionFunction, this.roomController,
                this.programUI, this.styling.patchFromClass(instruction.getClass()),
                this.styling.nameFromClass(instruction.getClass()), this.width, this.addressSpace);
    }

    private ProgramInstructionActor jump(final JumpInstruction instruction) {
        // Generate a label if not present for the jump
        if (!this.instructionsToLabels.containsKey(instruction.getId())) {
            this.instructionsToLabels.put(instruction.getId(), this.labelsSupplier.get());
        }
        final int label = this.instructionsToLabels.get(instruction.getId());
        return new LabelledProgramInstructionActorImpl(instruction, this.roomController, this.programUI,
                this.styling.patchFromClass(instruction.getClass()), this.styling.nameFromClass(instruction.getClass()),
                this.width, label);
    }

    private ProgramInstructionActor jumpTarget(final JumpTargetInstruction instruction) {
        // Generate a label if not present for the jump associated to the target
        if (!this.instructionsToLabels.containsKey(instruction.getSourceId())) {
            this.instructionsToLabels.put(instruction.getSourceId(), this.labelsSupplier.get());
        }
        final int label = this.instructionsToLabels.get(instruction.getSourceId());
        return new LabelledProgramInstructionActorImpl(instruction, this.roomController, this.programUI,
                this.styling.patchFromClass(instruction.getClass()), this.styling.nameFromClass(instruction.getClass()),
                this.width, label);
    }
}
