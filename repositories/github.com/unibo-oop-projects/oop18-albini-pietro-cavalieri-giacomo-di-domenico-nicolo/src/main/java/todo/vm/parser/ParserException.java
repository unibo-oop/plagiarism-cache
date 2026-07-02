package todo.vm.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import todo.utils.Checks;

/**
 * This exception is thrown when there is an error during the parsing of a
 * string into a list of instructions. The exception contains the relevant
 * {@link Span}s to identify where it happened inside the input string.
 */
public class ParserException extends RuntimeException {
    private static final long serialVersionUID = 420732563113112073L;

    private final List<Span> spans;
    private final String source;

    public ParserException(final String source, final String message) {
        super(message);
        this.spans = new ArrayList<>();
        this.source = source;
    }

    /**
     * Attach a span to the exception with the builder pattern.
     *
     * @param span the span to attach
     * @return the exception with the span attached
     */
    public ParserException withSpan(final Span span) {
        Checks.require(!Objects.requireNonNull(span).equals(Span.DUMMY), IllegalArgumentException.class,
                "dummy spans are not accepted");
        this.spans.add(span);
        this.spans.sort(null);
        return this;
    }

    /**
     * @return the spans that caused this exception
     */
    public List<Span> getSpans() {
        return Collections.unmodifiableList(this.spans);
    }

    /**
     * @return the source code that caused this exception
     */
    public String getSource() {
        return this.source;
    }
}
