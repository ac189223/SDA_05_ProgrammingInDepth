package exercises;

class Node {                                                // Elements of our list
    int elem;                                               // Identification
    Node next;                                              // Pointing to next element

    Node(int elem, Node next) {
        this.elem = elem;
        this.next = next;
    }
}

public class LinkedList {
    Node first = null;

    public void add(int elem) {
        if (first == null) {
            if (first == null)                                  // LinkedList is empty
                first = new Node(elem, null);             // Create first element
            else {
                Node current = first;                           // Starting from first
                while (current.next != null)                    // If there exist next one
                    current = current.next;                     // Then go to successor
                current.next = new Node(elem, null);      // Add new one after "no successor" element
            }
        }
    }
}

