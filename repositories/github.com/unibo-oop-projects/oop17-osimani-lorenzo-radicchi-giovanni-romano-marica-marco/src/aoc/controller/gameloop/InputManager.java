package aoc.controller.gameloop;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import aoc.utilities.Input;

/**
 * This class provides methods to collect
 * and return the Inputs that the gameview receives.
 */
public class InputManager {
    
    private List<Input> inputList = new LinkedList<>();
    private List<Input> buffer = new LinkedList<>();
    
    public InputManager() {}
    
    /**
     * This method returns the list of inputs.
     * @return list of inputs
     */
    public List<Input> getInputs() {
	return Collections.unmodifiableList(this.inputList);
    }
    
    /**
     * This method clears the buffer,
     * loading its content in inputList.
     */
    public synchronized void clearBuffer() {
	this.inputList = new LinkedList<Input>(this.buffer);
	this.buffer.clear();
    }
    
    /**
     * This method adds an input to the buffer.
     * @param input
     */
    public synchronized void addInput(final Input input) {
	this.buffer.add(input);
    }
}
