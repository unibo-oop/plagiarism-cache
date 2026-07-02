package it.unibo.monoopoly.utils.api;

import java.util.List;
import java.util.Map;

/**
 * Represent a tool to translate a Json file in a collection of a given Java
 * object.
 * 
 * @param <T> the class of the elements that the json file will be converted to.
 */
public interface JsonConverter<T> {

    /**
     * Convert a json file in a list of type T.
     * 
     * @param path the path of the file to convert
     * @return the converted list.
     */
    List<T> jsonToList(String path);

    /**
     * Convert a json file in a list of list of type T.
     * 
     * @param path the path of the file to convert.
     * @return the converted list of list.
     */
    List<List<T>> jsonToListOfList(String path);

    /**
     * Convert a json file in a Map of type Integer, T.
     * 
     * @param path the path of the file to convert.
     * @return the converted Map.
     */
    Map<Integer, T> jsonToMap(String path);

}
