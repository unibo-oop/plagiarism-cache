package it.tbt.controller.resources;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Handle Serialization and Deserialization of Optiona<T> objects.
 * @param <T>
 * @see https://stackoverflow.com/a/43727852 (CC BY-SA 4,0)
 */
public final class OptionalHandler<T> implements JsonSerializer<Optional<T>>, JsonDeserializer<Optional<T>> {

    @Override
    @SuppressFBWarnings(
        value = "BC",
        justification = "ParameterizedType extends the interface Type"
    )
    public Optional<T> deserialize(
        final JsonElement json,
        final Type typeOfT,
        final JsonDeserializationContext context
    ) {
        final T value = context.deserialize(
            json,
            ((ParameterizedType) typeOfT).getActualTypeArguments()[0]
        );
        return Optional.ofNullable(value);
    }

    @Override
    public JsonElement serialize(
        final Optional<T> src,
        final Type typeOfSrc,
        final JsonSerializationContext context
    ) {
        return context.serialize(src.orElse(null));
    }
}
