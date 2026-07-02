package jwhale.model.engine.operations.creation;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
import jwhale.model.engine.Operation;
/**
 * Abstract creational operation.
 * @param <T>
 *      extender type.
 */
public abstract class AbstractCreationalOpImpl<T extends AbstractCreationalOpImpl<T>> implements CreationalOp<T> {
    private boolean configured;
    private final Operation creationalOperation;
    /**
     * Abstract creational operation.
     * @param method
     *          operation method.
     * @param endPoint
     *          operation end point.
     */
    public AbstractCreationalOpImpl(final Method method, final EndPoint endPoint) {
        creationalOperation = new Operation(method, endPoint);
    }

    @Override
    public abstract T create(String mandatoryParam);

    @Override
    public final Operation configOperation() {
        if (!configured) {
            throw new IllegalStateException();
        }
        return creationalOperation;
    }
    /**
     * Get operation instance.
     * @return
     *          operation instance.
     */
    protected final Operation getOperation() {
        return creationalOperation;
    }
    /**
     * Close configuration.
     */
    protected final void setSetup() {
        configured = true;
    }
}
