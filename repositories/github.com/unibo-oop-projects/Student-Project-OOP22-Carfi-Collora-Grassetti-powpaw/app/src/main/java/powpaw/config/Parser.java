package powpaw.config;

import org.yaml.snakeyaml.Yaml;

import javafx.scene.input.KeyCode;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A class responsible for parsing a YAML file containing key inputs and
 * converting them to KeyCode objects.
 * 
 * @author Alessia Carfì
 */
public class Parser {

    /**
     * The name of the YAML file to be parsed.
     */
    private static final String YAMLNAME = "keyinput.yaml";

    /**
     * A mapping of player numbers to their corresponding key inputs as KeyCode
     * objects.
     */
    private final Map<Integer, Map<String, KeyCode>> commands = new HashMap<>();

    private final Yaml yaml = new Yaml();
    private final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(YAMLNAME);

    /**
     * Constructs a Parser object and parses the YAML file, converting key inputs to
     * KeyCode objects.
     */
    public Parser() {
        final Map<Integer, Map<String, String>> stringCommands = yaml.load(inputStream);

        stringCommands.entrySet().forEach(e -> {
            this.commands.put(e.getKey(), new HashMap<>());
            e.getValue().entrySet()
                    .forEach(t -> this.commands.get(e.getKey()).put(t.getKey(), KeyCode.valueOf(t.getValue())));
        });
    }

    /**
     * Returns a mapping of player number to their corresponding key inputs as
     * KeyCode objects.
     * 
     * @param playerNumber the number of the player whose key inputs are requested.
     * @return a mapping of key input names to their corresponding KeyCode objects.
     */
    public Map<String, KeyCode> getCommands(final int playerNumber) {
        return this.commands.get(playerNumber);
    }

}
