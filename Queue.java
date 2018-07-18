import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class {@code Queue} represent a FIFO queue, implemented as a list with
 * generic type of item
 *
 *
 * @param <Item> generic type
 */
public class Queue<Item> implements Iterable<Item>
{
    /**
     *    NODE <-- NODE <-- NODE ... NODE <-- NODE <-- NODE
     *      ^                                           ^
     *    last                                         first
     *      ^                                           ^
     *    enqueue                                      dequeue
     */
    private Node first; // first is the oldest node which will be dequeued
    private Node last;  // last is the latest arrived node, enqueue after it
    private int count;  // count record the number of items in the queue

    private class Node {
        Item item;
        Node next;
    }

    public Queue() {}
    public void enqueue(Item item) {
        Node node = new Node();

        node.item = item;
        node.next = null;

        if (isEmpty()) {
            first = last = node;
        } else {
            // add new node to last end
            last.next = node;
            last = node;
        }
        count++;
    }

    public Item dequeue() {
        if (isEmpty()) { throw new NoSuchElementException(); }

        Item item = first.item; // get item to return
        first = first.next; // update first node to the second oldest node
        count--;

        /* last has to be set to null if only one node before dequeue */
        if(isEmpty()) last = null;

        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    // implement iterator in Iterable interface
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // implement Iterator interface
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next()
        {
            /* next must operate on existed node */
            if (isEmpty())  { throw new NoSuchElementException(); }

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {}
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Queue<Long> whitelist = new Queue<>();

        if (whitelist.isEmpty())
            StdOut.println("Queue is empty just after created");
        StdOut.println("Queue size just after created is " + whitelist.size());

        while (!in.isEmpty()) {
            whitelist.enqueue(in.readLong());
        }

        if (!whitelist.isEmpty())
            StdOut.println("Queue is not empty after enqueued");

        StdOut.println("Queue size after added items is " + whitelist.size());

        StdOut.println("Queue dump by iterator:");
        for (long num : whitelist)
        {
            StdOut.println(num);
        }

        StdOut.println("Queue dump by dequeue:");
        while(!whitelist.isEmpty())
        {
            StdOut.println(whitelist.dequeue());
        }
    }
}
