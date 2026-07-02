package todo.vm.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple2;

import todo.utils.PeekableIterator;
import todo.utils.PeekableIteratorImpl;
import todo.vm.MicrocodeBuilder;
import todo.vm.instructions.Add;
import todo.vm.instructions.BaseInstruction;
import todo.vm.instructions.CopyFrom;
import todo.vm.instructions.CopyTo;
import todo.vm.instructions.Decr;
import todo.vm.instructions.Incr;
import todo.vm.instructions.Input;
import todo.vm.instructions.Instruction;
import todo.vm.instructions.Jump;
import todo.vm.instructions.JumpInstruction;
import todo.vm.instructions.JumpNegative;
import todo.vm.instructions.JumpZero;
import todo.vm.instructions.Output;
import todo.vm.instructions.Sub;
import todo.vm.parser.tokenizer.Keyword;
import todo.vm.parser.tokenizer.Number;
import todo.vm.parser.tokenizer.Symbol;
import todo.vm.parser.tokenizer.SymbolKind;
import todo.vm.parser.tokenizer.Token;
import todo.vm.parser.tokenizer.Tokenizer;
import todo.vm.printer.PrinterState;

/**
 * This parser turns a raw string into a list of instructions, if the string
 * contains a valid program.
 */
public class ParserImpl implements Parser {
    private static final Map<String, Function<State, Instruction>> INSTRUCTIONS_TABLE;
    private final Set<Class<? extends Instruction>> whitelistedInstructions;

    static {
        INSTRUCTIONS_TABLE = new HashMap<>();
        INSTRUCTIONS_TABLE.put("input", state -> new Input());
        INSTRUCTIONS_TABLE.put("output", state -> new Output());
        INSTRUCTIONS_TABLE.put("copyfrom", state -> new CopyFrom(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("copyto", state -> new CopyTo(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("add", state -> new Add(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("sub", state -> new Sub(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("incr", state -> new Incr(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("decr", state -> new Decr(state.parseAddress()));
        INSTRUCTIONS_TABLE.put("jump", state -> state.prepareJump(new Jump()));
        INSTRUCTIONS_TABLE.put("jumpzero", state -> state.prepareJump(new JumpZero()));
        INSTRUCTIONS_TABLE.put("jumpneg", state -> state.prepareJump(new JumpNegative()));
    }

    /**
     * Create a new instance of the parser.
     * 
     * @param whitelistedInstructions the instructions that are allowed to be used
     */
    public ParserImpl(final Set<Class<? extends Instruction>> whitelistedInstructions) {
        this.whitelistedInstructions = Objects.requireNonNull(new HashSet<>((whitelistedInstructions)));
    }

    @Override
    public List<Instruction> parse(final String input) {
        return new State(Objects.requireNonNull(input)).parse();
    }

    private final class State {
        private final String input;
        private final PeekableIterator<Token<?>> tokens;
        private final Map<String, Tuple2<Instruction, Span>> jumpTargets;
        private Span currentSpan;

        private State(final String input) {
            this.input = input;
            this.tokens = new PeekableIteratorImpl<>(new Tokenizer(input));
            this.jumpTargets = new HashMap<>();
            this.currentSpan = Span.DUMMY;
        }

        private List<Instruction> parse() {
            final List<Instruction> instructions = new ArrayList<>();
            eatNewlines();
            while (!eat(Symbol.class, SymbolKind.END_OF_INPUT)) {
                instructions.add(parseInstruction());
                if (!eat(Symbol.class, SymbolKind.END_OF_INPUT)) {
                    expect(Symbol.class, SymbolKind.NEW_LINE);
                    eatNewlines();
                }
            }
            replaceDummyTargets(instructions);
            return instructions;
        }

        private Instruction parseInstruction() {
            final String name = expect(Keyword.class);
            final Span span = this.currentSpan;
            if (eat(Symbol.class, SymbolKind.COLON)) {
                return new DummyTarget(name, span.to(this.currentSpan));
            } else if (ParserImpl.INSTRUCTIONS_TABLE.containsKey(name)) {
                final Instruction parsed = ParserImpl.INSTRUCTIONS_TABLE.get(name).apply(this);
                if (!(parsed.isDummy() || ParserImpl.this.whitelistedInstructions.contains(parsed.getClass()))) {
                    throw new ParserException(this.input, "instruction not allowed: " + name).withSpan(span);
                }
                return parsed;
            } else {
                throw new ParserException(this.input, "unknown instruction: " + name).withSpan(span);
            }
        }

        private int parseAddress() {
            return expect(Number.class);
        }

        private Instruction prepareJump(final JumpInstruction jump) {
            final String target = expect(Keyword.class);
            if (this.jumpTargets.containsKey(target)) {
                throw new ParserException(this.input, "duplicate jump target: " + target).withSpan(
                        this.currentSpan).withSpan(this.jumpTargets.get(target).v2());
            }
            this.jumpTargets.put(target, new Tuple2<>(jump.getTarget(), this.currentSpan));
            return jump;
        }

        private void replaceDummyTargets(final List<Instruction> instructions) {
            // A c-style for is used here because the list is changed inside the loop
            for (int i = 0; i < instructions.size(); i++) {
                final Instruction instr = instructions.get(i);
                if (instr instanceof DummyTarget) {
                    final DummyTarget dummy = (DummyTarget) instr;
                    if (!this.jumpTargets.containsKey(dummy.target)) {
                        throw new ParserException(this.input, "unused jump target: " + dummy.target).withSpan(
                                dummy.span);
                    }
                    final Tuple2<Instruction, Span> targetTuple = this.jumpTargets.remove(dummy.target);
                    instructions.set(i, targetTuple.v1());
                }
            }
            if (!this.jumpTargets.isEmpty()) {
                ParserException e = new ParserException(this.input, "some jumps point to nothing");
                for (final Tuple2<Instruction, Span> tuple : this.jumpTargets.values()) {
                    e = e.withSpan(tuple.v2());
                }
                throw e;
            }
        }

        @SuppressWarnings("unchecked")
        private <T> T expect(final Class<? extends Token<T>> type) {
            final Token<?> next = this.tokens.next();
            if (type.isInstance(next)) {
                this.currentSpan = next.getSpan();
                // The cast is safe since the type was checked at runtime.
                return ((Token<T>) next).getContent();
            } else {
                throw new ParserException(this.input,
                        "expected token " + type.getSimpleName() + ", found " + next.toString()).withSpan(
                                next.getSpan());
            }
        }

        private <T> void expect(final Class<? extends Token<T>> type, final T content) {
            final T next = expect(type);
            if (!next.equals(content)) {
                throw new ParserException(this.input, "expected token " + type.getSimpleName() + " with content "
                        + content.toString() + ", found content " + next.toString()).withSpan(this.currentSpan);
            }
        }

        @SuppressWarnings("unchecked")
        private <T> boolean eat(final Class<? extends Token<T>> type, final T content) {
            final Token<?> peek = this.tokens.peek();
            // The cast is safe since the type was checked at runtime.
            if (type.isInstance(peek) && ((Token<T>) peek).getContent().equals(content)) {
                final Token<?> token = this.tokens.next();
                this.currentSpan = token.getSpan();
                return true;
            }
            return false;
        }

        private void eatNewlines() {
            while (eat(Symbol.class, SymbolKind.NEW_LINE)) {
                // Do nothing.
            }
        }
    }

    private static final class DummyTarget extends BaseInstruction {
        private final String target;
        private final Span span;

        private DummyTarget(final String target, final Span span) {
            this.target = target;
            this.span = span;
        }

        @Override
        public void buildMicrocode(final MicrocodeBuilder builder) {
            throw new UnsupportedOperationException("the dummy target from the parser shouldn't have leaked");
        }

        @Override
        public boolean equals(final Object other) {
            return other instanceof DummyTarget && ((DummyTarget) other).target.equals(this.target);
        }

        @Override
        public String generatePrintableString(final PrinterState state) {
            throw new UnsupportedOperationException("the dummy target from the parser shouldn't have leaked");
        }
    }
}
