package view.controllers.minigames.perilouspath;

public enum Instructions {

    /**
     * Mines memorization instruction.
     */
    MINES_MEMORIZATION("Memorize the position of the mines"),

    /**
     * The instruction for the search of a safe path from start to finish.
     */
    FIND_SAFE_PATH("Find a safe path from start to finish"),

    /**
     * The instruction to show the perilous path chosen by the user.
     */
    PERILOUS_PATH("Perilous Path");

    private String instruction;

    Instructions(final String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return this.instruction;
    }
}
