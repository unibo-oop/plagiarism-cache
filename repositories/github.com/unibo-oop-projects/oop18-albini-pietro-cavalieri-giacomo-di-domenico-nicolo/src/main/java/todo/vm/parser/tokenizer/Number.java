package todo.vm.parser.tokenizer;

import todo.vm.parser.Span;

/**
 * A number is a {@link Token} containing an integer.
 */
public class Number extends BaseToken<Integer> {
    public Number(final int number, final Span span) {
        super(number, span);
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Number && ((Number) other).getContent().equals(getContent());
    }

    @Override
    public String toString() {
        return "NUMBER(" + getContent() + ")";
    }
}
