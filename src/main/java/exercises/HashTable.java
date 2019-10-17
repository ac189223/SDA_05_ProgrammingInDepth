package exercises;

class Entry {                                                       // object in the chain
    Object key;
    Object value;
    Entry next;

    public Entry(Object key, Object value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class HashTable {
    private static final int SIZE = 1024;                         // FINAL are checked by compiler regarding trying to change
    private Entry[] table = new Entry[SIZE];                      // Entry do not need a list

    public void insert(Object key, Object value) throws IllegalAccessException {
        if (key == null) {
            throw new IllegalAccessException("null not allowed");
        }
        int hc = key.hashCode();
        int index = hc % SIZE;
        Entry e = new Entry(key, value, null);              // Insert new Entry object into the "table" array
        if (table[index] == null) {
            table[index] = e;                                    // Start a chain at this index
        } else {
            e.next = table[index];                               // Add to the beginning of chain
            table[index] = e;
        }
    }

    public Entry lookup(Object key) {
        Entry e = table[key.hashCode() % SIZE];                 // Locate beginning of right chain
        while (e != null) {                                     // Until the end of the chain
            if (e.key == key)                                   // If an entry has the right key
                return e;                                       // Found
            else                                                // If key is different
                e = e.next;                                     // Go to next one
        }
        return null;                                            // Null if chain has ended and we did not found the key
    }
}
