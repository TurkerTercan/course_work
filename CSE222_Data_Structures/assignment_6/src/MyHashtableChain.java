public class MyHashtableChain<K extends Comparable<K>, V> implements KWHashMap<K, V> {
    private static class Entry<K extends Comparable<K>, V > implements Comparable<Entry<K, V>> {
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

        @Override
        public int compareTo(Entry<K, V> o) {
            return key.compareTo(o.key);
        }
    }
    /** The table */
    private BinarySearchTree<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    /**
     * Basic constructor
     */
    public MyHashtableChain() { table = new BinarySearchTree[CAPACACITY]; }

    /**
     * Method get for class MyHashtableChain
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;

        //Search the BinarySearchTree at table[index] to find the key.
        return getValue(table[index], key);
    }

    /**
     * Helper method to find V in BinaryTree
     * @param search starting root
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    private V getValue(BinaryTree<Entry<K, V>> search, Object key) {
        if (search == null)
            return null;
        if (search.getData().getKey().compareTo((K) key) == 0) {
            return search.getData().getValue();
        }
        else if (search.getData().getKey().compareTo((K) key) > 0) {
            return getValue(search.getRightSubtree(), key);
        }
        else
            return getValue(search.getLeftSubtree(), key);
    }

    /**
     * Method put for class HashtableChain
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if found;
     *         otherwise, null
     */
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            //Create a new linked list at table[index].
            table[index] = new BinarySearchTree<>();
        }

        //Search the list at table[index] to find the key
        V temp = searchAndPut(table[index], key, value);
        if (temp != null)
            return temp;
        table[index].add(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    /**
     * Helper method to search through BinaryTree
     * @param search starting tree
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if found;
     *         otherwise, null
     */
    private V searchAndPut(BinaryTree<Entry<K, V>> search, K key, V value) {
        if (search == null || search.root == null)
            return null;
        //If the search is successful, replace the old value.
        if (search.getData().getKey().compareTo(key) == 0) {
            //Replace value for this key
            V oldVal = search.getData().getValue();
            search.getData().setValue(value);
            return oldVal;
        }
        else if (search.getData().getKey().compareTo(key) > 0) {
            return searchAndPut(search.getRightSubtree(), key, value);
        }
        else {
            return searchAndPut(search.getLeftSubtree(), key, value);
        }
    }

    /**
     * Expands table size when numKeys exceed LOAD_THRESHOLD * table.length
     * @post The size of the table is doubled and is an odd integer.
     *       Each entry from the original table is
     *       reinserted into the expanded table.
     *       The value of numKeys is reset to the number of items
     *       actually inserted;
     */
    public void rehash() {
        //Save a reference to oldTable;
        BinarySearchTree<Entry<K, V>>[] oldTable = table;
        table = new BinarySearchTree[2 * oldTable.length + 1];
        numKeys = 0;
        //Reinsert all items in oldTable into expanded table;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                helperRehash(oldTable[i]);
            }
        }
    }

    /**
     * Helper recursive method to put all values oldTable to expanded table
     * @param search local root
     */
    private void helperRehash(BinaryTree<Entry<K, V>> search) {
        if (search == null)
            return;
        helperRehash(search.getLeftSubtree());
        put(search.getData().getKey(), search.getData().getValue());
        helperRehash(search.getRightSubtree());
    }

    /**
     * Returns true if this table contains no key-value mappings
     * @return if table is empty, true; otherwise, false
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /**
     * Removes the mapping for this key from this table if it is present
     * (optional operation). Returns the previous value associated with the
     * specified key, or null if there was no mapping
     *
     * @param key specified key
     * @return the previous value associated with the specified key
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            //Key is not in the table
            return null;
        }
        V val = get(key);
        if (val == null) {
            return null;
        }

        table[index].remove(new Entry<>((K) key, val));
        numKeys--;
        return val;
    }

    /**
     * Returns the size of the table
     *
     * @return the size of the table
     */
    @Override
    public int size() {
        return numKeys;
    }
}
