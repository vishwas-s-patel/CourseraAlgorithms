/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int noOfElements;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {                          // construct an empty deque
        noOfElements = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return first == null;
    }

    public int size() {                       // return the number of items on the deque
        return noOfElements;
    }

    public void addFirst(Item item) {        // add the item to the front
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Item cannot be null");
        }
        Node temp = new Node();
        temp.item = item;

        if (first == null && last == null) {
            temp.next = null;
            temp.prev = null;
            last = temp;
            first = temp;
        }
        else if (first != null && last != null) {
            temp.next = first;
            temp.prev = null;
            first.prev = temp;
            first = temp;
        }
        else {
            throw new java.lang.IllegalArgumentException("first or last is corrupted");
        }
        noOfElements++;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Item cannot be null");
        }
        Node temp = new Node();
        temp.item = item;

        if (first == null && last == null) {
            temp.next = null;
            temp.prev = null;
            first = temp;
            last = temp;
        }
        else if (first != null && last != null) {
            temp.next = null;
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        else {
            throw new java.lang.IllegalArgumentException("first or last is corrupted");
        }

        noOfElements++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }

        Item res = first.item;

        if (first.equals(last)) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }

        noOfElements--;

        return res;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }

        Item res = last.item;

        if (first.equals(last)) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }

        noOfElements--;

        return res;
    }

    public Iterator<Item> iterator() {          // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* */
            throw new java.lang.UnsupportedOperationException("Unsupported operation");
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("Deque is empty, no such element");
            }

            Item temp;
            temp = current.item;

            current = current.next;
            return temp;
        }
    }

    public static void main(String[] args) {  // unit testing (optional)
        Deque<String> de = new Deque<String>();

        System.out.print("\n");
        de.addFirst("10");
        de.addLast("20");
        de.addLast("30");
        System.out.print("\n");

        Iterator<String> iter = de.iterator();

        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        System.out.print("\n");

        StdOut.print(de.removeLast());
        System.out.print("\n");

        StdOut.print(de.removeFirst());
        System.out.print("\n");
    }
}
