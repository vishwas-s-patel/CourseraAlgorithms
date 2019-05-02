/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] RandQueue;
    private int noOfEle = 0;

    public RandomizedQueue() {              // construct an empty randomized queue
        RandQueue = (Item[]) new Object[noOfEle];
    }

    public boolean isEmpty() {                 // is the randomized queue empty?
        return (noOfEle == 0);
    }

    public int size() {                       // return the number of items on the randomized queue
        return noOfEle;
    }

    public void enqueue(Item item) {           // add the item
        if ()
    }

    public Item dequeue() {                    // remove and return a random item
    }

    public Item sample() {                     // return a random item (but do not remove it)
    }

    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
    }

    public static void main(String[] args) {   // unit testing (optional)
    }

}
