package todo.vm.parser.tokenizer;

import todo.vm.parser.Span;

/**
 * A token represents a parsed slice of the tokenized string. Each token has a
 * type and an inner value.
 *
 * @param <T> the type of the inner value of the token
 */
public interface Token<T> {
    /**
     * @return the inner value of the token
     */
    T getContent();

    /**
     * @return the span that contains this token
     */
    Span getSpan();
}
