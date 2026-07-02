package todo.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import todo.controller.clipboard.ClipboardProvider;
import todo.model.program.Program;
import todo.model.program.ProgramImpl;
import todo.vm.instructions.Instruction;
import todo.vm.parser.ParserException;
import todo.vm.parser.ParserImpl;
import todo.vm.printer.PrintableInstruction;
import todo.vm.printer.Printer;
import todo.vm.printer.PrinterImpl;

/**
 * This is a class with static utility methods to load and save a program so
 * that the user will not lose his code upon closing the application.
 */
public final class SaveManager {
    private static final String SAVE_DIRECTORY = System.getProperty("user.home") + System.getProperty("file.separator")
            + ".todo";

    static {
        new File(SAVE_DIRECTORY).mkdirs();
    }

    private SaveManager() {
    }

    /**
     * This method saves the {@link Program} associated with the level passed as a
     * parameter, if the program contains instructions not allowed by the level it
     * simply will not be saved.
     *
     * @param programToSave the {@link Program} that has to be saved permanently
     * @param levelController the {@link LevelController} to which the
     *            {@link Program} is associated
     */
    public static void saveProgram(final Program programToSave, final LevelController levelController) {
        // If the program passed would not be allowed by the level it is not saved
        if (!areAllInstructionsAllowed(programToSave.getInstructions(), levelController)) {
            return;
        }

        final Printer printer = new PrinterImpl();
        programToSave.getInstructions().stream()
                                       .map(instr -> (PrintableInstruction) instr)
                                       .forEach(printer::add);
        try (Writer writer = Files.newBufferedWriter(
                Paths.get(SAVE_DIRECTORY + System.getProperty("file.separator") + levelController.getLevelTitle()))) {
            writer.write(printer.generate());
        } catch (final IOException e) {
            // If an exception occurs the level will not be saved
        }
    }

    /**
     * This method allows to load a program associated with the {@link Level} passed
     * as a parameter; if there is no {@link Program} saved the program returned by
     * this method will be empty (i.e. it will have no instructions). If, for any
     * reason, the program associated was modified without using the
     * {@link #saveProgram} method and made to contain instructions not allowed by
     * the level it will not be loaded.
     *
     * @param levelController the {@link LevelController} whose {@link Program} has
     *            to be retrieved (if any)
     * @param clipboard the {@link ClipboardProvider} that will be used by the
     *            loaded {@link Level}
     * @return the {@link Program} associated with the level, it will be empty if
     *         there is none saved
     */
    public static Program loadProgram(final LevelController levelController, final ClipboardProvider clipboard) {
        final List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(new File(
                    SAVE_DIRECTORY + System.getProperty("file.separator") + levelController.getLevelTitle()).toPath()));
        } catch (final IOException e) {
            // If an exception occurs it return an empty Program
            new ProgramImpl(levelController.getLevelAllowedInstructions(), clipboard,
                    levelController.getEventManager());
        }
        try {
            final List<Instruction> instructions = new ParserImpl(levelController.getLevelAllowedInstructions()).parse(
                    lines.stream().collect(Collectors.joining("\n")));
            return new ProgramImpl(instructions, levelController.getLevelAllowedInstructions(), clipboard,
                    levelController.getEventManager());
        } catch (final ParserException e) {
            // If loading the saved program fails silently ignore the failure and just show
            // the error message on the console, since it means the user tampered with the
            // save file. The game will start correctly with an empty program.
            System.out.println("Failed to load saved program: " + e.getMessage());
            return new ProgramImpl(levelController.getLevelAllowedInstructions(), clipboard,
                    levelController.getEventManager());
        }
    }

    private static boolean areAllInstructionsAllowed(final List<Instruction> instructions,
            final LevelController levelController) {
        return !instructions.stream()
                            .anyMatch(
                                    instr -> !levelController.getLevelAllowedInstructions().contains(instr.getClass()));
    }
}
