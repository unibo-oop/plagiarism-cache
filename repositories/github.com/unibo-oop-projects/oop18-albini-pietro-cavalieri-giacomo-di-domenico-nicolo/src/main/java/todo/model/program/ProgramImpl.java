package todo.model.program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import todo.controller.clipboard.ClipboardProvider;
import todo.controller.events.EventManager;
import todo.controller.events.ParsingErrorEvent;
import todo.utils.Checks;
import todo.vm.instructions.Instruction;
import todo.vm.parser.ParserException;
import todo.vm.parser.ParserImpl;
import todo.vm.printer.PrintableInstruction;
import todo.vm.printer.Printer;
import todo.vm.printer.PrinterImpl;

/**
 * This class represents the program being built by the user, that is a sequence
 * of {@link Instruction}s that can be modified by adding, moving, replacing or
 * removing one.
 */
public class ProgramImpl implements Program {
    private static final int MAX_HISTORY_LAYERS = 20;

    private final Set<Class<? extends Instruction>> allowedInstructions;
    private final List<ProgramCommand> commandsHistory;
    private final List<Instruction> instructions;
    private final ClipboardProvider clipboard;
    private final EventManager eventManager;
    private int historyPosition;

    public ProgramImpl(final Set<Class<? extends Instruction>> allowedInstructions, final ClipboardProvider clipboard,
            final EventManager eventManager) {
        this.allowedInstructions = new LinkedHashSet<>(Objects.requireNonNull(allowedInstructions));
        this.commandsHistory = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.clipboard = Objects.requireNonNull(clipboard);
        this.eventManager = Objects.requireNonNull(eventManager);
        this.historyPosition = 0;
    }

    public ProgramImpl(final List<Instruction> instructions,
            final Set<Class<? extends Instruction>> allowedInstructions, final ClipboardProvider clipboard,
            final EventManager eventManager) {
        this(allowedInstructions, clipboard, eventManager);
        if (areAllInstructionsAllowed(instructions)) {
            this.instructions.addAll(instructions);
        }
    }

    @Override
    public List<Instruction> getInstructions() {
        return Collections.unmodifiableList(this.instructions);
    }

    @Override
    public int getInstructionIndex(final Instruction instruction) {
        final int index = this.instructions.stream()
                                           .map(Instruction::getId)
                                           .collect(Collectors.toList())
                                           .indexOf(instruction.getId());
        Checks.require(index != -1, NoSuchElementException.class);
        return index;
    }

    @Override
    public void move(final int from, final int to) {
        // A useless action will not be recorded in the history of the program
        if (from != to) {
            addNewCommand(new MoveCommand(this.instructions, from, to));
        }
    }

    @Override
    public void replace(final int index, final Instruction instruction) {
        if (this.allowedInstructions.contains(Objects.requireNonNull(instruction).getClass())) {
            addNewCommand(new ReplaceCommand(this.instructions, index, instruction));
        }
    }

    @Override
    public void add(final int to, final Instruction instruction) {
        if (this.allowedInstructions.contains(Objects.requireNonNull(instruction).getClass())) {
            addNewCommand(new AddCommand(this.instructions, to, instruction));
        }
    }

    @Override
    public void remove(final int from) {
        addNewCommand(new RemoveCommand(this.instructions, from));
    }

    @Override
    public void clear() {
        if (!this.instructions.isEmpty()) {
            addNewCommand(new ClearCommand(this.instructions));
        }
    }

    @Override
    public boolean canUndo() {
        return this.historyPosition > 0;
    }

    @Override
    public void undo() {
        Checks.require(canUndo(), IllegalStateException.class);
        this.historyPosition--;
        this.commandsHistory.get(this.historyPosition).unexecute();
    }

    @Override
    public boolean canRedo() {
        return this.historyPosition < this.commandsHistory.size();
    }

    @Override
    public void redo() {
        Checks.require(canRedo(), IllegalStateException.class);
        this.commandsHistory.get(this.historyPosition).execute();
        this.historyPosition++;
    }

    @Override
    public void copy() {
        if (!this.instructions.isEmpty()) {
            final Printer printer = new PrinterImpl();
            this.instructions.stream()
                             .map(instr -> (PrintableInstruction) instr)
                             .forEach(printer::add);
            this.clipboard.set(printer.generate());
        }
    }

    @Override
    public void paste() {
        final List<Instruction> toPaste = new ArrayList<>();
        final Optional<String> clipboardContent = this.clipboard.get();
        if (clipboardContent.isPresent()) {
            try {
                toPaste.addAll(new ParserImpl(this.allowedInstructions).parse(clipboardContent.get()));
            } catch (final ParserException e) {
                this.eventManager.dispatch(new ParsingErrorEvent(e));
            }
            if (!toPaste.isEmpty() && !this.instructions.equals(toPaste)) {
                addNewCommand(new ReplaceAllCommand(this.instructions, toPaste));
            }
        }
    }

    private void addNewCommand(final ProgramCommand command) {
        if (canRedo()) {
            this.commandsHistory.subList(this.historyPosition, this.commandsHistory.size()).clear();
        }
        if (this.commandsHistory.size() == MAX_HISTORY_LAYERS) {
            this.commandsHistory.remove(0);
            this.historyPosition--;
        }
        command.execute();
        this.commandsHistory.add(command);
        this.historyPosition++;
    }

    private boolean areAllInstructionsAllowed(final List<Instruction> instructions) {
        return instructions.stream().allMatch(instr -> this.allowedInstructions.contains(instr.getClass()));
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ProgramImpl && this.instructions.equals(((ProgramImpl) obj).getInstructions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.instructions);
    }
}
