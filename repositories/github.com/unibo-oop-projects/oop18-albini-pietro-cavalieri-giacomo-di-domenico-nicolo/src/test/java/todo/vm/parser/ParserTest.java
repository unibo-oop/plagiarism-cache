package todo.vm.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import todo.vm.Value;
import todo.vm.Vm;
import todo.vm.VmImpl;
import todo.vm.exceptions.VmRuntimeException;
import todo.vm.instructions.Add;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpNegative;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;

public class ParserTest {
    private static final Set<Class<? extends Instruction>> INSTRUCTIONS_WHITELIST = new HashSet<>(l(Input.class,
            Output.class, Jump.class, JumpZero.class, JumpNegative.class, CopyFrom.class, CopyTo.class, Add.class));

    @Test
    public void testEmptyInput() {
        assertEquals(Collections.emptyList(), parse(""));
    }

    @Test
    public void testEmptyLines() {
        assertEquals(l(new Input(), new Output()), parse("\n\ninput\n\noutput\n\n\n\n\n"));
    }

    @Test
    public void testSimpleProgram() {
        final Jump jump = new Jump();
        assertEquals(l(jump.getTarget(), new Input(), new Output(), jump), parse("loop:\ninput\noutput\njump loop"));
    }

    @Test
    public void testUnknownInstruction() {
        final ParserException e = assertParseError("input\nfoo\noutput");
        assertEquals("unknown instruction: foo", e.getMessage());
        assertEquals(l(new Span(6, 9)), e.getSpans());
    }

    @Test
    public void testDuplicateJumpTarget() {
        final ParserException e = assertParseError("foo:\njump foo\njumpzero foo");
        assertEquals("duplicate jump target: foo", e.getMessage());
        assertEquals(l(new Span(10, 13), new Span(23, 26)), e.getSpans());
    }

    @Test
    public void testJumpToNothing() {
        final ParserException e = assertParseError("bar:\njump foo\njumpzero bar\njumpneg baz");
        assertEquals("some jumps point to nothing", e.getMessage());
        assertEquals(l(new Span(10, 13), new Span(35, 38)), e.getSpans());
    }

    @Test
    public void testUnusedJumpTarget() {
        final ParserException e = assertParseError("foo:\ninput");
        assertEquals("unused jump target: foo", e.getMessage());
        assertEquals(l(new Span(0, 4)), e.getSpans());
    }

    @Test
    public void testInvalidToken() {
        final ParserException e = assertParseError(":");
        assertEquals("expected token Keyword, found SYMBOL(COLON)", e.getMessage());
        assertEquals(l(new Span(0, 1)), e.getSpans());
    }

    @Test
    public void testInvalidTokenContent() {
        final ParserException e = assertParseError("add 1:");
        assertEquals("expected token Symbol with content NEW_LINE, found content COLON", e.getMessage());
        assertEquals(l(new Span(5, 6)), e.getSpans());
    }

    @Test
    public void testExecutingParsedProgram() throws VmRuntimeException {
        final List<Instruction> parsed = parse("loop:\ninput\ncopyto 0\ninput\noutput\ncopyfrom 0\noutput\njump loop");
        final List<Value> input = l(Value.number(1), Value.number(2), Value.number(3), Value.number(4));
        final Vm vm = new VmImpl(parsed, input, l(Value.empty()));
        vm.execute();
        assertEquals(l(Value.number(2), Value.number(1), Value.number(4), Value.number(3)), vm.getOutput());
    }

    @Test
    public void testParseNotWhitelistedInstructions() throws VmRuntimeException {
        final ParserException e = assertParseError("sub 1");
        assertEquals("instruction not allowed: sub", e.getMessage());
        assertEquals(l(new Span(0, 3)), e.getSpans());
    }

    @Test
    public void testLabelsNamedAsInstructions() throws VmRuntimeException {
        final Jump jmp = new Jump();
        assertEquals(l(jmp.getTarget(), new Input(), jmp), parse("input:\ninput\njump input"));
    }

    @SafeVarargs
    private static final <T> List<T> l(final T... items) {
        // Just a shorthand to avoid writing Arrays.asList everywhere
        // @SafeVarargs is "safe" because it's also present in Arrays.asList, and this
        // just wraps that method
        return Arrays.asList(items);
    }

    private ParserException assertParseError(final String input) {
        try {
            parse(input);
            fail("parsing did not fail: " + input);
        } catch (final ParserException e) {
            return e;
        } catch (final Exception e) {
            fail("parsing failed with " + e.toString() + ": " + input);
        }
        // Everywhere fail() is called, but Java doesn't detect this is unreachable
        return null;
    }

    private List<Instruction> parse(final String input) {
        return new ParserImpl(INSTRUCTIONS_WHITELIST).parse(input);
    }
}
