package it.oop.project.util;

import java.net.URL;

/**
 * A class to load resources from it.oop.project.resources package.
 *
 */
public interface ResourceLoader {

    /**
     * Loads an URL for specified path in it.oop.project.resources package.
     * 
     * @return an URL for specified path in it.oop.project.resources package.
     */
    URL load(String path);

}
