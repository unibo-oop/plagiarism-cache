package todo.model.program;

/**
 * A ProgramCommand is an entity that represents the possible actions that can
 * be performed on a {@link Program}, a Program internally uses the Commands to
 * enable undoing and redoing capabiliteis.
 */
interface ProgramCommand {
    /**
     * The Command performes its operation on the receiving Program.
     *
     * @throws IllegalStateException if it is called twice in a row
     */
    void execute();

    /**
     * The Command unexecutes its operation on the receiving Program.
     *
     * @throws IllegalStateException if it is not called immediatly after
     *             {@link #execute()}
     */
    void unexecute();
}
