package view.graphics_util;

/**
 * Generic cache interface defining basic cache operations.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public interface Cache<K, V> {

    /**
     * Checks if the cache contains a value mapped to the specified key.
     *
     * @param key the key whose presence in this cache is to be tested
     * @return {@code true} if the cache contains a mapping for the specified key,
     *         {@code false} otherwise
     */
    boolean contains(final K key);

    /**
     * Retrieves the value mapped to the specified key from the cache.
     *
     * @param key the key whose associated value is to be returned
     * @return the value mapped to the specified key, or {@code null} if no mapping
     *         exists
     */
    V get(final K key);

    /**
     * Inserts a key-value mapping into the cache.
     * If the cache previously contained a mapping for the key, the old value is
     * replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(final K key, final V value);
}
