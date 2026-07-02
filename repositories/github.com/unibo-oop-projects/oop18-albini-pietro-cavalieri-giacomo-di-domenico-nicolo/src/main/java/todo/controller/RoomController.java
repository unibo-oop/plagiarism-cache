package todo.controller;

import java.util.List;
import java.util.Optional;

import todo.vm.instructions.Instruction;

/**
 * This interface models the controller for the room and performs the actions
 * required by the VM.
 */
public interface RoomController {
    /**
     * @return the level controller
     */
    LevelController getLevelController();

    /**
     * This method returns the execution controller only if the program is currently
     * being executed.
     *
     * @return the execution controller
     */
    Optional<ExecutionController> getExecutionController();

    /**
     * Called when the user wants to play the program.
     */
    void onPlay();

    /**
     * Called when the user wants to stop the program.
     */
    void onStop();

    /**
     * Called when the user wants to step to the next instruction in the program.
     */
    void onStep();

    /**
     * Called when the user wants to undo the last action.
     */
    void onUndo();

    /**
     * Called when the user wants to redo the last undone action.
     */
    void onRedo();

    /**
     * Called when the user wants to copy the program to the clipboard.
     */
    void onCopy();

    /**
     * Called when the user wants to paste the program from the clipboard.
     */
    void onPaste();

    /**
     * @return true if the user can perform an undo
     */
    boolean canUndo();

    /**
     * @return true if the user can perform a redo
     */
    boolean canRedo();

    /**
     * @return true if the program is playing
     */
    boolean isPlaying();

    /**
     * @return true if the program can be stepped (i.e. only when play has not yet
     *         been pressed)
     */
    boolean canStep();

    /**
     * Called when the user wants to clear the program.
     */
    void onClear();

    /**
     * Add an instruction to the program and refresh the UI.
     *
     * @param instruction is the instruction to be added
     * @param to is the position of the instruction
     */
    void add(Instruction instruction, int to);

    /**
     * Move an instruction in the program and refresh the UI.
     *
     * @param instruction is the instruction to be moved
     * @param to is the new position of the instruction
     */
    void move(Instruction instruction, int to);

    /**
     * Remove an instruction from the program and refresh the UI.
     *
     * @param instruction is the instruction to be removed
     */
    void remove(Instruction instruction);

    /**
     * Replace an instruction with another one and refresh the UI.
     *
     * @param oldInstruction is the old instruction
     * @param newInstruction is the new instruction
     */
    void replace(Instruction oldInstruction, Instruction newInstruction);

    /**
     * Get the position of an instruction.
     *
     * @param instruction is the instruction to check
     * @return the position of the specified instruction
     */
    int getPosition(Instruction instruction);

    /**
     * @return the instructions in the program
     */
    List<Instruction> getInstructions();
}
