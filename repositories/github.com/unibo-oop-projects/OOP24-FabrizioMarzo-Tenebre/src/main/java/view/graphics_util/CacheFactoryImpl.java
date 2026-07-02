package view.graphics_util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.awt.image.BufferedImage;

/**
 * Implementation of the CacheFactory interface providing concrete cache
 * instances.
 */
public class CacheFactoryImpl implements CacheFactory {

    /**
     * Generic cache implementation backed by a Map.
     * 
     * @param <K> the type of keys maintained by this cache
     * @param <V> the type of cached values
     */
    private class CacheTemplate<K, V> implements Cache<K, V> {

        private final Map<K, V> mapCache;

        /**
         * Constructs a cache backed by the given Map supplier.
         * 
         * @param mapSupplier a supplier providing the Map instance to back the cache
         */
        protected CacheTemplate(final Supplier<Map<K, V>> mapSupplier) {
            this.mapCache = mapSupplier.get();
        }

        @Override
        public boolean contains(final K key) {
            return mapCache.containsKey(key);
        }

        @Override
        public V get(final K key) {
            return mapCache.get(key);
        }

        @Override
        public void put(final K key, final V value) {
            mapCache.put(key, value);
        }

    }

    /**
     * Creates and returns a new cache for storing images, mapping {@link String}
     * keys to {@link BufferedImage} values.
     * 
     * @return a new {@code Cache<String, BufferedImage>} instance backed by a
     *         {@link HashMap}
     */
    @Override
    public Cache<String, BufferedImage> imageCache() {
        return new CacheTemplate<>(HashMap::new);
    }

}
