import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements  Iterable<Item> {
    // have tail so that have constant worst case time
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;

        private Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    public Deque() {
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the last
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = new Node(item);
            tail = head;
        } else {
            Node node = new Node(item);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node(item);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() { // Without this if block line 74 will give null pointer exception.
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            Node node = head;
            head = null;
            size--;
            return node.item;
        } else {
            head.next.prev = null;
            Node returner = head;
            head = head.next;
            size--;
            return returner.item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) { // Without this if block line 97 will give null pointer exception.
            Node node = head;
            head = null;
            size--;
            return node.item;
        }
        Node node = tail;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        return node.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                if (node == null) {
                    throw new NoSuchElementException();
                }
                Node node1 = node;
                node = node.next;
                return node1.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    // return an iterator over items in order from front to back


    // unit testing (required)
    public static void main(String[] args) {
       /* Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.removeFirst() ;
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
        deque.addFirst(5);
        System.out.println(deque.isEmpty());
        deque.addFirst(25);
        deque.addFirst(33);
        deque.addLast(55);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());*/


    }
}
