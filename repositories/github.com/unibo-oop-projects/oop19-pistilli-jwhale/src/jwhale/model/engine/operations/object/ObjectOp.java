package jwhale.model.engine.operations.object;

import jwhale.model.engine.Operation;
/**
 * Configurator for object operation.
 * @param <T>
 *      implementer's type.
 */
public interface ObjectOp<T> {
    /**
     * Create a object operation configurator.
     * @param mandatoryParam
     *          target of the operation.
     * @return
     *          itself
     */
    T target(String mandatoryParam);
    /**
     * Get a configured operation.
     * @return
     *          Configured operation.
     */
    Operation configOperation();

}
