package it.unibo.biscia.utils.fileIO;

import it.unibo.biscia.utils.Pair;

import java.util.Map;

/**
 * Write and read properties to and from a xml file. This interface follows the
 * build pattern: After adding all the propertys via
 * {@link FileIO#add(String, Object)} they are written to the file via
 * {@link FileIO#build()}.
 *
 */
public interface FileIO {

    /**
     * Add property with its value.
     * 
     * @param <T>  Value's type
     * @param key  Property's name
     * @param data Property's value
     */
    <T> void add(String key, T data);

    /**
     * Add all the entries contained in the map
     * {@code property's name -> property's value}.
     * 
     * @param data A map containing all the entries to be added.
     */
    void addAll(Map<String, ?> data);

    /**
     * Write all the property added to the file.
     */
    void build();

    /**
     * Get a specific property with its value from the file.
     * 
     * @param <T>       Property's value's type. It depends on the {@code dataType}
     *                  field passed.
     * @param fieldName Property's name
     * @param dataType  Property's value class.
     * @return A Pair containing property's name and its value
     * 
     * @see Pair
     */
    <T> Pair<String, T> get(String fieldName, Class<T> dataType);

    /**
     * Get a specific property with its value from the file. In case property's name
     * {@code fieldName} wasn't found it is returned {@code defaultValue}
     * 
     * @param <T>          Property's value's type. It depends on the
     *                     {@code dataType} field passed.
     * @param fieldName    Property's name
     * @param dataType     Property's value class.
     * @param defaultValue The default value to be returned
     * @return A Pair containing property's name and its value or
     *         {@code defaultValue}
     */
    <T> Pair<String, T> getOrDefaultValue(String fieldName, Class<T> dataType, T defaultValue);

    /**
     * Returns every property with its value contained in the file.
     * 
     * @param <T>      Property's value's type. It depends on the {@code dataType}
     *                 field passed.
     * @param dataType Property's value class.
     * @return A map containing all the property and their respective values in the
     *         file.
     */
    <T> Map<String, T> getAllAs(Class<T> dataType);

}
