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
        Item[] temp = (Item[]) new Object[length];
        int j = 0;

        for (int i = 0; i < RandQueue.length; i++) {
            if (RandQueue[i] != null) {
                temp[j] = RandQueue[i];
                j++;
            }
        }
        last = noOfEle;
        RandQueue = temp;
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

        noOfEle--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandQueueIterator();
    }

    private class RandQueueIterator implements Iterator<Item> {
        private int[] shuffleArray;
        private int shuffleIter;
        private int iter;

        public RandQueueIterator() {
            shuffleArray = new int[RandQueue.length];
            for (int i = 0; i < RandQueue.length; i++)
                shuffleArray[i] = i;
            StdRandom.shuffle(shuffleArray);
            iter = 0;
            shuffleIter = 0;
        }

        public boolean hasNext() {
            return (iter < noOfEle);
        }

        public void remove() {
            /* */
            throw new java.lang.UnsupportedOperationException("Unsupported operation");
        }

        public Item next() {
            Item item;
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("Deque is empty, no such element");
            }

            while (RandQueue[shuffleArray[shuffleIter]] == null) {
                shuffleIter++;
            }

            item = RandQueue[shuffleArray[shuffleIter]];
            shuffleIter++;
            iter++;

            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        rq.enqueue("10");
        rq.enqueue("20");
        rq.enqueue("30");
        rq.enqueue("40");
        rq.enqueue("50");
        rq.enqueue("60");

        System.out.println(rq.dequeue());

        Iterator<String> iter = rq.iterator();
        System.out.println("Iterator First\n");

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Iterator<String> iter1 = rq.iterator();

        System.out.println("Iterator Second\n");

        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
    }
}
