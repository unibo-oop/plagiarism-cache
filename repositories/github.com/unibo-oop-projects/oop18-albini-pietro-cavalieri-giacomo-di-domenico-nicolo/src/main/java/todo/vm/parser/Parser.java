package todo.vm.parser;

import java.util.List;

import todo.vm.instructions.Instruction;

/**
 * A parser turns a raw string into a list of instructions, if the string
 * contains a valid program.
 */
public interface Parser {
    /**
     * Parse the provided string, returning the list of instructions that are part
     * of the program if there are no parsing errors.
     *
     * @param input the string to parse
     * @throws ParserException if the input is not a valid program
     * @return the list of instructions that are part of the program
     */
    List<Instruction> parse(String input);
}
