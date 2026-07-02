package todo.vm.parser.tokenizer;

import java.util.Iterator;

import todo.vm.parser.ParserException;
import todo.vm.parser.Span;

/**
 * The tokenizer transforms the input string into multiple {@link Token}. This
 * is done to simplify the parsing step by working on an higher level
 * representation, designed to be easily consumed by the parser.
 */
public class Tokenizer implements Iterator<Token<?>> {
    private final String input;
    private final StringBuilder buffer;
    private Span startSpan;
    private Span endSpan;
    private int cursor;
    private State state;
    private boolean finished;

    public Tokenizer(final String input) {
        this.input = input;
        this.buffer = new StringBuilder();
        this.startSpan = Span.DUMMY;
        this.endSpan = Span.DUMMY;
        this.cursor = 0;
        this.state = State.NONE;
        this.finished = false;
    }

    @Override
    public boolean hasNext() {
        return this.finished;
    }

    @Override
    public Token<?> next() {
        // The tokenizer is implemented as a state machine: each char is processed in a
        // loop until a token is fully parsed, and the current state decides how that
        // char is going to be processed, switching between states when needed.
        while (this.cursor < this.input.length()) {
            final char current = this.input.charAt(this.cursor);
            final Span span = new Span(this.cursor, this.cursor + 1);
            this.cursor++;

            switch (this.state) {
                case NONE:
                    this.buffer.setLength(0); // Clear the StringBuilder
                    if (isWhitespace(current)) {
                        // Ignore whitespace
                    } else if (current == '#') {
                        // Ignore this char and switch state
                        this.state = State.COMMENT;
                    } else if (current == '\n') {
                        return new Symbol(SymbolKind.NEW_LINE, span);
                    } else if (current == ':') {
                        return new Symbol(SymbolKind.COLON, span);
                    } else if (isDigit(current)) {
                        this.startSpan = span;
                        this.endSpan = span;
                        this.buffer.append(current);
                        this.state = State.NUMBER;
                    } else if (isAlpha(current)) {
                        this.startSpan = span;
                        this.endSpan = span;
                        this.buffer.append(current);
                        this.state = State.KEYWORD;
                    } else {
                        throw new ParserException(this.input,
                                "unexpected char during tokenization: " + current).withSpan(span);
                    }
                    break;
                case COMMENT:
                    // When a comment is being processed ignore all the chars until a newline is
                    // encountered
                    if (current == '\n') {
                        this.state = State.NONE;
                        this.cursor--;
                    }
                    break;
                case NUMBER:
                    if (isDigit(current)) {
                        this.buffer.append(current);
                        this.endSpan = span;
                    } else if (isAlpha(current)) {
                        // If the number turns out to be a keyword switch to the proper state
                        this.buffer.append(current);
                        this.endSpan = span;
                        this.state = State.KEYWORD;
                    } else {
                        this.state = State.NONE;
                        this.cursor--;
                        return finalizeNumber();
                    }
                    break;
                case KEYWORD:
                    if (isAlpha(current)) {
                        this.buffer.append(current);
                        this.endSpan = span;
                    } else {
                        this.state = State.NONE;
                        this.cursor--;
                        return finalizeKeyword();
                    }
            }
        }
        // The input is terminated now
        switch (this.state) {
            case NUMBER:
                this.state = State.NONE;
                return finalizeNumber();
            case KEYWORD:
                this.state = State.NONE;
                return finalizeKeyword();
            default:
                this.finished = true;
                return new Symbol(SymbolKind.END_OF_INPUT, new Span(this.cursor, this.cursor));
        }
    }

    private Token<?> finalizeNumber() {
        final Span span = this.startSpan.to(this.endSpan);
        this.startSpan = Span.DUMMY;
        this.endSpan = Span.DUMMY;
        return new Number(Integer.parseInt(this.buffer.toString()), span);
    }

    private Token<?> finalizeKeyword() {
        final Span span = this.startSpan.to(this.endSpan);
        this.startSpan = Span.DUMMY;
        this.endSpan = Span.DUMMY;
        return new Keyword(this.buffer.toString(), span);
    }

    private boolean isWhitespace(final char chr) {
        return chr == ' ' || chr == '\r' || chr == '\t';
    }

    private boolean isDigit(final char chr) {
        return chr >= '0' && chr <= '9';
    }

    private boolean isAlpha(final char chr) {
        return (chr >= 'A' && chr <= 'Z') || (chr >= 'a' && chr <= 'z') || isDigit(chr);
    }

    private enum State {
        NONE, COMMENT, NUMBER, KEYWORD;
    }
}
