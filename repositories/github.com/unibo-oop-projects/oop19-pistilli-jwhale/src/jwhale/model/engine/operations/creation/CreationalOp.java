package jwhale.model.engine.operations.creation;

import jwhale.model.engine.Operation;
/**
 * Configurator for creational operation.
 * @param <T>
 *      implementer's type.
 */
public interface CreationalOp<T> {
    /**
     * Create a creational operation configurator.
     * @param mandatoryParam
     *          mandatory parameters.
     * @return
     *          itself
     */
    T create(String mandatoryParam);
    /**
     * Get a configured operation.
     * @return
     *          Configured operation.
     */
    Operation configOperation();
}
