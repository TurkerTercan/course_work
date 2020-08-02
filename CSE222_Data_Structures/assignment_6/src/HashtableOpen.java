public class HashtableOpen<K, V> implements KWHashMap<K, V>{
    //Data fields
    /**
     * The hash table array
     */
    private Entry<K, V>[] table;
    /**
     * The initial capacity
     */
    private static final int START_CAPACITY = 101;
    /**
     * The maximum load factor
     */
    private double LOAD_THRESHOLD = 0.75;
    /**
     * The number of keys in the table excluding keys that were deleted
     */
    private int numKeys;
    /**
     * The number of deleted keys
     */
    private int numDeletes = 1;
    /**
     * A special object to indicate that an entry has been deleted
     */
    private final Entry<K, V> DELETED = new Entry<>(null,null);

    /**
     * Basic constructor
     */
    public HashtableOpen() {
        table = new Entry[START_CAPACITY];
    }

    /**
     * Finds either the target key of the first empty slot in the search chain
     * using linear probing.
     * @pre The table is not full.
     * @param key The of the target object
     * @return The position of the target or the first empty slot if the target
     *         is not in the table.
     */
    private int find(Object key) {
        //Calculate the starting index
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        //Increment index until an empty slot is reached
        //or the key is found
        while((table[index] != null) && (!key.equals(table[index].getKey()))) {
            index++;
            //Check for wraparound
            if (index >= table.length)
                index = 0;
        }
        return index;
    }

    /**
     * Method get for class HashtableOpen
     * @param key The key being sought
     * @return the value associated with this key if found; otherwise, null
     */
    @Override
    public V get(Object key) {
        //Find the first table element that is empty
        //or the table element that contains the key.
        int index = find(key);
        //If search is successful, return the value.
        if (table[index] != null)
            return table[index].getValue();
        else
            return null; //Key not found
    }

    /**
     * Method put for class HashtableOpen
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed. If the LOAD_THRESHOLD
     *       is exceeded, the table is expanded
     * @param key specified key
     * @param value specified value with the specified key
     * @return Old value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V put(K key, V value) {
        //Find the first table element that is empty
        //or the table element that contains the key
        int index = find(key);

        //If an empty element was found, insert new entry
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            //Check whether rehash is needed.
            double loadFactor = (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        // assert: table element that contains the was found.
        // Replace value for this key.
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }

    /**
     * Returns true if this table contains no key-value mappings
     *
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
        int index = find(key);
        if (table[index] == null)
            return null;
        V oldVal = table[index].getValue();
        table[index] = DELETED;
        numKeys--;
        numDeletes++;
        return oldVal;
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

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * @post The size of the table is doubled and is an odd integer.
     *       Each nondeleted entry from the original table is
     *       reinserted into the expanded table.
     *       The value of numKeys is reset to the number of items
     *       actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        //Save a reference to oldTable
        Entry<K, V>[] oldTable = table;
        //Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];

        //Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 1;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                //Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }
}
