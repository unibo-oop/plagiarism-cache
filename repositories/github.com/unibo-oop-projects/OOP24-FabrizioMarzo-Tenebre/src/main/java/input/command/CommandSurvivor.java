package input.command;

import java.util.function.Consumer;

import model.entities.survivor.Survivor;

/**
 * Utility class to execute commands on a {@link Survivor} instance.
 * 
 * <p>This class provides a method to issue a command, represented as a
 * {@link Consumer}, to a given Survivor object.</p>
 */
public class CommandSurvivor {

    /**
     * Issues a command to the specified {@link Survivor}.
     *
     * @param sur the {@link Survivor} instance on which the command will be executed
     * @param command a {@link Consumer} representing the command to apply to the Survivor
     * 
     * <p>The command is executed by invoking {@code accept} on the provided Consumer.</p>
     */
    public static void issue(final Survivor sur,final Consumer<Survivor> command){
        command.accept(sur);
    }
    
}
