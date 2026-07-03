package org.gitgud.model.utils;

/**
 * A generic builder.
 *
 * @param <T>
 *            the type of the object to build
 */
public interface Builder<T> {

    /**
     * Execute the build operation.
     *
     * @return the object built
     */
    T build();

}
