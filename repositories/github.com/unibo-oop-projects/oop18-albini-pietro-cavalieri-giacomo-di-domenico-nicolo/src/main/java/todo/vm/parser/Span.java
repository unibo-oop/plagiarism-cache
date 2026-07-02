package todo.vm.parser;

import java.util.Objects;

import todo.utils.Checks;

/**
 * A span represents a slice of the parser's input, identifying it with the
 * start and end position inside the input string. The span doesn't contain the
 * actual input string.
 */
public class Span implements Comparable<Span> {
    public static final Span DUMMY = new Span();

    private final int start;
    private final int end;

    /**
     * Create a new span.
     *
     * @param start the start position of the span
     * @param end the end position of the span
     */
    public Span(final int start, final int end) {
        Checks.require(start >= 0, IllegalArgumentException.class, "the span start is negative");
        Checks.require(end >= 0, IllegalArgumentException.class, "the span end is negative");
        this.start = start;
        this.end = end;
    }

    /**
     * Create a dummy span. Public code should use {@link #DUMMY}.
     */
    private Span() {
        this.start = -1;
        this.end = -1;
    }

    /**
     * Merge two spans, returning the shortest span that contains both of them. The
     * method doesn't mutate either of the spans, but returns a new one.
     *
     * @param other the span to merge with the current one
     * @return the merged span
     */
    public Span to(final Span other) {
        Objects.requireNonNull(other);
        Checks.require(!other.equals(DUMMY) && !equals(DUMMY), IllegalArgumentException.class,
                "can't merge dummy spans");
        return new Span(Math.min(this.start, other.start), Math.max(this.end, other.end));
    }

    /**
     * @return the start position of this span
     */
    public int getStart() {
        Checks.require(!equals(DUMMY), IllegalStateException.class, "can't get the start position of dummy spans");
        return this.start;
    }

    /**
     * @return the end position of this span
     */
    public int getEnd() {
        Checks.require(!equals(DUMMY), IllegalStateException.class, "can't get the end position of dummy spans");
        return this.end;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Span && ((Span) other).start == this.start && ((Span) other).end == this.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }

    @Override
    public String toString() {
        return "Span(" + (!equals(DUMMY) ? this.start + " -> " + this.end : "dummy") + ")";
    }

    @Override
    public int compareTo(final Span other) {
        Checks.require(!other.equals(DUMMY) && !equals(DUMMY), IllegalArgumentException.class,
                "can't compare dummy spans");
        final int start = this.start - other.start;
        return start != 0 ? start : this.end - other.end;
    }
}
