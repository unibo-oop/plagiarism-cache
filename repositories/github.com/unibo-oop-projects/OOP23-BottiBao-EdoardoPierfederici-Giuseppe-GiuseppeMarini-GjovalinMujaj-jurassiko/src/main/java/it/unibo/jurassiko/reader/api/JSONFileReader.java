package it.unibo.jurassiko.reader.api;

/**
 * Interface to parse data from the JSON file of a generic type.
 * 
 * @param <T> The type of object to be read
 */
public interface JSONFileReader<T> {

    /**
     * Deserializes data from the given JSON file.
     * 
     * @param filePath path of the file to read
     * @return the deserialized objects
     * @throws IllegalStateException if the file is not parsed correctly
     */
    T readFileData(String filePath);

}
