package todo.model.level.parser;

import java.io.InputStream;

import todo.model.level.Level;

/**
 * This interface represents a parser to create instances of {@link Level} from
 * XML files.
 */
public interface LevelParser {
    /**
     * @param filePath the XML file to parse from
     * @return a new {@link Level} with the characteristics specified by the XML
     *         file
     * @throws LevelParsingException a custom made Exception for {@link Level}
     *             parsing related problems
     */
    Level parseLevel(InputStream filePath) throws LevelParsingException;
}
