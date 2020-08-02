public class MyHashtableOpen<K, V> implements KWHashMap<K, V>{
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
    private int numDeletes;
    /**
     * A special object to indicate that an entry has been deleted
     */
    private final Entry<K, V> DELETED = new Entry<>(null,null);

    /**
     * Prime number for hash2
     */
    private int prime;

    /**
     * Basic constructor
     */
    public MyHashtableOpen() {
        table = new Entry[START_CAPACITY];
        prime = findPrimeNumber();
    }

    /**
     * Finds either the target key of the first empty slot in the search chain
     * using double hashing.
     * @pre The table is not full.
     * @param key The of the target object
     * @return The position of the target or the first empty slot if the target
     *         is not in the table.
     */
    private int find(Object key) {
        int i = 0;
        int index = (hash1(key) + (i * hash2(key))) % table.length;
        while ((table[index]) != null && (!key.equals(table[index].getKey()))) {
            index = (hash1(key) + (i * hash2(key))) % table.length;
        }
        return index;
    }

    /**
     * Helper hash1 function to use double hashing
     * @param key The of the target object
     * @return key % length
     */
    private int hash1(Object key) {
        int i = key.hashCode() % table.length;
        if (i < 0)
            i += table.length;
        return i;
    }

    /**
     * Helper hash2 function to use double hashing
     * @param key The of the target object
     * @return primeNumber - key % primeNumber
     */
    private int hash2(Object key) {
        return prime - key.hashCode() % prime;
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
        numDeletes = 0;
        for (Entry<K, V> kvEntry : oldTable) {
            if ((kvEntry != null) && (kvEntry != DELETED)) {
                //Insert entry in expanded table
                put(kvEntry.getKey(), kvEntry.getValue());
            }
        }
    }

    private int findPrimeNumber() {
        for (int i = table.length - 1; i >= 1; i--) {
            boolean divisible = false;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0) {
                    divisible = true;
                    break;
                }
            if (!divisible)
                return i;
        }
        return 3;
    }
}
