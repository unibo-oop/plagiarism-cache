package controller;

import java.util.Set;
import java.util.stream.Collectors;
import model.entities.Movement;
import view.ViewInputs;

/**
 * A Functional Interface for converting concrete Keyboard inputs into abstract
 * model movements.
 *
 */
@FunctionalInterface
public interface InputTranslator {

    /**
     * This method maps each concrete input to a single abstract movement, it is
     * used by the template method inputParser.
     * 
     * @param input
     *            The input to convert.
     * @return The corresponding movement.
     */
    Movement inputConverter(ViewInputs input);

    /**
     * A template method to convert a set of concrete inputs into a set of abstract
     * movements.
     * 
     * @param inputs
     *            The set of concrete inputs.
     * @return The set of abstract inputs.
     */
    default Set<Movement> inputParser(Set<ViewInputs> inputs) {
        return inputs.stream().map(e -> inputConverter(e)).collect(Collectors.toSet());
    }

}
