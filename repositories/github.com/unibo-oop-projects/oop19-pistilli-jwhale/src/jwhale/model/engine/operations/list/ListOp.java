package jwhale.model.engine.operations.list;

import jwhale.model.engine.Operation;
/**
 * Configurator for listing operation.
 * @param <T>
 *      implementer's type.
 */
public interface ListOp<T>  {
    /**
     * Get a configured operation.
     * @return
     *          Configured operation.
     */
    Operation configOperation();
    /**
     * Create a listing operation configurator.
     * @return
     *          itself
     */
    T baseSetup();
}
