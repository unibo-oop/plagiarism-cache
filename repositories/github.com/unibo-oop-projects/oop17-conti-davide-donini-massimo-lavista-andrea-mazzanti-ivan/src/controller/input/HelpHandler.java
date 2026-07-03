package controller.input;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Common interface to manage requests of input.
 * 
 */
public interface HelpHandler {

    /**
     * 
     * @param command
     *          successor that try to handle the request.
     */
    void setSuccessor(Optional<AbstractHandler> command);


    /**
     * 
     * Try to handle the request.
     * 
     * @param commandType
     *          the request
     * @return the response to the request.
     */
    List<Optional<Command>> processRequest(CommandType commandType);
}
