package jwhale.model.engine.operations.object;

import jwhale.model.engine.EndPoint;
import jwhale.model.engine.Method;
import jwhale.model.engine.Operation;
/**
 * Abstract object operation.
 * @param <T>
 *      extender type.
 */
public abstract class AbstractObjectOpImpl<T extends AbstractObjectOpImpl<T>> implements ObjectOp<T> {
    private boolean setup;
    private final Operation op;

    public AbstractObjectOpImpl(final Method method, final EndPoint endPoint) {
        op = new Operation(method, endPoint);
    }
    @Override
    public abstract T target(String mandatoryParam);

    @Override
    public final Operation configOperation() {
        if (!setup) {
            throw new IllegalStateException();
        }
        return op;
    }
    /**
     * Get operation instance.
     * @return
     *          operation instance.
     */
    protected final Operation getOperation() {
        return op;
    }
    /**
     * Close configuration.
     */
    protected final void setSetup() {
        setup = true;
    }
}
