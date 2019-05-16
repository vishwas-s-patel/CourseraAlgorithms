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
    private int last;

    public RandomizedQueue() {
        RandQueue = (Item[]) new Object[2];
        noOfEle = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return (noOfEle == 0);
    }

    public int size() {
        return noOfEle;
    }

    public void resize(int length) {
        Item[] temp = (Item[]) Object(length);
        int j = 0;

        for (int i = 0; i < RandQueue.length; i++) {
            if (RandQueue[i] != null) {
                temp[j] = RandQueue[i];
                j++;
            }
            last = noOfEle;
            RandQueue = temp;
        }
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item cannot be null");
        }

        if (noOfEle == RandQueue.length) {
            resize(2 * RandQueue.length);
        }

        RandQueue[last] = item;
        last++;

        if (last == RandQueue.length)
            last = 0;

        noOfEle++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Randomized Queue is empty");

        int randEleIndex;
        Item item;
        randEleIndex = StdRandom.uniform(noOfEle);

        item = RandQueue[randEleIndex];
        RandQueue[randEleIndex] = null;

        if (noOfEle > 0 && (noOfEle == RandQueue.length / 4)) {
            resize(RandQueue.length / 2);
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandQueueIterator();
    }

    private class RandQueueIterator implements Iterator<Item> {
        private int[] shuffleArray;

        public RandQueueIterator() {
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

    public void main() {

    }
}
