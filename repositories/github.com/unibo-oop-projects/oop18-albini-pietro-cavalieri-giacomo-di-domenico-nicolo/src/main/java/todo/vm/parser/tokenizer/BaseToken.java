package todo.vm.parser.tokenizer;

import java.util.Objects;

import todo.vm.parser.Span;

public abstract class BaseToken<T> implements Token<T> {
    private final T content;
    private final Span span;

    public BaseToken(final T content, final Span span) {
        this.content = Objects.requireNonNull(content);
        this.span = Objects.requireNonNull(span);
    }

    @Override
    public T getContent() {
        return this.content;
    }

    @Override
    public Span getSpan() {
        return this.span;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }
}
