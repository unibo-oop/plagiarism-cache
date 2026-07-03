package laterunner.input;

/**
 * Command controller.
 */
public interface Controller {

    /**
     * Notifies a command.
     * 
     * @param cmd
     *          command to be notified
     */
    void notifyCommand(Command cmd);

}
