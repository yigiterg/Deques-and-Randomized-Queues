import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue <Item> implements Iterable <Item> {

    private Item[] queue;
    private int size = 0;
    private int capacity;
    private int currentIndex = 0;
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        capacity = 4;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){return size == 0; }

    // return the number of items on the randomized queue
    public int size(){ return size; }

    // add the item
    public void enqueue(Item item){
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == capacity) {
            resize(2 * capacity);
        } else if (size == (double) capacity/4 ) {
            resize( capacity / 2);
        }
        queue[currentIndex++] = item;
        size++;
    }
    private void resize(int updatedSize) {
            capacity = updatedSize;
            int j = 0;
            Item[] item = (Item[]) new Object[capacity];
            for (int i = 0; i < queue.length; i++) {
                if (queue[i] != null) {
                    item[j++] = queue[i];
                }
            }
            currentIndex = j;
            queue = item;

    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(0,queue.length);
        while (queue[random] ==  null) {
            random = StdRandom.uniform(0,queue.length);
        }
        Item returner = queue[random];
        queue[random] = null;
        size--;
        return returner;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(0,queue.length);
        while (queue[random] ==  null) {
            random = StdRandom.uniform(0,queue.length);
        }
        return queue[random];
    }


    private class ArrayIterator implements Iterator <Item> {
        private final int[] randomArray = new int[size];
        private int j = 0;
        private int index = 0;
        public ArrayIterator() {
            for (int i = 0; i < queue.length; i++) {
                if (queue[i] != null) {
                    randomArray[j++] = i;
                }
            }
            StdRandom.shuffle(randomArray);
        }


        @Override
        public boolean hasNext() {
            return index != size &&queue[randomArray[index]] != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return queue[randomArray[index++]];
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
     /*   RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(14);
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.size());
        randomizedQueue.enqueue(33);
        randomizedQueue.enqueue(35);
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.dequeue());
        /*Iterator <Integer> myIterator = randomizedQueue.iterator();
        while(myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }*/
    }

}
