package it.unibo.monoopoly.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import it.unibo.monoopoly.utils.api.JsonConverter;

/**
 * Generic implementation of interface {@link JsonConverter}.
 * 
 * @param <T> the class that the json file will be converted to.
 */
public class JsonConverterImpl<T> implements JsonConverter<T> {

    private final Class<T> type;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Create a converter for the given class.
     * 
     * @param type the class that the json file will be converted to.
     */
    public JsonConverterImpl(final Class<T> type) {
        this.type = type;
        this.mapper.registerModule(new Jdk8Module());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> jsonToList(final String path) {
        final List<T> out;
        try (InputStream fileJson = ClassLoader.getSystemResourceAsStream(path)) {
            final JavaType outType = mapper.getTypeFactory()
                    .constructCollectionLikeType(List.class, type);
            out = mapper.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file", e);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<T>> jsonToListOfList(final String path) {
        final List<List<T>> out;
        try (InputStream fileJson = ClassLoader.getSystemResourceAsStream(path)) {
            final JavaType outType = mapper.getTypeFactory()
                    .constructCollectionType(List.class,
                            mapper.getTypeFactory().constructCollectionType(List.class, type));
            out = mapper.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file to List of List", e);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, T> jsonToMap(final String path) {
        final Map<Integer, T> out;
        try (InputStream fileJson = ClassLoader.getSystemResourceAsStream(path)) {
            final JavaType outType = mapper.getTypeFactory()
                    .constructMapType(Map.class, Integer.class, type);

            out = mapper.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file to Map", e);
        }
        return out;
    }

}
