package lectures;

class Node {                                                // Elements of our list
    int elem;                                               // Identification
    Node next;                                              // Pointing to next element

    // Constructor
    Node(int elem, Node next) {
        this.elem = elem;
        this.next = next;
    }
}

public class LinkedList {
    Node first = null;

    // Add new node to the list
    public void add(int elem) {
        if (first == null)                                  // LinkedList is empty
            first = new Node(elem, null);             // Create first element
        else {
            Node current = first;                           // Starting from first
            while (current.next != null)                    // If there exist next one
                current = current.next;                     // Then go to successor
            current.next = new Node(elem, null);      // Add new one after "no successor" element
        }
    }

    // Get element stored under index in the list
    public int get(int index) {                                 // 2 cases
        if (first == null) {                                    // Case 1: zero elements
                                                                // throw exception
            throw new IndexOutOfBoundsException("list is empty");
        } else {                                                // Case 2: at least one element
            int counnter = 0;                                   // It counts at which element we are
            Node current = first;                               // Starting from first
            while (current.next != null && counnter < index) {  // If there exist next one && desired index is not reached
                current = current.next;                         // Then go to successor
                counnter++;                                     // Increase counter
            }
            if (counnter == index) {                            // If we reached desired index
                return current.elem;                            // Return the element of current node
            } else {
                                                                // If desired index is not reachable
                                                                // throw exception
                throw new IndexOutOfBoundsException("index greater then the lenght of the list");
            }
        }
    }

    // Get size of the list
    public int size() {                                         // 2 cases
        if (first == null) {                                    // Case 1: zero elements
            return 0;
        } else {                                                // Case 2: at least one element
            int counter = 0;                                    // It counts at which element we are
            Node current = first;                               // Starting from first
            while (current.next != null) {                      // If there exist next one
                current = current.next;                         // Then go to successor
                counter++;                                      // Increase counter
            }
            return counter + 1;                                 // Return the number of elements (counted from 0)
        }
    }
}

