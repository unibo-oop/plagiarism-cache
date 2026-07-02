package todo.vm.parser.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import todo.vm.parser.ParserException;
import todo.vm.parser.Span;

public class TokenizerTest {
    private static final Symbol EOI = new Symbol(SymbolKind.END_OF_INPUT, Span.DUMMY);
    private static final Symbol NL = new Symbol(SymbolKind.NEW_LINE, Span.DUMMY);
    private static final Symbol COLON = new Symbol(SymbolKind.COLON, Span.DUMMY);
    private static final Keyword FOO = new Keyword("foo", Span.DUMMY);
    private static final Keyword BAR = new Keyword("bar", Span.DUMMY);

    @Test
    public void testWhitespacesAreSkipped() {
        final List<Token<?>> tokens = expect("foo  \t\r  bar", FOO, BAR, EOI);
        assertEquals(new Span(0, 3), tokens.get(0).getSpan());
        assertEquals(new Span(9, 12), tokens.get(1).getSpan());
        assertEquals(new Span(12, 12), tokens.get(2).getSpan());
    }

    @Test
    public void testCommentsAreSkipped() {
        final List<Token<?>> tokens = expect("# foo\nbar# baz", NL, BAR, EOI);
        assertEquals(new Span(5, 6), tokens.get(0).getSpan());
        assertEquals(new Span(6, 9), tokens.get(1).getSpan());
        assertEquals(new Span(14, 14), tokens.get(2).getSpan());
    }

    @Test
    public void testEmptyStringIsValid() {
        final List<Token<?>> tokens = expect("", EOI);
        assertEquals(new Span(0, 0), tokens.get(0).getSpan());
    }

    @Test
    public void testNewLines() {
        final List<Token<?>> tokens = expect("foo\nbar", FOO, NL, BAR, EOI);
        assertEquals(new Span(0, 3), tokens.get(0).getSpan());
        assertEquals(new Span(3, 4), tokens.get(1).getSpan());
        assertEquals(new Span(4, 7), tokens.get(2).getSpan());
        assertEquals(new Span(7, 7), tokens.get(3).getSpan());
    }

    @Test
    public void testColons() {
        final List<Token<?>> tokens = expect("foo:bar", FOO, COLON, BAR, EOI);
        assertEquals(new Span(0, 3), tokens.get(0).getSpan());
        assertEquals(new Span(3, 4), tokens.get(1).getSpan());
        assertEquals(new Span(4, 7), tokens.get(2).getSpan());
        assertEquals(new Span(7, 7), tokens.get(3).getSpan());
    }

    @Test
    public void testNumbers() {
        final List<Token<?>> tokens = expect("0 1 42 100000", new Number(0, Span.DUMMY), new Number(1, Span.DUMMY),
                new Number(42, Span.DUMMY), new Number(100000, Span.DUMMY), EOI);
        assertEquals(new Span(0, 1), tokens.get(0).getSpan());
        assertEquals(new Span(2, 3), tokens.get(1).getSpan());
        assertEquals(new Span(4, 6), tokens.get(2).getSpan());
        assertEquals(new Span(7, 13), tokens.get(3).getSpan());
        assertEquals(new Span(13, 13), tokens.get(4).getSpan());
    }

    @Test
    public void testKeywords() {
        final List<Token<?>> tokens = expect("foo bar BAZ 0quUx", FOO, BAR, new Keyword("BAZ", Span.DUMMY),
                new Keyword("0quUx", Span.DUMMY), EOI);
        assertEquals(new Span(0, 3), tokens.get(0).getSpan());
        assertEquals(new Span(4, 7), tokens.get(1).getSpan());
        assertEquals(new Span(8, 11), tokens.get(2).getSpan());
        assertEquals(new Span(12, 17), tokens.get(3).getSpan());
        assertEquals(new Span(17, 17), tokens.get(4).getSpan());
    }

    @Test
    public void testKeywordsWithInvalidEnding() {
        try {
            expect("foo!", FOO, EOI);
            fail("unexpected success");
        } catch (final ParserException e) {
            assertEquals(Arrays.asList(new Span(3, 4)), e.getSpans());
        } catch (final Exception e) {
            fail("unexpected exception: " + e.toString());
        }
    }

    private List<Token<?>> expect(final String input, final Token<?>... tokens) {
        final Tokenizer tokenizer = new Tokenizer(input);
        final List<Token<?>> result = new ArrayList<>();
        while (true) {
            final Token<?> token = tokenizer.next();
            result.add(token);
            if (token.equals(EOI)) {
                break;
            }
        }
        assertEquals(Arrays.asList(tokens), result);
        return result;
    }
}
