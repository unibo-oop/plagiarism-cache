package it.oop.project.util;

import java.net.URL;

/**
 * {@inheritDoc}
 *
 */
final public class ResourceLoaderImpl implements ResourceLoader {

    /**
     * {@inheritDoc}
     */
    @Override
    public URL load(String path) {
        return ResourceLoaderImpl.class.getClassLoader().getResource(path);
    }

}
