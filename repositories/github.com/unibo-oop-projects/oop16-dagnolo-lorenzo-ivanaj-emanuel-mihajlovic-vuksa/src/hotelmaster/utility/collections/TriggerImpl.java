package hotelmaster.utility.collections;

import java.util.function.Consumer;

/**
 * A basic implementation of {@link Trigger}.
 * 
 * @param <E>
 *            the type of the consumer
 */
public class TriggerImpl<E> implements Trigger<E> {

    private final TriggeringOperation operation;
    private final Consumer<E> consumer;

    TriggerImpl(final TriggeringOperation operation, final Consumer<E> consumer) {
        this.operation = operation;
        this.consumer = consumer;
    }

    @Override
    public TriggeringOperation getOperation() {
        return this.operation;
    }

    @Override
    public void execute(final E element) {
        this.consumer.accept(element);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumer == null) ? 0 : consumer.hashCode());
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj;
    }

}
