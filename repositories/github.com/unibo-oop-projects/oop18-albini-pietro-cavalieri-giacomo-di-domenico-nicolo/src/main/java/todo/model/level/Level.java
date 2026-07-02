package todo.model.level;

import java.util.List;
import java.util.Set;

import todo.vm.Value;
import todo.vm.instructions.Instruction;

/**
 * This interface models a game's level. It has almost entirely getters for all
 * the level-related informations.
 */
public interface Level {
    /**
     * @return the level's title
     */
    String getTitle();

    /**
     * @return the list of memory addresses' values
     */
    List<Value> getMemoryAddresses();

    /**
     * @return the number of rows for the memory area
     */
    int getMemoryRows();

    /**
     * @return the number of columns for the memory area
     */
    int getMemoryColumns();

    /**
     * @return the number of this level
     */
    int getProgressiveNumber();

    /**
     * @return a description for this level, with the task to be completed
     */
    String getDescription();

    /**
     * @return this level's list of allowed instructions
     */
    Set<Class<? extends Instruction>> getAllowedInstructions();

    /**
     * @return this level's solutions, expressed by a list of instructions
     */
    List<Instruction> getSolution();

    /**
     * @return a new list of inputs for this specific level
     */
    List<Value> getInput();
}
