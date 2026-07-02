package it.unibo.jurassiko.reader.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.jurassiko.reader.api.JSONFileReader;

/**
 * Abstract class providing a common implementation to read data from a JSON
 * file with different types of objects and producing and Set of elements.
 * 
 * @param <T> The type of object to be read
 */
public class BoardDataReader<T> implements JSONFileReader<Set<T>> {

    private final ObjectMapper mapper;
    private final Class<T> targetClass;

    /**
     * Creates a JSONFileReader for the given class type.
     * 
     * @param targetClass The class of the objects to read used by Jackson parser
     */
    public BoardDataReader(final Class<T> targetClass) {
        this.mapper = new ObjectMapper();
        this.targetClass = targetClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<T> readFileData(final String filePath) {
        Set<T> data;
        // Generic type contained by the Set to deserialize
        final JavaType type = mapper.getTypeFactory().constructCollectionType(Set.class, targetClass);

        try (InputStream in = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath))) {
            data = mapper.readValue(in, type);
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to read " + filePath + " file", e);
        }

        return Set.copyOf(data);
    }

}
