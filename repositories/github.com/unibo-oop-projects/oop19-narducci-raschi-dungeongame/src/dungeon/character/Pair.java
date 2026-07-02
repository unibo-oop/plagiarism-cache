package dungeon.character;

import java.util.Map;

public final class Pair<K, V> implements Map.Entry<K, V> {
  private final K key;
  private V value;

  /**
   * Instantiates a new pair.
   *
   * @param key the key
   * @param value the value
   */
  public Pair(final K key, final V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public V setValue(final V value) {
    V prev = this.value;
    this.value = value;
    return prev;
  }
}

