package it.unibo.biscia.utils.fileIO;

import it.unibo.biscia.utils.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Implementation of {@link FileIO}. This class is a wrapper of
 * {@link Preferences}. Property's are added in the xml file as {@code String}'s
 * instances. Property's values are read as {@code String}s and properly
 * converted to an instance of the class passed only if it is a primitive,
 * primitive's wrappers and enums class, otherwise UnsupportedOperationException
 * is threw. The conversion is made by the {@code parser HashMap} of this class.
 * 
 * @see FileIOImpl#add(String, Object)
 * @see FileIOImpl#get(String, Class)
 * @see FileIOImpl#getOrDefaultValue(String, Class, Object)
 *
 */
public class FileIOImpl implements FileIO {
    private final Preferences prefs;
    /**
     * This map's every {@code Class -> Function: String -> Class instance}. This is
     * made by the String to instance methods of certain classes, like
     * {@link Integer#valueOf(String)}. Supported classes are primitive classes and
     * thei respective wrappers, enums and {@link LocalDate}.
     * 
     */
    private final HashMap<Class<?>, Function<String, ?>> parser = new HashMap<>() {
        private static final long serialVersionUID = 1694870678496279326L;
        {
            this.put(boolean.class, Boolean::parseBoolean);
            this.put(int.class, Integer::parseInt);
            this.put(long.class, Long::parseLong);
            this.put(Boolean.class, Boolean::valueOf);
            this.put(Integer.class, Integer::valueOf);
            this.put(Long.class, Long::valueOf);
            this.put(Double.class, Double::valueOf);
            this.put(Float.class, Float::valueOf);
            this.put(String.class, String::valueOf);
            this.put(BigDecimal.class, BigDecimal::new);
            this.put(BigInteger.class, BigInteger::new);
            this.put(LocalDate.class, LocalDate::parse);
        }
    };

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object parse(final String argString, final Class param) {
        Function<String, ?> func = this.parser.get(param);
        if (func != null) {
            return func.apply(argString);
        }
        if (param.isEnum()) {
            return Enum.valueOf(param, argString);
        }
        throw new UnsupportedOperationException("Cannot parse string to " + param.getName());
    }

    public FileIOImpl(final String fileName) {
        prefs = Gdx.app.getPreferences(fileName);
    }

    @Override
    public final <T> void add(final String key, final T data) {
        prefs.putString(key, data.toString());
    }

    @Override
    public final void addAll(final Map<String, ?> data) {
        data.entrySet().stream().forEach(e -> this.add(e.getKey(), e.getValue()));
    }

    @Override
    public final void build() {
        prefs.flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> Pair<String, T> get(final String fieldName, final Class<T> dataType) {
        return new Pair<>(fieldName, (T) parse(prefs.getString(fieldName), dataType));
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> Pair<String, T> getOrDefaultValue(final String fieldName, final Class<T> dataType,
            final T defaultValue) {
        return new Pair<>(fieldName, (T) parse(prefs.getString(fieldName, defaultValue.toString()), dataType));
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> Map<String, T> getAllAs(final Class<T> dataType) {
        return prefs.get().entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> (T) parse(e.getValue().toString(), dataType)));
    }

}
