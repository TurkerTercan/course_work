public interface KWHashMap<K, V> {
    /**
     * Returns the value associated with the specified key.
     * Returns null if the key is not present
     * @param key specified key
     * @return value associated with jey
     */
    V get(Object key);

    /**
     * Returns true if this table contains no key-value mappings
     * @return if table is empty, true; otherwise, false
     */
    boolean isEmpty();

    /**
     * Associates the specified value with the specified key.
     * Returns the previous value associated with the specified key, or null
     * if there was no mapping for the key
     * @param key specified key
     * @param value specified value with the specified key
     * @return Returns the previous value associated with the specified key
     */
    V put(K key, V value);

    /**
     * Removes the mapping for this key from this table if it is present
     * (optional operation). Returns the previous value associated with the
     * specified key, or null if there was no mapping
     * @param key specified key
     * @return the previous value associated with the specified key
     */
    V remove(Object key);

    /**
     * Returns the size of the table
     * @return the size of the table
     */
    int size();

    /**
     *
     * @param <K> key
     * @param <V> value
     */
    class Entry<K, V> {
        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }
}
