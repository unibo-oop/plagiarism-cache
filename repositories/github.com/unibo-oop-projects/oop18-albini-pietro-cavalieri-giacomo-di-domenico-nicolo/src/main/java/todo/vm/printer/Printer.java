package todo.vm.printer;

/**
 * A Printer is an object that converts a program (made of multiple printable
 * instructions) into a string.
 */
public interface Printer {
    /**
     * @param instr the instruction to print
     */
    void add(PrintableInstruction instr);

    /**
     * Generate the string representation of the added instructions. The method can
     * be called multiple times, and it will output a stable result.
     *
     * @return the string representation of the program
     */
    String generate();
}
