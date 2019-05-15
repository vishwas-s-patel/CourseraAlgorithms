/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] RandQueue;
    private int noOfELe;
    private int first = 0;
    private int last = 0;

    public RandomizedQueue() {
        RandQueue = (Item[]) new Object[2];
        noOfELe = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return (noOfELe == 0);
    }

    public int size() {
        return noOfELe;
    }

    public void resize(int length) {
        Item[] temp = (Item[]) Object(length);
        int j = 0;

        for (int i = 0; i < RandQueue.length; i++) {
            if (RandQueue[i] != null) {
                temp[j] = RandQueue[i];
                j++;
            }
            last = noOfELe;
            RandQueue = temp;
        }
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item cannot be null");
        }

        if (noOfELe == RandQueue.length) {
            resize(2 * RandQueue.length);
        }

        RandQueue[last] = item;
        last++;

        if (last == RandQueue.length)
            last = 0;

        noOfELe++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Randomized Queue is empty");

        int randEleIndex;
        Item item;
        randEleIndex = StdRandom.uniform(noOfELe);

        item = RandQueue[randEleIndex];
        RandQueue[randEleIndex] = null;

        if (noOfELe > 0 && (noOfELe == RandQueue.length / 4)) {
            resize(RandQueue.length / 2);
        }
        return item;
    }

    public Iterator<Item> ierator() {
        return new RandQueueIterator();
    }

    private class RandQueueIterator implements Iterator<Item> {
        private int[] shuffleArray;

        public RandQueueIterator() {
            shuffleArray = new int[noOfELe];
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

    public void main() {

    }
}
