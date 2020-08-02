import java.util.LinkedList;

public class HashtableChain<K, V> implements KWHashMap<K, V>{
    //Insert inner class Entry<K, V> here.
    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    /**
     * Basic Constructor
     */
    public HashtableChain() {
        table = new LinkedList[CAPACITY];
    }

    /**
     * Method get for class HashtableChain.
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V get(Object key){
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null; //key is not in the table.

        //Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }
        // assert: key is not in the table.
        return null;
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
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null) {
            //Create a new linked list at table[index].
            table[index] = new LinkedList<>();
        }

        //Search the list at table[index] to find the key
        for (Entry<K, V> nextItem : table[index]) {
            //If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                //Replace value for this key.
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }

        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
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
        //Save a reference to oldTable.
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length +1];
        numKeys = 0;
        //Reinsert all items in oldTable into expanded table
        for (LinkedList<Entry<K, V>> entries : oldTable) {
            if (entries != null) {
                for (Entry<K, V> entry : entries)
                    put(entry.getKey(), entry.getValue());
            }
        }
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
        for (int i = 0; i < table[index].size(); i++) {
            if (table[index].get(i).getKey().equals(key)) {
                Entry<K, V> val= table[index].remove(i);
                numKeys--;
                if (table[index].size() == 0) {
                    table[index] = null;
                }
                return val.getValue();
            }
        }
        return null;
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
