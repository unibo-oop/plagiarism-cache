package todo.model.program;

import java.util.List;
import java.util.NoSuchElementException;

import todo.vm.instructions.Instruction;

/**
 * This interface represents Program (i.e. a list of instructions) that provides
 * the user the possibility to undo/redo the changes applied to it through its
 * methods.
 */
public interface Program {
    /**
     * @return the list of instructions currently stored by the program
     */
    List<Instruction> getInstructions();

    /**
     * @param instruction the {@link Instruction} whose index the user want to know
     * @return the instruction index in the program
     * @throws NoSuchElementException if the instruction passed is not in the
     *             Program
     */
    int getInstructionIndex(Instruction instruction);

    /**
     * @param from the index of the instruction to be moved
     * @param to the index where the instruction will be placed. Shifts the element
     *            currently at that position (if any) and any subsequent elements to
     *            the right
     */
    void move(int from, int to);

    /**
     * @param index the index of the instruction to be moved
     * @param instruction the instruction to replace the previous one
     */
    void replace(int index, Instruction instruction);

    /**
     * @param to the index where the instruction will be placed causing a shift of
     *            the element currently at that position (if any) and any subsequent
     *            elements to the right
     * @param instruction the instruction to be added
     */
    void add(int to, Instruction instruction);

    /**
     * @param from the index of the instruction to be removed
     */
    void remove(int from);

    /**
     * Remove all instructions from the program.
     */
    void clear();

    /**
     * @return true if undoing the last operation is possible
     */
    boolean canUndo();

    /**
     * Undo the last performed action on the program of the level.
     *
     * @throws IllegalStateException if it is called when {@link #canUndo()} returns
     *             false
     */
    void undo();

    /**
     * @return true if redoing the last undone operation is possible
     */
    boolean canRedo();

    /**
     * Redo the last undone action.
     *
     * @throws IllegalStateException if it is called when {@link #canRedo()} returns
     *             false
     */
    void redo();

    /**
     * Copy in the system clipboard the program in a string format.
     */
    void copy();

    /**
     * Paste a program in string form from the system clipboard to the program
     * substituing any previously present instruction. In case the content of the
     * clipboard can not be parsed paste will not affect the program state.
     */
    void paste();
}
