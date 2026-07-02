package todo.model.level.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.stream.Collectors;

import org.jooq.lambda.fi.util.function.CheckedFunction;
import org.junit.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import todo.model.level.Level;
import todo.model.level.parser.LevelParser;
import todo.model.level.parser.LevelParserImpl;

/**
 * Extensions of this class are made to check that the parser works for every
 * possible use-case
 */
public abstract class BaseParserTest {
    private static final LevelParser PARSER = new LevelParserImpl();

    protected Level builtLevel;
    protected final Level parsedLevel;

    BaseParserTest() {
        this.parsedLevel = getParsedLevel(getFilePath());
    }

    private Level getParsedLevel(final String pathfile) {
        try (ScanResult levelScan = new ClassGraph().whitelistPaths("assets/levels").scan()) {
            return levelScan.getResourcesWithPath(pathfile)
                            .stream()
                            .map(CheckedFunction.unchecked(r -> r.open()))
                            .map(PARSER::parseLevel)
                            .collect(Collectors.toList())
                            .get(0);
        }
    }

    protected abstract String getFilePath();

    @Test
    public void testTitle() {
        assertEquals(this.builtLevel.getTitle(), this.parsedLevel.getTitle());
    }

    @Test
    public void testRows() {
        assertEquals(this.builtLevel.getMemoryRows(), this.parsedLevel.getMemoryRows());
    }

    @Test
    public void testColumns() {
        assertEquals(this.builtLevel.getMemoryColumns(), this.parsedLevel.getMemoryColumns());
    }

    @Test
    public void testDescription() {
        assertEquals(this.builtLevel.getDescription(), this.parsedLevel.getDescription());
    }

    @Test
    public void testAllowedInstruction() {
        assertEquals(this.builtLevel.getAllowedInstructions(), this.parsedLevel.getAllowedInstructions());
    }

    @Test
    public void testSolution() {
        assertEquals(this.builtLevel.getSolution(), this.parsedLevel.getSolution());
    }

    @Test
    public void testMemoryAddresses() {
        assertEquals(this.builtLevel.getMemoryAddresses(), this.parsedLevel.getMemoryAddresses());
    }

    @Test
    public void testInput() {
        fail("Input test must be overridden because it's case specific");
    }
}
