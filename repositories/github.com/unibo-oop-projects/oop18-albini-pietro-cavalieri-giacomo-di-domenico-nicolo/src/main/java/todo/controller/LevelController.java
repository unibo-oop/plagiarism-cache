package todo.controller;

import java.util.List;
import java.util.Set;

import todo.controller.events.EventManager;
import todo.model.program.Program;
import todo.vm.Value;
import todo.vm.instructions.Instruction;

/**
 * A LevelController is used to wrap a Level and access its data or modify the
 * current program being written by the user through a {@link Program}.
 */
public interface LevelController {
    /**
     * @return the level's title
     */
    String getLevelTitle();

    /**
     * @return the level's starting memory addresses
     */
    List<Value> getLevelMemoryAddresses();

    /**
     * @return the level's memory area width
     */
    int getLevelMemoryAreaWidth();

    /**
     * @return the level's memory area height
     */
    int getLevelMemoryAreaHeight();

    /**
     * @return the level description
     */
    String getLevelDescription();

    /**
     * @return the level's allowed instructions
     */
    Set<Class<? extends Instruction>> getLevelAllowedInstructions();

    /**
     * The input will change each time an execution ends.
     *
     * @return a list of the values of the input used for the current execution
     */
    List<Value> getCurrentInput();

    /**
     * Start the execution of the vm giving it the current program written by the
     * user.
     *
     * @return the {@link ExecutionController} linked to the vm, used to control the
     *         execution of the program
     */
    ExecutionController start();

    /**
     * @return the {@link Program} of the current level
     */
    Program getProgram();

    /**
     * @return the {@link EventManager} of the current level
     */
    EventManager getEventManager();
}
