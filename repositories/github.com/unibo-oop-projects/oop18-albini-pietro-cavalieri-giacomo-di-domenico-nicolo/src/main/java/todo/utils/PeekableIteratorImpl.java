package todo.utils;

import java.util.Iterator;
import java.util.Optional;

public class PeekableIteratorImpl<T> implements PeekableIterator<T> {
    private final Iterator<T> inner;
    private Optional<T> cache;

    public PeekableIteratorImpl(final Iterator<T> inner) {
        this.inner = inner;
        this.cache = Optional.empty();
    }

    @Override
    public boolean hasNext() {
        return this.cache.isPresent() || this.inner.hasNext();
    }

    @Override
    public T next() {
        if (this.cache.isPresent()) {
            final T value = this.cache.get();
            this.cache = Optional.empty();
            return value;
        } else {
            return this.inner.next();
        }
    }

    @Override
    public T peek() {
        if (!this.cache.isPresent()) {
            this.cache = Optional.ofNullable(this.inner.next());
        }
        return this.cache.get();
    }
}
