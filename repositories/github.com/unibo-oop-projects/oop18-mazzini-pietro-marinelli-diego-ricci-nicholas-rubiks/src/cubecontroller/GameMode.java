package cubecontroller;

/**
 * Enumerator containing game modes; this makes the code more extendible.
 */
public enum GameMode {
    /**
     * Mode: Random, a random cube is generated and the user will solve this.
     */
    RANDOM,
    /**
     * Mode: Solve, the user inserts an existent cube and the algorithm solves the cube.
     */
    SOLVE
}
