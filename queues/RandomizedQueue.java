/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] RandQueue;

    private int noOfEle;

    public RandomizedQueue() {              // construct an empty randomized queue
        RandQueue = (Item[]) new Object[2];
        noOfEle = 0;
    }

    public boolean isEmpty() {                 // is the randomized queue empty?
        return (noOfEle == 0);
    }

    public int size() {                       // return the number of items on the randomized queue
        return noOfEle;
    }

    private void resize(int length) {
        RandQueue = ()
    }

    public void enqueue(Item item) {           // add the item
        if (item == null)
            throw new java.lang.IllegalArgumentException("item cannot be null");

        if (noOfEle == RandQueue.length) {
            resize(2 * RandQueue.length);
        }
        RandQueue[noOfEle] = item;
        noOfEle++;
    }

    public Item dequeue() {                    // remove and return a random item
        int random;
        Item res;
        Node temp;

        if (noOfEle == 0) {
            throw new java.util.NoSuchElementException("Randomized Queue is empty");
        }
        random = StdRandom.uniform(noOfEle);
        Node iter = first;
        temp = first;
        while ((random > 0) && (iter != null)) {
            random--;
            temp = iter;
            iter = iter.next;
        }
        res = iter.item;

        if (iter.next == null) {
            temp.next = null;
            iter = null;
        }
        else {
            temp.next = iter.next;
            iter = null;
        }
    }

    public Item sample() {                     // return a random item (but do not remove it)
        int random;

        if (noOfEle == 0) {
            throw new java.util.NoSuchElementException("Randomized Queue is empty");
        }
        random = StdRandom.uniform(noOfEle);
        Node temp = first;
        while ((random > 0) && (temp != null)) {
            random--;
            temp = temp.next;
        }
        return temp.item;
    }

    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] shuffleArray;

        public RandomizedQueueIterator() {
            shuffleArray = new int[noOfEle];
            StdRandom.shuffle(shuffleArray);
        }

        public boolean hasNext() {

        }

        public Item next() {

        }

        public void remove() {
            /* */
            throw new java.lang.UnsupportedOperationException("Unsupported operation");
        }
    }

    public static void main(String[] args) {   // unit testing (optional)
    }

}

