package jwhale.model.engine.operations.list;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
import jwhale.model.engine.Operation;
/**
 * Abstract listing operation.
 * @param <T>
 *      extender type.
 */
public abstract class AbstractListOpImpl<T extends AbstractListOpImpl<T>> implements ListOp<T> {
    private final Operation listOperation;
    private boolean setup;
    /**
     * Abstract listing operation.
     * @param method
     *          operation method.
     * @param endPoint
     *          operation end point.
     */
    public AbstractListOpImpl(final Method method, final EndPoint endPoint) {
        listOperation = new Operation(method, endPoint);
    }

    @Override
    public final Operation configOperation() {
        if (!setup) {
            throw new IllegalStateException();
        }
        return listOperation;
    }

    @Override
    public abstract T baseSetup();
    /**
     * Close configuration.
     */
    protected final void setSetup() {
        setup = true;
    }
    /**
     * Get operation instance.
     * @return
     *          operation instance.
     */
    protected final Operation getOperation() {
        return listOperation;
    }
}
