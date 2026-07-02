package net.pokemonbt.controller.resources;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;
import net.pokemonbt.model.condition.Condition;
import net.pokemonbt.model.condition.components.ConditionDispenser;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.MoveCategory;
import net.pokemonbt.model.move.SimpleMove;
import net.pokemonbt.model.move.SubCategory;
import net.pokemonbt.model.move.components.BehaviourFactory;
import net.pokemonbt.model.move.components.MoveBehaviour;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.BasePokemon;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.components.ConditionComponent;
import net.pokemonbt.model.pokemon.components.ItemComponent;
import net.pokemonbt.model.pokemon.components.MoveComponent;
import net.pokemonbt.model.pokemon.components.StatComponent;
import net.pokemonbt.utility.Pair;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class that handles anything related to resources: JSON files, images and any
 * other.
 */
public class ResourceManager {
    private static final String ID_JSON_KEY = "id";

    private static Map<String, Move> moveList = new LinkedHashMap<>();

    /**
     * Instantiate this class for utility usage in getting resources.
     */
    public ResourceManager() {
        // It doesn't need any parameters to be initialized.
    }

    /**
     * @param path The provided path.
     * @return The corresponding exception message.
     */
    private String exceptionInvalidPath(final String path) {
        return "Provided path ("
                .concat(path)
                .concat(") is invalid.");
    }

    /**
     * Gets a file under the resource folder as a String. The resource is decoded
     * assuming the contents are encoded using the charset provided to this method.
     *
     * @param path The path of the file relative to the resource folder.
     * @param charset The charset to decode the file's contents with.
     * @return  A string containing all the bytes inside the resource and decoded using
     *          the provided charset.
     */
    public static Optional<String> getResourceAsString(final String path, final Charset charset) {
        try (InputStream is = ResourceManager.class.getClassLoader().getResourceAsStream(path)) {
            if (Objects.isNull(is)) {
                throw new IllegalArgumentException("Internal input stream at path \""
                        .concat(path).concat("\" could not be generated."));
            }
            try (
                    InputStreamReader isreader = new InputStreamReader(is, charset);
                    BufferedReader reader = new BufferedReader(isreader)
            ) {
                return Optional.of(reader.lines().collect(Collectors.joining("\n")));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage()); //NOPMD
            return Optional.empty();
        }
    }

    /**
     * Gets a file under the resource folder as a String. The resource is decoded
     * assuming the contents are encoded using the standard charset.
     *
     * @param path The path of the file relative to the resource folder.
     * @return  A string containing all the bytes inside the resource and decoded using
     *          the default charset ({@code Charset.defaultCharset()}).
     */
    public static Optional<String> getResourceAsString(final String path) {
        return getResourceAsString(path, Charset.defaultCharset());
    }

    /**
     * Gets a file under the resource folder as an input stream, then writes
     * all the contents read with the input stream in a temporary file. This method
     * can be used when other methods want a resource exclusively as a {@link File}.
     *
     * @param path The path of the file relative to the resource folder.
     * @return  A temporary {@link File} created under the jar's "resource" location, with
     *          the ".tmp" file extension.
     * @throws IOException If the file could not be read.
     */
    public static File getResourceAsTempFile(final String path) throws IOException {
        final InputStream is = ResourceManager.class.getClassLoader().getResourceAsStream(path);
        if (Objects.isNull(is)) {
            throw new IllegalArgumentException("Internal input stream at path \""
                    .concat(path).concat("\" could not be generated."));
        }
        /* Temporary file created in the resource path */
        final File tempFile = File.createTempFile("resource-", ".tmp");
        tempFile.deleteOnExit();
        try (OutputStream os = new FileOutputStream(tempFile)) {
            /* Transfers all data from the input stream to the temp file. */
            is.transferTo(os);
        }
        return tempFile;
    }

    /**
     * Gets a file under the resource folder as a string, and uses thw {@link JsonParser}
     * class to try to convert it to a {@link JsonElement}.
     *
     * @param path The path of the file relative to the resource folder.
     * @return The {@link JsonElement} wrapped in an {@link Optional}.
     */
    public static Optional<JsonElement> getResourceAsJsonElement(final String path) {
        return getResourceAsString(path).map(JsonParser::parseString);
    }

    /**
     * Gets a file under the resource folder as an {@link Image}.
     *
     * @param path The path of the file relative to the resource folder.
     * @return The created {@link Image}.
     */
    public static Image getResourceAsImage(final String path) {
        return new Image(path);
    }

    /**
     * Gets a file under the resource folder as an {@link InputStream}.
     *
     * @param path The path of the file relative to the resource folder.
     * @return An {@link InputStream} to the specified file.
     */
    public static InputStream getResourceAsInputStream(final String path) {
        return ResourceManager.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * Tries to get a list of objects created from the specified JSON and
     * converted into objects using the given operation.
     *
     * @param path      A path relative to the resource folder of the JSON file.
     * @param operation The operation to apply to the {@link JsonObject} in order
     *                  to convert it to the desired object.
     * @param <T>       The desired object type.
     * @return A list of all the given object correctly created from the JSON file.
     */
    public <T> List<T> getJsonList(final String path, final Function<JsonObject, T> operation) {
        Objects.requireNonNull(path);
        if (path.isBlank()) {
            throw new IllegalArgumentException(this.exceptionInvalidPath(path));
        }
        final Optional<String> jsonString = getResourceAsString(path);
        if (jsonString.isEmpty()) {
            return List.of();
        }
        final List<JsonElement> list = JsonParser.parseString(jsonString.get()).getAsJsonArray().asList();
        final List<T> result = new LinkedList<>();
        for (final var i : list) {
            result.add(operation.apply(i.getAsJsonObject()));
        }
        return result;
    }

    /**
     * Tries to get a map of objects created from the specified JSON and
     * converted into objects using the given operation.
     *
     * @param path           A path relative to the resource folder of the JSON
     *                       file.
     * @param keyOperation   The operation to apply to the {@link JsonObject} in
     *                       order
     *                       to convert it to the desired key.
     * @param valueOperation The operation to apply to the {@link JsonObject} in
     *                       order
     *                       to convert it to the desired object.
     * @param <K>            The desired key type.
     * @param <V>            The desired object type.
     * @return A map of all the given objects, mapped to their respective keys,
     *         correctly created from the JSON file.
     */
    public <K, V> Map<K, V> getJsonMap(
            final String path,
            final Function<JsonObject, K> keyOperation,
            final Function<JsonObject, V> valueOperation) {
        Objects.requireNonNull(path);
        if (path.isBlank()) {
            throw new IllegalArgumentException(this.exceptionInvalidPath(path));
        }
        final Optional<String> moveJson = getResourceAsString(path);
        if (moveJson.isEmpty()) {
            return Map.of();
        }
        final List<JsonElement> list = JsonParser.parseString(moveJson.get()).getAsJsonArray().asList();
        final Map<K, V> result = new LinkedHashMap<>();
        for (final var i : list) {
            result.put(keyOperation.apply(i.getAsJsonObject()), valueOperation.apply(i.getAsJsonObject()));
        }
        return result;
    }

    /**
     * Given a string, it makes the first character of that string lowercase,
     * while keeping everything else the same.
     *
     * @param str The string to convert.
     * @return The resulting string.
     */
    private static String makeFirstCharLowerCase(final String str) {
        return str.substring(0, 1).toLowerCase(Locale.ROOT) + str.substring(1);
    }

    /**
     * Creates a move from the given JSON object.
     *
     * @param obj The {@link JsonObject} to parse.
     * @return The newly created move.
     */
    public static Move createMove(final JsonObject obj) {
        Objects.requireNonNull(obj);
        final String moveID = obj.get(ID_JSON_KEY).getAsString();
        final MoveCategory category = MoveCategory.stringToType(obj.get("category").getAsString())
                .orElse(MoveCategory.PHYSICAL);

        final MoveBehaviour behaviour = BehaviourFactory.get(
                moveID,
                obj.get("behaviour").getAsJsonArray().asList().stream()
                        .map(JsonElement::getAsString)
                        .map(x -> new Pair<>(
                                x,
                                Optional.ofNullable(obj.get(makeFirstCharLowerCase(x)))))
                        .toList());

        final var moveBuilder = new SimpleMove.Builder()
                .id(moveID)
                .name(obj.get("displayName").getAsString())
                .description(obj.get("description").getAsString())
                .type(PokeType.stringToType(obj.get("type").getAsString()))
                .behaviour(behaviour)
                .category(category)
                .maxPP(obj.get("maxPP").getAsInt());

        if (obj.has("damage")) {
            final var dmg = obj.get("damage").getAsJsonObject();
            moveBuilder.power(dmg.get("power").getAsInt());
            moveBuilder.accuracy(dmg.get("accuracy").getAsFloat());
            moveBuilder.contact(dmg.get("contact").getAsBoolean());
        }

        moveBuilder.priority(
                obj.has("priority")
                        ? obj.get("priority").getAsInt()
                        : 0);

        if (obj.has("subcategory")) {
            final List<SubCategory> list = new ArrayList<>();
            for (final var sub : obj.get("subcategory").getAsJsonArray().asList()) {
                SubCategory.stringToType(sub.getAsString()).ifPresent(list::add);
            }

            moveBuilder.subCategory(list);
        }

        return moveBuilder.build();
    }

    /**
     * @param moveID The unique ID of the move.
     * @return The given move from the internal previously generated map.
     */
    public static Move getMove(final String moveID) {
        return moveList.get(moveID);
    }

    /**
     * @param path A path relative to the resource folder of the JSON file.
     */
    public void initializeMoveList(final String path) {
        moveList.putAll(
                this.getJsonMap(path,
                        x -> x.get(ID_JSON_KEY).getAsString(),
                        ResourceManager::createMove));
    }

    /**
     * Frees up the internal move list loaded from reading the
     * provided JSON file. This doesn't guarantee that any
     * memory will be freed, as references to said moves
     * may still be remaining somewhere in the program.
     */
    public static synchronized void clearMoveList() {
        moveList = new LinkedHashMap<>();
    }

    /**
     * Gets the complete list of {@link Condition}s specified at the path relative
     * to the resource folder. The file has to be in JSON format,
     * and should follow this program's standard key-value pairs for specifying
     * a {@link Condition}'s characteristics and behaviour.
     *
     * @param path A path relative to the resource folder of the JSON file.
     */
    public static void initializeConditionList(final String path) {
        getResourceAsJsonElement(path).ifPresent(
                x -> x.getAsJsonArray().asList().stream()
                        .filter(JsonElement::isJsonObject)
                        .map(JsonElement::getAsJsonObject)
                        .forEach(ConditionDispenser::createCondition)
        );
    }

    /**
     * Parses a given {@link JsonObject} in order to create a {@link Pokemon}
     * object.
     *
     * @param obj The {@link JsonObject} to parse.
     * @return The newly created pokemon.
     */
    private Pokemon createPokemon(final JsonObject obj) {
        Objects.requireNonNull(obj);
        final var movesObj = obj.get("moves").getAsJsonObject();

        final var poke = new BasePokemon(
                obj.get(ID_JSON_KEY).getAsString(),
                obj.get("displayName").getAsString(),
                new MoveComponent(),
                new StatComponent(
                        /* Stats of the pokemon */
                        Map.of(
                                PokeStatType.HP_MAX, obj.get("hp").getAsInt(),
                                PokeStatType.ATK, obj.get("atk").getAsInt(),
                                PokeStatType.DEF, obj.get("def").getAsInt(),
                                PokeStatType.SPA, obj.get("spa").getAsInt(),
                                PokeStatType.SPD, obj.get("spd").getAsInt(),
                                PokeStatType.SPE, obj.get("spe").getAsInt(),
                                PokeStatType.EVA, 100,
                                PokeStatType.ACC, 100,
                                PokeStatType.WEIGHT, obj.get("weight").getAsInt()),
                        /*
                         * The pokemon's first type. Cannot be optional,
                         * therefore it should fail if it doesn't find the property.
                         */
                        PokeType.stringToType(obj.get("type_1").getAsString()),
                        /*
                         * The pokemon's second type. *CAN* be optional,
                         * therefore there should be a fallback if the property doesn't exist.
                         */
                        obj.has("type_2")
                                ? PokeType.stringToType(obj.get("type_2").getAsString())
                                : PokeType.NONE),
                new ConditionComponent(),
                new ItemComponent(obj.has("item")
                        ? obj.get("item").getAsString()
                        : "null"));
        /* Set all component's parent as this pokemon */
        poke.getMoveComponent().setPokeParent(poke);
        poke.getStatComponent().setPokeParent(poke);
        poke.getConditionComponent().setPokeParent(poke);
        poke.getItemComponent().setPokeParent(poke);
        /* Assign moveset */
        final Set<Move> moveset = new LinkedHashSet<>();
        for (int i = 0; i <= 4; i++) {
            moveset.add(getMove(movesObj
                    .get("move_".concat(String.valueOf(i)))
                    .getAsString()));
        }
        poke.getMoveComponent().setMoveSet(moveset);
        return poke;
    }

    /**
     * Gets the complete list of {@link Pokemon}s specified at the path relative
     * to the resource folder. The file has to be in JSON format and should
     * follow this program's standard key-value pairs for specifying a
     * {@link Pokemon}'s characteristics and stats.
     *
     * @param path A path relative to the resource folder of the JSON file.
     * @return A list of all the valid pokemons constructed from the given JSON file
     *         path.
     */
    public List<Pokemon> getPokemonList(final String path) {
        return this.getJsonList(path, this::createPokemon);
    }
}
