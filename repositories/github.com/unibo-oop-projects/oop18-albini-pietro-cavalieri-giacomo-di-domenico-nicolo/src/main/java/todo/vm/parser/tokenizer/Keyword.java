package todo.vm.parser.tokenizer;

import java.util.Objects;

import todo.vm.parser.Span;

/**
 * A keyword is a {@link Token} containing a word.
 */
public class Keyword extends BaseToken<String> {
    public Keyword(final String keyword, final Span span) {
        super(keyword, Objects.requireNonNull(span));
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Keyword && ((Keyword) other).getContent().equals(getContent());
    }

    @Override
    public String toString() {
        return "KEYWORD(" + getContent() + ")";
    }
}
