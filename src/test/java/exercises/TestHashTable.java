package exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHashTable {
    @Test
    public void testLookup() throws IllegalAccessException {
        HashTable hashTable = new HashTable();
        hashTable.insert("Ping", "Pong");
        String value = (String) hashTable.lookup("Ping");
        assertEquals("Pong", value);
    }
}
