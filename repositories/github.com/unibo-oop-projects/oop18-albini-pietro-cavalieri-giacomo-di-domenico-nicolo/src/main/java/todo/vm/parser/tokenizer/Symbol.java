package todo.vm.parser.tokenizer;

import todo.vm.parser.Span;

/**
 * A symbol is a {@link Token} containing a {@link SymbolKind}.
 */
public class Symbol extends BaseToken<SymbolKind> {
    public Symbol(final SymbolKind kind, final Span span) {
        super(kind, span);
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Symbol && ((Symbol) other).getContent().equals(getContent());
    }

    @Override
    public String toString() {
        return "SYMBOL(" + getContent().name() + ")";
    }
}
