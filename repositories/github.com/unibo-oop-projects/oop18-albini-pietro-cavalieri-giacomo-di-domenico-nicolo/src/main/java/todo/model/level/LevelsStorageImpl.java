package todo.model.level;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jooq.lambda.fi.util.function.CheckedFunction;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import todo.model.level.parser.LevelParser;
import todo.model.level.parser.LevelParserImpl;
import todo.utils.Checks;

/**
 * See {@link LevelsStorage}.
 */
public class LevelsStorageImpl implements LevelsStorage {
    private static final LevelParser PARSER = new LevelParserImpl();

    private final Map<String, Level> levels;

    public LevelsStorageImpl() throws IOException {
        try (ScanResult levelsScan = new ClassGraph().whitelistPackages("assets/levels").scan()) {
            this.levels = levelsScan.getAllResources()
                                    .stream()
                                    .map(CheckedFunction.unchecked(r -> r.open()))
                                    .map(PARSER::parseLevel)
                                    .collect(Collectors.toMap(Level::getTitle, Function.identity()));
        }
    }

    @Override
    public Set<Level> getAllLevels() {
        return new LinkedHashSet<>(this.levels.values());
    }

    @Override
    public Level getLevel(final String title) {
        Checks.require(this.levels.containsKey(title), NoSuchElementException.class,
                "There is no level with the given name");
        return this.levels.get(title);
    }
}
